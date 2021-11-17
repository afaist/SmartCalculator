fun main() {
    val (a, b, n) = IntArray(3) { readLine()!!.toInt() }
    var nDiv = 0
    for (i in a..b) {
        if (i % n == 0) nDiv++
    }
    println(nDiv)
}