package com.wardrobes.sisyphus.domain.wardrobe

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Wardrobe(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var symbol: String = "",
    var width: Int = 0,
    var height: Int = 0,
    var depth: Int = 0
)