package io.airfoil.common.extension

import io.airfoil.common.types.TestEnum
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

class EnumByNameIgnoreCaseSpec : FunSpec({

    context("enumByNameIgnoreCase") {
        test("SUCCESS - enum from valid case sensitive string") {
            "ENUM_VALUE_1".enumByNameIgnoreCase<TestEnum>() shouldBe TestEnum.ENUM_VALUE_1
            "enum_value_2".enumByNameIgnoreCase<TestEnum>() shouldBe TestEnum.enum_value_2
            "Enum_value_3".enumByNameIgnoreCase<TestEnum>() shouldBe TestEnum.Enum_value_3
        }

        test("SUCCESS - enum from valid case insensitive string") {
            "enum_value_1".enumByNameIgnoreCase<TestEnum>() shouldBe TestEnum.ENUM_VALUE_1
            "ENUM_VALUE_2".enumByNameIgnoreCase<TestEnum>() shouldBe TestEnum.enum_value_2
            "eNUM_VALUE_3".enumByNameIgnoreCase<TestEnum>() shouldBe TestEnum.Enum_value_3
        }

        test("FAILURE - enum from invalid string") {
            "not_valid_enum".enumByNameIgnoreCase<TestEnum>() shouldBe null
        }
    }

})
