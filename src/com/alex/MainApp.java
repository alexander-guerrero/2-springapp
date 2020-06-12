package com.alex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alex.pojo.Admin;

public class MainApp {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		
		Admin administrador = (Admin) applicationContext.getBean("admin");
		// administrador.imprimirDireccion();
		System.out.println(administrador);
		
		((ClassPathXmlApplicationContext) applicationContext).close();
	}

}
