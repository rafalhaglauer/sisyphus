package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/drilling")
class DrillingController(
        private val referenceElementRelativeDrillingCompositionRepository: ReferenceElementRelativeDrillingCompositionRepository,
        private val relativeDrillingRepository: RelativeDrillingRepository,
        private val drillingRepository: DrillingRepository,
        private val elementRepository: ElementRepository
) {
    @GetMapping("/all/{elementId}")
    fun getAll(@PathVariable elementId: Long): Collection<Drilling> {
        val relativeDrillingGroup = relativeDrillingRepository.findAll()
        val drillingGroup = mutableListOf<Drilling>()

        referenceElementRelativeDrillingCompositionRepository
                .findAll()
                .filter { it.element.id == elementId }
                .forEach { referenceElementRelativeDrillingComposition ->
                    relativeDrillingGroup
                            .filter { it.relativeDrillingComposition == referenceElementRelativeDrillingComposition.relativeDrillingComposition }
                            .map {
                                referenceElementRelativeDrillingComposition.xOffset
                                it.toAbsolute(
                                        element = referenceElementRelativeDrillingComposition.element,
                                        referenceElement = referenceElementRelativeDrillingComposition.referenceElement,
                                        xReferenceLength = referenceElementRelativeDrillingComposition.xReferenceLength,
                                        yReferenceLength = referenceElementRelativeDrillingComposition.yReferenceLength,
                                        xOffset = referenceElementRelativeDrillingComposition.xOffset,
                                        yOffset = referenceElementRelativeDrillingComposition.yOffset
                                )
                            }
                            .also { drillingGroup.addAll(it) }
                }
        drillingRepository
                .findAll()
                .filter { it.element.id == elementId }
                .also { drillingGroup.addAll(it) }

        return drillingGroup
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Drilling = drillingRepository.findById(id).get()

    @PostMapping
    fun create(@RequestBody drilling: DrillingLight): Long {
        val element = elementRepository.findById(drilling.elementId).get()
        return drillingRepository.save(drilling.toFull(element)).id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody drilling: DrillingLight) {
        drillingRepository.findById(id).get().apply {
            xPosition = drilling.xPosition
            yPosition = drilling.yPosition
            depth = drilling.depth
            diameter = drilling.diameter
        }.also { drillingRepository.save(it) }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        drillingRepository.deleteById(id)
    }
}