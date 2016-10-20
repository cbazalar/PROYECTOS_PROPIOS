package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECCierreBRDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECCierreBRService;

@Service("spusicc.procesoRECCierreBRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRECCierreBRServiceImpl extends BaseService implements ProcesoRECCierreBRService{
	
	@Resource(name="spusicc.procesoRECCierreBRDAO")
	private ProcesoRECCierreBRDAO procesoRECCierreBRDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#getResultadoBRList(java.util.Map)
	 */
	public List getResultadoBRList() {
		return procesoRECCierreBRDAO.getResultadoBRList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#getListaBoletasBR(java.util.Map)
	 */
	public List getListaBoletasBRList(Map criteria) {
		return procesoRECCierreBRDAO.getListaBoletasBRList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#getListaBoletasDetalleBRList(java.util.Map)
	 */
	public List getListaBoletasDetalleBRList(Map criteria) {
		return procesoRECCierreBRDAO.getListaBoletasDetalleBRList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#insertProcesoBoletaTemporal(java.util.Map)
	 */
	public void insertProcesoBoletaTemporal(Map criteria) {
		procesoRECCierreBRDAO.insertProcesoBoletaTemporal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#getProcesoBoletaTemporalList(java.util.Map)
	 */
	public List getProcesoBoletaTemporalList() {
		return procesoRECCierreBRDAO.getProcesoBoletaTemporalList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#executeValidarProcesoBoleta(java.util.Map)
	 */
	public void executeValidarProcesoBoleta(Map criteria) {
		//Insertamos las boletas correctas y lanzamos el proceso 
		
		List listaTemporalBoletasCorrectas = (List)MapUtils.getObject(criteria, "listaTemporalBoletasCorrectas");
		
		procesoRECCierreBRDAO.deleteTablaTemporal();
		
		if(listaTemporalBoletasCorrectas != null && listaTemporalBoletasCorrectas.size() > 0)
		{
			for(int i=0; i<listaTemporalBoletasCorrectas.size(); i++)
			{
				Map boleta = (Map)listaTemporalBoletasCorrectas.get(i);				
				procesoRECCierreBRDAO.insertProcesoBoletaTemporal(boleta);
			}
			
			procesoRECCierreBRDAO.executeValidarProcesoBoleta(criteria);
			
			List lista = procesoRECCierreBRDAO.getProcesoBoletaTemporalList();
			
			criteria.put("listaProcesada", lista);
		}
		else
		{
			log.warn("No existen boletas para procesar");
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#getNumeroLoteBoletasRecojo(java.util.Map)
	 */
	public String getNumeroLoteBoletasRecojo() {
		return procesoRECCierreBRDAO.getNumeroLoteBoletasRecojo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#deleteTablaTemporal()
	 */
	public void deleteTablaTemporal() {
		procesoRECCierreBRDAO.deleteTablaTemporal();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCierreBRService#executeValidaRelacionBoletaRecojo(java.util.Map)
	 */
	public Map executeValidaRelacionBoletaRecojo(Map criteria) {
		
		//Borramos la tabla temporal
		procesoRECCierreBRDAO.deleteTablaTemporal();
		
		List listCamposArchivo = (List)criteria.get("listCamposArchivo");
		
	    for(int i=0; i<listCamposArchivo.size(); i++){
	    	Map map = (HashMap)listCamposArchivo.get(i);
	    	String numeroBR = (String)map.get("numeroBR");
	    	long numero = new Float(numeroBR).longValue();
	    	if(numero > 0){
	    		map.put("numeroBR", numero);
	    		procesoRECCierreBRDAO.executeValidaRelacionBoletaRecojo(map);
	    		procesoRECCierreBRDAO.insertProcesoBoletaRecojoTemporal(map);
	    	}
	    }
	    
	    List listTablaTemporal = procesoRECCierreBRDAO.getProcesoBoletaTemporalList();
	    criteria.put("listTablaTemporal", listTablaTemporal);
	    
	    Integer totalIncorrectas = procesoRECCierreBRDAO.getProcesoBoletaTemporalListIncorrectas();
	    Integer totalCorrectas = listCamposArchivo.size() - totalIncorrectas; 
	    criteria.put("totalBoletas", listCamposArchivo.size());
	    criteria.put("totalIncorrectas", totalIncorrectas);
	    criteria.put("totalCorrectas", totalCorrectas);
	    
	    return criteria;
	}
	
}
