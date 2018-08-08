package com.wardrobes.sisyphus.api.element

import com.wardrobes.sisyphus.api.wardrobe.WardrobeRepository
import com.wardrobes.sisyphus.model.wardrobe.Element
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/element")
class ElementController(val elementRepository: ElementRepository, val wardrobeRepository: WardrobeRepository) {

    @GetMapping("/all/{wardrobeId}")
    fun getAll(@PathVariable wardrobeId: Long) = wardrobeRepository.findById(wardrobeId).get().elements

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = elementRepository.findById(id)

    @PostMapping
    fun create(@RequestParam("wardrobeId") wardrobeId: Long, @RequestBody element: Element): Long {
        val wardrobe = wardrobeRepository.findById(wardrobeId).get()
        val elementDetails = element.toDetails()
        wardrobe.add(elementDetails)
        wardrobeRepository.save(wardrobe)
        return wardrobe.elements.last().id
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): Boolean {
        val wardrobe = wardrobeRepository.findAll().first { it.elements.find { it.id == id } != null }
        wardrobe.elements.removeIf { it.id == id }
        wardrobeRepository.save(wardrobe)
        return true
    }

    @PostMapping("/copy")
    fun copy(@RequestParam("id") elementId: Long, @RequestParam("name") elementName: String): Long {
        val wardrobe = wardrobeRepository.findAll().first { it.elements.find { it.id == elementId } != null }
        val oldElement = wardrobe.elements.first { it.id == elementId }
        val new = oldElement.copy(elementName)
        new.also { element ->
            oldElement.drillings.forEach { element.addDrilling(it.copy()) }
            wardrobe.add(element)
        }
        wardrobeRepository.save(wardrobe)
        return wardrobe.elements.last().id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody element: Element): Long {
        val wardrobe = wardrobeRepository.findAll().first { it.elements.find { it.id == id } != null }
        val oldElement = wardrobe.elements.first { it.id == id }
        oldElement.apply {
            name = element.name
            length = element.length
            width = element.width
            height = element.height
        }
        wardrobeRepository.save(wardrobe)
        return id
    }
}