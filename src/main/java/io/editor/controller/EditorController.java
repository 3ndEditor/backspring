package io.editor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class EditorController {
	
	@GetMapping("/hello")
	public TestMoc hello(){
		
		TestMoc result = new TestMoc();
		result.setName("tttt");
		result.setNumber(1111);
		
		return result;
	}

}

@AllArgsConstructor
@Data
class Member {
	public Member(long l, String string) {
		// TODO Auto-generated constructor stub
	}
	int id;
	private String name;
}
