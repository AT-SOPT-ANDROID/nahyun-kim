package org.sopt.at

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AtSoptApplication : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: AtSoptApplication
            private set
    }
}