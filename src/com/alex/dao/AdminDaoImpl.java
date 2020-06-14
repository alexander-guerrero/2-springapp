package com.alex.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.pojo.Admin;
import com.alex.pojo.AdminRowMapper;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Admin admin) {
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());

		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);

		return jdbcTemplate.update(
				"INSERT INTO Admin (nombre, cargo, fechaCreacion) VALUES (:nombre, :cargo, :fechaCreacion)",
				paramMap) == 1;
	}

	@Override
	public List<Admin> findAll() {

		return jdbcTemplate.query("SELECT * FROM Admin", new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				admin.setIdAdm(rs.getInt("idAdm"));
				admin.setNombre(rs.getString("nombre"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));

				return admin;
			}
		});
	}

	@Override
	public Admin findById(int id) {

		return jdbcTemplate.queryForObject("SELECT * FROM Admin WHERE idAdm=:_idAdm",
				new MapSqlParameterSource("_idAdm", id), new AdminRowMapper());
	}

	@Override
	public List<Admin> findByNombre(String nombre) {

		return jdbcTemplate.query("SELECT * FROM Admin WHERE nombre LIKE :_nombre",
				new MapSqlParameterSource("_nombre", "%" + nombre + "%"), new AdminRowMapper());
	}

	@Override
	public boolean update(Admin admin) {

		return jdbcTemplate.update(
				"UPDATE Admin SET nombre=:nombre, cargo=:cargo, fechaCreacion=:fechaCreacion WHERE idAdm=:idAdm",
				new BeanPropertySqlParameterSource(admin)) == 1;
	}

	@Override
	public boolean delete(int id) {

		return jdbcTemplate.update("DELETE FROM Admin WHERE idAdm=:_idAdm",
				new MapSqlParameterSource("_idAdm", id)) == 1;
	}

	@Transactional
	@Override
	public int[] saveAll(List<Admin> admins) {
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());

		return jdbcTemplate.batchUpdate(
				"INSERT INTO Admin (idAdm, nombre, cargo, fechaCreacion) VALUES (:idAdm, :nombre, :cargo, :fechaCreacion)", batchArgs);
	}

}
