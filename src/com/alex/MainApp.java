package com.alex;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.alex.dao.AdminDao;
import com.alex.pojo.Admin;

public class MainApp {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");

		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

//		Admin admin = new Admin();
//		admin.setNombre("Jorge");
//		admin.setCargo("Gerente");
//		admin.setFechaCreacion(new Timestamp(new Date().getTime()));

		try {
//			adminDao.save(admin);

//			List<Admin> admins = adminDao.findAll();

//			for (Admin adm : admins) {
//				System.out.println(adm);
//			}

//			System.out.println(adminDao.findById(7));
//			System.out.println(adminDao.findByNombre("SA"));

//			// Actualizar
//			Admin admin = adminDao.findById(5);
//			System.out.println("Original " + admin);
//			admin.setNombre("Antonio");
//			admin.setCargo("Consultor");
//
//			if (adminDao.update(admin)) {
//				System.out.println("Actualizado " + admin);
//			}
//
//			// Eliminar
//			Admin admin2 = adminDao.findById(2);
//			System.out.println("Se eliminará " + admin2);
//			if (adminDao.delete(admin2.getIdAdm())) {
//				System.out.println("Eliminado " + admin2);
//			}
			
			// Obtener la fecha actual
			Timestamp ts = new Timestamp(new Date().getTime());
			
			// Batch Update (Insert, Update o Delete masivo)
			List<Admin> admins = new ArrayList<Admin>();
			admins.add(new Admin("Luis", "Arquitecto de software", ts));
			admins.add(new Admin("Rafael", "Jefe de proyectos", ts));
			admins.add(new Admin("José", "DBA", ts));
			int[] numFilasAfectadas = adminDao.saveAll(admins);
			for (int i : numFilasAfectadas) {
				System.out.println("Se afectó " + i + " fila(s).");
			}

		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		((ClassPathXmlApplicationContext) applicationContext).close();
	}

}
