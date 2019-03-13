package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.Drilling
import com.wardrobes.sisyphus.model.ElementDrillingSetCompositionRepository
import com.wardrobes.sisyphus.model.RelativeDrillingRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drilling")
class DrillingController(
        private val elementDrillingSetCompositionRepository: ElementDrillingSetCompositionRepository,
        private val relativeDrillingRepository: RelativeDrillingRepository
) {
    @GetMapping("/all/{elementId}")
    fun getAll(@PathVariable elementId: Long): Collection<Drilling> {
        val relativeDrillingGroup = relativeDrillingRepository.findAll()
        val drillingGroup = mutableListOf<Drilling>()

        elementDrillingSetCompositionRepository
                .findAll()
                .filter { it.element.id == elementId }
                .forEach { composition ->
                    relativeDrillingGroup
                            .filter { it.relativeDrillingSet == composition.drillingSet }
                            .map {
                                it.toAbsolute(
                                        element = composition.element,
                                        xOffset = composition.xOffset,
                                        yOffset = composition.yOffset
                                )
                            }
                            .also { drillingGroup.addAll(it) }
                }

        return drillingGroup
    }
}