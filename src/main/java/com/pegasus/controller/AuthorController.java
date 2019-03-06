package com.pegasus.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pegasus.domain.Author;
import com.pegasus.service.IAuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private IAuthorService authorService;
	
	@RequestMapping("/addAuthor.do")
	
	public String addAuthor(@RequestParam() MultipartFile file,HttpSession session) {
		
		Author author = new Author();
		//int result =0;
		try {
			InputStream in = file.getInputStream();
			byte[] img = StreamUtils.copyToByteArray(in);
			author.setImg(img);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		authorService.AddAuthor(author);
		session.setAttribute("author", author);
		
		return "redirect:/page/success";
	}
	
	@RequestMapping("/findAuthorById.do")
	
	public void findAuthorById(@RequestParam(defaultValue="1") int id,Model model,HttpServletResponse response,HttpSession session ) {
		
		// response.setContentType("image/jpeg");  
		//response.setHeader("Content-Disposition","attachment");
		OutputStream out;
		Author author=null;
		try {
			out = response.getOutputStream();
			author = authorService.findAuthorById(id);
			byte[] img = author.getImg();
			StreamUtils.copy(img, out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("author",author);
		session.setAttribute("author", author);
	}
}
