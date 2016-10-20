/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.ConsultoraDAO;
import biz.belcorp.ssicc.dao.scdf.ControlFacturacionDAO;
import biz.belcorp.ssicc.dao.scdf.CuentaClienteDAO;
import biz.belcorp.ssicc.dao.scdf.PremioDAO;
import biz.belcorp.ssicc.dao.scdf.ProductoDAO;
import biz.belcorp.ssicc.dao.scdf.RegionDAO;
import biz.belcorp.ssicc.dao.scdf.StickerDAO;
import biz.belcorp.ssicc.dao.scdf.SubgerenciaDAO;
import biz.belcorp.ssicc.dao.scdf.TarjetaDAO;
import biz.belcorp.ssicc.dao.scdf.ZonaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.dao.scdf.model.ClientePK;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.scdf.model.CuentaCliente;
import biz.belcorp.ssicc.dao.scdf.model.Premio;
import biz.belcorp.ssicc.dao.scdf.model.Tarjeta;
import biz.belcorp.ssicc.dao.scdf.model.TarjetaPK;
import biz.belcorp.ssicc.dao.sisicc.DelimitadorDAO;
import biz.belcorp.ssicc.dao.sisicc.EstructuraArchivoDAO;
import biz.belcorp.ssicc.dao.sisicc.FormatoDAO;
import biz.belcorp.ssicc.dao.sisicc.HistoricoDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.NormaInterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.TipoFormatoArchivoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;
import biz.belcorp.ssicc.dao.sisicc.model.Formato;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPERMovimientosBancariosDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.ProcesaInterfaceService;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.util.Fichero;
import biz.belcorp.ssicc.service.sisicc.util.exception.NoDataFoundException;

/**
 * @author Richard De los Reyes Principe
 * 
 */
