package biz.belcorp.ssicc.dao.spusicc.sgr;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoSGRGenericoDAO extends DAO {

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
	 * Inserta la vigencia
	 * @param map
	 */
	void saveVigencia(Map map);

	/**
	 * Inserta el kit
	 * @param map
	 */
	void saveKit(Map map);

	/**
	 * Salvar Descuento
	 * @param map
	 */
	void saveDescto(Map map);

	/**
	 * Elimina el parametro existente
	 * @param map
	 */
	void deleteParametros(Map map);

	/**
	 * inserta Parametros
	 * @param map
	 */
	void insertParametros(Map map);

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
	 * actualiza la vigencia
	 * @param hmap
	 */
	void updateVigencia(Map hmap);

	/**
	 * Elimina la Vigencia
	 * @param hmap
	 */
	void deleteVigencia(Map hmap);

	/**
	 * Actualiza el Kit
	 * @param hmap
	 */
	void updateKit(Map hmap);

	/**
	 * Elimina el kit
	 * @param hmap
	 */
	void deleteKit(Map hmap);

	/**
	 * Actualiza el descuento
	 * @param hmap
	 */
	void updateDescto(Map hmap);

	/**
	 * Elimina el descuento
	 * @param hmap
	 */
	void deleteDescto(Map hmap);
	
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
	 * Devuelve 1 si existe poliza activa para consultora, caso contario 0 
	 * @param oidLotePremio
	 * @param fila
	 * @return
	 */	
	Integer getExisteConsultoraPolizaActiva(String codigoCliente);

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
	 * Obtiene la longitud por el codigo del tipo de documento
	 * @param oidPais
	 * @param codigoTipoDocumento
	 * @return
	 */
	String getLongitudTipoDocumentoByCodigo(String oidPais,
			String codigoTipoDocumento);

	/**
	 * Inserta el Estatus
	 * @param map
	 */
	public void saveEstatus(Map map);

	/**
	 * Elimina un Estatus
	 * @param map
	 */
	public void deleteEstatus(Map map);

	/**
	 * Retorna los estatus de la poliza
	 * @param bean
	 * @return
	 */
	public List getEstatus(Map bean);

	/**
	 * Obtiene el numero de documento de identidad por el codigo de cliente
	 * @param codigoCliente
	 * @param codigoTipoDocu
	 * @return
	 */
	public String getNumDocumentoIdentByCodigoCliente(String codigoCliente,String codigoTipoDocu);

	/**
	 * Retorna las campaas gratuitas de la poliza
	 * @param bean
	 * @return
	 */
	public List getCampaniaGratuita(Map bean);

	/**
	 * Inserta una campaa gratuita
	 * @param hmap
	 */
	public void saveCampaniaGratuitas(Map hmap);

	/**
	 * Actualiza una campaa gratuita
	 * @param hmap
	 */
	public void updateCampaniaGratuitas(Map hmap);

	/**
	 * Elimina una campaa gratuita
	 * @param hmap
	 */
	public void deleteCampaniaGratuitas(Map hmap);
	
	/**
	 * Obtiene la longitud por defecto del tipo de documento.
	 * @param oidPais
	 * @return
	 */
	public String getLongitudDefaultTipoDocumento(String oidPais);
	
	/**
	 * Cambia una poliza de activa a cancelada
	 * @param map
	 */
	public void updateInscripcionPolizaActiva(Map map);
}
