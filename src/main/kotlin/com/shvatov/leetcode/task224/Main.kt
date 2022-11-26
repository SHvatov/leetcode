@file:Suppress("RegExpRedundantEscape")

package com.shvatov.leetcode.task224

import java.util.*

/**
 * Link to the task: https://leetcode.com/problems/basic-calculator/
 */
class Solution {
    fun calculate(s: String): Int {

        fun handle(s: String): Int {
            if (s.all { it.isDigit() }
                || (s.startsWith('-') && s.substring(1).all { it.isDigit() }))
                return s.toInt()

            var cursor = 0
            var brackets = 0
            val operands = ArrayDeque<String>()
            val operations = ArrayDeque<String>()
            val token = StringBuilder()
            while (cursor < s.length) {
                when (s[cursor]) {
                    '(' -> {
                        if (brackets++ != 0) {
                            token.append("(")
                        }
                    }

                    ')' -> {
                        brackets--
                        if (brackets == 0) {
                            with(token) {
                                operands.add(toString())
                                clear()
                            }
                        } else {
                            token.append(")")
                        }
                    }

                    in '0'..'9' -> {
                        token.append(s[cursor])
                    }

                    '+', '-' -> {
                        if (brackets == 0) {
                            if (token.isNotBlank()) {
                                with(token) {
                                    operands.add(toString())
                                    clear()
                                }
                            }
                            operations.add(s[cursor].toString())
                        } else {
                            token.append(s[cursor])
                        }
                    }
                }
                cursor++
            }

            if (token.isNotBlank()) {
                operands.add(token.toString())
            }

            while (!operations.isEmpty()) {
                val op = operations.poll()
                if (op == "+") {
                    val lhs = handle(operands.poll())
                    val rhs = handle(operands.poll())
                    operands.add((rhs + lhs).toString())
                } else {
                    val lhs = handle(operands.poll())
                    val rhs = if (!operands.isEmpty()) {
                        handle(operands.poll())
                    } else null
                    operands.add((lhs - (rhs ?: 0)).toString())
                }
            }
            return handle(operands.pop())
        }

        return handle(s)
    }
}

fun main() {
    with(Solution()) {
        // test the solution
        println(calculate("(1+(4+5+2)  -33)+(6+8)"))
        println(calculate("(1+(4+5+2)  -3)+(6+8)"))
        println(calculate("1 + 1"))
        println(calculate("-(2 + 3)"))
        println(calculate("1 + (-1)"))
        println(calculate("( 3-1 + 2 )"))
        println(calculate(" 2-1 + 2 "))
    }
}