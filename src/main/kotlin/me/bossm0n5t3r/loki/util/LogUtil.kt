package me.bossm0n5t3r.loki.util

import java.util.UUID

object LogUtil {
    fun generateTransactionId() = UUID.randomUUID().toString().replace("-", "")
}
