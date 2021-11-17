package calculator

const val PROGRAM_DESCRIPTION = "The program is a calculator."
val listOperation = listOf('+', '-', '*', '/', '^')
val regArithmeticExpression = Regex("^[+\\-/(]*\\w+([ ]*[+\\-/*^]+[ /(]*\\w+[)]*)+")
val regInitVariable = Regex("(^[a-zA-Z]+[ ]*=[+\\- ]*\\d+)")
val regInitFromVariable = Regex("(^[a-zA-Z]+[ ]*=[ ]*[a-zA-Z]+)")
val regVariable = Regex("^[a-zA-Z]+")
val regNumber = "[+-]?\\d+".toRegex()
val regOperation = "[+\\-*/^]".toRegex()
val regPriorityThree = "\\^".toRegex()
val regPriorityTwo = "[*/]".toRegex()
val regPriorityTwoAndThree = "[\\^*/]".toRegex()
val regPriorityOne = "[+-]".toRegex()
