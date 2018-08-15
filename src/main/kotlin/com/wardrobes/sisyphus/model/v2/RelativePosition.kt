package com.wardrobes.sisyphus.model.v2

import javax.persistence.*

@Entity
data class Element(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val length: Float = 0f,
        val width: Float = 0f,
        val height: Float = 0f
) {
    enum class LengthType {
        LENGTH, WIDTH, HEIGHT
    }

    fun getValue(lengthType: LengthType): Float =
            when (lengthType) {
                LengthType.LENGTH -> length
                LengthType.WIDTH -> width
                LengthType.HEIGHT -> height
            }
}

@Entity
data class Drilling(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val xPosition: Float = 0f,
        val yPosition: Float = 0f,
        val diameter: Float = 0f,
        val depth: Float = 0f,
        val type: CreationType = CreationType.CUSTOM,
        @ManyToOne val element: Element = Element()
) {
    enum class CreationType {
        CUSTOM, GENERATED
    }
}

@Entity
data class ReferenceElementRelativeDrillingComposition(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @OneToOne val relativeDrillingComposition: RelativeDrillingComposition = RelativeDrillingComposition(),
        @OneToOne val referenceElement: Element = Element(),
        val xReferenceLength: Element.LengthType = Element.LengthType.WIDTH,
        val yReferenceLength: Element.LengthType = Element.LengthType.HEIGHT
) {
//    fun getDrilling(): Collection<Drilling> = relativeDrillingComposition.toAbsolute(referenceElement, xReferenceLength, yReferenceLength)
}

@Entity
data class RelativeDrillingComposition(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val suggestXReferenceValue: String = "",
        val suggestYReferenceValue: String = ""
) {
//    fun toAbsolute(referenceElement: Element, xReferenceLength: Element.LengthType, yReferenceLength: Element.LengthType): Collection<Drilling> = drillingGroup.map { it.toAbsolute(referenceElement, xReferenceLength, yReferenceLength) }
}

@Entity
data class RelativeDrilling(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @OneToOne val xOffset: CompositeOffset = CompositeOffset(),
        @OneToOne val yOffset: CompositeOffset = CompositeOffset(),
        val diameter: Float = 0f,
        val depth: Float = 0f,
        @ManyToOne val relativeDrillingComposition: RelativeDrillingComposition = RelativeDrillingComposition()
) {
    fun toAbsolute(referenceElement: Element, xReferenceLength: Element.LengthType, yReferenceLength: Element.LengthType): Drilling {
        return Drilling(
                xPosition = xOffset.toAbsolute(referenceElement.getValue(xReferenceLength)),
                yPosition = yOffset.toAbsolute(referenceElement.getValue(yReferenceLength)),
                diameter = diameter,
                depth = depth,
                type = Drilling.CreationType.GENERATED,
                element = referenceElement
        )
    }
}

@Entity
data class CompositeOffset(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @OneToOne val offset: Offset = Offset(),
        @OneToOne val percentageOffset: Offset = Offset()
) {
    fun toAbsolute(referenceValue: Float): Float {
        val percentageOffsetFromBegin = with(percentageOffset) { if (reference == com.wardrobes.sisyphus.model.v2.Offset.Reference.BEGIN) value else 1 - value }
        return if (percentageOffsetFromBegin == 0F) {
            with(offset) { if (reference == com.wardrobes.sisyphus.model.v2.Offset.Reference.BEGIN) value else referenceValue - value }
        } else {
            val offsetFromBegin = with(offset) { if (reference == com.wardrobes.sisyphus.model.v2.Offset.Reference.BEGIN) value else -value }
            (referenceValue * percentageOffsetFromBegin) + offsetFromBegin
        }
    }
}

@Entity
data class Offset(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val value: Float = 0f,
        val reference: Reference = Reference.BEGIN
) {
    enum class Reference {
        BEGIN, END
    }
}