package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoLECCargaDatosExcelDAO extends DAO{
	
	public List getTipoCarga(Map map);
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ProcesoPEJCargaProgramaEjecutivasService#getTipoCarga()
	 */
	public List getPrograma(Map map);
	
	public String getNumeroCarga() ;
	
	public Integer insertCargaDatos(Map params) ;
	
	public String executeValidarCargaDatos(Map params);

	public List getCargaDatosList(Map params);
	
	public List getListaIntermedia(Map params); 
	
	public String executeActualizarCargaDatos(Map params);
	
	
	public String getCampanhaInicialPrograma(Map params);
	
	public boolean getNumeroRegistrosResultadosLet(Map params);

}
