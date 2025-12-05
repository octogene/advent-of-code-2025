import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.LocalDateTime

plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_24
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

tasks.register("createANewDay") {
    val day = LocalDateTime.now().dayOfMonth.toString().padStart(2, '0')
    val template = """
        fun main() {
            fun part1(input: List<String>): Int {
                return 0
            }

            fun part2(input: List<String>): Int {
                return 0
            }

            // Or read a large test input from the `src/Day${day}_test.txt` file:
            val testInput = readInput("Day${day}_test")
            println(part1(testInput))
            check(part1(testInput) == -1)
            // println(part2(testInput))
            // check(part2(testInput) == -1)

            // Read the input from the `src/Day$day.txt` file.
            val input = readInput("Day$day")
            part1(input).println()
            part2(input).println()
        }
    """.trimIndent()
    val srcDir = layout.projectDirectory.dir("src")
    srcDir.files(
        "Day$day.kt",
        "Day${day}_test.txt",
        "Day$day.txt"
    ).files.forEach { file ->
        if (!file.exists()) {
            val created = file.createNewFile()
            if (created && file.name == "Day$day.kt") {
                file.writeText(template)
            } else {
                logger.quiet("Seems $file was not created")
            }
        }
    }
}