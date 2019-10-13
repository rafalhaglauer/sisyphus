package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/composition")
class CompositionController(
    private val repositorySet: ElementDrillingSetCompositionRepository,
    private val elementRepository: ElementRepository,
    private val relativeDrillingSetRepository: RelativeDrillingSetRepository
) {

    @GetMapping("/all/{elementId}")
    fun getAll(@PathVariable elementId: Long): Collection<ElementDrillingSetComposition> {
        return repositorySet
            .findAll()
            .filter { it.element.id == elementId }
    }

    @GetMapping("/{compositionId}")
    fun get(@PathVariable compositionId: Long): ElementDrillingSetComposition {
        return repositorySet.findById(compositionId).get()
    }

    @DeleteMapping("/{compositionId}")
    fun delete(@PathVariable compositionId: Long) {
        repositorySet.deleteById(compositionId)
    }

    @PutMapping("/{compositionId}")
    fun update(@PathVariable compositionId: Long, @RequestBody requestBody: ElementDrillingSetCompositionRequest) {
        repositorySet.findById(compositionId).get().apply {
            xOffset = requestBody.xOffset.apply { id = xOffset.id }
            yOffset = requestBody.yOffset.apply { id = yOffset.id }
        }.also { repositorySet.save(it) }
    }

    @PostMapping
    fun create(@RequestBody requestBody: ElementDrillingSetCompositionRequest) {
        with(requestBody) {
            val element = elementRepository.findById(elementId).get()
            val drillingSet = relativeDrillingSetRepository.findById(drillingSetId).get()
            ElementDrillingSetComposition(
                element = element,
                drillingSet = drillingSet,
                xOffset = xOffset,
                yOffset = yOffset
            ).also { repositorySet.save(it) }
        }
    }
}

class ElementDrillingSetCompositionRequest(
    val elementId: Long,
    val drillingSetId: Long,
    val xOffset: Offset,
    val yOffset: Offset
)