package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPActualizacionDireccionClienteForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPActualizacionDatosClienteAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan Altamirano </a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPActualizacionDireccionClienteAction extends BasePopupAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private LabelValue[] STOlistaProvinciasSolicitudCredito; //Constants.MAE_CLIENTE_NIVEL2_LIST
	private LabelValue[] STOlistaDistritosSolicitudCredito; //Constants.MAE_CLIENTE_NIVEL3_LIST
	private LabelValue[] STOlistaSectoresSolicitudCredito; //Constants.MAE_CLIENTE_NIVEL4_LIST
	
	private LabelValue[] maeClienteNivel1List; //Constants.MAE_CLIENTE_NIVEL1_LIST
	private LabelValue[] maeClienteNivel5List; //Constants.MAE_CLIENTE_NIVEL5_LIST
	private LabelValue[] maeClienteNivel6List; //Constants.MAE_CLIENTE_NIVEL6_LIST
	private List maeClienteTipoViaList;
	
	private LabelValue[] maeCiudadList;
	private List maeCiudadCTList;
	
	
	public static final String MAE_LONGITUD_CODIGO_ZONA = "4";
	private boolean permitirGrabar = true;
	
	private Cliente clienteSession;
	private String mensajeValidacionDeuda = "";
	private boolean divDireccionDomicilio4;
	private boolean divDireccionDomicilio51;
	private boolean mostrarNivel1;
	private boolean mostrarNivel2;
	private boolean mostrarNivel3;
	private boolean mostrarNivel4;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ConsultaHIPActualizacionDireccionClienteForm form = new ConsultaHIPActualizacionDireccionClienteForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - view' method");
        }
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.putAll(parametros);
		try {
			this.formBusqueda = this.devuelveFormBusqueda();
		}
		catch (Exception e) {
			
		}
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		f.setCodCliente(dtoDatosCliente.getCodigoCliente());
		f.setNomCliente(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonSecTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
				+ " / " + dtoDatosCliente.getCodigoSeccion() + " / " + dtoDatosCliente.getCodigoTerritorio());
		
		Map criterios = new HashMap();
		criterios.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criterios.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criterios.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criterios.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criterios.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		//recuperamos el oid Pais
        String oidPais = clienteService.getOidPais(criterios);
        f.setCodigoPais(dtoDatosCliente.getCodigoPais());
        f.setOidPais(oidPais);
        criterios.put("oidPais", oidPais);
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		Map mapDatosGenerales = service.getDatosGenerales(criterios);

		f.setPrimerApellido(MapUtils.getString(mapDatosGenerales, "apellido1", "")); 
		f.setSegundoApellido(MapUtils.getString(mapDatosGenerales, "apellido2", "")); 
		f.setPrimerNombre(MapUtils.getString(mapDatosGenerales, "nombre1", "")); 
		f.setSegundoNombre(MapUtils.getString(mapDatosGenerales, "nombre2", ""));
			
		f.setTelefonoFijo(MapUtils.getString(mapDatosGenerales, "telefono", ""));  
		f.setTelefonoCelular(MapUtils.getString(mapDatosGenerales, "celular", ""));
		f.setEmail(MapUtils.getString(mapDatosGenerales, "email", ""));
		f.setFechaNacimiento(MapUtils.getString(mapDatosGenerales, "fechaNacimiento", ""));
		f.setCodigoEstatus(MapUtils.getString(mapDatosGenerales, "codigoEstatus", ""));
		f.setSaldoDeuda(MapUtils.getString(mapDatosGenerales, "saldoDeuda", ""));
		f.setDocumento(dtoDatosCliente.getNumeroDocIdentidad());
		
		//Validarores
		String validarCaractererNV1 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO1);
		String validarCaractererNV2 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO2);
		String validarCaractererNV3 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO3);
		
		if(validarCaractererNV1 != null) {
			f.setValidarCaracteres1(validarCaractererNV1);
			f.setCadenaCaracteresV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "S"));
			f.setCadenaCaracteresNV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "N"));
		}
		if(validarCaractererNV2 != null) {
			f.setValidarCaracteres2(validarCaractererNV2);
			f.setCadenaCaracteresV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "S"));
			f.setCadenaCaracteresNV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "N"));
		}
		if(validarCaractererNV3 != null) {
			f.setValidarCaracteres3(validarCaractererNV3);
			f.setCadenaCaracteresV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "S"));
			f.setCadenaCaracteresNV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "N"));
		}
		
		f.setGraboOK(false);
		
        
        List nivelesGeograficos = clienteService.getNivelesGeograficos(criterios);
        
        //seteamos los niveles geograficos del pais
        if(nivelesGeograficos != null && nivelesGeograficos.size() > 0) {
        	f.setTotalNiveles(String.valueOf(nivelesGeograficos.size()));
        	
        	for(int i=0; i<nivelesGeograficos.size(); i++) {
        		Base nivel = (Base)nivelesGeograficos.get(i);
        		
        		if(i==0) f.setDescripcionNivel1(nivel.getDescripcion());        		
        		if(i==1) f.setDescripcionNivel2(nivel.getDescripcion()); 
        		if(i==2) f.setDescripcionNivel3(nivel.getDescripcion());
        		if(i==3) f.setDescripcionNivel4(nivel.getDescripcion());
        		if(i==4) f.setDescripcionNivel5(nivel.getDescripcion());
        		if(i==5) f.setDescripcionNivel6(nivel.getDescripcion());
        	}
        }
        
        
        this.mensajeValidacionDeuda = "";
        setMaeClienteNivel1List(ajaxService.getUnidadesGeograficas(oidPais, ""));
        setMaeClienteTipoViaList(clienteService.getTiposVias(criterios));
        
		//limpiamos los datos de pantalla
        limpiar(f);
        
        // seteamos datos de tipoVia, NumeroPrincipal, ubigeo.
		setearCamposModuloPais(f, clienteService);
		
		//obtenemos los datos del Cliente a Modificar
        Cliente cliente = obtenerDatosCliente(clienteService, f.getCodigoPais(), dtoDatosCliente.getOidCliente());
        
        //seteamos los datos del formulario con los datos a modificar del Cliente
      	initCabecera(f, cliente);
        
		//actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);
				
		//validacion Grupo Direcciones
		LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criterios);
        validarGrupoDirecciones(f, clienteService, f.getCodCliente(), periodos);
        
        //Verificamos si la consultora paso pedidos EN PERIODOS VIGENTES
		String[] periodoVig = new String [periodos.length];
		for(int i=0;i<periodos.length;i++) {
			periodoVig[i] = periodos[i].getValue();
		}
		criterios.put("oidCliente", cliente.getOid().toString());
		criterios.put("listPeriodos", periodoVig); 
		f.setConsultoraPasoPedido(clienteService.esClienteHaFacturadoPeriodos(criterios));
		
		f.setPermitirGrabar(true);
		this.permitirGrabar = true;
		
		//Validar Deuda Vencida
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais paramPais = new ParametroPais();
		
		paramPais.setCodigoPais(f.getCodigoPais());
		paramPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		paramPais.setNombreParametro("activarValidacionDeuda");
		paramPais.setIndicadorActivo(Constants.NUMERO_UNO);
		
		List lstParametros = genericoService.getParametrosPais(paramPais);
		boolean validarDeudaVencida = false;
		if (lstParametros != null && lstParametros.size() > 0) 
			validarDeudaVencida = true;

		//MessageResources messageResources = getResources(request);
		if(validarDeudaVencida) {
			paramPais.setNombreParametro("montoDeuda");
			paramPais.setIndicadorActivo(Constants.NUMERO_UNO);
			
			lstParametros = genericoService.getParametrosPais(paramPais);
			if (lstParametros != null && lstParametros.size() > 0) {
				paramPais = (ParametroPais)lstParametros.get(0);
				
				Double saldoDeuda = new Double(f.getSaldoDeuda());
				Double montoDeuda = new Double(paramPais.getValorParametro());
				
				if(saldoDeuda.doubleValue() > montoDeuda.doubleValue()) {
					Double valor[] = {saldoDeuda}; 
					mensajeValidacionDeuda = this.getResourceMessage("consultaHIPActualizacionDireccionClienteForm.msg.activarValidacionDeuda", valor);
					f.setMensajeSaldoDeuda(mensajeValidacionDeuda);
					f.setPermitirGrabar(false);
					this.permitirGrabar = false;
				}
			}
		} 
		
		if(StringUtils.isEmpty(f.getMensajeSaldoDeuda())) {
			paramPais = new ParametroPais();
			paramPais.setCodigoPais(f.getCodigoPais());
			paramPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			paramPais.setNombreParametro("activarValidacionDeuda");
			paramPais.setIndicadorActivo(Constants.NUMERO_UNO);
			
			lstParametros = genericoService.getParametrosPais(paramPais);
			boolean validarBloqueoActivo = false;
			if (lstParametros != null && lstParametros.size() > 0) 
				validarBloqueoActivo = true;
			
			if(validarBloqueoActivo) {
				String bloqueoActivo = service.getBloqueoActivo(f.getCodCliente());
				if(bloqueoActivo != null) {
					String valor[] = {bloqueoActivo}; 
					mensajeValidacionDeuda = this.getResourceMessage("consultaHIPActualizacionDireccionClienteForm.msg.activarValidacionBloqueo", valor);
					f.setMensajeBloqueoActivo(mensajeValidacionDeuda);
					f.setPermitirGrabar(false);
					this.permitirGrabar = false;
				}
			}	
		}
		
		if(f.isPermitirGrabar()) {
			//Obtenemos los datos iniciales del Cliente
			ClienteHistoricoDatos clienteHistoricoDatos = new ClienteHistoricoDatos();
			clienteHistoricoDatos.setCodigoCliente(f.getCodCliente());
			clienteHistoricoDatos.setCodigoPais(dtoDatosCliente.getCodigoPais());
			
			if(StringUtils.isNotEmpty(f.getNivel6()))
				clienteHistoricoDatos.setUbigeoAnterior(f.getNivel6());
			if(StringUtils.isNotEmpty(f.getNivel5()))
				clienteHistoricoDatos.setUbigeoAnterior(f.getNivel5());
			if(StringUtils.isNotEmpty(f.getNivel4()))
				clienteHistoricoDatos.setUbigeoAnterior(f.getNivel4());
			if(StringUtils.isNotEmpty(f.getNivel3()))
				clienteHistoricoDatos.setUbigeoAnterior(f.getNivel3());
			
			clienteHistoricoDatos.setTipoViaAnterior(f.getTipoVia());
			clienteHistoricoDatos.setNumeroPrincipalAnterior(f.getNumeroPrincipal());
			clienteHistoricoDatos.setBarrioAnterior(f.getBarrio());
			clienteHistoricoDatos.setDireccionAnterior(f.getNombreVia());
			clienteHistoricoDatos.setReferenciaAnterior(f.getObservacionDireccion());

			clienteHistoricoDatos.setZonaAnterior(f.getZona());
			clienteHistoricoDatos.setTerritorioAnterior(f.getTerritorio());
			clienteHistoricoDatos.setRegionAnterior(f.getCodigoRegion());
			clienteHistoricoDatos.setSeccionAnterior(f.getCodigoSeccion());
			clienteHistoricoDatos.setIndicadorOrigen("CM");
			
			clienteHistoricoDatos.setIndicadorOrigen("CH");
			cliente.setClienteHistoricoDatos(clienteHistoricoDatos);
		}	
		
		//Deshabilitar Zona y/o Territorio
		f.setDeshabilitarZonaTerritorio(false);
		
		paramPais = new ParametroPais();
		paramPais.setCodigoPais(f.getCodigoPais());
		paramPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		paramPais.setNombreParametro("numcampanaActualizarUbigeo");
		paramPais.setIndicadorActivo(Constants.NUMERO_UNO);
		
		lstParametros = genericoService.getParametrosPais(paramPais);
		if (lstParametros != null && lstParametros.size() > 0) {
			paramPais = (ParametroPais)lstParametros.get(0);
			String numeroPeriodos = paramPais.getValorParametro();
			
			criterios.put("numeroPeriodos", numeroPeriodos);
			String deshabilitar = service.getDeshabilitarZonaTerritorio(criterios);
			
			if(Constants.NUMERO_UNO.equals(deshabilitar))
				f.setDeshabilitarZonaTerritorio(true);
		}
		
		if (StringUtils.isNotBlank(mensajeValidacionDeuda)) {
			//this.setMensajeAlertaDefault(mensajeValidacionDeuda);
			//this.getRequestContext().execute("PF('popupFormAlert_alertDialog').show()");
			//this.addError("Error", mensajeValidacionDeuda);
		}
		inicializar();
	}
	
	public void inicializar(){
		ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
		visualizarDireccionDomicilio(f);
		
		//verificamos si mostramos mensaje de cambio de periodo vigente
		if(f.isMostrarMensajeCambioPeriodoVigente()){
			this.addInfo("Mensaje.", this.getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.cambioPeriodoVigente"));
			f.setMostrarMensajeCambioPeriodoVigente(false);
		}
		//verificamos si mostramos mensaje de pedido extemporaneo
		if(f.isMostrarMensajePedidoExtemporaneo()){
			this.addInfo("Mensaje.", this.getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.regionZonaExtemporaneo"));
			f.setMostrarMensajePedidoExtemporaneo(false);
		}
		
		//si el ubigeo de la direccion no corresponde al ubigeo del territorio
	    //se confirmara si de todas maneras se graba o modificar la zona/territorio/ubigeo direccion
		if(!StringUtils.isEmpty(f.getMensajeConfirmacion())){
//			if (confirm(mensajeConfirmacion.value))  {
//	  			confirmacionTerritorio.value = 'ok';
//	      		mensajeConfirmacion.value = ''; 		  		
//	      		actualizarDireccion();
//	    	} else {
//	    		mensajeConfirmacion.value = '';
//	    		document.getElementById("codigoZona").focus();
//	    	}
		}
		
		if(!f.isPermitirModificarUbigeo()){
			this.mostrarNivel1=true;
			this.mostrarNivel2=true;
			this.mostrarNivel3=true;
			
			if(f.getNivel4()!=null){
				this.mostrarNivel4=true;
			}
		}
		
		

		
		
	}
	public void visualizarDireccionDomicilio(ConsultaHIPActualizacionDireccionClienteForm f) {
		this.divDireccionDomicilio4=false;
		this.divDireccionDomicilio51=false;
		if(f.isMostrarTipoVia() || f.isMostrarNumeroPrincipal()){
			this.divDireccionDomicilio4=true;
		}
		
		if(f.isMostrarBarrio()){
			this.divDireccionDomicilio51=true;
		}
    }	
	
	/**
	 * Seteamos los datos de Direccion y Unidad Administrativa
	 * 
	 * @param f
	 * @param cliente
	 * @param session
	 */
	private void initCabecera(ConsultaHIPActualizacionDireccionClienteForm f, Cliente cliente)  {
		//Recuperamos la Zona y Territorio del cliente
		if(cliente.getClienteUnidadAdministrativa()!=null) {
			ClienteUnidadAdministrativa clienteUnidadAdministrativa = cliente.getClienteUnidadAdministrativa();
			
			f.setZona(clienteUnidadAdministrativa.getCodigoZona());
			f.setTerritorio(clienteUnidadAdministrativa.getCodigoTerritorio());
			
			f.setCodigoZonaInicial(f.getZona());
			f.setCodigoTerritorioInicial(f.getTerritorio());
			
			f.setCodigoRegion(clienteUnidadAdministrativa.getCodigoRegion());
			f.setCodigoSeccion(clienteUnidadAdministrativa.getCodigoSeccion());
		} 
				
		//Recuperamos las direcciones del cliente
		List listClienteDireccion = cliente.getListClienteDireccion();
		List listClienteDireccionAux = new ArrayList();
		if(listClienteDireccion.size() > 0) {
			//obtenemos la direccion principal del cliente
			ClienteDireccion clienteDireccion = (ClienteDireccion)cliente.getListClienteDireccion().get(0);
			
			if(clienteDireccion.getIndicadorDireccionPrincipal().intValue() == 1) {
				String codigoUnidadGeografica = reemplazarNulo(clienteDireccion.getCodigoUnidadGeografica());
				
				if(codigoUnidadGeografica.length()> 0) {
					f.setNivel1(codigoUnidadGeografica.substring(0,6));
					
					if(codigoUnidadGeografica.length()> 6) 
						f.setNivel2(f.getNivel1() + codigoUnidadGeografica.substring(6,12));
					if(codigoUnidadGeografica.length()> 12)
						f.setNivel3(f.getNivel2() + codigoUnidadGeografica.substring(12,18));
					if(codigoUnidadGeografica.length()> 18)
						f.setNivel4(f.getNivel3() + codigoUnidadGeografica.substring(18,24));
					if(codigoUnidadGeografica.length()> 24)
						f.setNivel5(f.getNivel4() + codigoUnidadGeografica.substring(24,30));					
					if(codigoUnidadGeografica.length()> 30)
						f.setNivel6(f.getNivel5() + codigoUnidadGeografica.substring(30,36));
					
					f.setCodNivel1(f.getNivel1());
					f.setCodNivel2(f.getNivel2());
					f.setCodNivel3(f.getNivel3());
					f.setCodNivel4(f.getNivel4());
					f.setCodNivel5(f.getNivel5());
					f.setCodNivel6(f.getNivel6());
				}
				
				String ubigeo1=reemplazarNulo(clienteDireccion.getCodigoUbigeo1());
				String ciudad=reemplazarNulo(clienteDireccion.getCodigoCiudad());
				
				if(ciudad.length()>0)
					f.setCodigoCiudad(ubigeo1 + "__" + ciudad);
				
				f.setVillaPoblacion(reemplazarNulo(clienteDireccion.getVillaPoblacion()));
				
				f.setTipoVia(clienteDireccion.getOidTipoVia().toString());
				f.setNumeroPrincipal(clienteDireccion.getNumeroPrincipal());
				f.setNombreVia(clienteDireccion.getNombreVia());
				f.setObservacionDireccion(clienteDireccion.getObservaciones());
				f.setBarrio(clienteDireccion.getBarrio());
				
				listClienteDireccionAux.add(clienteDireccion);

			} 
		}

		cliente.setListClienteDireccion(listClienteDireccionAux);
		//session.setAttribute(HIP_CLIENTE_DIRECCION, cliente);
		
		setClienteSession(cliente);
		
		f.setEsEjecutiva(cliente.isEsEjecutiva());
	}
	
	/**
	 * EL grupo de direcciones NO será editable , 
	 *  si la consultora tiene pedidos que ya están marcados como enviados a la  entidad  PEDIDOS
	 * @param f
	 * @param clienteService
	 * @param cliente
	 * @param periodos 
	 */
	private void validarGrupoDirecciones(ConsultaHIPActualizacionDireccionClienteForm f,
			MantenimientoMAEClienteService clienteService, String codigoCliente, LabelValue[] periodos) {
		Map map = new HashMap();
		List listPeriodos = new ArrayList();
        if(periodos != null && periodos.length > 0) {
        	for(int i=0;i<periodos.length;i++){
        		LabelValue base = periodos[i];
        		listPeriodos.add(base.getValue());
        	}            
        }
        map.put("codigoCliente", codigoCliente);
        map.put("oidPeriodo", listPeriodos);
        
        Integer numPedidos = clienteService.getSizePedidosEnviados(map);
        
        log.debug("numPedidos >>> "+ numPedidos);
        if(numPedidos.intValue() > 0){
        	f.setEditable(false);
        	this.mostrarNivel1=true;
        	this.mostrarNivel2=true;
        	this.mostrarNivel3=true;
        	this.mostrarNivel4=true;
        }
        else {
        	f.setEditable(true);
        	this.mostrarNivel1=false;
        	this.mostrarNivel2=false;
        	this.mostrarNivel3=false;
        	this.mostrarNivel4=false;
        }
        
	}

	/**
	 * metodo auxiliar que me permite recuperar en cadena el valor de un objeto
	 * 
	 * @param obj
	 * @return
	 */
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return String.valueOf(obj);
	}	
	
	private void actualizarListarUbigeo(ConsultaHIPActualizacionDireccionClienteForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		//HttpSession session = request.getSession(true);
	
		//actualizamos las listas de ubigeo de Direccion Domicilio
		if(f.getNivel1().length()>0) {
			//session.setAttribute(Constants.MAE_CLIENTE_NIVEL2_LIST, ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel1()));
			setSTOlistaProvinciasSolicitudCredito(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel1()));
		}
		
		if(f.isMostrarCiudad()) {
			if(f.getNivel1().length()>0) {
				//session.setAttribute(Constants.MAE_CIUDAD_LIST, ajaxService.getCiudadesByRegion(f.getNivel1()));
				setMaeCiudadList(ajaxService.getCiudadesByRegion(f.getNivel1()));
			}
		}
		
		if(f.getNivel2().length()>0) {
			//session.setAttribute(Constants.MAE_CLIENTE_NIVEL3_LIST, ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel2()));
			setSTOlistaDistritosSolicitudCredito(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel2()));
		}
		if(f.getNivel3()!=null && f.getNivel3().length()>0) {
			//session.setAttribute(Constants.MAE_CLIENTE_NIVEL4_LIST, ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel3()));
			setSTOlistaSectoresSolicitudCredito(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel3()));
		}
		if(f.getNivel4()!=null && f.getNivel4().length()>0) {
			//session.setAttribute(Constants.MAE_CLIENTE_NIVEL5_LIST, ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel4()));
			setMaeClienteNivel5List(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel4()));
		}
		if(f.getNivel5()!=null && f.getNivel5().length()>0) {
			//session.setAttribute(Constants.MAE_CLIENTE_NIVEL6_LIST, ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel5()));
			setMaeClienteNivel6List(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel5()));
		}
		
	}
	
	/**
	 * inicializa los datos del formulario
	 * 
	 * @param f
	 * @param session
	 */
	private void limpiar(ConsultaHIPActualizacionDireccionClienteForm f) {
		f.setZona("");
		f.setTerritorio("");
		f.setCodigoZonaInicial("");
		f.setCodigoTerritorioInicial("");
		
		f.setNivel1("");
		f.setNivel2("");
		f.setNivel3("");
		f.setNivel4("");
		f.setNivel5("");
		f.setNivel6("");
		f.setTipoVia("");
		f.setNumeroPrincipal("");
		f.setNombreVia("");
		f.setObservacionDireccion("");
		f.setBarrio("");
		
		f.setConfirmacionTerritorio("");
		f.setMensajeConfirmacion("");
		
		f.setCambioZonaTerritorio(false);
		f.setConsultoraPasoPedido(false);
		
		f.setLongitudCodigoZona(MAE_LONGITUD_CODIGO_ZONA);
		f.setPermitirModificarUbigeo(true);
		f.setMostrarTipoVia(true);
		f.setMostrarNumeroPrincipal(true);
		
		f.setValidarCaracteres1("");
		f.setValidarCaracteres2("");
		f.setValidarCaracteres3("");
		
		f.setMostrarBarrio(false);
		
		f.setMostrarCiudad(false);
		f.setMostrarVillaPoblacion(false);
		f.setCodigoCiudad("");
		f.setVillaPoblacion("");
		
		f.setMensajeSaldoDeuda("");
		f.setMensajeBloqueoActivo("");
		
		f.setMostrarMensajeCambioPeriodoVigente(false);
		f.setMostrarMensajePeriodoFinCerrado(false);
		f.setMostrarMensajePedidoExtemporaneo(false);
		
		f.setEsMayorPeriodoVigente(false);
		f.setRequiereGenerarEstatus(false);
		f.setCodigoPeriodoIniUA("");
	}
	
	/**
	 * Seteamos los campos definidos en la tabla de modulo de Cliente x Pais
	 * 
	 * @param f
	 * @param clienteService
	 */
	private void setearCamposModuloPais(ConsultaHIPActualizacionDireccionClienteForm f, MantenimientoMAEClienteService clienteService) {
		String permitirModificarUbigeo = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_UBIGEO);
		String mostrarTipoVia = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_TIPO_VIA);
		String mostrarNumeroPrincipal = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_NUMERO_PRINCIPAL);
		
		String validarBarrio = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_BARRIO);

		String mostrarCiudad = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_CIUDAD);
		String mostrarVillaPoblacion = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_VILLA);
		
		if(permitirModificarUbigeo != null)
			f.setPermitirModificarUbigeo(false);
		else
			f.setPermitirModificarUbigeo(true);
		
		if(mostrarTipoVia != null) {
			f.setMostrarTipoVia(false);
			f.setTipoVia(clienteService.getOidTipoVia(mostrarTipoVia).toString());
		} else
			f.setMostrarTipoVia(true);
		
		if(mostrarNumeroPrincipal != null)
			f.setMostrarNumeroPrincipal(false);
		else
			f.setMostrarNumeroPrincipal(true);
		
		if(validarBarrio != null) 
			f.setMostrarBarrio(true);
		else
			f.setMostrarBarrio(false);
		
		if(mostrarCiudad != null) 
			f.setMostrarCiudad(true);
		else
			f.setMostrarCiudad(false);
		
		if(mostrarVillaPoblacion != null) 
			f.setMostrarVillaPoblacion(true);
		else
			f.setMostrarVillaPoblacion(false);

	}
	
	
	/**
	 * Obtener Datos del Cliente para Modificar Direccion y Unidad Administrativa
	 * 
	 * @param codigoPais
	 * @param codigoCliente
	 * @return
	 */
	private Cliente obtenerDatosCliente(MantenimientoMAEClienteService clienteService, String codigoPais, String oidCliente) {
		Cliente cliente = new Cliente();
		cliente.setOid(new Long(oidCliente));
		cliente.setCodigoPais(codigoPais);
		
		List listClienteSubTipo = clienteService.getListTipoSubtipoCliente(cliente.getOid().toString());
		cliente.setListClienteSubTipo(listClienteSubTipo);
		
		List listClienteDireccion = clienteService.getListDireccionCliente(cliente.getOid().toString());
		cliente.setListClienteDireccion(listClienteDireccion);		
		
		ClienteUnidadAdministrativa clienteUnidadAdministrativa = clienteService.getUnidadAdministrativaCliente(cliente.getOid().toString());
		cliente.setClienteUnidadAdministrativa(clienteUnidadAdministrativa);

		ClienteAdicional clienteAdicional = clienteService.getDatosAdicionalesCliente(cliente.getOid().toString());
		cliente.setClienteAdicional(clienteAdicional);
		
		cliente.setEsEjecutiva(clienteService.esEjecutiva(cliente.getOid().toString()));
		
		return cliente;
	}
	
	/**
	 * Obtener Obtener provincias al seleccionar departamento
	 * @param valueChangeEvent
	 */
	public void loadProvinciasChangeListener(ValueChangeEvent event){
		
		log.debug("loadProvinciasChangeListener method");
		String opc = (String)event.getNewValue();
		log.info(">>Departamento: "+opc);
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] result = new LabelValue[1];
		result[0] = new LabelValue("", "");
		
		ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
		f.setNivel1(opc);
		setSTOlistaProvinciasSolicitudCredito(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel1()));
		setSTOlistaDistritosSolicitudCredito(result);
		setSTOlistaSectoresSolicitudCredito(result);
		
		if(f.isMostrarCiudad()){
			setMaeCiudadList(ajaxService.getCiudadesByRegion(opc));
		}
   	}
	
	/**
	 * Obtener Obtener distritos al seleccionar provincia
	 * @param valueChangeEvent
	 */
	public void loadDistritosChangeListener(ValueChangeEvent event){
		
		log.debug("loadDistritosChangeListener method");
		String opc = (String)event.getNewValue();
		log.info(">>Provincia: "+opc);
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] result = new LabelValue[1];
		result[0] = new LabelValue("", "");
		
		ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
		f.setNivel2(opc);
		setSTOlistaDistritosSolicitudCredito(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel2()));
		setSTOlistaSectoresSolicitudCredito(result);
		
		if(f.isMostrarCiudad()){
			setMaeCiudadList(ajaxService.getCiudadesByRegion(opc));
		}
   	}
	
	/**
	 * Obtener Obtener sectores al seleccionar distrito
	 * @param valueChangeEvent
	 */
	public void loadSectoresChangeListener(ValueChangeEvent event){
		
		log.debug("loadSectoresChangeListener method");
		String opc = (String)event.getNewValue();
		log.info(">>Distrito: "+opc);
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] result = new LabelValue[1];
		result[0] = new LabelValue("", "");
		
		ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
		f.setNivel3(opc);
		setSTOlistaSectoresSolicitudCredito(ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getNivel3()));
		
		if(f.isMostrarCiudad()){
			setMaeCiudadList(ajaxService.getCiudadesByRegion(opc));
		}
   	}
	
	/**
	 * Graba la informacion de los datos del cliente 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void save(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()){
			log.debug("Entering 'save' method");
		}
		boolean activarCerrar = false;
		try{
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
			//ActionMessages messages = new ActionMessages();	
			
			Map criteria = BeanUtils.describe(f);
			criteria.put("codigoZona", f.getZona());
			criteria.put("codigoTerritorio", f.getTerritorio());
			
			//Cliente clienteAux = (Cliente)request.getSession().getAttribute(HIP_CLIENTE_DIRECCION);
			Cliente clienteAux = this.getClienteSession();
			boolean validacion = true;
			
			if(!f.isPermitirModificarUbigeo()){
		    	f.setNivel1(f.getCodNivel1());
		    	f.setNivel2(f.getCodNivel2());
		    	f.setNivel3(f.getCodNivel3());
		    	f.setNivel4(f.getCodNivel4());
		    	f.setNivel5(f.getCodNivel5());
		    	f.setNivel6(f.getCodNivel6());
		    }
			
			//Obtenemos la unidad geografica de la direccion principal
			String codigoUbigeo = "";
			if(f.getNivel6()!=null && !("".equals(f.getNivel6())))
				codigoUbigeo = f.getNivel6();
			else if(f.getNivel5()!=null && !("".equals(f.getNivel5())))
				codigoUbigeo = f.getNivel5();
			else if(f.getNivel4()!=null && !("".equals(f.getNivel4())))
				codigoUbigeo = f.getNivel4();
			else if(f.getNivel3()!=null && !("".equals(f.getNivel3())))
				codigoUbigeo = f.getNivel3();		
			criteria.put("codigoUbigeo", codigoUbigeo);
			
			//Verificamos si cambios Zona y Territorio
			evaluarCambioZonaTerritorio(f, criteria);
			criteria.put("listSubTipo", clienteAux.getListClienteSubTipo());
			criteria.put("mostrarUnidadAdministrativa","true");
			criteria.put("esDuplaCyzone","false");
			criteria.put("hiperconsulta","true");
			
			List erroresEncontrados = clienteService.validarDatosCliente(criteria);
			StringBuilder cadenaErrores = new StringBuilder();
			if(erroresEncontrados.size()>0) {
				Iterator it = erroresEncontrados.iterator();
				while(it.hasNext()) {
					String error = (String)it.next();
					
					if(error.equals("mantenimientoMAEClienteForm.msg.TerritorioNoCorrespondeDistrito")) {
						//MessageResources messageResources = getResources(request);
						//String texto = messageResources.getMessage(error);
						String texto = this.getResourceMessage(error);
						f.setMensajeConfirmacion(texto);
						validacion = false;
						break;
					}
					
					//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(error));
					//this.addError("Mensaje.", error);
					cadenaErrores.append(error + "\n");
					
				}
				
				it = erroresEncontrados.iterator();
				while(it.hasNext()) {
					String error = (String)it.next();
					
					if(error.equals("mantenimientoMAEClienteForm.msg.ZonaInactivaNoPermiteCambio")) {
						f.setPermitirGrabar(false);
					}
					
					cadenaErrores.append(error + "\n");
				}
	
				//saveErrors(request, messages);
				String[] lines = cadenaErrores.toString().split("\\n");
				
				if(!CollectionUtils.sizeIsEmpty(lines)){
					Set<String> noRepetidos = new HashSet<String>();					
					for(int i = 0; i< lines.length ; i++){
						if(!noRepetidos.add(lines[i])){
							log.debug("Elementos duplicados: " + lines[i]);
						}
					}
					for(String msg : noRepetidos){
						this.addError("Mensaje.", this.getResourceMessage(msg));
					}
					noRepetidos.clear();
				}
				validacion = false;
			} else {
				f.setOidTerritorioAdministrativo((String)criteria.get("oidTerritorioAdministrativo"));
				f.setOidTerritorio((String)criteria.get("oidTerritorio"));
			}
			
			if(validacion) {
				Cliente cliente = obtenerCliente(f);
				clienteService.updateDireccionClienteHiperConsulta(cliente);
			
				//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("consultaHIPActualizacionDireccionClienteForm.msg.graboOk"));
				//saveMessages(request.getSession(), messages);
				
				String msgConfirm = this.getResourceMessage("consultaHIPActualizacionDireccionClienteForm.msg.graboOk");
				this.addInfo("Mensaje.", msgConfirm);
				
				f.setGraboOK(true);
				activarCerrar = true;
			}
	
			//return mapping.findForward(getViewForward());
			
		}catch(Exception e){
			log.debug("Error al grabar. "+ e.getMessage());
			e.printStackTrace();
			this.addInfo("Error al grabar.", e.getMessage());
		}
		this.getRequestContext().addCallbackParam("activarCerrar", activarCerrar);
		
	}
	
	private void evaluarCambioZonaTerritorio(ConsultaHIPActualizacionDireccionClienteForm f, Map criteria) {
		boolean cambio = true;
		String confirmacionTerritorio = (String)criteria.get("confirmacionTerritorio");
		
		if(!confirmacionTerritorio.equals("ok")) {
			if((f.getCodigoZonaInicial().equals(f.getZona())) && 
				(f.getCodigoTerritorioInicial().equals(f.getTerritorio()))) {
					cambio = false;	
			}
			
			if(!cambio) {
				criteria.put("confirmacionTerritorio", "ok");
				f.setCambioZonaTerritorio(false);
			} else {
				criteria.put("confirmacionTerritorio", "");
				f.setCambioZonaTerritorio(true);
			}
		}
	}
	
	
	/**
	 * Se recupera los datos ingresados para el Cliente Modificado, en una clase DTO
	 * 
	 * @param request
	 * @param f
	 * @param listSubTipo
	 * @param listClasificacion
	 * @return
	 * @throws Exception
	 */
	private Cliente obtenerCliente(ConsultaHIPActualizacionDireccionClienteForm f) throws Exception {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(new Date(System.currentTimeMillis()));

		//Obtenemos el periodo Actual
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
				
		//Cliente cliente = (Cliente)request.getSession().getAttribute(HIP_CLIENTE_DIRECCION);
		Cliente cliente = this.getClienteSession();
		
		//DIRECCIONES DEL CLIENTE
		ClienteDireccion clienteDireccion = (ClienteDireccion)cliente.getListClienteDireccion().get(0);
		clienteDireccion.setOidTerritorio(new Long(f.getOidTerritorio()));
		
		if(f.getTipoVia()!=null && !("".equals(f.getTipoVia()))) 
			clienteDireccion.setOidTipoVia(new Long(f.getTipoVia()));
		clienteDireccion.setNombreVia(f.getNombreVia());
		clienteDireccion.setNumeroPrincipal(f.getNumeroPrincipal());
		clienteDireccion.setObservaciones(f.getObservacionDireccion());
		clienteDireccion.setBarrio(f.getBarrio());
		clienteDireccion.setIndicadorDireccionPrincipal(new Integer(1));
		clienteDireccion.setIndicadorEliminacion(new Integer(0));
		
		if(f.getCodigoCiudad()!=null && !("".equals(f.getCodigoCiudad()))) {
			StringTokenizer stCodigoCiudad = new StringTokenizer(f.getCodigoCiudad(), "__");
			clienteDireccion.setCodigoUbigeo1(stCodigoCiudad.nextToken());
			clienteDireccion.setCodigoCiudad(stCodigoCiudad.nextToken());
		}
		clienteDireccion.setVillaPoblacion(f.getVillaPoblacion());
		
		if(f.getNivel6()!=null && !("".equals(f.getNivel6())))
			clienteDireccion.setCodigoUnidadGeografica(f.getNivel6());
		else if(f.getNivel5()!=null && !("".equals(f.getNivel5())))
			clienteDireccion.setCodigoUnidadGeografica(f.getNivel5());
		else if(f.getNivel4()!=null && !("".equals(f.getNivel4())))
			clienteDireccion.setCodigoUnidadGeografica(f.getNivel4());
		else if(f.getNivel3()!=null && !("".equals(f.getNivel3())))
			clienteDireccion.setCodigoUnidadGeografica(f.getNivel3());

		
		//UNIDAD ADMINISTRATIVA DEL CLIENTE
		if(f.isCambioZonaTerritorio()) {
			ClienteUnidadAdministrativa clienteUnidadAdministrativaOld = cliente.getClienteUnidadAdministrativa();
			ClienteUnidadAdministrativa clienteUnidadAdministrativaNew = new ClienteUnidadAdministrativa();
			
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("oidPeriodo", f.getOidPeriodo());
			Base basePeriodoFin =  clienteService.getPeriodoAnterior(criteriaPeriodo);
			String oidPeriodoFin = basePeriodoFin.getCodigo();
			criteriaPeriodo.put("oidPeriodo", oidPeriodoFin);
			
			boolean esPeriodoFinCerrado = clienteService.esPeriodoCerrado(criteriaPeriodo);
			Integer indActivoUANuevo;
			Integer indActivoUAAnterior;
			
			if (!esPeriodoFinCerrado && f.isConsultoraPasoPedido()) {
	            indActivoUANuevo = new Integer(0);
	            indActivoUAAnterior = new Integer(1);
	            f.setMostrarMensajePeriodoFinCerrado(true);
	        } else {
	            indActivoUANuevo = new Integer(1);
	            indActivoUAAnterior = new Integer(0);
	        }  

			//Actualizamos el periodo Fin de la actual unidad administrativa
			if(clienteUnidadAdministrativaOld != null) {
				clienteUnidadAdministrativaOld.setPeriodoFin(new Long(oidPeriodoFin));
				clienteUnidadAdministrativaOld.setIndicadorActivo(indActivoUAAnterior);
			
				//si es registrada y no paso pedido, que limpie la unidad administrativa
				if(f.getCodigoEstatus().equals(Constants.MAE_ESTADO_REGISTRADA) && !f.isConsultoraPasoPedido())
					clienteUnidadAdministrativaOld.setEsPeriodoInicioMayorIgualPeriodoVigente(true); 
				else  //verificamos si el periodo de inicio de la unidad administrativa ultima, es mayor o igual al periodo vigente
					clienteUnidadAdministrativaOld.setEsPeriodoInicioMayorIgualPeriodoVigente(
							validarPeriodoInicioMayorAPeriodoVigente(clienteUnidadAdministrativaOld.getPeriodoInicio().toString(), 
									f.getOidPeriodo(), periodos));
			}	
						

			//creamos una nueva unidad administrativa
			clienteUnidadAdministrativaNew.setOidCliente(cliente.getOid());
			clienteUnidadAdministrativaNew.setOidTerritorioAdministrativo(new Long(f.getOidTerritorioAdministrativo()));
			clienteUnidadAdministrativaNew.setIndicadorActivo(new Integer(1));
			clienteUnidadAdministrativaNew.setPeriodoInicio(new Long(f.getOidPeriodo()));
			clienteUnidadAdministrativaNew.setPeriodoFin(null);
			clienteUnidadAdministrativaNew.setIndicadorActivo(indActivoUANuevo);
			clienteUnidadAdministrativaNew.setCodigoZona(f.getZona());
			
			cliente.setClienteUnidadAdministrativaNew(clienteUnidadAdministrativaNew);
		}
		
		//guardamos historial de cambio de datos del cliente
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ClienteHistoricoDatos clienteHistoricoDatos = cliente.getClienteHistoricoDatos();
		
		if(StringUtils.isNotEmpty(f.getNivel6()))
			clienteHistoricoDatos.setUbigeo(f.getNivel6());
		if(StringUtils.isNotEmpty(f.getNivel5()))
			clienteHistoricoDatos.setUbigeo(f.getNivel5());
		if(StringUtils.isNotEmpty(f.getNivel4()))
			clienteHistoricoDatos.setUbigeo(f.getNivel4());
		if(StringUtils.isNotEmpty(f.getNivel3()))
			clienteHistoricoDatos.setUbigeo(f.getNivel3());
		
		clienteHistoricoDatos.setTipoVia(f.getTipoVia());
		clienteHistoricoDatos.setNumeroPrincipal(f.getNumeroPrincipal());
		clienteHistoricoDatos.setBarrio(f.getBarrio());
		clienteHistoricoDatos.setDireccion(f.getNombreVia());
		clienteHistoricoDatos.setReferencia(f.getObservacionDireccion());

		clienteHistoricoDatos.setZona(f.getZona());
		clienteHistoricoDatos.setTerritorio(f.getTerritorio());
		
		//Obtenemos codigoRegion y Codigo de Seccion
		if(f.isCambioZonaTerritorio()) {
			Map mapRegionSeccion = clienteService.getCodigoRegionySeccion(f.getZona(), f.getTerritorio());
			
			if(mapRegionSeccion!=null) {
				clienteHistoricoDatos.setRegion(MapUtils.getString(mapRegionSeccion, "codigoRegion"));
				clienteHistoricoDatos.setSeccion(MapUtils.getString(mapRegionSeccion, "codigoSeccion"));
			} else {
				clienteHistoricoDatos.setRegion(f.getCodigoRegion());
				clienteHistoricoDatos.setSeccion(f.getCodigoSeccion());
			}
		} else {
			clienteHistoricoDatos.setRegion(f.getCodigoRegion());
			clienteHistoricoDatos.setSeccion(f.getCodigoSeccion());
		}
		
		clienteHistoricoDatos.setCodigoUsuario(usuario.getLogin());
		cliente.setClienteHistoricoDatos(clienteHistoricoDatos);
		cliente.setCodigoUsuario(usuario.getLogin());
		
		return cliente;
	}
	
	public void validarZonaTerritorio() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarZonaTerritorio' method");
		}
		ConsultaHIPActualizacionDireccionClienteForm f = (ConsultaHIPActualizacionDireccionClienteForm) this.formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		f.setPermitirGrabar(true);
		
		Map criteria = BeanUtils.describe(f);
		criteria.put("hiperconsulta","true");
		criteria.put("codigoZona", f.getZona());
		criteria.put("codigoTerritorio", f.getTerritorio());
		
		List resultados = clienteService.validarZonaTerritorio(criteria);
		String result = (String)resultados.get(0);
		
		if(result.equals("ok")) {
			//if(f.isTieneDireccionPrincipal()) {
				Map mapUbigeo = (Map)resultados.get(1);
				f.setNivel1((String)mapUbigeo.get("ubigeo1"));
				f.setCodNivel1(f.getNivel1());
				
				if((String)mapUbigeo.get("ubigeo2")!=null)
					f.setNivel2(f.getNivel1() + (String)mapUbigeo.get("ubigeo2"));
				if((String)mapUbigeo.get("ubigeo3")!=null)
					f.setNivel3(f.getNivel2() + (String)mapUbigeo.get("ubigeo3"));
				if((String)mapUbigeo.get("ubigeo4")!=null)
					f.setNivel4(f.getNivel3() + (String)mapUbigeo.get("ubigeo4"));
				if((String)mapUbigeo.get("ubigeo5")!=null)
					f.setNivel5(f.getNivel4() + (String)mapUbigeo.get("ubigeo5"));
				if((String)mapUbigeo.get("ubigeo6")!=null)
					f.setNivel6(f.getNivel5() + (String)mapUbigeo.get("ubigeo6"));
				
				f.setCodNivel2(f.getNivel2());
				f.setCodNivel3(f.getNivel3());
				f.setCodNivel4(f.getNivel4());
				f.setCodNivel5(f.getNivel5());
				f.setCodNivel6(f.getNivel6());
			//}
			
			//Verificamos que haya cambio de Zona y Territorio, si ese es el caso, se evaluar periodo actual
			boolean cambio = true;
			
			if((f.getCodigoZonaInicial().equals(f.getZona())) && 
				(f.getCodigoTerritorioInicial().equals(f.getTerritorio()))) {
				cambio = false;	
			}
			
			f.setCambioZonaTerritorio(cambio);
			
			//f.setActualizaUbigeoDirecciones(true);
			
			//Si se aplica calculo de periodo de ingreso en base al cierre de Region y Zona 
			//boolean esPaisCalculoPeriodoIngreso = clienteService.esPaisCalculaPeriodoIngreso(f.getCodigoPais());
			//if(esPaisCalculoPeriodoIngreso)
			
			obtenerPeriodoActual(clienteService, f);
			
			if(f.isMostrarMensajePedidoExtemporaneo()) {
				this.addInfo("", this.getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.regionZonaExtemporaneo"));
			}
			
			if(f.isMostrarMensajeCambioPeriodoVigente()) {
				this.addInfo("", this.getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.cambioPeriodoVigenteo"));
			}
		        
			/*if(f.isPermitirModificarUbigeo())
				f.setControlFoco(MAE_CONTROL_NIVEL1);
			else
				f.setControlFoco(MAE_CONTROL_NOMBREVIA);*/
		}
		else {
			Iterator it = resultados.iterator();
			while(it.hasNext()) {
				String error = (String)it.next();
				
				if(error.equals("mantenimientoMAEClienteForm.msg.ZonaInactivaNoPermiteCambio")) {
					f.setPermitirGrabar(false);
				}
				
				this.addError("", this.getResourceMessage(error));
						
			}

		} 

		//actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

	}
	
	/**
	 * Obtiene Periodo Actual, validando si la region de la zona de la consultora ha cerrado para las campañas vigentes
	 * y si la consultora ha pasado pedido en los periodos vigentes
	 * 
	 * @param date
	 * @return
	 */
	private void obtenerPeriodoActual(MantenimientoMAEClienteService clienteService,  
							ConsultaHIPActualizacionDireccionClienteForm f) {
		//datos del cliente
		Cliente cliente = this.getClienteSession();
        
        Map resultados = clienteService.obtenerDatosCambioUA(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,	
        						cliente.getOid().toString(), f.getCodigoZonaInicial(), f.getZona());
		
        log.debug("resultados obtenerDatosCambioUA : " + resultados);
        
        String oidPeriodo = (String)resultados.get("oidPeriodo");
        String codigoPeriodo = (String)resultados.get("codigoPeriodo");
        String mostrarPedidoExtemporaneo = (String)resultados.get("mostrarPedidoExtemporaneo");
        String mostrarMensajeCambioPeriodoVigente = (String)resultados.get("mostrarMensajeCambioPeriodoVigente");
        String indicadorPasoPedido = (String)resultados.get("indicadorPasoPedido");
        String requiereGenerarEstatus = (String)resultados.get("requiereGenerarEstatus");
      
        if(Constants.SI.equals(mostrarPedidoExtemporaneo))
        	f.setMostrarMensajePedidoExtemporaneo(true);
        else
        	f.setMostrarMensajePedidoExtemporaneo(false);
        
        if(Constants.SI.equals(mostrarMensajeCambioPeriodoVigente))
        	f.setMostrarMensajeCambioPeriodoVigente(true);
        else
        	f.setMostrarMensajeCambioPeriodoVigente(false);
        
        if(Constants.SI.equals(indicadorPasoPedido))
        	f.setConsultoraPasoPedido(true);
        else
        	f.setConsultoraPasoPedido(false);
        
        if(Constants.SI.equals(requiereGenerarEstatus))
        	f.setRequiereGenerarEstatus(true);
        else
        	f.setRequiereGenerarEstatus(false);
        
        //RECUPERAMOS LOS PERIODOS VIGENTES
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
      	LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
      		
      	//Verificamos si el periodo calculado se encuentra dentro de los periodos vigentes
		boolean esPeriodoVigente = false;
		for(int i=0; i < periodos.length; i++) {
			LabelValue periodo = periodos[i];
			
			if(periodo.getValue().equals(oidPeriodo)) {
				esPeriodoVigente = true;
			}
		}
		
		//Verificamos si el periodo Inicial Calculado es mayor al periodo Vigente
		boolean esMayorPeriodoVigente = false;
		if(Integer.parseInt(codigoPeriodo) > Integer.parseInt(periodos[0].getLabel()))
			esMayorPeriodoVigente = true;
		f.setEsMayorPeriodoVigente(esMayorPeriodoVigente);
		
		if(!esPeriodoVigente) {
			LabelValue[] periodosAux = new LabelValue[periodos.length+1];
			for(int j=0; j< periodos.length; j++) {
				periodosAux[j] = periodos[j];
			}
		
			LabelValue lblSiguientePeriodo = new LabelValue(codigoPeriodo, oidPeriodo);
			periodosAux[periodos.length] = lblSiguientePeriodo;
			
			//session.setAttribute(Constants.SICC_PERIODO_LIST, periodosAux);
		} else {
			//session.setAttribute(Constants.SICC_PERIODO_LIST, periodos);
		}
		
		f.setOidPeriodo(oidPeriodo);
		f.setCodigoPeriodoIniUA(codigoPeriodo);
	}
	
	private boolean validarPeriodoInicioMayorAPeriodoVigente(String oidPeriodoInicio, String oidPeriodoVigente, 
			LabelValue[] listPeriodosVigentes) {
		MantenimientoMAEClienteService clienteService = 
								(MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		LabelValue basePeriodoVigente = listPeriodosVigentes[0];
		boolean 	esPeriodoMayorIgual = false;

		String codigoPeriodoInicio = clienteService.getCodigoPeriodoByOidPeriodo(oidPeriodoInicio);

		if(codigoPeriodoInicio.compareTo(basePeriodoVigente.getLabel()) >= 0 ) {
			esPeriodoMayorIgual = true;
		}

		return esPeriodoMayorIgual;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public LabelValue[] getSTOlistaProvinciasSolicitudCredito() {
		return STOlistaProvinciasSolicitudCredito;
	}

	public void setSTOlistaProvinciasSolicitudCredito(
			LabelValue[] sTOlistaProvinciasSolicitudCredito) {
		STOlistaProvinciasSolicitudCredito = sTOlistaProvinciasSolicitudCredito;
	}

	public LabelValue[] getSTOlistaDistritosSolicitudCredito() {
		return STOlistaDistritosSolicitudCredito;
	}

	public void setSTOlistaDistritosSolicitudCredito(
			LabelValue[] sTOlistaDistritosSolicitudCredito) {
		STOlistaDistritosSolicitudCredito = sTOlistaDistritosSolicitudCredito;
	}

	public LabelValue[] getSTOlistaSectoresSolicitudCredito() {
		return STOlistaSectoresSolicitudCredito;
	}

	public void setSTOlistaSectoresSolicitudCredito(
			LabelValue[] sTOlistaSectoresSolicitudCredito) {
		STOlistaSectoresSolicitudCredito = sTOlistaSectoresSolicitudCredito;
	}

	public LabelValue[] getMaeClienteNivel5List() {
		return maeClienteNivel5List;
	}

	public void setMaeClienteNivel5List(LabelValue[] maeClienteNivel5List) {
		this.maeClienteNivel5List = maeClienteNivel5List;
	}

	public LabelValue[] getMaeClienteNivel6List() {
		return maeClienteNivel6List;
	}

	public void setMaeClienteNivel6List(LabelValue[] maeClienteNivel6List) {
		this.maeClienteNivel6List = maeClienteNivel6List;
	}

	public LabelValue[] getMaeCiudadList() {
		return maeCiudadList;
	}

	public void setMaeCiudadList(LabelValue[] maeCiudadList) {
		this.maeCiudadList = maeCiudadList;
	}

	public List getMaeCiudadCTList() {
		return maeCiudadCTList;
	}

	public void setMaeCiudadCTList(List maeCiudadCTList) {
		this.maeCiudadCTList = maeCiudadCTList;
	}

	public LabelValue[] getMaeClienteNivel1List() {
		return maeClienteNivel1List;
	}

	public void setMaeClienteNivel1List(LabelValue[] maeClienteNivel1List) {
		this.maeClienteNivel1List = maeClienteNivel1List;
	}

	public List getMaeClienteTipoViaList() {
		return maeClienteTipoViaList;
	}

	public void setMaeClienteTipoViaList(List maeClienteTipoViaList) {
		this.maeClienteTipoViaList = maeClienteTipoViaList;
	}

	public Cliente getClienteSession() {
		return clienteSession;
	}

	public void setClienteSession(Cliente clienteSession) {
		this.clienteSession = clienteSession;
	}

	/**
	 * @return the permitirGrabar
	 */
	public boolean isPermitirGrabar() {
		return permitirGrabar;
	}

	/**
	 * @param permitirGrabar the permitirGrabar to set
	 */
	public void setPermitirGrabar(boolean permitirGrabar) {
		this.permitirGrabar = permitirGrabar;
	}

	/**
	 * @return the mensajeValidacionDeuda
	 */
	public String getMensajeValidacionDeuda() {
		return mensajeValidacionDeuda;
	}

	/**
	 * @param mensajeValidacionDeuda the mensajeValidacionDeuda to set
	 */
	public void setMensajeValidacionDeuda(String mensajeValidacionDeuda) {
		this.mensajeValidacionDeuda = mensajeValidacionDeuda;
	}

	/**
	 * @return the divDireccionDomicilio4
	 */
	public boolean isDivDireccionDomicilio4() {
		return divDireccionDomicilio4;
	}

	/**
	 * @param divDireccionDomicilio4 the divDireccionDomicilio4 to set
	 */
	public void setDivDireccionDomicilio4(boolean divDireccionDomicilio4) {
		this.divDireccionDomicilio4 = divDireccionDomicilio4;
	}

	/**
	 * @return the divDireccionDomicilio51
	 */
	public boolean isDivDireccionDomicilio51() {
		return divDireccionDomicilio51;
	}

	/**
	 * @param divDireccionDomicilio51 the divDireccionDomicilio51 to set
	 */
	public void setDivDireccionDomicilio51(boolean divDireccionDomicilio51) {
		this.divDireccionDomicilio51 = divDireccionDomicilio51;
	}

	/**
	 * @return the mostrarNivel1
	 */
	public boolean isMostrarNivel1() {
		return mostrarNivel1;
	}

	/**
	 * @param mostrarNivel1 the mostrarNivel1 to set
	 */
	public void setMostrarNivel1(boolean mostrarNivel1) {
		this.mostrarNivel1 = mostrarNivel1;
	}

	/**
	 * @return the mostrarNivel2
	 */
	public boolean isMostrarNivel2() {
		return mostrarNivel2;
	}

	/**
	 * @param mostrarNivel2 the mostrarNivel2 to set
	 */
	public void setMostrarNivel2(boolean mostrarNivel2) {
		this.mostrarNivel2 = mostrarNivel2;
	}

	/**
	 * @return the mostrarNivel3
	 */
	public boolean isMostrarNivel3() {
		return mostrarNivel3;
	}

	/**
	 * @param mostrarNivel3 the mostrarNivel3 to set
	 */
	public void setMostrarNivel3(boolean mostrarNivel3) {
		this.mostrarNivel3 = mostrarNivel3;
	}

	/**
	 * @return the mostrarNivel4
	 */
	public boolean isMostrarNivel4() {
		return mostrarNivel4;
	}

	/**
	 * @param mostrarNivel4 the mostrarNivel4 to set
	 */
	public void setMostrarNivel4(boolean mostrarNivel4) {
		this.mostrarNivel4 = mostrarNivel4;
	}
	
	
}
