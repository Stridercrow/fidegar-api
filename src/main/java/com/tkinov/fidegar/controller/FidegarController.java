package com.tkinov.fidegar.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkinov.fidegar.domain.Pregunta;
import com.tkinov.fidegar.domain.Response;
import com.tkinov.fidegar.service.FidegarService;


@RestController
@RequestMapping("/fidegar-api")
public class FidegarController {
	
	@Autowired
	FidegarService fidegarService;
	
    @RequestMapping("/login")
    public Response login() {
    	
    	List<Pregunta> preguntas = fidegarService.findAll();
    	
    	for(Pregunta pregunta : preguntas) {
    		System.out.println(pregunta.toString());
    	}
    	
        return new Response(200, "Logino");
    }
    
    @RequestMapping("/reset/nip")
    public Response greeting() {
        return new Response(200, "Reseteando NIP");
    }
    /*
    @RequestMapping("/noCelular")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/preguntaSecreta")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }*/
}