package com.example.rx_sample_app.rx

import com.example.rx_sample_app.model.User
import io.reactivex.schedulers.Schedulers
import android.util.Log
import com.example.rx_sample_app.data.UserDataProvider
import io.reactivex.*
import io.reactivex.functions.Function
import io.reactivex.subjects.ReplaySubject
import java.util.ArrayList
import java.util.concurrent.TimeUnit

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/7/2020
 */
object RxDataManager {

    private val TAG = RxDataManager::class.java.simpleName
    private val userDataProvider: UserDataProvider = UserDataProvider()

    //
    // OBSERVABLES
    //
    fun justExampleObservable(): Observable<Int> {
        return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    fun createExampleObservable(): Observable<User> {
        return Observable.create { emitter ->
            for (user in userDataProvider.users) {
                emitter.onNext(user)
            }
            emitter.onComplete()
        }
    }

    //
    // SINGLE
    //
    fun singleExample(): Single<String> {
        return Single.just("This is a Single example.")
    }

    //
    // MAYBE
    //
    fun maybeExample(): Maybe<String> {
        return Maybe.just("This is a Maybe example.")
    }

    fun emptyMaybeExample(): Maybe<String> {
        return Maybe.empty()
    }

    //
    // THREADING EXAMPLES
    //
    fun threadingExampleMixedThreads(): Observable<Int> {
        return Observable.just("long", "longer", "longest")
            .subscribeOn(Schedulers.io())
            .map { s: String ->
                Log.d(TAG, " map() element: '" + s + "', on thread: ${Thread.currentThread().name}")
                s.length
            }
            .observeOn(Schedulers.computation())
            .filter { integer: Int ->
                Log.d(TAG, " filter() element: " + integer + ", on thread: ${Thread.currentThread().name}")
                integer > 6
            }
    }

    fun threadingExampleMainThread(): Observable<Int> {
        return Observable.just("long", "longer", "longest")
            .map { s: String ->
                Log.d(TAG, " map() element: '" + s + "', on thread: ${Thread.currentThread().name}")
                s.length
            }
            .filter { integer: Int ->
                Log.d(TAG, " filter() element: " + integer + ", on thread: ${Thread.currentThread().name}")
                integer > 6
            }
    }

    //
    // HOT AND COLD OBSERVABLES
    //
    fun coldObservable(): Observable<Long> {
        return Observable.interval(500, TimeUnit.MILLISECONDS)
    }

    fun hotObservable(): ReplaySubject<Int> {
        return ReplaySubject.create()
    }

    //
    // OPERATORS
    //
    fun mapExampleObservable(): Observable<String> {
        return justExampleObservable().map(createStringFromIntFunction())
    }

    fun filterExampleObservable(): Observable<Int> {
        return justExampleObservable().filter { integer -> integer % 2 == 0 }
    }

    fun zipExampleObserver(): Observable<List<User>> {
        return Observable.zip(
            footballFanUsersObservable(),
            handballFanUsersObservable(),
            { footballFans, handballFans ->
                val userWhoLoveBoth: MutableList<User> = ArrayList()
                for (footballFan in footballFans) {
                    if (handballFans.contains(footballFan)) {
                        userWhoLoveBoth.add(footballFan)
                    }
                }
                userWhoLoveBoth
            })
    }

    fun footballFanUsersObservable(): Observable<List<User>> {
        return Observable.create { emitter ->
            val footballFans = ArrayList<User>()
            for (user in userDataProvider.users) {
                if (user.loveFootball) {
                    footballFans.add(user)
                }
            }
            emitter.onNext(footballFans)
            emitter.onComplete()
        }
    }

    fun handballFanUsersObservable(): Observable<List<User>> {
        return Observable.create { emitter ->
            val handballFans = ArrayList<User>()
            for (user in userDataProvider.users) {
                if (user.loveHandball) {
                    handballFans.add(user)
                }
            }
            emitter.onNext(handballFans)
            emitter.onComplete()
        }
    }

    //
    // Rx FUNCTIONS
    //
    private fun createStringFromIntFunction(): Function<Int, String> {
        return Function { integer -> integer.toString() }
    }
}