package sohu

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


class Kotlin {
    val x by lazy {
        "x"
    }
}

fun a(x: () -> Unit) {
    x()
}


suspend fun get(): String {
    delay(1000)
    repeat(100) {

    }
    return "123"
}

fun main() {
    println(sqrt(2f) / 2)
}

