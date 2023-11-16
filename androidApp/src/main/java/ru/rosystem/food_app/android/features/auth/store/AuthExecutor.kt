package ru.rosystem.food_app.android.features.auth.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor

/**
 * Mvi Executor экрана авторизации.
 */
internal class AuthExecutor: CoroutineExecutor<Intent, Action, AuthStore.State, Message, Label>() {
    override fun executeIntent(intent: Intent, getState: () -> AuthStore.State) {
        when (intent) {
            is Intent.ChangeLogin -> dispatch(Message.UpdateInputState(newValueForLogin = intent.value))
            is Intent.ChangePassword -> dispatch(Message.UpdateInputState(newValueForPassword = intent.value))
        }
    }
}