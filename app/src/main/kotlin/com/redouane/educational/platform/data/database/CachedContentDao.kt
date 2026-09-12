package com.redouane.educational.platform.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.redouane.educational.platform.data.models.CachedContent
import com.redouane.educational.platform.data.models.ContentType
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedContentDao {
    @Query("SELECT * FROM cached_content ORDER BY cachedAt DESC")
    fun getAllCachedContent(): Flow<List<CachedContent>>

    @Query("SELECT * FROM cached_content WHERE id = :id")
    fun getCachedContentById(id: String): Flow<CachedContent?>

    @Query("SELECT * FROM cached_content WHERE contentId = :contentId AND contentType = :contentType")
    fun getCachedContent(contentId: String, contentType: ContentType): Flow<CachedContent?>

    @Query("SELECT SUM(size) FROM cached_content")
    fun getTotalCacheSize(): Flow<Long?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedContent(cachedContent: CachedContent)

    @Update
    suspend fun updateCachedContent(cachedContent: CachedContent)

    @Query("DELETE FROM cached_content WHERE id = :id")
    suspend fun deleteCachedContent(id: String)

    @Query("DELETE FROM cached_content WHERE cachedAt < :timestamp")
    suspend fun deleteOldCachedContent(timestamp: Long)
}
