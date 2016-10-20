package biz.belcorp.ssicc.service.spusicc.sto.framework.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;

/**
 * Implementacion de ProcesoSTOExcecutionServiceImpl que utiliza un Map con las
 * implementaciones especificas de los Documentos STO SiCC inyectados mediante
 * Spring. Nuevos documentos requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOValidateOnlineExecutorServiceImpl extends ProcesoSTOValidateExecutorServiceImpl {
	
	
	/**
	 * Prepara los documentos enviados en la lista para que puedan ser validados
	 * 
	 * @param documentoSTOParams
	 *            parametros STO
	 */
	protected  void insertHistoricoValidaciones(HistoricoTipoDocumento historico){
		procesoSTOHistoricoService.insertHistoricoValidacionesOnline(historico);
	}

}
