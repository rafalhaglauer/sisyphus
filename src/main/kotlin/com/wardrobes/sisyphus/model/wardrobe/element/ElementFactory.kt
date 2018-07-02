package com.wardrobes.sisyphus.model.wardrobe.element

import com.wardrobes.sisyphus.model.wardrobe.Element
import com.wardrobes.sisyphus.model.wardrobe.STANDARD_ELEMENT_HEIGHT

sealed class ElementFactory(val wardrobeWidth: Float, val wardrobeHeight: Float, val wardrobeDepth: Float, val elementHeight: Float = STANDARD_ELEMENT_HEIGHT) {

    abstract fun createElements(): List<Element>
}

class HangingWardrobeElementFactory(wardrobeWidth: Float, wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float = STANDARD_ELEMENT_HEIGHT): ElementFactory(wardrobeWidth, wardrobeHeight, wardrobeDepth, elementHeight) {

    override fun createElements(): List<Element> = listOf(
            createPanel("Wieniec górny"),
            createPanel("Wieniec dolny"),
            createSide("Bok lewy"),
            createSide("Bok prawy"),
            createShelf()
    )

    private fun createPanel(name: String) = Element(name = name, width = wardrobeDepth - 30, length = wardrobeWidth - (2 * elementHeight + 1), height = elementHeight)

    private fun createSide(name: String) = Element(name = name, width = wardrobeDepth, length = wardrobeHeight, height = elementHeight)

    private fun createShelf() = Element(name = "Półka", width = wardrobeDepth, length = wardrobeWidth, height = elementHeight)
}

// TODO Implement StandingWardrobeElementFactory methods!
class StandingWardrobeElementFactory(wardrobeWidth: Float, wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float = STANDARD_ELEMENT_HEIGHT): ElementFactory(wardrobeWidth, wardrobeHeight, wardrobeDepth, elementHeight) {

    override fun createElements(): List<Element> = listOf(
            createPanel("Wieniec górny"),
            createPanel("Wieniec dolny"),
            createSide("Bok lewy"),
            createSide("Bok prawy"),
            createShelf()
    )

    private fun createPanel(name: String) = Element(name = name, width = wardrobeDepth - 30, length = wardrobeWidth - (2 * elementHeight + 1), height = elementHeight)

    private fun createSide(name: String) = Element(name = name, width = wardrobeDepth, length = wardrobeHeight, height = elementHeight)

    private fun createShelf(): Element = Element(name = "Półka", width = wardrobeDepth, length = wardrobeWidth, height = elementHeight)
}

object UndefinedElementFactory: ElementFactory(0F, 0F, 0F, 0F) {

    override fun createElements(): List<Element> = emptyList()
}
