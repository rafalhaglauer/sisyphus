package com.wardrobes.sisyphus.api.wardrobe

import com.wardrobes.sisyphus.model.wardrobe.WardrobeDetails
import org.springframework.data.repository.CrudRepository

interface WardrobeRepository : CrudRepository<WardrobeDetails, Long>