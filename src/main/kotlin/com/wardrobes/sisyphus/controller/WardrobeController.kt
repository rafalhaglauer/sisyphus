package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.domain.wardrobe.Wardrobe
import com.wardrobes.sisyphus.model.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wardrobe")
class WardrobeController(
    private val wardrobePatternRepository: WardrobePatternRepository,
    private val wardrobeRepository: WardrobeRepository
) {

    @GetMapping
    fun getAll(): Collection<Wardrobe> = wardrobeRepository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = wardrobeRepository.findById(id).get()

    @PostMapping("/{patternId}")
    fun create(@RequestBody wardrobe: Wardrobe, @PathVariable patternId: String) {
        if (patternId.isBlank()) {
            wardrobeRepository.save(wardrobe)
        } else {
            wardrobePatternRepository.findByIdOrNull(patternId)?.run {
                wardrobeRepository.save(createWardrobe(wardrobe.width, wardrobe.height, wardrobe.depth))
            }
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody wardrobe: Wardrobe) {
        wardrobeRepository.findById(id).get().apply {
            symbol = wardrobe.symbol
            width = wardrobe.width
            height = wardrobe.height
            depth = wardrobe.depth
        }.also { wardrobeRepository.save(it) }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        wardrobeRepository.deleteById(id)
    }

}