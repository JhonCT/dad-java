package dad.dad.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
public class DadController {

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
			//output = line.replace("[", " ").replace("]", " ");
			result.append(line);
		}
		rd.close();
		

		 //JSONObject json = new JSONObject(result.toString());

		ObjectMapper mapper = new ObjectMapper();
		actualObj = mapper.readTree(result.toString());
		
		System.out.println(actualObj);

		return actualObj;
	}
}
