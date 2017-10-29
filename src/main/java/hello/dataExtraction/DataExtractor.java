package hello.dataExtraction;

import java.io.InputStream;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DataExtractor {
	private String imageJson;
	private String textJson;
	private String data;
	private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/ocr";
	private static final String uriImageBase="https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";
	private static final String subscriptionKey = "0c3ee59a86c240a09cfa4750e492c4d7";

	/*public DataExtractor(String data) {
		this.data = data;
		imageJson = extractImageDetails();
		textJson = extractTextDetails();
	}*/

	public String extractTextDetails(InputStream inputStream) {
		String text = null;
		HttpClient httpClient = new DefaultHttpClient();

		try {
			// NOTE: You must use the same location in your REST call as you used to obtain
			// your subscription keys.
			// For example, if you obtained your subscription keys from westus, replace
			// "westcentralus" in the
			// URL below with "westus".
			URIBuilder uriBuilder = new URIBuilder(uriBase);

			uriBuilder.setParameter("language", "en");
			uriBuilder.setParameter("detectOrientation ", "true");

			// Request parameters.
			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			HttpEntity requestEntity = new InputStreamEntity(inputStream, ContentType.APPLICATION_OCTET_STREAM);
			request.setEntity(requestEntity);

			
			// Execute the REST API call and get the response entity.
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// Format and display the JSON response.
				String jsonString = EntityUtils.toString(entity);
				return jsonString;
				/*JSONObject json = new JSONObject(jsonString);
				System.out.println("REST Response:\n");
				System.out.println(json.toString(2));*/
			}
		} catch (Exception e) {
			// Display error message.
			System.out.println(e.getMessage());
		}

		return text;
	}

	public String extractImageDetails(InputStream inputStream) {
		String image = null;
		HttpClient httpclient = new DefaultHttpClient();

		try {
			URIBuilder builder = new URIBuilder(uriImageBase);

			// Request parameters. All of them are optional.
			builder.setParameter("visualFeatures", "Faces,Color");
			builder.setParameter("language", "en");

			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			HttpEntity requestEntity = new InputStreamEntity(inputStream, ContentType.APPLICATION_OCTET_STREAM);
			request.setEntity(requestEntity);

			// Execute the REST API call and get the response entity.
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// Format and display the JSON response.
				String jsonString = EntityUtils.toString(entity);
				JSONObject json = new JSONObject(jsonString);
				 JSONObject jsonColor = new JSONObject(json.get("color").toString());
				 Object colorValue = jsonColor.get("accentColor");
				 return colorValue.toString();
				/*System.out.println("REST Response:\n");
				System.out.println(json.toString(2));*/
			}
		} catch (Exception e) {
			// Display error message.
			System.out.println(e.getMessage());
		}
		return image;
		
	}

	public String getImageJson() {
		return imageJson;
	}

	public String getTextDetails() {
		return textJson;
	}

	public static void main(String args[]) {

	}
}
