package aoc2017.day06

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val blocks = Files
            .readAllLines(Paths.get("inputs/Day06.txt"))[0]
            .split("\t")
            .map { Integer.parseInt(it) }

    val firstResult = countCycles(blocks)
    val secondResult = countCycles(firstResult.last())

    println(secondResult.size)
}