package com.github.hamthelegend.enchantmentorder

import com.github.hamthelegend.extensions.toRomanNumerals
import com.github.hamthelegend.extensions.toTitleCase

fun max(enchantmentType: EnchantmentType) = enchantmentType.toMaxEnchantment()

data class Enchantment(val type: EnchantmentType, val level: Int) {
    override fun toString() = "${type.friendlyName} ${level.toRomanNumerals()}"
}

enum class EnchantmentType(
    val maxLevel: Int,
    val nonBookItemMultiplier: Int,
    val enchantedBookMultiplier: Int
) {

    AQUA_AFFINITY(1, 4, 2),
    BANE_OF_ARTHROPODS(5, 2, 1),
    BLAST_PROTECTION(4, 4, 2),
    CHANNELING(1, 8, 4),
    CURSE_OF_BINDING(1, 8, 4),
    CURSE_OF_VANISHING(1, 8, 4),
    DEPTH_STRIDER(3, 4, 2),
    EFFICIENCY(5, 1, 1),
    FEATHER_FALLING(4, 2, 1),
    FIRE_ASPECT(2, 2, 2),
    FIRE_PROTECTION(4, 2, 1),
    FLAME(1, 4, 2),
    FORTUNE(3, 4, 2),
    FROST_WALKER(2, 4, 2),
    IMPALING(5, 4, 2),
    INFINITY(1, 8, 4),
    KNOCKBACK(2, 2, 1),
    LOOTING(3, 4, 2),
    LOYALTY(3, 1, 1),
    LUCK_OF_THE_SEA(3, 4, 2),
    LURE(3, 4, 2),
    MENDING(1, 4, 2),
    MULTISHOT(1, 4, 2),
    PIERCING(4, 1, 1),
    POWER(5, 1, 1),
    PROJECTILE_PROTECTION(4, 2, 1),
    PROTECTION(4, 1, 1),
    PUNCH(2, 4, 2),
    QUICK_CHARGE(3, 2, 1),
    RESPIRATION(3, 4, 2),
    RIPTIDE(3, 4, 2),
    SHARPNESS(5, 1, 1),
    SILK_TOUCH(1, 8, 4),
    SMITE(5, 2, 1),
    SOUL_SPEED(3, 8, 4),
    SWEEPING_EDGE(3, 4, 2),
    THORNS(3, 8, 4),
    UNBREAKING(3, 2, 1);

    val incompatibleEnchantmentTypes: List<EnchantmentType>
        get() = when (this) {
            AQUA_AFFINITY -> listOf()
            BANE_OF_ARTHROPODS -> listOf(SHARPNESS, SMITE)
            BLAST_PROTECTION -> listOf(FIRE_PROTECTION, PROJECTILE_PROTECTION, PROTECTION)
            CHANNELING -> listOf(RIPTIDE)
            CURSE_OF_BINDING -> listOf()
            CURSE_OF_VANISHING -> listOf()
            DEPTH_STRIDER -> listOf(FROST_WALKER)
            EFFICIENCY -> listOf()
            FEATHER_FALLING -> listOf()
            FIRE_ASPECT -> listOf()
            FIRE_PROTECTION -> listOf(BLAST_PROTECTION, PROJECTILE_PROTECTION, PROTECTION)
            FLAME -> listOf()
            FORTUNE -> listOf(SILK_TOUCH)
            FROST_WALKER -> listOf(DEPTH_STRIDER)
            IMPALING -> listOf()
            INFINITY -> listOf(MENDING)
            KNOCKBACK -> listOf()
            LOOTING -> listOf()
            LOYALTY -> listOf(RIPTIDE)
            LUCK_OF_THE_SEA -> listOf()
            LURE -> listOf()
            MENDING -> listOf(INFINITY)
            MULTISHOT -> listOf(PIERCING)
            PIERCING -> listOf(MULTISHOT)
            POWER -> listOf()
            PROJECTILE_PROTECTION -> listOf(BLAST_PROTECTION, FIRE_PROTECTION, PROTECTION)
            PROTECTION -> listOf(BLAST_PROTECTION, PROJECTILE_PROTECTION, PROTECTION)
            PUNCH -> listOf()
            QUICK_CHARGE -> listOf()
            RESPIRATION -> listOf()
            RIPTIDE -> listOf(CHANNELING, LOYALTY)
            SHARPNESS -> listOf(BANE_OF_ARTHROPODS, SMITE)
            SILK_TOUCH -> listOf(FORTUNE)
            SMITE -> listOf(BANE_OF_ARTHROPODS, SHARPNESS)
            SOUL_SPEED -> listOf()
            SWEEPING_EDGE -> listOf()
            THORNS -> listOf()
            UNBREAKING -> listOf()
        }

    val friendlyName: String
        get() = name.toTitleCase('_')

    fun toMaxEnchantment() = Enchantment(this, maxLevel)

    fun isCompatibleWith(enchantmentType: EnchantmentType) = enchantmentType !in incompatibleEnchantmentTypes
    fun isIncompatibleWith(enchantmentType: EnchantmentType) = !isCompatibleWith(enchantmentType)
}