package aoc2017.day09

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val characters = Files
            .readAllLines(Paths.get("inputs/Day09.txt"))
            .first()

    val stats = parseGroups(characters)

    println(stats.score)

//    runCountTests()
//    runScoreTests()
//    runGarbageSizeTests()
}

data class GroupStats(val count: Int, val score: Int, var garbageSize: Int)

fun parseGroups(input: String): GroupStats {
    var count = 0
    var nestCount = 0
    var garbageSize = 0
    var score = 0

    var garbage = false
    var garbageStart = false
    var skip = false
    var skipStart = false

    input.forEach {
        if (skipStart) {
            skip = true
            skipStart = false
        }
        else if (skip) {
            skip = false
        }

        if (garbageStart) {
            garbage = true
            garbageStart = false
        }

        if (it == '{' && !garbage && !skip) {
            count++
            nestCount++
            score += nestCount
        }

        if (it == '}' && !garbage && !skip) {
            nestCount--
        }

        if (it == '!' && !skip) {
            skipStart = true
        }

        if (it == '<' && !skip) {
            garbageStart = true
        }

        if (it == '>' && !skip) {
            garbage = false
            garbageStart = false
        }

        if (garbage && !skipStart && !skip) {
            garbageSize += 1
        }
    }

    return GroupStats(count, score, garbageSize)
}

fun runCountTests() {
    mapOf(
            "{}" to 1,
            "{{{}}}" to 3,
            "{{},{}}" to 3,
            "{{{},{},{{}}}}" to 6,
            "{<{},{},{{}}>}" to 1, // which itself contains garbage)
            "{<a>,<a>,<a>,<a>}" to 1,
            "{{<a>},{<a>},{<a>},{<a>}}" to 5,
            "{{<!>},{<!>},{<!>},{<a>}}" to 2 // since all but the last > are canceled
    )
            .forEach {
                val stats = parseGroups(it.key)

                if (stats.count != it.value) {
                    println("${stats.count} groups counted in ${it.key} but ${it.value} expected")
                }
            }
}

fun runScoreTests() {
    mapOf(
            "{}" to 1,
            "{{{}}}" to 6,
            "{{},{}}" to 5,
            "{{{},{},{{}}}}" to 16,
            "{<a>,<a>,<a>,<a>}" to 1,
            "{{<ab>},{<ab>},{<ab>},{<ab>}}" to 9,
            "{{<!!>},{<!!>},{<!!>},{<!!>}}" to 9,
            "{{<a!>},{<a!>},{<a!>},{<ab>}}" to 3
    )
            .forEach {
                val stats = parseGroups(it.key)

                if (stats.score != it.value) {
                    println("${stats.score} score calculated in ${it.key} but ${it.value} expected")
                }
            }
}

fun runGarbageSizeTests() {
    mapOf(
            "<>" to 0,
            "<random characters>" to 17,
            "<<<<>" to 3,
            "<{!>}>" to 2,
            "<!!>" to 0,
            "<!!!>>" to 0,
            "<{o\"i!a,<{i<a>" to 10
    )
            .forEach {
                val stats = parseGroups(it.key)

                if (stats.garbageSize != it.value) {
                    println("${stats.garbageSize} garbage elements calculated in ${it.key} but ${it.value} expected")
                }
            }
}