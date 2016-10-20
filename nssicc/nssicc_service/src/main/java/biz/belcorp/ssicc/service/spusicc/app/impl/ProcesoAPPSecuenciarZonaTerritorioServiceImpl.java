package biz.belcorp.ssicc.service.spusicc.app.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioDAO;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;

/**  
 * <p>
 * <a href="ProcesoAPPSecuenciarZonaTerritorioServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */
@Service("spusicc.procesoAPPSecuenciarZonaTerritorioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoAPPSecuenciarZonaTerritorioServiceImpl extends BaseService implements ProcesoAPPSecuenciarZonaTerritorioService {

	@Resource(name="spusicc.procesoAPPSecuenciarZonaTerritorioDAO")
	private ProcesoAPPSecuenciarZonaTerritorioDAO procesoAPPSecuenciarZonaTerritorioDAO;
			


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPPSecuenciarZonaTerritorioService#getRegionZonaSecuenciarList(java.util.Map)
	 */
	public List getRegionZonaSecuenciarList(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getRegionZonaSecuenciarList(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPPSecuenciarZonaTerritorioService#executeResetearSecuencia(java.util.List)
	 */
	public void executeResetearSecuencia(List list, String oidPais, String codUsuario){
		for (int i = 0; i < list.size(); i++) {
			Map criteria = new HashMap();
			criteria = (Map)list.get(i);
			criteria.put("oidPais", oidPais);
			criteria.put("usuario", codUsuario);

			String mensajeError = "-1";
			criteria.put("mensajeError", mensajeError);
			mensajeError = procesoAPPSecuenciarZonaTerritorioDAO.executeResetearSecuencia(criteria);

			if(!mensajeError.equals("-1"))
				throw new ServiceException("Se trato de asignar una secuencia que ya existe a la zona "+criteria.get("codigoZona").toString());					
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getTerritoriosSecuenciarList(java.util.Map)
	 */
	public List getTerritoriosSecuenciarList(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getTerritoriosSecuenciarList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getEmailSecuenciarList(java.util.Map)
	 */
	public List getEmailSecuenciarList(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getEmailSecuenciarList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#grabarSecuenciacionZonas(java.util.List, java.lang.String[])
	 */
	public void grabarSecuenciacionZonas(List lista, String[] secuencias, String oidPais, String codUsuario){
		for (int i = 0; i < lista.size(); i++) {
			Map criteria = (Map)lista.get(i);
			criteria.put("numeroSecuencia", secuencias[i]);
			criteria.put("oidPais", oidPais);
			criteria.put("usuario", codUsuario);

			if (!secuencias[i].equals(""))
				procesoAPPSecuenciarZonaTerritorioDAO.grabarSecuenciacionZonas(criteria);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getZonasSinSecuenciarList()
	 */
	public List getZonasSinSecuenciarList(){
		return procesoAPPSecuenciarZonaTerritorioDAO.getZonasSinSecuenciarList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getTerritoriosSinSecuenciarList()
	 */
	public List getTerritoriosSinSecuenciarList(){
		return procesoAPPSecuenciarZonaTerritorioDAO.getTerritoriosSinSecuenciarList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#executeResetearSecuenciaTerritorios(java.util.List)
	 */
	public void executeResetearSecuenciaTerritorios(List l){
		for (int i = 0; i < l.size(); i++) {
			Map criteria = new HashMap();
			criteria = (Map)l.get(i);
			procesoAPPSecuenciarZonaTerritorioDAO.executeResetearSecuenciaTerritorios(criteria);
		}	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#grabarSecuenciacionTerritorios(java.util.List, java.lang.String[])
	 */
	public void grabarSecuenciacionTerritorios(List lista, String[] secuencias, String codUsuario, String oidPais){
		for (int i = 0; i < lista.size(); i++) {
			Map criteria = (Map)lista.get(i);

			criteria.put("numeroSecuencia", secuencias[i]);
			criteria.put("oidPais", oidPais);
			criteria.put("usuario", codUsuario);

			if (!secuencias[i].equals(""))
				procesoAPPSecuenciarZonaTerritorioDAO.grabarSecuenciacionTerritorios(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#deleteRutasTerri()
	 */
	public void deleteRutasTerri(){
		procesoAPPSecuenciarZonaTerritorioDAO.deleteRutasTerri();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getCantidadSecuenciasExistentes(java.util.Map)
	 */
	public List getCantidadSecuenciasExistentes(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getCantidadSecuenciasExistentes(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#executeResetearSecuenciaTerritorios2(java.util.List)
	 */
	public String executeResetearSecuenciaTerritorios2(List l, String codUsuario){
		String valor = "false";
		int contador = 0;
		String codZona = "";
		Map map = new HashMap();

		for (int i = 0; i < l.size(); i++) {
			contador = contador + 1;
			Map criteria = new HashMap();
			criteria = (Map)l.get(i);
			codZona = (String)criteria.get("codigoZona");
			//criteria.put("usuario", codUsuario);

			//procesoAPPSecuenciarZonaTerritorioDAO.executeResetearSecuenciaTerritorios2(criteria);
		}

		map.put("usuario", codUsuario);
		map.put("codigoZona", codZona);
		procesoAPPSecuenciarZonaTerritorioDAO.executeResetearSecuenciaTerritorios2(map);
		
		if (contador==l.size()){
			valor = "true";
		}

		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getAuditoriaList(java.util.Map)
	 */
	public List getAuditoriaList(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getAuditoriaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#insertaAuditoria(java.util.Map)
	 */
	public void insertaAuditoria(Map criteria){
		procesoAPPSecuenciarZonaTerritorioDAO.insertaAuditoria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getSecuenciaZonasList(java.util.Map)
	 */
	public List getSecuenciaZonasList(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getSecuenciaZonasList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#getSecuenciaTerritorioList(java.util.Map)
	 */
	public List getSecuenciaTerritorioList(Map criteria){
		return procesoAPPSecuenciarZonaTerritorioDAO.getSecuenciaTerritorioList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPSecuenciarZonaTerritorioService#executeProcesoSecuenciarZonaTerritorio(java.util.Map)
	 */
	public void executeProcesoSecuenciarZonaTerritorio(Map criteria) {
		procesoAPPSecuenciarZonaTerritorioDAO.executeProcesoSecuenciarZonaTerritorio(criteria);
	}
	
}
