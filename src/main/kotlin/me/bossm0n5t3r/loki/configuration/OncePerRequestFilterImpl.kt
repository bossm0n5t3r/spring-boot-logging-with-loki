package me.bossm0n5t3r.loki.configuration

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bossm0n5t3r.loki.enumeration.MDCKey
import me.bossm0n5t3r.loki.util.LogUtil.generateTransactionId
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class OncePerRequestFilterImpl : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        MDC.put(MDCKey.TRANSACTION_ID.value, generateTransactionId())
        filterChain.doFilter(request, response)
        MDC.clear()
    }
}
