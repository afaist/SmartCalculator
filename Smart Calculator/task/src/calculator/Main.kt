package calculator

/**
 * TODO
 *
 */
fun main() {
    val parser = Parsing
    val calculator = Calculator()
    while (true) {
        val input: String? = readLine()

        if (input != null && input.isNotEmpty()) {
            if (input.matches(Regex("^/\\w+"))) {
                when (input) {
                    "" -> continue
                    "/exit" -> break
                    "/help" -> {
                        println(PROGRAM_DESCRIPTION)
                        continue
                    }
                    else -> {
                        println("Unknown command")
                        continue
                    }
                }
            } else {
                try {
                    val tokens = parser.parseInput(input)
                    if (tokens != null) {
                        calculator.calculate(tokens)
                    }
                } catch (e: Exception) {
                    println(e.message)
                    continue
                }
            }
        }
    }
    println("Bye!")
}
