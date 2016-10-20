/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scdf.SubgerenciaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIEnviarSubGerenciaVentasServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIEnviarSubGerenciaVentasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIEnviarSubGerenciaVentasServiceImpl extends
		BaseInterfazSalidaAbstractService {

	@Resource(name="scdf.subgerenciaDAO")
	SubgerenciaDAO subgerenciaDAO ;
	
	
	public SubgerenciaDAO getSubgerenciaDAO() {
		return subgerenciaDAO;
	}


	public void setSubgerenciaDAO(SubgerenciaDAO subgerenciaDAO) {
		this.subgerenciaDAO = subgerenciaDAO;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			String codigoPais = (String)queryParams.get("codigoPais");
			log.debug("codigoPais "+codigoPais);
			list = this.subgerenciaDAO.getSubgerenciaMapByPais(codigoPais);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return list;
	}
}
