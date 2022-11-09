package com.shvatov.leetcode.task901

import java.util.*

/**
 * Link to the task: https://leetcode.com/problems/online-stock-span/
 * Runtime: 603 ms, faster than 100.00% of Kotlin online submissions for Online Stock Span.
 * Memory Usage: 65.5 MB, less than 92.31% of Kotlin online submissions for Online Stock Span.
 */
class StockSpanner {

    private data class StockPriceToSpan(val price: Int, val span: Int)

    private val stack = ArrayDeque<StockPriceToSpan>()

    fun next(price: Int): Int {
        var result = 1
        while (!stack.isEmpty() && stack.first.price <= price) {
            result += stack.pop().span
        }
        stack.push(StockPriceToSpan(price, result))
        return result
    }

}

fun main() {
    with(StockSpanner()) {
        println("next(100)=" + next(100)) // return 1
        println("next(80)=" + next(80))  // return 1
        println("next(60)=" + next(60))  // return 1
        println("next(70)=" + next(70))  // return 2
        println("next(60)=" + next(60))  // return 1
        println("next(75)=" + next(75))  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        println("next(85)=" + next(85))  // return 6
    }
}