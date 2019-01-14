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

    @PostMapping
    fun create(@RequestBody element: ElementLight): Long {
        val wardrobe = wardrobeRepository.findById(element.wardrobeId).get()
        return elementRepository.save(element.toFull(wardrobe)).id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody element: ElementLight) {
        elementRepository.findById(id).get().apply {
            name = element.name
            length = element.length
            width = element.width
            height = element.height
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        elementRepository.deleteById(id)
    }
}

@RestController
@RequestMapping("/drilling/relative/composition")
class RelativeDrillingCompositionController(private val repository: RelativeDrillingCompositionRepository) {

    @GetMapping("/all")
    fun getAll(): Collection<RelativeDrillingSet> = repository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RelativeDrillingSet = repository.findById(id).get()

    @PostMapping
    fun create(@RequestBody relativeDrillingSet: RelativeDrillingSetLight): Long = repository.save(relativeDrillingSet.toFull()).id

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        repository.deleteById(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody relativeDrillingSet: RelativeDrillingSetLight) {
        repository.findById(id).get().apply {
            name = relativeDrillingSet.name
        }
    }
}

@RestController
@RequestMapping("/drilling/relative")
class RelativeDrillingController(
        private val repository: RelativeDrillingRepository,
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository
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

    @PostMapping
    fun create(@RequestBody relativeDrilling: RelativeDrillingLight): Long {
        val relativeDrillingComposition = relativeDrillingCompositionRepository.findById(relativeDrilling.relativeDrillingCompositionId).get()
        return repository.save(relativeDrilling.toFull(relativeDrillingComposition)).id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody relativeDrilling: RelativeDrillingLight) {
        repository.findById(id).get().apply {
            name = relativeDrilling.name
            xOffset = relativeDrilling.xOffset.toFull()
            yOffset = relativeDrilling.yOffset.toFull()
            diameter = relativeDrilling.diameter
            depth = relativeDrilling.depth
        }.also { repository.save(it) }
    }
}