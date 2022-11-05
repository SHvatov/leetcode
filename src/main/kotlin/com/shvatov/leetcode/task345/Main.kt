package com.shvatov.leetcode.task345

/**
 * Link to the task: https://leetcode.com/problems/reverse-vowels-of-a-string/
 * Runtime: 503 ms, faster than 28.21% of Kotlin online submissions for Reverse Vowels of a String.
 * Memory Usage: 46.2 MB, less than 30.77% of Kotlin online submissions for Reverse Vowels of a String.
 */
class Solution {
    fun Char.isVowel() = this in VOWELS

    fun reverseVowels(s: String): String {
        val input = s.toCharArray()

        var (start, end) = 0 to input.size - 1
        while (start < end) {
            if (input[start].isVowel()) {
                while (!input[end].isVowel()) {
                    end--
                }

                val temp = input[start]
                input[start] = input[end]
                input[end] = temp

                end--
                start++
            } else {
                start++
            }
        }

        return input.joinToString(separator = "")
    }

    private companion object {
        val VOWELS = setOf('a', 'e', 'i', 'o', 'u') +
                setOf('A', 'E', 'I', 'O', 'U')
    }
}

fun main() {
    with(Solution()) {
        require(reverseVowels("hello") == "holle")
        require(reverseVowels("leetcode") == "leotcede")
        require(reverseVowels("aabbee") == "eebbaa")
        require(reverseVowels("aaee") == "eeaa")
        require(reverseVowels("bbaebb") == "bbeabb")
        require(reverseVowels("bbebb") == "bbebb")
        require(reverseVowels("bbbb") == "bbbb")
        require(reverseVowels("aA") == "Aa")
    }
}