package com.wardrobes.sisyphus.controller

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

    @PostMapping
    fun create(@RequestBody wardrobe: Wardrobe) {
        wardrobeRepository.save(wardrobe)
    }

    @PostMapping("/fromPattern")
    fun create(@RequestBody request: CreateWardrobeFromPatternRequest) {
        wardrobePatternRepository.findByIdOrNull(request.wardrobePatternId)?.run {
            wardrobeRepository.save(createWardrobe(request.wardrobeWidth, request.wardrobeHeight, request.wardrobeDepth))
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

class CreateWardrobeFromPatternRequest(
    val wardrobePatternId: Long,
    val wardrobeWidth: Int,
    val wardrobeHeight: Int,
    val wardrobeDepth: Int
)