
package biz.belcorp.ssicc.service.spusicc.sto.framework;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOResult;

public interface ProcesoSTOExecutorService {
	
	
	/**
	 * @param accionTipoDocumento
	 * @param params
	 * @param documentoValidacionList
	 * @throws Exception
	 */
	public DocumentoSTOResult executeProcess(AccionTipoDocumento accionTipoDocumento,Map params, List documentoValidacionList) throws Exception;
	
}

