package aoc2017.day17

fun main(args: Array<String>) {
    val result = applySteps(2017, 324).next

    println(result)

//    runTests()
}

data class CircularBuffer(val elements: IntArray, private var position: Int) {
    val next: Int
        get() = elements[(position + 1) % elements.size]
}

fun applySteps(count: Int, times: Int): CircularBuffer {
    val elements = IntArray(count + 1)
    var position = 0
    var size = 1

    for (i in 1..count) {
        val nextValue = elements[position] + 1
        position = (position + times) % size

        for (j in size downTo (position + 1)) {
            elements[j] = elements[j - 1]
        }

        elements[position + 1] = nextValue

        position++
        size++
    }

    return CircularBuffer(elements, position)
}

fun runTests() {
    testSpinlock()
}

fun testSpinlock() {
    val buffer = applySteps(2017, 3)

    if (buffer.next != 638) {
        println("Spinlock: ${buffer.next} != 638")
    }
}