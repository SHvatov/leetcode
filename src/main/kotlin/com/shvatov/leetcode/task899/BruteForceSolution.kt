package com.shvatov.leetcode.task899

private fun CharArray.concatToString() = joinToString("")
private fun List<Char>.concatToString() = joinToString("")

/**
 * Link to the task: https://leetcode.com/problems/orderly-queue/
 */
class BruteForceSolution {

    private fun String.pop(index: Int): String {
        val charArray = toCharArray()
        val ch = charArray[index]
        for (i in (index until charArray.size - 1)) {
            charArray[i] = charArray[i + 1]
        }
        charArray[charArray.size - 1] = ch
        return charArray.concatToString()
    }

    private fun bruteForcePossibleModifications(v: MutableSet<String>, s: String, k: Int) {
        repeat(k) {
            val temp = s.pop(it)
            if (temp in v) {
                return@repeat
            }
            v.add(temp)
            bruteForcePossibleModifications(v, temp, k)
        }
    }

    fun orderlyQueue(s: String, k: Int): String {
        if (k >= 10) return s.toCharArray().sorted().concatToString()

        val temp = mutableSetOf<String>()
        bruteForcePossibleModifications(temp, s, k)
        println("Count = ${temp.size}, options = ${temp.joinToString()}")
        return temp.min()!!
    }
}

fun main() {
    with(BruteForceSolution()) {
        println(orderlyQueue("baaca", 3))
        println(orderlyQueue("cba", 1))
        println(orderlyQueue("v", 1))
        println(orderlyQueue("nhtq", 1))
        println(orderlyQueue("xmvzi", 2))
        println(
            orderlyQueue(
                "xitavoyjqiupzadbdyymyvuteolyeerecnuptghl" + "zsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq",
                1
            )
        )
    }
}