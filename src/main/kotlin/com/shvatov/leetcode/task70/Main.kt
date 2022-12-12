package com.shvatov.leetcode.task70

/**
 * Link to the task: https://leetcode.com/problems/climbing-stairs/
 */
class Solution {
    fun climbStairs(n: Int): Int {
        if (n == 1) return 1
        if (n == 2) return 2

        val dp = IntArray(n + 1) { 0 }
        dp[1] = 1
        dp[2] = 2

        (3 until dp.size).forEach { i ->
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }
}

fun main() {
    with(Solution()) {
        println(climbStairs(2))
        println(climbStairs(3))
    }
}