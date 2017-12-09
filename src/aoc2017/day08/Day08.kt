package aoc2017.day08

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.locks.Condition

fun main(args: Array<String>) {
    val instructions = Files
            .readAllLines(Paths.get("inputs/Day08.txt"))
            .map { it.toInstruction() }

    val result = runInstructions(instructions)
            .maxBy { it.value }!!
            .value

    println(result)
}

data class Instruction(val register: String,
                       val operation: String,
                       val value: Int,
                       val conditionRegister: String,
                       val conditionOperation: String,
                       val conditionValue: Int)

fun String.toInstruction(): Instruction {
    val parts = this.split(" ")

    return Instruction(parts[0], parts[1], parts[2].toInt(), parts[4], parts[5], parts[6].toInt())
}

fun runInstructions(instructions: List<Instruction>, callback: (Map<String, Int>) -> Unit = {}): Map<String, Int> {
    val registers = mutableMapOf<String, Int>()

    instructions.forEach {
        val conditionRegister = registers[it.conditionRegister] ?: 0

        val conditionResult = when (it.conditionOperation) {
            ">"  -> conditionRegister >  it.conditionValue
            ">=" -> conditionRegister >= it.conditionValue
            "<"  -> conditionRegister <  it.conditionValue
            "<=" -> conditionRegister <= it.conditionValue
            "==" -> conditionRegister == it.conditionValue
            "!=" -> conditionRegister != it.conditionValue
            else -> throw IllegalArgumentException("Unsupported condition operation: ${it.conditionOperation}")
        }

        if (conditionResult) {
            val register = registers[it.register] ?: 0

            when (it.operation) {
                "inc" -> registers[it.register] = register + it.value
                "dec" -> registers[it.register] = register - it.value
                else -> throw IllegalArgumentException("Unsupported operation: ${it.operation}")
            }
        }

        callback(registers)
    }

    return registers
}