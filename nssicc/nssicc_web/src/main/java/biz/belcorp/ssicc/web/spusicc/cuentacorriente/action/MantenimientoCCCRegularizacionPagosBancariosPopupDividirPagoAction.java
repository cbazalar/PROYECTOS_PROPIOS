package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCRegularizacionPagosBancariosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoAction
		extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private String oidMovBancario;
	private List cccPagosBancariosPorRegularizarList;
	private List cccRegularizacionPagosBancariosDividirPagoList;
	private List hipDetallePedidosConsultoraList;
	private List mantenimientoCCCRegularizacionPagosBancariosDividirPagoList;
	private List listaCampos1;
	private DataTableModel datatableCCCD;
	private Object columnasSeleccionadas;
	private Double importePago;

	public void agregarUnaFila(ActionEvent evt) {

		Map base = new HashMap();
		String mensaje = "";
//		int tamanio = this.listaCampos1.size();
//		if(this.listaCampos1 != null && this.listaCampos1.size() > 0){
//			for (int i = 0; i < listaCampos1.size(); i++) {
//				Map obj = (HashMap) listaCampos1.get(i);
//
//				base.put("codigoConsultora", MapUtils.getString(obj, "codigoConsultora"));
//				base.put("cedulaConsultora", MapUtils.getString(obj, "cedulaConsultora"));
//				base.put("nombreConsultora", MapUtils.getString(obj, "nombreConsultora"));
//				base.put("importePagoDetalle", MapUtils.getString(obj, "importePagoDetalle"));
//			}
//		}		
		this.listaCampos1.add(base);		
		this.datatableCCCD = new DataTableModel(this.listaCampos1);
	}

	/**
	 * Elimina fila de la grilla
	 * 
	 * @param evt
	 */
	public void eliminar(ActionEvent evt){
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'delete' method");
		}
		
		if(this.listaCampos1 != null && this.listaCampos1.size() > 0){
			this.listaCampos1.remove(this.columnasSeleccionadas);
			this.datatableCCCD = new DataTableModel(this.listaCampos1);
		}else{
			this.addError("Error: ", "La lista se encuentra vacía");
			return;
		}

	}
	
	public void inicializarValores() {
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.listaCampos1 = new ArrayList();
		MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm f = (MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm) this.formBusqueda;
		

		MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");
		ConsultaHIPDatosClienteService serviceHIP = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");

		// session.setAttribute("oid",this.oidMovBancario);

		Map criteria = new HashMap();
		criteria.put("oidMovBancario", this.oidMovBancario);

		Map resultado = service
				.getPagosBancariosPorRegularizarDividirPagoByFilter(criteria);

		MantenimientoPEDConfiguracionOfertasPorConcursosService serviceMoneda = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		criteria.put("codigoPais", pais.getCodigo());
		Map moneda = serviceMoneda.getMoneda(criteria);
		
		f.setNumeroDecimales(MapUtils.getString(moneda, "numeroDecimales"));
		f.setCodigoPais(pais.getCodigo());
		// f.setCodigoPais(pais.getDescripcion());
		f.setTipoError(MapUtils.getString(resultado, "descripcionError"));
		f.setFechaPago(MapUtils.getString(resultado, "fechaPago"));
		f.setCuentaCorriente(MapUtils.getString(resultado,
				"descripcionCuentaCorrienteBancaria"));
		f.setImportePago(MapUtils.getString(resultado, "importePago"));
		this.importePago = Double.valueOf(f.getImportePago());

		/*
		 * List detallePedidoList = serviceHIP.getDetallePedido(criteria);
		 * request
		 * .getSession().setAttribute(Constants.HIP_DETALLE_PEDIDOS_CONSULTORA_LIST
		 * , detallePedidoList);
		 */

		List listaResultado = new ArrayList();
		List lista = this.cccPagosBancariosPorRegularizarList;

		String oid = this.oidMovBancario;
		if (lista != null && lista.size() > 0) {
			for (int i = 0; i < lista.size(); i++) {
				Map index = (Map) lista.get(i);
				if (StringUtils.equalsIgnoreCase(MapUtils.getString(index, "oidMovimientoBancario"), oid)) {
					listaResultado = (List) index.get("listaDetalle");
				}
			}
		}

		this.cccRegularizacionPagosBancariosDividirPagoList = listaResultado;
		f.setIndicadorCerrar("N");
		String ventana = "PF('dialogMantenimientoForm2').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		this.datatableCCCD = new DataTableModel(this.cccRegularizacionPagosBancariosDividirPagoList);
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm f = (MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm) this.formBusqueda;
		Map criteria = new HashMap();
		criteria.put("oidSoliCabecera", this.oidMovBancario);
		criteria.put("codigoVentaLog", f.getCodigoVenta());
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		List detallePedidoList = service.getDetallePedido(criteria);

		this.hipDetallePedidosConsultoraList = detallePedidoList;

		return mapping.findForward("list");
	}
	
	/**
	 * Valida codigo cliente
	 * 
	 * @param indice
	 */
	public void validaConsultoraOnEnter() {
		try {
			
		
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		log.info(value);
		int i = new Integer(value).intValue();
		if (i < 0)
			return;
		Map bean = (HashMap) this.listaCampos1.get(i);
		String codigoConsultora = MapUtils.getString(bean, "codigoConsultora");
		String mensaje = null;
		if (bean == null) {
			return;
		}
		if (StringUtils.isBlank(codigoConsultora)) {
			return;
		} else {
			MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm f = (MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm)this.formBusqueda;

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
//			String codigoConsultora = bean.getCodigoConsultora();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
			LabelDatosConsultoraValue[] obtenerDatosConsultora = null;
			obtenerDatosConsultora = aSvc.getCabeceraConsultoraSimple(codigoPais, codigoConsultora);
			if(obtenerDatosConsultora == null){
				mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
				this.addError("Error : ", mensaje);
				return;
			}
			
			bean.put("nombreConsultora", obtenerDatosConsultora[0].getNombreConsultora());
			
		}
		} catch (Exception e) {
			this.addError("Error : " , this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Valida cedula cliente
	 * 
	 * @param indice
	 */
	public void validaCedulaOnEnter() {
		try {
			
		
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		log.info(value);
		int i = new Integer(value).intValue();
		if (i < 0)
			return;
		Map bean = (HashMap) this.listaCampos1.get(i);
		String cedulaConsultora = MapUtils.getString(bean, "cedulaConsultora");
		String mensaje = null;
		if (bean == null) {
			return;
		}
		if (StringUtils.isBlank(cedulaConsultora)) {
			return;
		} else {
			MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm f = (MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm)this.formBusqueda;


			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo();
			DatosConsultoraBasico[] obtenerDatosConsultora = null;
			obtenerDatosConsultora = aSvc.getCabeceraConsultoraByDocIdentidad(codigoPais, cedulaConsultora,"","");
			if(obtenerDatosConsultora == null){
				mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
				this.addError("Error : ", mensaje);
				return;
			}
			
			bean.put("nombreConsultora", obtenerDatosConsultora[0].getNombreConsultora());	
			bean.put("codigoConsultora", obtenerDatosConsultora[0].getCodigoConsultora());
			
		}
		} catch (Exception e) {
			this.addError("Error : " , this.obtieneMensajeErrorException(e));

		}
	}
	
	/**
	 * Suma los importes
	 * 
	 * @return
	 */
	public Boolean calcularSumar(){
		int tamanio = listaCampos1.size();
		Double acumulado = 0.0;
		for (int i = 0; i < tamanio; i++) {
			
			Map obj = (HashMap) this.listaCampos1.get(i);
			String importeString = MapUtils.getString(obj, "importePagoDetalle");
			Double importe = Double.parseDouble(importeString);
			acumulado = importe + acumulado;
		}
		if(acumulado.equals(this.importePago)){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Guarda la lista
	 * 
	 * @param evt
	 */
	public void asignar(ActionEvent evt) {
		String mensaje = "";
		try {
			if (log.isDebugEnabled()) {
				log.debug("JFA : Entering 'asignar' method");
			}
		
			List lista = this.cccPagosBancariosPorRegularizarList;
			
//			Validaciones JS
			for (int i = 0; i < listaCampos1.size(); i++) {
				Map map = (HashMap)listaCampos1.get(i); 
				String importeString = MapUtils.getString(map, "importePagoDetalle");
				if(StringUtils.isBlank(importeString)){
					this.addError("Error: ", "No debe ingresar valores vacíos");
					return;					
				}
						
				int importePagoDetalle =Integer.parseInt(importeString);
				if(importePagoDetalle == 0){
					this.addError("Error: ", "Debe ingresar valores mayores a 0");
					return;
				}				
			}
			
			List listaDetalle = new ArrayList();
			if(this.calcularSumar()){
				MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm f = (MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm) this.formBusqueda;
//				Setear los valores de la lista al form 
				String [] listCodigoConsultora = new String[listaCampos1.size()];
				String [] listCedulaConsultora = new String[listaCampos1.size()];
				String [] listNombreConsultora = new String[listaCampos1.size()];
				String [] listImportePagoDetalle = new String[listaCampos1.size()];
				
				for (int i = 0; i < listaCampos1.size(); i++) {
					Map base = (HashMap)this.listaCampos1.get(i);
					listCodigoConsultora[i]= MapUtils.getString(base, "codigoConsultora");
					listCedulaConsultora[i]= MapUtils.getString(base, "cedulaConsultora");
					listNombreConsultora[i]= MapUtils.getString(base, "nombreConsultora");
					listImportePagoDetalle[i]= MapUtils.getString(base, "importePagoDetalle");					
				}
				
				f.setListCodigoConsultora(listCodigoConsultora);
				f.setListCedulaConsultora(listCedulaConsultora);
				f.setListNombreConsultora(listNombreConsultora);
				f.setListImportePagoDetalle(listImportePagoDetalle);
				
				if(f.getListCodigoConsultora()!=null){
					for(int i = 0; i<f.getListCodigoConsultora().length;i++){
						Map criteria = new HashMap();
						if(f.getListCedulaConsultora()[i] == null){
							break;
						}
						criteria.put("oidMovimientoBancario", this.oidMovBancario);
						criteria.put("codigoConsultora", f.getListCodigoConsultora()[i]);
						criteria.put("cedulaConsultora", f.getListCedulaConsultora()[i]);
						criteria.put("nombreConsultora", f.getListNombreConsultora()[i]);
						criteria.put("importePagoDetalle", f.getListImportePagoDetalle()[i]);
						
						listaDetalle.add(criteria);
					}
				}				
//				this.mantenimientoCCCRegularizacionPagosBancariosDividirPagoList = new ArrayList();
//				this.mantenimientoCCCRegularizacionPagosBancariosDividirPagoList = listaDetalle;

				String oid = this.oidMovBancario;
				if (lista != null && lista.size() > 0) {
					for (int i = 0; i < lista.size(); i++) {
						Map index = (Map) lista.get(i);
						if (StringUtils.equalsIgnoreCase(MapUtils.getString(index, "oidMovimientoBancario"), oid)) {
							index.put("listaDetalle", listaDetalle);
							index.put("indicadorEditarCodigoConsultora", "N");
						}
					}
				}
				mensaje = this.getResourceMessage("mantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm.created");
				this.addInfo("Info : ", mensaje);
				f.setIndicadorCerrar("S");
				
			}else{
				mensaje = "La suma de los importes debe dar el importe total";
				this.addError("Error : ", mensaje);
			}
			

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	// metodo que sale del popup
	public void salirPopup(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering my method 'salirUA'");
			}

			String ventana = "PF('dialogMantenimientoForm2').hide()";
			this.getRequestContext().execute(ventana);
			this.mostrarBotonSalir = true;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm form = new MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.listaCampos1 = new ArrayList();
	}

	/**
	 * @return the oidMovBancario
	 */
	public String getOidMovBancario() {
		return oidMovBancario;
	}

	/**
	 * @param oidMovBancario
	 *            the oidMovBancario to set
	 */
	public void setOidMovBancario(String oidMovBancario) {
		this.oidMovBancario = oidMovBancario;
	}

	/**
	 * @return the cccPagosBancariosPorRegularizarList
	 */
	public List getCccPagosBancariosPorRegularizarList() {
		return cccPagosBancariosPorRegularizarList;
	}

	/**
	 * @param cccPagosBancariosPorRegularizarList
	 *            the cccPagosBancariosPorRegularizarList to set
	 */
	public void setCccPagosBancariosPorRegularizarList(
			List cccPagosBancariosPorRegularizarList) {
		this.cccPagosBancariosPorRegularizarList = cccPagosBancariosPorRegularizarList;
	}

	/**
	 * @return the cccRegularizacionPagosBancariosDividirPagoList
	 */
	public List getCccRegularizacionPagosBancariosDividirPagoList() {
		return cccRegularizacionPagosBancariosDividirPagoList;
	}

	/**
	 * @param cccRegularizacionPagosBancariosDividirPagoList
	 *            the cccRegularizacionPagosBancariosDividirPagoList to set
	 */
	public void setCccRegularizacionPagosBancariosDividirPagoList(
			List cccRegularizacionPagosBancariosDividirPagoList) {
		this.cccRegularizacionPagosBancariosDividirPagoList = cccRegularizacionPagosBancariosDividirPagoList;
	}

	/**
	 * @return the hipDetallePedidosConsultoraList
	 */
	public List getHipDetallePedidosConsultoraList() {
		return hipDetallePedidosConsultoraList;
	}

	/**
	 * @param hipDetallePedidosConsultoraList
	 *            the hipDetallePedidosConsultoraList to set
	 */
	public void setHipDetallePedidosConsultoraList(
			List hipDetallePedidosConsultoraList) {
		this.hipDetallePedidosConsultoraList = hipDetallePedidosConsultoraList;
	}

	/**
	 * @return the mantenimientoCCCRegularizacionPagosBancariosDividirPagoList
	 */
	public List getMantenimientoCCCRegularizacionPagosBancariosDividirPagoList() {
		return mantenimientoCCCRegularizacionPagosBancariosDividirPagoList;
	}

	/**
	 * @param mantenimientoCCCRegularizacionPagosBancariosDividirPagoList
	 *            the
	 *            mantenimientoCCCRegularizacionPagosBancariosDividirPagoList to
	 *            set
	 */
	public void setMantenimientoCCCRegularizacionPagosBancariosDividirPagoList(
			List mantenimientoCCCRegularizacionPagosBancariosDividirPagoList) {
		this.mantenimientoCCCRegularizacionPagosBancariosDividirPagoList = mantenimientoCCCRegularizacionPagosBancariosDividirPagoList;
	}

	/**
	 * @return the listaCampos1
	 */
	public List getListaCampos1() {
		return listaCampos1;
	}

	/**
	 * @param listaCampos1
	 *            the listaCampos1 to set
	 */
	public void setListaCampos1(List listaCampos1) {
		this.listaCampos1 = listaCampos1;
	}

	/**
	 * @return the datatableCCCD
	 */
	public DataTableModel getDatatableCCCD() {
		return datatableCCCD;
	}

	/**
	 * @param datatableCCCD
	 *            the datatableCCCD to set
	 */
	public void setDatatableCCCD(DataTableModel datatableCCCD) {
		this.datatableCCCD = datatableCCCD;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Object getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}
}