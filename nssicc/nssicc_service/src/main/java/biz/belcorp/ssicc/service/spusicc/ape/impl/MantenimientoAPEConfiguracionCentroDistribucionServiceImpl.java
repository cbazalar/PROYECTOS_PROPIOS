package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionCentroDistribucionDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CentroDistribucion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionCentroDistribucionService;
/**
 *  
 * <p>
 * <a href="MantenimientoAPEConfiguracionCentroDistribucionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEConfiguracionCentroDistribucionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEConfiguracionCentroDistribucionServiceImpl extends BaseService implements MantenimientoAPEConfiguracionCentroDistribucionService{
	
	@Resource(name="spusicc.mantenimientoAPEConfiguracionCentroDistribucionDAO")
	private MantenimientoAPEConfiguracionCentroDistribucionDAO mantenimientoAPEConfiguracionCentroDistribucionDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getCodigoCentroDistribucionList(java.util.Map)
	 */
	public List getCodigoCentroDistribucionList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getCodigoCentroDistribucionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getDescripcionCentroDistribucionList(java.util.Map)
	 */
	public List getDescripcionCentroDistribucionList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getDescripcionCentroDistribucionList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getCentroDistribucionList(java.util.Map)
	 */
	public List getCentroDistribucionList(Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getCentroDistribucionList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getNivelOutsourcingList(java.util.Map)
	 */
	public List getNivelOutsourcingList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getNivelOutsourcingList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOrdenList(java.util.Map)
	 */
	public List getOrdenList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOrdenList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOrdenListaPicadoList(java.util.Map)
	 */
	public List getOrdenListaPicadoList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOrdenListaPicadoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getAgrupacionList(java.util.Map)
	 */
	public List getAgrupacionList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getAgrupacionList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getVisualizacionChequeoList(java.util.Map)
	 */
	public List getVisualizacionChequeoList(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getVisualizacionChequeoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getCentroDistribucionObject(java.util.Map)
	 */
	public CentroDistribucion getCentroDistribucionObject(Map criteria) {
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getCentroDistribucionObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#insertCentroDistribucion(java.util.Map)
	 */
	public void insertCentroDistribucion(Map criteria) {
		mantenimientoAPEConfiguracionCentroDistribucionDAO.insertCentroDistribucion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#updateCentroDistribucion(java.util.Map)
	 */
	public void updateCentroDistribucion(Map criteria) {
		mantenimientoAPEConfiguracionCentroDistribucionDAO.updateCentroDistribucion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOidNivelOutsourcing(java.util.Map)
	 */
	public String getOidNivelOutsourcing( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOidNivelOutsourcing(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOidOrdenListaPicado(java.util.Map)
	 */
	public String getOidOrdenListaPicado( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOidOrdenListaPicado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOidOrden(java.util.Map)
	 */
	public String getOidOrden( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOidOrden(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOidAgrupacionAFP(java.util.Map)
	 */
	public String getOidAgrupacionAFP( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOidAgrupacionAFP(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOidOrdenVisualizacion(java.util.Map)
	 */
	public String getOidOrdenVisualizacion( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOidOrdenVisualizacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getOidAgrupacionOLAS(java.util.Map)
	 */
	public String getOidAgrupacionOLAS( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getOidAgrupacionOLAS(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#insertIdiomasAPE(java.util.Map)
	 */
	public void insertIdiomasAPE(Map criteria) {
		mantenimientoAPEConfiguracionCentroDistribucionDAO.insertIdiomasAPE(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#updateIdiomasAPE(java.util.Map)
	 */
	public void updateIdiomasAPE(Map criteria) {
		mantenimientoAPEConfiguracionCentroDistribucionDAO.updateIdiomasAPE(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getNumAtributoIdioma(java.util.Map)
	 */
	public String getNumAtributoIdioma( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getNumAtributoIdioma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getMaximoOidCentroDistribucion(java.util.Map)
	 */
	public String getMaximoOidCentroDistribucion( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getMaximoOidCentroDistribucion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getExisteCentroDefault(java.util.Map)
	 */
	public int getExisteCentroDefault( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getExisteCentroDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#getValidaCentroDefault(java.util.Map)
	 */
	public String getDescripcionCentroDefault( Map criteria){
		return mantenimientoAPEConfiguracionCentroDistribucionDAO.getDescripcionCentroDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionCentroDistribucionService#deleteCentroDistribucion(java.util.Map, java.lang.String[])
	 */
	public String deleteCentroDistribucion(Map criteria, String[] items) {
		
		String vNomTabla = new String();
		String error= new String();
		//String codEliminado = new String();
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidCentro", StringUtils.split(id, "|")[0]);
			criteria.put("codCentro", StringUtils.split(id, "|")[1]);
			criteria.put("nombreTabla", Constants.TABLA_CENTRO_DISTRIBUCION);
			//codEliminado  = (String)criteria.get("codCentro");
			
			//Antes de eliminar se valida que no existan registros en las tablas hijas
			mantenimientoAPEConfiguracionCentroDistribucionDAO.executeValidarRegistrosCentroDistribucion(criteria);
			error = (String) criteria.get("valError");
			
			if (Constants.NUMERO_CERO.equals(error)){
				mantenimientoAPEConfiguracionCentroDistribucionDAO.deleteCentroDistribucion(criteria);
				
				//Se elimina de la tabla de idoma
				mantenimientoAPEConfiguracionCentroDistribucionDAO.deleteIdiomaAPE(criteria);
				vNomTabla ="1";
			}else{
				vNomTabla = (String) criteria.get("nomTabla");
				break;
			}
		}
		return vNomTabla;
	}
}