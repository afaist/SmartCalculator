fun solution(numbers: List<Int>, number: Int) =
    numbers.toMutableList().apply { add(number) }