package aoc2017.day04

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val phrases = Files
            .readAllLines(Paths.get("inputs/Day04.txt"))

    val result = phrases
            .map { it.split(" ") }
            .filter { it.size == it.toSet().size }
            .count()

    print(result)
}