package com.github.hamthelegend.enchantmentorder

import com.github.hamthelegend.extensions.pow
import com.github.hamthelegend.enchantmentorder.ItemType.*
import kotlin.math.max

typealias CombinationOrder = List<Anvil.CombinationResult>

val CombinationOrder.totalCost: Int
    get() = sumOf { combinationResult -> combinationResult.combiningCost }

fun CombinationOrder.println() {
    for (combinationResult in this) {
        println(combinationResult)
    }
    println("Final item: ${last().productItem}")
    println("Total cost: $totalCost")
    kotlin.io.println()
}

abstract class AnvilException(message: String) : IllegalArgumentException(message)

class ItemMismatchException(item1Type: ItemType, item2Type: ItemType) :
    AnvilException("You cannot combine a/an ${item1Type.friendlyName} with a/an ${item2Type.friendlyName}")

class IncompatibleItemEnchantmentException(itemType: ItemType, enchantmentType: EnchantmentType) :
    AnvilException("You cannot combine ${itemType.friendlyName} with ${enchantmentType.friendlyName}")

class IncompatibleEnchantmentsException(enchantmentType1: EnchantmentType, enchantmentType2: EnchantmentType) :
    AnvilException("You cannot combine ${enchantmentType1.friendlyName} with ${enchantmentType2.friendlyName}")

object Anvil {

    data class CombinationResult(val target: Item, val sacrifice: Item, val productItem: Item, val combiningCost: Int) {
        override fun toString() = "$target + $sacrifice; Cost = $combiningCost"
    }

    fun combine(target: Item, sacrifice: Item): CombinationResult {
        if ((target.type != ENCHANTED_BOOK && sacrifice.type == ENCHANTED_BOOK) || (target.type == sacrifice.type)) {
            var combiningCost = target.anvilUseCount.anvilUseCountToCost() + sacrifice.anvilUseCount.anvilUseCountToCost()
            val productEnchantments = target.enchantments.toMutableList()
            for (enchantment2 in sacrifice.enchantments) {
                var matchFound = false
                val multiplier = if (sacrifice.type == ENCHANTED_BOOK) {
                    enchantment2.type.enchantedBookMultiplier
                } else {
                    enchantment2.type.nonBookItemMultiplier
                }
                for ((index, enchantment1) in target.enchantments.withIndex()) {
                    if (enchantment2.type == enchantment1.type) {
                        if (enchantment2.level > enchantment1.level) {
                            productEnchantments.removeAt(index)
                            productEnchantments.add(index, enchantment2)
                            combiningCost += enchantment2.level * multiplier
                        } else if (enchantment2.level == enchantment1.level) {
                            productEnchantments.removeAt(index)
                            val upgradedEnchantment = Enchantment(enchantment1.type, enchantment1.level + 1)
                            productEnchantments.add(upgradedEnchantment)
                            combiningCost += upgradedEnchantment.level * multiplier
                        }
                        matchFound = true
                        break
                    }
                }
                if (!matchFound) {
                    productEnchantments.add(enchantment2)
                    combiningCost += enchantment2.level * multiplier
                }
            }
            productEnchantments.sortBy { it.type.friendlyName }
            val productAnvilUseCount = max(target.anvilUseCount, sacrifice.anvilUseCount) + 1
            val productItem = Item(target.type, productEnchantments, productAnvilUseCount)
            return CombinationResult(target, sacrifice, productItem, combiningCost)
        } else {
            throw ItemMismatchException(target.type, sacrifice.type)
        }
    }

    private fun Int.anvilUseCountToCost() = 2.pow(this) - 1
}