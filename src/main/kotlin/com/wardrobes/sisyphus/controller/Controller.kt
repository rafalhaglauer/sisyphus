package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.RelativeDrilling
import com.wardrobes.sisyphus.model.RelativeDrillingRepository
import com.wardrobes.sisyphus.model.RelativeDrillingSet
import com.wardrobes.sisyphus.model.RelativeDrillingSetRepository
import org.springframework.web.bind.annotation.*

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