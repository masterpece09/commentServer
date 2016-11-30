package com.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Cette classe est juste à titre de test
 * @author celestin.placide.eloundou@gmail.com
 *
 */

@RestController
public class HelloControler { 
	  
	@RequestMapping(value="/hello")
	public String hello(){
		return "Hello world!";
	}
	
	@RequestMapping(value="/")
	public String welcome(){
		return "welcome to the comment management";
	}
	
	@RequestMapping("/upload")
	private void upload(@RequestParam("file") MultipartFile file,HttpSession session) {
		String path=session.getServletContext().getRealPath("/");  
        String filename=file.getOriginalFilename();  
          
        System.out.println(path+" "+filename);  
        try{  
        byte barr[]=file.getBytes();   
          
        BufferedOutputStream bout=new BufferedOutputStream(  
//                 new FileOutputStream(path+"/"+filename)); 
        		new FileOutputStream("/opt/"+filename));
        bout.write(barr);  
        bout.flush();  
        bout.close();  
          
        }catch(Exception e){System.out.println(e);}  
	}

}
