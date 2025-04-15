package com.ykcoding.recipefinderapp.extensions

import com.ykcoding.recipefinderapp.helper.common.Quadruple
import com.ykcoding.recipefinderapp.helper.common.Quintuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T1: Any, T2: Any> zipSuspendable(
    block1: suspend () -> T1,
    block2: suspend () -> T2,
    coroutineContext: CoroutineContext = Dispatchers.Default
): Pair<T1, T2> {

    return withContext(coroutineContext) {
        val deferredJob1 = async { block1() }
        val deferredJob2 = async { block2() }
        Pair(deferredJob1.await(), deferredJob2.await())
    }
}

suspend fun <T1: Any, T2: Any, T3: Any> zipSuspendable(
    block1: suspend () -> T1,
    block2: suspend () -> T2,
    block3: suspend () -> T3,
    coroutineContext: CoroutineContext = Dispatchers.Default
): Triple<T1, T2, T3> {

    return withContext(coroutineContext) {
        val deferredJob1 = async { block1() }
        val deferredJob2 = async { block2() }
        val deferredJob3 = async { block3() }
        Triple(deferredJob1.await(), deferredJob2.await(), deferredJob3.await())
    }
}

suspend fun <T1: Any, T2: Any, T3: Any, T4: Any> zipSuspendable(
    block1: suspend () -> T1,
    block2: suspend () -> T2,
    block3: suspend () -> T3,
    block4: suspend () -> T4,
    coroutineContext: CoroutineContext = Dispatchers.Default
): Quadruple<T1, T2, T3, T4> {

    return withContext(coroutineContext) {
        val deferredJob1 = async { block1() }
        val deferredJob2 = async { block2() }
        val deferredJob3 = async { block3() }
        val deferredJob4 = async { block4() }
        Quadruple(deferredJob1.await(), deferredJob2.await(), deferredJob3.await(), deferredJob4.await())
    }
}


suspend fun <T1: Any, T2: Any, T3: Any, T4: Any, T5: Any> zipSuspendable(
    block1: suspend () -> T1,
    block2: suspend () -> T2,
    block3: suspend () -> T3,
    block4: suspend () -> T4,
    block5: suspend () -> T5,
    coroutineContext: CoroutineContext = Dispatchers.Default
): Quintuple<T1, T2, T3, T4, T5> {

    return withContext(coroutineContext) {
        val deferredJob1 = async { block1() }
        val deferredJob2 = async { block2() }
        val deferredJob3 = async { block3() }
        val deferredJob4 = async { block4() }
        val deferredJob5 = async { block5() }
        Quintuple(deferredJob1.await(), deferredJob2.await(), deferredJob3.await(), deferredJob4.await(), deferredJob5.await())
    }
}