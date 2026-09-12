package com.redouane.educational.platform

import android.app.Application
import com.redouane.educational.platform.data.database.RedouaneDatabase

class RedouaneApp : Application() {
    val database by lazy { RedouaneDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
    }
}
