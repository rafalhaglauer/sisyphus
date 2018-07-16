package com.wardrobes.sisyphus.model.drilling.driller

import com.wardrobes.sisyphus.model.drilling.set.ShelfDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.transform
import com.wardrobes.sisyphus.model.wardrobe.Element

object ShelfDriller {
    private val drillingSet = ShelfDrillingSet

    fun drill(element: Element, numberOfShelves: Int) {
        (1..numberOfShelves).forEach {
            drillingSet.getDrillingGroup().transform(yOffset = (element.length / numberOfShelves) * it).forEach { element.addDrilling(it) }
        }
    }
}