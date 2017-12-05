package aoc2017.day05

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val offsets = Files
            .readAllLines(Paths.get("inputs/Day05.txt"))
            .map { Integer.parseInt(it) }
            .toMutableList()

    val result = countStepsWithStrangeJumps(offsets)

    print(result)
}

fun countStepsWithStrangeJumps(offsets: MutableList<Int>): Int {
    var i = 0
    var steps = 0

    while (i >= 0 && i < offsets.size) {
        val jumpOffset = offsets[i]
        offsets[i] += if (jumpOffset >= 3) -1 else 1
        i += jumpOffset
        steps += 1
    }

    return steps
}