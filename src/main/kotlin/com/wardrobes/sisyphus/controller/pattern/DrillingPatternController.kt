package com.wardrobes.sisyphus.controller.pattern

import com.wardrobes.sisyphus.domain.pattern.DrillingPattern
import com.wardrobes.sisyphus.model.DrillingPatternRepository
import com.wardrobes.sisyphus.model.ElementPatternRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pattern")
class DrillingPatternController(
    private val drillingPatternRepository: DrillingPatternRepository,
    private val elementPatternRepository: ElementPatternRepository
) {

    @GetMapping("/element/{elementId}/drillings")
    fun getAll(@PathVariable elementId: Long): List<DrillingPattern> = drillingPatternRepository.findAll().filter {
        it.element.id == elementId
    }

    @GetMapping("/drilling/{id}")
    fun get(@PathVariable id: Long) = drillingPatternRepository.findById(id).get()

    @PostMapping("/element/{elementId}/drillings")
    fun create(@PathVariable elementId: Long, @RequestBody drilling: DrillingPattern) {
        val elementPattern = elementPatternRepository.findById(elementId).get()
        drillingPatternRepository.save(drilling.apply { element = elementPattern })
    }

    @PutMapping("/drilling/{id}")
    fun update(@PathVariable id: Long, @RequestBody drilling: DrillingPattern) {
        drillingPatternRepository.save(get(id).apply {
            name = drilling.name
            positionX = drilling.positionX
            positionY = drilling.positionY
            destination = drilling.destination
        })
    }

    @DeleteMapping("/drilling/{id}")
    fun delete(@PathVariable id: Long) = drillingPatternRepository.deleteById(id)

}