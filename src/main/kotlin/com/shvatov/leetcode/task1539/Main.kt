package com.shvatov.leetcode.task1539

/**
 * Link to the task:
 */
class Solution {
    fun findKthPositive(arr: IntArray, k: Int): Int {
        val full = BooleanArray(10001) { false }
        for (el in arr) {
            full[el] = true
        }
        return full.withIndex()
            .filter { !it.value }
            .drop(k)
            .firstOrNull()
            ?.index ?: -1
    }
}

fun main() {
    with(Solution()) {
        println(findKthPositive(intArrayOf(2, 3, 4, 7, 11), 5))
        println(findKthPositive(intArrayOf(1, 2, 3, 4), 2))
        println(findKthPositive(intArrayOf(2), 1))
    }
}