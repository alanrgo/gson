package com.mc636.lb03;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class TestPeek {
	Gson gson = new Gson();
	Reader reader;
	JsonReader jreader;
	
	
	/* Test invalid token */
	@Test
	public void CT1() throws Exception {
		String json = "$'name': 'Jose Emanuel', 'scenes': [1, 2, 3, 4]}";
		json = json.replace('\'', '\"');
		try {
			reader = new StringReader(json);
			jreader = new JsonReader(reader);
			jreader.setLenient(true);
			jreader.beginObject();
			fail();
		}
		catch(RuntimeException expected) {}
	}
	
	/* Test open brackets  */
	@Test
	public void CT2() throws IOException {
		String json = "{'name': 'Jose Emanuel', 'scenes': [1, 2, 3, 4]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		assertEquals(jreader.peek(), JsonToken.BEGIN_OBJECT);
	}
	
	/* Test close brackets */
	@Test
	public void CT3() throws IOException {
		String json = "{'name': 'Jose Emanuel'}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.nextString();
		assertEquals(jreader.peek(), JsonToken.END_OBJECT);
	}
	
	/* Test open squared brackets */
	@Test
	public void CT4() throws IOException {
		String json = "{'aa': [1, 2, 3]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		assertEquals(jreader.peek(), JsonToken.BEGIN_ARRAY);
	}
	
	/* Test close squared brackets */
	@Test
	public void CT5() throws IOException {
		String json = "{'aa': [1, 2, 3]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.beginArray();
		jreader.nextInt();
		jreader.nextInt();
		jreader.nextInt();
		assertEquals(jreader.peek(), JsonToken.END_ARRAY);
	}
	
	/* Test name of property */
	@Test
	public void CT6() throws IOException {
		String json = "{'name': 'Jose Emanuel', 'scenes': [1, 2, 3, 4]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		assertEquals(jreader.peek(), JsonToken.NAME);
	}
	
	/* Test integer */
	@Test
	public void CT7() throws IOException {
		String json = "{'aa': [1, 2, 3]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.beginArray();
		assertEquals(jreader.peek(), JsonToken.NUMBER);
	}
	
	/* Test float number */
	@Test
	public void CT8() throws IOException {
		String json = "{'aa': [1, 2, 3.145]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.beginArray();
		jreader.nextInt();
		jreader.nextInt();
		assertEquals(jreader.peek(), JsonToken.NUMBER);
	}
	
	/* Test scientific number  */
	@Test
	public void CT9() throws IOException {
		String json = "{'aa': [1, 2, 3.145e10]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.beginArray();
		jreader.nextInt();
		jreader.nextInt();
		assertEquals(jreader.peek(), JsonToken.NUMBER);
	}
	
	/* Test string */
	@Test 
	public void CT10() throws IOException {
		String json = "{'name': 'Jose Emanuel', 'scenes': [1, 2, 3, 4]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		
		assertEquals(jreader.peek(), JsonToken.STRING);
	}
	
	/* Test null */
	@Test
	public void CT11() throws IOException {
		String json = "{'name': null, 'scenes': [1, 2, 3, 4]}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		assertEquals(jreader.peek(), JsonToken.NULL);
	}
	
	/* Test end of Json */ 
	@Test 
	public void CT12() throws IOException {
		String json = "{'name': 'Jose Emanuel', 'scenes': 0}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.nextString();
		jreader.nextName();
		jreader.nextInt();

		assertEquals(jreader.peek(), JsonToken.END_OBJECT);
	}
	
	/* Test boolean */
	@Test 
	public void CT13() throws IOException {
		String json = "{'name': 'Jose Emanuel', 'scenes': true}";
		json = json.replace('\'', '\"');
		
		reader = new StringReader(json);
		jreader = new JsonReader(reader);
		jreader.beginObject();
		jreader.nextName();
		jreader.nextString();
		jreader.nextName();

		assertEquals(jreader.peek(), JsonToken.BOOLEAN);
	}

}
