package rui.kotlin

import kotlinx.coroutines.*
import kotlin.system.*

class KotlinTest {

}

suspend fun main() = coroutineScope  {
    launch { // 启动一个新协程并保持对这个作业的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
