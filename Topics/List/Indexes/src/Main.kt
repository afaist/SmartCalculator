fun solution(products: List<String>, product: String) =
    products.forEachIndexed { i, it -> if (product == it) print("$i ") }