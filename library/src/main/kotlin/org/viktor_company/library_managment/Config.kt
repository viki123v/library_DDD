package org.viktor_company.library_managment

import org.axonframework.common.jpa.EntityManagerProvider
import org.axonframework.eventhandling.EventBus
import org.axonframework.modelling.command.GenericJpaRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriBuilder
import org.viktor_company.library_managment.domain.lib_branches.LibBranch
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID


@Configuration
@EnableJpaRepositories
class Config {
    val BASE_URL = "localhost:8080/api/v1"

    @Bean
    fun uriFactoryWithBaseUrl(): UriBuilder {
        val factory = DefaultUriBuilderFactory(BASE_URL)
        return factory.builder()
    }
}

@Configuration
class AggregatesRepoConfig {
    @Bean(name = ["axonLibBranchRepo"])
    fun libBranch(
        provider: EntityManagerProvider,
        eventBus: EventBus,
    ): GenericJpaRepository<LibBranch> {
        return GenericJpaRepository
            .builder(LibBranch::class.java)
            .identifierConverter { id ->
                val parts = id.split("-")
                val cityName = parts[0]
                val branchName = parts[1]
                LibBranchID(cityName, branchName)
            }
            .entityManagerProvider(provider)
            .eventBus(eventBus)
            .build()
    }
}
