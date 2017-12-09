package aoc2017.day09

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val characters = Files
            .readAllLines(Paths.get("inputs/Day09.txt"))
            .first()

    val stats = parseGroups(characters)

    println(stats.garbageSize)
}