package com.example.notesapp.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by stepan on 28.10.2017.
 */
object RxUtils {
    fun <T> applySchedulers(observable: Observable<T>): Observable<T> =
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun <T> applySchedulers(maybe: Maybe<T>): Maybe<T> =
        maybe.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun <T> applySingleSchedulers(): SingleTransformer<T, T> =
        SingleTransformer { upstream: Single<T> ->
            upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        }

    fun applyCompletableSchedulers(): CompletableTransformer =
        CompletableTransformer { upstream: Completable ->
            upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        }
}