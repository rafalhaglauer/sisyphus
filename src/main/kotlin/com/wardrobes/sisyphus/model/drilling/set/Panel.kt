package com.wardrobes.sisyphus.model.drilling.set

object PanelDrillingSet : RelativeDrillingSet {

    override fun getRelativeDrillingGroup(): List<RelativeDrilling> =
        listOf(RelativeDrilling(xPosition = RelativePosition(offset = 40f), drillingType = DowelDrilling),
                RelativeDrilling(xPosition = RelativePosition(offset = -20f, percentageOffset = 0.5f), drillingType = DowelDrilling),
                RelativeDrilling(xPosition = RelativePosition(percentageOffset = 0.5f), drillingType = DowelDrilling),
                RelativeDrilling(xPosition = RelativePosition(offset = 20f, percentageOffset = 0.5f), drillingType = DowelDrilling),
                RelativeDrilling(xPosition = RelativePosition(offset = 40f, referenceType =  ReferenceType.END), drillingType = DowelDrilling))

}