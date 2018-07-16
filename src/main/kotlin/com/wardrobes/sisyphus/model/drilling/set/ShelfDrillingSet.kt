package com.wardrobes.sisyphus.model.drilling.set

object ShelfDrillingSet : RelativeDrillingSet {

    override fun getDrillingGroup(): List<RelativeDrilling> =
            listOf(RelativeDrilling(xPosition = RelativePosition(offset = 40f), drillingType = SupportDrilling),
                    RelativeDrilling(xPosition = RelativePosition(offset = 40f, referenceType = ReferenceType.END), drillingType = SupportDrilling))

}