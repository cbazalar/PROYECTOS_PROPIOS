
package biz.belcorp.ssicc.service.spusicc.sto.framework;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOResult;

public interface ProcesoSTOExecutionService {

	
	/**
	 * Se encarga de ejecutar un procesos STO
	 * @param params
	 * @param documentoSTOList
	 * @throws Exception
	 */
	public DocumentoSTOResult execute(AccionTipoDocumento accionTipoDocumento,Map params, List documentoSTOList) throws Exception;
	
}

