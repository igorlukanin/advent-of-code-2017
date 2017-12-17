package aoc2017.day17

fun main(args: Array<String>) {
    val result = applyStepsAndFindElementAfterZero(50000000, 324)

    println(result)

//    runTests2()
}

fun applyStepsAndFindElementAfterZero(count: Int, times: Int): Int {
    var position = 0
    var size = 1
    var value = 0

    for (i in 1..count) {
        position = (position + times) % size

        position++
        size++

        if (position == 1) {
            value = i
        }
    }

    return value
}

fun runTests2() {
    testSpinlock2()
}

fun testSpinlock2() {
    val result = applyStepsAndFindElementAfterZero(10, 3)

    if (result != 9) {
        println("Spinlock: $result != 9")
    }
}