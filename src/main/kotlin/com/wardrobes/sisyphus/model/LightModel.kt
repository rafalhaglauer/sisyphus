package com.wardrobes.sisyphus.model

data class WardrobeLight(
        var symbol: String,
        var width: Float,
        var height: Float,
        var depth: Float,
        var type: Wardrobe.Type
) {
    fun toFull(): Wardrobe = Wardrobe(
            symbol = symbol,
            width = width,
            height = height,
            depth = depth,
            type = type
    )
}

data class ElementLight(
        val name: String,
        val length: Float,
        val width: Float,
        val height: Float,
        val wardrobeId: Long
) {
    fun toFull(wardrobe: Wardrobe): Element = Element(
            name = name,
            length = length,
            width = width,
            height = height,
            wardrobe = wardrobe
    )
}

data class DrillingLight(
        val xPosition: Float,
        val yPosition: Float,
        val diameter: Float,
        val depth: Float,
        val elementId: Long
) {
    fun toFull(element: Element): Drilling {
        return Drilling(
                xPosition = xPosition,
                yPosition = yPosition,
                diameter = diameter,
                depth = depth,
                type = CreationType.CUSTOM,
                element = element
        )
    }
}

data class RelativeDrillingSetLight(val name: String) {
    fun toFull(): RelativeDrillingSet = RelativeDrillingSet(name = name)
}

data class RelativeDrillingLight(
        val name: String,
        val diameter: Float,
        val depth: Float,
        val relativeDrillingCompositionId: Long,
        val xOffset: OffsetLight = OffsetLight(),
        val yOffset: OffsetLight = OffsetLight()
) {
    fun toFull(relativeDrillingSet: RelativeDrillingSet): RelativeDrilling {
        return RelativeDrilling(
                name = name,
                xOffset = xOffset.toFull(),
                yOffset = yOffset.toFull(),
                diameter = diameter,
                depth = depth,
                relativeDrillingSet = relativeDrillingSet
        )
    }
}

data class OffsetLight(
        var value: Float = 0F,
        var percentageValue: Float = 0F,
        var direction: Offset.Direction = Offset.Direction.FORWARD
) {
    fun toFull(): Offset = Offset(value = value, percentageValue = percentageValue, direction = direction)
}