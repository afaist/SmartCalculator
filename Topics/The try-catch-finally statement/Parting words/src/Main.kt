fun pepTalk(name: String): String {
    val array = name.split(" ").toTypedArray()
    val firstName = array[0]
    val secondName = array[1]
    return "Don't lose the towel, traveler $firstName $secondName!"
}
// do not change the function above        

fun main() {
    val name = readLine()!!
    println("Good luck!")
    val advice = try {
        pepTalk(name)
    } catch (e: Exception) {
        "Don't lose the towel, nameless one."
    }
    print(advice)
}
