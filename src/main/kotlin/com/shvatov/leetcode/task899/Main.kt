package com.shvatov.leetcode.task899

private fun CharArray.concatToString() = joinToString("")
private fun List<Char>.concatToString() = joinToString("")

/**
 * Link to the task: https://leetcode.com/problems/orderly-queue/
 * Runtime: 529 ms, faster than 100.00% of Kotlin online submissions for Orderly Queue.
 * Memory Usage: 52.4 MB, less than 100.00% of Kotlin online submissions for Orderly Queue.
 *
 * Part with k >= 2 inspired by: https://leetcode.com/problems/orderly-queue/solution/
 */
class Solution {

    private class InfiniteQueueNode(val ch: Char, var next: InfiniteQueueNode? = null)

    private class InfiniteQueue(s: CharArray) {
        private val root: InfiniteQueueNode
        private var pointer: InfiniteQueueNode
        private val length = s.size

        init {
            root = InfiniteQueueNode(s[0])
            pointer = root

            var previous = root
            for (i in (1 until s.size)) {
                val temp = InfiniteQueueNode(s[i])
                previous.next = temp
                previous = temp
            }
            previous.next = root
        }

        fun movePointer() {
            pointer = pointer.next!!
        }

        fun isFullCircle() = pointer == root

        override fun toString(): String {
            val tempCharArray = CharArray(length) { Char.MAX_VALUE }
            var tempPointer = pointer
            var tempCounter = 0
            do {
                tempCharArray[tempCounter++] = tempPointer.ch
                tempPointer = tempPointer.next!!
            } while (tempPointer != pointer)
            return tempCharArray.concatToString()
        }
    }

    fun orderlyQueue(s: String, k: Int): String {
        if (s.length == 1) return s
        if (k >= 2) return s.toCharArray().sorted().concatToString()

        var minimumString = CharArray(s.length) { Char.MAX_VALUE }.concatToString()
        val queue = InfiniteQueue(s.toCharArray())
        do {
            val currentStr = queue.toString()
            if (currentStr < minimumString) {
                minimumString = currentStr
            }
            queue.movePointer()
        } while (!queue.isFullCircle())

        return minimumString
    }
}

fun main() {
    with(Solution()) {
        println(orderlyQueue("baaca", 3))
        println(orderlyQueue("cba", 1))
        println(orderlyQueue("v", 1))
        println(orderlyQueue("nhtq", 1))
        println(orderlyQueue("xmvzi", 2))
        println(
            orderlyQueue(
                "xitavoyjqiupzadbdyymyvuteolyeerecnuptghl" +
                        "zsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq",
                1
            )
        )
    }
}