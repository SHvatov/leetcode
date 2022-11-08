package com.shvatov.leetcode.task1544

import kotlin.math.abs

/**
 * Link to the task: https://leetcode.com/problems/make-the-string-great/
 * Runtime: 351 ms, faster than 6.67% of Kotlin online submissions for Make The String Great.
 * Memory Usage: 43 MB, less than 6.67% of Kotlin online submissions for Make The String Great.
 */
class Solution {

//    fun makeGood(s: String): String {
//        var temp = s
//        var weakSubString = POSSIBLE_WEAK_SUBSTRINGS.firstOrNull { temp.contains(it) }
//        while (weakSubString != null) {
//            temp = temp.replace(weakSubString, "")
//            weakSubString = POSSIBLE_WEAK_SUBSTRINGS.firstOrNull { temp.contains(it) }
//        }
//        return temp
//    }
//
//    private companion object {
//        val POSSIBLE_WEAK_SUBSTRINGS =
//            ('a'..'z')
//                .flatMap { listOf("$it${it.toUpperCase()}", "${it.toUpperCase()}$it") }
//                .toSet()
//    }

    private fun CharArray.remove(index: Int) {
        this[index] = Char.MIN_VALUE
    }

    private fun CharArray.neighbour(index: Int): Pair<Char, Int>? {
        var cursor = index + 1
        while (cursor < size && this[cursor] == Char.MIN_VALUE) {
            cursor++
        }

        if (cursor == size) {
            return null
        }

        return this[cursor] to cursor
    }

    private fun CharArray.findWeakNeighbours(): Pair<Int, Int>? {
        (0 until size - 1).forEach { cursor ->
            val (neighbourCh, neighbourPos) = neighbour(cursor) ?: return null
            if (abs(this[cursor] - neighbourCh) == 32) {
                return cursor to neighbourPos
            }
        }
        return null
    }

    fun makeGood(s: String): String {
        if (s.length == 1) return s
        if (s.length == 2) {
            return if (abs(s[0] - s[1]) == 32) "" else s
        }

        val temp = s.toCharArray()
        var weakNeighbours = temp.findWeakNeighbours()
        while (weakNeighbours != null) {
            temp.remove(weakNeighbours.first)
            temp.remove(weakNeighbours.second)
            weakNeighbours = temp.findWeakNeighbours()
        }
        return temp.filter { it != Char.MIN_VALUE }.joinToString("")
    }
}

fun main() {
    with(Solution()) {
//        println("a = ${makeGood("a")}")
//        println("aA = ${makeGood("aA")}")
//        println("abBA = ${makeGood("abBA")}")
//        println("abBAz = ${makeGood("abBAz")}")
        println("leEeetcode = ${makeGood("leEeetcode")}")
//        println("abBAcC = ${makeGood("abBAcC")}")
//        println("s = ${makeGood("s")}")
    }
}