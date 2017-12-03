package aoc2017.day02

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val spreadsheet = Files
            .readAllLines(Paths.get("inputs/Day02.txt"))
            .map { it.split("\t").map { Integer.parseInt(it) } }

    val result = spreadsheet
            .map { (it.max() ?: 0) - (it.min() ?: 0) }
            .sum()

    print(result)
}