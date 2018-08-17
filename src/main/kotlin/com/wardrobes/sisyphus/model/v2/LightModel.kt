package com.wardrobes.sisyphus.model.v2

data class WardrobeLight(
        var symbol: String = "",
        var width: Float = 0f,
        var height: Float = 0f,
        var depth: Float = 0f,
        var type: Wardrobe.Type = Wardrobe.Type.STANDING,
        var creationType: Wardrobe.CreationType = Wardrobe.CreationType.STANDARD
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
        val height: Float
) {
    fun toFull(): Element = Element(
            name = name,
            length = length,
            width = width,
            height = height
    )
}

data class DrillingLight(
        val xPosition: Float,
        val yPosition: Float,
        val diameter: Float,
        val depth: Float,
        val type: Drilling.CreationType,
        val elementId: Long
) {
    fun toFull(element: Element): Drilling {
        return Drilling(
                xPosition = xPosition,
                yPosition = yPosition,
                diameter = diameter,
                depth = depth,
                type = type,
                element = element
        )
    }
}

data class ReferenceElementRelativeDrillingCompositionLight(
        val xReferenceLength: Element.LengthType,
        val yReferenceLength: Element.LengthType,
        val xOffset: CompositeOffsetLight,
        val yOffset: CompositeOffsetLight,
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
    fun toFull(): RelativeDrillingComposition = RelativeDrillingComposition(
            name = name,
            suggestXReferenceValue = suggestXReferenceValue,
            suggestYReferenceValue = suggestYReferenceValue
    )
}

data class RelativeDrillingLight(
        val xOffset: CompositeOffsetLight,
        val yOffset: CompositeOffsetLight,
        val diameter: Float,
        val depth: Float,
        val relativeDrillingCompositionId: Long
) {
    fun toFull(relativeDrillingComposition: RelativeDrillingComposition): RelativeDrilling {
        return RelativeDrilling(
                xOffset = xOffset.toFull(),
                yOffset = yOffset.toFull(),
                diameter = diameter,
                depth = depth,
                relativeDrillingComposition = relativeDrillingComposition
        )
    }
}

data class CompositeOffsetLight(
        val offset: OffsetLight,
        val percentageOffset: OffsetLight
) {
    fun toFull(): CompositeOffset = CompositeOffset(offset = offset.toFull(), percentageOffset = percentageOffset.toFull())
}

data class OffsetLight(
        val value: Float = 0f,
        val reference: Offset.Reference
) {
    fun toFull(): Offset = Offset(value = value, reference = reference)
}