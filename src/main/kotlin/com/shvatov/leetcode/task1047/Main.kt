package com.shvatov.leetcode.task1047

import java.util.*

/**
 * Link to the task: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * Runtime: 634 ms, faster than 53.85% of Kotlin online submissions for Remove All Adjacent Duplicates In String.
 * Memory Usage: 60.8 MB, less than 23.08% of Kotlin online submissions for Remove All Adjacent Duplicates In String.
 */
class Solution {
    fun removeDuplicates(s: String): String {
        val elements = ArrayDeque<Char>()
        s.forEach { ch ->
            if (!elements.isEmpty() && elements.first == ch) {
                elements.pop()
            } else {
                elements.push(ch)
            }
        }
        return elements.reversed().joinToString("")
    }
}

fun main() {
    with(Solution()) {
        // test the solution
        println(removeDuplicates("abbaca"))
        println(removeDuplicates("azxxzy"))
    }
}