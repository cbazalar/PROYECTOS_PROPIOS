/*
 * Created on 29-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoDatoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface TipoDatoDAO extends DAO {
	/**
	 * Obtiene un listado de todos los tipos de dato, de las interfaces.
	 * 
	 * @return
	 */
	public List getTiposDato();
}
