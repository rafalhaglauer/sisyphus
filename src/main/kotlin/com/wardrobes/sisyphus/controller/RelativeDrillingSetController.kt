package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.RelativeDrillingSet
import com.wardrobes.sisyphus.model.RelativeDrillingSetRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/drilling/relative/composition")
class RelativeDrillingSetController(private val repository: RelativeDrillingSetRepository) {

    @GetMapping("/all")
    fun getAll(): Collection<RelativeDrillingSet> = repository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RelativeDrillingSet = repository.findById(id).get()

    @PostMapping
    fun create(@RequestBody relativeDrillingSet: RelativeDrillingSet) {
        repository.save(relativeDrillingSet).id
    }

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
