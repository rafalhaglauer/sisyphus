package com.wardrobes.sisyphus.model

const val PANEL_HANGING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa wisząca | wieniec"
const val BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | wieniec"
const val SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | listwa wspierająca"
const val SHELF_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | półka"
const val SHELF_HANGING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa wisząca | półka"

class DatabaseInitializer(
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository,
        private val relativeDrillingRepository: RelativeDrillingRepository
) {
    fun init() {
        createHangingWardrobeComposition()
        createStandingWardrobeComposition()
        createShelfDrillingComposition()
    }

    private fun createHangingWardrobeComposition() {
        val panelHangingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingSetLight(name = PANEL_HANGING_WARDROBE_COMPOSITION_NAME).toFull()
        )
        listOf(
                RelativeDrillingLight(
                        name = "Kołek - początek",
                        xOffset = OffsetLight(value = 40F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - początek",
                        xOffset = OffsetLight(value = 60F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - środek",
                        xOffset = OffsetLight(percentageValue = 0.5F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - koniec",
                        xOffset = OffsetLight(value = 60F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - koniec",
                        xOffset = OffsetLight(value = 40F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(panelHangingWardrobeComposition)) }
    }

    private fun createStandingWardrobeComposition() {
        val bottomPanelStandingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingSetLight(name = BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME).toFull()
        )
        listOf(
                RelativeDrillingLight(
                        name = "Konfirmat - początek",
                        xOffset = OffsetLight(value = 40F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - początek",
                        xOffset = OffsetLight(value = 60F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - środek",
                        xOffset = OffsetLight(percentageValue = 0.5F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - koniec",
                        xOffset = OffsetLight(value = 60F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - koniec",
                        xOffset = OffsetLight(value = 40F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(bottomPanelStandingWardrobeComposition)) }

        val supportingBarStandingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingSetLight(name = SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME).toFull()
        )
        listOf(
                RelativeDrillingLight(
                        name = "Kołek - początek",
                        xOffset = OffsetLight(25F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = supportingBarStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - środek",
                        xOffset = OffsetLight(percentageValue = 0.5F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = supportingBarStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - koniec",
                        xOffset = OffsetLight(value = 25F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = supportingBarStandingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(supportingBarStandingWardrobeComposition)) }
    }

    private fun createShelfDrillingComposition() {
        val shelfStandingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingSetLight(name = SHELF_STANDING_WARDROBE_COMPOSITION_NAME).toFull()
        )
        listOf(
                RelativeDrillingLight(
                        name = "Podpórka - początek",
                        xOffset = OffsetLight(70F),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingCompositionId = shelfStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Podpórka - koniec",
                        xOffset = OffsetLight(60F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingCompositionId = shelfStandingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(shelfStandingWardrobeComposition)) }

        val shelfHangingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingSetLight(name = SHELF_HANGING_WARDROBE_COMPOSITION_NAME).toFull()
        )
        listOf(
                RelativeDrillingLight(
                        name = "Podpórka - początek",
                        xOffset = OffsetLight(50F),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingCompositionId = shelfStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Podpórka - koniec",
                        xOffset = OffsetLight(40F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingCompositionId = shelfStandingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(shelfHangingWardrobeComposition)) }

    }
}