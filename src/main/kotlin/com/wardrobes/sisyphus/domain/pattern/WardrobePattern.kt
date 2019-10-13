package com.wardrobes.sisyphus.domain.pattern

import com.wardrobes.sisyphus.model.Wardrobe
import javax.persistence.*

@Entity
data class WardrobePattern(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    var symbol: String = "",
    var description: String = ""
) {

    fun createWardrobe(width: Int, height: Int, depth: Int): Wardrobe = Wardrobe(
        symbol = symbol,
        width = width,
        height = height,
        depth = depth
    )

}