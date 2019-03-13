package com.wardrobes.sisyphus.model

const val PANEL_HANGING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa wisząca | wieniec"
const val BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | wieniec"
const val SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | listwa wspierająca"
const val SHELF_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | półka"
const val SHELF_HANGING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa wisząca | półka"

class DatabaseInitializer(
        private val relativeDrillingSetRepository: RelativeDrillingSetRepository,
        private val relativeDrillingRepository: RelativeDrillingRepository
) {
    fun init() {
        createHangingWardrobeComposition()
        createStandingWardrobeComposition()
        createShelfDrillingComposition()
    }

    private fun createHangingWardrobeComposition() {
        val panelHangingWardrobeComposition = relativeDrillingSetRepository.save(RelativeDrillingSet(name = PANEL_HANGING_WARDROBE_COMPOSITION_NAME))
        listOf(
                RelativeDrilling(
                        name = "Kołek - początek",
                        xOffset = Offset(value = 40F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = panelHangingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Konfirmat - początek",
                        xOffset = Offset(value = 60F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = panelHangingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Kołek - środek",
                        xOffset = Offset(percentageValue = 0.5F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = panelHangingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Konfirmat - koniec",
                        xOffset = Offset(value = 60F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = panelHangingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Kołek - koniec",
                        xOffset = Offset(value = 40F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = panelHangingWardrobeComposition
                )
        ).forEach { relativeDrillingRepository.save(it) }
    }

    private fun createStandingWardrobeComposition() {
        val bottomPanelStandingWardrobeComposition = relativeDrillingSetRepository.save(
                RelativeDrillingSet(name = BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME)
        )
        listOf(
                RelativeDrilling(
                        name = "Konfirmat - początek",
                        xOffset = Offset(value = 40F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = bottomPanelStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Kołek - początek",
                        xOffset = Offset(value = 60F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = bottomPanelStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Konfirmat - środek",
                        xOffset = Offset(percentageValue = 0.5F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = bottomPanelStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Kołek - koniec",
                        xOffset = Offset(value = 60F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = bottomPanelStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Konfirmat - koniec",
                        xOffset = Offset(value = 40F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = bottomPanelStandingWardrobeComposition
                )
        ).forEach { relativeDrillingRepository.save(it) }

        val supportingBarStandingWardrobeComposition = relativeDrillingSetRepository.save(
                RelativeDrillingSet(name = SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME)
        )
        listOf(
                RelativeDrilling(
                        name = "Kołek - początek",
                        xOffset = Offset(value = 25F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = supportingBarStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Konfirmat - środek",
                        xOffset = Offset(percentageValue = 0.5F),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = supportingBarStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Kołek - koniec",
                        xOffset = Offset(value = 25F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingSet = supportingBarStandingWardrobeComposition
                )
        ).forEach { relativeDrillingRepository.save(it) }
    }

    private fun createShelfDrillingComposition() {
        val shelfStandingWardrobeComposition = relativeDrillingSetRepository.save(
                RelativeDrillingSet(name = SHELF_STANDING_WARDROBE_COMPOSITION_NAME)
        )
        listOf(
                RelativeDrilling(
                        name = "Podpórka - początek",
                        xOffset = Offset(value = 70F),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingSet = shelfStandingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Podpórka - koniec",
                        xOffset = Offset(value = 60F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingSet = shelfStandingWardrobeComposition
                )
        ).forEach { relativeDrillingRepository.save(it) }

        val shelfHangingWardrobeComposition = relativeDrillingSetRepository.save(
                RelativeDrillingSet(name = SHELF_HANGING_WARDROBE_COMPOSITION_NAME)
        )
        listOf(
                RelativeDrilling(
                        name = "Podpórka - początek",
                        xOffset = Offset(value = 50F),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingSet = shelfHangingWardrobeComposition
                ),
                RelativeDrilling(
                        name = "Podpórka - koniec",
                        xOffset = Offset(value = 40F, percentageValue = 1F, direction = Offset.Direction.BACKWARD),
                        diameter = 5F,
                        depth = 12F,
                        relativeDrillingSet = shelfHangingWardrobeComposition
                )
        ).forEach { relativeDrillingRepository.save(it) }

    }
}