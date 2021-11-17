fun main() = mutableMapOf<String, Int>().run {
    var name = readLine()!!
    while (name != "stop") {
        val value = readLine()!!.toInt()
        if (!this.containsKey(name)) {
            this[name] = value
        }
        name = readLine()!!
    }
    println(this)
}