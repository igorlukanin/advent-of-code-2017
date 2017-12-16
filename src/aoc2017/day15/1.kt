package aoc2017.day15

fun main(args: Array<String>) {
    var a: Long = 634
    var b: Long = 301

    var count = 0

    for (i in 1..40000000) {
        a = generateA(a)
        b = generateB(b)

        count = update(count, a, b)
    }

    println(count)

//    runTests()
}

fun generate(previous: Long, factor: Int, divisor: Int) = (previous * factor) % divisor

fun generateA(previous: Long) = generate(previous, 16807, 2147483647)
fun generateB(previous: Long) = generate(previous, 48271, 2147483647)

fun Long.toLowerBits() = this.toString(2).takeLast(16)

fun update(previous: Int, a: Long, b: Long) =
        previous + if (a.toLowerBits() == b.toLowerBits()) 1 else 0

fun runTests() {
    testA()
    testJudge()
}

fun testA() {
    val actual = mutableListOf<Long>(65)
    actual.add(generateA(actual.last()))
    actual.add(generateA(actual.last()))
    actual.add(generateA(actual.last()))
    actual.add(generateA(actual.last()))
    actual.add(generateA(actual.last()))

    val expected = listOf(1092455, 1181022009, 245556042, 1744312007, 1352636452)

    if (actual.drop(1) == expected) {
        println("A: $actual != $expected")
    }
}

fun testJudge() {
    val pairs = mutableListOf<Pair<Long, Long>>(Pair(65, 8921))
    pairs.add(Pair(generateA(pairs.last().first), generateB(pairs.last().second)))
    pairs.add(Pair(generateA(pairs.last().first), generateB(pairs.last().second)))
    pairs.add(Pair(generateA(pairs.last().first), generateB(pairs.last().second)))
    pairs.add(Pair(generateA(pairs.last().first), generateB(pairs.last().second)))
    pairs.add(Pair(generateA(pairs.last().first), generateB(pairs.last().second)))

    var count = 0

    pairs.drop(1).forEach {
        count = update(count, it.first, it.second)
    }

    if (count != 1) {
        println("Judge: $count != 1")
    }
}