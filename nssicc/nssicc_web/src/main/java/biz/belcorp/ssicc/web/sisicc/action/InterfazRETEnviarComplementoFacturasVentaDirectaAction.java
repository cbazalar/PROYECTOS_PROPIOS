/*
 * Created on 29-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETEnviarComplementoFacturasVentaDirectaForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazRETEnviarComplementoFacturasVentaDirectaAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 */
@ManagedBean
@SessionScoped
public class InterfazRETEnviarComplementoFacturasVentaDirectaAction extends BaseInterfazAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4492866755297384280L;
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRETEnviarComplementoFacturasVentaDirectaForm f = new InterfazRETEnviarComplementoFacturasVentaDirectaForm();
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'view' method");
        }
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccMarcaList = svc.getMarcas();		
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazRETEnviarComplementoFacturasVentaDirectaForm f = (InterfazRETEnviarComplementoFacturasVentaDirectaForm) this.formInterfaz;

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");	       
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        f.setFechaFacturacion(controlFacturacion.getFechaProceso());
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        f.setFechaFacturacionD(DateUtil.convertStringToDate(f.getFechaFacturacion()));
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		
		this.formInterfaz = f;
	
	}
	
	public String periodoRequerido(String marca, String canal) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		dato = ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), marca,
				canal);
		return dato;
	}
	
	public String setValidarInterfaz() {
		InterfazRETEnviarComplementoFacturasVentaDirectaForm form = (InterfazRETEnviarComplementoFacturasVentaDirectaForm) this.formInterfaz;

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String periodo = form.getCodigoPeriodo();
		String periodorequerido = periodoRequerido(form.getCodigoMarca(),
				form.getCodigoCanal());
		if (!periodo.equals(periodorequerido)) {
			String mensaje = this
					.getResourceMessage("interfazRETEnviarDetalleFacturasVDForm.error.rango.fechaFacturacion");
			return mensaje;
		}

		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		try {
			if (form.getFechaFacturacionD().before(
					DateUtil.convertStringToDate(fechaDesde))
					|| form.getFechaFacturacionD().after(
							DateUtil.convertStringToDate(fechaHasta))) {
				String mensaje = this
						.getResourceMessage("interfazRETEnviarDetalleFacturasVDForm.error.rango.fechaFacturacion")
						+ " (" + fechaDesde + " - " + fechaHasta + ")";
				return mensaje;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}


		return null;
	}

	/**
	 * Metodo para obtener el periodo por marca
	 * 
	 * @param val
	 */
	public void loadPeriodoMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazRETEnviarComplementoFacturasVentaDirectaForm f = (InterfazRETEnviarComplementoFacturasVentaDirectaForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
	}

	/**
	 * Metodo para obtener el periodo por canal
	 * 
	 * @param val
	 */
	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazRETEnviarComplementoFacturasVentaDirectaForm f = (InterfazRETEnviarComplementoFacturasVentaDirectaForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params =  super.prepareParamsBeforeExecute(params, form);
		
		InterfazRETEnviarComplementoFacturasVentaDirectaForm form1 = (InterfazRETEnviarComplementoFacturasVentaDirectaForm) this.formInterfaz;
		params.put("fechaFacturacion", DateUtil.convertDateToString(form1.getFechaFacturacionD())); 
		return params;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	
}
