package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/element")
class ElementController(
        private val elementRepository: ElementRepository,
        private val wardrobeRepository: WardrobeRepository
) {

    @GetMapping("/all/{wardrobeId}")
    fun getAll(@PathVariable wardrobeId: Long): Collection<Element> = elementRepository.findAll().filter { it.wardrobe.id == wardrobeId }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Element = elementRepository.findById(id).get()

    @PostMapping("/{wardrobeId}")
    fun create(@RequestBody createElement: Element, @PathVariable wardrobeId: Long): Long {
        val wardrobe = wardrobeRepository.findById(wardrobeId).get()
        return elementRepository.save(createElement.apply { this.wardrobe = wardrobe }).id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody createElement: Element) {
        elementRepository.findById(id).get().apply {
            name = createElement.name
            length = createElement.length
            width = createElement.width
            height = createElement.height
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        elementRepository.deleteById(id)
    }
}

@RestController
@RequestMapping("/drilling/relative/composition")
class RelativeDrillingCompositionController(private val repository: RelativeDrillingSetRepository) {

    @GetMapping("/all")
    fun getAll(): Collection<RelativeDrillingSet> = repository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RelativeDrillingSet = repository.findById(id).get()

    @PostMapping
    fun create(@RequestBody relativeDrillingSet: RelativeDrillingSet): Long = repository.save(relativeDrillingSet).id

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        repository.deleteById(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody relativeDrillingSet: RelativeDrillingSet) {
        repository.findById(id).get().apply {
            name = relativeDrillingSet.name
        }
    }
}

@RestController
@RequestMapping("/drilling/relative")
class RelativeDrillingController(
        private val repository: RelativeDrillingRepository,
        private val relativeDrillingSetRepository: RelativeDrillingSetRepository
) {

    @GetMapping("/all/{relativeDrillingCompositionId}")
    fun getAll(@PathVariable relativeDrillingCompositionId: Long): Collection<RelativeDrilling> =
            repository.findAll()
                    .filter { it.relativeDrillingSet.id == relativeDrillingCompositionId }
                    .toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RelativeDrilling = repository.findById(id).get()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        repository.deleteById(id)
    }

    @PostMapping("/{relativeDrillingSetId}")
    fun create(@RequestBody relativeDrilling: RelativeDrilling, relativeDrillingSetId: Long): Long {
        val relativeDrillingSet = relativeDrillingSetRepository.findById(relativeDrillingSetId).get()
        return repository.save(relativeDrilling.apply { this.relativeDrillingSet = relativeDrillingSet }).id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody relativeDrilling: RelativeDrilling) {
        repository.findById(id).get().apply {
            name = relativeDrilling.name
            xOffset = relativeDrilling.xOffset
            yOffset = relativeDrilling.yOffset
            diameter = relativeDrilling.diameter
            depth = relativeDrilling.depth
        }.also { repository.save(it) }
    }
}