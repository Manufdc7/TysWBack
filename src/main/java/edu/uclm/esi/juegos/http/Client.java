package edu.uclm.esi.juegos.http;

import java.io.IOException;
import java.io.Serializable;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Client {

	private static final long serialVersionUID = 1L;
	private BasicCookieStore cookieStore = new BasicCookieStore();

	public JSONObject sendGet(String url) {
		try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build()) {
			HttpGet get = new HttpGet(url);
			try (CloseableHttpResponse response = client.execute(get)) {
				HttpEntity entity = response.getEntity();
				Header[] hh = response.getAllHeaders();
				// SE IMPRIMEN TODAS LAS CABECERAS
				for (int i = 0; i < hh.length; i++)
					System.out.println(hh[i].getName() + " = " + hh[i].getValue());
				System.out.println("-----------------");
				String responseText = EntityUtils.toString(entity);
				if (responseText == null || responseText.length() == 0)
					return null;
				return new JSONObject(responseText);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		} catch (IOException e1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e1.getMessage());
		}
	}

	public JSONObject sendPost(String url, JSONObject payload) {

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build()) {

			HttpPost makePost = new HttpPost(url);

			try {

				HttpEntity entity = new StringEntity(payload.toString());

				makePost.setEntity(entity);
				makePost.setHeader("Accept", "application/json");
				makePost.setHeader("Content-type", "application/json");

				CloseableHttpResponse response = httpClient.execute(makePost);
				entity = response.getEntity();
				String responseText = EntityUtils.toString(entity);
				if (responseText == null || responseText.length() == 0)
					return null;
				return new JSONObject(responseText);

			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}

		} catch (Exception e1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e1.getMessage());
		}
	}
	
	public JSONObject sendCurlPost(JSONObject jsoPayload, String body) {
		try(CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build()) {
			HttpPost post = new HttpPost(jsoPayload.getString("url"));
			JSONArray jsaHeaders = jsoPayload.getJSONArray("headers");
			for (int i=0; i<jsaHeaders.length(); i++) {
				post.setHeader(jsaHeaders.getString(i), jsaHeaders.getString(i+1));
				i++;
			}

			JSONObject jsoData = jsoPayload.getJSONObject("data");
			HttpEntity entity = new StringEntity(jsoData.toString());
			post.setEntity(entity);
			
			CloseableHttpResponse response = client.execute(post);
			entity = response.getEntity();
			String responseText = EntityUtils.toString(entity);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode()>=400)
				throw new ResponseStatusException(HttpStatus.resolve(statusLine.getStatusCode()), statusLine.getReasonPhrase());
			if (responseText==null || responseText.length()==0)
				return null;
			return new JSONObject(responseText);
		} catch (IOException e1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e1.getMessage());
		}
	}

}
