package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.model.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wardrobe")
class WardrobeController(
        private val wardrobeRepository: WardrobeRepository,
        private val elementRepository: ElementRepository,
        private val relativeDrillingSetRepository: RelativeDrillingSetRepository,
        private val elementDrillingSetCompositionRepository: ElementDrillingSetCompositionRepository
) {

    @GetMapping
    fun getAll(@RequestParam("wardrobeType") wardrobeType: Wardrobe.Type? = null) = wardrobeRepository.findAll().filter { wardrobeType == null || it.type == wardrobeType }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = wardrobeRepository.findById(id).get()

    @PostMapping
    fun create(@RequestBody wardrobe: Wardrobe, @RequestParam("creationType") creationType: CreationType): Long {
        val createdWardrobe = wardrobeRepository.save(wardrobe)
        if (creationType == CreationType.GENERATE) {
            when (createdWardrobe.type) {
                Wardrobe.Type.UPPER -> {
                    val panelDrillingSet = relativeDrillingSetRepository.findAll().first { it.name == PANEL_HANGING_WARDROBE_COMPOSITION_NAME }
                    val elements = HangingWardrobeElementFactory.createElements(createdWardrobe, 18f)
                            .map { elementRepository.save(it) }
                    val numberOfShelves = elements.filter { it.name == "Półka" }.size
                    elements.filter { it.name == "Bok" }.forEach {
                        elementDrillingSetCompositionRepository.save(
                                ElementDrillingSetComposition(
                                        xOffset = Offset(value = 1F),
                                        yOffset = Offset(value = (it.height / 2) + 1F),
                                        drillingSet = panelDrillingSet,
                                        element = it
                                )
                        )
                        elementDrillingSetCompositionRepository.save(
                                ElementDrillingSetComposition(
                                        xOffset = Offset(value = 1F),
                                        yOffset = Offset(
                                                value = (it.height / 2) + 1F,
                                                percentageValue = 1F,
                                                direction = Offset.Direction.BACKWARD
                                        ),
                                        drillingSet = panelDrillingSet,
                                        element = it
                                )
                        )
                        it.addDrillingListForShelves(numberOfShelves, createdWardrobe.type)
                    }
                }
                Wardrobe.Type.BOTTOM -> {
                    val bottomPanelDrillingComposition = relativeDrillingSetRepository.findAll().first { it.name == BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME }
                    val supportingBarDrillingComposition = relativeDrillingSetRepository.findAll().first { it.name == SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME }
                    val elements = StandingWardrobeElementFactory.createElements(createdWardrobe, 18f)
                            .map { elementRepository.save(it) }
                    val numberOfShelves = elements.filter { it.name == "Półka" }.size
                    elements.filter { it.name == "Bok" }.forEach {
                        elementDrillingSetCompositionRepository.save(
                                ElementDrillingSetComposition(
                                        xOffset = Offset(value = 1F),
                                        yOffset = Offset(value = (it.height / 2) + 1F),
                                        drillingSet = supportingBarDrillingComposition,
                                        element = it
                                )
                        )
                        elementDrillingSetCompositionRepository.save(
                                ElementDrillingSetComposition(
                                        xOffset = Offset(value = 1F), // TODO 1 + nuta!
                                        yOffset = Offset(value = (it.height / 2) + 1F),
                                        drillingSet = supportingBarDrillingComposition,
                                        element = it
                                )
                        )
                        elementDrillingSetCompositionRepository.save(
                                ElementDrillingSetComposition(
                                        xOffset = Offset(value = 1F),
                                        yOffset = Offset(
                                                value = (it.height / 2) + 1F,
                                                direction = Offset.Direction.BACKWARD,
                                                percentageValue = 1F
                                        ), // TODO OFFSET END VERIFICATION AND REPAIR
                                        drillingSet = bottomPanelDrillingComposition,
                                        element = it
                                )
                        )
                        it.addDrillingListForShelves(numberOfShelves, createdWardrobe.type)
                    }
                }
            }
        }
        return createdWardrobe.id
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody wardrobe: Wardrobe) {
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

    private fun Element.addDrillingListForShelves(numberOfShelves: Int, wardrobeType: Wardrobe.Type) {
        createDrillingSetCompositionsForShelves(numberOfShelves, wardrobeType).forEach { elementDrillingSetCompositionRepository.save(it) }
    }


    private fun Element.createDrillingSetCompositionsForShelves(numberOfShelves: Int, wardrobeType: Wardrobe.Type): List<ElementDrillingSetComposition> =
            when (wardrobeType) {
                Wardrobe.Type.UPPER -> SHELF_HANGING_WARDROBE_COMPOSITION_NAME
                Wardrobe.Type.BOTTOM -> SHELF_STANDING_WARDROBE_COMPOSITION_NAME
            }.let { name -> relativeDrillingSetRepository.findAll().first { it.name == name } }
                    .let {
                        mutableListOf<ElementDrillingSetComposition>().apply {
                            (1..numberOfShelves).forEach { shelfIndex ->
                                add(
                                        ElementDrillingSetComposition(
                                                xOffset = Offset(value = 20F),
                                                yOffset = Offset(percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)),
                                                drillingSet = it,
                                                element = this@createDrillingSetCompositionsForShelves
                                        )
                                )
                                add(
                                        ElementDrillingSetComposition(
                                                xOffset = Offset(value = 20F),
                                                yOffset = Offset(
                                                        value = 32F,
                                                        percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)
                                                ),
                                                drillingSet = it,
                                                element = this@createDrillingSetCompositionsForShelves
                                        )
                                )
                                add(
                                        ElementDrillingSetComposition(
                                                xOffset = Offset(value = 20F),
                                                yOffset = Offset(
                                                        value = 32F,
                                                        direction = Offset.Direction.BACKWARD,
                                                        percentageValue = shelfIndex.toFloat() / (1 + numberOfShelves)
                                                ),
                                                drillingSet = it,
                                                element = this@createDrillingSetCompositionsForShelves
                                        )
                                )
                            }
                        }
                    }

}