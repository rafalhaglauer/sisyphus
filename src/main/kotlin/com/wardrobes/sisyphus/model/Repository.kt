package com.wardrobes.sisyphus.model

import com.wardrobes.sisyphus.domain.pattern.DrillingPattern
import com.wardrobes.sisyphus.domain.pattern.ElementPattern
import com.wardrobes.sisyphus.domain.pattern.WardrobePattern
import com.wardrobes.sisyphus.domain.wardrobe.Wardrobe
import org.springframework.data.repository.CrudRepository

interface WardrobeRepository : CrudRepository<Wardrobe, Long>

interface WardrobePatternRepository : CrudRepository<WardrobePattern, Long>

interface AttachmentRepository : CrudRepository<Attachment, Long>

interface ElementRepository : CrudRepository<Element, Long>

interface ElementPatternRepository : CrudRepository<ElementPattern, Long>

interface DrillingPatternRepository : CrudRepository<DrillingPattern, Long>

interface RelativeDrillingRepository : CrudRepository<RelativeDrilling, Long>

interface RelativeDrillingSetRepository : CrudRepository<RelativeDrillingSet, Long>

interface ElementDrillingSetCompositionRepository : CrudRepository<ElementDrillingSetComposition, Long>

