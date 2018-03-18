package com.cva.demo.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cva.demo.services.ItemService;

/**
 * This controller serving the crud operations for Items(used the Ignite Native
 * persistence)
 * 
 *
 */
@RestController
@RequestMapping("/service")
public class ItemController {
	@Autowired
	ItemService itemService;
	private static final Logger logger = Logger.getLogger(ItemController.class);

	@RequestMapping(value = "/items", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createItem(@RequestParam String cacheName, @RequestParam String key,
			@RequestBody String value) {
		try {

			itemService.createItemService(cacheName, key, value);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/items/query", method = RequestMethod.GET, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> queryItem(@RequestParam String cacheName, @RequestParam String query) {

		try {
			String resultSet = itemService.queryIgnite(cacheName, query);
			return new ResponseEntity<>(resultSet, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
