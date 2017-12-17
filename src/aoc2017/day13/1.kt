package aoc2017.day13

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val scanners = Files
            .readAllLines(Paths.get("inputs/13.txt"))
            .map { it.split(": ") }
            .associate { it[0].toInt() to it[1].toInt() }
            .toArray()

    val result = calculateSeverity(scanners)

    println(result.severity)

//    runTests()
}

fun Map<Int, Int>.toArray(): IntArray {
    val array = IntArray(this.keys.max()!! + 1)
    this.forEach { array[it.key] = it.value }
    return array
}

fun getScannerPosition(ps: Int, depth: Int): Int {
    val length = depth * 2 - 2
    val value = ps % length
    return if (value < depth) value else depth - value + 1
}

data class Result(val severity: Int, val isCaught: Boolean)

fun calculateSeverity(scanners: IntArray, delay: Int = 0, breakIfCaught: Boolean = false): Result {
    var severity = 0
    var caught = false
    val time = delay + scanners.size - 1

    for (ps in delay..time) {
        val depth = scanners[ps - delay]

        if (depth != 0 && getScannerPosition(ps, depth) == 0) {
            severity += ps * depth
            caught = true

            if (breakIfCaught) {
                return Result(severity, caught)
            }
        }
    }

    return Result(severity, caught)
}

fun runTests() {
    runScannerPositionTest()
    runSeverityTest()
    runZeroSeverityTest()
}

fun runScannerPositionTest() {
    val depth = 3
    val positions = listOf(0, 1, 2, 1, 0, 1, 2, 1, 0)

    for (ps in 0..(positions.size - 1)) {
        val position = getScannerPosition(ps, depth)

        if (position != positions[ps]) {
            println("$ps ps: $position != ${positions[ps]}")
        }
    }
}

fun runSeverityTest() {
    val scanners = mapOf(
            0 to 3,
            1 to 2,
            4 to 4,
            6 to 4
    ).toArray()

    val result = calculateSeverity(scanners)

    if (result.severity != 24 || !result.isCaught) {
        println("Severity: ${result.severity} != 24")
    }
}

fun runZeroSeverityTest() {
    val scanners = mapOf(
            0 to 3,
            1 to 2,
            4 to 4,
            6 to 4
    ).toArray()

    val result = calculateSeverity(scanners, 10)

    if (result.isCaught) {
        println("Severity: caught!")
    }
}