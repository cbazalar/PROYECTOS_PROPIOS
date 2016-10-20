/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarNotasCreditoBoletasCabeceraDocumentoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("sisicc.interfazIMPEnviarNotasCreditoBoletasCabeceraDocumentoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarNotasCreditoBoletasCabeceraDocumentoServiceImpl
		extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="spisicc.interfazIMPGeneracionDocumentosNumeroInternoService")
	private InterfazIMPGeneracionDocumentosNumeroInternoService interfazIMPGeneracionDocumentosNumeroInternoService;

	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		String fechaFacturacion = (String)params.get("fechaFacturacion");
		params.put("tipoProceso", "04");
		  String indicadorDocumentosInternos =  (String)params.get("indicadorDocumentosInternos"); 
			log.debug("indicadorDocumentosInternos " +indicadorDocumentosInternos);	
		    if(StringUtils.isEmpty(indicadorDocumentosInternos))
		    	interfazIMPGeneracionDocumentosNumeroInternoService.insertDocInterno(params);

		interfazIMPDAO.executeInterfazIMPEnviarNotasCreditoBoletasCabeceraDocumento(params);
	}
	
	public void setInterfazIMPGeneracionDocumentosNumeroInternoService(
			InterfazIMPGeneracionDocumentosNumeroInternoService interfazIMPGeneracionDocumentosNumeroInternoService) {
		this.interfazIMPGeneracionDocumentosNumeroInternoService = interfazIMPGeneracionDocumentosNumeroInternoService;
	}


}
