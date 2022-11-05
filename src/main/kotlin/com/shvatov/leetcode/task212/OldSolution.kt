package com.shvatov.leetcode.task212

import kotlin.math.abs

typealias Route = ArrayList<Pair<Int, Int>>
typealias Routes = ArrayList<Route>

/**
 * Link to the task: https://leetcode.com/problems/word-search-ii/
 */
@Deprecated(message = "See NewSolution instead")
class OldSolution {

    private fun Pair<Int, Int>.isNeighbour(other: Pair<Int, Int>) =
        first == other.first && (abs(second - other.second) == 1)
                || second == other.second && (abs(first - other.first) == 1)

    private fun findRoutes(letterPositions: Map<Char, List<Pair<Int, Int>>>, word: String): Routes {
        val routes = Routes()

        letterPositions[word[0]]?.let {
            it.forEach {
                routes.add(Route().apply {
                    add(it)
                })
            }
        } ?: return Routes()

        for (i in (1 until word.length)) {
            val availablePositions = letterPositions[word[i]]
                ?: return Routes()

            val routesToRemove = Routes()
            val routesToAdd = Routes()
            for (route in routes) {
                val lastPoint = route.last()
                val availableNeighbours = availablePositions
                    .filter { lastPoint.isNeighbour(it) }
                    .filter { it !in route }

                if (availableNeighbours.isEmpty()) {
                    routesToRemove.add(route)
                } else if (availableNeighbours.size == 1) {
                    route.add(availableNeighbours[0])
                } else {
                    routesToRemove.add(route)
                    availableNeighbours.forEach { neighbour ->
                        routesToAdd.add(Route().apply {
                            addAll(route)
                            add(neighbour)
                        })
                    }
                }
            }

            routes.removeAll(routesToRemove)
            routes.addAll(routesToAdd)

            if (routes.isEmpty()) {
                return Routes()
            }
        }

        return routes
    }

    private fun Array<CharArray>.consistOfOneChar(): Boolean {
        forEach { row ->
            row.forEach { symbol ->
                if (symbol != this[0][0]) {
                    return false
                }
            }
        }
        return true
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        if (board.isEmpty()) return emptyList()

        // case when board consists of only single char
        if (board.consistOfOneChar()) {
            val singleChar = setOf(board[0][0])
            val maximumWordSize = board.size * board[0].size
            return words.filter { word ->
                (setOf(*word.toCharArray().toTypedArray()) - singleChar).isEmpty()
                        && word.length <= maximumWordSize
            }
        }

        // other cases
        val letterPositions = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        board.indices.forEach { i ->
            board[0].indices.forEach { j ->
                letterPositions.putIfAbsent(board[i][j], mutableListOf())
                letterPositions.getValue(board[i][j]).add(i to j)
            }
        }

        return words.filter { findRoutes(letterPositions, it).isNotEmpty() }
    }
}

fun main() {
    with(OldSolution()) {
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
                ),
                arrayOf(
                    "oath", "pea", "eat", "rain",
                    "oathi", "oathk", "oathf", "oate",
                    "oathii", "oathfi", "oathfii"
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
                ),
                arrayOf(
                    "a", "aa", "aaa", "aaaa",
                    "aaaaa", "aaaaaa", "aaaaaaa",
                    "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"
                )
            )
        )
        println(
            findWords(
                arrayOf(
                    charArrayOf('a', 'b'), charArrayOf('a', 'a')
                ),
                arrayOf(
                    "aba", "baa", "bab", "aaab",
                    "aaa", "aaaa", "aaba"
                )
            )
        )
    }
}