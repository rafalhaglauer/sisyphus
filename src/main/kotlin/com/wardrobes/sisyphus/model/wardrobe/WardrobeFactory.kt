package com.wardrobes.sisyphus.model.wardrobe

import kotlin.math.roundToInt


sealed class WardrobeFactory(protected val elementFactory: ElementFactory) {

    abstract fun createElements(wardrobe: WardrobeDetails, elementHeight: Float = STANDARD_ELEMENT_HEIGHT): List<Element>
}

object HangingWardrobeFactory : WardrobeFactory(elementFactory = HangingWardrobeElementFactory) {

    override fun createElements(wardrobe: WardrobeDetails, elementHeight: Float) = wardrobe.run {
        listOf(createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createBottomPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createLeftSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createRightSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight))
    }

    private fun createTopPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = elementFactory.createTopPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = elementFactory.createBottomPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createLeftSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int) = elementFactory.createLeftSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createRightSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int) = elementFactory.createRightSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = elementFactory.createShelf(width = wardrobeDepth - 50, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight)

    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
}

object StandingWardrobeFactory : WardrobeFactory(elementFactory = StandingWardrobeElementFactory) {

    override fun createElements(wardrobe: WardrobeDetails, elementHeight: Float) = wardrobe.run {
        listOf(
                createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createBottomPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
                createLeftSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createRightSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight, numberOfShelves = getNumberOfShelves(height)),
                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight)
        )
    }

    private fun createTopPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = elementFactory.createTopPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = elementFactory.createBottomPanel(width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)

    private fun createLeftSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int) = elementFactory.createLeftSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createRightSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float, numberOfShelves: Int) = elementFactory.createRightSide(width = wardrobeDepth, length = wardrobeHeight, height = elementHeight, numberOfShelves = numberOfShelves)

    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = elementFactory.createShelf(width = wardrobeDepth - 50, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight)

    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
}