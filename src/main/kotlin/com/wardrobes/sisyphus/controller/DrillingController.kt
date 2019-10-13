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

    @GetMapping("/gcode/{elementId}/{drillingSize}")
    fun getGcode(@PathVariable elementId: Long, @PathVariable drillingSize: Int): String = buildString {
        appendln("S1")
        appendln("M3")
        appendln("G0 X0 Y0 Z50")
        getAll(elementId).filter { it.diameter == drillingSize.toFloat() }.forEach {
            appendln(if (drillingSize > 5) "F120" else "F160")
            appendln("G0 X${it.xPosition} Y${it.yPosition}")
            appendln("G0 Z22")
            appendln("G1 Z6")
            appendln("G0 Z22")
        }
        appendln("G0 Z50")
        appendln("G0 X0 Y0 Z50")
        appendln("M2")
    }
}