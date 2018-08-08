package com.wardrobes.sisyphus.model.wardrobe

import com.wardrobes.sisyphus.model.drilling.set.RelativeDrilling
import javax.persistence.*

const val STANDARD_ELEMENT_HEIGHT = 18F

data class Element(val name: String, val length: Float, val width: Float, val height: Float) {

    fun toDetails() = ElementDetails(name = name, length = length, width = width, height = height)
}

@Entity
data class ElementDetails(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 9,
        var name: String = "",
        var length: Float = 0F,
        var width: Float = 0F,
        var height: Float = STANDARD_ELEMENT_HEIGHT,
        @OneToMany(cascade = [CascadeType.ALL])
        var drillings: MutableList<DrillingDetails> = mutableListOf()
) {

    fun copy() = ElementDetails(name = name, length = length, width = width, height = height)

    fun copy(newName: String) = ElementDetails(name = newName, length = length, width = width, height = height)

    fun addDrilling(relativeDrilling: RelativeDrilling) {
        relativeDrilling.toDrilling(this).also { addDrilling(it) }
    }

    fun addDrilling(drillingDetails: DrillingDetails) {
        drillings.add(drillingDetails)
    }
}