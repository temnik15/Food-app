package ru.rosystem.food_app.android.features.auth.store

/** Сообщения обновления состояния экрана авторизации. */
internal sealed interface Message {

    /** Обновить состояние полей ввода. */
    class UpdateInputState(
        val newValueForLogin: String? = null,
        val newValueForPassword: String? = null
    ): Message
}