//package com.wardrobes.sisyphus.api.drilling
//
//import com.wardrobes.sisyphus.api.element.ElementRepository
//import com.wardrobes.sisyphus.model.wardrobe.Drilling
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/drilling")
//class DrillingController(val elementRepository: ElementRepository) {
//
//    @GetMapping("/all/{elementId}")
//    fun getAll(@PathVariable elementId: Long) = elementRepository.findById(elementId).get().drillings
//
//    @GetMapping("/{drillingId}")
//    fun get(@PathVariable drillingId: Long) = elementRepository.findAll().first { it.drillings.find { it.id == drillingId } != null }.drillings.first { it.id == drillingId }
//
//    @PostMapping
//    fun create(@RequestParam("elementId") elementId: Long, @RequestBody drilling: Drilling): Long {
//        val element = elementRepository.findById(elementId).get()
//        element.drillings.add(drilling.toDetails())
//        elementRepository.save(element)
//        return element.drillings.last().id
//    }
//
//    @PutMapping("/{drillingId}")
//    fun update(@PathVariable drillingId: Long, @RequestBody drilling: Drilling): Long {
//        val element = elementRepository.findAll().first { it.drillings.find { it.id == drillingId } != null }
//        val newDrilling = element.drillings.first { it.id == drillingId }
//        newDrilling.apply {
//            xPosition = drilling.xPosition
//            yPosition = drilling.yPosition
//            depth = drilling.depth
//            diameter = drilling.diameter
//        }
//        elementRepository.save(element)
//        return drillingId
//    }
//
//    @DeleteMapping("/{drillingId}")
//    fun remove(@PathVariable drillingId: Long): Boolean {
//        val element = elementRepository.findAll().first { it.drillings.find { it.id == drillingId } != null }
//        element.drillings.removeIf { it.id == drillingId }
//        elementRepository.save(element)
//        return true
//    }
//}