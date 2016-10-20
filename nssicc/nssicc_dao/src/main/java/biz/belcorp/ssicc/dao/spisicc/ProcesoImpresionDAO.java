/*
 * Created on 04/07/2006 03:10:04 PM
 * biz.belcorp.ssicc.dao.PaqueteDocumentadioDAO
 */
package biz.belcorp.ssicc.dao.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PaqueteDocumentadioDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ProcesoImpresionDAO extends DAO {

    /**
     * Obtiene una relacion de los procesos de impresion tomando como criterio
     * de busqueda los valores de los atributos del objeto pasado como
     * parametro.
     * 
     * @param procesoImpresion
     *            Objeto usado para enviar los criterios de busqueda.
     * @return Lista de objetos ProcesoImpresion
     */
    public List getProcesosImpresion(ProcesoImpresion procesoImpresion);

    /**
     * Obtiene una relacion de los procesos de impresion tomando como criterio
     * de busqueda los valores enviados a través de un objeto tipo map.
     * 
     * @param criteria
     * @return Lista de objetos ProcesoImpresion
     */
    public List getProcesosImpresionByCriteria(Map criteria);

    /**
     * Obtiene la información de un proceso de impresion en base a su codigo.
     * 
     * @param codigoProceso
     * @return Objeto ProcesoImpresion conteniendo la información del proceso,
     *         de los subprocesos y de los archivos asociados a cada subproceso.
     */
    public ProcesoImpresion getProcesoImpresion(String codigoProceso);

    /**
     * Inserta la informacion de un proceso de impresion en la base de datos.
     * 
     * @param procesoImpresion
     *            Objeto conteniendo la informacion a insertar
     * @param usuario
     *            usuario que realiza la transaccion
     */
    public void insertProcesoImpresion(ProcesoImpresion procesoImpresion,
            Usuario usuario);

    /**
     * Actualiza la informacion de un proceso de impresion en la base de datos.
     * 
     * @param procesoImpresion
     *            Objeto conteniendo la informacion del proceso de impresion.
     * @param usuario
     *            usuario que realiza la transaccion
     */
    public void updateProcesoImpresion(ProcesoImpresion procesoImpresion,
            Usuario usuario);

    /**
     * Ejecuta el procedimiento encargado de cargar la informacion del archivo
     * XML generado por SiCC conteniendo el paquete documentario sin incluir el
     * Cupon de Pago.
     */
    public void executeCargarPaqueteDocumentarioSiCC(Map parametros);

    /**
     * Ejecuta el procedimiento encargado de cargar la informacion del archivo
     * XML generado por SiCC conteniendo los Cupones de Pago de las consultoras.
     */
    public void executeCargarCuponPago(Map parametros);

    /**
     * Ejecuta el procedimiento encargado de realizar la fusión de los
     * diferentes archivos XML, el Paquete Documentario SiCC, los Cupones de
     * Pago y los Reportes de Ultimas Noticias Privilege.
     */
    public void executeFusionarPaqueteDocumentario(Map parametros);

    /**
     * Ejecuta el procedimiento encargado de generar el archivo XML conteniendo
     * el Paquete Documentario definitivo ha ser enviado al spool de impresion.
     */
    public void executeGenerarPaqueteDocumentario(Map parametros);

    /**
     * Elimina la informacion de la tabla en la cual se cargan las notas de
     * credito para la generacion de un nuevo archivo consolidado
     * 
     */
    public void removeNotasCredito();

    /**
     * Ejecuta el procedimiento encargado de cargar un archivo de Nota de
     * Credito.
     * 
     * @param parametros
     */
    public void executeCargarNotaCredito(Map parametros);

    /**
     * Ejecuta el procedimiento encargado de generar el archivo conteniendo
     * todas las Notas de Credito asociadas a un mismo Numero de Serie,
     * ordenados en base al Numero de Documento Interno.
     * 
     * @param parametros
     */
    public void executeGenerarNotaCreditoConsolidada(Map parametros);
    
    /**
     * Obtiene el valor del parametro de impresion en base al codigo del proceso
     * de impresión y el nombre del parametro.
     * 
     * @param codigoProceso
     *            Codigo del Proceso de Impresión
     * @param nombreParametro
     *            Nombre del Parametro del cual se quiere obtener su valor.
     * @return Valor del Parametro de Impresión
     */
    public String getValorParametroImpresion(String codigoProceso, String nombreParametro);
    
    /**
     * Ejecuta los procedimientos encargados de actualizar el indicador
     * de impresion de las notas de credito en formato laser.
     * @param parametros
     */
    public void executeActualizarIndicadorImpresionNotasCredito(Map parametros);

    /**
     * Ejecuta los procedimientos encargados de actualizar el indicador
     * de impresion de las notas de debito en formato laser.
     * @param parametros
     */
    public void executeActualizarIndicadorImpresionNotasDebito(Map parametros);

    /**
     * Ejecuta el procedimiento encargado de cargar la informacion del archivo
     * XML generado por SiCC conteniendo las Hojas de Picado de las consultoras.
     */
    public void executeCargarHojaPicado(Map parametros);
    
    /**
     * Ejecuta el procedimiento encargado de limpiar las tablas que son 
     * cargadas con información a partir de archivos, este es el primer paso
     * previo a la compaginacion.
     * @param parametros
     */
    public void executeEliminarPaquetesDocumentarios(Map parametros);
    
    /**
     * Ejecuta el procedimiento encargado de obtener los paquetes documentarios a partir 
     * de la base de datos, en lugar del XML en base al periodo y fecha de facturacion.
     * @param parametros
     */
    public void executeCargarBlobPaqueteDocumentarioSiCC(Map parametros);

    /**
     * Ejecuta el procedimiento encargado de obtener los cupones de pago a partir 
     * de la base de datos, en lugar del XML en base al periodo y fecha de facturacion.
     * @param parametros
     */
    public void executeCargarBlobCuponPago(Map parametros);

	/**
	 * Ejecuta el procedimiento encargado de generar el XML del cupon de pago.
	 * 
	 * @param parametros
	 */
    public void executeGenerarCuponPago(Map parametros);

	/**
	 * elimina los archivos temporales
	 * @param object
	 */
	public void executeEliminarPaquetesDocumentariosColor(Object object);

	/**
	 * ejecuta la imprsion de color 
	 * @param params
	 */
	public void executeGenerarPaqueteDocumentarioColor(Map params);

	/**
	 * Obtiene el listado de Regiones para el Spool
	 */
	public List getListaRegionesActivasSpool();

	/**
	 * @param proceso
	 */
	public void executeProcesoIMPSpoolDetalleFactura(ProcesoSpool proceso);

	/**
	 * 
	 */
	public void deleteProcesoIMPSpoolDetalleFactura();

	/**
	 * @return
	 */
	public List getListaZonasActivasSpool();

	/**
	 * @param proceso
	 */
	public void executeSpoolCupones(ProcesoSpool proceso);

	/**
	 * 
	 */
	public void deleteProcesoIMPSpoolCupones();

	/**
	 * Envia la interfaz de ultimas noticias
	 * @param proceso
	 */
	public void executeSpoolUltimasNoticias(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de boleta despacho
	 * @param proceso
	 */
	public void executeSpoolBoletaDespacho(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de orden de compra
	 * @param proceso
	 */
	public void executeSpoolOrdenCompra(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de estado cuenta corriente
	 * @param proceso
	 */
	public void executeSpoolEstadoCtaCte(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de detalle factura por zona
	 * @param proceso
	 */
	public void executeSpoolDetalleFacturaZona(ProcesoSpool proceso);
	

	/**
	 * executa el sp de asignar stock
	 * @param params
	 */
	public void executeAsignacionStock(Map params);

	/**ejecuta el sp de calculo de flete
	 * @param params
	 */
	public void executeCalculoFlete(Map params);

	/**ejecuta el sp de documento de chequeo
	 * @param params
	 */
	public void executeDocumentoChequeo(Map params);

	/**
	 * ejecuta el sp de boletas electronicas
	 * @param params
	 */
	public void executeBoletaElectronicas(ProcesoSpool params);

	/**
	 * ejecuta el sp de generacion de consolidao
	 * @param params
	 */
	public void executeGeneraConsolidado(Map params);

	/**
	 * ejecuta el sp de secuencia de pedido
	 * @param params
	 */
	public void executeSecuenciaPedido(Map params);

	/**
	 *  ejecuta el sp de consolidado
	 * @param proceso
	 */
	public void executeSpoolCalculaConsolidado(ProcesoSpool proceso);

	/**
	 *  ejecuta el sp de compaginacion
	 * @param proceso
	 */
	public void executeSpoolCompaginacionFinal(ProcesoSpool proceso);

	/**
	 *  ejecuta el sp de boleta matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoBoletaMatricial(Map proceso);

	/**
	 *  ejecuta el sp de boleta premio
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoBoletaPremioMatricial(
			Map proceso);

	/**
	 *  ejecuta el sp de archivo cupon
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoCuponPagoMatricial(Map proceso);

	/**
	 *  ejecuta el sp de archivvo factura
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoFacturaMatricial(Map proceso);

	/**
	 *  ejecuta el sp de archivo guia
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoGuiaRemisionMatricial(
			Map proceso);

	/**
	 *  ejecuta el sp de archivo nota creito
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoNotaCreditoMatricial(
			Map proceso);

	/**
	 *  ejecuta el sp de genera archivo nota debito
	 * @param proceso
	 */
	public void executeSpoolGeneraArchivoNotaDebitoMatricial(
			Map proceso);

	/**
	 *  ejecuta el sp de genera boleta matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraBoletaMatricial(ProcesoSpool proceso);

	/**
	 *  ejecuta el sp de genera boleta premio matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraBoletaPremioMatricial(ProcesoSpool proceso);

	/**
	 *  ejecuta el sp de genera cta cte 
	 * @param proceso
	 */
	public void executeSpoolGeneraCtaCteService(ProcesoSpool proceso);

	/**
	 *  ejecuta el sp de geneta cupon
	 * @param proceso
	 */
	public void executeSpoolGeneraCuponMatricial(ProcesoSpool proceso);

	/**
	 *  ejecuta el sp de documentos legales
	 * @param proceso
	 */
	public void executeSpoolGeneraDocumentosLegales(ProcesoSpool proceso);

	/** 
	 *  ejecuta el sp de factura matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraFacturaMatricial(ProcesoSpool proceso);

	/**
	 * 	ejecuta el sp guia remision matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraGuiaRemisionMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de nota de credito
	 * @param proceso
	 */
	public void executeSpoolGeneraNotaCreditoMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de nota de debito
	 * @param proceso
	 */
	public void executeSpoolGeneraNotaDebitoMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de generar ruv
	 * @param proceso
	 */
	public void executeSpoolGeneraRuv(ProcesoSpool proceso);

	/**
	 * Ejecuta el sp Factura Laser Multihilo
	 * @param proceso
	 */
	public void executeSpoolFacturaLaserMultihilo(ProcesoSpool proceso);
	
	/**
	 * Ejecuta el sp Nota de Credito Laser Multihilo
	 * @param proceso
	 */
	public void executeSpoolNotaCreditoLaserMultihilo(ProcesoSpool proceso);

	/**Ejecuta el sp Nota de Debito Laser Multihilo
	 * @param proceso
	 */
	public void executeSpoolNotaDebitoLaserMultihilo(ProcesoSpool proceso);

	/**
	 * Genera el archivo de nota de debito
	 * @param params
	 */
	public void executeSpoolGeneraNotaDebito(Map params);

	/**
	 * Genera el archivo de nota de credito
	 * @param params
	 */
	public void executeSpoolGeneraNotaCredito(Map params);

	/**
	 * Genera el archivo de factura laser
	 * @param params
	 */
	public void executeSpoolGeneraFacturaLaser(Map params);	

	/**
	 * @param proceso
	 */
	public void executeSpoolDetalleFactura3(ProcesoSpool proceso);	

	/**
	 * @param params
	 */
	public void executeSpoolCalculaConsolidadoTotal(Map params);	
	
	/**
	 * @param proceso
	 */
	public void executeSpoolDetalleFactura4(ProcesoSpool proceso);

	/**proceso que genera cta corriente
	 * @param params
	 */
	public void executeSpoolGeneraCtaCorriente(Map params);
	
	/**proceso que genera secuanciacion
	 * @param params
	 */
	public void executeSpoolProcesoSecuenciacion(Map params);

	/**
	 * proceso que genera la compaginacion final 2
	 * @param params
	 */
	public void executeCompaginacionPaqueteDocumentarioFinal2(Map params);
	
	/**
	 * carga la tabla temnmporal para obtener los reportes errores STO
	 * @param params
	 */
	public void executeCargaTemporalReporteErroresSTO(Map params);
	
	/**
	 * Obtine los correos de gerentes de regin
	 * @param params
	 * @return
	 */
    public List getCorreosGerenteRegion(String codigoUsuario);
	
	/**
	 * Obtine el correo del gerente de regin de Bas_Param_Pais
	 * @param criteria
     * @return Correo Electronico de Gerente de Regin por Defecto
	 */
    public String getParamEmailGerenteRegion(Map criteria);
    
	/**
	 * Obtine los correos de gerentes de zona
	 * @param params
	 * @return
	 */
    public List getCorreosGerenteZona(String codigoUsuario);
    
	/**
	 * proceso que ejecuta el sp de actualizacin del indicador GP2
	 * @param params
	 */
	public void executeActualizaIndicadorGP2(Map params);
	
	/**
	 * Ejecuta el sp Genera Fecha de Reparto
	 * @param proceso
	 */
	public void executeGeneraFechaRepartoMultihilo(ProcesoSpool proceso);

	/**
	 * proceso que ejecuta el sp de Generar Archivo de Paquete Documentario
	 * @param params
	 */
	public void executeGenerarArchivoPaqueteDocumentario(Map params);
	
	/**
	 * proceso que genera el XML para las boletas de Honorarios
	 * 
	 * @param params
	 */
	public void executeGeneraBoletaHonorarios(Map params);
		
	/**
	 * proceso que genera las facturas globales de mexico
	 * @param params
	 */
	public void executeGeneraFacturaGlobalMexico(Map params);
	
	/**
	 * 
	 * @param params
	 */
	public String getParametroSTO(Map params);
	
	/**proceso que genera calculo inter mora
	 * @param params
	 */
	public void executeCalculoInterMora();
		
	public boolean validacionLimiteTiempoEjecucionProceso();

		
}