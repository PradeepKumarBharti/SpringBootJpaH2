package org.o7planning.springbooth2.controller;
 
import org.o7planning.springbooth2.dao.PersonDAO;
import org.o7planning.springbooth2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
 
@Controller
public class MainController {
 
    @Autowired
    private PersonDAO personDAO;
    
 // inject via application.properties
  	@Value("${welcome.message:test}")
  	private String message = "Hello World";
 
    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Person> all = personDAO.findAll();
 
        StringBuilder sb = new StringBuilder();
 
        all.forEach(p -> sb.append(p.getFullName() + "<br>"));
 
        return sb.toString();
    }
    
    @RequestMapping("/hai")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
 
 
}