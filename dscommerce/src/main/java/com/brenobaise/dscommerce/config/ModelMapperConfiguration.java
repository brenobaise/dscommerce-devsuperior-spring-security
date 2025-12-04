package com.brenobaise.dscommerce.config;

import com.brenobaise.dscommerce.dtos.ProductDTO;
import com.brenobaise.dscommerce.entities.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Tells modelmapper to not copy the ID of the classes.
        modelMapper.typeMap(ProductDTO.class, Product.class)
                .addMappings(m -> m.skip(Product::setId));

        return modelMapper;
    }
}
