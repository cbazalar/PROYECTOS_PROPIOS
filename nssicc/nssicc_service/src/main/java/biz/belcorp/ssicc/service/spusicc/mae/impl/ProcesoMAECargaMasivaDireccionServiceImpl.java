package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaMasivaDireccionDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaMasivaDireccionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAECargaMasivaDireccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAECargaMasivaDireccionServiceImpl extends BaseService implements
	ProcesoMAECargaMasivaDireccionService {
		
	@Resource(name="spusicc.procesoMAECargaMasivaDireccionDAO")
	private ProcesoMAECargaMasivaDireccionDAO procesoMAECargaMasivaDireccionDAO;

	@Resource(name="spusicc.mantenimientoMAEClienteService")
	protected MantenimientoMAEClienteService mantenimientoMAEClienteService;	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {	
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");	
		String indAdicionales = (String)criteria.get("indCamposAdicionales");
	

		//recupera el numero de carga
		String numeroCarga = getNumeroCarga();
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		Map params = new HashMap();
		params.put("numeroCarga", numeroCarga);

		Map mapRegistros = new HashMap();
		int fila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			fila +=1;
			
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
			String codigoCliente = (String)mapRow.get("0");
			String referencia = (String)mapRow.get("1");
			String barrio = (String)mapRow.get("2");
			String zona = (String)mapRow.get("3");
			String territorio = (String)mapRow.get("4");
			String tipoVia = (String)mapRow.get("5");
			String direccion = (String)mapRow.get("6");
			String numeroPrincipal = (String)mapRow.get("7");
			String tipoDireccion = (String)mapRow.get("8");
			if(StringUtils.isNotEmpty(referencia))
				referencia = StringUtils.substring(referencia, 0, 100);
			if(StringUtils.isNotEmpty(barrio))
				barrio = StringUtils.substring(barrio, 0, 40);
			if(StringUtils.isNotEmpty(direccion))
				direccion = StringUtils.substring(direccion, 0, 60);
			if(StringUtils.isNotEmpty(numeroPrincipal))
				numeroPrincipal = StringUtils.substring(numeroPrincipal, 0, 6);			
			String manzanaLetra ="";
			String etapaConjunto="";
			String callePrincipal="";
			String calleSecundaria="";
			
			//Valor Parametro adicional activado
			if(StringUtils.equals(indAdicionales, Constants.NUMERO_UNO)){
				 manzanaLetra = (String)mapRow.get("9");
				 etapaConjunto = (String)mapRow.get("10");
				 callePrincipal = (String)mapRow.get("11");
				 calleSecundaria = (String)mapRow.get("12");
				
				if(StringUtils.isNotBlank(manzanaLetra))
					manzanaLetra=StringUtils.substring(manzanaLetra, 0, 9);
				if(StringUtils.isNotBlank(etapaConjunto))
					etapaConjunto=StringUtils.substring(etapaConjunto, 0, 12);
				if(StringUtils.isNotBlank(callePrincipal))
					callePrincipal=StringUtils.substring(callePrincipal, 0, 25);
				if(StringUtils.isNotBlank(calleSecundaria))
					calleSecundaria=StringUtils.substring(calleSecundaria, 0, 25);
			}			
		    
			Map mapRegistro = new HashMap();
			params.put("codigoCliente", codigoCliente);
			params.put("referencia", referencia);
			params.put("barrio", barrio);
			params.put("zona", zona);
			params.put("territorio", territorio);
			params.put("tipoVia", tipoVia);
			params.put("direccion", direccion);
			params.put("numeroPrincipal", numeroPrincipal);
			params.put("tipoDireccion", tipoDireccion);
			params.put("codigoUsuario", usuario.getLogin());
			params.put("numeroFila", fila);
			params.put("manzanaLetra", manzanaLetra);
			params.put("etapaConjunto", etapaConjunto);
			params.put("callePrincipal", callePrincipal);
			params.put("calleSecundaria", calleSecundaria);
    
			procesoMAECargaMasivaDireccionDAO.insertCargaMasivaDireccion(params);			
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaMasivaDireccionService#executeValidarCargaMasivaDireccion(java.util.Map)
	 */
	public Map executeValidarCargaMasivaDireccion(Map params) {
		Map mapRespuesta = new HashMap();
		
		//EJECUTA LAS VALIDACIONES DE LOS DATOS DE LA DIRECCION
		procesoMAECargaMasivaDireccionDAO.executeValidarCargaMasivaDireccion(params);
		
		//SE RECUPERA LOS REGISTROS QUE INDICAN UN CAMBIO DE UA,
		//Y SE REALIZARA LA VALIDACION DE UA, PARA RECUPERA DATOS DE PERIODO INICIO,
		//INDICADOR PASO PEDIDO, REQUIERE GENERAR ESTATUS, Y OTROS QUE SE GUARDARA EN LA 
		//TABLA DE CARGA PARA SER RECUPERADOS EN EL PROCESO DE ACTUALIZACION
        String codigoPais = params.get("codigoPais").toString();
        String numeroCarga = params.get("numeroCarga").toString();
		List resultadosUA = procesoMAECargaMasivaDireccionDAO.getCargaMasivaDireccionUAList(params);
		for(int i=0; i<resultadosUA.size(); i++) {
			Map datosUA = (Map)resultadosUA.get(i);
			String oidCliente = datosUA.get("oidCliente").toString();
			String codigoZona = datosUA.get("codigoZona").toString();
			String codigoZonaInicial = datosUA.get("codigoZonaActual").toString();
			
			 Map resultados = mantenimientoMAEClienteService.obtenerDatosCambioUA(codigoPais, Constants.CODIGO_MARCA_DEFAULT, 
					 Constants.CODIGO_CANAL_DEFAULT, oidCliente, codigoZonaInicial, codigoZona);

			log.debug("resultados obtenerDatosCambioUA : " + resultados);
			
			String oidPeriodo = (String)resultados.get("oidPeriodo");
			String codigoPeriodo = (String)resultados.get("codigoPeriodo");
			String mostrarPedidoExtemporaneo = (String)resultados.get("mostrarPedidoExtemporaneo");
			String mostrarMensajeCambioPeriodoVigente = (String)resultados.get("mostrarMensajeCambioPeriodoVigente");
			String indicadorPasoPedido = (String)resultados.get("indicadorPasoPedido");
			String requiereGenerarEstatus = (String)resultados.get("requiereGenerarEstatus");
			
			String codigoObservacion = "";
			
			if(Constants.SI.equals(mostrarPedidoExtemporaneo))
				codigoObservacion = "10";
			if(Constants.SI.equals(mostrarMensajeCambioPeriodoVigente))
				codigoObservacion = "11";
			
			Map updateUA = new HashMap();
			updateUA.put("oidPeriodo", oidPeriodo);
			updateUA.put("codigoPeriodo", codigoPeriodo);
			updateUA.put("indicadorPasoPedido", indicadorPasoPedido);
			updateUA.put("indicadorGeneraEstatus", requiereGenerarEstatus);
			updateUA.put("codigoObservacion", codigoObservacion);
			updateUA.put("numeroCarga", numeroCarga);
			updateUA.put("numeroFila", datosUA.get("numeroFila").toString());
			
			procesoMAECargaMasivaDireccionDAO.updateCargaMasivaDireccionUA(updateUA);
			
		}
		
		//RECUPERAMOS LA LISTA DE ERRORES
		List resultados = procesoMAECargaMasivaDireccionDAO.getCargaMasivaDireccionList(params);
		mapRespuesta.put("resultados", resultados);
		
		//RECUPERAMOS LA LISTA OK Y CON OBSERVACIONES
		List resultadosOK = procesoMAECargaMasivaDireccionDAO.getCargaMasivaDireccionObsList(params);
		mapRespuesta.put("resultadosOK", resultadosOK);
		
		return mapRespuesta;
	}
	
	public void executeActualizarCargaMasivaDireccion(Map params) throws Exception {
		String codigoPais = params.get("codigoPais").toString();
		String codigoUsuario = params.get("codigoUsuario").toString();
		String indAdicional= (String)params.get("indCamposAdicionales");
		
		
		//Obtenemos los periodos vigentes
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		LabelValue[] periodos = mantenimientoMAEClienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
						
		try {
			//Obtenemos la lista de registros a procesar la direccion, UA e historico de cambio
			List resultadosOK = procesoMAECargaMasivaDireccionDAO.getCargaMasivaDireccionOKList(params);
				
			for(int i=0; i<resultadosOK.size(); i++) {
				Map datosOK = (Map)resultadosOK.get(i);
				String oidCliente = datosOK.get("oidCliente").toString();
				String codigoCliente = (String)datosOK.get("codigoCliente");
				String referencia = (String)datosOK.get("referencia");
				String barrio = (String)datosOK.get("barrio");
				String zona = (String)datosOK.get("zona");
				String territorio = (String)datosOK.get("territorio");
				String tipoVia = (String)datosOK.get("tipoVia");
				String direccion = (String)datosOK.get("direccion");
				String numeroPrincipal = (String)datosOK.get("numeroPrincipal");
				String tipoDireccion = (String)datosOK.get("tipoDireccion");
				String manzanaletra = (String)datosOK.get("manzanaletra");
				String etapaConjunto = (String)datosOK.get("etapaConjunto");
				String callePrincipal = (String)datosOK.get("callePrincipal");
				String calleSecundaria = (String)datosOK.get("calleSecundaria");
				
				String oidTerritorio = null;
				if(datosOK.get("oidTerritorio")!=null)
					oidTerritorio = datosOK.get("oidTerritorio").toString();
				
				String oidTerriAdmi = null;
				if(datosOK.get("oidTerriAdmi")!=null)
					oidTerriAdmi = datosOK.get("oidTerriAdmi").toString();
	
				String codigoUbigeo = (String)datosOK.get("codigoUbigeo");
				
				String estatus = (String)datosOK.get("estatus");
				
				String oidPeriodo = null;
				if(datosOK.get("oidPeriodo")!=null)
					oidPeriodo = datosOK.get("oidPeriodo").toString();
				
				String codigoPeriodo = (String)datosOK.get("codigoPeriodo");
				String indPasoPedido = (String)datosOK.get("indPasoPedido");
				String indGeneraEstatus = (String)datosOK.get("indGeneraEstatus");
				
				//OBTENEMOS LOS DATOS DE LA DIRECCION Y UA del CLIENTE
				Cliente cliente = obtenerDatosCliente(codigoPais, oidCliente, codigoCliente, tipoDireccion);
				   
				//DIRECCIONES DEL CLIENTE
				ClienteDireccion clienteDireccion = (ClienteDireccion)cliente.getListClienteDireccion().get(0);
				if(StringUtils.isNotEmpty(oidTerriAdmi)) 
					clienteDireccion.setOidTerritorio(new Long(oidTerritorio));
				
				if(StringUtils.isEmpty(tipoVia)) {
					tipoVia = "99";
				}
				Long oidTipoVia = mantenimientoMAEClienteService.getOidTipoVia(tipoVia);
				clienteDireccion.setOidTipoVia(oidTipoVia);
	
				clienteDireccion.setNombreVia(direccion);
				clienteDireccion.setNumeroPrincipal(numeroPrincipal);
				clienteDireccion.setObservaciones(referencia);
				clienteDireccion.setBarrio(barrio);
				clienteDireccion.setValNomManzana(manzanaletra);
				clienteDireccion.setValEtapaConjunto(etapaConjunto);
				clienteDireccion.setValCallePrincipal(callePrincipal);
				clienteDireccion.setValCalleSecundaria(calleSecundaria);
				clienteDireccion.setIndicadorEliminacion(new Integer(0));
				clienteDireccion.setIndicadorCamposAdicionales(indAdicional);
				
				if(StringUtils.isNotEmpty(codigoUbigeo)) {
					clienteDireccion.setCodigoUnidadGeografica(codigoUbigeo);
				} else {
					codigoUbigeo = clienteDireccion.getCodigoUnidadGeografica();
				}	
				
				//UNIDAD ADMINISTRATIVA DEL CLIENTE
				if("01".equals(tipoDireccion) && StringUtils.isNotEmpty(oidTerriAdmi)) {
					ClienteUnidadAdministrativa clienteUnidadAdministrativaOld = cliente.getClienteUnidadAdministrativa();
					ClienteUnidadAdministrativa clienteUnidadAdministrativaNew = new ClienteUnidadAdministrativa();
					
					Map criteriaPeriodo = new HashMap();
					criteriaPeriodo.put("oidPeriodo", oidPeriodo);
					Base basePeriodoFin =  mantenimientoMAEClienteService.getPeriodoAnterior(criteriaPeriodo);
					String oidPeriodoFin = basePeriodoFin.getCodigo();
					criteriaPeriodo.put("oidPeriodo", oidPeriodoFin);
					
					boolean esPeriodoFinCerrado = mantenimientoMAEClienteService.esPeriodoCerrado(criteriaPeriodo);
					Integer indActivoUANuevo;
					Integer indActivoUAAnterior;
					
					if (!esPeriodoFinCerrado && Constants.SI.equals(indPasoPedido)) {
			            indActivoUANuevo = new Integer(0);
			            indActivoUAAnterior = new Integer(1);
			        } else {
			            indActivoUANuevo = new Integer(1);
			            indActivoUAAnterior = new Integer(0);
			        }  
	
					//Actualizamos el periodo Fin de la actual unidad administrativa
					if(clienteUnidadAdministrativaOld != null) {
						clienteUnidadAdministrativaOld.setPeriodoFin(new Long(oidPeriodoFin));
						clienteUnidadAdministrativaOld.setIndicadorActivo(indActivoUAAnterior);
					
						//si es registrada y no paso pedido, que limpie la unidad administrativa
						if(estatus.equals(Constants.MAE_ESTADO_REGISTRADA) && !Constants.SI.equals(indPasoPedido))
							clienteUnidadAdministrativaOld.setEsPeriodoInicioMayorIgualPeriodoVigente(true); 
						else  //verificamos si el periodo de inicio de la unidad administrativa ultima, es mayor o igual al periodo vigente
							clienteUnidadAdministrativaOld.setEsPeriodoInicioMayorIgualPeriodoVigente(
									validarPeriodoInicioMayorAPeriodoVigente(clienteUnidadAdministrativaOld.getPeriodoInicio().toString(), 
											oidPeriodo, periodos));
					}	
								
	
					//creamos una nueva unidad administrativa
					clienteUnidadAdministrativaNew.setOidCliente(cliente.getOid());
					clienteUnidadAdministrativaNew.setOidTerritorioAdministrativo(new Long(oidTerriAdmi));
					clienteUnidadAdministrativaNew.setIndicadorActivo(new Integer(1));
					clienteUnidadAdministrativaNew.setPeriodoInicio(new Long(oidPeriodo));
					if(Constants.SI.equals(indGeneraEstatus))
						clienteUnidadAdministrativaNew.setRequiereGenerarEstatus(true);
					else
						clienteUnidadAdministrativaNew.setRequiereGenerarEstatus(false);
					clienteUnidadAdministrativaNew.setPeriodoFin(null);
					clienteUnidadAdministrativaNew.setIndicadorActivo(indActivoUANuevo);
					clienteUnidadAdministrativaNew.setCodigoZona(zona);
					
					cliente.setClienteUnidadAdministrativaNew(clienteUnidadAdministrativaNew);
				}
				
				//guardamos historial de cambio de datos del cliente
				ClienteHistoricoDatos clienteHistoricoDatos = cliente.getClienteHistoricoDatos();
				
				clienteHistoricoDatos.setUbigeo(codigoUbigeo);
				clienteHistoricoDatos.setTipoVia(oidTipoVia.toString());
				clienteHistoricoDatos.setNumeroPrincipal(numeroPrincipal);
				clienteHistoricoDatos.setBarrio(barrio);
				clienteHistoricoDatos.setDireccion(direccion);
				clienteHistoricoDatos.setReferencia(referencia);
	
				//SOLO PARA TIPO DOMICILIO, SE GRABARA ZONA y TERRITORIO
				if("01".equals(tipoDireccion)) {
					clienteHistoricoDatos.setZona(zona);
					clienteHistoricoDatos.setTerritorio(territorio);
				}
				
				//Obtenemos codigoRegion y Codigo de Seccion
				if(StringUtils.isNotEmpty(oidTerriAdmi)) {
					Map mapRegionSeccion = mantenimientoMAEClienteService.getCodigoRegionySeccion(zona, territorio);
					
					if(mapRegionSeccion!=null) {
						clienteHistoricoDatos.setRegion(MapUtils.getString(mapRegionSeccion, "codigoRegion"));
						clienteHistoricoDatos.setSeccion(MapUtils.getString(mapRegionSeccion, "codigoSeccion"));
					} else {
						clienteHistoricoDatos.setRegion(clienteHistoricoDatos.getRegionAnterior());
						clienteHistoricoDatos.setSeccion(clienteHistoricoDatos.getSeccionAnterior());
					}
				} else {
					clienteHistoricoDatos.setRegion(clienteHistoricoDatos.getRegionAnterior());
					clienteHistoricoDatos.setSeccion(clienteHistoricoDatos.getSeccionAnterior());
				}
				
				clienteHistoricoDatos.setCodigoUsuario(codigoUsuario);
				cliente.setClienteHistoricoDatos(clienteHistoricoDatos);
				cliente.setCodigoUsuario(codigoUsuario);
				
				mantenimientoMAEClienteService.updateDireccionCargaMasiva(cliente);
			}
			
			//ACTUALIZAMOS EL NUMERO DE LOTE Y FECHA DE PROCESO
			params.put("numeroLote", getNumeroLote());
			procesoMAECargaMasivaDireccionDAO.updateCargaMasivaDireccionOK(params);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
	}
	
	private Cliente obtenerDatosCliente(String codigoPais, String oidCliente, String codigoCliente, String tipoDireccion) {
		Cliente cliente = new Cliente();
		cliente.setOid(new Long(oidCliente));
		cliente.setCodigoPais(codigoPais);
		
		ClienteHistoricoDatos clienteHistoricoDatos = new ClienteHistoricoDatos();
		clienteHistoricoDatos.setCodigoCliente(codigoCliente);
		clienteHistoricoDatos.setCodigoPais(codigoPais);
		clienteHistoricoDatos.setIndicadorOrigen("CC");
		
		List listClienteDireccion = mantenimientoMAEClienteService.getListDireccionCliente(cliente.getOid().toString());
		cliente.setListClienteDireccion(listClienteDireccion);		
		
		List listClienteDireccionAux = new ArrayList();
		boolean encontradoTipoDireccion = false;
		for(int i=0; i<listClienteDireccion.size(); i++) {
			ClienteDireccion clienteDireccion = (ClienteDireccion)listClienteDireccion.get(i);
			if(clienteDireccion.getCodigoTipoDireccion().equals(tipoDireccion)) {
				encontradoTipoDireccion = true;
				listClienteDireccionAux.add(clienteDireccion);
				
				
				//OBTENEMOS LOS DATOS INICIAlES DE DIRECCION DEL CLIENTE
				clienteHistoricoDatos.setUbigeoAnterior(clienteDireccion.getCodigoUnidadGeografica());
				clienteHistoricoDatos.setTipoViaAnterior(clienteDireccion.getOidTipoVia().toString());
				clienteHistoricoDatos.setNumeroPrincipalAnterior(clienteDireccion.getNumeroPrincipal());
				clienteHistoricoDatos.setBarrioAnterior(clienteDireccion.getBarrio());
				clienteHistoricoDatos.setDireccionAnterior(clienteDireccion.getNombreVia());
				clienteHistoricoDatos.setReferenciaAnterior(clienteDireccion.getObservaciones());
				
				break;
			}
		}
		
		if(!encontradoTipoDireccion) {
			ClienteDireccion clienteDireccion = new ClienteDireccion();
			clienteDireccion.setOidTipoDireccion(mantenimientoMAEClienteService.getOidTipoDireccion(tipoDireccion));
			clienteDireccion.setIndicadorDireccionPrincipal(new Integer(0));
			listClienteDireccionAux.add(clienteDireccion);
		}
		cliente.setListClienteDireccion(listClienteDireccionAux);	
		
		//RECUPERAMOS LA UA, PARA EL TIPO DIRECCION DOMICILIO (01)
		if("01".equals(tipoDireccion)) {
			ClienteUnidadAdministrativa clienteUnidadAdministrativa = 
							mantenimientoMAEClienteService.getUnidadAdministrativaCliente(cliente.getOid().toString());
			cliente.setClienteUnidadAdministrativa(clienteUnidadAdministrativa);
			
			//OBTENEMOS LOS DATOS INICIAlES DE LA UA ACTIVA DEL CLIENTE
			clienteHistoricoDatos.setZonaAnterior(clienteUnidadAdministrativa.getCodigoZona());
			clienteHistoricoDatos.setTerritorioAnterior(clienteUnidadAdministrativa.getCodigoTerritorio());
			clienteHistoricoDatos.setRegionAnterior(clienteUnidadAdministrativa.getCodigoRegion());
			clienteHistoricoDatos.setSeccionAnterior(clienteUnidadAdministrativa.getCodigoSeccion());
		}
		
		clienteHistoricoDatos.setIndicadorOrigen("CC");
		cliente.setClienteHistoricoDatos(clienteHistoricoDatos);
		
		return cliente;
	}
	
	private boolean validarPeriodoInicioMayorAPeriodoVigente(String oidPeriodoInicio, String oidPeriodoVigente, 
			LabelValue[] listPeriodosVigentes) {
		LabelValue basePeriodoVigente = listPeriodosVigentes[0];
		boolean 	esPeriodoMayorIgual = false;

		String codigoPeriodoInicio = mantenimientoMAEClienteService.getCodigoPeriodoByOidPeriodo(oidPeriodoInicio);

		if(codigoPeriodoInicio.compareTo(basePeriodoVigente.getLabel()) >= 0 ) {
			esPeriodoMayorIgual = true;
		}

		return esPeriodoMayorIgual;
	}
	
	/**
	 * Retorna el numero de carga
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoMAECargaMasivaDireccionDAO.getNumeroCarga();
	}
	
	/**
	 * Retorna el numero de lote
	 * @return
	 */
	private String getNumeroLote() {
		return procesoMAECargaMasivaDireccionDAO.getNumeroLote();
	}

}

