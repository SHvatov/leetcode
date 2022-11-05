package com.shvatov.leetcode.task433

/**
 * Link to task: https://leetcode.com/problems/minimum-genetic-mutation/
 * Runtime: 303 ms, faster than 25.00% of Kotlin online submissions for Minimum Genetic Mutation.
 * Memory Usage: 38.9 MB, less than 8.33% of Kotlin online submissions for Minimum Genetic Mutation.
 * ---
 * Runtime: 185 ms, faster than 91.67% of Kotlin online submissions for Minimum Genetic Mutation.
 * Memory Usage: 36.3 MB, less than 83.33% of Kotlin online submissions for Minimum Genetic Mutation.
 */
class Solution {
    private fun String.diff(other: String): Int {
        // at this point we know, that strings are of the same size
        if (this == other) return 0

        var diff = 0
        indices.forEach {
            if (this[it] != other[it]) diff++
        }
        return diff
    }

    fun minMutation(start: String, end: String, bank: Array<String>): Int {
        val allowedMutations = bank.toMutableSet()

        // mutation is impossible
        if (allowedMutations.isEmpty() || end !in allowedMutations) return -1

        return minMutation(start, end, allowedMutations)
    }

    private fun minMutation(start: String, end: String, allowedMutations: MutableSet<String>): Int {
        // mutation is not needed, we reached the end
        if (start == end) {
            return 0
        }

        // find available mutations
        val availableMutations = allowedMutations.filter { it.diff(start) == 1 }.toSet()

        // this is a dead end
        if (availableMutations.isEmpty()) return -1

        // in one mutation we can reach the end
        if (end in availableMutations) return 1

        // repeat the process for remaining mutation
        var minimumMutations = Int.MAX_VALUE
        for (mutation in availableMutations) {
            var temp = minMutation(mutation, end, (allowedMutations - availableMutations).toMutableSet())
            if (temp == -1) continue

            temp += 1
            if (temp < minimumMutations) {
                minimumMutations = temp
            }
        }

        return if (minimumMutations != Int.MAX_VALUE) minimumMutations else -1
    }
}

fun main() {
    with(Solution()) {
        println(minMutation("AACCGGTT", "AACCGGTA", arrayOf("AACCGGTA")))
        println(minMutation("AACCGGTT", "AAACGGTA", arrayOf("AACCGGTA", "AACCGCTA", "AAACGGTA")))
        println(minMutation("AAAAACCC", "AACCCCCC", arrayOf("AAAACCCC", "AAACCCCC", "AACCCCCC")))
        println(
            minMutation(
                "AAAACCCC",
                "CCCCCCCC",
                arrayOf(
                    "AAAACCCA", "AAACCCCA", "AACCCCCA",
                    "AACCCCCC", "ACCCCCCC", "CCCCCCCC",
                    "AAACCCCC", "AACCCCCC"
                )
            )
        )
    }
}