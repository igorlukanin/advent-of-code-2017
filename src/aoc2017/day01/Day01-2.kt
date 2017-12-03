package aoc2017.day01

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val numbers = Files
            .readAllLines(Paths.get("inputs/Day01.txt"))[0]
            .toCharArray()
            .map { Integer.parseInt(it.toString()) }

    val result = numbers
            .mapIndexed { index, number -> compareWithHalfwayAroundIndex(index, number, numbers) }
            .sum()

    print(result)
}

fun compareWithHalfwayAroundIndex(index: Int, number: Int, numbers: List<Int>): Int {
    return if (number == numbers[(index + numbers.size / 2) % numbers.size]) {
        number
    } else {
        0
    }
}