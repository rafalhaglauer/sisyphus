package com.wardrobes.sisyphus

import com.wardrobes.sisyphus.model.BottomWardrobeElementFactory
import com.wardrobes.sisyphus.model.Element
import com.wardrobes.sisyphus.model.TopWardrobeElementFactory
import com.wardrobes.sisyphus.model.Wardrobe
import org.junit.Test

class ElementFactoryTests {

    @Test
    fun shouldCreateElementsForTopWardrobe() {
        val wardrobe = Wardrobe(width = 600f, height = 716f, depth = 320f, type = Wardrobe.Type.UPPER)
        val elements = TopWardrobeElementFactory.createElements(wardrobe = wardrobe, elementHeight = 18f)

        with(elements) {
            assert(size == 6); assert(getNumberOfPanels() == 2); assert(getNumberOfSides() == 2); assert(getNumberOfShelves() == 2)
            with(getPanel()) { assert(width == 300f); assert(length == 563f); assert(height == 18f) }
            with(getSide()) { assert(width == 320f); assert(length == 716f); assert(height == 18f) }
            with(getShelf()) { assert(width == 270f); assert(length == 562f); assert(height == 18f) }
        }

    }

    @Test
    fun shouldCreateElementsForBottomWardrobe() {
        val wardrobe = Wardrobe(width = 400f, height = 800f, depth = 500f, type = Wardrobe.Type.BOTTOM)
        val elements = BottomWardrobeElementFactory.createElements(wardrobe = wardrobe, elementHeight = 18f)

        with(elements) {
            assert(size == 8); assert(getNumberOfPanels() == 1); getNumberOfSupportingBars() == 2; assert(getNumberOfSides() == 2); assert(getNumberOfShelves() == 3)
            with(getSupportingBar()) { assert(width == 136f); assert(length == 363f); assert(height == 18f) }
            with(getPanel()) { assert(width == 445f); assert(length == 363f); assert(height == 18f) }
            with(getSide()) { assert(width == 500f); assert(length == 800f); assert(height == 18f) }
            with(getShelf()) { assert(width == 415f); assert(length == 362f); assert(height == 18f) }
        }

    }

}


private fun List<Element>.getPanel() = first { it.name == "Wieniec" }
private fun List<Element>.getNumberOfPanels() = count { it.name == "Wieniec" }

private fun List<Element>.getSupportingBar() = first { it.name == "Listwa wspierająca" }
private fun List<Element>.getNumberOfSupportingBars() = count { it.name == "Listwa wspierająca" }

private fun List<Element>.getSide() = first { it.name == "Bok" }
private fun List<Element>.getNumberOfSides() = count { it.name == "Bok" }

private fun List<Element>.getShelf() = first { it.name == "Półka" }
private fun List<Element>.getNumberOfShelves() = count { it.name == "Półka" }