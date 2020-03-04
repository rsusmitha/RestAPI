package restapi;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

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
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		httpResponse = restClient.get(url);
		
			//1. Status Code: 	
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("statusCode -- - > " + statusCode);
			Assert.assertEquals(statusCode,  RESPONSE_STATUS_CODE_200, "status code is not 200");
				
			//2. Json String
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println(" Response JSON from API -- > " + responseJson);
		
			//per_page: 
			String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("value of per page from Json response is --->" + perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), 6);
			
			//total value :
			String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
			System.out.println("total value from Json resp ---- -> " + totalValue);
			Assert.assertEquals(Integer.parseInt(totalValue), 12);
			
			//get values values from JSON Array:
			String lastName = TestUtil.getValueByJPath(responseJson, "/data[5]/last_name");
			Assert.assertEquals(lastName, "Ramos");
			String id = TestUtil.getValueByJPath(responseJson, "/data[5]/id");
			String firstName = TestUtil.getValueByJPath(responseJson, "/data[5]/first_name");
			String avatar = TestUtil.getValueByJPath(responseJson, "/data[5]/avatar");
			
			System.out.println("Values from the Json Array - - - > " + lastName);
			System.out.println("Values from the Json Array - - - > " + id);
			System.out.println("Values from the Json Array - - - > " + firstName);
			System.out.println("Values from the Json Array - - - > " + avatar);

			
			//3. all Headers
			Header[] headersArray = httpResponse.getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			for(Header header  : headersArray) {
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println(" Headers are " + allHeaders);
			
			}
	@Test(priority=1)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
			restClient = new RestClient();
			
			
			HashMap<String, String>headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			
			httpResponse = restClient.get(url, headerMap);
		
			//1. Status Code: 	
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("statusCode -- - > " + statusCode);
			Assert.assertEquals(statusCode,  RESPONSE_STATUS_CODE_200, "status code is not 200");
				
			//2. Json String
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println(" Response JSON from API -- > " + responseJson);
		
			//per_page: 
			String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("value of per page from Json response is --->" + perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), 6);
			
			//total value :
			String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
			System.out.println("total value from Json resp ---- -> " + totalValue);
			Assert.assertEquals(Integer.parseInt(totalValue), 12);
			
			//get values values from JSON Array:
			String lastName = TestUtil.getValueByJPath(responseJson, "/data[5]/last_name");
			Assert.assertEquals(lastName, "Ramos");
			String id = TestUtil.getValueByJPath(responseJson, "/data[5]/id");
			String firstName = TestUtil.getValueByJPath(responseJson, "/data[5]/first_name");
			String avatar = TestUtil.getValueByJPath(responseJson, "/data[5]/avatar");
			
			System.out.println("Values from the Json Array - - - > " + lastName);
			System.out.println("Values from the Json Array - - - > " + id);
			System.out.println("Values from the Json Array - - - > " + firstName);
			System.out.println("Values from the Json Array - - - > " + avatar);

			
			//3. all Headers
			Header[] headersArray = httpResponse.getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			for(Header header  : headersArray) {
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println(" Headers are " + allHeaders);
			
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

