//package com.wardrobes.sisyphus.api.wardrobe
//
//import com.wardrobes.sisyphus.model.drilling.driller.HangingWardrobePanelDriller
//import com.wardrobes.sisyphus.model.drilling.driller.ShelfDriller
//import com.wardrobes.sisyphus.model.drilling.driller.StandingWardrobePanelDriller
//import com.wardrobes.sisyphus.model.wardrobe.*
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/wardrobe")
//class WardrobeController(val wardrobeRepository: WardrobeRepository) {
//
//    @GetMapping
//    fun getAll(@RequestParam("creationType") creationType: Wardrobe.CreationType? = null) = wardrobeRepository.findAll().filter { creationType == null || it.creationType == creationType }
//
//    @GetMapping("/{id}")
//    fun get(@PathVariable id: Long) = wardrobeRepository.findById(id)
//
//    @PostMapping
//    fun create(@RequestBody wardrobe: Wardrobe, @RequestParam("creationType") creationType: Wardrobe.CreationType): Long {
//        val wardrobeDetails = wardrobe.toDetails(creationType)
//        if (creationType == Wardrobe.CreationType.STANDARD) wardrobeDetails.appendElements()
//        return wardrobeRepository.save(wardrobeDetails).id
//    }
//
//    @PutMapping("/{id}")
//    fun update(@PathVariable id: Long, @RequestBody wardrobe: Wardrobe): Long {
//        val old = wardrobeRepository.findById(id).get()
//
//        if (old.creationType == Wardrobe.CreationType.CUSTOM) {
//            old.apply {
//                symbol = wardrobe.symbol
//                width = wardrobe.width
//                height = wardrobe.height
//                depth = wardrobe.depth
//                type = wardrobe.type
//            }
//            return wardrobeRepository.save(old).id
//        }
//
//        if (old.creationType == Wardrobe.CreationType.STANDARD) {
//            wardrobeRepository.deleteById(id)
//            val new = wardrobe.toDetails(Wardrobe.CreationType.STANDARD)
//            new.appendElements()
//            return wardrobeRepository.save(new).id
//        }
//        return 0
//    }
//
//    @DeleteMapping("/{id}")
//    fun remove(@PathVariable id: Long): Boolean {
//        wardrobeRepository.deleteById(id)
//        return true
//    }
//
//    @PostMapping("/copy")
//    fun copy(@RequestParam("id") wardrobeId: Long, @RequestParam("symbol") wardrobeSymbol: String): Long {
//        val oldWardrobe = wardrobeRepository.findById(wardrobeId).get()
//        val newWardrobe = oldWardrobe.copy(wardrobeSymbol)
//        oldWardrobe.elements.forEach { oldElement ->
//
//            val newElement = oldElement.copy()
//
//            oldElement.drillings.forEach {
//                newElement.addDrilling(it.copy())
//            }
//
//            newWardrobe.add(newElement)
//        }
//        return wardrobeRepository.save(newWardrobe).id
//    }
//}
//
//private fun WardrobeDetails.appendElements() {
//    val elements = when (type!!) {
//        Wardrobe.Type.HANGING -> HangingWardrobeFactory
//        Wardrobe.Type.STANDING -> StandingWardrobeFactory
//    }.createElements(this)
////    val topPanel = elements.first { it.name == "Wieniec górny" }
//
//
////            .forEach {
////        val elementDetails = it.toDetails()
//
////        if(it.name == "Bok lewy" || it.name == "Bok prawy") {
////        val drillings = mutableListOf<DrillingDetails>()
////        when (type) {
////            Wardrobe.Type.HANGING -> HangingWardrobePanelDriller.createDrillings(elementDetails).also { drillings.addAll(it) }
////            Wardrobe.Type.STANDING -> StandingWardrobePanelDriller.createDrillings(elementDetails).also { drillings.addAll(it) }
////        }
////        ShelfDriller.createDrillings(elementDetails, 3).also { drillings.addAll(it) }
////
////        drillings.forEach { elementDetails.addDrilling(it) }
////        }
//
////        add(elementDetails)
//    }
//}