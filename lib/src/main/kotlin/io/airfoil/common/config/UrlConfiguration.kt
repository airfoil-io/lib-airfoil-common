package io.airfoil.common.config

import io.airfoil.common.extension.intValueOrDefault
import io.airfoil.common.extension.stringValueOrError
import io.airfoil.common.extension.stringValueOrNull
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.ApplicationConfigurationException
import java.net.URI

class UrlConfiguration {
    lateinit var prefix: String
    lateinit var hostname: String
    var port: Int = DEFAULT_PORT
    var path: String? = null

    companion object {
        const val DEFAULT_PORT = 80

        fun load(
            config: ApplicationConfig,
            configKey: String,
        ): UrlConfiguration = config.config(configKey).let { cfg ->
            UrlConfiguration().also {
                it.prefix = cfg.stringValueOrError("prefix", "Property $configKey.prefix not found")
                it.hostname = cfg.stringValueOrError("hostname", "Property $configKey.hostname not found")
                it.port = cfg.intValueOrDefault("port", DEFAULT_PORT)
                it.path = cfg.stringValueOrNull("path")
            }
        }

        fun loadOrNull(
            config: ApplicationConfig,
            configKey: String,
        ): UrlConfiguration? = try {
            load(config, configKey)
        } catch (ex: ApplicationConfigurationException) {
            null
        }
    }

    override fun toString(): String = buildString {
        append("$prefix://$hostname:$port")
        if (path != null) {
            append("/$path")
        }
    }

    fun toURI(): URI = URI.create(this.toString())
}
