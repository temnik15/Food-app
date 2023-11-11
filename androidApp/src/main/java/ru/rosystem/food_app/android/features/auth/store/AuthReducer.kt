package ru.rosystem.food_app.android.features.auth.store

import com.arkivanov.mvikotlin.core.store.Reducer

/** @SelfDocumented */
internal class AuthReducer : Reducer<AuthStore.State, Message> {
    override fun AuthStore.State.reduce(msg: Message): AuthStore.State {
        return when (msg) {
            is Message.UpdateInputState -> copy(
                login = msg.newValueForLogin ?: login,
                password = msg.newValueForPassword ?: password
            )
        }
    }

}