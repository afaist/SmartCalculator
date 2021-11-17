// write your function here
fun isVowel(letter: Char) = letter.uppercaseChar() in arrayOf('A', 'E', 'I', 'O', 'U')
fun main() {
    val letter = readLine()!!.first()

    println(isVowel(letter))
}