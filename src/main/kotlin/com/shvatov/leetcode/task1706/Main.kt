package com.shvatov.leetcode.task1706

/**
 * Link to task: https://leetcode.com/problems/where-will-the-ball-fall/
 * Runtime: 241 ms, faster than 100.00% of Kotlin online submissions for Where Will the Ball Fall.
 * Memory Usage: 44.7 MB, less than 89.09% of Kotlin online submissions for Where Will the Ball Fall.
 */
class Solution {
    fun findBall(grid: Array<Array<Int>>): IntArray {
        // empty grid
        if (grid.isEmpty()) return IntArray(0)

        // grid dimensions
        val m = grid.size
        val n = grid[0].size

        // current positions of the balls
        val ballsPositions = IntArray(n) { it }

        // MxN grid
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (ballsPositions[j] == -1) {
                    continue
                }

                val ballPosition = ballsPositions[j]
                val nextBallPosition = ballPosition + grid[i][ballPosition]

                if (nextBallPosition < 0 || nextBallPosition > n) {
                    ballsPositions[j] = -1
                    continue
                }

                if (
                    (grid[i][ballPosition] == 1 && (ballPosition + 1 >= n || grid[i][ballPosition + 1] == -1))
                    || (grid[i][ballPosition] == -1 && (ballPosition - 1 < 0 || grid[i][ballPosition - 1] == 1))
                ) {
                    ballsPositions[j] = -1
                    continue
                }

                ballsPositions[j] = nextBallPosition
            }
        }

        return ballsPositions
    }
}

fun main() {
    with(Solution()) {
        println(
            findBall(
                arrayOf(
                    arrayOf(1, 1, 1, 1, 1, 1),
                    arrayOf(-1, -1, -1, -1, -1, -1),
                    arrayOf(1, 1, 1, 1, 1, 1),
                    arrayOf(-1, -1, -1, -1, -1, -1)
                )
            ).joinToString()
        )
        require(
            findBall(
                arrayOf(
                    arrayOf(1, -1, -1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, -1, -1, 1),
                    arrayOf(-1, 1, 1, 1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, 1, -1, 1, 1),
                    arrayOf(1, -1, -1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1, 1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1, -1)
                )
            ).joinToString(",") == "-1,-1,1,-1,-1,-1,-1,10,11,-1,-1,12,13,-1,-1,-1,-1,-1,17,-1,-1,20,-1,-1,-1,-1,-1,-1,-1,-1,27,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1"
        )
    }
}