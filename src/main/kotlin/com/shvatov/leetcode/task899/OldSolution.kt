package com.shvatov.leetcode.task899

/**
 * Link to the task: https://leetcode.com/problems/orderly-queue/
 */
@Deprecated(message = "see NewSolution instead")
class OldSolution {
    private class InfiniteQueueNode(
        val ch: Char,
        var next: InfiniteQueueNode? = null,
        var previous: InfiniteQueueNode? = null
    )

    private class InfiniteQueue(queue: CharArray) {
        private val root: InfiniteQueueNode
        private var pointer: InfiniteQueueNode

        private val persistedQueue = CharArray(queue.size) { Char.MAX_VALUE }
        private var persistedQueuePtr = 0

        init {
            root = InfiniteQueueNode(queue[0])
            pointer = root

            var previous = root
            for (i in (1 until queue.size)) {
                val temp = InfiniteQueueNode(queue[i])
                previous.next = temp
                temp.previous = previous
                previous = temp
            }
            previous.next = root
            root.previous = previous
        }

        fun movePointer(): InfiniteQueue {
            pointer = pointer.next!!
            return this
        }

        fun charAtPointer(): Char = pointer.ch

        fun minimumCharAfterPointer(): Char {
            var minimum = Char.MAX_VALUE
            var temp = pointer.next!!
            while (temp != pointer) {
                if (temp.ch < minimum) {
                    minimum = temp.ch
                }
                temp = temp.next!!
            }
            return minimum
        }

        fun persistCharAtPointer() {
            if (persistedQueuePtr == persistedQueue.size) {
                return
            }
            persistedQueue[persistedQueuePtr++] = charAtPointer()
            pointer.previous!!.next = pointer.next
            pointer.next!!.previous = pointer.previous
            pointer = pointer.next!!
        }

        fun pointerOutOfModificationRange(k: Int) =
            persistedQueuePtr >= k || persistedQueuePtr == persistedQueue.size

        fun persistRemainingChars() {
            while (pointer.next != pointer) {
                persistCharAtPointer()
            }
            persistCharAtPointer()
        }

        fun getOrderedString() = persistedQueue.joinToString("")
    }

    fun orderlyQueue(s: String, k: Int): String {
        if (s.length == 1) return s

        if (k >= 2) return s.toCharArray().sorted().joinToString("")

        val queue = InfiniteQueue(s.toCharArray())

        fun step() {
            val minimumValue = queue.minimumCharAfterPointer()
            while (queue.charAtPointer() != minimumValue || queue.minimumCharAfterPointer() == minimumValue) {
                queue.movePointer()
            }
            queue.persistCharAtPointer()
        }

        while (!queue.pointerOutOfModificationRange(k)) {
            step()
        }
        queue.persistRemainingChars()

        return queue.getOrderedString()
    }
}

fun main() {
    with(OldSolution()) {
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