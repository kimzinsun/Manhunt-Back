package com.tovelop.maphant.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/ping")
class UptimePing {
    @RequestMapping("/check")
    @ResponseBody
    fun ping(): String {
        return "pong"
    }

    @RequestMapping("/jenkins")
    @ResponseBody
    fun jenkins(): String {
        return "pong"
    }
}