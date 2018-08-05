package com.wardrobes.sisyphus.api.wardrobe

import com.wardrobes.sisyphus.model.wardrobe.Wardrobe
import com.wardrobes.sisyphus.model.wardrobe.WardrobeDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wardrobe")
class WardrobeController(val repository: WardrobeRepository) {

    @GetMapping
    fun getAll(@RequestParam("creationType") creationType: Wardrobe.CreationType) = repository.findAll().filter { it.creationType == creationType }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody wardrobe: Wardrobe, @RequestParam("creationType") creationType: Wardrobe.CreationType) =
            when (creationType) {
                Wardrobe.CreationType.STANDARD -> repository.save(WardrobeDetails.standardWardrobe(wardrobe))
                Wardrobe.CreationType.CUSTOM -> repository.save(WardrobeDetails.customWardrobe(wardrobe))
                Wardrobe.CreationType.UNDEFINED -> repository.save(WardrobeDetails.unknown())
            }.id

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody wardrobe: Wardrobe): Long {
        var wardrobeDetails = repository.findById(id).get()
        repository.deleteById(id)
        when (wardrobeDetails.creationType) {
            Wardrobe.CreationType.CUSTOM -> {
                wardrobeDetails.apply {
                    symbol = wardrobe.symbol
                    width = wardrobe.width
                    height = wardrobe.height
                    depth = wardrobe.depth
                    type = wardrobe.type
                }
            }
            Wardrobe.CreationType.STANDARD -> {
                wardrobeDetails = WardrobeDetails.standardWardrobe(wardrobe)
            }
        }

        return repository.save(wardrobeDetails).id
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): Boolean {
        repository.deleteById(id)
        return true
    }

    @PostMapping("/{id}/copy")
    fun copy(@PathVariable("id") wardrobeId: Long, @RequestParam("symbol") wardrobeSymbol: String) =
            repository.findById(wardrobeId).get().apply {
                id = 0
                symbol = wardrobeSymbol
                creationType = Wardrobe.CreationType.CUSTOM
            }.let { repository.save(it).id }
}