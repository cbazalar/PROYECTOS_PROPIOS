package biz.belcorp.ssicc.dao.spusicc.sgr;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoSGRGenericoDAO extends DAO {
	
	/**
	 * Proceso que se encarga de cancelar las polizas 
	 * @param map
	 */
	void executeCancelarPolizas(Map map);

	/**
	 * Proceso que se encrag de identificar las polizar y actulizar su estado a vigentes
	 * @param map
	 */
	void executeIdentificarPolizasVigentes(Map map);

	/**
	 * Proceso que actualiza el historico de coberturas de poliza
	 * @param params
	 */
	void executeActualizarHistoricoCoberturaPoliza(Map params);
	
	/**
	 * Ejecuta el proceso de consulta de poliza vigente FFVV GP3
	 * @param params
	 */
	void executeConsultarPolizaVigenteFFVVGP3(Map params);
	
	/**
	 * Proceso que actualiza la Clasificacin de las Consultoras
	 * @param params
	 */
	void executeActualizarClasificacionConsultora(Map params);

}
