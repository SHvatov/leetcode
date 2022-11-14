package com.shvatov.leetcode.task151

import java.util.*

/**
 * Link to the task: https://leetcode.com/problems/reverse-words-in-a-string/submissions/
 * Runtime: 382 ms, faster than 37.96% of Kotlin online submissions for Reverse Words in a String.
 * Memory Usage: 39.1 MB, less than 61.11% of Kotlin online submissions for Reverse Words in a String.
 */
class Solution {
//    Runtime: 439 ms, faster than 6.48% of Kotlin online submissions for Reverse Words in a String.
//    Memory Usage: 46.3 MB, less than 5.56% of Kotlin online submissions for Reverse Words in a String.
//    fun reverseWords(s: String): String {
//        return s.trimIndent()
//            .split(" ")
//            .filter { it.isNotBlank() }
//            .reversed()
//            .joinToString(" ")
//    }

    fun reverseWords(s: String): String {
        val stack = ArrayDeque<String>()

        val sb = StringBuilder()
        for (ch in s) {
            if (ch != ' ') {
                sb.append(ch)
                continue
            }

            if (sb.isNotBlank()) {
                stack.push(sb.toString())
                sb.clear()
            }
        }

        if (sb.isNotBlank()) {
            stack.push(sb.toString())
        }

        sb.clear()
        while (stack.isNotEmpty()) {
            sb.append(stack.pop())

            if (stack.isNotEmpty()) {
                sb.append(" ")
            }
        }
        return sb.toString()
    }
}

fun main() {
    with(Solution()) {
        println(reverseWords("the sky is blue"))
        println(reverseWords("  hello world  "))
        println(reverseWords("a good   example"))
    }
}