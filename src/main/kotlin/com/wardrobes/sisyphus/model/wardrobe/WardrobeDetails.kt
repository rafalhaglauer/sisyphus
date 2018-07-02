package com.wardrobes.sisyphus.model.wardrobe

import com.wardrobes.sisyphus.model.wardrobe.element.HangingWardrobeElementFactory
import com.wardrobes.sisyphus.model.wardrobe.element.StandingWardrobeElementFactory
import com.wardrobes.sisyphus.model.wardrobe.element.UndefinedElementFactory
import javax.persistence.*

data class SimpleWardrobe(var symbol: String,
                          var width: Float,
                          var height: Float,
                          var depth: Float,
                          var type: WardrobeType)

@Entity
data class WardrobeDetails(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var symbol: String,
        var width: Float,
        var height: Float,
        var depth: Float,
        var type: WardrobeType,
        @OneToMany
        var elements: MutableList<Element> = mutableListOf()) {

    companion object {

        fun newInstance(simpleWardrobe: SimpleWardrobe): WardrobeDetails = simpleWardrobe.run { createWardrobe(symbol, width, height, depth, type) }

        private fun createWardrobe(symbol: String, width: Float, height: Float, depth: Float, type: WardrobeType): WardrobeDetails =
            WardrobeDetails(symbol = symbol, width = width, height = height, depth = depth, type = type).apply {
                when(type) {
                    WardrobeType.STANDING -> StandingWardrobeElementFactory(width, height, depth)
                    WardrobeType.HANGING -> HangingWardrobeElementFactory(width, height, depth)
                    WardrobeType.UNDEFINED -> UndefinedElementFactory
                }.createElements().forEach { addElement(it) }
            }
    }

    fun addElement(element: Element) {
        element.wardrobeId = id
        elements.add(element)
    }
}

enum class WardrobeType {
    STANDING, HANGING, UNDEFINED
}