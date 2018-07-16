package com.wardrobes.sisyphus.api.drilling

import com.wardrobes.sisyphus.api.element.ElementRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drilling")
class DrillingController(val drillingRespository: DrillingRepository, val elementRepository: ElementRepository) {

    @GetMapping("/{elementId}")
    fun getAll(@PathVariable elementId: Long) = elementRepository.findById(elementId).get().drillings
}