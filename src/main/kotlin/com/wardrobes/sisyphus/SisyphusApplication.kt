package com.wardrobes.sisyphus

import com.wardrobes.sisyphus.model.DatabaseInitializer
import com.wardrobes.sisyphus.model.RelativeDrillingCompositionRepository
import com.wardrobes.sisyphus.model.RelativeDrillingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class SisyphusApplication

fun main(args: Array<String>) {
    runApplication<SisyphusApplication>(*args)
}


@Component
class DataLoader @Autowired constructor(
        private val relativeDrillingRepository: RelativeDrillingRepository,
        private val relativeDrillingCompositionRepository: RelativeDrillingCompositionRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        DatabaseInitializer(relativeDrillingCompositionRepository, relativeDrillingRepository).init()
    }
}