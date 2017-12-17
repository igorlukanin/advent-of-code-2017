package aoc2017.day11

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val moves = Files
            .readAllLines(Paths.get("inputs/11.txt"))
            .first()
            .split(",")

    val result = calculateDistance(applyMoves(moves))

    println(result)

//    runTests()
}

typealias XY = Pair<Int, Int>

fun calculateDistance(xy: XY): Int {
    val x = xy.first
    val y = xy.second
    val z = 0 - x - y

    return listOf(x, y, z).max()!!
}

// See http://keekerdc.com/2011/03/hexagon-grids-coordinate-systems-and-distance-calculations/
fun applyMoves(moves: List<String>, callback: (XY) -> Unit = {}): XY {
    var x = 0
    var y = 0

    moves.forEach {
        when (it) {
            "n"  -> { y += 1 }
            "ne" -> { x += 1 }
            "nw" -> { x -= 1; y += 1 }
            "s"  -> { y -= 1 }
            "sw" -> { x -= 1 }
            "se" -> { x += 1; y -= 1 }
            else -> throw IllegalArgumentException(it)
        }

        callback(Pair(x, y))
    }

    return Pair(x, y)
}

fun runTests() {
    val inputs = listOf(
            listOf("ne", "ne", "ne") to 3,
            listOf("ne", "ne", "sw", "sw") to 0,
            listOf("ne", "ne", "s", "s") to 2,
            listOf("se", "sw", "se", "sw", "sw") to 3
    )

    inputs.forEach {
        val distance = calculateDistance(applyMoves(it.first))

        if (distance != it.second) {
            println("For ${it.first} distance $distance != ${it.second}")
        }
    }

}