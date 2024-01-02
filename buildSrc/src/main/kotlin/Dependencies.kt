object Dependencies {
    object Apache {
        object Commons {
            val Text = DependencySpec("org.apache.commons:commons-text", Versions.Apache.Commons.Text)
            val Validator = DependencySpec("commons-validator:commons-validator", Versions.Apache.Commons.Validator)
        }
    }
    object Auth0 {
        val JavaJwt = DependencySpec("com.auth0:java-jwt", Versions.Auth0.JavaJwt)
    }
    object Exposed {
        val Core = DependencySpec("org.jetbrains.exposed:exposed-core", Versions.Exposed)
        val Dao = DependencySpec("org.jetbrains.exposed:exposed-dao", Versions.Exposed)
    }
    object Google {
        val LibPhoneNumber = DependencySpec("com.googlecode.libphonenumber:libphonenumber", Versions.Google.LibPhoneNumber)
    }
    val Jbcrypt = DependencySpec("org.mindrot:jbcrypt", Versions.Jbcrypt)
    object Kotest {
        val AssertionsCore = DependencySpec("io.kotest:kotest-assertions-core", Versions.Kotest.Core)
        val FrameworkDataset = DependencySpec("io.kotest:kotest-framework-datatest", Versions.Kotest.Core)
        val FrameworkEngine = DependencySpec("io.kotest:kotest-framework-engine", Versions.Kotest.Core)
        val Property = DependencySpec("io.kotest:kotest-property", Versions.Kotest.Core)
        val RunnerJunit5 = DependencySpec("io.kotest:kotest-runner-junit5", Versions.Kotest.Core)
    }
    object Kotlinx {
        val Datetime = DependencySpec("org.jetbrains.kotlinx:kotlinx-datetime", Versions.Kotlinx.Datetime)
    }
    object Ktor {
        object KotlinxSerialization {
            val Json = DependencySpec("io.ktor:ktor-serialization-kotlinx-json-jvm", Versions.Ktor)
        }
        object Server {
            val Auth = DependencySpec("io.ktor:ktor-server-auth-jvm", Versions.Ktor)
            object Config {
                val Yaml = DependencySpec("io.ktor:ktor-server-config-yaml-jvm", Versions.Ktor)
            }
            val Core = DependencySpec("io.ktor:ktor-server-core-jvm", Versions.Ktor)
        }
    }
    val Postgres = DependencySpec("org.postgresql:postgresql", Versions.Postgres)
}
