package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPELineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.LineaArmado;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPELineaArmadoService;
/**
 *  
 * <p>
 * <a href="MantenimientoAPELineaArmadoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPELineaArmadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPELineaArmadoServiceImpl extends BaseService implements MantenimientoAPELineaArmadoService{
	
	@Resource(name="spusicc.mantenimientoAPELineaArmadoDAO")
	private MantenimientoAPELineaArmadoDAO mantenimientoAPELineaArmadoDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getCentroDistList(java.util.Map)
	 */
	public List getCentroDistList(Map criteria) {
		return mantenimientoAPELineaArmadoDAO.getCentroDistList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getLineaArmadoCabec(java.util.Map)
	 */
	public List getLineaArmadoCabec(Map criteria){
		return mantenimientoAPELineaArmadoDAO.getLineaArmadoCabec(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getOidCentroDistribucionPais(java.util.Map)
	 */
	public String getOidCentroDistribucionPais( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getOidCentroDistribucionPais(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#deleteLineaArmado(java.util.Map, java.lang.String[])
	 */
	public String deleteLineaArmado(Map criteria, String[] items) {
		
		String vNomTabla = new String();
		String error= new String();
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidCentro", StringUtils.split(id, "|")[0]);
			criteria.put("oidLinea", StringUtils.split(id, "|")[1]);
			criteria.put("codLinea", StringUtils.split(id, "|")[2]);
			
			mantenimientoAPELineaArmadoDAO.deleteLineaArmado(criteria);
			error = (String) criteria.get("valError");
			
			if (Constants.NUMERO_CERO.equals(error)){
				vNomTabla ="1";
			}else{
				vNomTabla = (String) criteria.get("nomTabla");
				break;
			}
		}
		return vNomTabla;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getCentroDistribucionDefecto(java.util.Map)
	 */
	public String getCentroDistribucionDefecto( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getCentroDistribucionDefecto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getProgramaCubicajeList(java.util.Map)
	 */
	public List getProgramaCubicajeList(Map criteria) {
		return mantenimientoAPELineaArmadoDAO.getProgramaCubicajeList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getPlataformaList(java.util.Map)
	 */
	public List getPlataformaList(Map criteria) {
		return mantenimientoAPELineaArmadoDAO.getPlataformaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getTipoSolicitudConsoList(java.util.Map)
	 */
	public List getTipoSolicitudConsoList(Map criteria) {
		return mantenimientoAPELineaArmadoDAO.getTipoSolicitudConsoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getLineaArmadoObject(java.util.Map)
	 */
	public LineaArmado getLineaArmadoObject(Map criteria) {
		return mantenimientoAPELineaArmadoDAO.getLineaArmadoObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getUsuarioAlarmaList(java.util.Map)
	 */
	public List getUsuarioAlarmaList(Map criteria) {
		return mantenimientoAPELineaArmadoDAO.getUsuarioAlarmaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getNextOidLinaeArmado(java.util.Map)
	 */
	public int getNextOidLinaeArmado( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getNextOidLinaeArmado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getMaxCodLinaeArmado(java.util.Map)
	 */
	public int getMaxCodLinaeArmado( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getMaxCodLinaeArmado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getOidProgCubicaje(java.util.Map)
	 */
	public int getOidProgCubicaje( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getOidProgCubicaje(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getOidPlataforma(java.util.Map)
	 */
	public int getOidPlataforma( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getOidPlataforma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getExisteLineaDefault(java.util.Map)
	 */
	public int getExisteLineaDefault( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getExisteLineaDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getDescripcionLineaDefault(java.util.Map)
	 */
	public String getDescripcionLineaDefault( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getDescripcionLineaDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#insertLineaArmado(java.util.Map)
	 */
	public void insertLineaArmado(Map criteria) {
		mantenimientoAPELineaArmadoDAO.insertLineaArmado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#insertUsuarioAlarma(java.util.Map)
	 */
	public void insertUsuarioAlarma(Map criteria) {
		mantenimientoAPELineaArmadoDAO.insertUsuarioAlarma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#insertTipoSolicitudLinea(java.util.Map)
	 */
	public void insertTipoSolicitudLinea(Map criteria, String[] codTipoSolicitud) {
		
		mantenimientoAPELineaArmadoDAO.deleteTipoSolicitudLinea(criteria);
		
		for (int ind=0; ind< codTipoSolicitud.length; ind++)
		{
		    criteria.put("oidTipoSolicitud", codTipoSolicitud[ind]);	
			mantenimientoAPELineaArmadoDAO.insertTipoSolicitudLinea(criteria);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#updateLineaArmado(java.util.Map)
	 */
	public void updateLineaArmado(Map criteria) {
		mantenimientoAPELineaArmadoDAO.updateLineaArmado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#deleteUsuarioAlarma(java.util.Map)
	 */
	public void deleteUsuarioAlarma(Map criteria){
		mantenimientoAPELineaArmadoDAO.deleteUsuarioAlarma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getNextOidUsuarioAlarma()
	 */
	public int getNextOidUsuarioAlarma(){
		return mantenimientoAPELineaArmadoDAO.getNextOidUsuarioAlarma();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getTipoSolicitudConsoSelecList(java.util.Map)
	 */
	public List getTipoSolicitudConsoSelecList(Map criteria){
		return mantenimientoAPELineaArmadoDAO.getTipoSolicitudConsoSelecList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getCodigoLineaDefault(java.util.Map)
	 */
	public String getCodigoLineaDefault( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getCodigoLineaDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getPeriodoActual(java.util.Map)
	 */
	public String getPeriodoActual( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getPeriodoActual(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getPeriodoAnterior(java.util.Map)
	 */
	public String getPeriodoAnterior( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getPeriodoAnterior(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getDescCentroDistribucion(java.util.Map)
	 */
	public String getDescCentroDistribucion( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getDescCentroDistribucion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getDescLineaArmado(java.util.Map)
	 */
	public String getDescLineaArmado( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getDescLineaArmado(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getDescCanal(java.util.Map)
	 */
	public String getDescCanal( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getDescCanal(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getDescMarca(java.util.Map)
	 */
	public String getDescMarca( Map criteria){
		return mantenimientoAPELineaArmadoDAO.getDescMarca(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getTotalesMovimientoList(java.util.Map)
	 */
	public List getTotalesMovimientoList(Map criteria){
		return mantenimientoAPELineaArmadoDAO.getTotalesMovimientoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#validaExistePeriodobyMarcaCanal(java.util.Map)
	 */
	public int validaExistePeriodobyMarcaCanal(Map criteria){
		return mantenimientoAPELineaArmadoDAO.validaExistePeriodobyMarcaCanal(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getZonasList(java.util.Map)
	 */
	public List getZonasList(Map criteria){
		return mantenimientoAPELineaArmadoDAO.getZonasList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPELineaArmadoService#getTipoSolicitudList(java.util.Map)
	 */
	public List getTipoSolicitudList(Map criteria){
		return mantenimientoAPELineaArmadoDAO.getTipoSolicitudList(criteria);
	}
}