package calculator

class Stack<T> {
    private val elements: MutableList<T> = mutableListOf()
    fun isEmpty() = elements.isEmpty()
    fun push(item: T) = elements.add(item)
    fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): Any? = elements.lastOrNull()
    override fun toString(): String {
        return elements.toString()
    }

    operator fun contains(t: T) = t in elements

}