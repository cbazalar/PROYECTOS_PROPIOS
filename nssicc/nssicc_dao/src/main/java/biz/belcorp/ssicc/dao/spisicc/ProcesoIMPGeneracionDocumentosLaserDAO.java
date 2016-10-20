package biz.belcorp.ssicc.dao.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * 
 *  <p>
 * <a href="ProcesoIMPGeneracionDocumentosLaserDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * @author <a href="mailto:leonardo.lch@gmail.com">Leonardo Lizana Chauca</a>
 *
 */
public interface ProcesoIMPGeneracionDocumentosLaserDAO extends DAO {

	/**
	 * @param params
	 */
	public void executeGeneracionDocumentoLaserFactura(Map params);

	/**
	 * @return
	 */
	public List getDocumentoLaserFacturaCabeceraList();

	/**
	 * @param documentoLaserFacturaCabecera
	 * @return
	 */
	public List getDocumentoLaserFacturaDetalleList(Map documentoLaserFacturaCabecera);

	/**
	 * @param params
	 * @return
	 */
	public String getParametroPaginacionDetalle(Map params);

	/**
	 * 
	 */
	public void generacionDocumentosLaserFacturaXML();

	/**
	 * @param params
	 */
	public void saveDocumentoLaserFacturaXML(Map params);

	/**
	 * @param params
	 */
	public void executeGeneracionDocumentoLaserGuia(Map params);

	/**
	 * @param documentoLaserGuiaCabecera
	 * @return
	 */
	public List getDocumentoLaserGuiaDetalleList(Map documentoLaserGuiaCabecera);

	/**
	 * @param params
	 */
	public void saveDocumentoLaserGuiaXML(Map params);

	/**
	 * 
	 */
	public void generacionDocumentosLaseGuiaXML();

	/**
	 * @return
	 */
	public List getDocumentoLaserGuiaCabeceraList();

	/**
	 * @param params
	 * @return
	 */
	public List getDocumentoLaserCuentaCorrienteCabeceraList(Map params);

	/**
	 * @param params
	 * @return
	 */
	public String getSaldoActualConsultora(Map params);

	/**
	 * @param documentoLaserCuentaCorrienteCabecera
	 * @return
	 */
	public List getDocumentoLaserCuentaCorrienteDetalleList(Map documentoLaserCuentaCorrienteCabecera);

	/**
	 * @param params
	 */
	public void saveDocumentoLaserCuentaCorrienteXML(Map params);

	/**
	 * 
	 */
	public void generacionDocumentosLaserCuentaCorrienteXML();

	/**
	 * 
	 */
	public void deleteDocumentoLaserCuentaCorrienteXML();

	/**
	 * 
	 */
	public void deleteTablaDocumentoLaser();

	/**
	 * @param params
	 */
	public void executeGeneracionDocumentoLaserNotaCredito(Map params);

	/**
	 * @return
	 */
	public List getDocumentoLaserNotaCreditoCabeceraList();

	/**
	 * @param documentoLaserNotaCreditoCabecera
	 * @return
	 */
	
	public List getDocumentoLaserNotaCreditoDetalleList(Map documentoLaserNotaCreditoCabecera);

	/**
	 * @param params
	 */
	public void executeGeneracionDocumentoLaserNotaDebito(Map params);

	/**
	 * @return
	 */
	public List getDocumentoLaserNotaDebitoCabeceraList();

	/**
	 * 
	 */
	public void generacionDocumentosLaserNotaDebitoXML();

	/**
	 * 
	 */
	public void generacionDocumentosLaserNotaCretidoXML();

	/**
	 * @param documentoLaserNotaDebitoCabecera
	 * @return
	 */
	public List getDocumentoLaserNotaDebitoDetalleList(Map documentoLaserNotaDebitoCabecera);

	/**
	 * @param params
	 */
	public void saveDocumentoLaserNotaCreditoXML(Map params);

	/**
	 * @param documentoLaserNotaDebitoCabecera
	 */
	public void saveDocumentoLaserNotaDebitoXML(Map documentoLaserNotaDebitoCabecera);

	/**
	 * @param criteria
	 * @return
	 * Devuelve el saldo inicial de la cuenta corriente
	 */
	public String getSaldoInicialCtaCte(Map criteria);

