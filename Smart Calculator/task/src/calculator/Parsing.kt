package calculator

object Parsing {
    private val variablesMap = mutableMapOf<String, String>()

    /**
     * parsing a mathematical expression
     *
     * @param text
     * @return
     */
    fun parseInput(text: String): Array<String>? {
        val input = text.replace("[ ]+".toRegex(), " ").trim()
        when {
            input.matches(regNumber) -> {
                // Output number
                println(input)
            }
            input.matches(regVariable) -> {
                // Output value for variable
                println(variablesMap[input] ?: "Unknown variable")
            }
            // If a variable is declared
            input.matches(regInitVariable) -> {
                // variable
                val (variable, value) = input.split(" *= *".toRegex())
                variablesMap[variable] = value
            }
            input.matches(regInitFromVariable) -> {
                val (variable, key) = input.split(" *= *".toRegex())
                if (variablesMap.containsKey(key)) {
                    variablesMap[variable] = variablesMap[key] ?: "0"
                } else {
                    throw ExceptionUnknownVariable()
                }
            }
            input.contains('=') -> {
                val tokens = input.split(" *= *".toRegex())
                if (tokens.size > 2) {
                    throw ExceptionInvalidAssignment()
                } else if (tokens.first().contains("\\d+".toRegex())) {
                    throw ExceptionInvalidIdentifier()
                } else {
                    throw ExceptionInvalidAssignment()
                }
            }
            input.matches(regArithmeticExpression) -> {
                return getTokens(normalize(input))
            }
            else -> {
                throw ExceptionInvalidExpression()
            }
        }
        return null
    }

    /**
     * additionally processes the parsed arithmetic expression
     *
     * @param namedParts
     * @return
     */
    private fun getTokens(namedParts: MutableList<Pair<String, Types>>): Array<String> {
        val tokens = mutableListOf<String>()
        for (pair in namedParts) {
            when (pair.second) {
                Types.NUMBER -> tokens.add(pair.first)
                Types.VARIABLE -> {
                    if (variablesMap.containsKey(pair.first)) {
                        tokens.add(variablesMap[pair.first].toString())
                    } else {
                        throw ExceptionUnknownVariable()
                    }
                }
                Types.OPERATION -> {
                    val operation = pair.first
                    if (operation.length == 1) {
                        tokens.add(operation)
                    } else if (operation.matches("[*/]+".toRegex())) {
                        throw IllegalStateException("Invalid expression")
                    } else if (operation.matches(Regex("\\++"))) {
                        tokens.add("+")
                    } else if (operation.matches(Regex("-+"))) {
                        tokens.add(with(operation) { if (count { it == '-' } and 1 == 0) "+" else "-" })
                    } else {
                        throw ExceptionInvalidExpression()
                    }
                }
                Types.BRACKET -> {
                    if (pair.first == "(") {
                        tokens.add(pair.first)
                    } else if (pair.first == ")") {
                        if (tokens.count { e -> e == "(" } - tokens.count { e -> e == ")" } >= 1) {
                            tokens.add(pair.first)
                        } else {
                            throw ExceptionInvalidExpression()
                        }
                    }
                }
                Types.NONE -> {
                    throw ExceptionInvalidExpression()
                }
            }
        }
        // Checking the pair of brackets
        if (tokens.count { e -> e == "(" } != tokens.count { e -> e == ")" }) {
            throw ExceptionInvalidExpression()
        }
        return tokens.toTypedArray()
    }

    /**
     * parses the string of an arithmetic expression into its component parts
     *
     * @param input
     * @return
     */
    private fun normalize(input: String): MutableList<Pair<String, Types>> {
        val preTokens = mutableListOf<Pair<String, Types>>()
        var currentType: Types = Types.NONE
        val currentString: StringBuilder = StringBuilder()
        for (symbol in input) {
            when (symbol) {
                in 'a'..'z', in 'A'..'Z' -> {
                    if (currentType == Types.NUMBER) {
                        throw ExceptionInvalidExpression()
                    }
                    if (currentType != Types.VARIABLE) {
                        if (currentString.isNotEmpty()) {
                            preTokens.add(Pair(currentString.toString(), currentType))
                            currentString.clear()
                        }
                        currentType = Types.VARIABLE
                    }
                    currentString.append(symbol)
                }
                in '0'..'9' -> {
                    if (currentType == Types.VARIABLE) {
                        throw ExceptionInvalidExpression()
                    }
                    if (currentType != Types.NUMBER) {
                        if (currentString.isNotEmpty()) {
                            preTokens.add(Pair(currentString.toString(), currentType))
                            currentString.clear()
                        }
                        currentType = Types.NUMBER
                    }
                    currentString.append(symbol)
                }
                in listOperation -> {
                    if (currentType == Types.NONE) {
                        if (symbol == '-' || symbol == '+') {
                            // Maybe a signed number
                            currentType = Types.NUMBER
                        }
                    } else if (currentType != Types.OPERATION) {
                        if (currentString.isNotEmpty()) {
                            preTokens.add(Pair(currentString.toString(), currentType))
                            currentString.clear()
                        }
                        currentType = Types.OPERATION
                    }
                    currentString.append(symbol)
                }
                '(', ')' -> {
                    if (currentString.isNotEmpty()) {
                        preTokens.add(Pair(currentString.toString(), currentType))
                        currentString.clear()
                    }
                    currentType = Types.BRACKET
                    currentString.append(symbol)
                }
            }
        }
        if (currentString.isNotEmpty()) {
            preTokens.add(Pair(currentString.toString(), currentType))
        }
        return preTokens
    }
}