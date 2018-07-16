package com.wardrobes.sisyphus.model.drilling.set

import com.wardrobes.sisyphus.model.wardrobe.Drilling
import com.wardrobes.sisyphus.model.wardrobe.Element

interface RelativeDrillingSet {

    fun getDrillingGroup(): List<RelativeDrilling>
}

class RelativeDrilling(val xPosition: RelativePosition = RelativePosition(), val yPosition: RelativePosition = RelativePosition(), val depth: Float, val diameter: Float, condition: (Int) -> Boolean = { true }) {

    constructor(xPosition: RelativePosition = RelativePosition(), yPosition: RelativePosition = RelativePosition(), drillingType: DrillingType, condition: (Int) -> Boolean = { true }) : this(xPosition, yPosition, drillingType.depth, drillingType.diameter, condition)

    fun toDrilling(element: Element): Drilling = with(element) {
        Drilling(
                xPosition = xPosition.toAbsolute(width),
                yPosition = yPosition.toAbsolute(length),
                diameter = diameter,
                depth = depth)
    }

}

class RelativePosition(var offset: Float = 0f, val percentageOffset: Float = 0f, val referenceType: ReferenceType = ReferenceType.BEGIN) {

    fun toAbsolute(size: Float): Float =
            when(referenceType) {
                ReferenceType.BEGIN -> (size * percentageOffset) + offset
                ReferenceType.END -> (size * (1 - percentageOffset)) - offset
            }
}

object DowelDrilling : DrillingType(12f, 8f)

object SupportDrilling : DrillingType(12f, 5f)

sealed class DrillingType(val depth: Float, val diameter: Float)

enum class ReferenceType {
    BEGIN, END
}

fun List<RelativeDrilling>.transform(xOffset: Float = 0F, yOffset: Float = 0F): List<RelativeDrilling> =
        map {
            it.xPosition.offset += if (it.xPosition.referenceType == ReferenceType.BEGIN) xOffset else -xOffset
            it.yPosition.offset += if (it.yPosition.referenceType == ReferenceType.BEGIN) yOffset else -yOffset
            it
        }