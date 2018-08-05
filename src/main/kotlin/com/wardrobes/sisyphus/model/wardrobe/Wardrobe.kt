package com.wardrobes.sisyphus.model.wardrobe

import javax.persistence.*

data class Wardrobe(var symbol: String = "",
                    var width: Float = 0F,
                    var height: Float = 0F,
                    var depth: Float = 0F,
                    var type: Type = Type.UNDEFINED) {

    enum class Type {
        STANDING, HANGING, UNDEFINED
    }

    enum class CreationType {
        CUSTOM, STANDARD, UNDEFINED
    }
}

@Entity
data class WardrobeDetails(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var symbol: String = "",
        var width: Float = 0F,
        var height: Float = 0F,
        var depth: Float = 0F,
        var type: Wardrobe.Type = Wardrobe.Type.UNDEFINED,
        var creationType: Wardrobe.CreationType = Wardrobe.CreationType.UNDEFINED,
        @OneToMany(cascade = [CascadeType.ALL])
        var elements: MutableList<Element> = mutableListOf()) {

    companion object {

        fun standardWardrobe(wardrobe: Wardrobe) = wardrobe.run {
            when (type) {
                Wardrobe.Type.STANDING -> StandingWardrobeFactory
                Wardrobe.Type.HANGING -> HangingWardrobeFactory
                Wardrobe.Type.UNDEFINED -> UndefinedWardrobeFactory
            }.createWardrobe(symbol, width, height, depth).apply {
                this.creationType = Wardrobe.CreationType.STANDARD
            }
        }

        fun customWardrobe(wardrobe: Wardrobe) = wardrobe.run {
            WardrobeDetails(
                    symbol = symbol,
                    width = width,
                    height = height,
                    depth = depth,
                    type = type,
                    creationType = Wardrobe.CreationType.CUSTOM
            )
        }

        fun unknown(): WardrobeDetails = WardrobeDetails(0, "", 0F, 0F, 0F, Wardrobe.Type.UNDEFINED, Wardrobe.CreationType.UNDEFINED, mutableListOf())

    }

    fun addElement(element: Element) {
        elements.add(element)
    }
}