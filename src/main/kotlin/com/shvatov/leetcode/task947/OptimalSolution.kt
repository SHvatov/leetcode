package com.shvatov.leetcode.task947

/**
 * Link to the task: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
class Solution {

    private class DisjointSet(var size: Int) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size) { 0 }

        fun find(x: Int): Int {
            return if (parent[x] != x) {
                find(parent[x])
                    .also { y ->
                        parent[x] = y
                    }
            } else x
        }

        fun union(x: Int, y: Int) {
            val parentX = find(x)
            val parentY = find(y)

            if (parentX == parentY) return

            if (rank[parentX] > rank[parentY]) {
                parent[parentY] = parentX
                rank[parentX]++
            } else if (rank[parentX] < rank[parentY]) {
                parent[parentX] = parentY
                rank[parentY]++
            } else {
                parent[parentX] = parentY
                rank[parentY]++
            }
            size--
        }
    }

    fun removeStones(stones: Array<IntArray>): Int {
        val set = DisjointSet(stones.size)
        val v = mutableMapOf<Int, Int>()
        val h = mutableMapOf<Int, Int>()

        stones.indices.forEach { i ->
            if (stones[i][0] in v) {
                set.union(i, v[stones[i][0]]!!)
            } else {
                v[stones[i][0]] = i
            }

            if (stones[i][1] in h) {
                set.union(i, h[stones[i][1]]!!)
            } else {
                h[stones[i][1]] = i
            }
        }
        return stones.size - set.size
    }
}

fun main() {
    with(Solution()) {
        println(
            removeStones(
                arrayOf(
                    intArrayOf(0, 0),
                    intArrayOf(0, 2),
                    intArrayOf(1, 1),
                    intArrayOf(2, 0),
                    intArrayOf(2, 2)
                )
            )
        )

        println(
            removeStones(
                arrayOf(
                    intArrayOf(0, 0),
                    intArrayOf(0, 1),
                    intArrayOf(1, 0),
                    intArrayOf(1, 2),
                    intArrayOf(2, 1),
                    intArrayOf(2, 2)
                )
            )
        )

        println(
            removeStones(
                arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(3, 1),
                    intArrayOf(4, 4),
                    intArrayOf(1, 1),
                    intArrayOf(0, 2),
                    intArrayOf(4, 0)
                )
            )
        )

        println(
            removeStones(
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 0),
                    intArrayOf(1, 1)
                )
            )
        )
    }
}