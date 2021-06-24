package com.example.demo.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/customer")
	public String customer() {
		return "customer";
	}
	
	@GetMapping("/order")
	public String order() {
		return "order";
	}
	
	@GetMapping("/Stock")
	public String Stock() {
		return "Stock";
	}
	
	@GetMapping("/stockdisplay")
	public String stockdisplay() {
		return "stockdisplay";
	}
	
	@GetMapping("/productByName")
	public String productByName() {
		return "productByName";
	}
	
	


}
