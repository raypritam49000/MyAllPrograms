package com.creteria.sql.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.creteria.sql.rest.api.repository.ItemRepository;

@SpringBootApplication
public class SpringBootCreteriaHsqlProjectApplication implements ApplicationRunner {
	
	@Autowired
	private ItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCreteriaHsqlProjectApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//      System.out.println(itemRepository.getItems());
//      System.out.println(itemRepository.findItemByPrice(1000));
//      System.out.println(itemRepository.getItemsBetweenPrice(100, 1000));
//      System.out.println(itemRepository.getItemsInNames(List.of("Skate Board", "Paint", "Glue")));
//      System.out.println(itemRepository.checkPropertyThenfindAllList("itemDescription"));
//      System.out.println(itemRepository.findAllListByMultipleExpression());
//      System.out.println(itemRepository.findAllLogicalAnd());
//      System.out.println(itemRepository.findAllLogicalOr());
//      System.out.println(itemRepository.findAllBySotring());
//      System.out.println(itemRepository.findAllItemCounts());
//      System.out.println(itemRepository.findAllItemAVG());
     // System.out.println(itemRepository.updateItem());
   //   System.out.println(itemRepository.deleteItem());
     // System.out.println(itemRepository.findItems());
		System.out.println(itemRepository.findAllItemsGreaterThanItemPrice());
		System.out.println(itemRepository.findAllItemsBetweenItemPrice());
	}

}
