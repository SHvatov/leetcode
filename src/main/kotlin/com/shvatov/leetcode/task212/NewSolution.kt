package com.shvatov.leetcode.task212


/**
 * Link to the task: https://leetcode.com/problems/word-search-ii/
 * Runtime: 473 ms, faster than 96.77% of Kotlin online submissions for Word Search II.
 * Memory Usage: 38.5 MB, less than 93.55% of Kotlin online submissions for Word Search II.
 * Inspired by: https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
 */
class Solution {

    private class TrieNode(
        val nodes: Array<TrieNode?> = Array(26) { null },
        var word: String? = null
    )

    private fun buildTrie(words: Array<String>): TrieNode {
        val root = TrieNode()

        for (word in words) {
            var tempRoot = root
            for (ch in word) {
                if (tempRoot.nodes[ch.toAlphaNumeric()] == null) {
                    tempRoot.nodes[ch.toAlphaNumeric()] = TrieNode()
                }
                tempRoot = tempRoot.nodes[ch.toAlphaNumeric()]!!
            }
            tempRoot.word = word
        }

        return root
    }

    private fun dfs(
        i: Int, j: Int,
        board: Array<CharArray>,
        trie: TrieNode,
        result: MutableList<String>
    ) {
        if (board[i][j] == VISITED_ELEMENT) return

        val ch = board[i][j]
        val ptr = trie.nodes[ch.toAlphaNumeric()] ?: return
        board[i][j] = VISITED_ELEMENT

        if (ptr.word != null) {
            result.add(ptr.word!!)
            ptr.word = null
        }

        if (i < board.size - 1) dfs(i + 1, j, board, ptr, result)
        if (i > 0) dfs(i - 1, j, board, ptr, result)
        if (j < board[0].size - 1) dfs(i, j + 1, board, ptr, result)
        if (j > 0) dfs(i, j - 1, board, ptr, result)

        board[i][j] = ch
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val trie = buildTrie(words)
        val result = mutableListOf<String>()
        board.indices.forEach { i ->
            board[0].indices.forEach { j ->
                dfs(i, j, board, trie, result)
            }
        }
        return result
    }

    private companion object {
        const val VISITED_ELEMENT = '@'

        fun Char.toAlphaNumeric() = this - 'a'
    }
}

fun main() {
    with(Solution()) {
        println(
            findWords(
                arrayOf(
                    charArrayOf('o', 'a', 'a', 'n'),
                    charArrayOf('e', 't', 'a', 'e'),
                    charArrayOf('i', 'h', 'k', 'r'),
                    charArrayOf('i', 'f', 'l', 'v')
                ), arrayOf("oath", "pea", "eat", "rain")
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('a', 'a')
                ), arrayOf("aaa")
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('a', 'a')
                ), arrayOf("a", "aa")
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('a', 'b'), charArrayOf('c', 'd')
                ), arrayOf("abcb")
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('o', 'a', 'a', 'n'),
                    charArrayOf('e', 't', 'a', 'e'),
                    charArrayOf('i', 'h', 'k', 'r'),
                    charArrayOf('i', 'f', 'l', 'v')
                ), arrayOf(
                    "oath", "pea", "eat", "rain", "oathi", "oathk", "oathf", "oate", "oathii", "oathfi", "oathfii"
                )
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                    charArrayOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a')
                ), arrayOf(
                    "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"
                )
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('a', 'b'), charArrayOf('a', 'a')
                ), arrayOf(
                    "aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"
                )
            )
        )
    }
}