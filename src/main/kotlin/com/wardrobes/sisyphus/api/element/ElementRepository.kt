package com.wardrobes.sisyphus.api.element

import com.wardrobes.sisyphus.model.wardrobe.Element
import org.springframework.data.repository.CrudRepository

interface ElementRepository : CrudRepository<Element, Long>