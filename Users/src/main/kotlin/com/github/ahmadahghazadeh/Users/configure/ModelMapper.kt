package com.github.ahmadahghazadeh.users.configure

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ModelMapper {

    @Bean
    fun getModelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        return modelMapper
    }


}