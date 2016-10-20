package biz.belcorp.ssicc.service.spusicc.sgr;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoSGRGenericoService  extends Service{

	/**
	 * retorna las polizas 
	 * @param criteria
	 * @return
	 */
	List getPoliza(Map criteria);

	/**elimna la poliza , logicamente
	 * @param map
	 */
	void deletePoliza(Map map);

	/**
	 * Realiza la insercion de la poliza y sus vigencias , kits y desctos
	 * @param map
	 */
	void savePoliza(Map map);

	/**
	 * retorna  la vigencia de la poliza 
	 * @param bean
	 * @return
	 */
	List getVigencia(Map bean);

	/**
	 * Retorna los kits d ela poliza
	 * @param bean
	 * @return
	 */
	List getKit(Map bean);

	/**
	 * Retona los descuentos d ela poliza
	 * @param bean
	 * @return
	 */
	List getDscto(Map bean);

	/**
	 * Obtine el numero de polizas ya registradas a clientes
	 * @param map
	 * @return
	 */
	Integer getNumPolizasRegistradas(Map map);

	/**
	 * Verifica si no hay traslape 
	 * @param map
	 * @return
	 */
	Integer getValidarTraslapeDescuento(Map map);

	/**
	 * Verifica si no hay traslape 
	 * @param map
	 * @return
	 */
	Integer getValidarTraslapeFechas(Map map);

	/**
	 * executa la validacion o rechazo segun accion 
	 * @param map
	 */
	void executeValidacionesInscripcionPoliza(Map map);

	/**
	 * realiza la inscripcion de poliza
	 * @param map
	 */
	void saveInscripcionPoliza(Map map);

	/**
	 * retorna la lista de beneficiarios
	 * @param map
	 * @return
	 */
	List getBeneficiarios(Map map);

	/**
	 * Actualiza la lista de beneficiarios
	 * @param map
	 */
	void updateInscripcionPoliza(Map map);

	/**
	 * Retorna la lista de polizas registradas
	 * @param criteria
	 * @return
	 */
	List getInscripcionPoliza(Map criteria);

	/**
	 * Elimina las polizas Registradas
	 * @param map
	 */
	void deleteInscripcionPoliza(Map map);

	/**
	 * Genera el Reporte de Control de Abonos 
	 * @param criteria
	 */
	public void executeGenerarReporteControlAbonos(Map criteria);

	/**
	 * Retorna los estatus de la poliza
	 * @param bean
	 * @return
	 */
	public List getEstatus(Map bean);

	/**
	 * Retorna las campaas gratuitas de la poliza
	 * @param bean
	 * @return
	 */
	public List getCampaniaGratuita(Map bean);
	
	/**
	 * Cambia el estado de activa a cancelada de una poliza
	 * @param map
	 */
	public void updateInscripcionPolizaActiva(Map map) ;
}
