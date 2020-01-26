package com.example.notesapp.model

import com.example.notesapp.skeleton.mvvm.BaseDataManager
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

/**
 * Class for sending events in the Observer pattern fashion
 *
 *
 * Created by Hynek Slahunek on 10.04.17.
 */
abstract class SimpleDataManager<T> : BaseDataManager() {

    private val mPublishSubject: PublishSubject<Notification<T>?> = PublishSubject.create()

    fun publish(observable: Observable<T>?) = publish(mPublishSubject, observable!!)

    fun subscribe(consumer: Consumer<Notification<T>?>?): Disposable =
        subscribe(mPublishSubject, consumer)
}