package com.shvatov.leetcode.other

class Main2 {

    fun call(string: String): Int {
        val temp = ArrayDeque<Char>()
        val symbols = mutableSetOf<Char>()
        var result = Int.MIN_VALUE
        for (ch in string) {
            if (!symbols.contains(ch)) {
                symbols.add(ch)
                temp.addLast(ch)
                if (temp.size >= result) {
                    result = temp.size
                }
            } else {
                while (temp.isNotEmpty() && temp.first() != ch) {
                    temp.removeFirst()
                }
            }
        }
        return result
    }

}

fun main() {
    with(Main2()) {
        println(call("bbbb"))
        println(call("abcabcbb"))
        println(call("abcade"))
    }
}