package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccesoRolSTO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaValidaciones;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;

@Service("spusicc.procesoSTOLevantamientoErroresValidacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOLevantamientoErroresValidacionServiceImpl extends BaseService implements ProcesoSTOLevantamientoErroresValidacionService {
	
	@Resource(name="spusicc.procesoSTOLevantamientoErroresValidacionDAO")
	ProcesoSTOLevantamientoErroresValidacionDAO procesoSTOLevantamientoErroresValidacionDAO;
	
	@Resource(name="spusicc.procesoSTODAO")
	private ProcesoSTODAO procesoSTODAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getLevantamientoErroresValidacionList(java.util.Map)
	 */
	public List getLevantamientoErroresValidacionList(Map params) {
		return procesoSTOLevantamientoErroresValidacionDAO.getLevantamientoErroresValidacionList(params);
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getValidacionesByDocumento(java.util.Map)
	 */
	public List getValidacionesByDocumento(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getValidacionesByDocumento(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getGestionDocumentoList(java.util.Map)
	 */
	public List getGestionDocumentoList(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getGestionDocumentoList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getAccionesGestionSTO(java.util.Map)
	 */
	public List getAccionesGestionSTO(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getAccionesGestionSTO(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#updateAprobarDocumentosSTO(java.util.Map)
	 */
	public void updateAprobarDocumentosSTO(Map params){
		procesoSTOLevantamientoErroresValidacionDAO.updateAprobarDocumentosSTO(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#updateDesaprobarDocumentosSTO(java.util.Map)
	 */
	public void updateDesaprobarDocumentosSTO(Map params){
		procesoSTOLevantamientoErroresValidacionDAO.updateDesaprobarDocumentosSTO(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#updateRechazarDocumentosSTO(java.util.Map)
	 */
	public void updateRechazarDocumentosSTO(Map params){
		procesoSTOLevantamientoErroresValidacionDAO.updateRechazarDocumentosSTO(params);
		if(params.get("indicadorEmail").equals("1")){
			procesoSTOLevantamientoErroresValidacionDAO.insertRechazoEnvioMailSTO(params);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getMensajesxAccionSTO(java.util.Map)
	 */
	public String getMensajesxAccionSTO(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getMensajesxAccionSTO(params);
	}
	
	public List getConsultaValidacionesList(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getConsultaValidacionesList(params);
	}

	
	public List getListaHoras(Map params) {
		
		return procesoSTOLevantamientoErroresValidacionDAO.getListaHoras(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getCuponesRechazoSello(java.util.Map)
	 */
	public List getCuponesRechazoSello (Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getCuponesRechazoSello(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#bloqueoCuponSTO(java.util.Map)
	 */
	public void bloqueoCuponSTO(Map params){
		procesoSTOLevantamientoErroresValidacionDAO.bloqueoCuponSTO(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#desbloqueoCuponSTO(java.util.Map)
	 */
	public void desbloqueoCuponSTO(Map params){
		procesoSTOLevantamientoErroresValidacionDAO.desbloqueoCuponSTO(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getAccionesByRol(java.util.Map)
	 */
	public List getAccionesByRol(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getAccionesByRol(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#insertRol(java.util.List, java.lang.String, java.lang.String)
	 */
	public void insertRol(List accesoList, String rol, String usuario){
	
		 if (accesoList != null) {
	            Iterator i = accesoList.iterator();
	            while (i.hasNext()) {
	            	AccesoRolSTO acceso = (AccesoRolSTO) i.next();
	                acceso.setCodigoAccion(StringUtils.isBlank(acceso.getCodigoAccion()) ? "" : acceso.getCodigoAccion());
	            	
	            	Map criteria = new HashMap();
	            	
	            	criteria.put("codigoAccion",acceso.getCodigoAccion());
	            	criteria.put("codigoDocumento",acceso.getCodigoTipoDocumento());
	            	criteria.put("codigoRol",rol);
	            	criteria.put("usuario",usuario);
	            	criteria.put("codigoPais",acceso.getCodigoPais());
	            	criteria.put("estado",acceso.getEstadoAccion());
	            	
	            	if(acceso.getCodigoAccion().compareToIgnoreCase("")==0){
	            		
		            	if(procesoSTOLevantamientoErroresValidacionDAO.getCodigoRolDocumento(criteria)==null){
		            		if(acceso.getEstadoAccion().compareToIgnoreCase("1")==0){
		            			procesoSTOLevantamientoErroresValidacionDAO.insertRolDocumento(criteria);
			            		
		            		}
		            		
		            	}
		            	else{
		            		procesoSTOLevantamientoErroresValidacionDAO.updateRolDocumento(criteria);
		            		
		            	}
	            	}
	            	else{
	            		if(procesoSTOLevantamientoErroresValidacionDAO.getCodigoRolDocumentoAccion(criteria)==null){
	            			if(acceso.getEstadoAccion().compareToIgnoreCase("1")==0){
	            				procesoSTOLevantamientoErroresValidacionDAO.insertRolDocumentoAccion(criteria);
	            			}
		            		
		            	}
		            	else{
		            		procesoSTOLevantamientoErroresValidacionDAO.updateRolDocumentoAccion(criteria);
		            	}
	            	}
	            }
	        }
		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getConsultaValidacionesTemporalList(java.util.Map)
	 */
	public List getConsultaValidacionesTemporalList(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getConsultaValidacionesTemporalList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#executeEliminarPedidosOrdenCompra(java.lang.String[], java.util.Map)
	 */
	public void executeEliminarPedidosOrdenCompra(String[] selectedItems, Map filter){
		Map criteria = new HashMap();
		List listaConsulta = procesoSTOLevantamientoErroresValidacionDAO.getConsultaValidacionesTemporalList(filter);
		if(selectedItems == null){
			selectedItems = new String[listaConsulta.size()];
			for (int i = 0; i < listaConsulta.size(); i++) {
				ConsultaValidaciones cv = (ConsultaValidaciones)listaConsulta.get(i);
				//--------------------
				String auxTipoDoc = cv.getCodigoTipoDocumento() != null ? cv.getCodigoTipoDocumento() : "";
				String auxCodPais= cv.getCodigoPais() != null ? cv.getCodigoPais() : "";
				String auxFecha= cv.getFecha() != null ? cv.getFecha() : "";
				String auxFechaMod= cv.getFechaModificacion() != null ? cv.getFechaModificacion() : "";
				String auxLote= cv.getLote() != null ? cv.getLote() : "";
				String auxCodRegion= cv.getCodRegion() != null ? cv.getCodRegion() : "";
				String auxZona= cv.getCodZona() != null ? cv.getCodZona() : "";
				String auxCliente= cv.getCliente() != null ? cv.getCliente() : "";
				String auxPeriodo= cv.getCodigoPeriodo() != null ? cv.getCodigoPeriodo() : "";
				String auxOrigen= cv.getOrigen() != null ? cv.getOrigen() : "";
				//--------------------
				String str = auxTipoDoc+"| "+
				             auxCodPais+"| "+
				             auxFecha+"| "+
				             auxFechaMod+"| "+
				             auxLote+"| "+
				             auxCodRegion+"| "+
				             auxZona+"| "+
				             auxCliente+"| "+
				             auxPeriodo+"| "+
				             auxOrigen;
				selectedItems[i]=str;
			}
		}
		criteria.put("codigoUsuario", filter.get("codigoUsuario"));
		for (int i = 0; i < selectedItems.length; i++) {
    		String codigoTipoDocumento = null;
    		try {codigoTipoDocumento = StringUtils.split(selectedItems[i], "|")[0].trim();}catch (Exception e) {}  
    		criteria.put("codigoTipoDocumento", codigoTipoDocumento);
    		String codigoPais = null;
    		try {codigoPais = StringUtils.split(selectedItems[i], "|")[1].trim();} catch (Exception e) {}
    		criteria.put("codigoPais", codigoPais);
    		String fecha = null;
    		try {fecha = StringUtils.split(selectedItems[i], "|")[2].trim();} catch (Exception e) {}
    		criteria.put("fecha", fecha);
    		String fechaModificacion = null;
    		try {fechaModificacion = StringUtils.split(selectedItems[i], "|")[3].trim();} catch (Exception e) {}
    		criteria.put("fechaModificacion", fechaModificacion);
    		String lote = null;
    		try {lote = StringUtils.split(selectedItems[i], "|")[4].trim();} catch (Exception e) {}
    		criteria.put("lote", lote);
    		String codRegion = null;
    		try {codRegion = StringUtils.split(selectedItems[i], "|")[5].trim();} catch (Exception e) {}
    		criteria.put("codRegion", codRegion);
    		String codZona = null;
    		try {codZona = StringUtils.split(selectedItems[i], "|")[6].trim();} catch (Exception e) {}
    		criteria.put("codZona", codZona);
    		String cliente = null;
    		try {cliente = StringUtils.split(selectedItems[i], "|")[7].trim();} catch (Exception e) {}
    		criteria.put("cliente", cliente);
    		String codigoPeriodo = null;
    		try {codigoPeriodo = StringUtils.split(selectedItems[i], "|")[8].trim();} catch (Exception e) {}
    		criteria.put("codigoPeriodo", codigoPeriodo);
    		String origen = null;
    		try {origen = StringUtils.split(selectedItems[i], "|")[9].trim();} catch (Exception e) {}
    		criteria.put("origen", origen);
    		procesoSTOLevantamientoErroresValidacionDAO.executeEliminarPedidosOrdenCompra(criteria);
		}        			
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getNumeroRegistrosSTO(java.util.Map)
	 */
	public String getNumeroRegistrosSTO(Map criteria){
		return procesoSTOLevantamientoErroresValidacionDAO.getNumeroRegistrosSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getFechaFinProceso(java.util.Map)
	 */
	public String getFechaFinProceso(Map criteria){
		return procesoSTOLevantamientoErroresValidacionDAO.getFechaFinProceso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getReporteTiposDocumentosSTO(java.util.Map)
	 */
	public List getReporteTiposDocumentosSTO(Map criteria){
		return procesoSTOLevantamientoErroresValidacionDAO.getReporteTiposDocumentosSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getReporteSTO(java.util.Map)
	 */
	public List getReporteSTO(Map criteria){
		return procesoSTOLevantamientoErroresValidacionDAO.getReporteSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getConsultaCDRTemporalList(java.util.Map)
	 */
	public List getConsultaCDRTemporalList(Map params){
		return procesoSTOLevantamientoErroresValidacionDAO.getConsultaCDRTemporalList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getOrigenSTOByTipoDocumento(java.util.Map)
	 */
	public List getOrigenSTOByTipoDocumento(Map params){
		return procesoSTODAO.getOrigenSTOByTipoDocumento(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getZonaArriboSTOByTipoDocumento(java.util.Map)
	 */
	public List getZonaArriboSTOByTipoDocumento(Map params){
		return procesoSTODAO.getZonaArriboSTOByTipoDocumento(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLevantamientoErroresValidacionService#getReclamosEliminados(java.util.Map)
	 */
	public List getReclamosEliminados(Map params) {
		return procesoSTODAO.getReclamosEliminados(params);
	}
}
