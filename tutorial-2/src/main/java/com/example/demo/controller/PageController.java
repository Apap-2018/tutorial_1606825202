package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value="name") String name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
//	@RequestMapping("/challenge/{name}")
//	public String challengePath(@PathVariable String name, Model model) {
//		model.addAttribute("name",name);
//		return "challenge";
//	}
	
	@RequestMapping(value = {"/challenge", "challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}
		else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	//a = jumlah m
	//b = kelipatan kata
	//a=0 atau a=1, kata pasti hm, wajib liat b
	//apapun value b harus liat a
	
	@RequestMapping(value = {"/generator"})
	public String viralGenerator(@RequestParam(value="a", required=false, defaultValue="0") String firstNum,
								 @RequestParam(value="b", required=false, defaultValue="0") String secondNum, Model model){
		
		String word="";
		
		if(firstNum.equals("0") || firstNum.equals("1")){
			word="hm";
		}
		
		else{
			word = "h";
			for (int i=0; i<Integer.parseInt(firstNum); i++) {
				word+="m";
			}
		}
		
		if (Integer.parseInt(secondNum)>1){
			String temp = word;	
			
			for (int i=0; i<Integer.parseInt(secondNum)-1; i++) {
				word+= " " + temp;
			}
			
		}
		
		model.addAttribute("firstNum", firstNum);
		model.addAttribute("secondNum", secondNum);
		model.addAttribute("word", word);

		return "generator";
	}
}
