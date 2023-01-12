package com.shvatov.leetcode.task1519

class Solution {

    private data class Node(val id: Int, val label: Char) {
        val nodes = mutableListOf<Node>()
        var counter = 1
    }

    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        val nodeById = mutableMapOf<Int, Node>()

        for (edge in edges) {
            val (from, to) = edge
            val fromNode = nodeById.computeIfAbsent(from) {
                Node(from, labels[from])
            }
            val toNode = nodeById.computeIfAbsent(to) {
                Node(to, labels[to])
            }
            fromNode.nodes.add(toNode)
        }

        fun traverse(node: Node, visited: MutableSet<Node>) {
            visited.filter { it.label == node.label }
                .forEach { it.counter++ }

            visited.add(node)
            node.nodes.forEach {
                traverse(it, visited)
                visited.remove(it)
            }
            visited.remove(node)
        }

        val root = nodeById[0] ?: return intArrayOf()
        traverse(root, mutableSetOf())

        val result = IntArray(nodeById.size) { -1 }
        nodeById.forEach { (id, node) ->
            result[id] = node.counter
        }
        return result
    }
}

fun main() {
    with(Solution()) {
        println(
            countSubTrees(
                n = 7,
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                labels = "abaedcd"
            ).joinToString()
        )
        println(
            countSubTrees(
                n = 4,
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 3),
                    intArrayOf(1, 2)
                ),
                labels = "bbbb"
            ).joinToString()
        )
        println(
            countSubTrees(
                n = 4,
                edges = arrayOf(
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(1, 2)
                ),
                labels = "aeed"
            ).joinToString()
        )
    }
}