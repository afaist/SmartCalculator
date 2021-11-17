package calculator

class Queue<T> {
    private var items: MutableList<T> = mutableListOf()
    override fun toString() = items.toString()
    fun toList(): List<T>{
        return items
    }
    fun enqueue(element: T) {
        items.add(element)
    }

    fun enqueueAll(stack: Stack<T>) {
        while (!stack.isEmpty()) {
            items.add(stack.pop()!!)
        }
    }

}