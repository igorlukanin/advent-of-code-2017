package aoc2017.day07

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val towers = Files
            .readAllLines(Paths.get("inputs/Day07.txt"))
            .map { it.toTower() }

    val result = findRoot(towers)

    println(result)
}

data class Tower(val name: String, val weight: Int, val towers: List<String> = listOf())

fun String.toTower(): Tower {
    if (this.contains("->")) {
        val (nameAndWeight, towersString) = this.split("->")
        val (name, weightString) = nameAndWeight.split(" ")

        val towers = towersString
                .split(",")
                .map { it.trim() }

        return Tower(name, weightString.trim('(', ')', ' ').toInt(), towers)
    }
    else {
        val (name, weightString) = this.split(" ")

        return Tower(name, weightString.trim('(', ')', ' ').toInt())
    }
}

fun findRoot(towers: List<Tower>): Tower {
    val nonLeafs = towers
            .filter { it.towers.isNotEmpty() }

    val root = nonLeafs
            .map { it.name }
            .minus(nonLeafs.flatMap { it.towers })
            .first()

    return towers.find { it.name == root }!!
}