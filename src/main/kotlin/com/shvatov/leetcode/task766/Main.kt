package com.shvatov.leetcode.task766

/**
 * Link to task: https://leetcode.com/problems/toeplitz-matrix/
 * Runtime: 379 ms, faster than 60.71% of Kotlin online submissions for Toeplitz Matrix.
 * Memory Usage: 42 MB, less than 89.29% of Kotlin online submissions for Toeplitz Matrix.
 */
class Solution {
    fun isToeplitzMatrix(matrix: Array<Array<Int>>): Boolean {
        // empty matrix
        if (matrix.isEmpty()) return false

        // matrix dimensions
        val m = matrix.size
        val n = matrix[0].size

        // 1xN vector
        if (m == 1) return true

        // MxN matrix
        @Suppress("LocalVariableName")
        fun checkDiagonal(i: Int, j: Int): Boolean {
            var _i = i + 1
            var _j = j + 1
            while (_i < m && _j < n) {
                if (matrix[_i++][_j++] != matrix[i][j]) {
                    return false
                }
            }
            return true
        }

        // check middle diagonal
        if (!checkDiagonal(0, 0)) {
            return false
        }

        // check upper diagonals
        (1 until n - 1).forEach {
            if (!checkDiagonal(0, it)) {
                return false
            }
        }

        // check lower diagonals
        (1 until m - 1).forEach {
            if (!checkDiagonal(it, 0)) {
                return false
            }
        }

        return true
    }
}

fun main() {
    with(Solution()) {
        require(
            isToeplitzMatrix(
                arrayOf(
                    arrayOf(1, 2, 3, 4),
                    arrayOf(5, 1, 2, 3),
                    arrayOf(9, 5, 1, 2)
                )
            )
        )
        require(
            !isToeplitzMatrix(
                arrayOf(
                    arrayOf(1, 2),
                    arrayOf(2, 2)
                )
            )
        )
        require(
            isToeplitzMatrix(
                arrayOf(arrayOf(39, 24))
            )
        )
    }
}