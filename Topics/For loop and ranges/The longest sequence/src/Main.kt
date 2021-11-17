fun main() {

    val array = IntArray(readLine()!!.toInt()) { readLine()!!.toInt() }
    var lenMaxSequence = 1
    var lenCurrentSequence = 1
    for (i in 0 until array.lastIndex) {
        if (array[i] <= array[i + 1]) {
            lenCurrentSequence++
            if (lenCurrentSequence > lenMaxSequence) {
                lenMaxSequence = lenCurrentSequence
            }
        } else {
            lenCurrentSequence = 1
        }
    }
    println(lenMaxSequence)
}