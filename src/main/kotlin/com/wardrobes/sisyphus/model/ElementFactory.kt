package com.wardrobes.sisyphus.model

import kotlin.math.roundToInt

sealed class ElementFactory {

    abstract fun createElements(wardrobe: Wardrobe, elementHeight: Float): List<Element>
}

object HangingWardrobeElementFactory : ElementFactory() {

    override fun createElements(wardrobe: Wardrobe, elementHeight: Float): List<Element> =
            mutableListOf<Element>().apply {
                (1..2).forEach { add(createPanel(wardrobeWidth = wardrobe.width, wardrobeDepth = wardrobe.depth, elementHeight = elementHeight, wardrobe = wardrobe)) }
                (1..2).forEach { add(createSide(wardrobeHeight = wardrobe.height, wardrobeDepth = wardrobe.depth, elementHeight = elementHeight, wardrobe = wardrobe)) }
                (1..getNumberOfShelves(wardrobeHeight = wardrobe.height)).forEach { add(createShelf(wardrobeWidth = wardrobe.width, wardrobeDepth = wardrobe.depth, elementHeight = elementHeight, wardrobe = wardrobe)) }
            }

    private fun createPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Wieniec", width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight, wardrobe = wardrobe)

    private fun createSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Bok", width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, wardrobe = wardrobe)

    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Półka", width = wardrobeDepth - 50, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight, wardrobe = wardrobe)

    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
}

object StandingWardrobeElementFactory : ElementFactory() {

    override fun createElements(wardrobe: Wardrobe, elementHeight: Float): List<Element> =
            mutableListOf<Element>().apply {
                (1..2).forEach { add(createSupportingBar(wardrobeWidth = wardrobe.width, elementHeight = elementHeight, wardrobe = wardrobe)) }
                add(createBottomPanel(wardrobeWidth = wardrobe.width, wardrobeDepth = wardrobe.depth, elementHeight = elementHeight, wardrobe = wardrobe))
                (1..2).forEach { add(createSide(wardrobeHeight = wardrobe.height, wardrobeDepth = wardrobe.depth, elementHeight = elementHeight, wardrobe = wardrobe)) }
                (1..getNumberOfShelves(wardrobeHeight = wardrobe.height)).forEach { add(createShelf(wardrobeWidth = wardrobe.width, wardrobeDepth = wardrobe.depth, elementHeight = elementHeight, wardrobe = wardrobe)) }
            }

    private fun createSupportingBar(wardrobeWidth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Listwa wspierająca", width = 136F, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight, wardrobe = wardrobe)

    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Wieniec dolny", width = wardrobeDepth - 55, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight, wardrobe = wardrobe)

    private fun createSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Bok", width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, wardrobe = wardrobe)

    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float, wardrobe: Wardrobe): Element = Element(name = "Półka", width = wardrobeDepth - 85, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight, wardrobe = wardrobe)

    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
}