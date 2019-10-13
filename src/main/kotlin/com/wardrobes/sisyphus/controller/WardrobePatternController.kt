package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.domain.pattern.WardrobePattern
import com.wardrobes.sisyphus.model.WardrobePatternRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pattern/wardrobe")
class WardrobePatternController(
    private val wardrobePatternRepository: WardrobePatternRepository
) {

    @GetMapping
    fun getAll(): List<WardrobePattern> = wardrobePatternRepository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = wardrobePatternRepository.findById(id).get()

    @PostMapping
    fun create(@RequestBody wardrobe: WardrobePattern) {
        wardrobePatternRepository.save(wardrobe)
    }

}