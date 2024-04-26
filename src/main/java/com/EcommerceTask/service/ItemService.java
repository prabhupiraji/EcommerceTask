package com.EcommerceTask.service;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.EcommerceTask.Repo.ItemRepository;
import com.EcommerceTask.exception.ItemNotFoundException;
import com.EcommerceTask.model.Item;

import ch.qos.logback.core.boolex.Matcher;

@Service
public class ItemService {
    @Autowired
	ItemRepository itemRepository;

	public Item saveItem(Item item) {
		String datePattern = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
	    Pattern pattern = Pattern.compile(datePattern);
	    java.util.regex.Matcher matcher = pattern.matcher(datePattern);
	    // Get the current date
	    LocalDate currentDate = LocalDate.now();
	    Date dopDate = java.sql.Date.valueOf(currentDate);

	     if (item == null) {
	        throw new ItemNotFoundException("Given item is null");
	     } 
	        
	     if (item.getDop() == null) {
	        
	            item.setDop(dopDate);
//	     
	        }
	     else if(!pattern.matcher(item.getDop().toString()).matches()) {
	    	    item.setDop(dopDate);
	     }
	
	        return itemRepository.save(item); 
	    }
//	private static boolean isValidDate(String dop) {
//		boolean validDate=true;
////		DateTimeFormatter dateFormatter=DateTimeFormat.forPattren("yyyy-mm-dd");
//		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		
//		Date ndop=dateFormatter.parse(dop, null)
//	}

		
	public Item getItem(String itemname) {
		Item findByitemname = itemRepository.findByitemname(itemname);
		if(findByitemname!=null) {
			return findByitemname;
		}
		else throw new ItemNotFoundException("item name not found");
	}

	

	public long deleteItem(String category) {
		List<Item> ls=itemRepository.findBycategory(category);
        if( ls.isEmpty()) {
        	  for(int i=0;i<ls.size();i++) {
    			  itemRepository.delete(ls.get(i));
    		  }
    		return ls.size();
        }
        else
		  throw new ItemNotFoundException("item name not found");
	}

	public Item updateItem1(Item item) {
		 java.util.Optional<Item> existingItem = itemRepository.findById(item.getItemid());
	        existingItem.get().setPrice(item.getPrice());
	        existingItem.get().setDop(item.getDop());
	        Item savedItem = itemRepository.save(existingItem.get());
	        
		return savedItem;
	}

	

	
}
