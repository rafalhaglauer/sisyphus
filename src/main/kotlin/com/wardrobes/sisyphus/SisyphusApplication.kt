package com.wardrobes.sisyphus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SisyphusApplication

fun main(args: Array<String>) {
    runApplication<SisyphusApplication>(*args)

//    val wardrobe = StandingWardrobeFactory.createWardrobe(
//            symbol = "DSG",
//            height = 720F,
//            width = 600F,
//            depth = 500F,
//            elementHeight = 18F
//    )
//    with(wardrobe) {
//        println("symbol: $symbol")
//        println("height: $height")
//        println("width: $width")
//        println("depth: $depth")
//        println()
//        println()
//        println("Elements:")
//        println()
//        elements.forEach {
//            println("ElementDetails:")
//            println("name: ${it.name}")
//            println("length: ${it.length}")
//            println("width: ${it.width}")
//            println("height: ${it.height}")
//            println()
//            println()
//            println("Drillings:")
//            println()
//            it.drillingDetails.forEach {
//                println("DrillingDetails")
//                println("xPosition: ${it.xPosition}")
//                println("yPosition: ${it.yPosition}")
//                println("depth: ${it.depth}")
//                println("diameter: ${it.diameter}")
//                println()
//            }
//        }
//        println()
//        println("----------------------------------------")
//        println()
//        println()
//    }
}
