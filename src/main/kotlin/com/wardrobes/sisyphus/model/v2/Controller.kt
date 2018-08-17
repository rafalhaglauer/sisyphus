package com.wardrobes.sisyphus.model.v2

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/element")
class ElementController(private val repository: ElementRepository) {

    @GetMapping("/all")
    fun getAll(): Collection<Element> = repository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Element = repository.findById(id).get()

    @PostMapping
    fun create(@RequestBody element: ElementLight): Long = repository.save(element.toFull()).id

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody element: ElementLight) {
        repository.findById(id).get().apply {
            name = element.name
            length = element.length
            width = element.width
            height = element.height
        }
    }
}

@RestController
@RequestMapping("/drilling/relative/composition")
class RelativeDrillingCompositionController(private val repository: RelativeDrillingCompositionRepository) {

    @GetMapping("/all")
    fun getAll(): Collection<RelativeDrillingComposition> = repository.findAll().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RelativeDrillingComposition = repository.findById(id).get()

    @PostMapping
    fun create(@RequestBody relativeDrillingComposition: RelativeDrillingCompositionLight): Long = repository.save(relativeDrillingComposition.toFull()).id

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody relativeDrillingComposition: RelativeDrillingCompositionLight) {
        repository.findById(id).get().apply {
            name = relativeDrillingComposition.name
            suggestXReferenceValue = relativeDrillingComposition.suggestXReferenceValue
            suggestYReferenceValue = relativeDrillingComposition.suggestYReferenceValue
        }
    }
}

@RestController
@RequestMapping("/drilling/relative")
class RelativeDrillingController(
        private val repository: RelativeDrillingRepository,
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository
) {

    @GetMapping("/all/{relativeDrillingCompositionId}")
    fun getAll(@PathVariable relativeDrillingCompositionId: Long): Collection<RelativeDrilling> =
            repository.findAll()
                    .filter { it.relativeDrillingComposition.id == relativeDrillingCompositionId }
                    .toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RelativeDrilling = repository.findById(id).get()

    @PostMapping
    fun create(@RequestBody relativeDrilling: RelativeDrillingLight): Long {
        val relativeDrillingComposition = relativeDrillingCompositionRepository.findById(relativeDrilling.relativeDrillingCompositionId).get()
        return repository.save(relativeDrilling.toFull(relativeDrillingComposition)).id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody relativeDrilling: RelativeDrillingLight) {
        repository.findById(id).get().apply {
            xOffset = relativeDrilling.xOffset.toFull()
            yOffset = relativeDrilling.yOffset.toFull()
            diameter = relativeDrilling.diameter
            depth = relativeDrilling.depth
        }
    }
}

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

    @PostMapping
    fun create(@RequestBody composition: ReferenceElementRelativeDrillingCompositionLight) {
        val element = elementRepository.findById(composition.elementId).get()
        val referenceElement = elementRepository.findById(composition.referenceElementId).get()
        val relativeDrillingComposition = relativeDrillingCompositionRepository.findById(composition.relativeDrillingCompositionId).get()
        repository.save(composition.toFull(relativeDrillingComposition, element = element, referenceElement = referenceElement))
    }
}

@RestController
@RequestMapping("/drilling")
class DrillingController(
        private val referenceElementRelativeDrillingCompositionRepository: ReferenceElementRelativeDrillingCompositionRepository,
        private val relativeDrillingRepository: RelativeDrillingRepository
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
                                it.toAbsolute(
                                        element = referenceElementRelativeDrillingComposition.element,
                                        referenceElement = referenceElementRelativeDrillingComposition.referenceElement,
                                        xReferenceLength = referenceElementRelativeDrillingComposition.xReferenceLength,
                                        yReferenceLength = referenceElementRelativeDrillingComposition.yReferenceLength
                                )
                            }
                            .also { drillingGroup.addAll(it) }
                }

        return drillingGroup
    }
}