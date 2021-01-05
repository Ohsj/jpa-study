package com.osj4532.jpastudy

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.measureTimeMillis

@SpringBootApplication
class JpaStudyApplication

fun main(args: Array<String>) {
    val elapsed = measureTimeMillis {
        runApplication<JpaStudyApplication>(*args) {
            setBannerMode(Banner.Mode.OFF)
        }
    }
}
