package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/composition")
class CompositionController(
        private val repository: ReferenceElementRelativeDrillingCompositionRepository,
        private val elementRepository: ElementRepository,
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository
) {

    @GetMapping("/all/{elementId}")
    fun getAll(@PathVariable elementId: Long): Collection<ElementDrillingSetComposition> {
        return repository
                .findAll()
                .filter { it.element.id == elementId }
    }

    @GetMapping("/{compositionId}")
    fun get(@PathVariable compositionId: Long): ElementDrillingSetComposition {
        return repository.findById(compositionId).get()
    }

    @DeleteMapping("/{compositionId}")
    fun delete(@PathVariable compositionId: Long) {
        repository.deleteById(compositionId)
    }

//    @PutMapping("/{compositionId}")
//    fun update(@PathVariable compositionId: Long, @RequestBody setComposition: CompositeOffset) {
//        val element = elementRepository.findById(setComposition.elementId).get()
//        val relativeDrillingComposition = relativeDrillingCompositionRepository.findById(setComposition.relativeDrillingCompositionId).get()
//        repository.findById(compositionId).get().apply {
//            this.element = element
//            this.drillingSet = relativeDrillingComposition
//            this.xOffset.apply {
//                val light = setComposition.xOffset
//                value = light.value
//                percentageValue = light.value
//                direction = light.direction
//            }
//            this.yOffset.apply {
//                val light = setComposition.yOffset
//                value = light.value
//                percentageValue = light.value
//                direction = light.direction
//            }
//        }.also { repository.save(it) }
//        repository.save(setComposition.toFull(relativeDrillingComposition, element = element))
//    }

    @PostMapping
    fun create(@RequestBody requestBody: CreateElementDrillingSetCompositionRequestBody) {
        val element = elementRepository.findById(requestBody.elementId).get()
        val drillingSet = relativeDrillingCompositionRepository.findById(requestBody.drillingSetId).get()
        repository.save(ElementDrillingSetComposition(
                xOffset = requestBody.xOffset,
                yOffset = requestBody.yOffset,
                drillingSet = drillingSet,
                element = element)
        )
    }
}

class CreateElementDrillingSetCompositionRequestBody(
        val xOffset: Offset,
        val yOffset: Offset,
        val elementId: Long,
        val drillingSetId: Long
)