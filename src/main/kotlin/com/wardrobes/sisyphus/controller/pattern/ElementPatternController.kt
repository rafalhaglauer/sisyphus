package com.wardrobes.sisyphus.controller.pattern

import com.wardrobes.sisyphus.domain.pattern.ElementPattern
import com.wardrobes.sisyphus.model.ElementPatternRepository
import com.wardrobes.sisyphus.model.WardrobePatternRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pattern")
class ElementPatternController(
    private val elementPatternRepository: ElementPatternRepository,
    private val wardrobePatternRepository: WardrobePatternRepository
) {

    @GetMapping("/wardrobe/{wardrobeId}/elements")
    fun getAll(@PathVariable wardrobeId: Long): List<ElementPattern> = elementPatternRepository.findAll().filter {
        it.wardrobe.id == wardrobeId
    }

    @GetMapping("/element/{id}")
    fun get(@PathVariable id: Long) = elementPatternRepository.findById(id).get()

    @PostMapping("/wardrobe/{wardrobeId}/elements")
    fun create(@PathVariable wardrobeId: Long, @RequestBody element: ElementPattern) {
        val wardrobePattern = wardrobePatternRepository.findById(wardrobeId).get()
        elementPatternRepository.save(element.apply { wardrobe = wardrobePattern })
    }

    @PutMapping("/element/{id}")
    fun update(@PathVariable id: Long, @RequestBody element: ElementPattern) {
        elementPatternRepository.save(get(id).apply {
            name = element.name
            length = element.length
            width = element.width
            height = element.height
        })
    }

    @DeleteMapping("/element/{id}")
    fun delete(@PathVariable id: Long) = elementPatternRepository.deleteById(id)

}