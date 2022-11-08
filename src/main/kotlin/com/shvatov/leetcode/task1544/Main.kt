package com.shvatov.leetcode.task1544

/**
 * Link to the task: https://leetcode.com/problems/make-the-string-great/
 */
class Solution {

    fun makeGood(s: String): String {
        var temp = s
        var weakSubString = POSSIBLE_WEAK_SUBSTRINGS.firstOrNull { temp.contains(it) }
        while (weakSubString != null) {
            temp = temp.replace(weakSubString, "")
            weakSubString = POSSIBLE_WEAK_SUBSTRINGS.firstOrNull { temp.contains(it) }
        }
        return temp
    }

    private companion object {
        val POSSIBLE_WEAK_SUBSTRINGS =
            ('a'..'z')
                .flatMap { listOf("$it${it.toUpperCase()}", "${it.toUpperCase()}$it") }
                .toSet()
    }
}

fun main() {
    with(Solution()) {
        println("a = ${makeGood("a")}")
        println("aA = ${makeGood("aA")}")
        println("abBA = ${makeGood("abBA")}")
        println("abBAz = ${makeGood("abBAz")}")
        println("leEeetcode = ${makeGood("leEeetcode")}")
        println("abBAcC = ${makeGood("abBAcC")}")
        println("s = ${makeGood("s")}")
    }
}