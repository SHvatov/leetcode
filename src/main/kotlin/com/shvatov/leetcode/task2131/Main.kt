package com.shvatov.leetcode.task2131

/**
 * Link to the task: https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/submissions/
 * Runtime: 1378 ms, faster than 9.09% of Kotlin online submissions for Longest Palindrome by Concatenating Two Letter Words.
 * Memory Usage: 59.9 MB, less than 86.36% of Kotlin online submissions for Longest Palindrome by Concatenating Two Letter Words.
 */
class Solution {
    fun longestPalindrome(words: Array<String>): Int {
        // check if it is possible to create a palindrome
        val __words = words.toMutableSet()
        var flag = false
        for (word in __words) {
            if (flag) {
                break
            }

            word.reversed().let { reversedWord ->
                if (reversedWord == word || reversedWord in __words) {
                    flag = true
                }
            }
        }

        if (!flag) return 0


        // main algorithm
        var cnt = 0
        val palindromes = mutableMapOf<String, Int>()
        val _words = mutableListOf<String>()

        for (word in words) {
            val reversedWord = word.reversed()

            if (reversedWord == word) {
                palindromes[word] = (palindromes[word] ?: 0) + 1
                continue
            }

            if (_words.remove(reversedWord)) {
                cnt++
            } else {
                _words.add(word)
            }
        }

        // number of symbols, that should form the palindromes after concatenation
        val a = cnt * 2 * 2

        if (palindromes.isEmpty()) {
            return a
        }

        val o = palindromes.filter { (_, v) -> v % 2 != 0 }
            .maxBy { it.value }

        // maximum number of the occurring actual palindrome strings, should be put in the middle
        val b = if (o != null) {
            palindromes.remove(o.key)
            o.value * 2
        } else {
            palindromes.maxBy { it.value }!!.let {
                palindromes.remove(it.key)
                it.value * 2
            }
        }

        // other palindromes should be in pairs
        val c = palindromes.map { (_, v) ->
            if (v % 2 == 0) v else v - 1
        }.sum() * 2

        return a + b + c
    }
}

fun main() {
    with(Solution()) {
        // test the solution
        require(longestPalindrome(arrayOf("lc", "cl", "gg")) == 6)
        require(longestPalindrome(arrayOf("ab", "ty", "yt", "lc", "cl", "ab")) == 8)
        require(longestPalindrome(arrayOf("cc", "ll", "xx")) == 2)
        // dd_dd_dd_aa_aa_aa_aa_aa_dd_dd_dd
        require(
            longestPalindrome(
                arrayOf(
                    "dd",
                    "aa",
                    "bb",
                    "dd",
                    "aa",
                    "dd",
                    "bb",
                    "dd",
                    "aa",
                    "cc",
                    "bb",
                    "cc",
                    "dd",
                    "cc"
                )
            ) == 22
        )
        require(
            longestPalindrome(
                arrayOf(
                    "nn", "nn", "hg", "gn", "nn", "hh", "gh", "nn", "nh", "nh"
                )
            ) == 14
        )
        require(
            longestPalindrome(
                arrayOf(
                    "qo", "fo", "fq", "qf", "fo", "ff", "qq", "qf", "of", "of", "oo", "of", "of", "qf", "qf", "of"
                )
            ) == 14
        )
    }
}