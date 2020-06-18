package com.hr.management.driver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hr.management.model.Employee;
import com.hr.management.service.UserService;

/**
 * Main DispatcherServlet Controller.<p>
 * Handles all the Incoming and outgoing requests<p>
 * and responses.
 * @author sumitsingh
 *
 */
@Controller
public class LoginController {

	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)  
	public String isInvalid(HttpServletRequest req, ModelMap m) {
		m.addAttribute("message", "Please Login First");
		return "errorpage";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)  
	public String isValidUser(HttpServletRequest req, ModelMap m) throws JsonParseException, JsonMappingException, IOException {
		
		 String name = req.getParameter("username");  
	     String pass = req.getParameter("password");  
	     
	     String realpass = userService.getPass(name);
	     
	     if (!pass.equals(realpass)) {
	    	 String msg="Sorry "+ name+". You entered an incorrect password";  
	         m.addAttribute("message", msg);  
	    	 return "errorpage";
	     }
	     
	     HttpSession session = req.getSession();
	     String msg="Hello "+ name;  
	     session.setAttribute("userinfo", msg);
         m.addAttribute("message", msg);
         m.addAttribute("employees", RestFetch.fetchAllEmployees());
	     return "viewpage";
	}
	
	
	@RequestMapping("/editDetails")
	public String editDetailsForm(HttpServletRequest req, ModelMap m) throws JsonMappingException, NumberFormatException, JsonProcessingException {
		if (req.getSession().getAttribute("userinfo") == null) {
			m.addAttribute("message", "Please Login First");
			return "errorpage";
		}
		String id = req.getParameter("empId");
		m.addAttribute("employee", RestFetch.fetchEmployeeById(Integer.parseInt(id)));
		return "editEmployeeDetailsForm";
	}
	
	@RequestMapping("/updateDetails")
	public String updateDetailsSubmission(HttpServletRequest req, ModelMap m) throws IOException {
		if (req.getSession().getAttribute("userinfo") == null) {
			m.addAttribute("message", "Please Login First");
			return "errorpage";
		}
		Employee ob = new Employee();
		ob.setEmpDOB(req.getParameter("empDOB"));
		ob.setEmpEmail(req.getParameter("empEmail"));
		ob.setEmpId(Integer.parseInt(req.getParameter("empId")));
		ob.setEmpLocation(req.getParameter("empLocation"));
		ob.setEmpName(req.getParameter("empName"));
		
		RestFetch.updateEmployeeDetails(ob);
		m.addAttribute("employees", RestFetch.fetchAllEmployees());
		return "viewpage";
	}
	
	@RequestMapping("/AddEmployee")
	public String addEmployeeDetails (HttpServletRequest req, ModelMap m) throws JsonProcessingException {
		if (req.getSession().getAttribute("userinfo") == null) {
			m.addAttribute("message", "Please Login First");
			return "errorpage";
		}
		
		Employee ob = new Employee();
		ob.setEmpDOB(req.getParameter("empDOB"));
		ob.setEmpEmail(req.getParameter("empEmail"));
		ob.setEmpId(Integer.parseInt(req.getParameter("empId")));
		ob.setEmpLocation(req.getParameter("empLocation"));
		ob.setEmpName(req.getParameter("empName"));
		
		RestFetch.addEmployee(ob);
		m.addAttribute("userinform", "Successfully added");
		return "uploadEmployeeDetailsForm";
	}
	
	
	@RequestMapping("/uploadEmployeeDetailsForm")
	public String addNewDetails (HttpServletRequest req, ModelMap m) throws IOException {
		if (req.getSession().getAttribute("userinfo") == null) {
			m.addAttribute("message", "Please Login First");
			return "errorpage";
		}
		return "uploadEmployeeDetailsForm";
	}
	@RequestMapping("/delete")
	public String deleteUserDetails(HttpServletRequest req, ModelMap m) throws JsonParseException, JsonMappingException, IOException {
		if (req.getSession().getAttribute("userinfo") == null) {
			m.addAttribute("message", "Please Login First");
			return "errorpage";
		}
		int id = Integer.parseInt(req.getParameter("id"));
		RestFetch.deleteEmployee(id);
		m.addAttribute("employees", RestFetch.fetchAllEmployees());
		return "viewpage";	
	}
	
	@RequestMapping("/logout")
	public void logoutCurrentUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		res.sendRedirect("index.jsp");
	}
	
	@RequestMapping("/downloadData")
	public void downloadData (HttpServletRequest req, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		if (req.getSession().getAttribute("userinfo") == null) {
			response.sendRedirect("index.jsp");;
		}
		String jsonfile = RestFetch.fetchAllEmployeesFile();
		InputStream stream = new ByteArrayInputStream(jsonfile.getBytes(StandardCharsets.UTF_8));
		PrintWriter out = response.getWriter();  
		response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=employeesInfo.json");
        int i;   
        while ((i=stream.read()) != -1) {  
        out.write(i);   
        }   
        stream.close();   
        out.close();   
	}
	
	@RequestMapping("/cancel")
	public String cancel(HttpServletRequest req, ModelMap m) throws JsonParseException, JsonMappingException, IOException {
		if (req.getSession().getAttribute("userinfo") == null) {
			m.addAttribute("message", "Please Login First");
			return "errorpage";
		}
		m.addAttribute("employees", RestFetch.fetchAllEmployees());
		return "viewpage";
	}
	
	
	
}
