package com.mc636.lb03;

import static org.junit.Assert.*;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import org.junit.Test;

public class TestFromJason {

	Gson gson = new Gson();
	
	/* test Json to simple Java Object */
	@Test
	public void CT1() {
		String json = "{'name': 'Jose Emanuel', 'age': 25, 'isParent': true, 'height': 15.52}";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			assertEquals(usr.name, "Jose Emanuel");
			assertEquals(usr.age, 25);
			assertTrue(usr.isParent);
			assertEquals(usr.height, 15.52, 0.01f);
			
		}
		catch(JsonSyntaxException expected) {
			fail();
		}
	}
	
	/* test no-property Json into simple Java Object */
	@Test
	public void CT9() {
		String json = "{}";
		json = json.replace('\'', '\"');
		User usr = gson.fromJson(json, User.class);
		assertEquals(usr.name, null);
		assertEquals(usr.age, 0);
	}
	
	/* test missing-brackets Json into simple Java Object */
	@Test
	public void CT12() {
		String json = "{";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			fail();
		}
		catch (RuntimeException expected) {
			
		}

	}
	
	/* test no-property Json into simple Java Object */
	@Test
	public void CT10() {
		String json = "{'carro': True}";
		json = json.replace('\'', '\"');
		User usr = gson.fromJson(json, User.class);
		assertEquals(usr.name, null);
		assertEquals(usr.age, 0);
	}
	
	/* test missing-comma Json into simple Java Object */
	@Test
	public void CT11() {
		String json = "{'name': 'Jose Emanuel' 'age': 26, 'isParent': false}";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			fail();
		}
		catch(RuntimeException expected) {}
	}
	
	/* test Json to simple Java Object with missing property */
	@Test
	public void CT2() {
		String json = "{'name': 'Jose Emanuel', 'isParent': false}";
		json = json.replace('\'', '\"');
		User usr = gson.fromJson(json, User.class);
		assertEquals(usr.name, "Jose Emanuel");
		assertFalse(usr.isParent);
		assertEquals(usr.age, 0);
	}
	
	/* test Json with wrong syntax to simple Java Object */
	@Test
	public void CT3()  throws  IOException {
		String json = "{'name' 'Jose Emanuel', 'isParent': false}";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			assertEquals(usr.name, "Jose Emanuel");
			assertFalse(usr.isParent);
			assertEquals(usr.age, 0);
			fail();
		}
		catch(JsonSyntaxException expected) {}
	}
	
	/* test Json with wrong syntax to simple Java Object */
	@Test
	public void CT8()  throws  IOException {
		String json = "'name': 'Jose Emanuel', 'isParent': false}";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			assertEquals(usr.name, "Jose Emanuel");
			assertFalse(usr.isParent);
			assertEquals(usr.age, 0);
			fail();
		}
		catch(JsonSyntaxException expected) {}
	}
	
	/* test Json with missing double quote to simple Java Object */
	@Test
	public void CT13()  throws  IOException {
		String json = "{'name: 'Jose Emanuel, 'isParent': false}";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			fail();
		}
		catch(JsonSyntaxException expected) {}
	}
	
	
	/* test Json with missing attib value to simple Java Object */
	@Test
	public void CT14()  throws  IOException {
		String json = "{'name': 'Jose Emanuel', 'isParent': }";
		json = json.replace('\'', '\"');
		try {
			User usr = gson.fromJson(json, User.class);
			fail();
		}
		catch(JsonSyntaxException expected) {}
	}

	public void CT15() {
		String json = "{'intnumber': 5468268, 'number': 15454.36}";
		json = json.replace('\'', '\"');
		Numbers n = gson.fromJson(json, Numbers.class);
		assertEquals(n.intnumber, 5468268);
		assertEquals(n.number, 15454.36, 0.001f);
	}
	
	public void CT16() {
		String json = "{'intnumber': 5468268, 'number': 1.2e3}";
		json = json.replace('\'', '\"');
		Numbers n = gson.fromJson(json, Numbers.class);
		assertEquals(n.intnumber, 5468268);
		assertEquals(n.number, 1200, 0.001f);
	}
	
	public void CT18() {
		String json = "{'name': 'Jose Emanuel', 'isParent': 7}";
		json = json.replace('\'', '\"');
		try {
			gson.fromJson(json, Numbers.class);
			fail();
		}
		catch(RuntimeException expected) {}

	}
	
	public void CT17() {
		String json = "{'intnumber': 5468268, 'number': --1.2e3}";
		json = json.replace('\'', '\"');
		try {
			gson.fromJson(json, Numbers.class);
			fail();
		}
		catch(RuntimeException expected) {}

	}
	
	
	/* Test arrays: valid */
	@Test
	public void CT4() {
		String json = "[1, 2, 3, 4, 5, 6]";
		
		int [] array = gson.fromJson(json, int[].class);
		assertEquals(array[0], 1);
		assertEquals(array[1], 2);
		assertEquals(array[2], 3);
		assertEquals(array[3], 4);
		assertEquals(array[4], 5);
		assertEquals(array[5], 6);
	}
	
	/* Test arrays: wrong type */
	@Test
	public void CT5() {
		String json = "[a, 2, 3, 4, 5, 6]";
		
		try {
		int [] array = gson.fromJson(json, int[].class);
			assertEquals(array[0], 1);
			assertEquals(array[1], 2);
			assertEquals(array[2], 3);
			assertEquals(array[3], 4);
			assertEquals(array[4], 5);
			assertEquals(array[5], 6);
			fail();
		}
		catch( RuntimeException expected) {
			assertEquals("java.lang.NumberFormatException: For input string: 'a'".replace('\'', '\"'), expected.getMessage());
		}
	}
	
	
	/* Test arrays: missing comma */
	@Test
	public void CT6() {
		String json = "[1, 2, 3 4, 5, 6]";
		
		try {
		int [] array = gson.fromJson(json, int[].class);
			assertEquals(array[0], 1);
			assertEquals(array[1], 2);
			assertEquals(array[2], 3);
			assertEquals(array[3], 4);
			assertEquals(array[4], 5);
			assertEquals(array[5], 6);
			fail();
		}
		catch( RuntimeException expected) {
			
		}
	}
	
	/* Test arrays: missing square bracket */
	@Test
	public void CT7() {
		String json = "[1, 2, 3, 4, 5, 6";
		
		try {
		int [] array = gson.fromJson(json, int[].class);
			assertEquals(array[0], 1);
			assertEquals(array[1], 2);
			assertEquals(array[2], 3);
			assertEquals(array[3], 4);
			assertEquals(array[4], 5);
			assertEquals(array[5], 6);
			fail();
		}
		catch( RuntimeException expected) {
			
		}
	}
	
}
