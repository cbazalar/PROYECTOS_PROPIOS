/**
 * 
 */
package biz.belcorp.ssicc.dao.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 *
 */
public interface ConcursoDAO extends DAO{

	/**
	 * obtiene detalle concurso constancia ventas
	 * @param criteria
	 * @return
	 */
	public List getDetalleConcursoConstanciaVentas(Map criteria); 
		
	/**
	 * obtiene totalizados de concurso de constancia 
	 * @param criteria
	 * @return
	 */
	public int getTotalGanadorasDetalleConcursoConstanciaVentas(Map criteria);
	
	/**
	 * obtiene concursos por tipo de concurso y estado de vigencia
	 * @param criteria
	 * @return
	 */
	public List getConcursosXTipo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getDetalleConcursoRecomendacion(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsultoraRecomendada(Map criteria);
	
	/**
	 * obtener Detalle Concurso Programas Nuevas
	 * @param criteria
	 * @return
	 */
	public List getDetalleConcursoProgramaNuevas(Map criteria);
	
	/**
	 * Retorna el detalle de los pedidos de la consultora nueva solicitada.
	 * @param criteria
	 * @return
	 */
	public List getPedidosNuevas(Map criteria);
	
	/**
	 * Retorna la informacin de las consultoras que participan en el Programa de Reconocimiento solicitado.
	 * @param criteria
	 * @return
	 */
	public List getDetalleConcursoReconocimiento(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getInformacionBasicaConcurso(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getDetalleConcursoRegaloPedido(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getOidPeriodoActual(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getTotalConcursantes(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getTotalGanadoras(Map criteria);	
	
}
