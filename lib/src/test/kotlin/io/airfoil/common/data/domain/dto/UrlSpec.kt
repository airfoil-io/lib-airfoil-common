package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val VALID_URLS = listOf(
    "http://www.google.com",
    "http://www.google.com/",
    "http://www.google.com/file%20one%26two",
    "http://www.google.com/#file%20one%26two",
    "ftp://webmaster@www.google.com/",
    "ftp://john%20doe@www.google.com/",
    "http://www.google.com/?",
    "http://www.google.com/?foo=bar?",
    "http://www.google.com/?q=figtree+app",
    "http://www.google.com/?q=figtree%20app",
    "http://www.google.com/a%20b?q=c+d",
    "http://user:password@google.com",
    "http://www.google.com/?q=figtree+app#foo",
    "http://www.google.com/?q=figtree+app#foo&bar",
    "http://www.google.com/?q=figtree+app#foo%26bar",
    "hTTp://www.GooGle.COM",
    "http://192.168.0.1/",
    "http://192.168.0.1:8080/",
    "http://[fe80::1]/",
    "http://[fe80::1]:8080/",
    "http://rest.rsc.io/foo%2fbar/baz%2Fquux?alt=media",
)

private val INVALID_URLS = listOf(
    "http:google.com",
    "http:www.google.com/?q=figtree+app",
    "http:%2f%2fwww.google.com/?q=figtree+app",
    "mailto:/webmaster@figtr.ee",
    "mailto:webmaster@figtr.ee",
    "/foo?query=http://bad",
    "//foo",
    "//user@foo/path?a=b",
    "http://j@ne:password@google.com",
    "http://jane:p@ssword@google.com",
)

class UrlSpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty url") {
            shouldThrow<IllegalArgumentException> {
                Url("")
            }
        }

        test("SUCCESS - from valid string") {
            Url.invoke(VALID_URLS[0]) shouldBe Url(VALID_URLS[0])
        }

        test("FAILURE - from invalid string") {
            Url.invoke(INVALID_URLS[0]) shouldBe null
        }

        test("SUCCESS - valid urls") {
            VALID_URLS.forEach { validUrl ->
                val url = Url(validUrl)
                url.value shouldBe validUrl
            }
        }

        test("FAILURE - invalid urls") {
            INVALID_URLS.forEach { invalidUrl ->
                shouldThrow<IllegalArgumentException> {
                    Url(invalidUrl)
                }
            }
        }
    }

})
