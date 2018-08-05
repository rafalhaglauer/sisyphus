package com.wardrobes.sisyphus.model.wardrobe

import kotlin.math.roundToInt


sealed class WardrobeFactory(val elementFactory: ElementFactory) {

    abstract fun createWardrobe(symbol: String, width: Float, height: Float, depth: Float, elementHeight: Float = 18F): WardrobeDetails
}

object HangingWardrobeFactory : WardrobeFactory(elementFactory = HangingWardrobeElementFactory) {

    override fun createWardrobe(symbol: String, width: Float, height: Float, depth: Float, elementHeight: Float): WardrobeDetails {
        val wardrobe = WardrobeDetails(symbol = symbol, type = Wardrobe.Type.HANGING, width = width, height = height, depth = depth)

        listOf(
                createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createBottomPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createLeftSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createRightSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight)
        ).forEach { wardrobe.addElement(it) }

        return wardrobe
    }

    private fun createTopPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float): Element = elementFactory.createTopPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float): Element = elementFactory.createBottomPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createLeftSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int): Element = elementFactory.createLeftSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createRightSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int): Element = elementFactory.createRightSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float): Element = elementFactory.createShelf(width = wardrobeDepth - 50, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight)

    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
}

object StandingWardrobeFactory : WardrobeFactory(elementFactory = StandingWardrobeElementFactory) {

    override fun createWardrobe(symbol: String, width: Float, height: Float, depth: Float, elementHeight: Float): WardrobeDetails {
        val wardrobe = WardrobeDetails(symbol = symbol, type = Wardrobe.Type.STANDING, width = width, height = height, depth = depth)

        listOf(
                createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createBottomPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createLeftSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createRightSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight)
        ).forEach { wardrobe.addElement(it) }

        return wardrobe
    }

    private fun createTopPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float): Element = elementFactory.createTopPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float): Element = elementFactory.createBottomPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createLeftSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int): Element = elementFactory.createLeftSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createRightSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int): Element = elementFactory.createRightSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float): Element = elementFactory.createShelf(width = wardrobeDepth - 50, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight)

    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
}

object UndefinedWardrobeFactory : WardrobeFactory(elementFactory = UnknownWardrobeElementFactory) {

    override fun createWardrobe(symbol: String, width: Float, height: Float, depth: Float, elementHeight: Float): WardrobeDetails = WardrobeDetails.unknown()

}