package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarVersionesProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.VersionProducto;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEAsignarVersionesProductosService;
/**
 *  
 * <p>
 * <a href="MantenimientoAPEAsignarVersionesProductosServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEAsignarVersionesProductosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEAsignarVersionesProductosServiceImpl extends BaseService implements MantenimientoAPEAsignarVersionesProductosService{
	
	@Resource(name="spusicc.mantenimientoAPEAsignarVersionesProductosDAO")
	private MantenimientoAPEAsignarVersionesProductosDAO mantenimientoAPEAsignarVersionesProductosDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getVersionesList(java.util.Map)
	 */
	public List getVersionesList(Map criteria) {
		return mantenimientoAPEAsignarVersionesProductosDAO.getVersionesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getVersionesAsignadosList(java.util.Map)
	 */
	public List getVersionesAsignadosList(Map criteria) {
		return mantenimientoAPEAsignarVersionesProductosDAO.getVersionesAsignadosList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getOidPeriodobyMarcaCanal(java.util.Map)
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getOidPeriodobyMarcaCanal(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getMapaZonaVersionList(java.util.Map)
	 */
	public List getMapaZonaVersionList(Map criteria) {
		return mantenimientoAPEAsignarVersionesProductosDAO.getMapaZonaVersionList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getVersionProductoObject(java.util.Map)
	 */
	public VersionProducto getVersionProductoObject(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getVersionProductoObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getMapaZonaComboList(java.util.Map)
	 */
	public List getMapaZonaComboList(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getMapaZonaComboList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getCodigoMapaZona(java.util.Map)
	 */
	public String getCodigoMapaZona(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getCodigoMapaZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getExisteVersionPeriodoMapaCD(java.util.Map)
	 */
	public String getExisteVersionPeriodoMapaCD(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getExisteVersionPeriodoMapaCD(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getVerificaVersionActiva(java.util.Map)
	 */
	public String getVerificaVersionActiva(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getVerificaVersionActiva(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getOidProcesoAPEEstimado(java.util.Map)
	 */
	public String getOidProcesoAPEEstimado(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getOidProcesoAPEEstimado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getNextOidAsignacionProductoCab()
	 */
	public String getNextOidAsignacionProductoCab(){
		return mantenimientoAPEAsignarVersionesProductosDAO.getNextOidAsignacionProductoCab(); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#executeInsertVersionesDetalle(java.util.Map)
	 */
	public void executeInsertVersionesDetalle(Map criteria){
		mantenimientoAPEAsignarVersionesProductosDAO.executeInsertVersionesDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#insertVersionesCab(java.util.Map)
	 */
	public void insertVersionesCab(Map criteria){
		mantenimientoAPEAsignarVersionesProductosDAO.insertVersionesCab(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#desactivarVersionesActuales(java.util.Map)
	 */
	public void desactivarVersionesActuales(Map criteria){
		mantenimientoAPEAsignarVersionesProductosDAO.desactivarVersionesActuales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#updateVersiones(java.util.Map)
	 */
	public void updateVersiones(Map criteria){
		mantenimientoAPEAsignarVersionesProductosDAO.updateVersiones(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#deleteVersionesCab(java.util.Map)
	 */
	public boolean deleteVersionesCab (Map criteria, String[] items){
		
		for(int i = 0; i < items.length; i++){
			String id = items[i];
			criteria.put("oidAsignacion", StringUtils.split(id, "|")[0]);
			criteria.put("oidMapaCentro", StringUtils.split(id, "|")[1]);
			criteria.put("oidMapaZona", StringUtils.split(id, "|")[2]);
			criteria.put("oidLinea", StringUtils.split(id, "|")[3]);
			criteria.put("oidPeriodo",  StringUtils.split(id, "|")[4]);
			
			//Se verifica que no existan Numero de Anaqueles en la Lista de Picado
			String validaAnaquel = mantenimientoAPEAsignarVersionesProductosDAO.validaVersionAnaquelListaPicado(criteria);
			if (validaAnaquel.equals(Constants.NUMERO_CERO)){
				//Se eliminan los detalles
				mantenimientoAPEAsignarVersionesProductosDAO.deleteVersionesDet(criteria);
				//Se elimina la caebecera
				mantenimientoAPEAsignarVersionesProductosDAO.deleteVersionesCab(criteria);
			}else{
				return false;
			}	
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getOidMapaCDbyCod(java.util.Map)
	 */
	public String getOidMapaCDbyCod(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getOidMapaCDbyCod(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getOidLineaVersion(java.util.Map)
	 */
	public String getOidLineaVersion(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getOidLineaVersion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getExisteOidPeriodoCanalMarca(java.util.Map)
	 */
	public int getExisteOidPeriodoCanalMarca(Map criteria){
		return mantenimientoAPEAsignarVersionesProductosDAO.getExisteOidPeriodoCanalMarca(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarVersionesProductosService#getVersionActiva(java.util.Map)
	 */
	public String getVersionActiva(Map criteria) {
		return this.mantenimientoAPEAsignarVersionesProductosDAO.getVersionActiva(criteria);
	}
	
}