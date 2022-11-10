package com.shvatov.leetcode.task1209

import java.util.*

/**
 * Link to the task: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 * Runtime: 839 ms, faster than 13.79% of Kotlin online submissions for Remove All Adjacent Duplicates in String II.
 * Memory Usage: 299.5 MB, less than 10.34% of Kotlin online submissions for Remove All Adjacent Duplicates in String II.
 */
class Solution {
//    fun removeDuplicates(s: String, k: Int): String {
//        val elements = ArrayDeque<Char>()
//        for (ch in s) {
//            if (!elements.isEmpty()) {
//                val removedElements = ArrayDeque<Char>()
//                for (i in (0 until k - 1)) {
//                    if (!elements.isEmpty() && elements.first == ch) {
//                        removedElements.push(elements.pop())
//                    } else {
//                        while (!removedElements.isEmpty()) {
//                            elements.push(removedElements.pop())
//                        }
//                        elements.push(ch)
//                        break
//                    }
//                }
//            } else {
//                elements.push(ch)
//            }
//        }
//        return elements.reversed().joinToString("")
//    }

    private data class CharToCount(val ch: Char, var cnt: Int = 1)

    fun removeDuplicates(s: String, k: Int): String {
        val elements = ArrayDeque<CharToCount>()
        for (ch in s) {
            if (elements.isEmpty()) {
                elements.push(CharToCount(ch))
                continue
            }

            if (elements.first.ch == ch) {
                elements.first.cnt++
                if (elements.first.cnt == k) {
                    elements.pop()
                }
            } else {
                elements.push(CharToCount(ch))
            }
        }
        return elements.reversed().joinToString("") { (ch, cnt) ->
            var temp = ""
            repeat(cnt) {
                temp += ch
            }
            temp
        }
    }
}

fun main() {
    with(Solution()) {
        // test the solution
        println(removeDuplicates("abcd", 2))
        println(removeDuplicates("deeedbbcccbdaa", 3))
        println(removeDuplicates("pbbcggttciiippooaais", 2))
    }
}