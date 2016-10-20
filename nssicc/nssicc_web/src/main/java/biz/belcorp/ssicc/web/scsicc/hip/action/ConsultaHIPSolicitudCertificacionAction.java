package biz.belcorp.ssicc.web.scsicc.hip.action;

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
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPSolicitudCertificacionForm;



/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPActualizacionDatosConsultoraAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPSolicitudCertificacionAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = 1966854531665137688L;
	
	public static final String HIP_TIPO_CERTIFICACION_VINCULO_COMERCIAL = "VC";
	public static final String HIP_TIPO_CERTIFICACION_CARTA_PROPIEDAD = "CP";
	
	private List tipoCertificacionList;
	private List premiosClienteList;
	
	AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");

	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPSolicitudCertificacionAction - devuelveFormBusqueda' method");
		}
		ConsultaHIPSolicitudCertificacionForm f = new ConsultaHIPSolicitudCertificacionForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPSolicitudCertificacionAction - setFindAttributes' method");
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
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPSolicitudCertificacionAction - setViewAtributes' method");
		}
		
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPSolicitudCertificacionForm f = (ConsultaHIPSolicitudCertificacionForm) this.formBusqueda;

        ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();

		Map criteria = new HashMap();
		criteria.put("codCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("nomCliente", dtoDatosCliente.getNombreCompleto());
		criteria.put("pais", dtoDatosCliente.getCodigoPais());
		criteria.put("marca", dtoDatosCliente.getCodigoMarca());
		criteria.put("canal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("desRegion", dtoDatosCliente.getDescripcionRegion());		
		criteria.put("codZona", dtoDatosCliente.getCodigoZona());
		criteria.put("desZona", dtoDatosCliente.getDescripcionZona());
		criteria.put("codSeccion", dtoDatosCliente.getCodigoSeccion());
		criteria.put("desSeccion", dtoDatosCliente.getDescripcionSeccion());
		criteria.put("codTerritorio", dtoDatosCliente.getCodigoTerritorio());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("codPeriodoFinalVentas", dtoDatosCliente.getPeriodoUltimoPedido());
		criteria.put("tipDocIdentidad", dtoDatosCliente.getTipoDocIdentidad());
		criteria.put("numDocIdentidad", dtoDatosCliente.getNumeroDocIdentidad());
		
		f.setCodConsultora((String) criteria.get("codCliente"));
		f.setNomConsultora((String) criteria.get("nomCliente"));
		f.setDesRegZonTerri((String) criteria.get("desRegion") + "/" + criteria.get("desZona") + "/" + criteria.get("codTerritorio"));
		f.setOidCliente((String) criteria.get("oidCliente"));
		f.setPeriodoUltimoPedido(dtoDatosCliente.getPeriodoUltimoPedido());
		f.setPais((String) criteria.get("pais"));
		f.setTipDocIdentidad((String) criteria.get("tipDocIdentidad"));
		f.setNumDocIdentidad((String) criteria.get("numDocIdentidad"));

		List listaTipoCertificacion = consultaHIPDatosClienteService.getTipoCertificacionList(criteria);
		setTipoCertificacionList(listaTipoCertificacion);
		List listaPremiosCliente = consultaHIPDatosClienteService.getPremiosClienteList(criteria);
		setPremiosClienteList(listaPremiosCliente);
		limpiar(f);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		ConsultaHIPSolicitudCertificacionForm f = (ConsultaHIPSolicitudCertificacionForm) this.formBusqueda;
		
		if(StringUtils.isBlank(f.getCodTCertificacion()))
			return this.getResourceMessage("consultaHIPSolicitudCertificacionForm.errors.sin.tipoCertificado");
		
		if(StringUtils.isBlank(f.getEmpDestino()))
			return this.getResourceMessage("consultaHIPSolicitudCertificacionForm.errors.sin.empresaDestino");
			
		return null;
	}
	
	/**
	 * Genera la certificacion del cliente
	*/
	public void certificacion(ActionEvent e) throws Exception {	

		if (log.isDebugEnabled()){
			log.debug("Entering 'certificacion' method");
		}

		ConsultaHIPSolicitudCertificacionForm f = (ConsultaHIPSolicitudCertificacionForm) this.formBusqueda;
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		Map params = new HashMap();
		params.put("nomCliente", f.getNomConsultora());
		params.put("tipDocIdentidad", f.getTipDocIdentidad());
		params.put("numDocIdentidad", f.getNumDocIdentidad());

		Map paramsOidCli = new HashMap();
		paramsOidCli.put("OidCliente", f.getOidCliente());

		String fecMesAnno = consultaHIPDatosClienteService.getFecMesAnno(paramsOidCli);
		if(fecMesAnno!=null && !("".equals(fecMesAnno))) {
			StringTokenizer st = new StringTokenizer(fecMesAnno, ",");
			params.put("annoInicPeriodo", st.nextToken());
			params.put("mesInicPeriodo", st.nextToken());
		}
		
		if(HIP_TIPO_CERTIFICACION_VINCULO_COMERCIAL.equals(f.getCodTCertificacion())){
			params.put("promedioVenta", obtenerPromedioVenta(f, consultaHIPDatosClienteService));
		}
		
		params.put("entidad", f.getEmpDestino());
		params.put("premio", f.getDesPremio());
		params.put("modelo", f.getModelo());
		params.put("serie", f.getSerie());
		params.put("marca", f.getMarca());
		params.put("color", f.getColor());
		params.put("fecFacturacion", f.getFecFacturacion());

		Map paramsTemp = new HashMap();
		paramsTemp.put("codTCertificacion", f.getCodTCertificacion());
		String msjOidMsj = consultaHIPDatosClienteService.getOidMsj(paramsTemp);
		params.put("msjOidMsj", msjOidMsj);
		params.put("clieOidClie", f.getOidCliente());
		params.put("moduloOidModuOrig", Constants.HIP_MODU_ORIG);

		consultaHIPDatosClienteService.execGeneraCertificacion(params);
		
		limpiar(f);
		
		String msgResult = this.getResourceMessage("consultaHIPSolicitudCertificacionForm.msg.graboOk");
		this.addInfo("Info", msgResult);
	}

	/**
	 * Se recupera el promedio de Venta de las Cuentas Corrientes x Facturacion de la Consultora
	 */
	public String obtenerPromedioVenta(ConsultaHIPSolicitudCertificacionForm f, ConsultaHIPDatosClienteService service) {
	
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
		criteria.put("codigoCanal", dtoDatosCliente.getCodigoCanal());

		criteria.put("periodoInicio", f.getCodPeriIniPromVenta());
		criteria.put("periodoFin", f.getCodPeriFinalPromVenta());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("numeroCampanas", f.getNumCampPromedio());

		return service.getPromedioVentas(criteria);
		
	}
	
	/**
	 * 
	 * @param f
	 * @param dtoDatosCliente
	 */
	public void limpiar(ConsultaHIPSolicitudCertificacionForm f) {
		f.setEmpDestino("");
		f.setCodPremio("");
		f.setDesPremio("");
		f.setModelo("");
		f.setSerie("");
		f.setMarca("");
		f.setColor("");

		f.setCodPeriFinalPromVenta("");
		f.setCodPeriIniPromVenta("");
		f.setNumCampPromedio("");
		f.setCodTCertificacion("");
	}
	
	
	/**
	 * 
	 * @param val
	 */
	public void showCamposxTC(ValueChangeEvent val){
		
		if(log.isDebugEnabled())
			log.debug("showCamposxTC");
		
		try {
			ConsultaHIPSolicitudCertificacionForm f = (ConsultaHIPSolicitudCertificacionForm) this.formBusqueda;
			
			f.setCodPeriFinalPromVenta("");
			f.setCodPeriIniPromVenta("");
			f.setNumCampPromedio("");
			
			f.setEmpDestino("");
			f.setCodPremio("");
			f.setDesPremio("");
			f.setModelo("");
			f.setSerie("");
			f.setMarca("");
			f.setColor("");
			
			String valor = val.getNewValue().toString();
			
			System.out.println("valor: " + valor);
			
			if(StringUtils.equals(valor, HIP_TIPO_CERTIFICACION_VINCULO_COMERCIAL))
			{				
				String dato = ajaxService.getValorStatus(f.getOidCliente());
				
				if(StringUtils.equals(dato, Constants.NUMERO_CERO))
					f.setFlagSoloLecturaEmpresaDestino(false);
				else
					f.setFlagSoloLecturaEmpresaDestino(true);
				
				f.setCodPeriFinalPromVenta(f.getPeriodoUltimoPedido());
				f.setNumCampPromedio("18");
				
				PerioFinalPromVentas();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * 
	 * @param val
	 */
	public void premioChange(ValueChangeEvent val){

		if(log.isDebugEnabled())
			log.debug("premioChange");

		ConsultaHIPSolicitudCertificacionForm f = (ConsultaHIPSolicitudCertificacionForm) this.formBusqueda;
		
		String []values = StringUtils.split(f.getCodPremio(), "|");
		
		if(values != null && values.length > 1)
		{
			f.setDesPremio(values[0]);
			f.setFecFacturacion(values[1]);
		}
		else
		{
			f.setDesPremio("");
			f.setFecFacturacion("");			
		}
	}
	
	
	/**
	 * 
	 */
	public void PerioFinalPromVentas()
	{
		if(log.isDebugEnabled())
			log.debug("PerioFinalPromVentas");
		
		ConsultaHIPSolicitudCertificacionForm f = (ConsultaHIPSolicitudCertificacionForm) this.formBusqueda;
		
		if(StringUtils.isNotBlank(f.getCodPeriFinalPromVenta()))
		{
			String data = ajaxService.execCalculoPeriIniPromVentas(f.getCodPeriFinalPromVenta(), f.getNumCampPromedio());
			
			f.setCodPeriIniPromVenta(data);
		}		
	}	
	
	//Getters && Setters
	
	public List getTipoCertificacionList() {
		return tipoCertificacionList;
	}

	public void setTipoCertificacionList(List tipoCertificacionList) {
		this.tipoCertificacionList = tipoCertificacionList;
	}

	public List getPremiosClienteList() {
		return premiosClienteList;
	}

	public void setPremiosClienteList(List premiosClienteList) {
		this.premiosClienteList = premiosClienteList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	
}
