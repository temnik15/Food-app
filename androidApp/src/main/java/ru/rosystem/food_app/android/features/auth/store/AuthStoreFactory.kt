package ru.rosystem.food_app.android.features.auth.store

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ru.rosystem.food_app.android.features.mvi_extensions.create
import javax.inject.Inject

/** @SelfDocumented */
internal class AuthStoreFactory @Inject constructor(private val storeFactory: StoreFactory) {

    fun create(stateKeeper: StateKeeper): AuthStore = object : AuthStore,
        Store<Intent, AuthStore.State, Label> by storeFactory.create(
            stateKeeper = stateKeeper,
            name = AuthStore::class.java.name,
            initialState = AuthStore.State(),
            bootstrapper = SimpleBootstrapper(),
            executorFactory = { AuthExecutor() },
            reducer = AuthReducer()
        ) {}
}