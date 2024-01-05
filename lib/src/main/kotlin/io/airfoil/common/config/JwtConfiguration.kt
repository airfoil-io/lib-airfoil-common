package io.airfoil.common.config

import io.airfoil.common.extension.boolValueOrDefault
import io.airfoil.common.extension.stringValueOrError
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.ApplicationConfigurationException
import kotlin.time.Duration

class JwtConfiguration {
    lateinit var publicKey: String
    lateinit var privateKey: String
    lateinit var issuer: String
    lateinit var audience: String
    var expiration: Duration = Duration.parse(DEFAULT_EXPIRATION)
    var neverExpires: Boolean = DEFAULT_NEVER_EXPIRES

    companion object {
        const val DEFAULT_EXPIRATION: String = "1h"
        const val DEFAULT_NEVER_EXPIRES: Boolean = false

        fun load(
            config: ApplicationConfig,
            configKey: String,
        ): JwtConfiguration = config.config(configKey).let { cfg ->
            JwtConfiguration().also {
                it.publicKey = cfg.stringValueOrError("publicKey", "Property $configKey.publicKey not found")
                it.privateKey = cfg.stringValueOrError("privateKey", "Property $configKey.privateKey not found")
                it.issuer = cfg.stringValueOrError("issuer", "Property $configKey.issuer not found")
                it.audience = cfg.stringValueOrError("audience", "Property $configKey.audience not found")
                it.expiration = cfg.stringValueOrError(
                    "expiration",
                    "Property $configKey.expiration not found",
                ).let { Duration.parse(it) }
                it.neverExpires = cfg.boolValueOrDefault("neverExpires", DEFAULT_NEVER_EXPIRES)
            }
        }

        fun loadOrNull(
            config: ApplicationConfig,
            configKey: String,
        ): JwtConfiguration? = try {
            load(config, configKey)
        } catch (ex: ApplicationConfigurationException) {
            null
        }
    }
}
