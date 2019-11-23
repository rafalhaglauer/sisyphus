package com.wardrobes.sisyphus.domain.pattern

import com.wardrobes.sisyphus.domain.wardrobe.Wardrobe
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class LengthPattern(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var pattern: String = ""
) {

    fun calculate(wardrobe: Wardrobe): Int = Calculator.calculate(WardrobePatternTransformer.transform(pattern, wardrobe)).toInt()

}

private object WardrobePatternTransformer {

    fun transform(rawPattern: String, wardrobe: Wardrobe): String = rawPattern
        .replace("W", wardrobe.width.toString())
        .replace("H", wardrobe.height.toString())
        .replace("D", wardrobe.depth.toString())

}