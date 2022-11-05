package com.shvatov.leetcode.task91

private val SYMBOL_TO_CODE = ('A'..'Z')
    .associateWith { (it.toInt() - 64).toString() }

private val SYMBOL_CODES = ('A'..'Z')
    .map { (it.toInt() - 64).toString() }
    .toSet()


private infix fun String.decodeSymbol(symbol: String): String? =
    if (startsWith(symbol)) {
        replaceFirst(symbol, "")
    } else null

private fun List<String>.consistOfEmptyStrings() =
    count { it.isEmpty() } == size

private class Leaf(val value: String, val leaves: List<Leaf>) {
    constructor(value: String) : this(value, generateLeaves(value))

    fun visitEachLeaf(visitor: Leaf.() -> Unit) {
        this.visitor()
        leaves.forEach { leaf ->
            leaf.visitEachLeaf(visitor)
        }
    }

    private companion object {
        fun generateLeaves(input: String) =
            SYMBOL_TO_CODE
                .map { (_, code) -> input decodeSymbol code }
                .filterNotNull()
                .map { subInput -> Leaf(subInput) }
    }
}

class Solution {
    fun numDecodings(input: String): Int {
        if (input.startsWith("0")) return 0

        if (input.length == 1) {
            return 1
        }

        if (input.length == 2) {
            return if (SYMBOL_CODES.contains(input)) {
                2
            } else {
                1
            }
        }

        if (input.length == 3) {
            if (input[1] == '0') {
                return 1
            }

            val startsWithCode = SYMBOL_CODES.contains(input.substring(0, 2))
            val endsWithCode = SYMBOL_CODES.contains(input.substring(1, 3))
            return if (startsWithCode && endsWithCode) {
                3
            } else if (startsWithCode || endsWithCode) {
                2
            } else {
                1
            }
        }

        return 0
    }
}

fun main() {
    with(Solution()) {
        require(numDecodings("12") == 2)
        require(numDecodings("226") == 3)
        require(numDecodings("06") == 0)
        require(numDecodings("106") == 1)
        require(numDecodings("116") == 3)
        require(numDecodings("110") == 2)
        require(numDecodings("111111111111111111111111111111111111111111111") == 0)
    }
}