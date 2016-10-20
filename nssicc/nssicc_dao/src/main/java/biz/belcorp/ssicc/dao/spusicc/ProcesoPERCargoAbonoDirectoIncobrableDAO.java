/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="procesoPERCargoAbonoDirectoIncobrableForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public interface ProcesoPERCargoAbonoDirectoIncobrableDAO extends DAO {

	public void executeCargoAbonoDirectoIncobrable(Map criteria);
	
}