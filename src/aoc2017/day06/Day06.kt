package aoc2017.day06

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val blocks = Files
            .readAllLines(Paths.get("inputs/Day06.txt"))[0]
            .split("\t")
            .map { Integer.parseInt(it) }

    val result = countCycles(blocks)

    print(result.size)
}

fun countCycles(blocks: List<Int>): MutableList<List<Int>> {
    val states = mutableListOf<List<Int>>()
    val block = blocks.toMutableList()

    while (!states.contains(block)) {
        states.add(block.toList())

        var (index, size) = block
                .withIndex()
                .maxWith(Comparator { a, b ->
                    if (a.value != b.value) (a.value - b.value) else (b.index - a.index)
                })!!

        block[index] = 0

        while (size > 0) {
            index = (index + 1) % block.size
            block[index] += 1
            size -= 1
        }
    }

    return states
}