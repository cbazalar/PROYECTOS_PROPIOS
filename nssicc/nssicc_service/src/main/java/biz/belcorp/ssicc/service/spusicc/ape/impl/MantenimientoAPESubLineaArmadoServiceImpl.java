package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPESubLineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.SubLineaArmado;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPESubLineaArmadoService;
/**
 *  
 * <p>
 * <a href="MantenimientoAPESubLineaArmadoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPESubLineaArmadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPESubLineaArmadoServiceImpl extends BaseService implements MantenimientoAPESubLineaArmadoService{
	
	@Resource(name="spusicc.mantenimientoAPESubLineaArmadoDAO")
	private MantenimientoAPESubLineaArmadoDAO mantenimientoAPESubLineaArmadoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getLineaArmadobyOidCentro(java.util.Map)
	 */
	public List getLineaArmadobyOidCentro(Map criteria) {
		return mantenimientoAPESubLineaArmadoDAO.getLineaArmadobyOidCentro(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getCodLineaArmadaDefecto(java.util.Map)
	 */
	public String getCodLineaArmadaDefecto(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getCodLineaArmadaDefecto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getSistemaPicadoList(java.util.Map)
	 */
	public List getSistemaPicadoList(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getSistemaPicadoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getCodigoImpresoraList()
	 */
	public List getCodigoImpresoraList(){
		return mantenimientoAPESubLineaArmadoDAO.getCodigoImpresoraList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getSubLineaArmadoCabec(java.util.Map)
	 */
	public List getSubLineaArmadoCabec(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getSubLineaArmadoCabec(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getOidLineaArmadobyCodigo(java.util.Map)
	 */
	public String getOidLineaArmadobyCodigo(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getOidLineaArmadobyCodigo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#deleteSubLineaArmado(java.util.Map, java.lang.String[])
	 */
	public String deleteSubLineaArmado(Map criteria, String[] items) {
		
		String vNomTabla = new String();
		String error= new String();
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidCentro", StringUtils.split(id, "|")[0]);
			criteria.put("oidLinea", StringUtils.split(id, "|")[1]);
			criteria.put("oidSubLinea", StringUtils.split(id, "|")[2]);
			
			mantenimientoAPESubLineaArmadoDAO.deleteSubLineaArmado(criteria);
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
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getLineaArmadoComboList(java.util.Map)
	 */
	public List getLineaArmadoComboList(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getLineaArmadoComboList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getCodLineaArmadaDefectoList(java.util.Map)
	 */
	public String getCodLineaArmadaDefectoList(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getCodLineaArmadaDefectoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getSubLineaArmadoObject(java.util.Map)
	 */
	public SubLineaArmado getSubLineaArmadoObject(Map criteria) {
		return mantenimientoAPESubLineaArmadoDAO.getSubLineaArmadoObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getTipoCajaProductoList(java.util.Map)
	 */
	public List getTipoCajaProductoList(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getTipoCajaProductoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getTipoCajaSubLineaList(java.util.Map)
	 */
	public List getTipoCajaSubLineaList(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getTipoCajaSubLineaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getOidSubLineaArmadobyCodigo(java.util.Map)
	 */
	public String getOidSubLineaArmadobyCodigo(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getOidSubLineaArmadobyCodigo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#deleteTipoCaja(java.util.Map)
	 */
	public void deleteTipoCaja(Map criteria){
		mantenimientoAPESubLineaArmadoDAO.deleteTipoCaja(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getNextOidTipoCaja()
	 */
	public int getNextOidTipoCaja(){
		return mantenimientoAPESubLineaArmadoDAO.getNextOidTipoCaja();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getOidTipoCajaProducto(java.util.Map)
	 */
	public String getOidTipoCajaProducto(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getOidTipoCajaProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#insertTipoCaja(java.util.Map)
	 */
	public void insertTipoCaja(Map criteria){
		mantenimientoAPESubLineaArmadoDAO.insertTipoCaja(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#insertSubLineaArmado(java.util.Map)
	 */
	public void insertSubLineaArmado(Map criteria){
		mantenimientoAPESubLineaArmadoDAO.insertSubLineaArmado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#insertImpresoraSubLinea(java.util.Map)
	 */
	public void insertImpresoraSubLinea(Map criteria){
		mantenimientoAPESubLineaArmadoDAO.insertImpresoraSubLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getOidSistemaPicado(java.util.Map)
	 */
	public String getOidSistemaPicado(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getOidSistemaPicado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getNextOidSubLinea()
	 */
	public int getNextOidSubLinea(){
		return mantenimientoAPESubLineaArmadoDAO.getNextOidSubLinea();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getMaxCodSubLinesArmado()
	 */
	public int getMaxCodSubLinesArmado(){
		return mantenimientoAPESubLineaArmadoDAO.getMaxCodSubLinesArmado();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#updateSubLineaArmado(java.util.Map)
	 */
	public void updateSubLineaArmado(Map criteria) {
		mantenimientoAPESubLineaArmadoDAO.updateSubLineaArmado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getExisteLetraAnquelCD(java.util.Map)
	 */
	public int getExisteLetraAnquelCD(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getExisteLetraAnquelCD(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getOidCodigoImpresora(java.util.Map)
	 */
	public int getOidCodigoImpresora(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getOidCodigoImpresora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#deleteCodigoImpresora(java.util.Map)
	 */
	public void deleteCodigoImpresora(Map criteria){
		mantenimientoAPESubLineaArmadoDAO.deleteCodigoImpresora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPESubLineaArmadoService#getCodigoLineaArmadobyOid(java.util.Map)
	 */
	public String getCodigoLineaArmadobyOid(Map criteria){
		return mantenimientoAPESubLineaArmadoDAO.getCodigoLineaArmadobyOid(criteria);
	}
}