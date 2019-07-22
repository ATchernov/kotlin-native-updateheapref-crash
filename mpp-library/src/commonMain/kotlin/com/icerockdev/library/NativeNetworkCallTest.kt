package com.icerockdev.library

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Aleksey Mikhailov <Aleksey.Mikhailov@icerockdev.com> on 2019-07-22.
 */
class NativeNetworkCallTest(private val nativeInterface: NativeCallInterface) {
    suspend fun testCall(): String {
        return suspendCoroutine { continuation ->
            nativeInterface.testCall { data, error ->
                when {
                    error != null -> continuation.resumeWithException(error)
                    data != null -> continuation.resume(data)
                    else -> throw IllegalStateException("invalid data from native side - data & error is null")
                }
            }
        }
    }
}

interface NativeCallInterface {
    fun testCall(callback: (data: String?, error: Throwable?) -> Unit)
}

class TestViewPresenter(
    private val networkCallTest: NativeNetworkCallTest,
    private val view: View
) {
    private val coroutineScope = CoroutineScope(Dispatchers.UI + SupervisorJob())
    private var counter = 0

    fun onButtonPressed() {
        coroutineScope.launch {
            val iteration = ++counter
            val result = networkCallTest.testCall()
            view.showMessage("iteration $iteration, result $result")
        }
    }

    interface View {
        fun showMessage(string: String)
    }
}

expect val Dispatchers.UI: CoroutineDispatcher