package com.wardrobes.sisyphus.model.drilling.driller

import com.wardrobes.sisyphus.model.drilling.set.PanelDrillingSet
import com.wardrobes.sisyphus.model.wardrobe.Drilling
import com.wardrobes.sisyphus.model.wardrobe.Element

object PanelSetDriller {
    private val drillingSet = PanelDrillingSet

    fun drill(panel: Element) {
        drillingSet.getRelativeDrillingGroup().forEach {
            panel.apply {
                addDrilling(Drilling(
                        xPosition = it.xPosition.toAbsolute(width),
                        yPosition = it.yPosition.toAbsolute(length),
                        diameter = it.diameter,
                        depth = it.depth,
                        elementId = id)
                )
            }
        }
    }
}