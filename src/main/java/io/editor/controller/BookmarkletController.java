package io.editor.controller;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookmarkletController {
	
	
	@RequestMapping(value="/collect",method=RequestMethod.GET)
	public String collect(HttpServletRequest req){
		String urlData = req.getParameter("urlData");
		System.out.println("입력정보"+urlData);
		TestMoc result = new TestMoc();
		result.setName("tttt");
		result.setNumber(1111);
		System.out.println("Test11");
		return result.toString();
		
	}

}
