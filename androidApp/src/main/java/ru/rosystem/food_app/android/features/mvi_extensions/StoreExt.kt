package ru.rosystem.food_app.android.features.mvi_extensions


import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.instancekeeper.instanceKeeper
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.stateKeeper
import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kotlin.reflect.KClass


inline fun <Intent : Any, Action : Any, Message : Any, reified State : Parcelable, Label : Any> StoreFactory.create(
    stateKeeper: StateKeeper,
    name: String? = null,
    autoInit: Boolean = true,
    initialState: State,
    bootstrapper: Bootstrapper<Action>? = null,
    noinline executorFactory: () -> Executor<Intent, Action, State, Message, Label>,
    reducer: Reducer<State, Message>,
    noinline saveStateSupplier: (State) -> State = { it }
): Store<Intent, State, Label> {

    val stateKey = State::class.toString()
    val savedState = stateKeeper.consume(stateKey, State::class)

    return create(
        name,
        autoInit,
        savedState ?: initialState,
        bootstrapper,
        executorFactory,
        reducer
    ).apply {
        stateKeeper.register(stateKey) {
            saveStateSupplier.invoke(state)
        }
    }
}

@Composable
inline fun <reified S : Store<*, *, *>> provideStore(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(
        LocalViewModelStoreOwner.current
    ) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    savedStateRegistryOwner: SavedStateRegistryOwner = checkNotNull(
        LocalSavedStateRegistryOwner.current
    ) {
        "No SavedStateRegistryOwner was provided via LocalSavedStateRegistryOwner"
    },
    noinline factory: (StateKeeper) -> S
): S {
    val localFactory = factory
    return remember {
        viewModelStoreOwner.instanceKeeper().getOrCreate {
            StoreHolder(localFactory(savedStateRegistryOwner.stateKeeper()))
        }.store
    }
}

fun <T : Store<*, *, *>> Fragment.findStore(kClass: KClass<T>): T {
    return instanceKeeper().getInstance<StoreHolder<T>>(kClass)?.store ?: parentFragment?.findStore(
        kClass
    ) ?: activity?.instanceKeeper()?.getInstance<StoreHolder<T>>(kClass)?.store
    ?: error("Не удалось найти Store=${kClass.qualifiedName}")
}


private inline fun <reified T : Any> InstanceKeeper.getInstance(key: Any) = get(key) as? T?

class StoreHolder<out T : Store<*, *, *>>(
    val store: T
) : InstanceKeeper.Instance {
    override fun onDestroy() {
        store.dispose()
    }
}