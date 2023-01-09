package com.shvatov.leetcode.task149

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Solution {
    fun maxPoints(points: Array<IntArray>): Int {
        fun dist(x1: Int, y1: Int, x2: Int, y2: Int): Double =
            sqrt(((x1 - x2).toDouble()).pow(2.0) + ((y1 - y2).toDouble()).pow(2.0))

        var maxPoints = Int.MIN_VALUE
        for (start in points) {
            for (end in points) {
                val dist = dist(start[0], start[1], end[0], end[1])
                var currentPoints = 0
                for (point in points) {
                    if (point == start || point == end) {
                        currentPoints++
                        println(
                            "Start or end point encountered ${point.joinToString()}, " +
                                    "start: ${start.joinToString()}, " +
                                    "end: ${end.joinToString()}, " +
                                    "cur points: $currentPoints"
                        )
                        continue
                    }

                    val distA = dist(start[0], start[1], point[0], point[1])
                    val distB = dist(point[0], point[1], end[0], end[1])

                    println(
                        "Dist between start = ${start.joinToString()}, " +
                                "end = ${end.joinToString()}, " +
                                "point = ${point.joinToString()}, " +
                                "=> ${distA + distB - dist}"
                    )
                    if (abs(distA + distB - dist) < 10e-10) {
                        println(
                            "Start or end point encountered (via dist) ${point.joinToString()}, " +
                                    "start: ${start.joinToString()}, " +
                                    "end: ${end.joinToString()},  " +
                                    "cur points: $currentPoints"
                        )
                        currentPoints++
                        continue
                    }
                }
                if (currentPoints > maxPoints) {
                    maxPoints = currentPoints
                }
            }
        }
        return maxPoints
    }
}

fun main() {
    with(Solution()) {
//        println("1")
//        println(maxPoints(arrayOf(intArrayOf(0, 0))))
//        println()
//        println("2")
//        println(maxPoints(arrayOf(intArrayOf(0, 0), intArrayOf(0, 1))))
//        println()
//        println("3")
//        println(maxPoints(arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3))))
//        println()
//        println("3")
//        println(maxPoints(arrayOf(intArrayOf(4, 5), intArrayOf(4, -1), intArrayOf(4, 0))))
        println("2")
        println(maxPoints(arrayOf(intArrayOf(5151, 5150), intArrayOf(0, 0), intArrayOf(5152, 5151))))
//        println()
//        println("2")
//        println(maxPoints(arrayOf(intArrayOf(0, 0), intArrayOf(1, -1), intArrayOf(1, 1))))
//        println("4")
//        println(
//            maxPoints(
//                arrayOf(
//                    intArrayOf(1, 1),
//                    intArrayOf(3, 2),
//                    intArrayOf(5, 3),
//                    intArrayOf(4, 1),
//                    intArrayOf(2, 3),
//                    intArrayOf(1, 4),
//                )
//            )
//        )
    }
}