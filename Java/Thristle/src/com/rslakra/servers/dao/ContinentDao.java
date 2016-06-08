package com.rslakra.servers.dao;

import java.util.List;

import com.rslakra.bos.Continent;
import com.rslakra.servers.exceptions.DaoException;

/**
 * Business DAO operations related to the <tt>Continent</tt> data.
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ContinentDao extends Dao<Continent> {
	
	/**
	 * Returns the list of all active <code>Continent</code>'s.
	 * 
	 * @return
	 * 
	 * @throws DaoException
	 * @since 1.0.0
	 */
	public List<Continent> getContinents() throws DaoException;
	
	/**
	 * Returns a <code>Continent</code> object for the specified
	 * <code>id</code>.
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 * @since 1.0.0
	 */
	public Continent getContinent(long id) throws DaoException;
	
}