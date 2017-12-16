package aoc2017.day15

fun main(args: Array<String>) {
    var a: Long = 634
    var b: Long = 301

    var count = 0

    for (i in 1..5000000) {
        a = generateA2(a)
        b = generateB2(b)

        count = update(count, a, b)
    }

    println(count)

//    runTests2()
}

fun generateWithCondition(previous: Long, factor: Int, divisor: Int, condition: (Long) -> Boolean): Long {
    val next = generate(previous, factor, divisor)

    return if (condition(next)) next else generateWithCondition(next, factor, divisor, condition)
}

fun generateA2(previous: Long) = generateWithCondition(previous, 16807, 2147483647) { it % 4 == 0L }
fun generateB2(previous: Long) = generateWithCondition(previous, 48271, 2147483647) { it % 8 == 0L }

fun runTests2() {
    testA2()
    testB2()
    testJudge2()
}

fun testA2() {
    val actual = mutableListOf<Long>(65)
    actual.add(generateA2(actual.last()))
    actual.add(generateA2(actual.last()))
    actual.add(generateA2(actual.last()))
    actual.add(generateA2(actual.last()))
    actual.add(generateA2(actual.last()))

    val expected = listOf<Long>(1352636452, 1992081072, 530830436, 1980017072, 740335192)

    if (!actual.drop(1).containsAll(expected)) {
        println("A: ${actual.drop(1)} != $expected")
    }
}

fun testB2() {
    val actual = mutableListOf<Long>(8921)
    actual.add(generateB2(actual.last()))
    actual.add(generateB2(actual.last()))
    actual.add(generateB2(actual.last()))
    actual.add(generateB2(actual.last()))
    actual.add(generateB2(actual.last()))

    val expected = listOf<Long>(1233683848, 862516352, 1159784568, 1616057672, 412269392)

    if (!actual.drop(1).containsAll(expected)) {
        println("B: ${actual.drop(1)} != $expected")
    }
}

fun testJudge2() {
    var pair = Pair<Long, Long>(65, 8921)

    var i = 0

    do {
        i++
        pair = Pair(generateA2(pair.first), generateB2(pair.second))
        val count = update(0, pair.first, pair.second)
    } while (count == 0)

    if (i != 1056) {
        println("Judge: $i")
    }
}