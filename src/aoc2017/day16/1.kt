package aoc2017.day16

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val moves = Files
            .readAllLines(Paths.get("inputs/16.txt"))
            .first()
            .split(",")
            .map { it.toMove() }

    val input = CharRange('a', 'p').toList().toCharArray()
    val result = dance(input, moves)

    println(result)
}

typealias Move = Data.() -> Unit

data class Data(var programs: CharArray, var offset: Int = 0) {
    fun index(i: Int) = (offset + i) % programs.size

    override fun toString(): String {
        val buffer = StringBuffer(programs.size)

        for (i in 0 until programs.size) {
            buffer.append(programs[index(i)])
        }

        return buffer.toString()
    }
}

fun spin(count: Int): Move = {
    this.offset -= count
    if (this.offset < 0) this.offset += this.programs.size
}

fun exchange(positionA: Int, positionB: Int): Move = {
    val program = this.programs[this.index(positionA)]
    this.programs[this.index(positionA)] = this.programs[this.index(positionB)]
    this.programs[this.index(positionB)] = program
}

fun partner(programA: Char, programB: Char): Move = {
    val positionA = this.programs.indexOf(programA)
    val positionB = this.programs.indexOf(programB)
    this.programs[positionA] = programB
    this.programs[positionB] = programA
}

fun String.toMove(): Move {
    val args = this.substring(1).split("/")

    return when (this[0]) {
        's' -> spin(args[0].toInt())
        'x' -> exchange(args[0].toInt(), args[1].toInt())
        'p' -> partner(args[0].toCharArray().first(), args[1].toCharArray().first())
        else -> throw IllegalArgumentException(this)
    }
}

fun dance(input: CharArray, moves: List<Move>) = applyMoves(input, moves).toString()

fun applyMoves(input: CharArray, moves: List<Move>): Data {
    val data = Data(input)
    moves.forEach { data.it() }
    return data
}

fun runTests() {
    testSpin()
    testTwoCycles()
}

fun testSpin() {
    val actual = dance("abcde".toCharArray(), listOf("s3".toMove()))
    val expected = "cdeab"

    if (actual != expected) {
        println("Spin: $actual != $expected")
    }
}

fun testTwoCycles() {
    val cycle = listOf("s1", "x3/4", "pe/b").map { it.toMove() }
    val actual = dance("abcde".toCharArray(), cycle + cycle)
    val expected = "ceadb"

    if (actual != expected) {
        println("Cycles: $actual != $expected")
    }
}