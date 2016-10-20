package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoRECCierreBRDAO extends DAO {

	public List getResultadoBRList();
	public Map executeValidaRelacionBoletaRecojo(Map criteria);
	
	public List getListaBoletasBRList(Map criteria);
	
	public List getListaBoletasDetalleBRList(Map criteria);
	
	public void insertProcesoBoletaTemporal(Map criteria);
	
	public List getProcesoBoletaTemporalList();
	
	public void removeBoletaTemporal(Map criteria);
	
	public void executeValidarProcesoBoleta(Map criteria);
	
	public String getNumeroLoteBoletasRecojo();
	
	public void deleteTablaTemporal();
	
	/**
	 * Devuelve la cantidad de boletas incorrectas obtenidas del archivo temporal
	 * @return
	 */
	public Integer getProcesoBoletaTemporalListIncorrectas();
	
	/**
	 * Inserta las boletas de recojo en la tabla temporal
	 * @param criteria
	 */
	public void insertProcesoBoletaRecojoTemporal(Map criteria);
}
