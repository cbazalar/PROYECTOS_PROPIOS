package biz.belcorp.ssicc.service.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCampanha;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCliente;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaRegion;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaZona;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptasDemandaCurso;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextrvela
 *
 */
public interface ProcesoEDUCalificacionAptasDemandaService extends Service{
	/**
	 * Devuelve la lista de registros de Calificacin de Aptas por Demanda
	 * @param calificacionAptasCurso
	 * 			objeto CalificacionAptasDemandaCurso poblado.
	 */
	public List getCalificacionAptasDemandaCurso(CalificacionAptasDemandaCurso calificacionAptasCurso);

	/**
	 * Devuelve la lista de registros de Zonas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaZona
	 * 			objeto CalificacionAptaDemandaZona poblado.
	 */
	public List getCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona);

	/**
	 * Devuelve la lista de registros de regiones para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaRegion
	 * 			objeto CalificacionAptaDemandaRegion poblado.
	 */
	public List getCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion);

	/**
	 * Devuelve la lista de registros de clientes para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCliente
	 * 			objeto CalificacionAptaDemandaCliente poblado.
	 */
	public List getCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente);

	/**
	 * Devuelve la lista de registros de campaas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCampanha
	 * 			objeto CalificacionAptaDemandaCampanha poblado.
	 */
	public List getCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha);
	

	/**
	 * Inserta un registro de Zonas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaZona
	 * 			objeto CalificacionAptaDemandaZona poblado.
	 */
	public void insertCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona,Usuario usuario);

	/**
	 * Inserta un registro de region para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaRegion
	 * 			objeto CalificacionAptaDemandaRegion poblado.
	 */
	public void insertCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion,Usuario usuario);

	/**
	 * Inserta un registro de cliente para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCliente
	 * 			objeto CalificacionAptaDemandaCliente poblado.
	 */
	public void insertCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente,Usuario usuario);

	/**
	 * inserta un registro de campaa para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCampanha
	 * 			objeto CalificacionAptaDemandaCampanha poblado.
	 */
	public void insertCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha,Usuario usuario);
	

	/**
	 * Elimina un registro de Zonas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaZona
	 * 			objeto CalificacionAptaDemandaZona poblado.
	 */
	public void removeCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona);

	/**
	 * Elimina un registro de region para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaRegion
	 * 			objeto CalificacionAptaDemandaRegion poblado.
	 */
	public void removeCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion);

	/**
	 * Elimina un registro de cliente para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCliente
	 * 			objeto CalificacionAptaDemandaCliente poblado.
	 */
	public void removeCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente);

	/**
	 * Elimina un registro de campaa para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCampanha
	 * 			objeto CalificacionAptaDemandaCampanha poblado.
	 */
	public void removeCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha);

	/**
	 * Actualiza todos los parmetros de la Calificacin de Aptas a Demanda
	 * @param listaClientes,listaCampanhas,listaRegiones,listaZonas
	 * 			objeto List poblados por cada uno de los tipos de parametros
	 */
	public void updateCalificacionAptaDemanda(CalificacionAptasDemandaCurso calificacionAptasDemandaCurso,Usuario usuario,
			List listaClientes,List listaCampanhas,List listaRegiones,List listaZonas);
	
	/**
	 * Realiza el proceso de Calificacin de Aptas a Demanda
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeCalificacionAptasDemanda(String codigoPais, Map params) throws Exception;
	
	/**
	 * Realiza el proceso Previo de Calificacin de Aptas a Demanda donde obtiene la Lista de clientes 
	 * iniciales a Calificar
	 * @param params
	 * @throws Exception
	 */
	public List executePrevioCalificacionAptasDemanda(String codigoPais, Map params) throws Exception;

}
