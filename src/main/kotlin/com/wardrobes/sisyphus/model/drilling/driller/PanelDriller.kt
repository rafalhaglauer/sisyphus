package com.wardrobes.sisyphus.model.drilling.driller

import com.wardrobes.sisyphus.model.drilling.set.HangingWardrobePanelDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.StandingWardrobeBottomPanelDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.StandingWardrobeTopPanelDrillingSet
import com.wardrobes.sisyphus.model.drilling.set.transform
import com.wardrobes.sisyphus.model.wardrobe.DrillingDetails
import com.wardrobes.sisyphus.model.wardrobe.ElementDetails

object HangingWardrobePanelDriller {
    private val drillingSet = HangingWardrobePanelDrillingSet

    fun createDrillings(element: ElementDetails): List<DrillingDetails> {
        val drillings = mutableListOf<DrillingDetails>()
        drillingSet.getDrillingGroup().transform(yOffset = (element.height / 2) + 1).map { it.toDrilling(element) }.also { drillings.addAll(it) }
        drillingSet.getDrillingGroup().transform(yOffset = element.length - (element.height / 2) - 1).map { it.toDrilling(element) }.also { drillings.addAll(it) }
        return drillings
    }
}

object StandingWardrobePanelDriller {
    private val topDrillingSet = StandingWardrobeTopPanelDrillingSet
    private val bottomDrillingSet = StandingWardrobeBottomPanelDrillingSet

    fun createDrillings(element: ElementDetails): List<DrillingDetails> {
        val drillings = mutableListOf<DrillingDetails>()
        topDrillingSet.getDrillingGroup().transform(yOffset = element.length - (element.height / 2) - 1, xOffset = 50F).map { it.toDrilling(element) }.also { drillings.addAll(it) }
        topDrillingSet.getDrillingGroup().transform(yOffset = element.length - (element.height / 2) - 1, xOffset = element.width - 100).map { it.toDrilling(element) }.also { drillings.addAll(it) }
        bottomDrillingSet.getDrillingGroup().transform(yOffset = (element.height / 2) + 1).map { it.toDrilling(element) }.also { drillings.addAll(it) }
        return drillings
    }
}