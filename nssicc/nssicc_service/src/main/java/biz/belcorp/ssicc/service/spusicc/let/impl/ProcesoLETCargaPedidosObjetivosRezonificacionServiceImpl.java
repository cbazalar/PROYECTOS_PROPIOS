package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCargaPedidosObjetivosRezonificacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETCargaPedidosObjetivosRezonificacionService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoLETCargaPedidosObjetivosRezonificacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCargaPedidosObjetivosRezonificacionServiceImpl extends BaseService implements ProcesoLETCargaPedidosObjetivosRezonificacionService{

	@Resource(name="spusicc.procesoLETCargaPedidosObjetivosRezonificacionDAO")
	private ProcesoLETCargaPedidosObjetivosRezonificacionDAO procesoLETCargaPedidosObjetivosRezonificacionDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#getValidaExisteConcursoByCodigoPeriodo(java.util.Map)
	 */
	public boolean getValidaExisteConcursoByCodigoPeriodo(Map criteria) {
		
		boolean flagConcurso = false;
		
		if(procesoLETCargaPedidosObjetivosRezonificacionDAO.getValidaExisteConcursoByCodigoPeriodo(criteria) > 0){ 
			flagConcurso = true;
		}
		
		return flagConcurso;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#getZonasRezonificacion(java.util.Map)
	 */
	public List getZonasRezonificacion(Map criteria) {
		return procesoLETCargaPedidosObjetivosRezonificacionDAO.getZonasRezonificacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#getValidaRegionesCerradas(java.lang.Integer, java.lang.String, java.lang.String[], java.util.List)
	 */
	public boolean getValidaRegionesCerradas(Integer oidPais, String codigoPeriodo, String[] codigoZona, List regionesCerradasList) {
		
		boolean flagRegionesCerradas = false;
		
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		
		for(int i=0; i<codigoZona.length;i++){
			
			criteria.put("codigoZona", codigoZona[i]);
			
			String descripcionRegion = procesoLETCargaPedidosObjetivosRezonificacionDAO.getRegionCerradaByCodigoZona(criteria);
			
			if(descripcionRegion !=  null){
				regionesCerradasList.add(descripcionRegion);
				flagRegionesCerradas = true;
			}
		}
		
		return flagRegionesCerradas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#executeCargaPedidosObjetivosRezonificacion(java.util.Map)
	 */
	public void executeCargaPedidosObjetivosRezonificacion(Map params) {
		
		String codigoPais = (String)params.get("codigoPais");
		String[] codigoZonaList = (String[])params.get("zonasList");
		String codigoZona;
		
		Map parametros = new HashMap();
		
		for(int i=0;i<codigoZonaList.length;i++){
			codigoZona = codigoZonaList[i];
			
			parametros.put("codigoPais", codigoPais);
			parametros.put("codigoZona", codigoZona);
			
			procesoLETCargaPedidosObjetivosRezonificacionDAO.insertZonasRezonificadasGTT(parametros);
		}
		
		procesoLETCargaPedidosObjetivosRezonificacionDAO.executeCargaPedidosObjetivosRezonificacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#getValidaZonaNuevaRezonificada(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	public boolean getValidaZonaNuevaRezonificada(String codigoPais,String indicadorValidaCargaObjetivos, String codigoPeriodo,String codigoMarcaDefault, String codigoCanalDefault,String[] codigoZona,String codigoZonaError) {
		
		boolean flagZonaNuevaRezonificada = true;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoMarca", codigoMarcaDefault);
		criteria.put("codigoCanal", codigoCanalDefault);
		
		for(int i=0;i<codigoZona.length;i++){
			criteria.put("codigoZona", codigoZona[i]);
			
			if(indicadorValidaCargaObjetivos.equals("1")){
				if(this.getZonaNueva(criteria).intValue() == 0 && this.getZonaNuevaRezonificada(criteria).intValue() == 0){
					flagZonaNuevaRezonificada = false;
					codigoZonaError.concat(codigoZona[i]);
					break;
				}
			}
		}
		
		return flagZonaNuevaRezonificada;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#getZonaNuevaRezonificada(java.util.Map)
	 */
	public Integer getZonaNuevaRezonificada(Map criteria) {
		return procesoLETCargaPedidosObjetivosRezonificacionDAO.getZonaNuevaRezonificada(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosRezonificacionService#getZonaNueva(java.util.Map)
	 */
	public Integer getZonaNueva(Map criteria) {
		return procesoLETCargaPedidosObjetivosRezonificacionDAO.getZonaNueva(criteria);
	}
}