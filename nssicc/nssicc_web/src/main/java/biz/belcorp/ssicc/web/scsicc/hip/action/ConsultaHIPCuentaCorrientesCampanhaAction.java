/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Precision;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.ColumnModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteCuentaCorrienteCampanhaAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCuentaCorrientesCampanha2Form;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCuentaCorrientesCampanhaForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;

@ManagedBean
@SessionScoped
public class ConsultaHIPCuentaCorrientesCampanhaAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	@ManagedProperty(value="#{reporteCuentaCorrienteCampanhaAction}")
	private ReporteCuentaCorrienteCampanhaAction reporteCuentaCorrienteCampanhaAction;
	
	private Map hipCuentaCorrientesCampanhaMovimientoCabecera;	
	
	private List listaCabecera = new ArrayList();
	private List listMovimientosCuentaCorriente = new ArrayList();
	private List listaCabeceraPbLc = new ArrayList();
	private List listaDetallePbLc = new ArrayList();
	
	
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<ColumnModel> columns1 = new ArrayList<ColumnModel>();
	
	private String indicadorPedidoPeriodo;
	private String indicadorPedidoPeriodoSiguiente;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPCuentaCorrientesCampanhaForm consultaHIPCuentaCorrientesCampanhaForm = new ConsultaHIPCuentaCorrientesCampanhaForm();		
		return consultaHIPCuentaCorrientesCampanhaForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPCuentaCorrientesCampanhaAction - setViewAtributes' method");
		}    
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
		if(log.isDebugEnabled()){
			log.debug("Entering 'ConsultaHIPCuentaCorrientesCampanhaAction - setViewAtributes' method");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService mopcfService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		ConsultaHIPCuentaCorrientesCampanhaForm f = (ConsultaHIPCuentaCorrientesCampanhaForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		//Obtenemos los datos del cliente (ya que no se pueden obtener de la session de usuario) /////////////////////
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());

		//Recuperamos el idioma
		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
		String s = this.getmPantallaPrincipalBean().getCurrentIdioma().getCodigoISO();
		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		
		//obtenemos la campaña activa		
		MantenimientoOCRPedidoControlFacturacionService service5 = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService" );
		Map criteria5 = new HashMap();
		String campanhaActivo = null;
		criteria5.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo()); 
		criteria5.put("estadoCampanha", "0" ); 
		criteria5.put("indicadorActiva", "1" ); 
		List lista5 = service5.getCampanhasActivasByCriteria(criteria5); 
		if (lista5.size()==1) { 
			campanhaActivo = (String) lista5.get(0); 
		}
		
		Map criterios = new HashMap();
		criterios.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criterios.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criterios.put("oidIdioma", oidIdiomaIso);
		criterios.put("campanhaActivo", campanhaActivo);
		
		Map mapDatosGenerales = service.getDatosGenerales(criterios);
		
		dtoDatosCliente.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		dtoDatosCliente.setOidCliente(MapUtils.getString(mapDatosGenerales, "oidCliente", ""));
		dtoDatosCliente.setCodigoCliente(MapUtils.getString(mapDatosGenerales, "codigoCliente", ""));
		
		String nombreCompleto = MapUtils.getString(mapDatosGenerales, "nombre1", "") + " " +
								MapUtils.getString(mapDatosGenerales, "nombre2", "") + " " +
								MapUtils.getString(mapDatosGenerales, "apellido1", "") + " " + 
								MapUtils.getString(mapDatosGenerales, "apellido2", "");
		
		dtoDatosCliente.setNombreCompleto(nombreCompleto);
		dtoDatosCliente.setEstadoFlx(MapUtils.getString(mapDatosGenerales, "estadoFlx", ""));
		dtoDatosCliente.setFechaActivacionFlx(MapUtils.getString(mapDatosGenerales, "fechaActivacionFlx", ""));
		
		//obtenemos el motivo del bloqueo de la consultora
		String bloqueo = service.getMotivoBloqueo(criterios);
		if(StringUtils.isNotBlank(bloqueo)) {
			StringTokenizer st = new StringTokenizer(bloqueo, "__");
			dtoDatosCliente.setCodigoBloqueo(st.nextToken());
			dtoDatosCliente.setBloqueo(st.nextToken());
			f.setBloqueo(dtoDatosCliente.getBloqueo());
		} 
		
		//obtenemos el saldo unico de la consultora
		criterios.put("oidCliente", dtoDatosCliente.getOidCliente());
		String su = service.getSaldoUnico(criterios);
		dtoDatosCliente.setSaldoUnico(su);
		
		//obtenemos las unidades administrativas de la consultora
		List listUnidadesAdministrativas = service.getUnidadesAdministrativas(criterios);
		if(listUnidadesAdministrativas!=null && listUnidadesAdministrativas.size()>0){
			for(int i=0; i<listUnidadesAdministrativas.size(); i++){
				Map mapUnidadAdministrativa = (Map)listUnidadesAdministrativas.get(i);
				
				String indicadorActividad = MapUtils.getString(mapUnidadAdministrativa, "indicadorActividad", "");
				
				if(StringUtils.equals(indicadorActividad, Constants.ESTADO_ACTIVO)){
					
					dtoDatosCliente.setDescripcionRegion(MapUtils.getString(mapUnidadAdministrativa, "descripcionRegion", ""));
					dtoDatosCliente.setDescripcionZona(MapUtils.getString(mapUnidadAdministrativa, "descripcionZona", ""));
					dtoDatosCliente.setCodigoTerritorio(MapUtils.getString(mapUnidadAdministrativa, "codigoTerritorio", ""));
					dtoDatosCliente.setCodigoMarca(MapUtils.getString(mapUnidadAdministrativa, "codigoMarca", ""));					
					dtoDatosCliente.setCodigoCanal(MapUtils.getString(mapUnidadAdministrativa, "codigoCanal", ""));
					
					dtoDatosCliente.setCodigoRegion(MapUtils.getString(mapUnidadAdministrativa, "codigoRegion", ""));
					dtoDatosCliente.setCodigoZona(MapUtils.getString(mapUnidadAdministrativa, "codigoZona", ""));
				}
			}
		}
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() + " / " + dtoDatosCliente.getCodigoTerritorio());
		f.setEstatusFlx(dtoDatosCliente.getEstadoFlx());
		f.setFechaActivacionFlx(dtoDatosCliente.getFechaActivacionFlx());
		
		//recuperamos el saldo unico de la Consultora
		f.setSaldoUnico(dtoDatosCliente.getSaldoUnico());
		
		//recuperamos el saldo a pagar para el proximo pedido
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
		criteria.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("codigoZona", dtoDatosCliente.getCodigoZona());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		
		Map criteria3 = new HashMap();
		criteria3.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria3.put("estadoCampanha", "0");
		criteria3.put("indicadorActiva", "1");
		
		List lista = mopcfService.getCampanhasActivasByCriteria(criteria3);
	
		Map criteria4 = new HashMap();
		criteria4.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria4.put("campaña", (lista.size() == 1) ? (String) lista.get(0) : "");
		
		Double saldo = service.getSaldoCupon3(criteria4);
		
		f.setSaldoCupon3( (saldo != null) ? String.valueOf(saldo) : "0");
		
		try {
			f.setSaldoPagar(service.getSaldoPagar(criteria));
			
			try {
				f.setPagosPosteriores(String.valueOf(Double.parseDouble(f.getSaldoUnico()) - 
													 Double.parseDouble(f.getSaldoPagar())));
			} catch (Exception ex) {
				f.setPagosPosteriores("");
			}
		} catch (Exception ex) {
			f.setSaldoPagar("");
		}	

		try {
			Map saldos = service.getSaldoVencidos(criteria);
			f.setSaldoVencido((String)saldos.get("saldoVencido"));
			f.setSaldoPorVencer((String)saldos.get("saldoPorVencer"));
		} catch (Exception ex) {
			f.setSaldoVencido("");
			f.setSaldoPorVencer("");
		}	
		
		double saldoVencido =Double.parseDouble(f.getSaldoVencido());
		double saldoPorVencer =Double.parseDouble(f.getSaldoPorVencer());
		double saldoPagar =Double.parseDouble(f.getSaldoPagar());
		double saldoCupon3 =Double.parseDouble(f.getSaldoCupon3());
		
		double saldoTotal =  saldoVencido + saldoPorVencer;
		double pagosPosteriores = saldoTotal - saldoPagar;
		double pagohoy = saldoTotal - saldoCupon3;
		
		f.setSaldoTotal( String.valueOf(saldoTotal));
		f.setPagosPosteriores(String.valueOf(pagosPosteriores));
		f.setPagoHoy(String.valueOf(pagohoy));
		
		//obtenemos el bloqueo de la consultora
		String fechaCastigada = service.getFechaCastigada(criteria);
		
		if(fechaCastigada != null) {
			f.setBloqueo("INCOBRABLE");
		} else {
			f.setBloqueo(dtoDatosCliente.getBloqueo());
		}
		
		//obtenemos los movimientos de Cuenta Corriente de la consultora		
		ConsultaCOBTelecobroService serviceCobranza = (ConsultaCOBTelecobroService)getBean("spusicc.consultaCOBTelecobroService");
		Map criteria2 = new HashMap();
		criteria2.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria2.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria2.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
		criteria2.put("saldoTotal", saldoTotal);
		criteria2.put("numeroCampanias", Constants.NUMERO_CUATRO);
		criteria2.put("codigoPeriodo", campanhaActivo);
				
		this.setHipCuentaCorrientesCampanhaMovimientoCabecera(criteria2);
		
		Map datos = serviceCobranza.getDetalleConsultoraCampanha(criteria2);
		
		List listaCabecera = (List)datos.get("cabecera");
		List listMovimientosCuentaCorriente = (List)datos.get("detalle"); 

		Double saldoTotalPenultimoFlx;
		Double saldoTotalFlx;
		
		int cantidadMovi = listMovimientosCuentaCorriente.size();
		Map ultimoMovi = (Map)listMovimientosCuentaCorriente.get(cantidadMovi - 1);
		String campanhaAnterior = String.valueOf(Integer.parseInt(campanhaActivo) - 1);
		saldoTotalPenultimoFlx = (Double)ultimoMovi.get(campanhaAnterior);
		
		int cantidadCabe = listaCabecera.size();
		Map ultimoCabe = (Map)listaCabecera.get(cantidadCabe - 1);
		String ultimaCampanya = (String)ultimoCabe.get("codigo");
		saldoTotalFlx = (Double)ultimoMovi.get(ultimaCampanya);
		
		if(saldoTotalPenultimoFlx == null)
			saldoTotalPenultimoFlx = saldoTotalFlx;
		
		//String.valueOf(saldoTotalPenultimoFlx.intValue())
		//Obtenemos el Pago Mínimo para pasar pedido sin usar Flexipago
		f.setPagoMinimoPenultimo(saldoTotalPenultimoFlx.toString());
		
		//Obtenemos el Pago total de la penultima campaña 
		f.setPagoTotalPenultimo(saldoTotalFlx.toString());
		
		int validaPedido = serviceCobranza.getPedidoCampanhaProceso(criteria2);
		int validaCierreRegion = serviceCobranza.getCierreRegionCampanhaProceso(criteria2);
		
		if( (validaPedido > 0 && saldoTotalPenultimoFlx == 0) || (validaCierreRegion == 0 && saldoTotalPenultimoFlx == 0) )
			this.setIndicadorPedidoPeriodo(Constants.NUMERO_UNO);
		
		if( validaPedido == 0 && validaCierreRegion > 0)
			this.setIndicadorPedidoPeriodoSiguiente(Constants.NUMERO_UNO);
				
		this.setListaCabecera(listaCabecera);
		this.setListMovimientosCuentaCorriente(listMovimientosCuentaCorriente);
		this.crearColumnasDinamicas(datos, "1");
		
		Map criteria6 = new HashMap();
		criteria6.put("codigoPeriodo", campanhaActivo);
		Integer oidPeriodo=service.getOidPeriodo(criteria6);
		
		Map criteria7 = new HashMap();
		criteria7.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		Integer oidCliente = service.getOidCliente(criteria7);
		
		//obtenemos el primer monto minimo
		Map criteria8 = new HashMap();
		criteria8.put("oidCliente", oidCliente);
		criteria8.put("codigoPeriodo", campanhaActivo);
		criteria8.put("oidPeriodo", oidPeriodo);
		Double montoMinimo=service.getMontoMinimo(criteria8);
		
		Map criteria9 = new HashMap();
		criteria9.put("campanias", 1);
		criteria9.put("codigoPeriodo", campanhaActivo);
		String periodoSiguiente = service.getDesPeriodoByCodigoPeriodoX(criteria9);
		
		Map criteria10 = new HashMap();
		criteria10.put("codigoPeriodo", periodoSiguiente);
		Integer oidPeriodoSiguiente=service.getOidPeriodo(criteria10);
		
		Map criteria11 = new HashMap();
		criteria11.put("oidCliente", oidCliente);
		criteria11.put("codigoPeriodo", periodoSiguiente);
		criteria11.put("oidPeriodo", oidPeriodoSiguiente);
		Double montoMinimoSiguiente=service.getMontoMinimo(criteria11);
		
		
		Map criteria12 = new HashMap();
		criteria12.put("oidCliente", oidCliente);
		criteria12.put("oidPeriodo", oidPeriodo);
		Map aplicaMontoMinimo = service.getAplicaMontoMinimo(criteria12);
		
		Map criteria13 = new HashMap();
		criteria13.put("oidCliente", oidCliente);
		criteria13.put("oidPeriodo", oidPeriodoSiguiente);
		Map aplicaMontoMinimoSiguiente = service.getAplicaMontoMinimo(criteria13);
		
		if(aplicaMontoMinimo==null || aplicaMontoMinimo.size()==0){
			f.setAplicaPeriodo("No Aplica");
			f.setMontoMinimo("");
		}
		else
			if ( montoMinimo < 0 ){				
				f.setAplicaPeriodo("Pagado");
				f.setMontoMinimo("");
			}
			else{
				f.setAplicaPeriodo("");
				f.setMontoMinimo(montoMinimo.toString());
			}
		
		if(aplicaMontoMinimoSiguiente==null || aplicaMontoMinimoSiguiente.size()==0){
			f.setAplicaPeriodoSiguiente("No Aplica");
			f.setMontoMinimoSiguiente("");
		}
		else
			if ( montoMinimoSiguiente < 0 ){				
				f.setAplicaPeriodoSiguiente("Pagado");
				f.setMontoMinimoSiguiente("");
			}
			else{
				f.setAplicaPeriodoSiguiente("");
				f.setMontoMinimoSiguiente(montoMinimoSiguiente.toString());
			}
		
				
		f.setPeriodo(campanhaActivo);
		f.setPeriodoSiguiente(periodoSiguiente);
		
		//Obtenemos el pago total de la ultima campaña  
		
		Map criteriaCampAnt = new HashMap();
		criteriaCampAnt.put("campanias", -1);
		criteriaCampAnt.put("codigoPeriodo", campanhaActivo);
		criteriaCampAnt.put("oidCliente", oidCliente);
		criteriaCampAnt.put("codigoPeriodo", campanhaActivo);
		String pagoTotalUltimo = service.getSaldoACampana(criteriaCampAnt);
		f.setPagoTotalUltimo(String.valueOf(pagoTotalUltimo));
		//
		
		//Obtenemos los pedidos base y lineas de credito de campanhaActivo-1, campanhaActivo, campanhaActivo+1
		Map pblcParams = new HashMap();
		pblcParams.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		pblcParams.put("codigoPeriodo", campanhaActivo);
		pblcParams.put("codigoConsultora", dtoDatosCliente.getCodigoCliente());
		
		Map datosPbLc = service.getPedidoBaseLineaCredito(pblcParams);
		
		List listaCabeceraPbLc = (List)datosPbLc.get("listaCabecera");
		List listaDetallePbLc = (List)datosPbLc.get("listaDetalle"); 
		
		//inicio PER-SiCC-2014-0999
		criterios.put("codigoPeriodo", campanhaActivo);
		
		String estatus = service.getEstatusCliente(criterios);
		
		if(estatus!=null && StringUtils.isNotBlank(estatus)){
			f.setEstadoFlexipago(estatus);
			if(StringUtils.equalsIgnoreCase(estatus, Constants.HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_ACTIVA)){
				Map fechaCampanha = service.getFechaCampanaActivacionCliente(criterios);
				f.setFechaActivacionFlexipago(MapUtils.getString(fechaCampanha, "fechaActivacion"));
				f.setCampanaActivacionFlexipago(MapUtils.getString(fechaCampanha, "campanaActivacion"));
			}else{
				if(StringUtils.equalsIgnoreCase(estatus, Constants.HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_INACTIVA)){
					f.setFechaComunicacionFlexipago(service.getFechaComunicacionCliente(criterios));
					f.setCampanaComunicacionFlexipago(service.getPeriodoComunicacionCliente(criterios));
				}else{
					if(StringUtils.equalsIgnoreCase(estatus, Constants.HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_CANCELADA)){
						f.setFechaCancelacionFlexipago(service.getFechaCancelacionCliente(criterios));
						f.setMotivoCancelacionFlexipago(service.getMotivoCancelacionCliente(criterios));
					}
				}
			}
		}else
			f.setEstadoFlexipago(Constants.HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_NO_INVITADA);
		//fin PER-SiCC-2014-0999
		
		this.setListaCabeceraPbLc(listaCabeceraPbLc);
		this.setListaDetallePbLc(listaDetallePbLc);
		this.crearColumnasDinamicas(datosPbLc, "2");
		
		ConsultaHIPDatosClienteForm hiperForm = (ConsultaHIPDatosClienteForm)consultaHIPDatosClienteAction.getFormBusqueda();
		f.setIndicadorBasparampais(hiperForm.getIndicadorBasparampais());
		
		f.setDmontoMinimo(null);
		f.setDpagoMinimoPenultimo(null);
		f.setDpagoTotalPenultimo(null);
		f.setDmontoMinimoSiguiente(null);
		f.setDpagoTotalUltimo(null);
		
		if(StringUtils.isNotEmpty(f.getMontoMinimo())) 
			f.setDmontoMinimo(Double.parseDouble(f.getMontoMinimo()));
		
		if(StringUtils.isNotEmpty(f.getPagoMinimoPenultimo())) 
			f.setDpagoMinimoPenultimo(Double.parseDouble(f.getPagoMinimoPenultimo()));
		
		if(StringUtils.isNotEmpty(f.getPagoTotalPenultimo())) 
			f.setDpagoTotalPenultimo(Double.parseDouble(f.getPagoTotalPenultimo()));
		
		if(StringUtils.isNotEmpty(f.getMontoMinimoSiguiente())) 
			f.setDmontoMinimoSiguiente(Double.parseDouble(f.getMontoMinimoSiguiente()));
		
		if(StringUtils.isNotEmpty(f.getPagoTotalUltimo())) 
			f.setDpagoTotalUltimo(Double.parseDouble(f.getPagoTotalUltimo()));
		
	}	

	
	/**
	 * Crear columnas dinamicas.
	 *
	 * @param datos the datos
	 * @param valor the valor
	 */
	public void crearColumnasDinamicas(Map datos, String valor){		
		if(valor.equals("1")){
			List listCabecera= (List)datos.get("cabecera");
			if(!listCabecera.isEmpty() && listCabecera.size()>0){
				List<String> listaCodCab = new ArrayList<String>();
				
				for (Object object : listCabecera) {
					log.debug("Para codigo:" + ((Map)object).get("codigo"));
					listaCodCab.add((String)((Map)object).get("codigo"));
				}
				
				if(!listaCodCab.isEmpty() && listaCodCab.size()>0){
					columns.clear();
					columns.add(new ColumnModel(this.getResourceMessage("detalleConsultorasList.fechaEmision"), "fechaEmision"));
					columns.add(new ColumnModel(this.getResourceMessage("detalleConsultorasList.descripcionMovimiento"), "descripcionMovimiento"));
					for (String columnKey : listaCodCab) {
						columns.add(new ColumnModel(columnKey, columnKey));
					}
				}
			}
		}
		
		if(valor.equals("2")){
			List listCabecera = (List)datos.get("listaCabecera");
			if(!listCabecera.isEmpty() && listCabecera.size()>0){
				List<String> listaCodCab = new ArrayList<String>();
				
				for (Object object : listCabecera) {
					if(object instanceof Map)
					{
						log.debug("Para codigo:" + ((Map)object).get("codigo"));
						listaCodCab.add((String)((Map)object).get("codigo"));
					}
					else if(object instanceof Base)
					{
						log.debug("Para codigo:" + ((Base)object).getCodigo());
						listaCodCab.add(((Base)object).getCodigo());
					}
				}
				
				if(!listaCodCab.isEmpty() && listaCodCab.size()>0){
					columns1.clear();
					columns1.add(new ColumnModel(this.getResourceMessage("detalleConsultorasList.fechaEmision"), "descripcion"));
					for (String columnKey : listaCodCab) {
						columns1.add(new ColumnModel(columnKey, columnKey));
					}
				}
			}
		}
	}
	
	/**
	 * Ejecutar reporte.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void ejecutarReporte(ActionEvent e)throws Exception{
		if(log.isDebugEnabled()){
			log.debug("ConsultaHIPCuentaCorrientesCampanhaAction - ejecutarReporte()");
		}
		((ConsultaHIPCuentaCorrientesCampanha2Form)this.getReporteCuentaCorrienteCampanhaAction().getFormReporte()).setFormatoExportacion("VXLS");
		this.getReporteCuentaCorrienteCampanhaAction().setHipCuentaCorrientesCanpanyaParamsReporte(this.getHipCuentaCorrientesCampanhaMovimientoCabecera());
		this.getReporteCuentaCorrienteCampanhaAction().setListMovimientosCuentaCorriente(this.listMovimientosCuentaCorriente);
		this.getReporteCuentaCorrienteCampanhaAction().setListaCabecera(this.listaCabecera);
		
		this.getReporteCuentaCorrienteCampanhaAction().executeReporte(e);
	}
	
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public Map getHipCuentaCorrientesCampanhaMovimientoCabecera() {
		return hipCuentaCorrientesCampanhaMovimientoCabecera;
	}

	public void setHipCuentaCorrientesCampanhaMovimientoCabecera(
			Map hipCuentaCorrientesCampanhaMovimientoCabecera) {
		this.hipCuentaCorrientesCampanhaMovimientoCabecera = hipCuentaCorrientesCampanhaMovimientoCabecera;
	}	

	public List getListaCabecera() {
		return listaCabecera;
	}

	public void setListaCabecera(List listaCabecera) {
		this.listaCabecera = listaCabecera;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public List getListMovimientosCuentaCorriente() {
		return listMovimientosCuentaCorriente;
	}

	public void setListMovimientosCuentaCorriente(
			List listMovimientosCuentaCorriente) {
		this.listMovimientosCuentaCorriente = listMovimientosCuentaCorriente;
	}

	public List getListaCabeceraPbLc() {
		return listaCabeceraPbLc;
	}

	public void setListaCabeceraPbLc(List listaCabeceraPbLc) {
		this.listaCabeceraPbLc = listaCabeceraPbLc;
	}

	public List getListaDetallePbLc() {
		return listaDetallePbLc;
	}

	public void setListaDetallePbLc(List listaDetallePbLc) {
		this.listaDetallePbLc = listaDetallePbLc;
	}

	public List<ColumnModel> getColumns1() {
		return columns1;
	}

	public void setColumns1(List<ColumnModel> columns1) {
		this.columns1 = columns1;
	}

	public ReporteCuentaCorrienteCampanhaAction getReporteCuentaCorrienteCampanhaAction() {
		return reporteCuentaCorrienteCampanhaAction;
	}

	public void setReporteCuentaCorrienteCampanhaAction(
			ReporteCuentaCorrienteCampanhaAction reporteCuentaCorrienteCampanhaAction) {
		this.reporteCuentaCorrienteCampanhaAction = reporteCuentaCorrienteCampanhaAction;
	}

	/**
	 * @return the indicadorPedidoPeriodo
	 */
	public String getIndicadorPedidoPeriodo() {
		return indicadorPedidoPeriodo;
	}

	/**
	 * @param indicadorPedidoPeriodo the indicadorPedidoPeriodo to set
	 */
	public void setIndicadorPedidoPeriodo(String indicadorPedidoPeriodo) {
		this.indicadorPedidoPeriodo = indicadorPedidoPeriodo;
	}

	/**
	 * @return the indicadorPedidoPeriodoSiguiente
	 */
	public String getIndicadorPedidoPeriodoSiguiente() {
		return indicadorPedidoPeriodoSiguiente;
	}

	/**
	 * @param indicadorPedidoPeriodoSiguiente the indicadorPedidoPeriodoSiguiente to set
	 */
	public void setIndicadorPedidoPeriodoSiguiente(
			String indicadorPedidoPeriodoSiguiente) {
		this.indicadorPedidoPeriodoSiguiente = indicadorPedidoPeriodoSiguiente;
	}
}