package org.viktor_company.library_managment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriBuilder


@Configuration
class Config {
    val BASE_URL = "localhost:8080/api/v1"

    @Bean
    fun uriFactoryWithBaseUrl(): UriBuilder {
        val factory = DefaultUriBuilderFactory(BASE_URL)
        return factory.builder()
    }
}
