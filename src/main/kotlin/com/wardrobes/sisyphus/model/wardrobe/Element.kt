package com.wardrobes.sisyphus.model.wardrobe

import com.wardrobes.sisyphus.model.drilling.set.RelativeDrilling
import javax.persistence.*

const val STANDARD_ELEMENT_HEIGHT = 18F

@Entity
data class Element(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var name: String = "",
        var length: Float = 0F,
        var width: Float = 0F,
        var height: Float = STANDARD_ELEMENT_HEIGHT,
        @OneToMany(cascade = [CascadeType.ALL])
        val drillings: MutableList<Drilling> = mutableListOf()) {

    companion object {

        fun newInstance(name: String, length: Float, width: Float, height: Float = STANDARD_ELEMENT_HEIGHT): Element = Element(0, name, length, width, height, mutableListOf())

        fun unknown(): Element = Element(0, "", 0F, 0F, 0F, mutableListOf())
    }

    fun addDrilling(relativeDrilling: RelativeDrilling) {
        relativeDrilling.toDrilling(this).also { addDrilling(it) }
    }

    fun addDrilling(drilling: Drilling) {
        drillings.add(drilling)
    }
}