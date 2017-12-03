package aoc2017.day02

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val spreadsheet = Files
            .readAllLines(Paths.get("inputs/Day02.txt"))
            .map { it.split("\t").map { Integer.parseInt(it) } }

    val result = spreadsheet
            .map { getEvenlyDivisiblePair(it.sortedDescending()) }
            .sum()

    print(result)
}


fun getEvenlyDivisiblePair(numbers: List<Int>): Int {
    for ((index, number) in numbers.withIndex()) {
        for (divisorIndex in (numbers.size - 1) downTo (index + 1)) {
            if (number % numbers[divisorIndex] == 0) {
                return number / numbers[divisorIndex]
            }
        }
    }

    return 0
}