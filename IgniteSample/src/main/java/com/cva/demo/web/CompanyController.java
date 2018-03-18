package com.cva.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cva.demo.services.CompanyService;

/**
 * Serving the Comapny crud operations (Used Ignite 3rd party persistence)
 * 
 * @author
 *
 */
@RestController
public class CompanyController {

	@Autowired
	CompanyService cService;

	@RequestMapping(value = "/schema", method = RequestMethod.GET)

	public ResponseEntity<String> getCompanyName(@RequestParam String cacheName, @RequestParam String key) {

		try {

			String value = cService.getcompanyName(cacheName, key);
			return new ResponseEntity<>(value, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
