//package com.wardrobes.sisyphus.model.wardrobe
//
//import javax.persistence.*
//
//data class Wardrobe(val symbol: String, val width: Float, val height: Float, val depth: Float, val type: Type) {
//
//    fun toDetails(creationType: Wardrobe.CreationType) = WardrobeDetails(symbol = symbol, width = width, height = height, depth = depth, type = type, creationType = creationType)
//
//    enum class Type { STANDING, HANGING }
//
//    enum class CreationType { CUSTOM, STANDARD }
//}
//
//@Entity
//data class WardrobeDetails(
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//        var id: Long = 0,
//        var symbol: String = "",
//        var width: Float = 0F,
//        var height: Float = 0F,
//        var depth: Float = 0F,
//        var type: Wardrobe.Type? = null,
//        var creationType: Wardrobe.CreationType? = null,
//        @OneToMany(cascade = [CascadeType.ALL])
//        var elements: MutableList<ElementDetails> = mutableListOf()
//) {
//
//    fun copy(wardrobeSymbol: String) = WardrobeDetails(
//            symbol = wardrobeSymbol,
//            width = width,
//            height = height,
//            depth = depth,
//            type = type,
//            creationType = Wardrobe.CreationType.CUSTOM
//    )
//
//    fun add(element: ElementDetails) {
//        elements.add(element)
//    }
//}