	/**
	 * Proceso que genera los formatos de detalle de factura laser, el
	 * procesamiento se hace completamente a nivel de base de datos eliminando
	 * el uso de la plantilla Velocity.
	 * 
	 * @param params Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeProcesaDetallesFacturaLaser(Map params);

	/**
	 * Proceso que reemplaza los caracteres especiales en el formato de detalle
	 * de factura laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeReemplazaCaracteresDetallesFacturaLaser(Map params);

	/**
	 * Proceso que genera el archivo de detalles de factura laser.
	 * 
	 * @param params Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeGeneraDetallesFacturaLaser(Map params);

	/**
	 * Devuelve la cantidad de registros almacenados en la entidad que contiene
	 * la informacion de los detalles de factura laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 * @return Cantidad de detalles de factura existentes.
	 */
	public int getCantidadDetallesFacturaLaser(Map params);
	
	/**
	 * Proceso que genera los formatos de detalle de cuenta corriente laser, el
	 * procesamiento se hace completamente a nivel de base de datos eliminando
	 * el uso de la plantilla Velocity.
	 * 
	 * @param params Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeProcesaDetallesCuentaCorrienteLaser(Map params);

	/**
	 * Proceso que genera el archivo de detalles de cuenta corriente laser.
	 * 
	 * @param params Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeGeneraDetallesCuentaCorrienteLaser(Map params);

	/**
	 * Devuelve la cantidad de registros almacenados en la entidad que contiene
	 * la informacion de los detalles de cuenta corriente laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 * @return Cantidad de detalles de cuenta corriente existentes.
	 */
	public int getCantidadDetallesCuentaCorrienteLaser(Map params);

	/**
	 * Proceso que reemplaza los caracteres especiales en el formato de Ultimas
	 * Noticias Privilege laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeReemplazaCaracteresUltimasNoticiasPrivilegeLaser(Map params);

	/**
	 * Proceso que genera el archivo de ultimas noticias Privilege laser.
	 * 
	 * @param params Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeGeneraUltimasNoticiasPrivilegeLaser(Map params);

	/**
	 * Devuelve la cantidad de registros almacenados en la entidad que contiene
	 * la informacion de las Ultimas Noticias Privilege laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 * @return Cantidad de Ultimas Noticias Privilege existentes.
	 */
	public int getCantidadUltimasNoticiasPrivilegeLaser(Map params);

	/**
	 * Proceso que obtiene los paquetes documentarios generados por SiCC.
	 * Invocamos al proceso que forma parte de la compaginacion.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeCargarPaquetesDocumetariosLaser(Map params);
	
	/**
	 * Proceso que modifica los paquetes documentarios generados por SiCC sin
	 * llegar a la complejidad de una compaginacion.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeProcesaPaquetesDocumentariosLaser(Map params);

	/**
	 * Proceso que reemplaza los caracteres especiales en el paquete
	 * documentario laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeReemplazaCaracteresPaquetesDocumentariosLaser(Map params);

	/**
	 * Proceso que genera el archivo de paquetes documentarios laser.
	 * 
	 * @param params Parametros necesarios para la ejecucion del proceso.
	 */
	public void executeGeneraPaquetesDocumentariosLaser(Map params);

	/**
	 * Devuelve la cantidad de registros almacenados en la entidad que contiene
	 * la informacion de los paquetes documentarios laser.
	 * 
	 * @param params
	 *            Parametros necesarios para la ejecucion del proceso.
	 * @return Cantidad de paquetes documentarios existentes.
	 */
	public int getCantidadPaquetesDocumentariosLaser(Map params);

	/**
	 * Realiza la genreacion de la orden de compra simplificada
	 * @param params
	 */
	public void generacionDocumentoLaserOrdenCompraSimplificadaXML(Map params);

	/**
	 * Realiza la generacion de la boleta de despacho
	 * @param params
	 */
	public void generacionDocumentoLaserBoletaDespachoXML(Map params);

	/**
	 * Genera documetacion laser
	 * @param params
	 */
	public void executeGeneracionDocumentosLaserColor(Map params);

	/**
	 * Recupera los concursos de tipo Bonificacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConcursosBonificacion(Map criteria);
	
	/**
	 * consulta la cantidad de registros en la entidad que contiene los niveles de concurso para ser impresos
	 * 
	 * @param params
	 * @return
	 */
	public Integer getCantidadSeccionVentas(Map params);
	
