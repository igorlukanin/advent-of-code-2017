package aoc2017.day08

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.locks.Condition

fun main(args: Array<String>) {
    val instructions = Files
            .readAllLines(Paths.get("inputs/Day08.txt"))
            .map { it.toInstruction() }

    var maxValue = 0

    fun updateMaxValue(registers: Map<String, Int>) {
        val maxRegisterValue = registers
                .maxBy { it.value }!!
                .value

        if (maxValue < maxRegisterValue) {
            maxValue = maxRegisterValue
        }
    }

    runInstructions(instructions, ::updateMaxValue)

    println(maxValue)
}