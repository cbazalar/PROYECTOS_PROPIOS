package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarNotasDebitoCabeceraPeruServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
@Service("sisicc.interfazIMPEnviarNotasDebitoCabeceraPeruService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarNotasDebitoCabeceraPeruServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		String codigoPeriodo = (String) params.get("codigoPeriodo");
		String fechaFacturacion = (String)params.get("fechaFacturacion");
		if (StringUtils.isNotBlank(codigoPeriodo) && StringUtils.isNotBlank(fechaFacturacion))
			interfazIMPDAO.executeInterfazIMPEnviarNotasDebitoCabeceraPeru(params);
		else
			this.log.info("No se ejecuto proceso ppr valores nulos en los parametros de Periodo o Fecha de Facturacin");
	}
    
}
