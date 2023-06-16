package com.keycloadegbaeduling.keycloadegbaeduling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController

@RequestMapping(value = "/api")
public class Controller {
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees() {

        return new ResponseEntity<>("employees", HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/departments")
    public ResponseEntity<?>getDeparts() {
        return new ResponseEntity<>("departments", HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/access-denied-response")
    public String accessDenied() {
        return "Access Denied... You don't have permission.";
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value="/demo")
    public SenderResponse adminEndPoint() {

        System.out.println("ME llamo admin");
        return new SenderResponse("Hello From Admin");
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value="/manager")
    public SenderResponse managerEndPoint() {
        System.out.println("ME llamo manager");
        return new SenderResponse("Hello From Manager");
    }
}


