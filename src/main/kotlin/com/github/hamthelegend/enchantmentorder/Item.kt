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

enum class ItemCategory {
    ENCHANTED_BOOK, WEARABLE, TOOL;

    val friendlyName: String
        get() = name.toTitleCase('_')
}

enum class ItemType(
    val compatibleEnchantmentTypes: Set<EnchantmentType>,
    val itemCategory: ItemCategory,
) {
    ENCHANTED_BOOK(EnchantmentType.values().toSet(), ItemCategory.ENCHANTED_BOOK),
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
    ),
        ItemCategory.WEARABLE
    ),
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
    ),
        ItemCategory.WEARABLE
    ),
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
    ),
        ItemCategory.WEARABLE
    ),
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
    ),
        ItemCategory.WEARABLE
    ),
    ELYTRA(setOf(
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    ),
        ItemCategory.WEARABLE
    ),
    HEAD(setOf(
        CURSE_OF_BINDING,
        CURSE_OF_VANISHING,
    ),
        ItemCategory.WEARABLE
    ),
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
    ),
        ItemCategory.TOOL
    ),
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
    ),
        ItemCategory.TOOL
    ),
    PICKAXE(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SILK_TOUCH,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    SHOVEL(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SILK_TOUCH,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    HOE(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        FORTUNE,
        MENDING,
        SILK_TOUCH,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    BOW(setOf(
        CURSE_OF_VANISHING,
        FLAME,
        INFINITY,
        MENDING,
        POWER,
        PUNCH,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    FISHING_ROD(setOf(
        CURSE_OF_VANISHING,
        LUCK_OF_THE_SEA,
        LURE,
        MENDING,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    TRIDENT(setOf(
        CHANNELING,
        CURSE_OF_VANISHING,
        IMPALING,
        LOYALTY,
        MENDING,
        RIPTIDE,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    CROSSBOW(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        MULTISHOT,
        PIERCING,
        QUICK_CHARGE,
        UNBREAKING,
    ),
        ItemCategory.TOOL
    ),
    SHEARS(setOf(
        CURSE_OF_VANISHING,
        EFFICIENCY,
        MENDING,
        UNBREAKING
    ),
        ItemCategory.TOOL
    ),
    FLINT_AND_STEEL(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    ),
        ItemCategory.TOOL
    ),
    CARROT_ON_A_STICK(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    ),
        ItemCategory.TOOL
    ),
    WARPED_FUNGUS_ON_A_STICK(setOf(
        CURSE_OF_VANISHING,
        MENDING,
        UNBREAKING
    ),
        ItemCategory.TOOL
    ),
    ;

    val friendlyName: String
        get() = name.toTitleCase('_')
}