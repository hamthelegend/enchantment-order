package com.github.hamthelegend.enchantmentorder

import com.github.hamthelegend.extensions.permutations

// TODO: Try to make this calculate the best order without brute force
fun getBestOrder(target: Item, vararg enchantments: Enchantment): CombinationOrder {
    val items = mutableListOf(target,
        *(enchantments.map { enchantment -> Item(ItemType.ENCHANTED_BOOK, listOf(enchantment)) }.toTypedArray()))
    val allOrderPermutations = items.permutations()
    var bestCombinationOrder = listOf<Anvil.CombinationResult>()
    var bestCost = Int.MAX_VALUE
    for (orderPermutation in allOrderPermutations) {
        val combinationOrder = orderPermutation.combine()
        if (combinationOrder.totalCost < bestCost) {
            bestCombinationOrder = combinationOrder
            bestCost = combinationOrder.totalCost
        }
    }
    return bestCombinationOrder
}

fun List<Item>.combine(): CombinationOrder {
    val combinations = mutableListOf<Anvil.CombinationResult>()
    var currentItems = this
    while (currentItems.size > 1) {
        val nextItems = mutableListOf<Item>()
        for (index in currentItems.indices step 2) {
            if (index + 1 <= currentItems.lastIndex) {
                val target = currentItems[index]
                val sacrifice = currentItems[index + 1]
                val combinationResult = target + sacrifice
                combinations.add(combinationResult)
                nextItems.add(combinationResult.productItem)
            } else nextItems.add(currentItems[index])
        }
        currentItems = nextItems
    }
    return combinations
}