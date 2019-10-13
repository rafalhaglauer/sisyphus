package com.wardrobes.sisyphus.domain.pattern

import com.wardrobes.sisyphus.model.Element
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class PositionPattern(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var pattern: String = ""
) {

    fun calculate(element: Element): Float = Calculator.calculate(ElementPatternTransformer.transform(pattern, element)).toFloat()

}

private object ElementPatternTransformer {

    fun transform(rawPattern: String, element: Element): String = rawPattern
        .replace("L", element.length.toString())
        .replace("W", element.width.toString())

}