package com.redouane.educational.platform.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit الإعداد مع دعم التخزين المؤقت والاتصال دون إنترنت
 */
class RetrofitClient(private val context: Context) {
    
    companion object {
        private const val BASE_URL = "https://api.moroccan-education.local/"
        private const val CACHE_SIZE = 50L * 1024L * 1024L // 50 MB
    }
    
    private val cache = Cache(context.cacheDir, CACHE_SIZE)
    
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
    
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .addNetworkInterceptor { chain ->
            var request = chain.request()
            
            // إضافة رأس التخزين المؤقت
            request = if (isNetworkAvailable()) {
                request.newBuilder()
                    .header("Cache-Control", "public, max-age=3600")
                    .build()
            } else {
                request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=604800")
                    .build()
            }
            chain.proceed(request)
        }
        .build()
    
    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()
    
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    
    val educationApi: EducationApi = retrofit.create(EducationApi::class.java)
    val contentApi: ContentApi = retrofit.create(ContentApi::class.java)
}
