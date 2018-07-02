package com.wardrobes.sisyphus.model.wardrobe

import javax.persistence.*

const val STANDARD_ELEMENT_HEIGHT = 18F

@Entity
data class Element(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var wardrobeId: Long = 0,
        var name: String,
        var length: Float,
        var width: Float,
        var height: Float,
        @OneToMany
        val drillings: MutableList<Drilling> = ArrayList()) {

    fun addDrilling(drilling: Drilling) {
        drilling.elementId = id
        drillings.add(drilling)
    }
}