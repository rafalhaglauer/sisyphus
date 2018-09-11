package com.wardrobes.sisyphus.model

data class WardrobeLight(
        var symbol: String,
        var width: Float,
        var height: Float,
        var depth: Float,
        var type: Wardrobe.Type,
        var creationType: Wardrobe.CreationType
) {
    fun toFull(): Wardrobe = Wardrobe(
            symbol = symbol,
            width = width,
            height = height,
            depth = depth,
            type = type,
            creationType = creationType
    )
}

data class ElementLight(
        val name: String,
        val length: Float,
        val width: Float,
        val height: Float,
        val wardrobeId: Long
) {
    fun toFull(wardrobe: Wardrobe, creationType: Element.CreationType = Element.CreationType.GENERATED): Element = Element(
            name = name,
            length = length,
            width = width,
            height = height,
            creationType = creationType,
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
                type = Drilling.CreationType.CUSTOM,
                element = element
        )
    }
}

data class ReferenceElementRelativeDrillingCompositionLight(
        val xReferenceLength: Element.LengthType,
        val yReferenceLength: Element.LengthType,
        val xOffset: OffsetLight,
        val yOffset: OffsetLight,
        val relativeDrillingCompositionId: Long,
        val referenceElementId: Long,
        val elementId: Long
) {
    fun toFull(relativeDrillingComposition: RelativeDrillingComposition, referenceElement: Element, element: Element): ReferenceElementRelativeDrillingComposition {
        return ReferenceElementRelativeDrillingComposition(
                xReferenceLength = xReferenceLength,
                yReferenceLength = yReferenceLength,
                xOffset = xOffset.toFull(),
                yOffset = yOffset.toFull(),
                relativeDrillingComposition = relativeDrillingComposition,
                referenceElement = referenceElement,
                element = element)
    }
}

data class RelativeDrillingCompositionLight(
        val name: String,
        val suggestXReferenceValue: String,
        val suggestYReferenceValue: String
) {
    fun toFull(creationType: RelativeDrillingComposition.CreationType = RelativeDrillingComposition.CreationType.CUSTOM): RelativeDrillingComposition = RelativeDrillingComposition(
            name = name,
            suggestXReferenceValue = suggestXReferenceValue,
            suggestYReferenceValue = suggestYReferenceValue,
            creationType = creationType
    )
}

data class RelativeDrillingLight(
        val name: String,
        val diameter: Float,
        val depth: Float,
        val relativeDrillingCompositionId: Long,
        val xOffset: OffsetLight = OffsetLight(),
        val yOffset: OffsetLight = OffsetLight()
) {
    fun toFull(relativeDrillingComposition: RelativeDrillingComposition): RelativeDrilling {
        return RelativeDrilling(
                name = name,
                xOffset = xOffset.toFull(),
                yOffset = yOffset.toFull(),
                diameter = diameter,
                depth = depth,
                relativeDrillingComposition = relativeDrillingComposition
        )
    }
}

data class OffsetLight(
        var value: Float = 0F,
        var percentageValue: Float = 0F,
        var direction: CompositeOffset.Direction = CompositeOffset.Direction.FORWARD
) {
    fun toFull(): CompositeOffset = CompositeOffset(value = value, percentageValue = percentageValue, direction = direction)
}