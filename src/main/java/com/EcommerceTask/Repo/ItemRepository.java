package com.EcommerceTask.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EcommerceTask.model.Item;
@Repository
public interface ItemRepository  extends JpaRepository<Item, Integer>{


	Item findByitemname(String itemname);

	List<Item> findBycategory(String category);



}
