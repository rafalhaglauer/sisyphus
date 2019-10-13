package com.wardrobes.sisyphus

import com.wardrobes.sisyphus.domain.pattern.LengthPattern
import com.wardrobes.sisyphus.model.Wardrobe
import org.junit.Test

class LengthPatternTests {

    private val wardrobe: Wardrobe = Wardrobe(
        width = 600,
        height = 720,
        depth = 480
    )

    @Test
    fun shouldProperlyCalculateBottomPanelLength() {
        LengthPattern(pattern = "W-(2*18)-1").calculate(wardrobe).assert(563)
    }

    @Test
    fun shouldProperlyCalculateBottomPanelWidth() {
        LengthPattern(pattern = "D").calculate(wardrobe).assert(480)
    }

    @Test
    fun shouldProperlyCalculateSideBarLength() {
        LengthPattern(pattern = "H").calculate(wardrobe).assert(720)
    }

    @Test
    fun shouldProperlyCalculateSideBarWidth() {
        LengthPattern(pattern = "D").calculate(wardrobe).assert(480)
    }

    private fun Number.assert(value: Int) {
        assert(this == value)
    }

}