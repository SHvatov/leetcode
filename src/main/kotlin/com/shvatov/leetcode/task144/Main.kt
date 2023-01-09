package com.shvatov.leetcode.task144

class Solution {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * Example:
     * var ti = TreeNode(5)
     * var v = ti.`val`
     * Definition for a binary tree node.
     * class TreeNode(var `val`: Int) {
     *     var left: TreeNode? = null
     *     var right: TreeNode? = null
     * }
     */
    class Solution {
        fun preorderTraversal(root: TreeNode?): List<Int> {
            root ?: return emptyList()
            val queue = ArrayDeque<TreeNode>().apply {
                addFirst(root)
            }
            val values = mutableListOf<Int>()
            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                node ?: return values

                values.add(node.`val`)
                node.right?.also { queue.addFirst(it) }
                node.left?.also { queue.addFirst(it) }
            }
            return values
        }
    }
}

fun main() {
    with(Solution()) {

    }
}