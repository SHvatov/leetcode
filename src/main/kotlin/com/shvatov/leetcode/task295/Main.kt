package com.shvatov.leetcode.task295

import java.util.*


/**
 * Link to the task: https://leetcode.com/problems/find-median-from-data-stream/
 * Runtime: 3605 ms, faster than 5.62% of Kotlin online submissions for Find Median from Data Stream.
 * Memory Usage: 147.7 MB, less than 31.46% of Kotlin online submissions for Find Median from Data Stream.
 */
class MedianFinder {

    private val elements = IntArray(50_000) { Int.MIN_VALUE }
    private var actualSize = 0

    fun addNum(num: Int) {
        if (actualSize == 0) {
            elements[actualSize++] = num
            return
        }

        var index = Arrays.binarySearch(elements, 0, actualSize, num)
        if (index < 0) index = -index - 1
        System.arraycopy(elements, index, elements, index + 1, actualSize - index + 1)
        elements[index] = num
        actualSize++
    }

    // time = O(1)
    // memory = O(2 * N) = O(N)
    fun findMedian(): Double {
        return if (actualSize % 2 == 1) {
            elements[actualSize / 2].toDouble()
        } else {
            val middle = actualSize / 2
            (elements[middle] + elements[middle - 1]) / 2.0
        }
    }

}

fun main() {
    // [6.00000,8.00000,
    // 6.00000,6.00000,6.00000,
    // 5.50000,6.00000,5.50000,
    // 5.00000,4.00000,3.00000]
    with(MedianFinder()) {
        addNum(6)
        println(findMedian())
        addNum(10)
        println(findMedian())
        addNum(2)
        println(findMedian())
        addNum(6)
        println(findMedian())
        addNum(5)
        println(findMedian())
        addNum(0)
        println(findMedian())
        addNum(6)
        println(findMedian())
        addNum(3)
        println(findMedian())
        addNum(1)
        println(findMedian())
        addNum(0)
        println(findMedian())
        addNum(0)
        println(findMedian())
    }
}