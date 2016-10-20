package biz.belcorp.ssicc.web.scsicc.hip.action;

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

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCuentaCorrientesHistoricaForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPCuentaCorrientesHistoricaAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPCuentaCorrientesHistoricaAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = -1504881132673731212L;
	
	private List hipCuentaCorrientesHistoricaMovimientosList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesHistoricaAction - devuelveFormBusqueda' method");
        }
		ConsultaHIPCuentaCorrientesHistoricaForm f = new ConsultaHIPCuentaCorrientesHistoricaForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesHistoricaAction - setFindAttributes' method");
        }
		return getHipCuentaCorrientesHistoricaMovimientosList();
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
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesHistoricaAction - setViewAtributes' method");
        }
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		ConsultaHIPCuentaCorrientesHistoricaForm f = (ConsultaHIPCuentaCorrientesHistoricaForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() + " / " + dtoDatosCliente.getCodigoTerritorio());
		
		//recuperamos el saldo a pagar para el proximo pedido
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
		criteria.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("codigoZona", dtoDatosCliente.getCodigoZona());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		
		//recuperamos el saldo unico de la Consultora
		f.setSaldoUnico(serviceCCC.getSaldoHistoricoTotal(criteria));
		
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

		//Si viene de exportacion, la columna tipomovimiento sera visualizado como texto normal
		/*String mostrarLinks = request.getParameter("mostrarLinks");
		if (mostrarLinks != null) 
			f.setMostrarLinks(false); 
		else
			f.setMostrarLinks(true);				
		*/
		//obtenemos el bloqueo de la consultora
		String fechaCastigada = service.getFechaCastigada(criteria);
		
		if(fechaCastigada != null) {
			f.setBloqueo("INCOBRABLE");
		} else {
			f.setBloqueo(dtoDatosCliente.getBloqueo());
		}
		
		//obtenemos los movimientos de Cuenta Corriente de la consultora				
		Map criteria2 = new HashMap();
		criteria2.put("codigoConsultora", dtoDatosCliente.getCodigoCliente());
		
		List listMovHistCuentaCorriente = serviceCCC.getCuentaCorrienteHistoricaConsultoraList(criteria2);
		List listMovHistCuentaCorrienteMap = new ArrayList();
		
		for(int i=0; i<listMovHistCuentaCorriente.size(); i++) {
			Map mapCuentaCorriente = null;
			try{
			 mapCuentaCorriente = BeanUtils.describe(listMovHistCuentaCorriente.get(i));
			}catch(Exception e){}
			mapCuentaCorriente.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
			mapCuentaCorriente.put("nombres", dtoDatosCliente.getNombreCompleto());
			mapCuentaCorriente.put("telefonoFijo", dtoDatosCliente.getTelefonoCasa());
			mapCuentaCorriente.put("telefonoMovil", dtoDatosCliente.getTelefonoCelular()); 
			
			listMovHistCuentaCorrienteMap.add(mapCuentaCorriente);
		}
		
		//session.setAttribute(Constants.HIP_CUENTA_CORRIENTES_HISTORICA_MOVIMIENTOS_LIST, listMovimientosHistoricoCuentaCorrienteMap);
		setHipCuentaCorrientesHistoricaMovimientosList(listMovHistCuentaCorrienteMap);
	}

	
	//getters && setters
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getHipCuentaCorrientesHistoricaMovimientosList() {
		return hipCuentaCorrientesHistoricaMovimientosList;
	}

	public void setHipCuentaCorrientesHistoricaMovimientosList(
			List hipCuentaCorrientesHistoricaMovimientosList) {
		this.hipCuentaCorrientesHistoricaMovimientosList = hipCuentaCorrientesHistoricaMovimientosList;
	}

	


}
