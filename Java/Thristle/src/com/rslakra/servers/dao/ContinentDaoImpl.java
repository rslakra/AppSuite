package com.rslakra.servers.dao;

import java.util.List;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;
import com.rslakra.bos.Continent;
import com.rslakra.servers.dao.rowmappers.ContinentRowMapper;
import com.rslakra.servers.exceptions.DaoException;

/**
 * Business DAO operations related to the <tt>AiLogixServer</tt> data.
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 * @since 1.0.0
 */
public class ContinentDaoImpl extends DaoImpl<Continent> implements ContinentDao {
	
	/* logger */
	private final Logger log = LogManager.getLogger(ContinentDaoImpl.class);
	
	/**
	 * @see com.rslakra.servers.dao.ContinentDao#getContinents()
	 */
	@Override
	public List<Continent> getContinents() throws DaoException {
		log.debug("+getContinents()");
		List<Continent> continents = null;
		try {
			/* execute query */
			continents = getSimpleJdbcTemplate().query("SELECT * FROM Continent WITH (NOLOCK) WHERE Active = 1", new ContinentRowMapper());
			
			/* check server found */
			if(null == continents || continents.size() <= 0) {
				throw new Exception("No continents were found in Continents table!");
			}
		} catch(Exception ex) {
			throw new DaoException("Error loading data from continents table!", ex);
		}
		
		log.debug("-getContinents(), continents:" + continents);
		return continents;
	}
	
	/**
	 * @see com.rslakra.servers.dao.ContinentDao#getContinent(long)
	 */
	@Override
	public Continent getContinent(long id) throws DaoException {
		log.debug("+getContinent()");
		Continent continent = null;
		try {
			String query = "SELECT * FROM Continents WITH (NOLOCK) WHERE Active = 1 and id = " + id;
			/* fetch result */
			continent = getSimpleJdbcTemplate().queryForObject(query, new ContinentRowMapper());
			/* valid server found. */
			if(null == continent) {
				throw new Exception("No Continent found for id:" + id);
			}
		} catch(Exception ex) {
			throw new DaoException("Error loading data from 'Continents' table for id:" + id, ex);
		}
		
		log.debug("-getContinent(), aiLogixServer: " + continent);
		return continent;
		
	}
}