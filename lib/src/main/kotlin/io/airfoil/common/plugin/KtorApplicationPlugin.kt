package io.airfoil.common.plugin

import io.ktor.http.content.OutgoingContent
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import io.ktor.server.config.*

interface KtorApplicationPlugin {
    fun onCall(call: ApplicationCall) {}
    fun onCallReceive(call: ApplicationCall) {}
    fun onCallRespond(call: ApplicationCall) {}
    fun onCallSetup(call: ApplicationCall) {}
    fun onCallFailed(call: ApplicationCall, throwable: Throwable) {}
    fun onResponseBodyReadyForSend(call: ApplicationCall, content: OutgoingContent) {}
    fun onResponseSent(call: ApplicationCall) {}
    fun onApplicationStarting(application: Application) {}
    fun onApplicationStarted(application: Application) {}
    fun onApplicationStopPreparing(env: ApplicationEnvironment) {}
    fun onApplicationStopping(application: Application) {}
    fun onApplicationStopped(application: Application) {}
}

fun <PluginConfigT : Any> createKtorApplicationPlugin(
    name: String, 
    configurationPath: String, 
    createConfiguration: (config: ApplicationConfig) -> PluginConfigT, 
    body: PluginBuilder<PluginConfigT>.() -> KtorApplicationPlugin?,
): ApplicationPlugin<PluginConfigT> =
    createApplicationPlugin(
        name = name,
        configurationPath = configurationPath,
        createConfiguration = createConfiguration,
    ) {
        body()?.also { plugin ->
            onCall { call -> plugin.onCall(call) }
            onCallReceive { call -> plugin.onCallReceive(call) }
            onCallRespond { call -> plugin.onCallRespond(call) }
            on(CallSetup) { call -> plugin.onCallSetup(call) }
            on(CallFailed) { call, throwable -> plugin.onCallFailed(call, throwable) }
            on(ResponseBodyReadyForSend) { call, content -> plugin.onResponseBodyReadyForSend(call, content) }
            on(ResponseSent) { call -> plugin.onResponseSent(call) }
            on(MonitoringEvent(ApplicationStarting)) { application -> plugin.onApplicationStarting(application) }
            on(MonitoringEvent(ApplicationStarted)) { application -> plugin.onApplicationStarted(application) }
            on(MonitoringEvent(ApplicationStopPreparing)) { env -> plugin.onApplicationStopPreparing(env) }
            on(MonitoringEvent(ApplicationStopping)) { application -> plugin.onApplicationStopping(application) }
            on(MonitoringEvent(ApplicationStopped)) { application -> plugin.onApplicationStopped(application) }
        }
    }

fun <PluginConfigT : Any> createKtorApplicationPlugin(
    name: String, 
    createConfiguration: () -> PluginConfigT, 
    body: PluginBuilder<PluginConfigT>.() -> KtorApplicationPlugin?,
): ApplicationPlugin<PluginConfigT> =
    createApplicationPlugin(
        name = name,
        createConfiguration = createConfiguration,
    ) {
        body()?.also { plugin ->
            onCall { call -> plugin.onCall(call) }
            onCallReceive { call -> plugin.onCallReceive(call) }
            onCallRespond { call -> plugin.onCallRespond(call) }
            on(CallSetup) { call -> plugin.onCallSetup(call) }
            on(CallFailed) { call, throwable -> plugin.onCallFailed(call, throwable) }
            on(ResponseBodyReadyForSend) { call, content -> plugin.onResponseBodyReadyForSend(call, content) }
            on(ResponseSent) { call -> plugin.onResponseSent(call) }
            on(MonitoringEvent(ApplicationStarting)) { application -> plugin.onApplicationStarting(application) }
            on(MonitoringEvent(ApplicationStarted)) { application -> plugin.onApplicationStarted(application) }
            on(MonitoringEvent(ApplicationStopPreparing)) { env -> plugin.onApplicationStopPreparing(env) }
            on(MonitoringEvent(ApplicationStopping)) { application -> plugin.onApplicationStopping(application) }
            on(MonitoringEvent(ApplicationStopped)) { application -> plugin.onApplicationStopped(application) }
        }
    }

fun createKtorApplicationPlugin(
    name: String,
    body: PluginBuilder<Unit>.() -> KtorApplicationPlugin?,
): ApplicationPlugin<Unit> =
    createApplicationPlugin(
        name = name,
    ) {
        body()?.also { plugin ->
            onCall { call -> plugin.onCall(call) }
            onCallReceive { call -> plugin.onCallReceive(call) }
            onCallRespond { call -> plugin.onCallRespond(call) }
            on(CallSetup) { call -> plugin.onCallSetup(call) }
            on(CallFailed) { call, throwable -> plugin.onCallFailed(call, throwable) }
            on(ResponseBodyReadyForSend) { call, content -> plugin.onResponseBodyReadyForSend(call, content) }
            on(ResponseSent) { call -> plugin.onResponseSent(call) }
            on(MonitoringEvent(ApplicationStarting)) { application -> plugin.onApplicationStarting(application) }
            on(MonitoringEvent(ApplicationStarted)) { application -> plugin.onApplicationStarted(application) }
            on(MonitoringEvent(ApplicationStopPreparing)) { env -> plugin.onApplicationStopPreparing(env) }
            on(MonitoringEvent(ApplicationStopping)) { application -> plugin.onApplicationStopping(application) }
            on(MonitoringEvent(ApplicationStopped)) { application -> plugin.onApplicationStopped(application) }
        }
    }
