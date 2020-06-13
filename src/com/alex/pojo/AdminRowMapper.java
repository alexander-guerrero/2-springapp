package com.alex.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminRowMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

		Admin admin = new Admin();
		admin.setIdAdm(rs.getInt("idAdm"));
		admin.setNombre(rs.getString("nombre"));
		admin.setCargo(rs.getString("cargo"));
		admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
		
		return admin;
	}

}
