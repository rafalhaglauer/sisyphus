package com.wardrobes.sisyphus.model.wardrobe

import com.wardrobes.sisyphus.model.drilling.driller.HangingWardrobePanelDriller
import com.wardrobes.sisyphus.model.drilling.driller.ShelfDriller
import com.wardrobes.sisyphus.model.drilling.driller.StandingWardrobePanelDriller

sealed class ElementFactory {

    abstract fun createTopPanel(width: Float, length: Float, height: Float): Element

    abstract fun createBottomPanel(width: Float, length: Float, height: Float): Element

    abstract fun createLeftSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element

    abstract fun createRightSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element

    abstract fun createShelf(width: Float, length: Float, height: Float): Element
}

object HangingWardrobeElementFactory : ElementFactory() {
    private val panelDriller = HangingWardrobePanelDriller
    private val shelfDriller = ShelfDriller

    override fun createTopPanel(width: Float, length: Float, height: Float): Element = Element.newInstance("Wieniec górny", length, width, height)

    override fun createBottomPanel(width: Float, length: Float, height: Float): Element = Element.newInstance("Wieniec dolny", length, width, height)

    override fun createLeftSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element = Element.newInstance("Bok lewy", length, width, height).also {
        panelDriller.drill(it)
        shelfDriller.drill(it, numberOfShelves)
    }

    override fun createRightSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element = Element.newInstance("Bok prawy", length, width, height).also {
        panelDriller.drill(it)
        shelfDriller.drill(it, numberOfShelves)
    }

    override fun createShelf(width: Float, length: Float, height: Float): Element = Element.newInstance("Półka", length, width, height)

}

object StandingWardrobeElementFactory : ElementFactory() {
    private val panelDriller = StandingWardrobePanelDriller
    private val shelfDriller = ShelfDriller

    override fun createTopPanel(width: Float, length: Float, height: Float): Element = Element.newInstance("Wieniec górny", length, width, height)

    override fun createBottomPanel(width: Float, length: Float, height: Float): Element = Element.newInstance("Wieniec dolny", length, width, height)

    override fun createLeftSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element = Element.newInstance("Bok lewy", length, width, height).also {
        panelDriller.drill(it)
        shelfDriller.drill(it, numberOfShelves)
    }

    override fun createRightSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element = Element.newInstance("Bok prawy", length, width, height).also {
        panelDriller.drill(it)
        shelfDriller.drill(it, numberOfShelves)
    }

    override fun createShelf(width: Float, length: Float, height: Float): Element = Element.newInstance("Półka", length, width, height)

}

object UnknownWardrobeElementFactory : ElementFactory() {

    override fun createTopPanel(width: Float, length: Float, height: Float): Element = Element.unknown()

    override fun createBottomPanel(width: Float, length: Float, height: Float): Element = Element.unknown()

    override fun createLeftSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element = Element.unknown()

    override fun createRightSide(width: Float, length: Float, height: Float, numberOfShelves: Int): Element = Element.unknown()

    override fun createShelf(width: Float, length: Float, height: Float): Element = Element.unknown()
}