package calculator

class ExceptionInvalidExpression : Exception("Invalid expression")
class ExceptionInvalidAssignment: Exception("Invalid assignment")
class ExceptionUnknownVariable: Exception("Unknown variable")
class ExceptionInvalidIdentifier: Exception("Invalid identifier")