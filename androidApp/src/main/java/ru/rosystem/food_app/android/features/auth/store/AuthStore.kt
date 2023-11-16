package ru.rosystem.food_app.android.features.auth.store

import android.os.Parcelable
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.parcelize.Parcelize

/** MviStore экрана авторизации. */
internal interface AuthStore: Store<Intent, AuthStore.State, Label> {

    @Parcelize
    data class State(
        val login: String = "",
        val password: String = ""
    ): Parcelable
}