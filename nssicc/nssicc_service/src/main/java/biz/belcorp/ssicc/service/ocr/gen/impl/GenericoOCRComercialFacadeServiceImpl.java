package biz.belcorp.ssicc.service.ocr.gen.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.ocr.gen.GenericoOCRComercialDAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.service.ocr.gen.GenericoOCRComercialFacadeService;

/**
 * 
 * @author Carlos Bazalar
 * 
 */
public class GenericoOCRComercialFacadeServiceImpl implements GenericoOCRComercialFacadeService {

	private Map genericoImplementations;
	
	protected GenericoOCRComercialDAO genericoImpl;
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.service.gen.GenericoOCRComercialFacadeService#getListProcesoCarga(java.lang.String, java.util.Map)
	 */
	public List getListProcesoCarga(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoOCRComercialDAO) getGenericoImplementations(conexionOCRWrapper, params);
		return genericoImpl.getListProcesoCarga(conexionOCRWrapper, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.service.gen.GenericoOCRComercialFacadeService#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListProcesoCargaCupon(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoOCRComercialDAO) getGenericoImplementations(conexionOCRWrapper, params);
		return genericoImpl.getListProcesoCargaCupon(conexionOCRWrapper, params);
	}

	/**
	 * @param codigo
	 * @param params
	 * @return
	 */
	public GenericoOCRComercialDAO getGenericoImplementations(ConexionOCRWrapper conexionOCRWrapper, Map params) {
		log.debug("ini getGenericoImplementations");
		
		/* Colocando el pais */
		if(params==null) params = new HashMap();
		params.remove("codigoPais");
		params.put("codigoPais", conexionOCRWrapper.getCodigoPais());
		
		/* obteniendo DAO */
		genericoImpl=(GenericoOCRComercialDAO)genericoImplementations.get(conexionOCRWrapper.getTipoConexionExterna());
		return genericoImpl;
	}
	
	
	public void setGenericoImplementations(Map genericoImplementations) {
		this.genericoImplementations = genericoImplementations;
	}

	
			
}
