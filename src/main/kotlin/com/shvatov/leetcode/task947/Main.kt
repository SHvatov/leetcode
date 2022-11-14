package com.shvatov.leetcode.task947

/**
 * Link to the task: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
class Solution {
    data class Node(val x: Int, val y: Int) {
        val nodes: MutableSet<Node> = mutableSetOf()
    }

    // 1 <= stones.length <= 1000
    // 0 <= xi, yi <= 10^4
    // No two stones are at the same coordinate point.
    fun removeStones(stones: Array<IntArray>): Int {
        val graph = buildGraph(stones)

        var max = Int.MIN_VALUE
        for (node in graph) {
            val path = findLongestPath(node, emptySet())
            if (path >= max) {
                max = path
            }
        }
        return max - 1
    }

    private fun buildGraph(stones: Array<IntArray>): Set<Node> {
        val nodes = stones.map { Node(it[0], it[1]) }.toSet()
        for (node in nodes) {
            for (other in nodes) {
                if (node == other) continue
                if (node.x == other.x || node.y == other.y) {
                    node.nodes.add(other)
                    other.nodes.add(node)
                }
            }
        }
        return nodes
    }

    private fun findLongestPath(node: Node, visited: Set<Node>): Int {
        if (node in visited) return 0
        return 1 + (node.nodes.map { findLongestPath(it, visited + node) }.max() ?: 0)
    }
}

fun main() {
    with(Solution()) {
//        removeStones(
//            arrayOf(
//                intArrayOf(0, 0),
//                intArrayOf(0, 2),
//                intArrayOf(1, 1),
//                intArrayOf(2, 0),
//                intArrayOf(2, 2)
//            )
//        )
//
//        removeStones(
//            arrayOf(
//                intArrayOf(0, 0),
//                intArrayOf(0, 1),
//                intArrayOf(1, 0),
//                intArrayOf(1, 2),
//                intArrayOf(2, 1),
//                intArrayOf(2, 2)
//            )
//        )

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
    }
}