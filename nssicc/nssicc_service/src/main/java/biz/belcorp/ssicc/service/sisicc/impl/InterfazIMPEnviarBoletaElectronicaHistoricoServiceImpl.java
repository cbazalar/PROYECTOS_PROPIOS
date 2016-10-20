package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarBoletaElectronicaHistoricoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("sisicc.interfazIMPEnviarBoletaElectronicaHistoricoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarBoletaElectronicaHistoricoServiceImpl extends BaseInterfazProcesoAbstractService {
	
	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		
		String codigoPeriodo = (String) params.get("codigoPeriodo");
		String fechaFacturacion = (String)params.get("fechaFacturacion");
		if (StringUtils.isNotBlank(codigoPeriodo) && StringUtils.isNotBlank(fechaFacturacion))
			interfazIMPDAO.executeInterfazIMPEnviarBoletaElectronicaHistorico(params);
		else
			this.log.info("No se ejecuto proceso ppr valores nulos en los parametros de Periodo o Fecha de Facturacin");
	}
}
