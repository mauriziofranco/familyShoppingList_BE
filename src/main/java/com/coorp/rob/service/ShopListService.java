package com.coorp.rob.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coorp.rob.model.ShopList;
import com.coorp.rob.repository.ShopListRepository;


/**
 * @author Roberto
 * 
 * */
@Service
public class ShopListService {
	
	private static Logger log = LoggerFactory.getLogger(ShopListService.class);

	@Autowired
	private ShopListRepository shopList;
	
	/**
	 * 
	 * 
	 * */
	public List<ShopList> getShoppingList() {
		 log.info("getShoppingList - START"); 
		
		 List<ShopList> list = this.shopList.findAll();
		 log.info("getShoppingList - END - list.size(): " + list.size());
		 return list;
	}
	
	/**
	 * 
	 * 
	 * */
	public Optional<ShopList> getShopListById(String id) {
		 log.info("method getShopListById(String id) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t    1:" +  "    ("+ id.getClass().getSimpleName() +")"+ id +
				  			  "\n\t}\n\n"); 
		
		 Optional<ShopList> shopListRetrieved = this.shopList.findById(id);
		 if(shopListRetrieved.isPresent()) {
			 log.info("method getShopListById(String id) - RETURN\n\n" +
					 "\t {\n" + 
						"\t   shopList:    " + shopListRetrieved.get() +
					 "\n\t}\n\n"); 
		 }
		 else {
			 log.info("method getShopListById(String id) - RETURN\n\n" +
					 "\t {\n" + 
						"\t    shopList:    " + shopListRetrieved +
					 "\n\t}\n\n"); 		 }
	
		 log.info("method getShopListById(String id) - END");
		 return shopListRetrieved;
	}
	
	/**
	 * 
	 * 
	 * */
	public boolean saveShopList(ShopList shopList) {
		 log.info("method saveShopList(ShopList shopList) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:    " + shopList.toString() +
				  			  "\n\t}\n\n"); 
		
		 boolean saved = true;
		 ShopList sl = null;
		 
		 try {
			 sl = this.shopList.save(shopList);
			 log.error("method saveShopList(ShopList shopList) - DEBUG\n\n" + 
					  "\t item-saved:{\n" + 
	  						"\t  shopListId:    " + sl.getId() +
	  						"\t  shopListNumberItems:    " + sl.getItemList().size() +
	  						"\t  shopListItems:    " + sl.getItemList() +
					  "\n\t}\n\n"); 
		 }catch(IllegalArgumentException ex) {
			 saved = false;
			 log.error("method saveShopList(ShopList shopList) - ERROR\n\n" + 
					  "\t {\n" + 
	  						"\t  error-message:    " + ex.getMessage() +
	  						"\t  error-cause    :" + ex.getCause() +
	  						"\t  error-stackTrace:    " + ex.getStackTrace() +
					  "\n\t}\n\n"); 
			 ex.printStackTrace();
		 }
		 log.info("methodsaveShopList(ShopList shopList) - RETURN\n\n" +
				 "\t {\n" + 
					"\t  saved:    " + saved +
				 "\n\t}\n\n"); 
		 log.info("method saveShopList(ShopList shopList) - END");
		 return saved;
	}
}
