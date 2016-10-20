package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionDAO;

@Repository("spusicc.procesoSTOLevantamientoErroresValidacionDAO")
public class ProcesoSTOLevantamientoErroresValidacionDAOIbatis extends BaseDAOiBatis implements 
		ProcesoSTOLevantamientoErroresValidacionDAO{

	/**/
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getLevantamientoErroresValidacionList(java.util.Map)
	 */
	public List getLevantamientoErroresValidacionList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getLevantamientoErroresValidacionList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getValidacionesByDocumento(java.util.Map)
	 */
	public List getValidacionesByDocumento(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getValidacionesByDocumento", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getGestionDocumentoList(java.util.Map)
	 */
	public List getGestionDocumentoList(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getGestionDocumentoList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getAccionesGestionSTO(java.util.Map)
	 */
	public List getAccionesGestionSTO(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getAccionesGestionSTO", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#updateAprobarDocumentosSTO(java.util.Map)
	 */
	public void updateAprobarDocumentosSTO(Map params){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateAprobarDocumentosSTO", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#updateDesaprobarDocumentosSTO(java.util.Map)
	 */
	public void updateDesaprobarDocumentosSTO(Map params){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateDesaprobarDocumentosSTO", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#updateRechazarDocumentosSTO(java.util.Map)
	 */
	public void updateRechazarDocumentosSTO(Map params){
		
		String tipoDocumento = (String)params.get("codigoTipo");
		
		if (tipoDocumento!=null && Constants.STO_TIPO_DOCUMENTO_OCC.equals(tipoDocumento)) 
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateIndicadorRechazoOCC", params);		
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateRechazarDocumentosSTO", params);		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getMensajesxAccionSTO(java.util.Map)
	 */
	public String getMensajesxAccionSTO(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getMensajesxAccionSTO", params);
	}
	
	public List getConsultaValidacionesList(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaValidacionesList", params);
	}
	
	
	/**
	 * @param params
	 * @return
	 */
	public List getListaHoras (Map params){		
		String intervaloCarga =(String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getParametroSTO", params);
		return getListaHoras(Integer.parseInt(intervaloCarga));	
	}
	
	/**
	 * @param intervalo
	 * @return
	 */
	private List getListaHoras(int intervalo){

		ArrayList listaHora = new ArrayList();  
		int minutoActual = 0;
		
		while (minutoActual<1440) {
			String  formatoHora ="";
			Base element = new Base();
			
			int hora = minutoActual/60;
			int minuto =  minutoActual%60;
			
			if (hora<10)  formatoHora = formatoHora + "0";
			formatoHora = formatoHora +hora;
			formatoHora = formatoHora +":";
			
			if (minuto<10) formatoHora = formatoHora + "0";
			formatoHora = formatoHora +minuto;
			
			
			element.setCodigo(formatoHora);
			
			element.setDescripcion(formatoHora);
			listaHora.add(element);
			
			minutoActual =  minutoActual + intervalo;
			
		} 
		
		return listaHora;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getCuponesRechazoSello(java.util.Map)
	 */
	public List getCuponesRechazoSello(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCuponesRechazoSello", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#bloqueoCuponSTO(java.util.Map)
	 */
	public void bloqueoCuponSTO(Map params){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.bloqueoCuponSTO", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#desbloqueoCuponSTO(java.util.Map)
	 */
	public void desbloqueoCuponSTO(Map params){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.desbloqueoCuponSTO", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getMotivosRechazo(java.util.Map)
	 */
	public List getMotivosRechazo(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getMotivosRechazo", params);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getAccionesByRol(java.util.Map)
     */
    public List getAccionesByRol(Map params){
    	return  getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getAccionesByRol", params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getCodigoRolDocumento(java.util.Map)
     */
    public String getCodigoRolDocumento(Map params){
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoRolDocumento", params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getCodigoRolDocumentoAccion(java.util.Map)
     */
    public String getCodigoRolDocumentoAccion(Map params){
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoRolDocumentoAccion", params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#updateRolDocumento(java.util.Map)
     */
    public void updateRolDocumento(Map params){
    	getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateRolDocumento", params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#updateRolDocumentoAccion(java.util.Map)
     */
    public void updateRolDocumentoAccion(Map params){
    	getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateRolDocumentoAccion", params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#insertRolDocumento(java.util.Map)
     */
    public void insertRolDocumento(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertRolDocumento", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#insertRolDocumentoAccion(java.util.Map)
     */
    public void insertRolDocumentoAccion(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertRolDocumentoAccion", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#insertRechazoEnvioMailSTO(java.util.Map)
     */
    public void insertRechazoEnvioMailSTO(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertRechazoEnvioMailSTO", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getEstadosSTOByTipoDocumento(java.util.Map)
     */
    public List getEstadosSTOByTipoDocumento(Map params){
    	return  getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getEstadosSTOByTipoDocumento", params);
    }        
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getConsultaValidacionesTemporalList(java.util.Map)
     */
    public List getConsultaValidacionesTemporalList(Map params){    	
    	getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertTemporalEliminarPedidos", params);
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaValidacionesTemporalList", params);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#executeEliminarPedidosOrdenCompra(java.util.Map)
     */
    public void executeEliminarPedidosOrdenCompra(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeEliminarPedidosOrdenCompra", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getNumeroRegistrosSTO(java.util.Map)
     */
    public String getNumeroRegistrosSTO(Map criteria){
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getNumeroRegistrosSTO", criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getFechaFinProceso(java.util.Map)
     */
    public String getFechaFinProceso(Map criteria){
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getFechaFinProceso", criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getReporteTiposDocumentosSTO(java.util.Map)
     */
    public List getReporteTiposDocumentosSTO(Map criteria){
    	return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getReporteTiposDocumentosSTO", criteria);
    }
    
   
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getReporteSTO(java.util.Map)
     */
    public List getReporteSTO(Map criteria){
    	return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getReporteSTO", criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getConsultaCDRTemporalList(java.util.Map)
     */
    public List getConsultaCDRTemporalList(Map params){    	
    	getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertTemporalEliminarCDR", params);
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaCDRTemporalList", params);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getParametroRechazoByDocumento(java.util.Map)
     */
    public String getParametroRechazoByDocumento(Map params){
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getParametroRechazoByDocumento", params);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getNumeroRegistroMinMaxSTO(java.util.Map)
	 */
	public String getNumeroRegistroMinMaxSTO(Map params) {
		log.info("Entro a ProcesoSTOLevantamientoErroresValidacionDAOIbatis - getNumeroRegistroMinMaxSTO(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getNumeroRegistroMinMaxSTO", params);
		log.info("Salio a ProcesoSTOLevantamientoErroresValidacionDAOIbatis - getNumeroRegistroMinMaxSTO(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLevantamientoErroresValidacionDAO#getValidacionesExcepcionByDocumento(java.util.Map)
	 */
	public List getValidacionesExcepcionByDocumento(Map params) {		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getValidacionesExcepcionByDocumento", params);
	}
    
}
