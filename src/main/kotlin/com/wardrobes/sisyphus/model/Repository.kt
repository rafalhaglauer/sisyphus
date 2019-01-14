package com.wardrobes.sisyphus.model

import org.springframework.data.repository.CrudRepository

interface WardrobeRepository : CrudRepository<Wardrobe, Long>

interface ElementRepository : CrudRepository<Element, Long>

interface RelativeDrillingRepository : CrudRepository<RelativeDrilling, Long>

interface RelativeDrillingCompositionRepository : CrudRepository<RelativeDrillingSet, Long>

interface ReferenceElementRelativeDrillingCompositionRepository : CrudRepository<ElementDrillingSetComposition, Long>

interface DrillingRepository : CrudRepository<Drilling, Long>