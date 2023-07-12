package com.tovelop.maphant.controller

import com.tovelop.maphant.service.AlertLogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AlertLogController() {
    @GetMapping("/notifications")
    fun showNotifications(): Unit {
        val alertlogservie = AlertLogService()
        return alertlogservie.getalert()
    }
}