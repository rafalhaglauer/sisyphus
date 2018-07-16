package com.wardrobes.sisyphus.api.wardrobe

import com.wardrobes.sisyphus.model.wardrobe.SimpleWardrobe
import com.wardrobes.sisyphus.model.wardrobe.WardrobeDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wardrobe")
class WardrobeController(val repository: WardrobeRepository) {

    @GetMapping
    fun all() = repository.findAll()

//    @PostMapping
//    fun add(@RequestBody wardrobe: WardrobeDetails) = repository.save(wardrobe)

    @PostMapping
    fun create(@RequestBody wardrobe: SimpleWardrobe) = repository.save(WardrobeDetails.newInstance(wardrobe))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody wardrobe: WardrobeDetails) {
        assert(wardrobe.id == id)
        repository.save(wardrobe)
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long) = repository.delete(repository.findById(id).get())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = repository.findById(id)
}