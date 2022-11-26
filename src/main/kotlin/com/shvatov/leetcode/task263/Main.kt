package com.shvatov.leetcode.task263

/**
 * Link to the task: https://leetcode.com/problems/ugly-number/
 */
class Solution {
    fun isUgly(n: Int): Boolean {
        if (n <= 0) return false

        var temp = n
        var currentFactor = 0
        while (temp != 1) {
            var factor = PRIME_FACTORS[currentFactor]
            while (temp % factor != 0 && currentFactor < 2) {
                factor = PRIME_FACTORS[++currentFactor]
            }

            if (temp % factor != 0) {
                return false
            }
            temp /= factor
        }
        return true
    }

    private companion object {
        val PRIME_FACTORS = listOf(5, 3, 2)
    }
}

fun main() {
    with(Solution()) {
        println("8 -> ${isUgly(8)}")
        println("2 -> ${isUgly(2)}")
        println("5 -> ${isUgly(5)}")
        println("6 -> ${isUgly(6)}")
        println("7 -> ${isUgly(7)}")
        println("35 -> ${isUgly(35)}")
        println("36 -> ${isUgly(36)}")
        println("13 -> ${isUgly(13)}")
        println("14 -> ${isUgly(14)}")
        println("0 -> ${isUgly(0)}")
        println("1 -> ${isUgly(1)}")
        println("-1 -> ${isUgly(-1)}")
        println("-35 -> ${isUgly(-35)}")
    }
}