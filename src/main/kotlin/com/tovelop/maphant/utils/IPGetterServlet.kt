package com.tovelop.maphant.utils

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import kotlin.jvm.Throws

class IPGetterServlet: HttpServlet() {
    @Throws(IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val clientIP = request.remoteAddr
        response.writer.write("Client IP: $clientIP")
    }
}