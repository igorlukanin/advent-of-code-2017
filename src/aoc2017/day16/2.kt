package aoc2017.day16

import org.testng.annotations.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val moves = Files
            .readAllLines(Paths.get("inputs/16.txt"))
            .first()
            .split(",")
            .map { it.toMove() }

    val data = Data(CharRange('a', 'p').toList().toCharArray())
    val states = mutableMapOf<String, Int>()
    val cycle = 60

    for (i in 1..(1000000000 % cycle)) {
        moves.forEach { data.it() }

        val result = data.toString()

        if (states.containsKey(result)) {
            println(states[result])
            println(i)
        }
        else {
            states[result] = i
        }
    }

    val result = data.toString()

    println(result)
}