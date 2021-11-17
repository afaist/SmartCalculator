fun removing(currentMap: MutableMap<Int, String>, value: String): MutableMap<Int, String> =
    currentMap
        .filter { it.value != value }
        .toMutableMap()
