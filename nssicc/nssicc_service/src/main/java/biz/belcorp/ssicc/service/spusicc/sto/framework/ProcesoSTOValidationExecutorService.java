package biz.belcorp.ssicc.service.spusicc.sto.framework;

import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;



public interface ProcesoSTOValidationExecutorService {
	
	
	/**
	 * @param validationSTOParams
	 * @throws Exception
	 */
	public void executeValidation(ValidacionDocumento validacionDocumento, String namespaceSTO , Map queryParams ) throws Exception;
	
}