	/**
	 * Borra logicamente los registro de la entidad que contiene los niveles de concurso para ser impresos
	 * 
	 * @param params
	 */
	public void deleteSeccionVentas(Map params);

	/**
	 * Inserta lista de registros en la entidad que contiene los niveles de concurso para ser impresos
	 * 
	 * @param params
	 */
	public void insertListSeccionVentas(final List list) throws Exception;

	/**
	 * consulta la cantidad de registros en la entidad que contiene los datos de las conferencias compartamos
	 * 
	 * @param params
	 * @return
	 */
	public Integer getCantidadSeccionCompartamos(Map params);

	/**
	 * valida un registro que va a ser insertado en la entidad que contiene los datos de las conferencias compartamos
	 * 
	 * @param params
	 * @return
	 */
	public String getValidacionSeccionCompartamos(Map params);

	/**
	 * Borra logicamente los registro de la entidad que contiene los datos de las conferencias compartamos
	 * 
	 * @param params
	 */
	public void deleteSeccionCompartamos(Map params);

	/**
	 * Inserta lista de registros en la entidad que contiene los datos de las conferencias compartamos
	 * 
	 * @param params
	 */
	public void insertListSeccionCompartamos(final List list) throws Exception;

	/**
	 * consulta la cantidad de registros en la entidad que contiene los datos para el plan piloto
	 * 
	 * @param params
	 * @return
	 */
	public Integer getCantidadPlanPiloto(Map params);

	/**
	 * Borra logicamente los registro de la entidad plan piloto
	 * 
	 * @param params
	 */
	public void deletePlanPiloto(Map params);

	/**
	 * Inserta lista de registros en la entidad que contiene los datos del plan piloto
	 * 
	 * @param params
	 */
	public void insertListPlanPiloto(final List list) throws Exception;

	/**
	 * consulta la cantidad de registros en la entidad que contiene los datos de las planes focalizados
	 * 
	 * @param params
	 * @return
	 */
	public Integer getCantidadSeccionFocalizados(Map params);

	/**
	 * valida un registro que va a ser insertado en la entidad que contiene los datos de las planes focalizados
	 * 
	 * @param params
	 * @return
	 */
	public String getValidacionSeccionFocalizados(Map params);

	/**
	 * Borra logicamente los registro de la entidad que contiene los datos de los planes focalizados
	 * 
	 * @param params
	 */
	public void deleteSeccionFocalizados(Map params);

	/**
	 * Inserta lista de registros en la entidad que contiene los datos de los planes focalizados
	 * 
	 * @param params
	 */
	public void insertListSeccionFocalizados(final List list) throws Exception;

	/**
	 * Ejecuta proceso de generacion XML
	 * @param params
	 */
	public void generacionDocumentoLaserProcesoFacturaXML(Map params);
	
	/**
	 * Genera el archivo Factura Laser
	 * @param params
	 */
	public void executeGeneraFacturaLaser(Map params);
	
	/**
	 * Obtiene la cantidad de registro de Factura Laser
	 * @param params
	 * @return
	 */
	public Integer getCantidadFacturaLaser(Map params);
	
	/**
	 * Genera el archivo Nota de Credito Laser
	 * @param params
	 */
	public void executeGeneraNotaCreditoLaser(Map params);
	
	/**
	 * Obtiene la cantidad de registro de Nota de Credito Laser
	 * @param params
	 * @return
	 */
	public Integer getCantidadNotaCreditoLaser(Map params);

	/**
	 * Genera el archivo Nota de Debito Laser
	 * @param params
	 */
	public void executeGeneraNotaDebitoLaser(Map params);
	
	/**
	 * Obtiene la cantidad de registro de Nota de Debito Laser
	 * @param params
	 * @return
	 */
	public Integer getCantidadNotaDebitoLaser(Map params);
	
	/**
	 * Envia el archivo con los almacenes
	 * @param params
	 */
	public void executeEnviarAlmacen(Map params);

	/**
	 * Ejecuta el proceso de genracion yenvio d ela nota de credito
	 * @param params
	 */
	public void generacionDocumentoLaserProcesoNotaCreditoXML(Map params);
}