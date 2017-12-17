package aoc2017.day11

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val moves = Files
            .readAllLines(Paths.get("inputs/11.txt"))
            .first()
            .split(",")

    var maxDistance = 0

    applyMoves(moves) {
        val distance = calculateDistance(it)

        if (maxDistance == 0 || maxDistance < distance) {
            maxDistance = distance
        }
    }

    println(maxDistance)
}