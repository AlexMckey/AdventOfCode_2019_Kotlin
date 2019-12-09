package Coroutins

import kotlin.concurrent.thread

fun main() {
    thread {
        // launch a new coroutine in background and continue
        Thread.sleep(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(1500L) // block main thread for 2 seconds to keep JVM alive
}