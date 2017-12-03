package aoc2017.day03

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val input = Files
            .readAllLines(Paths.get("inputs/Day03.txt"))[0]

    val point = calculateCoordinates(Integer.parseInt(input))
    val result = Math.abs(point.x) + Math.abs(point.y)

    print(result)
}

data class Point(val number: Int, val x: Int, val y: Int) {
    fun move(deltaX: Int, deltaY: Int) = Point(number, x + deltaX, y + deltaY)
    fun setNumber(number: Int) = Point(number, x, y)
}

fun calculateCoordinates(number: Int): Point {
    var dim = Math.floor(Math.sqrt(number * 1.0)).toInt()

    if (dim % 2 == 0) {
        dim -= 1
    }

    val steps = Math.abs(dim * dim - number) - 1

    var point = Point(dim * dim, dim / 2, dim / 2)
    var completeSidesCount = 0

    for (step in 0..steps) {
        completeSidesCount = step / (dim + 1)

        val doStartNewSide = step % (dim + 1) == 0
        val delta = if (doStartNewSide) 1 else 0

        val newPosition = when (completeSidesCount % 4) {
            0 -> point.move(delta, -1 + delta) // go top
            1 -> point.move(-1, 0) // go left
            2 -> point.move(0, 1) // go bottom
            else -> point.move(1, 0) // go right
        }

        point = newPosition.setNumber(newPosition.number + 1)
    }

    return point
}