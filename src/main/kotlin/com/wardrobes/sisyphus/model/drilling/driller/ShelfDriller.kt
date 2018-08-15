//package com.wardrobes.sisyphus.model.drilling.driller
//
//import com.wardrobes.sisyphus.model.drilling.set.ShelfDrillingSet
//import com.wardrobes.sisyphus.model.drilling.set.transform
//import com.wardrobes.sisyphus.model.wardrobe.DrillingDetails
//import com.wardrobes.sisyphus.model.wardrobe.ElementDetails
//
//object ShelfDriller {
//    private val drillingSet = ShelfDrillingSet
//
//    fun createDrillings(element: ElementDetails, numberOfShelves: Int): List<DrillingDetails> {
//        val drillings = mutableListOf<DrillingDetails>()
//        (1..numberOfShelves).forEach {
//            drillingSet.getDrillingGroup()
//                    .transform(yOffset = (element.length / numberOfShelves) * it)
//                    .map { it.toDrilling(element) }
//                    .also { drillings.addAll(it) }
//        }
//        return drillings
//    }
//}
