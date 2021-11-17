package calculator

import java.math.BigInteger


/**
 * Calculation class
 *
 */
class Calculator {
    fun calculate(tokens: Array<String>) {
        postfixToAnswer(infixToPostfix(tokens))
    }

    /**
     * converting an infix record to a postfix record
     *
     * @param tokens
     * @return list of tokens in postfix record
     */
    private fun infixToPostfix(tokens: Array<String>): List<String> {
        val inputStack = Stack<String>()
        val inputQueue = Queue<String>()
        for (token in tokens) {
            when {
                token.matches(regNumber) -> {
                    inputQueue.enqueue(token)
                }
                token == "(" -> {
                    inputStack.push(token)
                }
                token == ")" -> {
                    while (inputStack.peek() != "(") {
                        inputQueue.enqueue(inputStack.pop()!!)
                    }
                    inputStack.pop()
                }
                token.matches(regPriorityThree) -> {
                    while (inputStack.peek().toString().matches(regPriorityThree)) {
                        inputQueue.enqueue(inputStack.pop()!!)
                    }
                    inputStack.push(token)
                }
                token.matches(regPriorityTwo) -> {
                    while (inputStack.peek().toString().matches(regPriorityTwoAndThree)) {
                        inputQueue.enqueue(inputStack.pop()!!)
                    }
                    inputStack.push(token)
                }

                token.matches(regPriorityOne) -> {
                    while (inputStack.peek().toString().matches(regOperation)) {
                        inputQueue.enqueue(inputStack.pop()!!)
                    }
                    inputStack.push(token)
                }
            }
        }
        inputQueue.enqueueAll(inputStack)
        return inputQueue.toList()
    }

    /**
     * performs calculations based on a list of numbers and operations
     *
     * @param list of numbers and operations
     */
    private fun postfixToAnswer(list: List<String>) {
        val outputStack = Stack<BigInteger>()
        for (item in list) {
            if (item.matches(regOperation)) {
                val a = outputStack.pop()
                val b = outputStack.pop()
                if (a != null && b != null)
                    outputStack.push(when (item) {
                        "+" -> b + a
                        "-" -> b - a
                        "*" -> b * a
                        "/" -> if (a == BigInteger.ZERO) throw ExceptionInvalidExpression() else b / a
                        "^" -> b.pow(a.toInt())
                        else -> throw ExceptionInvalidExpression()
                    })
            } else outputStack.push(item.toBigInteger())
        }
        outputStack.pop()?.let { println(it) }
    }
}