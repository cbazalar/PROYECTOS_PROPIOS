package biz.belcorp.ssicc.dao.spusicc;

import java.util.List;

import biz.belcorp.ssicc.dao.spusicc.ventas.model.ParametroCDR;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoDATParametrosCDRDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
public interface MantenimientoDATParametrosCDRDAO {

	List getParametrosCDR(ParametroCDR parametroCDR);

	ParametroCDR getParametroCDRById(ParametroCDR parametroCDR);

	void insertParametroCDR(ParametroCDR parametroCDR);

	void updateParametroCDR(ParametroCDR parametroCDR);

	void deleteParametroCDR(ParametroCDR parametroCDR);

	

}
