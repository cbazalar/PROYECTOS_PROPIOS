package biz.belcorp.ssicc.service.spusicc;

import java.util.List;

import biz.belcorp.ssicc.dao.spusicc.ventas.model.ParametroCDR;

public interface MantenimientoDATParametrosCDRService {

	List getParametrosCDRByPais(ParametroCDR parametro);
	
	ParametroCDR getParametroCDRById(ParametroCDR cabecera);

	void insertParametrosCDR(ParametroCDR cabecera);
	
	void updateParametrosCDR(ParametroCDR cabecera);
	
	void deleteParametrosCDR(ParametroCDR cabecera);

}
