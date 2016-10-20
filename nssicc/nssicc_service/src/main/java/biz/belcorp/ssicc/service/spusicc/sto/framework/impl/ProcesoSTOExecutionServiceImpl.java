package biz.belcorp.ssicc.service.spusicc.sto.framework.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutorService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOResult;
/**
 * Implementacion de ProcesoSTOExcecutionServiceImpl que utiliza un Map con las
 * implementaciones especificas de las Acciones soportadas por STO inyectados mediante
 * Spring. Nuevas aciones requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
public class ProcesoSTOExecutionServiceImpl extends BaseService implements	ProcesoSTOExecutionService {
	protected final Log log = LogFactory.getLog(getClass());
	
	private Map stoProcess;
	
	
	/**
	 * @param stoProcess
	 */
	public void setStoProcess(Map stoProcess) {
		this.stoProcess = stoProcess;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.ProcesoSTOExecutionService#approveValidacionDocumentoSTO(java.util.Map, java.util.List)
	 */
	public DocumentoSTOResult execute(AccionTipoDocumento accionTipoDocumento,Map params, List documentoSTOList) throws Exception {
		
		ProcesoSTOExecutorService baseStoProcess = getSTOProcess(accionTipoDocumento);
		return baseStoProcess.executeProcess(accionTipoDocumento, params, documentoSTOList);
		
	}
	/**
	 * Obtiene la implementacion especifica del Tipo de Documento del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 *            utilizado como key del Map
	 * @return Implementacion especifica el documento STO
	 */
	private ProcesoSTOExecutorService getSTOProcess(AccionTipoDocumento accionTipoDocumento) {
		String codigo = accionTipoDocumento.getCodAcciTipoDocu();
		ProcesoSTOExecutorService devuelve = (ProcesoSTOExecutorService) stoProcess.get(codigo);
		return devuelve;
	}
	


}
