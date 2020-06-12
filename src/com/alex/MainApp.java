package com.alex;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alex.dao.AdminDao;
import com.alex.pojo.Admin;

public class MainApp {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");

		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

		Admin admin = new Admin();
		admin.setNombre("Cristian");
		admin.setCargo("Gerente");
		admin.setFechaCreacion(new Timestamp(new Date().getTime()));

		if (adminDao.save(admin)) {
			System.out.println("Se insertó " + admin);
		} else {
			System.out.println("No se insertó " + admin);
		}

		((ClassPathXmlApplicationContext) applicationContext).close();
	}

}
