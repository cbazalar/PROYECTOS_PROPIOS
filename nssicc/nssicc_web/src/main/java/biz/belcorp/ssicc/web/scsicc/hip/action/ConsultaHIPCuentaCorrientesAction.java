package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCuentaCorrientesForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPCuentaCorrientesAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPCuentaCorrientesAction extends BasePopupAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 496459489182191977L;
	
	private List hipCuentaCorrientesMovimientosList = new ArrayList();
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPCuentaCorrientesForm consultaHIPCuentaCorrientesForm = new ConsultaHIPCuentaCorrientesForm(); 
		return consultaHIPCuentaCorrientesForm;
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
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - setViewAtributes' method");
        }
		this.cantidadRowsDefaultDatatable = "50";
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");				
		ConsultaHIPCuentaCorrientesForm f = (ConsultaHIPCuentaCorrientesForm)this.formBusqueda; 
				
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
				
		MantenimientoOCRPedidoControlFacturacionService mopcfService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
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
		
		BigDecimal saldoTotal =  new BigDecimal(saldoVencido + saldoPorVencer);
		BigDecimal pagohoy =  new BigDecimal((saldoVencido + saldoPorVencer) - saldoCupon3);

		BigDecimal saldoAPagar = new BigDecimal(saldoPagar);
		BigDecimal pagosPosteriores = saldoTotal.subtract(saldoAPagar).setScale(2,RoundingMode.HALF_UP);
		
		saldoTotal = saldoTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		pagohoy = pagohoy.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		f.setSaldoTotal( saldoTotal.toString() );
		f.setPagoHoy( pagohoy.toString() );
		
		//PER-SiCC-2015-0344
		Map criteriaAbon = new HashMap();
		criteriaAbon.put("oidCliente", dtoDatosCliente.getOidCliente());
		Double abono = Double.parseDouble(service.getObtieAbonoPendi(criteriaAbon));
		BigDecimal Abono1 = new BigDecimal(abono);
		BigDecimal resultado = pagosPosteriores.subtract(Abono1).setScale(2,RoundingMode.HALF_UP);
		f.setPagosPosteriores(resultado.toString());
		
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
		criteria2.put("codigoConsultora", dtoDatosCliente.getCodigoCliente());
		
		List listMovimientosCuentaCorriente = serviceCobranza.getDetalleConsultora(criteria2);
		List listMovimientosCuentaCorrienteMap = new ArrayList();
		
		for(int i=0; i<listMovimientosCuentaCorriente.size(); i++) {
			Map mapCuentaCorriente = null;
			try{
			 mapCuentaCorriente = BeanUtils.describe(listMovimientosCuentaCorriente.get(i));
			}catch(Exception e){}
			mapCuentaCorriente.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
			mapCuentaCorriente.put("nombres", dtoDatosCliente.getNombreCompleto());
			mapCuentaCorriente.put("telefonoFijo", dtoDatosCliente.getTelefonoCasa());
			mapCuentaCorriente.put("telefonoMovil", dtoDatosCliente.getTelefonoCelular()); 
			
			String dcargo = (String)mapCuentaCorriente.get("cargo"); 
			String dabono = (String)mapCuentaCorriente.get("abono");
			String dsaldo = (String)mapCuentaCorriente.get("saldo");
			
			if(StringUtils.isNotEmpty(dcargo))
				mapCuentaCorriente.put("dcargo", Double.parseDouble(dcargo));
			else
				mapCuentaCorriente.put("dcargo", null);
			
			if(StringUtils.isNotEmpty(dabono))
				mapCuentaCorriente.put("dabono", Double.parseDouble(dabono));
			else
				mapCuentaCorriente.put("dabono", null);
			
			if(StringUtils.isNotEmpty(dsaldo))
				mapCuentaCorriente.put("dsaldo", Double.parseDouble(dsaldo));
			else
				mapCuentaCorriente.put("dsaldo", null);
			
			listMovimientosCuentaCorrienteMap.add(mapCuentaCorriente);
		}
		
		this.hipCuentaCorrientesMovimientosList = listMovimientosCuentaCorrienteMap;
		
		//PER-SiCC-2013-0893 @ghuertasa - inicio
		//obtenemos la campaña activa
		
		MantenimientoOCRPedidoControlFacturacionService service5 = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService" );
		Map criteria5 = new HashMap();
		String campanhaActivo = null;
		criteria5.put("codigoPais", dtoDatosCliente.getCodigoPais()); 
		criteria5.put("estadoCampanha", "0" ); 
		criteria5.put("indicadorActiva", "1" ); 
		List lista5 = service5.getCampanhasActivasByCriteria(criteria5); 
		if (lista5.size()==1) { 
			campanhaActivo = (String) lista5.get(0); 
		}
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
		//PER-SiCC-2013-0893 @ghuertasa - fin
		
		ConsultaHIPDatosClienteForm hiperForm = (ConsultaHIPDatosClienteForm)consultaHIPDatosClienteAction.getFormBusqueda();
		f.setIndicadorBasparampais(hiperForm.getIndicadorBasparampais());
		this.activarBuscar = true;
		
		f.setDsaldoVencido(null);
		f.setDsaldoPorVencer(null);
		f.setDsaldoTotal(null);
		f.setDpagosPosteriores(null);
		f.setDsaldoCupon3(null);
		f.setDpagoHoy(null);
		f.setDsaldoPagar(null);
		
		if(StringUtils.isNotEmpty(f.getSaldoVencido())) 
			f.setDsaldoVencido(Double.parseDouble(f.getSaldoVencido()));
		if(StringUtils.isNotEmpty(f.getSaldoPorVencer())) 
			f.setDsaldoPorVencer(Double.parseDouble(f.getSaldoPorVencer()));
		if(StringUtils.isNotEmpty(f.getSaldoTotal())) 
			f.setDsaldoTotal(Double.parseDouble(f.getSaldoTotal()));
		if(StringUtils.isNotEmpty(f.getPagosPosteriores())) 
			f.setDpagosPosteriores(Double.parseDouble(f.getPagosPosteriores()));
		if(StringUtils.isNotEmpty(f.getSaldoCupon3())) 
			f.setDsaldoCupon3(Double.parseDouble(f.getSaldoCupon3()));
		if(StringUtils.isNotEmpty(f.getPagoHoy())) 
			f.setDpagoHoy(Double.parseDouble(f.getPagoHoy()));
		if(StringUtils.isNotEmpty(f.getSaldoPagar())) 
			f.setDsaldoPagar(Double.parseDouble(f.getSaldoPagar()));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - setFindAttributes' method");
        }
		
		return this.hipCuentaCorrientesMovimientosList;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	/**
	 * @return the hipCuentaCorrientesMovimientosList
	 */
	public List getHipCuentaCorrientesMovimientosList() {
		return hipCuentaCorrientesMovimientosList;
	}

	/**
	 * @param hipCuentaCorrientesMovimientosList the hipCuentaCorrientesMovimientosList to set
	 */
	public void setHipCuentaCorrientesMovimientosList(
			List hipCuentaCorrientesMovimientosList) {
		this.hipCuentaCorrientesMovimientosList = hipCuentaCorrientesMovimientosList;
	}
	

}
