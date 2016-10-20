package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoPEDReasignacionDocumentosLegalesService extends Service{
	
	
	/**
	 * Este metodo obtiene todos los tipos de documentos
	 * @return List
	 */
	public List getTipoDocumentoContableAllList();

	/**
	 * Este metodo ejecuta la reasignacin de documentos
	 * @param params
	 */
	public void executeReasignacionDocumentosLegales(Map params);
}