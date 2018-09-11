package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wardrobe")
class WardrobeController(
        private val wardrobeRepository: WardrobeRepository,
        private val elementRepository: ElementRepository,
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository,
        private val referenceElementRelativeDrillingCompositionRepository: ReferenceElementRelativeDrillingCompositionRepository
) {

    @GetMapping
    fun getAll(@RequestParam("creationType") creationType: Wardrobe.CreationType? = null) = wardrobeRepository.findAll().filter { creationType == null || it.creationType == creationType }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = wardrobeRepository.findById(id).get()

    @PostMapping
    fun create(@RequestBody wardrobe: WardrobeLight): Long {
        val createdWardrobe = wardrobeRepository.save(wardrobe.toFull())
        if (createdWardrobe.creationType == Wardrobe.CreationType.STANDARD) {
            when (createdWardrobe.type) {
                Wardrobe.Type.HANGING -> {
                    val drillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == PANEL_HANGING_WARDROBE_COMPOSITION_NAME }
                    val shelfDrillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == SHELF_HANGING_WARDROBE_COMPOSITION_NAME }
                    val elements = HangingWardrobeElementFactory.createElements(createdWardrobe, 18f).map { it.toFull(createdWardrobe) }.also {
                        it.forEach { elementRepository.save(it) }
                    }
                    val panel = elements.first { it.name == "Wieniec" }
                    val shelf = elements.first { it.name == "Półka" }
                    val numberOfShelves = elements.filter { it.name == "Półka" }.size
                    elements.filter { it.name == "Bok" }.forEach {
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = OffsetLight(value = 1F),
                                        yOffset = OffsetLight(value = 1F),
                                        elementId = it.id,
                                        referenceElementId = panel.id,
                                        relativeDrillingCompositionId = drillingComposition.id
                                ).toFull(drillingComposition, element = it, referenceElement = panel)
                        )
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = OffsetLight(value = 1F),
                                        yOffset = OffsetLight(
                                                value = 1F,
                                                percentageValue = 1F,
                                                direction = CompositeOffset.Direction.BACKWARD
                                        ),
                                        elementId = it.id,
                                        referenceElementId = panel.id,
                                        relativeDrillingCompositionId = drillingComposition.id
                                ).toFull(drillingComposition, element = it, referenceElement = panel)
                        )

                        (1..numberOfShelves).forEach { shelfIndex ->
                            referenceElementRelativeDrillingCompositionRepository.save(
                                    ReferenceElementRelativeDrillingCompositionLight(
                                            xReferenceLength = Element.LengthType.WIDTH,
                                            yReferenceLength = Element.LengthType.HEIGHT,
                                            xOffset = OffsetLight(value = 20F),
                                            yOffset = OffsetLight(percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)),
                                            elementId = it.id,
                                            referenceElementId = shelf.id,
                                            relativeDrillingCompositionId = shelfDrillingComposition.id
                                    ).toFull(shelfDrillingComposition, element = it, referenceElement = shelf)
                            )
                            referenceElementRelativeDrillingCompositionRepository.save(
                                    ReferenceElementRelativeDrillingCompositionLight(
                                            xReferenceLength = Element.LengthType.WIDTH,
                                            yReferenceLength = Element.LengthType.HEIGHT,
                                            xOffset = OffsetLight(value = 20F),
                                            yOffset = OffsetLight(
                                                    value = 32F,
                                                    percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)
                                            ),
                                            elementId = it.id,
                                            referenceElementId = shelf.id,
                                            relativeDrillingCompositionId = shelfDrillingComposition.id
                                    ).toFull(shelfDrillingComposition, element = it, referenceElement = shelf)
                            )
                            referenceElementRelativeDrillingCompositionRepository.save(
                                    ReferenceElementRelativeDrillingCompositionLight(
                                            xReferenceLength = Element.LengthType.WIDTH,
                                            yReferenceLength = Element.LengthType.HEIGHT,
                                            xOffset = OffsetLight(value = 20F),
                                            yOffset = OffsetLight(
                                                    value = 32F,
                                                    direction = CompositeOffset.Direction.BACKWARD,
                                                    percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)
                                            ),
                                            elementId = it.id,
                                            referenceElementId = shelf.id,
                                            relativeDrillingCompositionId = shelfDrillingComposition.id
                                    ).toFull(shelfDrillingComposition, element = it, referenceElement = shelf)
                            )
                        }
                    }
                }
                Wardrobe.Type.STANDING -> {
                    val bottomPanelDrillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME }
                    val supportingBarDrillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME }
                    val shelfDrillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == SHELF_STANDING_WARDROBE_COMPOSITION_NAME }
                    val elements = StandingWardrobeElementFactory.createElements(createdWardrobe, 18f).map { it.toFull(createdWardrobe) }.also {
                        it.forEach { elementRepository.save(it) }
                    }
                    val bottomPanel = elements.first { it.name == "Wieniec dolny" }
                    val supportingBar = elements.first { it.name == "Listwa wspierająca" }
                    val shelf = elements.first { it.name == "Półka" }
                    val numberOfShelves = elements.filter { it.name == "Półka" }.size
                    elements.filter { it.name == "Bok" }.forEach {
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = OffsetLight(1F),
                                        yOffset = OffsetLight(1F),
                                        elementId = it.id,
                                        referenceElementId = supportingBar.id,
                                        relativeDrillingCompositionId = supportingBarDrillingComposition.id
                                ).toFull(supportingBarDrillingComposition, element = it, referenceElement = supportingBar)
                        )
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = OffsetLight(1F), // TODO 1 + nuta!
                                        yOffset = OffsetLight(1F),
                                        elementId = it.id,
                                        referenceElementId = supportingBar.id,
                                        relativeDrillingCompositionId = supportingBarDrillingComposition.id
                                ).toFull(supportingBarDrillingComposition, element = it, referenceElement = supportingBar)
                        )
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = OffsetLight(1F),
                                        yOffset = OffsetLight(
                                                value = 1F,
                                                direction = CompositeOffset.Direction.BACKWARD,
                                                percentageValue = 1F
                                        ), // TODO OFFSET END VERIFICATION AND REPAIR
                                        elementId = it.id,
                                        referenceElementId = bottomPanel.id,
                                        relativeDrillingCompositionId = bottomPanelDrillingComposition.id
                                ).toFull(bottomPanelDrillingComposition, element = it, referenceElement = bottomPanel)
                        )

                        (1..numberOfShelves).forEach { shelfIndex ->
                            referenceElementRelativeDrillingCompositionRepository.save(
                                    ReferenceElementRelativeDrillingCompositionLight(
                                            xReferenceLength = Element.LengthType.WIDTH,
                                            yReferenceLength = Element.LengthType.HEIGHT,
                                            xOffset = OffsetLight(value = 20F),
                                            yOffset = OffsetLight(percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)),
                                            elementId = it.id,
                                            referenceElementId = shelf.id,
                                            relativeDrillingCompositionId = shelfDrillingComposition.id
                                    ).toFull(shelfDrillingComposition, element = it, referenceElement = shelf))

                            referenceElementRelativeDrillingCompositionRepository.save(
                                    ReferenceElementRelativeDrillingCompositionLight(
                                            xReferenceLength = Element.LengthType.WIDTH,
                                            yReferenceLength = Element.LengthType.HEIGHT,
                                            xOffset = OffsetLight(value = 20F),
                                            yOffset = OffsetLight(
                                                    value = 32F,
                                                    percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)
                                            ),
                                            elementId = it.id,
                                            referenceElementId = shelf.id,
                                            relativeDrillingCompositionId = shelfDrillingComposition.id
                                    ).toFull(shelfDrillingComposition, element = it, referenceElement = shelf))

                            referenceElementRelativeDrillingCompositionRepository.save(
                                    ReferenceElementRelativeDrillingCompositionLight(
                                            xReferenceLength = Element.LengthType.WIDTH,
                                            yReferenceLength = Element.LengthType.HEIGHT,
                                            xOffset = OffsetLight(value = 20F),
                                            yOffset = OffsetLight(
                                                    value = 32F,
                                                    direction = CompositeOffset.Direction.BACKWARD,
                                                    percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)
                                            ),
                                            elementId = it.id,
                                            referenceElementId = shelf.id,
                                            relativeDrillingCompositionId = shelfDrillingComposition.id
                                    ).toFull(shelfDrillingComposition, element = it, referenceElement = shelf))
                        }
                    }
                }
            }
        }
        return createdWardrobe.id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody wardrobe: WardrobeLight) {
        wardrobeRepository.findById(id).get().apply {
            symbol = wardrobe.symbol
            width = wardrobe.width
            height = wardrobe.height
            depth = wardrobe.depth
            type = wardrobe.type
        }.also { wardrobeRepository.save(it) }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        wardrobeRepository.deleteById(id)
    }
}