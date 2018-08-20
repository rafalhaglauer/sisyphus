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
    fun create(@RequestBody wardrobe: WardrobeLight, @RequestParam("creationType") creationType: Wardrobe.CreationType): Long {
        val createdWardrobe = wardrobeRepository.save(wardrobe.toFull())
        if (createdWardrobe.creationType == Wardrobe.CreationType.STANDARD) {
            when (createdWardrobe.type) {
                Wardrobe.Type.HANGING -> {
                    val drillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == PANEL_HANHING_WARDROBE_COMPOSITION_NAME }
                    val elements = HangingWardrobeElementFactory.createElements(wardrobe, 18f).map { it.toFull(createdWardrobe) }.also {
                        it.forEach { elementRepository.save(it) }
                    }
                    val panel = elements.first { it.name == "Wieniec" }
                    elements.filter { it.name == "Bok" }.forEach {
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        yOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        elementId = it.id,
                                        referenceElementId = panel.id,
                                        relativeDrillingCompositionId = drillingComposition.id
                                ).toFull(drillingComposition, element = it, referenceElement = panel)
                        )
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        yOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 10F, reference = Offset.Reference.END), // TODO OFFSET END VERIFICATION AND REPAIR
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        elementId = it.id,
                                        referenceElementId = panel.id,
                                        relativeDrillingCompositionId = drillingComposition.id
                                ).toFull(drillingComposition, element = it, referenceElement = panel)
                        )
                    }
                }
                Wardrobe.Type.STANDING -> {
                    val bottomPanelDrillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME }
                    val supportingBarDrillingComposition = relativeDrillingCompositionRepository.findAll().first { it.name == SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME }
                    val elements = StandingWardrobeElementFactory.createElements(wardrobe, 18f).map { it.toFull(createdWardrobe) }.also {
                        it.forEach { elementRepository.save(it) }
                    }
                    val bottomPanel = elements.first { it.name == "Wieniec dolny" }
                    val supportingBar = elements.first { it.name == "Listwa wspierająca" }
                    elements.filter { it.name == "Bok" }.forEach {
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        yOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        elementId = it.id,
                                        referenceElementId = supportingBar.id,
                                        relativeDrillingCompositionId = supportingBarDrillingComposition.id
                                ).toFull(supportingBarDrillingComposition, element = it, referenceElement = supportingBar)
                        )
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.END), // TODO 1 + nuta!
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        yOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        elementId = it.id,
                                        referenceElementId = supportingBar.id,
                                        relativeDrillingCompositionId = supportingBarDrillingComposition.id
                                ).toFull(supportingBarDrillingComposition, element = it, referenceElement = supportingBar)
                        )
                        referenceElementRelativeDrillingCompositionRepository.save(
                                ReferenceElementRelativeDrillingCompositionLight(
                                        xReferenceLength = Element.LengthType.WIDTH,
                                        yReferenceLength = Element.LengthType.HEIGHT,
                                        xOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 1F, reference = Offset.Reference.BEGIN),
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        yOffset = CompositeOffsetLight(
                                                offset = OffsetLight(value = 10F, reference = Offset.Reference.END), // TODO OFFSET END VERIFICATION AND REPAIR
                                                percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)
                                        ),
                                        elementId = it.id,
                                        referenceElementId = bottomPanel.id,
                                        relativeDrillingCompositionId = bottomPanelDrillingComposition.id
                                ).toFull(bottomPanelDrillingComposition, element = it, referenceElement = bottomPanel)
                        )
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
            height = wardrobe.height
            depth = wardrobe.depth
            type = wardrobe.type
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        wardrobeRepository.deleteById(id)
    }
}