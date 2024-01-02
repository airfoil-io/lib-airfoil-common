package io.airfoil.common.extension

import io.airfoil.common.usecase.UnrestrictedUseCase
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.requireParameter(param: String): T =
    this.parameters.get(param)?.let { value ->
        when (T::class) {
            String::class -> { value as T }
            Int::class -> { value.toInt() as T }
            Long::class -> { value.toLong() as T }
            else -> {
                T::class.constructors
                    .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<String>() }
                    ?.call(value) ?:
                T::class.constructors
                    .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<Int>() }
                    ?.call(value.toInt()) ?:
                T::class.constructors
                    .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<Long>() }
                    ?.call(value.toLong())
            }
        }
    } ?: throw BadRequestException("Missing or invalid $param param")

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.parameterOrNull(param: String): T? =
    try {
        this.requireParameter<T>(param)
    } catch (ex: BadRequestException) {
        null
    }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.parameterOrDefault(param: String, default: T): T =
    this.parameterOrNull<T>(param) ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.requireIdParameter(param: String): T =
    this.parameters.get(param)?.let { value ->
        T::class.constructors
            .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<UUID>() }
            ?.call(UUID.fromString(value))
    } ?: throw BadRequestException("Missing or invalid $param param")

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.idParameterOrNull(param: String): T? =
    try {
        this.requireIdParameter<T>(param)
    } catch (ex: BadRequestException) {
        null
    }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.idParameterOrDefault(param: String, default: T): T =
    this.idParameterOrNull<T>(param) ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> ApplicationCall.requireEnumParameter(param: String): T =
    this.parameters.get(param)?.let { value ->
        value.enumByNameIgnoreCase<T>() ?: throw BadRequestException("Invalid $param param")
    } ?: throw BadRequestException("Missing or invalid $param param")

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> ApplicationCall.enumParameterOrNull(param: String): T? =
    try {
        this.requireEnumParameter<T>(param)
    } catch (ex: BadRequestException) {
        null
    }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> ApplicationCall.enumParameterOrDefault(param: String, default: T): T =
    this.enumParameterOrNull<T>(param) ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.requireHeader(header: String): T =
    this.request.headers.get(header)?.let { value ->
        when (T::class) {
            String::class -> { value as T }
            Int::class -> { value.toInt() as T }
            Long::class -> { value.toLong() as T }
            else -> {
                T::class.constructors
                    .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<String>() }
                    ?.call(value) ?:
                T::class.constructors
                    .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<Int>() }
                    ?.call(value.toInt()) ?:
                T::class.constructors
                    .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<Long>() }
                    ?.call(value.toLong())
            }
        }
    } ?: throw BadRequestException("Missing or invalid $header header")

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.headerOrNull(header: String): T? =
    try {
        this.requireHeader<T>(header)
    } catch (ex: BadRequestException) {
        null
    }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.headerOrDefault(header: String, default: T): T =
    this.headerOrNull<T>(header) ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.requireHeaderId(header: String): T =
    this.request.headers.get(header)?.let { value ->
        T::class.constructors
            .find { it.parameters.size == 1 && it.parameters[0].type == typeOf<UUID>() }
            ?.call(UUID.fromString(value))
    } ?: throw BadRequestException("Missing or invalid $header header")

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.headerIdOrNull(header: String): T? =
    try {
        this.requireHeaderId<T>(header)
    } catch (ex: BadRequestException) {
        null
    }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApplicationCall.headerIdOrDefault(header: String, default: T): T =
    this.headerIdOrNull<T>(header) ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> ApplicationCall.requireEnumHeader(header: String): T =
    this.request.headers.get(header)?.let { value ->
        value.enumByNameIgnoreCase<T>() ?: throw BadRequestException("Invalid $header header")
    } ?: throw BadRequestException("Missing or invalid $header header")

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> ApplicationCall.enumHeaderOrNull(param: String): T? =
    try {
        this.requireEnumHeader<T>(param)
    } catch (ex: BadRequestException) {
        null
    }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> ApplicationCall.enumHeaderOrDefault(param: String, default: T): T =
    this.enumHeaderOrNull<T>(param) ?: default

suspend fun ApplicationCall.requireMultipartFile(name: String): ByteArray =
    this.receiveMultipart().let { multipartData ->
        val part: PartData? = multipartData.find { it.name == name }
        when (part) {
            is PartData.FileItem -> part.streamProvider().readBytes() 
            else -> throw BadRequestException("Missing or invalid multipart file $name")
        }
    }

suspend fun <Args, Res> ApplicationCall.execute(
    usecase: UnrestrictedUseCase<Args, Res>,
    args: Args,
    status: HttpStatusCode = HttpStatusCode.OK,
): Res =
    usecase.execute(args, this).also {
        response.status(status)
    }

suspend inline fun <Args, reified Res: Any> ApplicationCall.executeAndRespond(
    usecase: UnrestrictedUseCase<Args, Res>,
    args: Args,
): Res =
    execute(usecase, args).also { result ->
        respond(result)
    }

suspend inline fun <Args> ApplicationCall.executeAndRespondBytes(
    usecase: UnrestrictedUseCase<Args, ByteArray>,
    args: Args,
    contentType: ContentType? = null,
    status: HttpStatusCode? = null,
): ByteArray =
    execute(usecase, args).also { result ->
        respondBytes(
            contentType = contentType,
            status = status,
            provider = { result },
        )
    }
