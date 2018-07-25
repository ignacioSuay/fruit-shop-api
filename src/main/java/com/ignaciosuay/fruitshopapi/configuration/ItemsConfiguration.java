package com.ignaciosuay.fruitshopapi.configuration;

import com.ignaciosuay.fruitshopapi.model.Item;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * This Configuration class loads the list of items in the application.yml into items
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.data.config")
@Data
public class ItemsConfiguration {

    private List<Item> items;
}
