package aoc2017.day07

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val towers = Files
            .readAllLines(Paths.get("inputs/Day07.txt"))
            .map { it.toTower() }

    val root = findRoot(towers)

    val tower = buildFullTower(towers.associateBy { it.name }, root)
    val imbalancedTower = findImbalance(tower)!!

    val children = imbalancedTower.towers
            .map { Pair(it, it.totalWeight) }

    val max = children.map { it.second }.max()!!
    val min = children.map { it.second }.min()!!

    val wrongTower = children
            .groupBy { it.second }
            .map { Pair(it, it.value.size) }
            .sortedBy { it.second }
            .first()
            .first
            .value
            .first()
            .first

    println(wrongTower.weight - (max - min))
}

data class FullTower(val name: String, val weight: Int, val totalWeight: Int, val towers: List<FullTower> = listOf(), val isBalanced: Boolean)

fun buildFullTower(towers: Map<String, Tower>, root: Tower): FullTower {
    val subs = root.towers.map { buildFullTower(towers, towers[it]!!) }
    val subsWeights = subs.map { it.totalWeight }
    val isBalanced = subsWeights.toSet().size <= 1

    return FullTower(root.name, root.weight, root.weight + subsWeights.sum(), subs, isBalanced)
}

fun findImbalance(root: FullTower): FullTower? {
    val imbalanced = root.towers.find { !it.isBalanced }
    return if (imbalanced == null) root else findImbalance(imbalanced)
}