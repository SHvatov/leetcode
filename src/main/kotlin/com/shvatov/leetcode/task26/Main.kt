package com.shvatov.leetcode.task26

/**
 * Link to task: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Runtime: 297 ms, faster than 86.39% of Kotlin online submissions for Remove Duplicates from Sorted Array.
 * Memory Usage: 39.8 MB, less than 86.77% of Kotlin online submissions for Remove Duplicates from Sorted Array.
 */
class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        var virtual = 0
        nums.indices.forEach { if (nums[virtual] != nums[it]) nums[++virtual] = nums[it] }
        return virtual + 1
    }
}

fun main() {
    with(Solution()) {
        println(removeDuplicates(intArrayOf(1, 1, 2)))
        println(removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)))
    }
}