package org.example.ws.web.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Thymeleaf Controller class is a RESTful web service controller.
 * it looks for html file in resources/template
 *
 * url      :http://localhost:8080/
 * user name: user      or operations
 * password : password  or operations
 *
 * @author Matt Warman
 */
@Controller
public class WelcomeThymLeafController extends BaseController {



    /**
     * Web service endpoint to fetch all Greeting entities. The service returns
     * the collection of Greeting entities as JSON.
     * @return A ResponseEntity containing a Collection of Greeting objects.*/
    @RequestMapping(  value = "/hello", method = RequestMethod.GET)
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
    @ResponseBody
    public String hello2(HttpServletRequest req) {
        String name = req.getParameter("name");
        if (name == null)
            name = "world";
        logger.info("> index.html");
        String msg = "Hellow";//HelloMessage.getMessage(name);
        //return "index";
        return "<h1>"+msg+" "+name+"</h1>";
    }
}
