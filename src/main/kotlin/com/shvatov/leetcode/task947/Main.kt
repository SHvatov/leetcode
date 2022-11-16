package com.shvatov.leetcode.task947

/**
 * Link to the task: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
class OldSolution {
    data class Node(val x: Int, val y: Int)

    // 1 <= stones.length <= 1000
    // 0 <= xi, yi <= 10^4
    // No two stones are at the same coordinate point.
    fun removeStones(stones: Array<IntArray>): Int {
        val nodes = stones.map { Node(it[0], it[1]) }.toSet()
        val graphs = mutableMapOf<Set<Node>, Int>()

        for (node in nodes) {
            val graph = mutableSetOf<Node>()
            val longestPath = findLongestPath(node, nodes - node, emptySet(), graph) - 1

            val prevLongestPath = graphs.filterKeys { it.contains(node) }
                .entries
                .firstOrNull()

            if (prevLongestPath != null) {
                val (prevGraph, prevPathLen) = prevLongestPath
                if (prevPathLen < longestPath) {
                    graphs[graph] = longestPath
                    graphs.remove(prevGraph)
                }
            } else {
                graphs[graph] = longestPath
            }
        }

        return graphs.map { (k, v) -> v }.sum()
    }

    private fun findLongestPath(
        node: Node,
        otherNodes: Set<Node>,
        visitedNodes: Set<Node>,
        graph: MutableSet<Node>
    ): Int {
        if (node in visitedNodes) return 0

        graph.add(node)

        val linkedNodes = otherNodes.filter { it.x == node.x || it.y == node.y }
        val maxSubPath = linkedNodes.map {
            findLongestPath(
                it, otherNodes, visitedNodes + node, graph
            )
        }.max() ?: 0
        return 1 + maxSubPath
    }
}

fun main() {
    with(OldSolution()) {
//        println(
//            removeStones(
//                arrayOf(
//                    intArrayOf(0, 0),
//                    intArrayOf(0, 2),
//                    intArrayOf(1, 1),
//                    intArrayOf(2, 0),
//                    intArrayOf(2, 2)
//                )
//            )
//        )
//
//        println(
//            removeStones(
//                arrayOf(
//                    intArrayOf(0, 0),
//                    intArrayOf(0, 1),
//                    intArrayOf(1, 0),
//                    intArrayOf(1, 2),
//                    intArrayOf(2, 1),
//                    intArrayOf(2, 2)
//                )
//            )
//        )

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