package rui.kotlin

import kotlinx.coroutines.*
import rui.leetcode.LeetCode
import kotlin.system.*

fun main() {
    val s = listOf(1, 2, 3).asSequence()
    for (i in s) {
        print(i)
    }
    print(s)
}
