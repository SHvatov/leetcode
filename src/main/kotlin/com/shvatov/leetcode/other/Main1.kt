package com.shvatov.leetcode.other

class Main1 {

    fun call(elements: IntArray): IntArray {
        if (elements.isEmpty()) return IntArray(0)

        val result = IntArray(elements.size) { 0 }
        var isOdd = (elements[0] % 2) == 0
        var counter = 0

        elements.withIndex().forEach { (index, element) ->
            // parity has not changed
            if (isOdd && element % 2 == 0
                || !isOdd && element % 2 == 1
            ) {
                counter++
            } else {
                (index - 1 downTo (index - counter)).forEach {
                    result[it] = counter
                }
                counter = 1
            }
            isOdd = element % 2 == 0
        }

        (result.size - 1 downTo (result.size - counter)).forEach {
            result[it] = counter
        }

        return result
    }

}

fun main() {
    with(Main1()) {
        println(call(intArrayOf(2, 1, 3, 2)).joinToString())
        println(call(intArrayOf(11, 3, 6, 0, 10)).joinToString())
        println(call(intArrayOf(7, 9, 3, 1, 8, 7)).joinToString())
    }
}