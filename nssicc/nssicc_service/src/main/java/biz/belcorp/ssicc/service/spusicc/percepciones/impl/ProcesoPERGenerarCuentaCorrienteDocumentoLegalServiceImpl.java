/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.percepciones.impl;

  
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.percepciones.ProcesoPERGenerarCuentaCorrienteDocumentoLegalDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.percepciones.ProcesoPERGenerarCuentaCorrienteDocumentoLegalService;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoPERGenerarCuentaCorrienteDocumentoLegalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERGenerarCuentaCorrienteDocumentoLegalServiceImpl extends BaseService implements ProcesoPERGenerarCuentaCorrienteDocumentoLegalService {
	
	@Resource(name="spusicc.procesoPERGenerarCuentaCorrienteDocumentoLegalDAO")
	ProcesoPERGenerarCuentaCorrienteDocumentoLegalDAO procesoPERGenerarCuentaCorrienteDocumentoLegalDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.procesoPERCargarPagosBancariosMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeGenerarCuentaCorrienteDocumentoLegal(Map datos){		
		procesoPERGenerarCuentaCorrienteDocumentoLegalDAO.executeGenerarCuentaCorrienteDocumentoLegal(datos);
	}
}
