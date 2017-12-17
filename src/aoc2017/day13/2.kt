package aoc2017.day13

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val scanners = Files
            .readAllLines(Paths.get("inputs/13.txt"))
            .map { it.split(": ") }
            .associate { it[0].toInt() to it[1].toInt() }
            .toArray()

    var delay = 0
    var minSeverity: Int? = null

    do {
        val result = calculateSeverity(scanners, delay++, true)
    }
    while (result.isCaught)

    println(delay - 1)
}