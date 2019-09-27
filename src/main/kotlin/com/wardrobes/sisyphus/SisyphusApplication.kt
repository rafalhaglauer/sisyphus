package com.wardrobes.sisyphus

import com.wardrobes.sisyphus.model.DatabaseInitializer
import com.wardrobes.sisyphus.model.RelativeDrillingRepository
import com.wardrobes.sisyphus.model.RelativeDrillingSetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
open class SisyphusApplication

fun main(args: Array<String>) {
    runApplication<SisyphusApplication>(*args)
}

@Component
class DataLoader @Autowired constructor(
        private val relativeDrillingRepository: RelativeDrillingRepository,
        private val relativeDrillingSetRepository: RelativeDrillingSetRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        DatabaseInitializer(relativeDrillingSetRepository, relativeDrillingRepository).init()
    }
}