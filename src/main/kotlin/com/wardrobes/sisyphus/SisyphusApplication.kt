package com.wardrobes.sisyphus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SisyphusApplication

fun main(args: Array<String>) {
    runApplication<SisyphusApplication>(*args)
}