package io.editor.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IframeController  {
	@RequestMapping(value = "/iframe", method = RequestMethod.GET)
	public TestMoc testIframe(HttpServletRequest request,HttpServletResponse response) {
		String url = "http://www.naver.com";
		HttpGet http = new HttpGet(url);
		HttpResponse httpResponse =null ;
		String doc = null;
		String testDoc = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		TestMoc testmoc = new TestMoc();
		try {
			httpResponse = httpClient.execute(http);
			HttpEntity httpEntity = httpResponse.getEntity();
			ContentType contentType = ContentType.getOrDefault(httpEntity);
			Charset charset = contentType.getCharset();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), charset));
			 for(int i=0; i<httpResponse.getAllHeaders().length;i++){
			 System.out.println("test"+httpResponse.getAllHeaders()[i]);
			 }
			StringBuffer dom = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				dom.append(line + "\n");
			}
			doc = dom.toString();
			String[] tags = doc.split(">");
			for (String tag : tags) {
				if (tag.contains("src") || tag.contains("href")||tag.contains("content")) {
					if (!(tag.contains("http"))) {
						if(tag.contains("src")){
							testDoc += tag.replace("src=\"", "src=\"".concat(url)).concat(">");
						}else if(tag.contains("href")){
							testDoc += tag.replace("href=\"", "href=\"".concat(url)).concat(">");
						}else{
							testDoc += tag.replace("content=\"","content=\"".concat(url));
						}
					}else{
						testDoc += tag.concat(">");
					}
				}else{
					testDoc += tag.concat(">");
				}
			}
			testmoc.setNumber(1);
			testmoc.setName(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testmoc;
	}
}
