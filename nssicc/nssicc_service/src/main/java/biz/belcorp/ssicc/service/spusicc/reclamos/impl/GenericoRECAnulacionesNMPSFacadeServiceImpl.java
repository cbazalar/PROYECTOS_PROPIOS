package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.reclamos.GenericoRECAnulacionesNMPSORAServerDAO;
import biz.belcorp.ssicc.service.spusicc.reclamos.GenericoRECAnulacionesNMPSFacadeService;

/**
 * 
 * @author Carlos Bazalar
 * 
 */
@Service("spusicc.genericoRECAnulacionesNMPSFacadeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GenericoRECAnulacionesNMPSFacadeServiceImpl implements GenericoRECAnulacionesNMPSFacadeService {

	private Map genericoImplementations;
	
	@Resource(name="spusicc.genericoRECAnulacionesNMPSORAServerDAO")
	protected GenericoRECAnulacionesNMPSORAServerDAO genericoImpl;
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.service.gen.GenericoOCRComercialFacadeService#getListProcesoCarga(java.lang.String, java.util.Map)
	 */
	public void excuteSpWCSAPE(ConexionOCRWrapper conexionOCRWrapper, Connection conexion, Map params) throws Exception{
		genericoImpl = (GenericoRECAnulacionesNMPSORAServerDAO) getGenericoImplementations(conexionOCRWrapper, params);
		genericoImpl.excuteSpWCSAPE(conexion, params);
	}
	
	public Connection obtenerConexion (ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoRECAnulacionesNMPSORAServerDAO) getGenericoImplementations(conexionOCRWrapper, params);
		return genericoImpl.obtenerConexion(conexionOCRWrapper);
	}
	
	public void cerrarConexion(ConexionOCRWrapper conexionOCRWrapper, Connection conexion, Map params) throws Exception{
		genericoImpl = (GenericoRECAnulacionesNMPSORAServerDAO) getGenericoImplementations(conexionOCRWrapper, params);
		genericoImpl.cerrarConexion(conexion);
	}

	/**
	 * @param codigo
	 * @param params
	 * @return
	 */
	public GenericoRECAnulacionesNMPSORAServerDAO getGenericoImplementations(ConexionOCRWrapper conexionOCRWrapper, Map params) {
		log.debug("ini getGenericoImplementations");
		
		/* Colocando el pais */
		if(params==null) params = new HashMap();
		params.remove("codigoPais");
		params.put("codigoPais", conexionOCRWrapper.getCodigoPais());
		
		/* obteniendo DAO */
		genericoImpl=(GenericoRECAnulacionesNMPSORAServerDAO)genericoImplementations.get(conexionOCRWrapper.getTipoConexionExterna());
		return genericoImpl;
	}
			
}
