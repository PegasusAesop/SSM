package com.pegasus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/layer")
public class LayerController {

	@RequestMapping("/test.do")
	@ResponseBody
	public String test(int number) {
		String msg=null;
		try {
			float a = 10/number;
			msg="OK";
		}catch(Exception e) {
			
			e.printStackTrace();
			msg="failure";
		}
		return msg;
	}
}
