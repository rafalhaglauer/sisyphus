package com.wardrobes.sisyphus.api.element

import com.wardrobes.sisyphus.api.wardrobe.WardrobeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/element")
class ElementController(val elementRepository: ElementRepository, val wardrobeRepository: WardrobeRepository) {

    @GetMapping("/all/{wardrobeId}")
    fun getAll(@PathVariable wardrobeId: Long) = wardrobeRepository.findById(wardrobeId).get().elements

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = elementRepository.findById(id)
}