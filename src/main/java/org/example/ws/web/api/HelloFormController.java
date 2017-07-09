package org.example.ws.web.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * The Thymeleaf Controller class is a RESTful web service controller.
 * it looks for html file in resources/templates
 *
 * url      :http://localhost:8080/
 * user name: user      or operations
 * password : password  or operations
 *
 * @author Matt Warman
 */
@Controller
public class HelloFormController extends BaseController {

    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index2";
    }

    @GetMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }

    @RequestMapping(value ="/hello3")
    public String hello3(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }

    /**
     * Web service endpoint to fetch all Greeting entities. The service returns
     * the collection of Greeting entities as JSON.
     * @return A ResponseEntity containing a Collection of Greeting objects.*/
    @RequestMapping(  value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String hello() {

        logger.info("> index.html");
        return "index";

    }


    /**
     * Web service endpoint to fetch all Greeting entities. The service returns
     * the collection of Greeting entities as JSON.
     * @return A ResponseEntity containing a Collection of Greeting objects.*/
    @RequestMapping(  value = "/hello2", method = RequestMethod.GET)
    @ResponseBody//return a string
    public String hello2(HttpServletRequest req) {
        String name = req.getParameter("name");
        if (name == null)
            name = "world";
        logger.info("> index.html");
        String msg = "Hellow";//HelloMessage.getMessage(name);
        //return "index";
        return "<h1>"+msg+" "+name+"</h1>";
    }


    /**
     * url : hello4?name=lolik
     **/
    @RequestMapping(  value = "/hello4", method = RequestMethod.GET)
    public String hello4(HttpServletRequest req, Model model) {

        String name = req.getParameter("name");
        if (name == null)
            name = "world";
        logger.info("> index.html");
        String msg = "Hello";//HelloMessage.getMessage(name);
        //return "index";

        model.addAttribute("message", msg);
        model.addAttribute("name", name);
        return "index";//this tell spring to render templates/index.html
    }
}
