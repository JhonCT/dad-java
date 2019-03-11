package dad.dad.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dad.dad.model.Post;
import dad.dad.repository.DadRepository;

@CrossOrigin(origins = "*")
@RestController
public class DadController {
	
	@Autowired
	private DadRepository dadRepository;
	
	@GetMapping("/post")
	public JsonNode getPost() throws Exception {

		String prettyStaff1 = "";
		
		JsonNode actualObj = null;

		StringBuilder result = new StringBuilder();
		URL url = new URL("https://jsonplaceholder.typicode.com/posts");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String output = null;

		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();		

		ObjectMapper mapper = new ObjectMapper();
						
		actualObj = mapper.readTree(result.toString());
				
		System.out.println(actualObj);
		
		for (int i = 0; i < actualObj.size(); i++) {
			Post post = new Post();		
			post.setId(Integer.parseInt(String.valueOf(actualObj.get(i).get("id"))));
			post.setTitle(String.valueOf(actualObj.get(i).get("title")));
			post.setBody(String.valueOf(actualObj.get(i).get("body")));
			post.setUserId(Integer.parseInt(String.valueOf(actualObj.get(i).get("userId"))));			
			dadRepository.save(post);
		}

		return actualObj;
	}
}
