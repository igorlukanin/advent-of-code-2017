package aoc2017.day03

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val inputString = Files
            .readAllLines(Paths.get("inputs/Day03.txt"))[0]

    val input = Integer.parseInt(inputString)

    var lastPoint = SumPoint(1, 1, Pair(0, 0))

    val points = mutableMapOf<Pair, SumPoint>()
    points[lastPoint.pair] = lastPoint

    while (lastPoint.sum < input) {
        val pair = getSpiralPoint(lastPoint.number + 1)
        val sum = calculateAdjacentSum(points, pair)
        val point = SumPoint(lastPoint.number + 1, sum, pair)
        points[pair] = point
        lastPoint = point
    }

    println(lastPoint.sum)
}

data class Pair(val x: Int, val y: Int) {
    fun move(deltaX: Int, deltaY: Int) = Pair(x + deltaX, y + deltaY)}

data class SumPoint(val number: Int, val sum: Int, val pair: Pair)

/**
 * Adapted from https://math.stackexchange.com/a/163101
 */
fun getSpiralPoint(n: Int): Pair {
    val k = Math.ceil((Math.sqrt(n * 1.0) - 1) / 2).toInt()
    var t = 2 * k + 1
    var m = t * t
    t -= 1

    if (n >= m - t) {
        return Pair(k - (m - n), -k)
    } else {
        m -= t
    }

    if (n >= m - t) {
        return Pair(-k, -k + (m - n))
    } else {
        m -= t
    }

    if (n >= m - t) {
        return Pair(-k + (m - n), k)
    } else {
        return Pair(k, k - (m - n - t))
    }
}

fun calculateAdjacentSum(points: Map<Pair, SumPoint>, pair: Pair) =
        (points[pair.move(-1, -1)]?.sum ?: 0) +
        (points[pair.move(+0, -1)]?.sum ?: 0) +
        (points[pair.move(+1, -1)]?.sum ?: 0) +
        (points[pair.move(-1, +0)]?.sum ?: 0) +
        (points[pair.move(+1, +0)]?.sum ?: 0) +
        (points[pair.move(-1, +1)]?.sum ?: 0) +
        (points[pair.move(+0, +1)]?.sum ?: 0) +
        (points[pair.move(+1, +1)]?.sum ?: 0)