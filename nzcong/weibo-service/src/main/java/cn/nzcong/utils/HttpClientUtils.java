package cn.nzcong.utils;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	public static byte[] doPost(String url, byte[] body) {
		byte[] response = null;
		try {
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new ByteArrayEntity(body));
			log.debug("http request url:" + url);
			log.debug("http request body: 0x" + ByteUtils.getHexString(body));
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			log.debug("http response status:" + httpResponse.getStatusLine());
			log.debug("http response headers:");
			HeaderIterator iterator = httpResponse.headerIterator();
			while (iterator.hasNext()) {
				log.debug("\t" + iterator.next());
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
				if (httpEntity != null) {
					response = EntityUtils.toByteArray(httpEntity);
					log.debug("http response body: 0x" + ByteUtils.getHexString(response));
				}
			} else {
				log.error("http response body: \n" + EntityUtils.toString(httpEntity));
			}
			closeableHttpClient.close();
		} catch (Exception e) {
			log.error("doPost error:", e);
		}
		return response;
	}

	public static void main(String[] args) {

	}
}
