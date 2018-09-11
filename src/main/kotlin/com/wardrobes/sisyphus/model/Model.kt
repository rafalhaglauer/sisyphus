package com.wardrobes.sisyphus.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
data class Wardrobe(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var symbol: String = "",
        var width: Float = 0f,
        var height: Float = 0f,
        var depth: Float = 0f,
        var type: Type = Type.STANDING,
        var creationType: CreationType = CreationType.STANDARD
) {
    enum class Type { STANDING, HANGING }

    enum class CreationType { CUSTOM, STANDARD }
}

@Entity
data class Element(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var name: String = "",
        var length: Float = 0f,
        var width: Float = 0f,
        var height: Float = 0f,
        val creationType: CreationType = CreationType.CUSTOM,
        @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) var wardrobe: Wardrobe = Wardrobe()
) {
    enum class LengthType {
        LENGTH, WIDTH, HEIGHT
    }

    enum class CreationType {
        CUSTOM, GENERATED
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
        var id: Long = 0,
        var xPosition: Float = 0f,
        var yPosition: Float = 0f,
        var diameter: Float = 0f,
        var depth: Float = 0f,
        var type: CreationType = CreationType.CUSTOM,
        @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) var element: Element = Element()
) {
    enum class CreationType {
        CUSTOM, GENERATED
    }
}

@Entity
data class ReferenceElementRelativeDrillingComposition(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        @OneToOne @OnDelete(action = OnDeleteAction.CASCADE) var relativeDrillingComposition: RelativeDrillingComposition = RelativeDrillingComposition(),
        @OneToOne @OnDelete(action = OnDeleteAction.CASCADE) var referenceElement: Element = Element(),
        @OneToOne @OnDelete(action = OnDeleteAction.CASCADE) var element: Element = Element(),
        @OneToOne(cascade = [(CascadeType.ALL)]) var xOffset: CompositeOffset = CompositeOffset(),
        @OneToOne(cascade = [(CascadeType.ALL)]) var yOffset: CompositeOffset = CompositeOffset(),
        var xReferenceLength: Element.LengthType = Element.LengthType.WIDTH,
        var yReferenceLength: Element.LengthType = Element.LengthType.HEIGHT
)

@Entity
data class RelativeDrillingComposition(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var name: String = "",
        var suggestXReferenceValue: String = "",
        var suggestYReferenceValue: String = "",
        var creationType: CreationType = CreationType.GENERATED
) {
    enum class CreationType {
        GENERATED, CUSTOM
    }
}

@Entity
data class RelativeDrilling(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var name: String = "",
        @OneToOne(cascade = [(CascadeType.ALL)]) var xOffset: CompositeOffset = CompositeOffset(),
        @OneToOne(cascade = [(CascadeType.ALL)]) var yOffset: CompositeOffset = CompositeOffset(),
        var diameter: Float = 0f,
        var depth: Float = 0f,
        @ManyToOne var relativeDrillingComposition: RelativeDrillingComposition = RelativeDrillingComposition()
) {
    fun toAbsolute(element: Element, referenceElement: Element, xReferenceLength: Element.LengthType, yReferenceLength: Element.LengthType,
                   xOffset: CompositeOffset, yOffset: CompositeOffset): Drilling {
        return Drilling(
                xPosition = this@RelativeDrilling.xOffset.toAbsolute(referenceElement.getValue(xReferenceLength)) + xOffset.toAbsolute(element.width),
                yPosition = this@RelativeDrilling.yOffset.toAbsolute(referenceElement.getValue(yReferenceLength)) + yOffset.toAbsolute(element.length),
                diameter = this@RelativeDrilling.diameter,
                depth = this@RelativeDrilling.depth,
                type = Drilling.CreationType.GENERATED,
                element = element
        )
    }
}

@Entity
data class CompositeOffset(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var value: Float = 0F,
        var percentageValue: Float = 0F,
        var direction: Direction = Direction.FORWARD
) {
    fun toAbsolute(referenceValue: Float): Float {
        return (percentageValue * referenceValue) + if (direction == Direction.FORWARD) value else -value
    }

    enum class Direction {
        FORWARD, BACKWARD
    }
}