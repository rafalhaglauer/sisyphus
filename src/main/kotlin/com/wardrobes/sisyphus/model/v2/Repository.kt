package com.wardrobes.sisyphus.model.v2

import org.springframework.data.repository.CrudRepository

interface ElementRepository : CrudRepository<Element, Long>

interface RelativeDrillingRepository : CrudRepository<RelativeDrilling, Long>

interface RelativeDrillingCompositionRepository : CrudRepository<RelativeDrillingComposition, Long>

interface ReferenceElementRelativeDrillingCompositionRepository : CrudRepository<ReferenceElementRelativeDrillingComposition, Long>

interface DrillingRepository : CrudRepository<Drilling, Long>

interface CompositeOffsetRepository : CrudRepository<CompositeOffset, Long>

interface OffsetRepository : CrudRepository<Offset, Long>