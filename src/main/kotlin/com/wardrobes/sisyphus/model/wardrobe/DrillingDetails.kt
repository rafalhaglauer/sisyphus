//package com.wardrobes.sisyphus.model.wardrobe
//
//import javax.persistence.*
//
//data class Drilling(
//        var xPosition: Float = 0F,
//        var yPosition: Float = 0F,
//        var depth: Float = 0F,
//        var diameter: Float = 0F
//) {
//
//        fun toDetails() = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = depth, diameter = diameter)
//}
//
//@Entity
//data class DrillingDetails(
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//        var id: Long = 0,
//        var xPosition: Float = 0F,
//        var yPosition: Float = 0F,
//        var depth: Float = 0F,
//        var diameter: Float = 0F) {
//
//        companion object {
//
////                fun dowel(xPosition: Float, yPosition: Float) = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = 10F, diameter = 8F)
////
////                fun stealDowel(xPosition: Float, yPosition: Float) = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = 8F, diameter = 5F)
////
////                fun stealDowelConnector(xPosition: Float, yPosition: Float) = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = 8F, diameter = 10F)
////
////                fun support(xPosition: Float, yPosition: Float) = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = 8F, diameter = 5F)
////
////                fun confirmatScrew(xPosition: Float, yPosition: Float, elementHeight: Float = 18F) = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = elementHeight, diameter = 5F)
//        }
//
//        fun copy() = DrillingDetails(xPosition = xPosition, yPosition = yPosition, depth = depth, diameter = diameter)
//}