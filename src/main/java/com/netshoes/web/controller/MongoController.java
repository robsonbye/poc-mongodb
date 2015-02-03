package com.netshoes.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netshoes.commons.data.mongodb.dao.GenericMongoDao;
import com.netshoes.web.entity.Customer;


@Controller
public class MongoController {
	
	@Autowired 
	private GenericMongoDao mongoDao;
	
	@RequestMapping(value = "/insert.html", method = RequestMethod.GET)
	public void insertData(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		
		Customer customer = new Customer(new Random().nextInt(999999),"Jhony","Guest");
		
		mongoDao.save(customer);
		
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	@RequestMapping(value = "/find.html", method = RequestMethod.GET)
	public void findData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id) {
		response.setContentType("text/html");
		
		PrintWriter writer;
		
		try {
			writer = response.getWriter();
			Customer customer = mongoDao.findById(Customer.class, id);
			
			writer.println(customer);
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/findByNumber.html", method = RequestMethod.GET)
	public void findByNumberData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "number", required = true) int number) {
		response.setContentType("text/html");
		
		Customer customer = mongoDao.findOneByAttributeInteger(Customer.class, "number", number);
		
		PrintWriter writer;
		try {
			writer = response.getWriter();
			if(customer == null){
				writer.println("Number "+number+" não cadastrado ");
			}
			
			writer.println(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public void deleteData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id) {
		response.setContentType("text/html");
		
		PrintWriter writer;
		
		try {
			writer = response.getWriter();
			Customer customer = mongoDao.findById(Customer.class, id);
			if(customer != null){
				writer.println("Objeto a ser deletado"+customer);
				writer.println(mongoDao.delete(customer));
			}else{
				writer.println("objeto não encontrado");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public void updateData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "number", required = true) int number) {
		response.setContentType("text/html");
		
		Customer customer = mongoDao.findOneByAttributeInteger(Customer.class, "number", number);
		
		PrintWriter writer;
		try {
			writer = response.getWriter();
			if(customer != null){
				customer.setFirstName("JhonyEditted");
				mongoDao.save(customer);
			}else{
				writer.println("Number "+number+" não cadastrado ");
			}
			
			writer.println(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateById.html", method = RequestMethod.GET)
	public void updateByIdData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id) {
		response.setContentType("text/html");
		
		Customer customer = mongoDao.findById(Customer.class, id);
		
		PrintWriter writer;
		try {
			writer = response.getWriter();
			if(customer != null){
				customer.setFirstName("JhonyEditted");
				mongoDao.save(customer);
			}else{
				writer.println("id "+id+" não cadastrado ");
			}
			
			writer.println(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value = "/findAll.html", method = RequestMethod.GET)
	public void findAllData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "show", required = false) boolean isShow) {
		response.setContentType("text/html");
		

		PrintWriter writer;
		try {
			writer = response.getWriter();
			List<Customer> listCustomer = mongoDao.findAll(Customer.class); 
			if(isShow){
				for (Customer customer : listCustomer) {
					writer.println(customer+"<BR>");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	
	
}
