package com.wardrobes.sisyphus.model.wardrobe

import javax.persistence.*

data class SimpleWardrobe(var symbol: String = "",
                          var width: Float = 0F,
                          var height: Float = 0F,
                          var depth: Float = 0F,
                          var type: WardrobeType = WardrobeType.UNDEFINED)

@Entity
data class WardrobeDetails(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var symbol: String = "",
        var width: Float = 0F,
        var height: Float = 0F,
        var depth: Float = 0F,
        var type: WardrobeType = WardrobeType.UNDEFINED,
        @OneToMany(cascade = [CascadeType.ALL])
        var elements: MutableList<Element> = mutableListOf()) {

    companion object {

        fun newInstance(simpleWardrobe: SimpleWardrobe): WardrobeDetails = simpleWardrobe.let { createWardrobe(it.symbol, it.width, it.height, it.depth, it.type) }

        private fun createWardrobe(symbol: String, width: Float, height: Float, depth: Float, type: WardrobeType): WardrobeDetails =
                when (type) {
                    WardrobeType.STANDING -> StandingWardrobeFactory
                    WardrobeType.HANGING -> HangingWardrobeFactory
                    WardrobeType.UNDEFINED -> UndefinedWardrobeFactory
                }.createWardrobe(symbol, width, height, depth)

        fun unknown(): WardrobeDetails = WardrobeDetails(0, "", 0F, 0F, 0F, WardrobeType.UNDEFINED, mutableListOf())

    }

    fun addElement(element: Element) {
        elements.add(element)
    }
}

enum class WardrobeType {
    STANDING, HANGING, UNDEFINED
}