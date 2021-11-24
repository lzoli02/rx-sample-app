package com.example.rx_sample_app

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.rx_sample_app.model.User
import com.example.rx_sample_app.rx.RxDataManager
import io.reactivex.MaybeObserver
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class MainActivity : Activity() {

    private val TAG = MainActivity::class.java.canonicalName
    private val TAG_TEST = "TEST_TAG"
    private val TAG_DISPOSE = "DISPOSE_TAG"

    private lateinit var mDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //createExample();
        //justExample();

        //singleExample();

        //maybeExample();
        //maybeEmptyExample();

        //threadingExampleMainThread();
        //threadingExampleMixedThreads();

        //mapExample();
        //filterExample();
        zipExample()
    }

    override fun onDestroy() {
        Log.d(TAG_DISPOSE, "onDestroy()")
        if (!mDisposable.isDisposed()) {
            Log.d(TAG_DISPOSE, "Disposing disposable...")
            mDisposable.dispose()
        } else {
            Log.d(TAG_DISPOSE, "Disposable is NULL or already disposed!")
        }
        super.onDestroy()
    }

    //
    // PRIVATE METHODS
    //

    private fun createExample() {
        RxDataManager.createExampleObservable()
            .subscribe(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "createExample() - subscribed...")
                }

                override fun onNext(user: User) {
                    Log.d(TAG_TEST, "Next user: " + user.toString())
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "createExample() - An error occurred: " + e.message)
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "createExample() - Completed!")
                }
            })
//            .subscribe(
//                { user -> Log.d(TAG_TEST, "Next user: $user") }, // onNext
//                { error -> Log.e(TAG_TEST, "createExample() - An error occurred: ${error.message}") }, // onError
//                { Log.d(TAG_TEST, "createExample() - Completed!") }, // onComplete
//                { disposable ->
//                    this.mDisposable = disposable
//                    Log.d(TAG_TEST, "createExample() - subscribed...")
//                } // onSubscribe
//            )
    }

    private fun justExample() {
        RxDataManager.justExampleObservable()
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "justExample() - subscribed...")
                }

                override fun onNext(integer: Int) {
                    Log.d(TAG_TEST, "Next item: $integer")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "justExample() - An error occurred: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "justExample() - Completed!")
                }
            })
    }

    private fun singleExample() {
        RxDataManager.singleExample()
            .subscribe(object : SingleObserver<String?> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "singleExample() - subscribed...")
                }

                override fun onSuccess(s: String) {
                    Log.d(TAG_TEST, "singleExample() - The result: $s")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "singleExample() - An error occurred: ${e.message}")
                }
            })
    }

    private fun maybeExample() {
        RxDataManager.maybeExample()
            .subscribe(object : MaybeObserver<String?> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "maybeExample() - subscribed...")
                }

                override fun onSuccess(s: String) {
                    Log.d(TAG_TEST, "maybeExample() - The result: $s")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "maybeExample() - An error occurred: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "maybeExample() - Completed!")
                }
            })
    }

    private fun maybeEmptyExample() {
        RxDataManager.emptyMaybeExample()
            .subscribe(object : MaybeObserver<String?> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "maybeEmptyExample() - subscribed...")
                }

                override fun onSuccess(s: String) {
                    Log.d(TAG_TEST, "maybeEmptyExample() - The result: $s")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "maybeEmptyExample() - An error occurred: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "maybeEmptyExample() - Completed!")
                }
            })
    }

    private fun threadingExampleMixedThreads() {
        mDisposable = RxDataManager.threadingExampleMixedThreads()
            .subscribe { length: Int -> Log.d(TAG_TEST, "Result length = $length") }
    }

    private fun threadingExampleMainThread() {
        mDisposable = RxDataManager.threadingExampleMainThread()
            .subscribe { length: Int -> Log.d(TAG_TEST, "Result length = $length") }
    }

    private fun mapExample() {
        RxDataManager.mapExampleObservable()
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "mapExample() - subscribed...")
                }

                override fun onNext(s: String) {
                    Log.d(TAG_TEST, "Next item: $s")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "mapExample() - An error occurred: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "mapExample() - Completed!")
                }
            })
    }

    private fun filterExample() {
        RxDataManager.filterExampleObservable()
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "filterExample() - subscribed...")
                }

                override fun onNext(s: Int) {
                    Log.d(TAG_TEST, "Next item: $s")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "mapExample() - An error occurred: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "filterExample() - Completed!")
                }
            })
    }

    private fun zipExample() {
        RxDataManager.zipExampleObserver()
            .subscribe(object : Observer<List<User>> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                    Log.d(TAG_TEST, "zipExample() - subscribed...")
                }

                override fun onNext(users: List<User>) {
                    Log.d(TAG_TEST, "Users who love football and handball: ")
                    for (user in users) {
                        Log.d(TAG_TEST, user.toString())
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG_TEST, "zipExample() - An error occurred: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG_TEST, "zipExample() - Completed!")
                }
            })
    }
}