package ru.rosystem.food_app.android.features.auth.store

/** Действия экрана авторизации. */
internal interface Intent {

    /** Изменить введеный логин. */
    class ChangeLogin(val value: String): Intent

    /** Изменить введеный пароль. */
    class ChangePassword(val value: String): Intent

    /** Обработать выход назад. */
    object HandleOnBackPressed: Intent
}