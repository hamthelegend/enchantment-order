package com.github.hamthelegend.extensions

import java.util.*
import kotlin.math.pow

fun Int.toRomanNumerals(): String {

    if (this > 3_999) return toString()

    val string = toString()

    val romanNumeralEquivalence = listOf(
        1 to 'I',
        5 to 'V',
        10 to 'X',
        50 to 'L',
        100 to 'C',
        500 to 'D',
        1000 to 'M'
    )

    fun findEquivalent(coefficient: Int, place: Int) =
        romanNumeralEquivalence.filter { it.first == coefficient * 10.00.pow(place).toInt() }[0].second

    var romanNumerals = ""
    var place = string.length - 1
    for (digit in string) {
        val number = digit.toString().toInt()
        when {
            number <= 3 -> {
                for (j in 1..number) {
                    romanNumerals += findEquivalent(1, place)
                }
            }
            number == 4 -> {
                romanNumerals += findEquivalent(1, place)
                romanNumerals += findEquivalent(5, place)
            }
            number == 5 -> {
                romanNumerals += findEquivalent(5, place)
            }
            number <=8 -> {
                romanNumerals += findEquivalent(5, place)
                for (j in 6..number) {
                    romanNumerals += findEquivalent(1, place)
                }
            }
            number == 9 -> {
                romanNumerals += findEquivalent(1, place)
                romanNumerals += findEquivalent(10, place)
            }
        }
        place--
    }
    return romanNumerals

}

fun String.toTitleCase(wordSeparator: Char): String {
    val uncapitalizedWords = setOf("a", "an", "and", "the", "of", "on")
    val words = split(wordSeparator)
    val titleBuilder = StringBuilder()
    for ((index, word) in words.map { it.lowercase() }.withIndex()) {
        if (word in uncapitalizedWords) titleBuilder.append(word)
        else titleBuilder.append(word.replaceFirstChar { it.uppercase() })
        if (index != words.lastIndex) titleBuilder.append(' ')
    }
    return titleBuilder.toString()
}

fun Int.pow(n: Int) = toDouble().pow(n).toInt()

/**
 * I didn't write this
 */
fun <V> List<V>.permutations(): List<List<V>> {
    val retVal: MutableList<List<V>> = mutableListOf()

    fun generate(k: Int, list: List<V>) {
        // If only 1 element, just output the array
        if (k == 1) {
            retVal.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    Collections.swap(list, i, k - 1)
                } else {
                    Collections.swap(list, 0, k - 1)
                }
            }
        }
    }

    val listWithoutFirst = toMutableList().apply { removeAt(0) }.toList()
    generate(listWithoutFirst.count(), listWithoutFirst.toList())

    for (i in retVal.indices) {
        val tool = this[0]
        retVal[i] = retVal[i].toMutableList().apply{ add(0, tool) }.toList()
    }
    return retVal
}