package com.shvatov.leetcode.task1323

/**
 * Link to the task: https://leetcode.com/problems/maximum-69-number/
 * Runtime: 211 ms, faster than 86.67% of Kotlin online submissions for Maximum 69 Number.
 * Memory Usage: 33.7 MB, less than 70.00% of Kotlin online submissions for Maximum 69 Number.
 */
class Solution {
    fun maximum69Number(num: Int): Int {
        var max = num
        var temp = num

        // 1
        if (temp % 10 == 6) {
            max = num + 3
        }
        temp /= 10
        if (temp == 0) return max

        // 10
        if (temp % 10 == 6) {
            max = num + 30
        }
        temp /= 10
        if (temp == 0) return max

        // 100
        if (temp % 10 == 6) {
            max = num + 300
        }
        temp /= 10
        if (temp == 0) return max

        // 1000
        if (temp % 10 == 6) {
            return num + 3000
        }
        return max
    }

//    fun maximum69Number(num: Int): Int {
//        var div = 1000
//        while (div > 0) {
//            val res = (num / div) % 10
//            if (res != 0) {
//                if (res == 6) {
//                    return num + 3 * div
//                }
//            }
//            div /= 10
//        }
//        return num
//    }
}

fun main() {
    with(Solution()) {
        require(maximum69Number(9669) == 9969)
        require(maximum69Number(9996) == 9999)
        require(maximum69Number(9999) == 9999)
        require(maximum69Number(6) == 9)
        require(maximum69Number(9) == 9)
    }
}