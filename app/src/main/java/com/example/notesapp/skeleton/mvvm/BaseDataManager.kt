package com.example.notesapp.skeleton.mvvm

import android.annotation.SuppressLint
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class BaseDataManager {

    protected fun <T> subscribe(ps: PublishSubject<T>, c: Consumer<T>?): Disposable =
        ps.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(c)

    @SuppressLint("CheckResult")
    protected fun <T> publish(ps: PublishSubject<Notification<T>?>, o: Observable<T>) {
        o.compose(applySchedulers()).subscribe({ ps.onNext(Notification.createOnNext(it)) },
            { ps.onNext(Notification.createOnError(it)) })
    }

    companion object {
        protected fun <T> applySchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable: Observable<T> ->
                observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
            }
        }
    }
}