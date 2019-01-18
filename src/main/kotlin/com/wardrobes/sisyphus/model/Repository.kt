package com.wardrobes.sisyphus.model

import org.springframework.data.repository.CrudRepository

interface WardrobeRepository : CrudRepository<Wardrobe, Long>

interface AttachmentRepository : CrudRepository<Attachment, Long>

interface ElementRepository : CrudRepository<Element, Long>

interface RelativeDrillingRepository : CrudRepository<RelativeDrilling, Long>

interface RelativeDrillingSetRepository : CrudRepository<RelativeDrillingSet, Long>

interface ElementDrillingSetCompositionRepository : CrudRepository<ElementDrillingSetComposition, Long>