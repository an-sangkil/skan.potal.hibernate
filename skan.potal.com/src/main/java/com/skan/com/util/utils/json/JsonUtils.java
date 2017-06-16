package com.skan.com.util.utils.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {
	
	public static <T> String convertJson(T t) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String strJson = mapper.writeValueAsString(t);
		
		return strJson;
	}
	

	public static void main(String[] args) throws IOException {
		// 테스트 데이터 : 맵에 string 2개랑 list 하나가 들어가 있는 형태
		List<String> list = new ArrayList<String>();
		list.add("list1");
		list.add("list2");
		list.add("list3");

		Map<String, Object> d = new HashMap<String, Object>();
		d.put("list", list);
		d.put("a", "va");
		d.put("b", "vb");

		// Create the node factory that gives us nodes.
		JsonNodeFactory factory = new JsonNodeFactory(false);

		// create a json factory to write the treenode as json. for the example
		// we just write to console
		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator generator = jsonFactory.createGenerator(System.out);
		ObjectMapper mapper = new ObjectMapper();

		String strJson = mapper.writeValueAsString(d);
		System.out.println(strJson);

		// the root node - album
		JsonNode album = factory.objectNode();
		((ObjectNode) album).put("Album-Title", "Kind Of Blue");

		mapper.writeTree(generator, album);
	}
}
