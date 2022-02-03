package com.github.hamthelegend.enchantmentorder

import com.github.hamthelegend.enchantmentorder.EnchantmentType.*
import com.github.hamthelegend.extensions.toTitleCase

data class Item(
    val type: ItemType,
    val enchantments: List<Enchantment> = listOf(),
    val anvilUseCount: Int = 0,
) {
    operator fun plus(other: Item) = Anvil.combine(this, other)
    override fun toString() = "${type.friendlyName}: $enchantments"
}

fun makeItemWithMaxEnchantments(type: ItemType, enchantmentTypes: List<EnchantmentType>, anvilUseCount: Int) =
    Item(type, enchantmentTypes.map { max(it) }, anvilUseCount)

enum class ItemType(val compatibleEnchantmentTypes: Set<EnchantmentType>) {
    ENCHANTED_BOOK(EnchantmentType.values().toSet()),
    HELMET(setOf(
        AQUA_AFFINITY,
        BLAST_PROTECTION,
        CURSE_OF_BINDING,
        FIRE_PROTECTION,
        MENDING,
        PROJECTILE_PROTECTION,
        PROTECTION,
        RESPIRATION,
        THORNS,
        UNBREAKING,
    )),
    CHESTPLATE(setOf(
        BLAST_PROTECTION,
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
        FIRE_PROTECTION,
        MENDING,
        PROJECTILE_PROTECTION,
        PROTECTION,
        THORNS,
        UNBREAKING,
    )),
    LEGGINGS(setOf(
        BLAST_PROTECTION,
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
        FIRE_PROTECTION,
        MENDING,
        PROJECTILE_PROTECTION,
        PROTECTION,
        THORNS,
        UNBREAKING,
    )),
    BOOTS(setOf(BLAST_PROTECTION,
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
        DEPTH_STRIDER,
        FEATHER_FALLING,
        FIRE_PROTECTION,
        FROST_WALKER,
        MENDING,
        PROJECTILE_PROTECTION,
        PROTECTION,
        SOUL_SPEED,
        THORNS,
        UNBREAKING
    )),
    ELYTRA(setOf(
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    )),
    HEAD(setOf(
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
    )),
    SWORD(setOf(
        BANE_OF_ARTHROPODS,
        CURSE_OF_VANISHING,
        FIRE_ASPECT,
        KNOCKBACK,
        LOOTING,
        MENDING,
        SHARPNESS,
        SMITE,
        SWEEPING_EDGE,
        UNBREAKING,
    )),
    AXE(setOf(
        BANE_OF_ARTHROPODS,
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SHARPNESS,
        SILK_TOUCH,
        SMITE,
        UNBREAKING,
    )),
    PICKAXE(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SILK_TOUCH,
        UNBREAKING,
    )),
    SHOVEL(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SILK_TOUCH,
        UNBREAKING,
    )),
    HOE(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SILK_TOUCH,
        UNBREAKING,
    )),
    BOW(setOf(
        CURSE_OF_VANISHING,
        FLAME,
        INFINITY,
        MENDING,
        POWER,
        PUNCH,
        UNBREAKING,
    )),
    FISHING_ROD(setOf(
        CURSE_OF_VANISHING,
        LUCK_OF_THE_SEA,
        LURE,
        MENDING,
        UNBREAKING,
    )),
    TRIDENT(setOf(
        CHANNELING,
        CURSE_OF_VANISHING,
        IMPALING,
        LOYALTY,
        MENDING,
        RIPTIDE,
        UNBREAKING,
    )),
    CROSSBOW(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        MULTISHOT,
        PIERCING,
        QUICK_CHARGE,
        UNBREAKING,
    )),
    SHEARS(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        MENDING,
        UNBREAKING
    )),
    FLINT_AND_STEEL(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    )),
    CARROT_ON_A_STICK(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    )),
    WARPED_FUNGUS_ON_A_STICK(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    )),
    ;

    val friendlyName: String
        get() = name.toTitleCase('_')
}