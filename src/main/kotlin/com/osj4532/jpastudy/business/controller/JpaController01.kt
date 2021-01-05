package com.osj4532.jpastudy.business.controller

import com.osj4532.jpastudy.business.service.JpaPractice01
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/prac01")
class JpaController01(
        val service01: JpaPractice01
) {
    @GetMapping
    fun practice01() {
        service01.jpaPractice_01()
    }
}