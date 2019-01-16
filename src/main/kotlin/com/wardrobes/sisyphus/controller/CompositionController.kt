package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.ElementDrillingSetComposition
import com.wardrobes.sisyphus.model.ElementDrillingSetCompositionRepository
import com.wardrobes.sisyphus.model.ElementRepository
import com.wardrobes.sisyphus.model.RelativeDrillingSetRepository
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

//    @PutMapping("/{compositionId}")
//    fun update(@PathVariable compositionId: Long, @RequestBody setComposition: CompositeOffset) {
//        val element = elementRepository.findById(setComposition.elementId).get()
//        val relativeDrillingComposition = relativeDrillingSetRepository.findById(setComposition.relativeDrillingCompositionId).get()
//        repositorySet.findById(compositionId).get().apply {
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
//        }.also { repositorySet.save(it) }
//        repositorySet.save(setComposition.toFull(relativeDrillingComposition, element = element))
//    }

    @PostMapping("/{elementId}/add/{drillingSetId}")
    fun create(@RequestBody requestBody: ElementDrillingSetComposition, @PathVariable elementId: Long, @PathVariable drillingSetId: Long) {
        val element = elementRepository.findById(elementId).get()
        val drillingSet = relativeDrillingSetRepository.findById(drillingSetId).get()
        repositorySet.save(requestBody.apply {
            this.drillingSet = drillingSet
            this.element = element
        })
    }
}