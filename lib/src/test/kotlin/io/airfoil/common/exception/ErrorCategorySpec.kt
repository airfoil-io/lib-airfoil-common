package io.airfoil.common.exception

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

class ErrorCategorySpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty error category") {
            shouldThrow<IllegalArgumentException> {
                ErrorCategory("")
            }
        }
    }

})
