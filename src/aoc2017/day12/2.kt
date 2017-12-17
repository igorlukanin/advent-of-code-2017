package aoc2017.day12

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val pipes = Files
            .readAllLines(Paths.get("inputs/12.txt"))
            .map { it.split(" <-> ") }
            .associate { it[0].toInt() to it[1].split(", ").map { it.toInt() }.toIntArray() }
            .toMutableMap()

    val result = calculateGroupCount(pipes)

    println(result)
}