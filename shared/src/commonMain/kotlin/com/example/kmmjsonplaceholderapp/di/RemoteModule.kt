package com.example.kmmjsonplaceholderapp.di

import com.example.kmmjsonplaceholderapp.core.data.logging.provideHttpLogger
import com.example.kmmjsonplaceholderapp.core.data.remote.KtorApi
import com.example.kmmjsonplaceholderapp.core.data.remote.KtorApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val remoteModule = module {
    single {
        HttpClient {

            install(Logging){
                level = LogLevel.HEADERS
                logger = provideHttpLogger()
            }

            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(DefaultRequest){
                url {
                    protocol = URLProtocol.HTTPS
                    host = "jsonplaceholder.typicode.com"
                }
                headers {
                    append(HttpHeaders.ContentType,ContentType.Application.Json)
                }
            }

            install(HttpRequestRetry){
                maxRetries = 3
                exponentialDelay(maxDelayMs = 16000)
            }
        }
    }

    single<KtorApi> {
        KtorApiImpl(get())
    }
}