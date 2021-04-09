package br.uol.ps.cards

import androidx.multidex.MultiDexApplication
import br.uol.ps.core.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MvpApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MvpApplication)
            modules(AppModule.instance)
        }
    }
}