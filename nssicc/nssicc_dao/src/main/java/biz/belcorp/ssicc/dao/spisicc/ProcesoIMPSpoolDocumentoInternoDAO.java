/*
 * Created on 04/07/2006 03:10:04 PM
 * biz.belcorp.ssicc.dao.PaqueteDocumentadioDAO
 */
package biz.belcorp.ssicc.dao.spisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface ProcesoIMPSpoolDocumentoInternoDAO extends DAO {

	/**
	 * Proceso que Genera el Documento Interno
	 * 
	 * @param params
	 */
	public void executeGeneraDocumentoInterno(Map params);
	
	/**
	 * Genera el paquete laser
	 * @param params
	 */
	public void executeGeneraPaqueteLaser(Map params);
}