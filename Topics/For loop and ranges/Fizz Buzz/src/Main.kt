fun main() = (readLine()!!.toInt()..readLine()!!.toInt()).forEach {
    println(if (it % 15 == 0) "FizzBuzz" else if (it % 3 == 0) "Fizz" else if (it % 5 == 0) "Buzz" else it)
}