package com.pegasus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.StreamUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.pegasus.dao.CustomerMapper;
import com.pegasus.domain.Customer;
import com.pegasus.domain.Material;
import com.pegasus.service.ICustomerService;
import com.pegasus.utils.DBWriteToExcel;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final int PAGE_SIZE=5;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ICustomerService customerService;
		
	@RequestMapping("/add.do")
	public String addCustomer(@ModelAttribute Customer customer, Errors errors){
		//1服务器端的校验
		if(errors.hasErrors()) {
			System.out.println("*******************************");
			System.out.println(errors);
			System.out.println("*******************************");
			return "redirect:/customer/findPageByPre.do";
		}
		customerMapper.insertCustomer(customer);
		return "redirect:/customer/findPageByPre.do";
	}
	@RequestMapping("/deleteById.do")
	@ResponseBody
	public int delCustomerById(String id) {
		int row = customerMapper.deleteCustomerById(id);
		return row;
	}
	@RequestMapping("/batchDeleteByIds.do")
	public String batchDelCustomerByIds(HttpServletRequest request) {
		
		String delItems=request.getParameter("delItems");
		String[] items=delItems.split(",");
		int rows = customerMapper.batchDeleteCustomerById(items);
		System.out.println("删除的条数："+rows);
		return "redirect:/customer/findPageByPre.do";	
	}
	
	@RequestMapping("/findById.do")
	public String findById(String id,Model model,HttpSession session) {
		Customer customer = customerService.selectById(id);
		model.addAttribute("customer", customer);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println(customer);
		
		session.setAttribute("customer", customer);
		return "redirect:/customer/findPageByPre.do";
	}
	
	@RequestMapping("/updateById.do")
	public String updateCustomerById(Customer customer,Errors errors) {
		//1服务器端的校验
		if(errors.hasErrors()) {
			System.out.println("*******************************");
			System.out.println(errors);
			System.out.println("*******************************");
			return "redirect:/customer/findPageByPre.do";
		}
		
		int row = customerMapper.updateCustomerById(customer);
		System.out.println("成功修改："+row);
		return "redirect:/customer/findPageByPre.do";
	}
	
	@RequestMapping("/findAll.do")
	@ResponseBody
	public List<Customer> findAll(Model model,HttpSession session){
		List<Customer> customers = customerMapper.findAll();
		
		return customers;
	}
	
	@RequestMapping("/findByName.do")
	@ResponseBody
	public String findByName(@RequestParam(defaultValue="中兴通讯") String name) {
		Customer customer = customerMapper.selectByName(name);
		
		//return true;
		return customer != null ? "<font color='pink'>用户名已存在</font>":"<font color='green'>可用该用户名</font>";
	}
	
	@RequestMapping("/findByPage.do")
	@ResponseBody
	public PageInfo<Customer> findByPage(@RequestParam(defaultValue="1") int pageNum,HttpSession session){
		PageInfo<Customer> pageInfo = customerService.findCustomerByPage(pageNum, PAGE_SIZE);
		session.setAttribute("pageInfo", pageInfo);
		List<Customer> customers = pageInfo.getList();
		session.setAttribute("customers", customers);
		return pageInfo;
	}
	
	@RequestMapping("/findPageByPre.do")
	public String getPage(@RequestParam(defaultValue="1") int pageNum,HttpSession session) {
		
		PageInfo<Customer> pageInfo = customerService.findCustomerByPage(pageNum, PAGE_SIZE);
		int prePage = pageInfo.getPrePage();
		if(prePage==0) {
			pageInfo.setPrePage(pageInfo.getPageNum());
		}
		
		int nextPage = pageInfo.getNextPage();
		if(nextPage==0) {
			pageInfo.setNextPage(pageInfo.getPages());
		}
		session.setAttribute("pageInfo", pageInfo);
		
		return "/home";
	}
	@RequestMapping("/exportAll.do")
	public void exportAll(HttpServletResponse response) {
		
		String pre_outFileName="客户信息表";
		try {
			pre_outFileName = new String(pre_outFileName.getBytes("utf-8"),"iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+pre_outFileName+"_"+System.currentTimeMillis()+".xlsx");
		
		List<Customer> customers = customerMapper.findAll();
		XSSFWorkbook workbook = DBWriteToExcel.writer(customers, "客户信息表", Customer.class);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/exportCkdAll.do")
	public void exportCkdAll(HttpServletRequest request,HttpServletResponse response) {
		
		String pre_outFileName="选中客户信息表";
		try {
			pre_outFileName = new String(pre_outFileName.getBytes("utf-8"),"iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+pre_outFileName+"_"+System.currentTimeMillis()+".xlsx");
		
		String[] ids = request.getParameterValues("id");
		for(String id:ids) {
			System.out.println("id:"+id);
		}
		
		List<Customer> customers = customerService.findByIds(ids);
		XSSFWorkbook workbook = DBWriteToExcel.writer(customers, "部分客户信息表", Customer.class);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/exportFileByName.do")
	public void exportFileByName(@RequestParam(defaultValue="springMVC0.jpg") String fileName, HttpSession session,HttpServletResponse response) {

		try {
			fileName=new String(fileName.getBytes("utf-8"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;filename="+fileName);
		//0获取项目的classpath路径；
		String path=ClassUtils.getDefaultClassLoader().getResource("").getPath();
		path=path+"static/images/";
		System.out.println(path);
		File file = new File(path,fileName);
		try {
			InputStream in = new FileInputStream(file);
			OutputStream  out = response.getOutputStream();
			StreamUtils.copy(in, out);
			in.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@RequestMapping("/importExcelData.do")
	//@ResponseBody
	public String importExcelData(@RequestParam() MultipartFile file) {
		System.out.println("import");
		boolean a = false;
		String fileName=file.getOriginalFilename();
		try {
			a= customerService.batchImport(fileName,file);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(a);
		return "redirect:/customer/findPageByPre.do";
	}
	
	
	@ModelAttribute("names")
	public List<String> getCustomerName(){
		List<String> result = Arrays.asList("--请选择--","中兴通讯","福建星网","阳光电源");
		return result;
	}
	
	@RequestMapping("/var/{var}")
	@ResponseBody
	public String testUrlParam(@PathVariable String var) {
		System.out.println("test param from url.");
		System.out.println("var:"+var);
		return var;
	}
	
	@RequestMapping("/testArr.do")
	@ResponseBody
	public String[] testArr(HttpServletRequest request) {
		
		String[] ids = request.getParameterValues("id");
		
		return ids;
	}
	
	@RequestMapping("/testMav.do")
	public ModelAndView testMav(String name) {
		
		ModelAndView mav=new ModelAndView();
		Material material = new Material();
		material.setName("aluminium steel");
		mav.addObject("material", material);
		mav.setViewName("temp/success");
		return mav;
	}
	
	
 }
