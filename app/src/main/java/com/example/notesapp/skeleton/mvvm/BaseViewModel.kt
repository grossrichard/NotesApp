package com.example.notesapp.skeleton.mvvm

import androidx.lifecycle.*
import com.example.notesapp.skeleton.mvvm.event.GenericErrorEvent
import com.example.notesapp.skeleton.mvvm.event.LiveEvent
import com.example.notesapp.skeleton.mvvm.event.LiveEventMap
import com.example.notesapp.util.RxUtils
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlin.reflect.KClass

/**
 * Created by Richard Gross on 2020-01-13
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {

    private val liveEventMap: LiveEventMap = LiveEventMap()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onViewCreated() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() { // override in child classes
    }

    protected open fun initData() { // override in child classes
    }

    protected open fun loadData() { // override in child classes
        loading.value = true
    }

    protected fun onError(throwable: Throwable?) {
        loading.value = false
        publish(GenericErrorEvent(throwable))
    }

    open fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected open fun <T : LiveEvent> publish(event: T) = liveEventMap.publish(event)

    open fun <T : LiveEvent> subscribe(
        lifecycleOwner: LifecycleOwner,
        eventClass: KClass<T>,
        eventObserver: Observer<T>
    ) = liveEventMap.subscribe(lifecycleOwner, eventClass, eventObserver)

    protected open fun <T> subscribeSingle(
        singleObservable: Single<T>,
        onSuccess: Consumer<in T>,
        onError: Consumer<in Throwable?>
    ) {
        addSubscription(
            singleObservable.compose<T>(RxUtils.applySingleSchedulers()).subscribe(
                onSuccess,
                onError
            )
        )
    }

    protected open fun <T> subscribeSingle(
        singleObservable: Single<T>,
        onSuccess: Consumer<in T>
    ) {
        subscribeSingle(singleObservable, onSuccess, Consumer(this::onError))
    }

}