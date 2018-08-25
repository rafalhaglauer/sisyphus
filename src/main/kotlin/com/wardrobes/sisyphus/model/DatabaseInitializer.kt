package com.wardrobes.sisyphus.model

const val PANEL_HANHING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa wisząca | wieniec"
const val BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | wieniec"
const val SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME = "Standardowa szafa dolna | listwa wspierająca"

class DatabaseInitializer(
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository,
        private val relativeDrillingRepository: RelativeDrillingRepository
) {
    fun init() {
        createHangingWardrobeComposition()
        createStandingWardrobeComposition()
    }

    private fun createHangingWardrobeComposition() {
        val panelHangingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingCompositionLight(
                        name = PANEL_HANHING_WARDROBE_COMPOSITION_NAME,
                        suggestXReferenceValue = "Wieniec - szerokość",
                        suggestYReferenceValue = "Wieniec - wysokość")
                        .toFull(RelativeDrillingComposition.CreationType.GENERATED)
        )
        listOf(
                RelativeDrillingLight(
                        name = "Kołek - początek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 40F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - początek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 60F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - środek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - koniec",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 60F, reference = Offset.Reference.END), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - koniec",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 40F, reference = Offset.Reference.END), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = panelHangingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(panelHangingWardrobeComposition)) }
    }

    private fun createStandingWardrobeComposition() {
        val bottomPanelStandingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingCompositionLight(
                        name = BOTTOM_PANEL_STANDING_WARDROBE_COMPOSITION_NAME,
                        suggestXReferenceValue = "Wieniec - szerokość",
                        suggestYReferenceValue = "Wieniec - wysokość")
                        .toFull(RelativeDrillingComposition.CreationType.GENERATED)
        )
        listOf(
                RelativeDrillingLight(
                        name = "Konfirmat - początek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 40F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - początek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 60F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - środek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - koniec",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 60F, reference = Offset.Reference.END), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - koniec",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 40F, reference = Offset.Reference.END), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = bottomPanelStandingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(bottomPanelStandingWardrobeComposition)) }

        val supportingBarStandingWardrobeComposition = relativeDrillingCompositionRepository.save(
                RelativeDrillingCompositionLight(
                        name = SUPPORTING_BAR_STANDING_WARDROBE_COMPOSITION_NAME,
                        suggestXReferenceValue = "Listwa wspierająca - szerokość",
                        suggestYReferenceValue = "Listwa wspierająca - wysokość")
                        .toFull(RelativeDrillingComposition.CreationType.GENERATED)
        )
        listOf(
                RelativeDrillingLight(
                        name = "Kołek - początek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 25F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = supportingBarStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Konfirmat - środek",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = supportingBarStandingWardrobeComposition.id
                ),
                RelativeDrillingLight(
                        name = "Kołek - koniec",
                        xOffset = CompositeOffsetLight(offset = OffsetLight(value = 25F, reference = Offset.Reference.END), percentageOffset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN)),
                        yOffset = CompositeOffsetLight(offset = OffsetLight(value = 0F, reference = Offset.Reference.BEGIN), percentageOffset = OffsetLight(value = 0.5F, reference = Offset.Reference.BEGIN)),
                        diameter = 8F,
                        depth = 12F,
                        relativeDrillingCompositionId = supportingBarStandingWardrobeComposition.id
                )
        ).forEach { relativeDrillingRepository.save(it.toFull(supportingBarStandingWardrobeComposition)) }
    }
}