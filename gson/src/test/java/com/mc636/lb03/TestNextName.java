package com.mc636.lb03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import com.google.gson.stream.JsonReader;

public class TestNextName {
	
	/* Double quoted */
	@Test
	public void CT1() throws IOException {
		String json = "{ \"name\":\"John\", \"age\":31, \"city\":\"New York\" }";
		StringReader reader = new StringReader(json);
		JsonReader jsonR = new JsonReader(reader);
		jsonR.beginObject();
		assertEquals(jsonR.nextName(), "name");
	}
	
	/* single quoted */
	@Test
	public void CT2() throws IOException {
		String json = "{ 'name':'John', 'age':31, 'city':'New York' }";
		StringReader reader = new StringReader(json);
		JsonReader jsonR = new JsonReader(reader);
		jsonR.setLenient(true);
		jsonR.beginObject();
		assertEquals(jsonR.nextName(), "name");
	}
	
	/* no quoted */
	@Test
	public void CT3() throws IOException {
		String json = "{ name:'John', age:31, city:'New York' }";
		StringReader reader = new StringReader(json);
		JsonReader jsonR = new JsonReader(reader);
		jsonR.setLenient(true);
		jsonR.beginObject();
		assertEquals(jsonR.nextName(), "name");
	}
	
	/* throw exception when trying to get a property as a name */
	@Test
	public void CT4() throws IOException {
		String json = "{ \"name\":\"John\", \"age\":31, \"city\":\"New York\" }";
		try {
			StringReader reader = new StringReader(json);
			JsonReader jsonR = new JsonReader(reader);
			jsonR.beginObject();
			jsonR.nextName();
			jsonR.nextName();
			fail(); /* Se chegar ateh aqui eh pq falhou */
		}
		catch(IllegalStateException expected) {}
	}

}
