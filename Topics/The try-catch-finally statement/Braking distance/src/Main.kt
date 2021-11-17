fun calculateBrakingDistance(v1: String, a: String) = try {
    val v = v1.toInt()
    -v * v / 2 / a.toInt()
} catch (e: ArithmeticException) {
    println("The car does not slow down!")
    -1
} catch (e: NumberFormatException) {
    println(e.message)
    -1
}
