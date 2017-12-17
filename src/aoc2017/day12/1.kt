package aoc2017.day12

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val pipes = Files
            .readAllLines(Paths.get("inputs/12.txt"))
            .map { it.split(" <-> ") }
            .associate { it[0].toInt() to it[1].split(", ").map { it.toInt() }.toIntArray() }

    val result = calculateGroupSize(pipes)

    println(result)

//    runTests()
}

fun addToGroup(group: MutableList<Int>, element: Int, pipes: Map<Int, IntArray>) {
    if (!group.contains(element)) {
        group.add(element)

        pipes[element]!!
                .filterNot { group.contains(it) }
                .forEach { addToGroup(group, it, pipes) }
    }
}

fun calculateGroupSize(pipes: Map<Int, IntArray>): Int {
    val group = mutableListOf<Int>()
    addToGroup(group, 0, pipes)
    return group.size
}

fun calculateGroupCount(pipes: MutableMap<Int, IntArray>): Int {
    var count = 0

    do {
        count++

        val group = mutableListOf<Int>()
        addToGroup(group, pipes.keys.first(), pipes)
        group.forEach { pipes.remove(it) }
    }
    while (pipes.isNotEmpty())

    return count
}

fun runTests() {
    runGroupSizeTest()
    runGroupCountTest()
}

fun runGroupSizeTest() {
    val pipes = mapOf(
            0 to intArrayOf(2),
            1 to intArrayOf(1),
            2 to intArrayOf(0, 3, 4),
            3 to intArrayOf(2, 4),
            4 to intArrayOf(2, 3, 6),
            5 to intArrayOf(6),
            6 to intArrayOf(4, 5)
    )

    val size = calculateGroupSize(pipes)

    if (size != 6) {
        println("Size: $size != 6")
    }
}

fun runGroupCountTest() {
    val pipes = mutableMapOf(
            0 to intArrayOf(2),
            1 to intArrayOf(1),
            2 to intArrayOf(0, 3, 4),
            3 to intArrayOf(2, 4),
            4 to intArrayOf(2, 3, 6),
            5 to intArrayOf(6),
            6 to intArrayOf(4, 5)
    )

    val count = calculateGroupCount(pipes)

    if (count != 2) {
        println("Count: $count != 2")
    }
}