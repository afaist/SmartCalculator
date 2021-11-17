fun main() {
    val arr = IntArray(readLine()!!.toInt()) { readLine()!!.toInt() }
    var n = arr[0]
    (1..arr.lastIndex).forEach { i ->
        if (arr[i] < n) {
            println("NO")
            return
        } else {
            n = arr[i]
        }
    }
    println("YES")
}