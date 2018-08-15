//package com.wardrobes.sisyphus.model.wardrobe
//
//import kotlin.math.roundToInt
//
//
//sealed class WardrobeFactory {
//
//    abstract fun createElements(wardrobe: WardrobeDetails, elementHeight: Float = STANDARD_ELEMENT_HEIGHT): List<Element>
//}
//
//object HangingWardrobeFactory : WardrobeFactory() {
//
//    override fun createElements(wardrobe: WardrobeDetails, elementHeight: Float) = wardrobe.run {
//        listOf(createTopPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createBottomPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createLeftSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight),
//                createRightSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight),
//                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight)
//        )
//    }
//
//    private fun createTopPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Wieniec górny", width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)
//
//    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Wieniec dolny", width = wardrobeDepth - 20, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)
//
//    private fun createLeftSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Bok lewy", width = wardrobeDepth, length = wardrobeHeight, height = elementHeight)
//
//    private fun createRightSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Bok prawy", width = wardrobeDepth, length = wardrobeHeight, height = elementHeight)
//
//    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Półka", width = wardrobeDepth - 50, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight)
//
//    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
//}
//
//object StandingWardrobeFactory : WardrobeFactory() {
//
//    override fun createElements(wardrobe: WardrobeDetails, elementHeight: Float) = wardrobe.run {
//        listOf(
//                createTopPanel(wardrobeWidth = width, elementHeight = elementHeight),
//                createTopPanel(wardrobeWidth = width, elementHeight = elementHeight),
//                createBottomPanel(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createLeftSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight),
//                createRightSide(wardrobeHeight = height, wardrobeDepth = depth, elementHeight = elementHeight),
//                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight),
//                createShelf(wardrobeWidth = width, wardrobeDepth = depth, elementHeight = elementHeight)
//        )
//    }
//
//    private fun createTopPanel(wardrobeWidth: Float, elementHeight: Float) = Element(name = "Listwa wspierająca", width = 136F, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)
//
//    private fun createBottomPanel(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Wieniec dolny", width = wardrobeDepth - 55, length = wardrobeWidth - (2 * elementHeight) - 1, height = elementHeight)
//
//    private fun createLeftSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Bok lewy", width = wardrobeDepth, length = wardrobeHeight, height = elementHeight)
//
//    private fun createRightSide(wardrobeHeight: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Bok prawy", width = wardrobeDepth, length = wardrobeHeight, height = elementHeight)
//
//    private fun createShelf(wardrobeWidth: Float, wardrobeDepth: Float, elementHeight: Float) = Element(name = "Półka", width = wardrobeDepth - 85, length = wardrobeWidth - (2 * elementHeight) - 2, height = elementHeight)
//
//    private fun getNumberOfShelves(wardrobeHeight: Float): Int = (wardrobeHeight / 300).roundToInt()
//
//    // TODO Add shelves depend on wardrobe height!
//}