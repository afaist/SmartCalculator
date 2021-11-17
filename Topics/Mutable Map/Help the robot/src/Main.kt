fun helpingTheRobot(purchases: Map<String, Int>, addition: Map<String, Int>): MutableMap<String, Int> {
    val total = purchases.toMutableMap()
    for (it in addition) {
        total[it.key] = (total[it.key] ?: 0) + it.value
    }
    return total
}