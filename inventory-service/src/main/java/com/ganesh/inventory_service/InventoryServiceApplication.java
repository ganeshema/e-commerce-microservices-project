package com.ganesh.inventory_service;

import com.ganesh.inventory_service.model.Inventory;
import com.ganesh.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository productRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("I phone 11");
			inventory.setQuantity(4);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("I phone 12");
			inventory2.setQuantity(3);

			productRepository.save(inventory);
			productRepository.save(inventory2);
		};
	}

}
