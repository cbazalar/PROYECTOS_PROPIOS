package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoPEDReasignacionDocumentosLegalesDAO extends DAO{
	
	/**
	 * Este metodo obtiene todos los tipos de documentos
	 * @return List
	 */
	public List getTipoDocumentoContableAllList();

	/**
	 * @param parametros
	 * @return
	 */
	public Map getCantDocImpr(Map parametros);

	/**
	 * Este metodo ejecuta la reasignacin de documentos
	 * @param params
	 */
	public void executeReasignacionDocumentosLegales(Map params);

	/**
	 * Metodo que devuleve indicador que determina el soporte de caracteres para la serie de documentos legales
	 * @param criteria
	 * @return
	 */
	public String getIndSoporteCaracteres(Map criteria);

}