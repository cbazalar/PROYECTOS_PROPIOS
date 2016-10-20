package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoRECCierreBRService extends Service{
	
	public List getResultadoBRList();
	
	public List getListaBoletasBRList(Map criteria);
	
	public List getListaBoletasDetalleBRList(Map criteria);
	
	public void insertProcesoBoletaTemporal(Map criteria);
	
	public List getProcesoBoletaTemporalList();
	
	public void executeValidarProcesoBoleta(Map criteria);
	
	public String getNumeroLoteBoletasRecojo();
	
	public void deleteTablaTemporal();
	
	/**
	 * Validacin de boletas de recojo
	 * @param criteria
	 * @return
	 */
	public Map executeValidaRelacionBoletaRecojo(Map criteria);
	
}
