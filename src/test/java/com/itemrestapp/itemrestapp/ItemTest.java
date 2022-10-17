package com.itemrestapp.itemrestapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Test;
import com.dao.ItemDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Item;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
@SpringBootTest
public class ItemTest {
	@Autowired
	ItemDao dao;
	Item item =new Item();
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception{
		
	}
	@BeforeEach
	void setUp() throws Exception{
		
	}
	@AfterEach
	void tearDown() throws Exception{
		
	}
	@Test
	void TestAddItem() {//add
		//Item item =new Item();
		item.setItemName("Jeans");
		item.setPrice(670);
		item.setQuantity(6);
		dao.save(item);
		Item item1=dao.findById(item.getItemId()).get();
		Assertions.assertEquals(item.getItemName(),item1.getItemName());
	}
	@Test
	void TestUpdateItem() {//update
		item.setItemName("Kurt");
		item.setPrice(630);
		item.setQuantity(2);
		dao.save(item);
		Item item1=dao.findById(item.getItemId()).get();
		Assertions.assertEquals(item.getItemName(),item1.getItemName());
	}
	@Test
	void TestFindById() {//findById
		item.setItemName("Kurt");
		item.setPrice(630);
		item.setQuantity(2);
		dao.save(item);
		Item item1=dao.findById(item.getItemId()).get();
		Assertions.assertEquals(item.getItemId(),item1.getItemId());
	}
	@Test
	void TestDeleteItem() {//delete
		dao.delete(item);
		Assertions.assertEquals(0,item.getItemId());
//		try {
//		System.out.println(item.getItemId());
//		//Assertions.assertEquals(item.getItemName(),item1.getItemName());
//		}catch(java.util.NoSuchElementException e) {
//			Assertions.assertEquals(e.getMessage(),dao.findById(item.getItemId()).get());
//		}
	}
	@Test
    void testFindByIdRest() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8080/findbyid/1";
      URI uri=new URI(url);
      ResponseEntity<String> res=template.getForEntity(uri,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
      
  }
	Item i=new Item(2,"Banana",678,4);
	@Test
    void testAddRest() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8080/additem";
      URI uri=new URI(url);
      HttpHeaders headers = new HttpHeaders();      
      HttpEntity<Item> request = new HttpEntity<>(i, headers);
      ResponseEntity<String> res=template.postForEntity(uri,request,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
      
  }
//	@Test
//    void testUpdateRest() throws URISyntaxException, JsonProcessingException {
//      RestTemplate template=new RestTemplate();
//      final String url="http://localhost:8080/updateitems";
//      i=new Item(2,"PineApple",128,6);
//      URI uri=new URI(url);
//      HttpHeaders headers = new HttpHeaders();      
//      HttpEntity<Item> request = new HttpEntity<>(i, headers);
//      ResponseEntity<String> res=template.postForEntity(uri,request,String.class);
//      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
//      
//  }
	@Test
    void testgetItemsRest() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8080/getitems";
      URI uri=new URI(url);
      ResponseEntity<String> res=template.getForEntity(uri,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
      
  }

}
