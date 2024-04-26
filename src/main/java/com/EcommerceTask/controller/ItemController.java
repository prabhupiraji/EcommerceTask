package com.EcommerceTask.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.apache.el.stream.Optional;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EcommerceTask.Repo.ItemRepository;
import com.EcommerceTask.exception.ExceptionResponse;
import com.EcommerceTask.exception.ItemNotFoundException;
import com.EcommerceTask.model.Item;
import com.EcommerceTask.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
     @Autowired
	 ItemService  itemService;
     @Autowired
     ItemRepository itemRepository;
     @Autowired
     ExceptionResponse exceptionResponse;
     
     @PostMapping("/add")
     public ResponseEntity<Item> saveItem(@RequestBody Item item ){
    	 return new ResponseEntity<Item>(itemService.saveItem(item),HttpStatus.OK);
     }
     
     @GetMapping("/findbyname/{itemname}")
     public ResponseEntity<?> getItem(@PathVariable String itemname){
    	 try  {
        	 Date dopDate = java.sql.Date.valueOf(LocalDate.now());
        	 exceptionResponse.setErrorMessage("this itemname is not found  please check");
        	 exceptionResponse.setRequestedURL("http://localhost:9086/item/findbyname/{itemname}");
        	 exceptionResponse.setTimeStamp(dopDate.toString());
    		Item item = itemService.getItem(itemname);
    		return ResponseEntity.ok(item);
		     } 
    	 catch (Exception e) {
    		 return  ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    	    }
		}
    	
  
     
     @PutMapping("/update")
     public ResponseEntity<?> updateItem(@RequestBody Item item ){
    	 java.util.Optional<Item> existingItem = itemRepository.findById(item.getItemid());
    	 Date dopDate = java.sql.Date.valueOf(LocalDate.now());
    	 exceptionResponse.setErrorMessage("this id  is not found in db please add item bofre updating ");
    	 exceptionResponse.setRequestedURL("http://localhost:9086/item/update");
    	 exceptionResponse.setTimeStamp(dopDate.toString());
         if (existingItem.isPresent()) {
    		 return new ResponseEntity<Item>(itemService.updateItem1(item),HttpStatus.OK);
    	 }
    	 else
    		 return  ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
     }

     
     
     @DeleteMapping("/delete/{category}")
     public ResponseEntity<?> deleteItem(@PathVariable String category){
    	 Date dopDate = java.sql.Date.valueOf(LocalDate.now());
    	 exceptionResponse.setErrorMessage("this category is not found ");
    	 exceptionResponse.setRequestedURL("http://localhost:9086/item/delete");
    	 exceptionResponse.setTimeStamp(dopDate.toString());
    	  try {
    		  long deleteItemcount = itemService.deleteItem(category);
    	    	 if(deleteItemcount>=1) {
    	    	  	 return  ResponseEntity.ok("total"+ deleteItemcount +"records deleted succesfully"); 
    	    	 }
    	    	 return  ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    	    	 }
    	  
    	  catch (ItemNotFoundException e) {
  			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      	    }
    	
     }
}
