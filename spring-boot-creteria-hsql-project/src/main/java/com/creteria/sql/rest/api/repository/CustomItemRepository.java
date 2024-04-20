package com.creteria.sql.rest.api.repository;

import java.util.List;

import com.creteria.sql.rest.api.entity.Item;

public interface CustomItemRepository {

	public List<Item> getItems();

	public Item findItemByPrice(Integer itemPrice);

	public List<Item> getItemsBetweenPrice(Integer startPrice, Integer endPrice);

	public List<Item> getItemsInNames(List<String> names);

	public List<Item> checkPropertyThenfindAllList(String propertyName);

	public List<Item> findAllListByMultipleExpression();

	public List<Item> findAllLogicalOr();

	public List<Item> findAllLogicalAnd();

	public List<Item> findAllBySotring();

	public List<Long> findAllItemCounts();

	public List<Double> findAllItemAVG();

	public String updateItem();

	public String deleteItem();

	public List<Item> findItems();

	public List<Item> findAllItemsGreaterThanItemPrice();
	
	public List<Item> findAllItemsBetweenItemPrice();

}
