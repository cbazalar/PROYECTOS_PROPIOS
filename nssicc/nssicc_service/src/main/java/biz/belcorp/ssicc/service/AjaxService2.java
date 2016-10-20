package biz.belcorp.ssicc.service;

import biz.belcorp.ssicc.dao.model.LabelValue;

/**
 * Service para ser accedido mediante AJAX. Los metodos deben ser configurados
 * en dwr.xml
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface AjaxService2 {

	public LabelValue[] getRegionesEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	public String getCampannaSgteEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);
	
	/**
	 * Obtiene una lista de Regiones en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de Regiones.
	 */

	public LabelValue[] getRegionesByPaisMarcaCanal(final String codigoPais,
			final String codigoMarca, final String codigoCanal);
	
	/**
	 * @param codigoPais
	 * @param codigoMarca
	 * @param condigoCanal
	 * @param codigoRegiones
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones,// final
																		// ArrayList
																		// codigoRegiones,
			String condicionTodos);
		
}