@Service("sisicc.procesaInterfaceService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesaInterfaceServiceImpl extends BaseService implements	ProcesaInterfaceService {

	@Resource(name="sisicc.historicoDAO")
	private HistoricoDAO historicoDAO;

	@Resource(name="sisicc.interfazDAO")
	private InterfazDAO interfazDAO;

	@Resource(name="sisicc.delimitadorDAO")
	private DelimitadorDAO delimitadorDAO;

	@Resource(name="sisicc.formatoDAO")
	private FormatoDAO formatoDAO;

	@Resource(name="sisicc.estructuraArchivoDAO")
	private EstructuraArchivoDAO estructuraArchivoDAO;

	@Resource(name="sisicc.normaInterfazDAO")
	private NormaInterfazDAO normaInterfazDAO;

	@Resource(name="sisicc.tipoFormatoArchivoDAO")
	private TipoFormatoArchivoDAO tipoFormatoArchivoDAO;

	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;

	/** ******************* Estas son las de SCDF **************************** */

	@Resource(name="scdf.controlFacturacionDAO")
	private ControlFacturacionDAO controlFacturacionDAO;

	@Resource(name="scdf.clienteDAO")
	private ClienteDAO clienteDAO;

	@Resource(name="scdf.cuentaClienteDAO")
	private CuentaClienteDAO cuentaClienteDAO;

	@Resource(name="scdf.tarjetaDAO")
	private TarjetaDAO tarjetaDAO;

	@Resource(name="scdf.premioDAO")
	private PremioDAO premioDAO;

	@Resource(name="scdf.subgerenciaDAO")
	private SubgerenciaDAO subgerenciaDAO;

	@Resource(name="scdf.regionDAO")
	private RegionDAO regionDAO;

	@Resource(name="scdf.zonaDAO")
	private ZonaDAO zonaDAO;

	@Resource(name="scdf.consultoraDAO")
	private ConsultoraDAO consultoraDAO;

	@Resource(name="scdf.productoDAO")
	private ProductoDAO productoDAO;

	@Resource(name="scdf.stickerDAO")
	private StickerDAO stickerDAO;
	
	@Resource(name="spusicc.mantenimientoPERMovimientosBancariosDAO")
	private MantenimientoPERMovimientosBancariosDAO mantenimientoPERMovimientoBancarioDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#executeEliminarInformacionPrivilege(biz.belcorp.ssicc.model.Pais)
	 */
	public void executeEliminarInformacionPrivilege(Pais pais) {
		premioDAO.removePremioByPais(pais.getCodigo());
		tarjetaDAO.removeTarjetaByPais(pais.getCodigo());
		cuentaClienteDAO.removeCuentaClienteByPais(pais.getCodigo());
		clienteDAO.removeClienteByPais(pais.getCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#procesaArchivoEntradaUnitario(biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public String[] procesaArchivoEntradaUnitario(Interfaz interfaz,
			Map criteria, Usuario usuario) {
		log.debug("****************************************************");
		log
				.debug("SERVICE: ProcesaInterfaceService - METHOD: procesaArchivoEntradaUnitario");
		String[] retorno = new String[3];
		InterfazPK pk = null;
		Fichero archivo = new Fichero();
		Historico historico = new Historico();
		Delimitador delimitador = null;
		Formato formato = null;
		List estructuraArchivo = null;
		TipoFormatoArchivo tipoFormatoArchivo = null;
		NormaInterfaz normaInterfaz = null;
		String mensajes = "";
		String mensajesError = "";
		HashMap intData = null;
		ArrayList registros = null;
		boolean flagLog = false;
		boolean flagNoDataFoundLog = false;
		boolean flagLogErrorValidacion = false; 
		Timestamp fechaInicioProceso = new Timestamp(System.currentTimeMillis());
		String estadoProceso = Constants.ESTADO_PROCESO_INTERFAZ_OK;
		String numeroLote = null;
		Exception errorExcepcion = null;
		
		try {
			pk = new InterfazPK(interfaz.getCodigoPais(), interfaz
					.getCodigoSistema(), interfaz.getCodigo());
			numeroLote = (String) criteria.get("numeroLote");
			if (numeroLote == null) 
				numeroLote = interfazDAO.getNumeroLote(pk);
			System.out.println("NUM LOTE: " + numeroLote);
			
			log.debug("EL NUMERO DE LOTE ES: " + numeroLote);			
			BeanUtils.copyProperties(historico, interfaz);
			historico.setCodigoInterfaz(interfaz.getCodigo());
			historico.setCodigoInterfazEmpaquetada(interfaz.getCodigo());
			historico.setNumeroLote(numeroLote);
			historico.setFechaInicioProceso(fechaInicioProceso);
			historico.setFechaFinProceso(fechaInicioProceso);
			historico.setDescripcionLote((String) criteria.get("descripcion"));
			historico.setFlagError(Constants.SI);
			historico.setRegistrosProcesados(new Long(0));
			historico.setRegistrosErroneos(Constants.REGISTROS_ERRONEOS_ZERO);
			historico.setEstadoProceso(estadoProceso);			
			try {
				historicoDAO.insertHistorico(historico, usuario);
				// Setear con los datos y normas de la interfaz "Archivo"
				delimitador = delimitadorDAO.getDelimitador(interfaz
						.getCodigoDelimitador());
				formato = formatoDAO.getFormato(interfaz.getCodigoFormato());
				estructuraArchivo = estructuraArchivoDAO
						.getEstructuraArchivo(pk);
				tipoFormatoArchivo = tipoFormatoArchivoDAO
						.getTipoFormatoArchivo(interfaz.getTipoFormatoArchivo());
				NormaInterfaz criteriaNorma = new NormaInterfaz();
				criteriaNorma.setCodigoPais(interfaz.getCodigoPais());
				criteriaNorma.setCodigoTipoFormatoArchivo(tipoFormatoArchivo
						.getCodigo());
				normaInterfaz = normaInterfazDAO
						.getNormaInterfazByCriteria(criteriaNorma);
				archivo.setDelimitador(delimitador);
				archivo.setFormato(formato);
				archivo.setNormaInterfaz(normaInterfaz);
				archivo.setEstructuraArchivoInterfaz(estructuraArchivo);
				archivo.setNombreArchivo(interfaz
						.getNombreArchivoEntradaSalida());
				archivo.setInterfaz(interfaz);
				log.debug("MOSTRAMOS EL NOMBRE DEL ARCHIVO "
						+ archivo.getNombreArchivo());				
				Object[] recepciones = archivo.leerArchivo(usuario); //Obteniendo los registros a partir del Archivo
				registros = (ArrayList) recepciones[0];
				mensajesError = (String) recepciones[1];
				log.debug("NUMERO DE REGISTROS " + registros.size());
				
				if (registros.size() == 0) {
					flagNoDataFoundLog = true;
					throw new NoDataFoundException();
				} 
				else {					
					String indTipo = interfaz.getIndicadorTipoInterface();
					/* Para los casos de Actualizacion de Interfases de Entrada de Datos de Cabecera */
					if ((indTipo == null) || StringUtils.isEmpty(indTipo) || (indTipo.equals(Constants.INTERFAZ_TIPO_INDICADOR_CABECERA))) {
						for (int j = 0; j < registros.size(); j++) {
							intData = (HashMap) registros.get(j);
							intData.put("codigoPais", interfaz.getCodigoPais());
							actualizarTabla(intData, usuario, interfaz
									.getMetodoInterface());
						}
					}
					/* Para los casos de Actualizacion de Interfases de Entrada de Datos de Cabecera y Detalle o 
					 * funcionalidad diferente*/
					else {
						if (indTipo.equals(Constants.INTERFAZ_TIPO_INDICADOR_CABECERA_DETALLE)) 
							this.actualizarTabla(registros, usuario, interfaz, criteria);						
					}
					mensajes = "interfazSiCC.archivo.recibido";
				}
			}
			catch (InterfazException e) {
				errorExcepcion = e;
				estadoProceso  = e.getCodigoError();
				if (StringUtils.isEmpty(mensajesError))
					mensajesError = "interfazSiCC.error.general.procesar";
				flagLog = true;
			}
			// Manejo de Excepcin de logica de Negocio
			catch (Exception e) {
				errorExcepcion = e;
				estadoProceso = Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS;
				if (StringUtils.isEmpty(mensajesError))
					mensajesError = "interfazSiCC.error.general.procesar";
				flagLog = true;
			}

		} catch (NoDataFoundException e) {
			if (log.isDebugEnabled()) {
				log.debug("llego al NoDataFoundException -----------------");
			}
			log.error(e.getMessage());
			errorExcepcion = e;
			if (StringUtils.isEmpty(mensajesError))
				mensajesError = "interfazSiCC.archivo.ceroregistros";
			flagNoDataFoundLog = true;
		}

		catch (Exception e) {
			e.printStackTrace();
			errorExcepcion = e;
			estadoProceso = Constants.ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO;
			if (StringUtils.isEmpty(mensajesError))
				mensajesError = "interfazSiCC.error.general.procesar";
			flagLog = true;
		}
		finally {
			if (!flagNoDataFoundLog) {
				StringBuffer logError = new StringBuffer();
				logError = (StringBuffer) criteria.get("logError");
				if (logError != null && StringUtils.isNotBlank(logError.toString())) {
					flagLog = true;
					flagLogErrorValidacion = true;
					mensajes = "";
					mensajesError = "interfazSiCC.error.general.procesar";
				}
				
				if (flagLog)
				{	historico.setFlagError(Constants.SI);
					historico.setRegistrosProcesados(Constants.REGISTROS_ERRONEOS_ZERO);
					/* Grabando Registros Procesados */
					try {
						historico.setRegistrosErroneos(new Long(registros.size()));
					}	
					catch (NullPointerException e) { // En caso se no se pudo obtener datos del Registro de Entrada
						historico.setRegistrosErroneos(new Long(0));
					}
					/* Verificancion si habido Error de validacion */
					if (flagLogErrorValidacion) {
						Exception errorValidacion = new Exception(logError.toString());
						errorExcepcion = errorValidacion;
					}					
					/* Grabando Descripcion de Error y Generando Log*/
					try {
						if (errorExcepcion.getCause().toString().length() < 500) 
							historico.setDescripcionError(errorExcepcion.getCause().toString());
						else
							historico.setDescripcionError(errorExcepcion.getCause().toString().substring(0,499));
					}	
					catch(Exception e) {
						historico.setDescripcionError(errorExcepcion.getMessage());
					}
					/* Generando Log */
					if (interfaz != null) {
						if (interfaz.getFlagLogErrores().trim().equalsIgnoreCase(Constants.SI)) {
							if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_FTP)) 
								archivo.dejarLogFTP(interfaz, numeroLote, errorExcepcion);
							else
								archivo.dejarLogRed(interfaz, numeroLote, errorExcepcion);
						}
													
					}
				}
				else
				{	historico.setFlagError(Constants.NO);
					if (registros != null)
						historico.setRegistrosProcesados(new Long(registros.size()));
				}
				
				historico.setFechaInicioProceso(fechaInicioProceso);
				historico.setEstadoProceso(estadoProceso);
				historico.setFechaFinProceso(new Timestamp(System.currentTimeMillis()));
				// Insertamos el registro en el historico
				historicoDAO.updateHistorico(historico, usuario);
				//Insertando Correlativo de Numero de ejecucion
				if (interfaz.getNumeroEjecucion() == null) 
					interfaz.setNumeroEjecucion(new Long(1));
				else
					interfaz.setNumeroEjecucion(new Long(interfaz.getNumeroEjecucion().longValue() + 1));
				interfazDAO.updateNumeroEjecucionInterfaz(interfaz, usuario);
			}
		} // Fin Finally
		retorno[0] = numeroLote;
		retorno[1] = mensajes;
		retorno[2] = mensajesError;
		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#procesaArchivoEntradaPaquete(biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      java.util.Map, java.lang.String[], biz.belcorp.ssicc.model.Usuario)
	 */
	public String[] procesaArchivoEntradaPaquete(Interfaz paquete,
			Map criteria, String[] codigoInterfazEmpaquetada, Usuario usuario) {
		log.debug("****************************************************");
		log.debug("SERVICE: ProcesaInterfaceService - METHOD: procesaArchivoEntradaPaquete");
		String[] retorno = new String[3];
		for (int i = 0; i < codigoInterfazEmpaquetada.length; i++) {
			Interfaz interfaz = null;
			InterfazPK pk = null;
			pk = new InterfazPK(paquete.getCodigoPais(), paquete.getCodigoSistema(), codigoInterfazEmpaquetada[i]);
			interfaz = interfazDAO.getInterfaz(pk);
			retorno = this.procesaArchivoEntradaUnitario(interfaz, criteria, usuario);
			if (i==0) 
				criteria.put("numeroLote", retorno[0]);		
			if (!"interfazSiCC.archivo.recibido".equals(retorno[1])) { 
				return retorno;
			}
		}
		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#procesaArchivoSalidaUnitario(biz.belcorp.ssicc.model.Pais,
	 *      biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public String[] procesaArchivoSalidaUnitario(Pais pais, Interfaz interfaz,
			Usuario usuario) {
		// TODO Este metodo se implementara con el tiempo en base a lo
		// desarrollado en el BaseInterfazAction
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#procesaArchivoSalidaPaquete(biz.belcorp.ssicc.model.Pais,
	 *      biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public String[] procesaArchivoSalidaPaquete(Interfaz paquete, Map criteria,
			String[] codigoInterfazEmpaquetada, Usuario usuario) {
		// TODO Este metodo se implementara con el tiempo en base a lo
		// desarrollado en el BaseInterfazAction
		return null;
	}

	/**
	 * Esta funcion se encarga de parametrizar de acuerdo a lo que se seleccione
	 * 
	 * @param intData
	 * @param usuario
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private void actualizarTabla(Map intData, Usuario usuario, String metodo)
			throws Exception {
		Class[] clase = new Class[2];
		clase[0] = Class.forName("java.util.Map");
		clase[1] = Class.forName("biz.belcorp.ssicc.model.Usuario");
		getClass().getMethod(metodo, clase).invoke(this,
				new Object[]{intData, usuario});

	}
	
	/**
	 * Esta funcion se encarga de parametrizar de acuerdo a lo que se seleccione
	 * 
	 * @param intData
	 * @param usuario
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private void actualizarTabla(ArrayList intData, Usuario usuario, Interfaz interfaz, Map params)
			throws Exception, SQLException {
		Class[] clase = new Class[4];
		clase[0] = Class.forName("java.util.ArrayList");
		clase[1] = Class.forName("biz.belcorp.ssicc.model.Usuario");
		clase[2] = Class.forName("biz.belcorp.ssicc.sisicc.model.Interfaz");
		clase[3] = Class.forName("java.util.Map");
		String metodo = interfaz.getMetodoInterface();
		getClass().getMethod(metodo, clase).invoke(this,
				new Object[]{intData, usuario, interfaz, params});
	}	
	
	
	public synchronized void cargarFactor(Map criteria, Usuario usuario)
			throws Exception {
		if (log.isInfoEnabled()) {
		//	log.info("Dentro del metodo 'cargarFactor'");
		}
		try {
			System.out.println("ENTRO METODO CARGAR FACTOR");
			ControlFacturacion controlFacturacion = new ControlFacturacion();
			BeanUtils.copyProperties(controlFacturacion, criteria);
			controlFacturacionDAO.updateControlFacturacion(controlFacturacion,
					usuario);
			controlFacturacion = null;
		}	
		catch (Exception e) {
			throw e;
		}
	}

	public synchronized void cargarFichasInscripcion(Map criteria,
			Usuario usuario) throws Exception {
		if (log.isInfoEnabled()) {
		//	log.info("Dentro del metodo 'cargarFichaInscripcion'");
		}
		try {
			System.out.println("ENTRO METODO CARGAR FICHAS INSCRPCION");
			Cliente cliente = new Cliente();
			BeanUtils.copyProperties(cliente, criteria);
			cliente.setStatus(Constants.STATUS_CLIENTE_FICHAS);
			clienteDAO.insertCliente(cliente, usuario);
			cliente = null;
		}	
		catch (Exception e) {
			throw e;
		}	
	}

	public synchronized void cargarTarjetasPuntos(Map criteria, Usuario usuario) throws Exception {
		if (log.isInfoEnabled()) {
		//	log.info("Dentro del metodo 'cargarTarjetaPuntos'");
		}
		try {
			System.out.println("ENTRO METODO CARGAR TARJETAS PUNTOS");
			CuentaCliente cuentaCliente = new CuentaCliente();
			try {
				BeanUtils.copyProperties(cuentaCliente, criteria);
			} catch (IllegalAccessException e) {
				throw new InterfazException("Error en el Copiado a CUENTA CLIENTE");
			} catch (InvocationTargetException e) {
				throw new InterfazException("Error en el Copiado a CUENTA CLIENTE");				
			}
			Tarjeta tarjeta = new Tarjeta();
			try {
				BeanUtils.copyProperties(tarjeta, criteria);
			} catch (IllegalAccessException e) {
				throw new InterfazException("Error en el Copiado a TARJETA");
			} catch (InvocationTargetException e) {
				throw new InterfazException("Error en el Copiado a TARJETA");
				
			}
			/**
			 * Si no existe el Cliente lo insertamos y establecemos el status a
			 * T.
			 */
			Cliente cliente = new Cliente();
			cliente.setCodigo(cuentaCliente.getCodigoCliente());
			List clientes = clienteDAO.getClientes(cliente);
			if (clientes.size() == 0) {
				Cliente mapCliente = new Cliente();
				try {
					BeanUtils.copyProperties(mapCliente, criteria);
				} catch (IllegalAccessException e) {
					throw new InterfazException("Error en el Copiado a CLIENTE");					
				} catch (InvocationTargetException e) {
					throw new InterfazException("Error en el Copiado a CLIENTE");					
				}
				cliente.setCodigo(tarjeta.getCodigoCliente());
				cliente.setCodigoPais(tarjeta.getCodigoPais());
				cliente.setNombres(mapCliente.getNombres());
				cliente.setDocumentoIdentidad(mapCliente
						.getDocumentoIdentidad());
				cliente.setCodigoConsultora(tarjeta.getCodigoConsultora());
				cliente.setStatus(Constants.STATUS_CLIENTE_TARJETA);
				clienteDAO.insertCliente(cliente, usuario);
			}
			CuentaCliente mapCuentaCliente = new CuentaCliente();
			mapCuentaCliente.setCodigoPais(cuentaCliente.getCodigoPais());
			mapCuentaCliente.setCodigoCliente(cuentaCliente.getCodigoCliente());
			List cuentaClientes = cuentaClienteDAO
					.getCuentaClientes(mapCuentaCliente);
			if (cuentaClientes.size() == 0) {
				cuentaClienteDAO.insertCuentaCliente(cuentaCliente, usuario);
			}
			Tarjeta mapTarjeta = new Tarjeta();
			mapTarjeta.setCodigoPais(tarjeta.getCodigoPais());
			mapTarjeta.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
			List tarjetas = tarjetaDAO.getTarjetas(mapTarjeta);
			if (tarjetas.size() == 0) {
				tarjetaDAO.insertTarjeta(tarjeta, usuario);
			}
		}	
		catch (Exception e) {
			throw e;
		}
	}

	public synchronized void cargarPremiosDespacho(Map criteria, Usuario usuario) throws Exception {
		if (log.isInfoEnabled()) {
		//	log.info("Dentro del metodo 'cargarPremioDespacho'");
		}
		Premio premio = new Premio();
		System.out.println("ENTRO METODO CARGAR PREMIO DESPACHO");
		try {
			BeanUtils.copyProperties(premio, criteria);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}

		// Buscamos la tarjeta para determinar si es necesario insertar
		// una nueva y su correspondiente cliente
		TarjetaPK tarjetaPK = new TarjetaPK(premio.getCodigoPais(), premio
				.getNumeroTarjeta());
		Tarjeta tarjeta = new Tarjeta();

		try {
			tarjeta = tarjetaDAO.getTarjeta(tarjetaPK);
		} catch (ObjectRetrievalFailureException orfe) {
			// En caso ocurra la excepcion entonces la tarjeta no existe
			try {
				BeanUtils.copyProperties(tarjeta, criteria);
			} catch (IllegalAccessException e) {
				throw e;
			} catch (InvocationTargetException e) {
				throw e;
			}
			tarjeta.setCodigoPais(tarjeta.getCodigoPais());

			// Ahora buscamos al cliente asociado al premio/tarjeta
			ClientePK clientePK = new ClientePK(tarjeta.getCodigoPais(),
					tarjeta.getCodigoCliente());
			Cliente cliente = new Cliente();

			try {
				cliente = clienteDAO.getCliente(clientePK);
			} catch (ObjectRetrievalFailureException orfe2) {
				// En caso ocurra la excepcion entonces el cliente no
				// existe
				try {
					BeanUtils.copyProperties(cliente, criteria);
				} catch (IllegalAccessException e) {
					throw e;
				} catch (InvocationTargetException e) {
					throw e;
				}
				cliente.setCodigoPais(tarjeta.getCodigoPais());
				cliente.setCodigo(tarjeta.getCodigoCliente());
				cliente.setStatus(Constants.STATUS_CLIENTE_PREMIO);

				// Insertamos al cliente
				try {
					clienteDAO.insertCliente(cliente, usuario);
				}	
				catch (Exception e) {
					throw e;
				}
			}

			// Insertamos la tarjeta
			try {
				tarjetaDAO.insertTarjeta(tarjeta, usuario);
			}	
			catch (Exception e) {
				throw e;
			}
			
		}
		try {
			premioDAO.insertPremio(premio, usuario);
		}	
		catch (Exception e) {
			throw e;
		}
		premio = null;
	}

	
	public synchronized void cargarMovimientosBancarios(ArrayList registros, Usuario usuario,
			Interfaz interfaz, Map params)  throws Exception, SQLException {
		HashMap criteria = null;
		Date    fechaPagoDate;
		Date    fechaGenerarDate;
		String  fechaGenerar;
		String codigoConsultora; 
		boolean error = false;
		StringBuffer logError = new StringBuffer();
		Integer contador;
		Long    idPais;
		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'cargarMovimientosBancarios'");
		}
		 
		/* Seteando valores para la cabecera */
		try {
			criteria = (HashMap) registros.get(0);
			criteria.put("codigoPais", interfaz.getCodigoPais());
			idPais = interfazSiCCDAO.getDevuelveIdPais(interfaz.getCodigoPais());
			String fechaProceso = (String) criteria.get("fechaProceso");
			String fechaPago = (String) criteria.get("fechaPago");
			fechaPago = convierteFormatoFecha(fechaPago);
			criteria.remove("fechaProceso");
			criteria.remove("fechaPago");
			criteria.put("fechaProceso",convierteFormatoFecha(fechaProceso));
			criteria.put("fechaPago", fechaPago);
			MovimientosBancariosCabecera cabecera = new MovimientosBancariosCabecera();
			try {
				BeanUtils.copyProperties(cabecera, criteria);
			} catch (Exception e) {
				throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
			}
			
			/* Verificando que la fecha de Pago sea menor a la fecha de Generacion */ 
			fechaGenerar = (String) params.get("fechaGenerar");
			fechaGenerarDate = DateUtil.convertStringToDate("dd/MM/yyyy", fechaGenerar);
			fechaPagoDate    = DateUtil.convertStringToDate("dd/MM/yyyy", fechaPago);
			if (fechaPagoDate.compareTo(fechaGenerarDate) > 0) {
				logError.append("Cabecera - Fecha de Pago es mayor a la Fecha de Generacin: Registro Nro 1: " + criteria.toString());
				params.put("logError", logError);
				return;
			}
			
			/* Verificando datos respectivos */			
			Map paramValida = new HashMap();
			paramValida.put("idPais", idPais);			
			String codigoBanco      = (String) criteria.get("codigoBancoSicc");
			if (StringUtils.isNotBlank(codigoBanco)) {
				paramValida.put("codigoBancoSicc", codigoBanco);
				contador = interfazSiCCDAO.getExisteCuentaCorrienteBancaria(paramValida);
				if (contador.intValue() <= 0) { 
					logError.append("Cabecera - No se encontr Cuenta de Corriente Bancaria: Registro Nro 1: " + criteria.toString());
					params.put("logError", logError);
					return;
				}
				else {
					String codigoSociedad  = interfazSiCCDAO.getCodigoSociedadbyCuentaCorrienteBancaria(paramValida);
					cabecera.setCodigoSociedad(codigoSociedad);
				}
					
			}
			String tipoTransaccion  = (String) criteria.get("tipoTransaccion");
			if (StringUtils.isNotBlank(tipoTransaccion)) {
				paramValida.put("tipoTransaccion", tipoTransaccion);
				contador = interfazSiCCDAO.getExisteTipoTransaccionBancaria(paramValida);
				if (contador.intValue() <= 0) { 
					logError.append("Cabecera - No se encontr Tipo de Transaccin: Registro Nro 1: " + criteria.toString());
					params.put("logError", logError);
					return;
				}	
			}			
			
			/* Verificando si el Lote ha sido registrado previamente en el Sistema */
			log.info("metodo cargarMovimientoBancarios");
			MovimientosBancariosDetalle  detalleBD = new MovimientosBancariosDetalle();
			MovimientosBancariosCabecera cabeceraBD = new MovimientosBancariosCabecera();
			cabeceraBD.setCodigoPais(cabecera.getCodigoPais());
			cabeceraBD.setNumeroLoteInterno(cabecera.getNumeroLoteInterno());
			cabeceraBD = this.mantenimientoPERMovimientoBancarioDAO.getBeanMovimientosBancariosCabecera(cabeceraBD);
			if (cabeceraBD != null) {			
				if (cabeceraBD.getStatusLote().equals(Constants.PERCEPCIONES_MOVIMIENTO_CONFIRMADO)) {
					throw new InterfazException(Constants.INTERFAZ_PER_LOTE_CONFIRMADO); 
				}
				detalleBD.setCodigoPais(cabeceraBD.getCodigoPais());
				detalleBD.setNumeroLoteInterno(cabeceraBD.getNumeroLoteInterno());
				log.info("Removiendo Movimientos bancarios Cabecera y Detalle");
				this.mantenimientoPERMovimientoBancarioDAO.removeMovimientosBancariosDetalle(detalleBD, usuario);
				this.mantenimientoPERMovimientoBancarioDAO.removeMovimientosBancariosCabecera(cabecera, usuario);
			}
			
			/* Insertando Cabecera de Movimientos Bancarios */
			cabecera.setEstado(Constants.ESTADO_ACTIVO);
			cabecera.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_AUTOMATICO);
			cabecera.setStatusLote(Constants.PERCEPCIONES_MOVIMIENTO_PROCESADO);
			log.info("insertando movimientos bancarios cabecera");
			interfazSiCCDAO.insertPercepcionesCabecera(cabecera, usuario);
			cabecera = null;
			
			/* Insertando Detalle correspondiente */
			log.info("insertando movimientos bancarios detalle");
			for (int i = 0; i < registros.size(); i++) {
				HashMap criteriaDetalle = (HashMap) registros.get(i);  
				criteriaDetalle.put("codigoPais", interfaz.getCodigoPais());
				String fechaProcesoD = (String) criteriaDetalle.get("fechaProceso");
				String fechaPagoD    = (String) criteriaDetalle.get("fechaPago");
				if (i != 0) {
					fechaPagoD = convierteFormatoFecha(fechaPagoD);
					criteriaDetalle.remove("fechaProceso");
					criteriaDetalle.remove("fechaPago");
					criteriaDetalle.put("fechaProceso",convierteFormatoFecha(fechaProcesoD));
					criteriaDetalle.put("fechaPago", fechaPagoD);
				}
				MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
				try {
					BeanUtils.copyProperties(detalle, criteriaDetalle);
				} catch (Exception e) {
					throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
				}
				detalle.setImportePagoAplicado(new Double(detalle.getImportePago()).doubleValue());
				detalle.setImporteRecaudoGenerado(new Double(detalle.getImportePago()).doubleValue());
				detalle.setImportePagoPendiente(0.00);
				detalle.setImportePercepcion(0.00);
				
				/* Efectuando validaciones Previas */
				/* Verificando que la fecha de Pago sea menor a la fecha de Generacion */				
				fechaPagoDate    = DateUtil.convertStringToDate("dd/MM/yyyy",fechaPagoD);
				if (fechaPagoDate.compareTo(fechaGenerarDate) > 0) {
					error = true;
					logError.append("Detalle - Fecha de Pago es mayor a la Fecha de Generacin: Registro Nro " 
							 + (i+1) + ": " + criteriaDetalle.toString() + System.getProperty("line.separator"));					
				}
				
				/* Verificando validaciones */
				detalle.setStatusMovimiento("C");
				if (StringUtils.isNotBlank(codigoBanco)) {
					detalle.setStatusMovimiento("P");
				}	
				codigoConsultora = (String) criteriaDetalle.get("codigoConsultora");
				if (StringUtils.isNotBlank(codigoConsultora)) {
					paramValida.put("codigoConsultora", codigoConsultora);
					contador = interfazSiCCDAO.getExisteConsultora(paramValida);
					if (contador.intValue() <= 0) { 
						error = true;
						logError.append("Detalle - No se encontr Consultora: Registro Nro " 
								+ (i+1) + ": " + criteriaDetalle.toString() + System.getProperty("line.separator"));
					}	
				}
				
				/* Insertando en el detalle */ 
				if (!error) {
					detalle.setEstado(Constants.ESTADO_ACTIVO);
				    interfazSiCCDAO.insertPercepcionesDetalle(detalle, usuario);
				}
				
				detalle = null;	
				criteriaDetalle.clear();
			}
		}	
		catch (Exception e) {
			throw e;
		}
		params.put("logError", logError);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#cargarConsolidadosOtrosCanales(java.util.Map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public synchronized void cargarConsolidadosOtrosCanales(Map criteria,
			Usuario usuario) throws Exception {
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'cargarConsolidadosOtrosCanales'");
		}
		try {
			/* Seteando Valores */ 
			InterfazPERActualizarPercepcionesConsolidado consolidado = new InterfazPERActualizarPercepcionesConsolidado();
			String fechaEmisionCom = (String) criteria.get("fechaEmisionComprobantePercepcion");
			String fechaEmisionDoc = (String) criteria.get("fechaEmisionDocumentoLegal");
			criteria.remove("fechaEmisionComprobantePercepcion");
			criteria.remove("fechaEmisionDocumentoLegal");
			criteria.put("fechaEmisionComprobantePercepcion",convierteFormatoFecha(fechaEmisionCom));
			criteria.put("fechaEmisionDocumentoLegal", convierteFormatoFecha(fechaEmisionDoc));
		    BeanUtils.copyProperties(consolidado, criteria);
		    
		    /* Insertando en la Base de Datos */
		    String correlativo = interfazSiCCDAO.getPercepcionesConsolidadoCorrelativoSiguiente();
			consolidado.setCorrelativoComprobantePercepcion(correlativo);
			consolidado.setEstado(Constants.ESTADO_ACTIVO);
			log.debug("consolidado: Apunto de insertar");			
			log.debug(consolidado.getCodigoPais()+" "+consolidado.getSerieComprobantePercepcion());
			
			interfazSiCCDAO.insertInterfazPERActualizarPercepcionesConsolidado(consolidado, usuario);
		} 
		catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#cargarComisionCodigoPlanilla(java.util.ArrayList,
	 *      biz.belcorp.ssicc.model.Usuario,
	 *      biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public synchronized void cargarComisionCodigoPlanilla(ArrayList registros, Usuario usuario,
			Interfaz interfaz, Map params )  throws Exception, SQLException {		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'cargarComisionCodigoPlanilla'");
		}
		try {
			LibretaAhorro libretaAhorro;
			int existe = 0;
			String existeStr = "";
			
			/* Insertando valores */
			for (int i = 0; i < registros.size(); i++) {
				HashMap criteria = (HashMap) registros.get(i);
				try {
					libretaAhorro = new LibretaAhorro();
					libretaAhorro.setCodigoPlanilla((String) criteria.get("codigoPlanilla"));
					libretaAhorro.setDocumentoIdentidad((String) criteria.get("dni"));
					
					existeStr = this.interfazSiCCDAO.getCantidadLibretaAhorrobyDNI(libretaAhorro.getDocumentoIdentidad());
					existe = Integer.parseInt(existeStr);
					if (existe != 0)
						this.interfazSiCCDAO.updateLibretaAhorro(libretaAhorro, usuario);
				} catch (Exception e) {
					throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
				}
			}
		}	
		catch (Exception e) {
			throw e;
		}
	
	
	}

	

	/**
	 * SON FUNCIONES CREADAS QUE A LA FINAL TENDRAN QUE SER REEMPLAZADAS POR EL
	 * NUEVO ESQUEMA DE SALIDA...
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getControlFacturacion(java.lang.String)
	 */
	public List getControlFacturacion(String codigoPais) {
		return controlFacturacionDAO.getControlFacturacionMap(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getSubgerencias(java.lang.String)
	 */
	public List getSubgerencias(String codigoPais) {
		return subgerenciaDAO.getSubgerenciaMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getRegiones(java.lang.String)
	 */
	public List getRegiones(String codigoPais) {
		return regionDAO.getRegionMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getZonas(java.lang.String)
	 */
	public List getZonas(String codigoPais) {
		return zonaDAO.getZonaMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getConsultoras(java.lang.String)
	 */
	public List getConsultoras(String codigoPais) {
		return consultoraDAO.getConsultorasMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getProductos(java.lang.String)
	 */
	public List getProductos(String codigoPais) {
		return productoDAO.getProductosMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getStickers(java.lang.String)
	 */
	public List getStickers(String codigoPais) {
		return stickerDAO.getHistoricoStickersMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getFichas(java.lang.String)
	 */
	public List getFichas(String codigoPais) {
		Cliente cliente = new Cliente();
		cliente.setCodigoPais(codigoPais);
		cliente.setStatus(Constants.STATUS_CLIENTE_FICHAS);
		List clientes = clienteDAO.getClientesMap(cliente);
		return clientes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getTarjetas(java.lang.String)
	 */
	public List getTarjetas(String codigoPais) {
		return tarjetaDAO.getTarjetasMapByPais(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#getPremios(java.lang.String)
	 */
	public List getPremios(String codigoPais) {
		return premioDAO.getPremiosMapByPais(codigoPais);
	}
	
	
	/**
	 * Convierte del formato <i>yyyyMMdd</i> al formato <i>dd/MM/yyyy</i>. <br /> Ej: 20061231 a 31/12/2006
	 * @param fechaProceso String con el formato <i>yyyyMMdd</i>
	 * @return String con el formato <i>dd/MM/yyyy</i>
	 */
	public String convierteFormatoFecha(String fecha) {
		String resultado = "";
		resultado = fecha.substring(6,8)+"/"+fecha.substring(4,6)+"/"+fecha.substring(0, 4);
		return resultado;
	}

/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#cargarConsolidadoOCS(java.util.Map,
	 *      biz.belcorp.ssicc.model.Usuario,
	 *      biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public synchronized void cargarConsolidadoOCSCabecera(Map params,
			Usuario usuario, Interfaz interfaz) {
		log.debug("Entering method 'cargarConsolidadoOCSCabecera'");
		interfazSiCCDAO.insertInterfazOCRConsolidadoOCSCabecera(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.ProcesaInterfaceService#cargarConsolidadoOCSDetalle(java.util.Map,
	 *      biz.belcorp.ssicc.model.Usuario,
	 *      biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public synchronized void cargarConsolidadoOCSDetalle(Map params, Usuario usuario,
			Interfaz interfaz) {
		log.debug("Entering method 'cargarConsolidadoOCSDetalle'");
		interfazSiCCDAO.insertInterfazOCRConsolidadoOCSDetalle(params);
	}

}
