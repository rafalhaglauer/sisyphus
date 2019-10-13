package com.wardrobes.sisyphus.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
data class Wardrobe(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var symbol: String = "",
    var width: Int = 0,
    var height: Int = 0,
    var depth: Int = 0
)

@Entity
data class Attachment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var url: String = "",
    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) var wardrobe: Wardrobe = Wardrobe()
)

@Entity
data class Element(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var name: String = "",
    var length: Int = 0,
    var width: Int = 0,
    var height: Int = 0,
    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) var wardrobe: Wardrobe = Wardrobe()
)

data class Drilling(
    var xPosition: Float = 0f,
    var yPosition: Float = 0f,
    var diameter: Float = 0f,
    var depth: Float = 0f
)

@Entity
data class ElementDrillingSetComposition(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @OneToOne @OnDelete(action = OnDeleteAction.CASCADE) var drillingSet: RelativeDrillingSet = RelativeDrillingSet(),
    @OneToOne @OnDelete(action = OnDeleteAction.CASCADE) var element: Element = Element(),
    @OneToOne(cascade = [(CascadeType.ALL)]) var xOffset: Offset = Offset(),
    @OneToOne(cascade = [(CascadeType.ALL)]) var yOffset: Offset = Offset()
)

@Entity
data class RelativeDrillingSet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var name: String = ""
)

@Entity
data class RelativeDrilling(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var name: String = "",
    @OneToOne(cascade = [(CascadeType.ALL)]) var xOffset: Offset = Offset(),
    @OneToOne(cascade = [(CascadeType.ALL)]) var yOffset: Offset = Offset(),
    var diameter: Float = 0f,
    var depth: Float = 0f,
    @ManyToOne var relativeDrillingSet: RelativeDrillingSet = RelativeDrillingSet()
) {
    fun toAbsolute(element: Element, xOffset: Offset, yOffset: Offset): Drilling {
        return Drilling(
            xPosition = this@RelativeDrilling.xOffset.toAbsolute(element.width) + xOffset.toAbsolute(element.width),
            yPosition = this@RelativeDrilling.yOffset.toAbsolute(element.height) + yOffset.toAbsolute(element.length),
            diameter = this@RelativeDrilling.diameter,
            depth = this@RelativeDrilling.depth
        )
    }
}

@Entity
data class Offset(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var value: Float = 0F,
    var percentageValue: Float = 0F,
    var direction: Direction = Direction.FORWARD
) {
    fun toAbsolute(referenceValue: Int): Float {
        return (percentageValue * referenceValue) + if (direction == Direction.FORWARD) value else -value
    }

    enum class Direction {
        FORWARD, BACKWARD
    }
}