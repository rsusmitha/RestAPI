package restapi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{
	
	TestBase testBase;
	String url;
	String serviceUrl;
	String apiUrl;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase(); 
		 serviceUrl = prop.getProperty("URL");
		 apiUrl = prop.getProperty("serviceURL");
		
		 url = serviceUrl+apiUrl;  // https://reqres.in/api/users 
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String>headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson API : convert java  object to json
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "manager");
		
		//object to json file:
		//mapper.writeValue(new File("/Users/srakapali/eclipse-workspace/restapi/src/main/java/com/qa/data/users.json"), users);
		
		//object to json in String
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println("usersJsonString - - > "+ usersJsonString);
		
		httpResponse = restClient.post(url, usersJsonString, headerMap);
		
		//1. Check Status code:
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();	
		System.out.println("Status code --->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		
		//2. Json String/Response: 
		 
		String jsonResponseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println("JSON STRING from API --> :" + jsonResponseString);
		
		//convert string to json
		
		JSONObject responseJSON = new JSONObject(jsonResponseString);
		System.out.println(responseJSON);
	}

	
	
	
	
	
}
