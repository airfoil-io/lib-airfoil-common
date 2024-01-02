package io.airfoil.common.config

import io.airfoil.common.extension.configOrNull
import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import io.ktor.server.config.*
import io.ktor.server.config.yaml.YamlConfigLoader
import java.io.File
import java.net.URI

private val APPLICATION_CONFIG: ApplicationConfig = YamlConfigLoader().load(
    File(ClassLoader.getSystemClassLoader()
        .getResource("application-test.yaml")
        .toURI()
    ).toString()
)!!

private val TEST_GROUP_2: ApplicationConfig = APPLICATION_CONFIG.configOrNull("testGroup2")!!

private val TEST_URL_CONFIG = UrlConfiguration().also {
    it.prefix = "https"
    it.hostname = "airfoil.io"
    it.port = 8080
    it.path = "chord-line"
}

private val TEST_URL_WITHOUT_PORT_CONFIG = UrlConfiguration().also {
    it.prefix = "https"
    it.hostname = "airfoil.io"
    it.path = "chord-line"
}

private val TEST_URL_WITHOUT_PATH_CONFIG = UrlConfiguration().also {
    it.prefix = "https"
    it.hostname = "airfoil.io"
    it.port = 8080
}

private val TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG = UrlConfiguration().also {
    it.prefix = "https"
    it.hostname = "airfoil.io"
}

class UrlConfigurationSpec : FunSpec({

    context("load") {
        test("SUCCESS - loads valid full URL config") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrl").also {
                it.prefix shouldBe TEST_URL_CONFIG.prefix
                it.hostname shouldBe TEST_URL_CONFIG.hostname
                it.port shouldBe TEST_URL_CONFIG.port
                it.path shouldBe TEST_URL_CONFIG.path
            }
        }

        test("SUCCESS - loads valid URL config without port") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPort").also {
                it.prefix shouldBe TEST_URL_WITHOUT_PORT_CONFIG.prefix
                it.hostname shouldBe TEST_URL_WITHOUT_PORT_CONFIG.hostname
                it.port shouldBe TEST_URL_WITHOUT_PORT_CONFIG.port
                it.path shouldBe TEST_URL_WITHOUT_PORT_CONFIG.path
            }
        }

        test("SUCCESS - loads valid URL config without path") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPath").also {
                it.prefix shouldBe TEST_URL_WITHOUT_PATH_CONFIG.prefix
                it.hostname shouldBe TEST_URL_WITHOUT_PATH_CONFIG.hostname
                it.port shouldBe TEST_URL_WITHOUT_PATH_CONFIG.port
                it.path shouldBe TEST_URL_WITHOUT_PATH_CONFIG.path
            }
        }

        test("SUCCESS - loads valid URL config without port or path") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPortOrPath").also {
                it.prefix shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.prefix
                it.hostname shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.hostname
                it.port shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.port
                it.path shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.path
            }
        }

        test("FAILURE - throws exception for invalid URL config") {
            shouldThrow<ApplicationConfigurationException> {
                UrlConfiguration.load(TEST_GROUP_2, "invalidUrlConfig")
            }
        }
    }

    context("loadOrNull") {
        test("SUCCESS - loads valid full URL config") {
            UrlConfiguration.loadOrNull(TEST_GROUP_2, "testUrl")!!.also {
                it.prefix shouldBe TEST_URL_CONFIG.prefix
                it.hostname shouldBe TEST_URL_CONFIG.hostname
                it.port shouldBe TEST_URL_CONFIG.port
                it.path shouldBe TEST_URL_CONFIG.path
            }
        }

        test("SUCCESS - loads valid URL config without port") {
            UrlConfiguration.loadOrNull(TEST_GROUP_2, "testUrlWithoutPort")!!.also {
                it.prefix shouldBe TEST_URL_WITHOUT_PORT_CONFIG.prefix
                it.hostname shouldBe TEST_URL_WITHOUT_PORT_CONFIG.hostname
                it.port shouldBe TEST_URL_WITHOUT_PORT_CONFIG.port
                it.path shouldBe TEST_URL_WITHOUT_PORT_CONFIG.path
            }
        }

        test("SUCCESS - loads valid URL config without path") {
            UrlConfiguration.loadOrNull(TEST_GROUP_2, "testUrlWithoutPath")!!.also {
                it.prefix shouldBe TEST_URL_WITHOUT_PATH_CONFIG.prefix
                it.hostname shouldBe TEST_URL_WITHOUT_PATH_CONFIG.hostname
                it.port shouldBe TEST_URL_WITHOUT_PATH_CONFIG.port
                it.path shouldBe TEST_URL_WITHOUT_PATH_CONFIG.path
            }
        }

        test("SUCCESS - loads valid URL config without port or path") {
            UrlConfiguration.loadOrNull(TEST_GROUP_2, "testUrlWithoutPortOrPath")!!.also {
                it.prefix shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.prefix
                it.hostname shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.hostname
                it.port shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.port
                it.path shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.path
            }
        }

        test("FAILURE - returns null for invalid URL config") {
            UrlConfiguration.loadOrNull(TEST_GROUP_2, "invalidUrlConfig") shouldBe null
        }
    }

    context("toString") {
        test("SUCCESS - full URL") {
            val urlConfiguration = UrlConfiguration.load(TEST_GROUP_2, "testUrl")
            urlConfiguration.toString() shouldBe TEST_URL_CONFIG.toString()
        }

        test("SUCCESS - URL without port") {
            val urlConfiguration = UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPort")
            urlConfiguration.toString() shouldBe TEST_URL_WITHOUT_PORT_CONFIG.toString()
        }

        test("SUCCESS - URL without path") {
            val urlConfiguration = UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPath")
            urlConfiguration.toString() shouldBe TEST_URL_WITHOUT_PATH_CONFIG.toString()
        }

        test("SUCCESS - URL without port or path") {
            val urlConfiguration = UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPortOrPath")
            urlConfiguration.toString() shouldBe TEST_URL_WITHOUT_PORT_OR_PATH_CONFIG.toString()
        }
    }

    context("toURI") {
        test("SUCCESS - loads valid full URL config") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrl").also {
                it.toURI() shouldBe URI.create(it.toString())
            }
        }

        test("SUCCESS - loads valid URL config without port") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPort").also {
                it.toURI() shouldBe URI.create(it.toString())
            }
        }

        test("SUCCESS - loads valid URL config without path") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPath").also {
                it.toURI() shouldBe URI.create(it.toString())
            }
        }

        test("SUCCESS - loads valid URL config without port or path") {
            UrlConfiguration.load(TEST_GROUP_2, "testUrlWithoutPortOrPath").also {
                it.toURI() shouldBe URI.create(it.toString())
            }
        }
    }

})
