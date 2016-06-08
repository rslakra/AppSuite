package com.rslakra.servers.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;
import com.rslakra.bos.Continent;

/**
 * Converts a row into a new instance of the specified mapped target class,
 * which in this case is a <AiLogixServer> object.
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 * @since 1.0.0
 */
public class ContinentRowMapper implements ParameterizedRowMapper<Continent> {
	
	/* logger */
	private final Logger log = LogManager.getLogger(ContinentRowMapper.class);
	
	/**
	 * Maps the result set data with POJO object.
	 * 
	 * @see org.springframework.jdbc.core.simple.ParameterizedRowMapper#mapRow(java.sql.ResultSet,
	 *      int)
	 * @since 1.5
	 */
	public Continent mapRow(ResultSet rs, int rowNumber) throws SQLException {
		log.debug("+mapRow(" + rs + ", " + rowNumber + ")");
		
		Continent continent = new Continent();
		continent.setId(rs.getInt("id"));
		continent.setName(rs.getString("name"));
		continent.setArea(rs.getString("area"));
		
		log.debug("-mapRow(), continent:" + continent);
		return continent;
	}
}