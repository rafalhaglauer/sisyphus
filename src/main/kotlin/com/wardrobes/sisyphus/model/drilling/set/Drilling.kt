package com.wardrobes.sisyphus.model.drilling.set

interface RelativeDrillingSet {

    fun getRelativeDrillingGroup() : List<RelativeDrilling>
}

class RelativeDrilling(val xPosition: RelativePosition = RelativePosition(), val yPosition: RelativePosition = RelativePosition(), val depth: Float, val diameter: Float, condition: (Int) -> Boolean = { true }) {

    constructor(xPosition: RelativePosition = RelativePosition(), yPosition: RelativePosition = RelativePosition(), drillingType: DrillingType, condition: (Int) -> Boolean = { true }) : this(xPosition, yPosition, drillingType.depth, drillingType.diameter, condition)

}

class RelativePosition(private val offset: Float = 0f, private val percentageOffset: Float = 0f, private val referenceType: ReferenceType = ReferenceType.BEGIN) {

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

/*
*
* companion object {

                fun dowel(xPosition: Float, yPosition: Float) = Drilling(xPosition = xPosition, yPosition = yPosition, depth = 10F, diameter = 8F)

                fun stealDowel(xPosition: Float, yPosition: Float) = Drilling(xPosition = xPosition, yPosition = yPosition, depth = 8F, diameter = 5F)

                fun stealDowelConnector(xPosition: Float, yPosition: Float) = Drilling(xPosition = xPosition, yPosition = yPosition, depth = 8F, diameter = 10F)

                fun support(xPosition: Float, yPosition: Float) = Drilling(xPosition = xPosition, yPosition = yPosition, depth = 8F, diameter = 5F)

                fun confirmatScrew(xPosition: Float, yPosition: Float, elementHeight: Float = 18F) = Drilling(xPosition = xPosition, yPosition = yPosition, depth = elementHeight.toFloat(), diameter = 5F)
        }

* */