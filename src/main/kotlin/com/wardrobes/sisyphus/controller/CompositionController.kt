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
    fun getAll(@PathVariable elementId: Long): Collection<ReferenceElementRelativeDrillingComposition> {
        return repository.findAll()
                .filter { it.element.id == elementId }
    }

    @GetMapping("/{compositionId}")
    fun get(@PathVariable compositionId: Long): ReferenceElementRelativeDrillingComposition {
        return repository.findById(compositionId).get()
    }

    @DeleteMapping("/{compositionId}")
    fun delete(@PathVariable compositionId: Long) {
        repository.deleteById(compositionId)
    }

    @PutMapping("/{compositionId}")
    fun update(@PathVariable compositionId: Long, @RequestBody composition: ReferenceElementRelativeDrillingCompositionLight) {
        val element = elementRepository.findById(composition.elementId).get()
        val referenceElement = elementRepository.findById(composition.referenceElementId).get()
        val relativeDrillingComposition = relativeDrillingCompositionRepository.findById(composition.relativeDrillingCompositionId).get()
        repository.findById(compositionId).get().apply {
            this.element = element
            this.referenceElement = element
            this.relativeDrillingComposition = relativeDrillingComposition
            this.xOffset.apply {
                val light = composition.xOffset
                this.offset.apply {
                    this.value = light.offset.value
                    this.reference = light.offset.reference
                }
                this.percentageOffset.apply {
                    this.value = light.percentageOffset.value
                    this.reference = light.percentageOffset.reference
                }
            }
            this.xReferenceLength = composition.xReferenceLength
            this.yOffset.apply {
                val light = composition.yOffset
                this.offset.apply {
                    this.value = light.offset.value
                    this.reference = light.offset.reference
                }
                this.percentageOffset.apply {
                    this.value = light.percentageOffset.value
                    this.reference = light.percentageOffset.reference
                }
            }
            this.yReferenceLength = composition.yReferenceLength
        }.also { repository.save(it) }
        repository.save(composition.toFull(relativeDrillingComposition, element = element, referenceElement = referenceElement))
    }

    @PostMapping
    fun create(@RequestBody composition: ReferenceElementRelativeDrillingCompositionLight) {
        val element = elementRepository.findById(composition.elementId).get()
        val referenceElement = elementRepository.findById(composition.referenceElementId).get()
        val relativeDrillingComposition = relativeDrillingCompositionRepository.findById(composition.relativeDrillingCompositionId).get()
        repository.save(composition.toFull(relativeDrillingComposition, element = element, referenceElement = referenceElement))
    }
}