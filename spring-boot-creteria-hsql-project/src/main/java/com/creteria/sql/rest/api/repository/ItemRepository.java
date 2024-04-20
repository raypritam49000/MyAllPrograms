package com.creteria.sql.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.creteria.sql.rest.api.entity.Item;

@Repository
public interface ItemRepository
		extends CustomItemRepository, JpaRepository<Item, String>, JpaSpecificationExecutor<Item> {
}
