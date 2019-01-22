package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.Element
import com.wardrobes.sisyphus.model.ElementRepository
import com.wardrobes.sisyphus.model.WardrobeRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/element")
class ElementController(private val elementRepository: ElementRepository, private val wardrobeRepository: WardrobeRepository) {

    @GetMapping("/all/{wardrobeId}")
    fun getAll(@PathVariable wardrobeId: Long): Collection<Element> = elementRepository.findAll().filter { it.wardrobe.id == wardrobeId }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Element = elementRepository.findById(id).get()

    @PostMapping("/{wardrobeId}")
    fun create(@RequestBody createElement: Element, @PathVariable wardrobeId: Long) {
        val wardrobe = wardrobeRepository.findById(wardrobeId).get()
        elementRepository.save(createElement.apply { this.wardrobe = wardrobe })
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody element: Element) {
        elementRepository.findById(id).get().apply {
            name = element.name
            length = element.length
            width = element.width
            height = element.height
        }.also { elementRepository.save(it) }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        elementRepository.deleteById(id)
    }
}





