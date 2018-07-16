package com.wardrobes.sisyphus.model.drilling.driller

import com.wardrobes.sisyphus.model.drilling.set.HangingWardrobePanelDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.StandingWardrobeBottomPanelDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.StandingWardrobeTopPanelDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.transform
import com.wardrobes.sisyphus.model.wardrobe.Element

object HangingWardrobePanelDriller {
    private val drillingSet = HangingWardrobePanelDrillingSet

    fun drill(element: Element) {
        drillingSet.getDrillingGroup().transform(yOffset = (element.height / 2) + 1).forEach { element.addDrilling(it) }
        drillingSet.getDrillingGroup().transform(yOffset = element.length - (element.height / 2) - 1).forEach { element.addDrilling(it) }
    }
}

object StandingWardrobePanelDriller {
    private val topDrillingSet = StandingWardrobeTopPanelDrillingSet
    private val bottomDrillingSet = StandingWardrobeBottomPanelDrillingSet

    fun drill(element: Element) {
        topDrillingSet.getDrillingGroup().transform(yOffset = element.length - (element.height / 2) - 1, xOffset = 50F).forEach { element.addDrilling(it) }
        topDrillingSet.getDrillingGroup().transform(yOffset = element.length - (element.height / 2) - 1, xOffset = element.width - 100).forEach { element.addDrilling(it) }
        bottomDrillingSet.getDrillingGroup().transform(yOffset = (element.height / 2) + 1).forEach { element.addDrilling(it) }
    }
}