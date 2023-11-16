package ru.rosystem.food_app.android.features.auth.di

import dagger.Component
import ru.rosystem.food_app.android.features.auth.store.AuthStoreFactory

@AuthScope
@Component(modules = [AuthModule::class])
internal interface AuthComponent {

    val storeFactory: AuthStoreFactory

    @Component.Factory
    interface Factory {

        fun create(): AuthComponent
    }
}