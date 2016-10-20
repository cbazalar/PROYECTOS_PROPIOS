package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioExterno;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionExternaPagosBancariosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionExternaPagosBancariosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoCCCDigitacionExternaPagosBancariosAction extends BaseMantenimientoSearchAbstractAction {
	private static final long serialVersionUID = 1L;
	private String numeroLoteInterno;
	private List cccPagosBancariosManuelesDigitadosList;
	private List siccCuentaCorrienteList;
	private List listaCampos1;
	private DataTableModel datatableCCCD;
	private List columnasSeleccionadas;
	private List detallesCargoAbonoDirecList;
	
	public void agregarUltimaFila(String indice) {

		int i = new Integer(indice).intValue();

		MantenimientoCCCDigitacionExternaPagosBancariosForm bean = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.listaCampos1
				.get(i);
		bean.setFechaPago(DateUtil.convertDateToString(bean.getFechaPagoD()));
		String fechaPago = bean.getFechaPago();
		String importePago = bean.getImportePago();
		int tamanio = this.listaCampos1.size();
	
			if (tamanio > 0) {
				for (int j = 0; j < listaCampos1.size(); j++) {
					if (this.listaCampos1.size() == j) {
						MantenimientoCCCDigitacionExternaPagosBancariosForm base = new MantenimientoCCCDigitacionExternaPagosBancariosForm();
						base.setFechaPagoD(new Date());
						this.listaCampos1.add(base);
					}

				}
			} else {
				this.addWarn("Advertencia : ",
						this.getResourceMessage("ccc.mensaje.completarDatos"));
				return;
			}
	}

	public void agregarUnaFila(String indice) {
		try {
			
		int i = new Integer(indice).intValue();

		MantenimientoCCCDigitacionExternaPagosBancariosForm bean = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.listaCampos1
				.get(i);
		bean.setFechaPago(DateUtil.convertDateToString(bean.getFechaPagoD()));
		
			String fechaPago = bean.getFechaPago();
			String importePago = bean.getImportePago();
			
			String nombreConsultora = bean.getNombreConsultora();

			if (!nombreConsultora.isEmpty() && !fechaPago.isEmpty()
					&& !importePago.isEmpty()) {
				MantenimientoCCCDigitacionExternaPagosBancariosForm base = new MantenimientoCCCDigitacionExternaPagosBancariosForm();
				base.setFechaPagoD(new Date());
				this.listaCampos1.add(base);
				this.datatableCCCD = new DataTableModel(this.listaCampos1);
				this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(i+1)+":idCodConsultora')");
			} else {
				this.addWarn("Advertencia : ",
						this.getResourceMessage("ccc.mensaje.completarDatos"));
				return;
			}
		} catch (Exception e) {
			this.addWarn("Advertencia : ",
					this.getResourceMessage("ccc.mensaje.completarDatos"));
			return;
		}
		

	}
	
	

	/**
	 * @param indice
	 */
	public void validaConsultoraOnEnter(String indice) {
		log.info(indice);
		int i = new Integer(indice).intValue();
		if (i < 0)
			return;
		MantenimientoCCCDigitacionExternaPagosBancariosForm bean = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.listaCampos1
				.get(i);
		
		String mensaje = null;
		if (bean == null) {
			return;
		}
		if (StringUtils.isBlank(bean.getCodigoConsultora())) {
			this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(indice)+":idCodConsultora')");
			return;
		} else {
			MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm)this.formBusqueda;

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String codigoConsultora = bean.getCodigoConsultora();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo();
			LabelDatosConsultoraValue[] obtenerDatosConsultora = null;
			obtenerDatosConsultora = aSvc.getCabeceraConsultoraSimple(codigoPais, codigoConsultora);
			if(obtenerDatosConsultora == null){
				mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
				this.addError("Error : ", mensaje);
				return;
			}
			bean.setNombreConsultora(obtenerDatosConsultora[0]
					.getNombreConsultora());
			
		}
		this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(indice)+":idImportePago')");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		
		if(StringUtils.equals(accion, "SAVE")){
			if(this.listaCampos1.size() == 1){
				MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm)this.listaCampos1.get(0);
				if(StringUtils.isBlank(f.getCodigoConsultora()) && StringUtils.isBlank(f.getImportePago())){
					mensaje = this.getResourceMessage("mensaje.ingresarUnDetalle");
				}
				else{
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImportePago()))
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
				}				
			}else{
				for (int i = 0; i < this.listaCampos1.size(); i++) {
					MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm)this.listaCampos1.get(i);
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImportePago())){
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
						break;
					}				
				}
			}
		}
		
		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseMantenimientoAbstractAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void save(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'save' method");
		}
		
		String mensaje = "";
		
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			// Extraemos el usuario de la sesi�n
	        String codigoUsuario = usuario.getLogin();
	        
	        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService)getBean("spusicc.consultaCCCGenericoService");
	        
	        Map criteria = new HashMap();
			       				
			serviceCCC.getNumeroLote(criteria);
							
			this.numeroLoteInterno = criteria.get("numeroLote").toString();
			
			 if (log.isDebugEnabled()) {
		            log.debug("JFA Obteniendo Numero de Lote " + numeroLoteInterno);
		        }		
			 
	        
			MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm)this.formBusqueda;
				
			if (log.isDebugEnabled()) {
				log.debug(codigoUsuario);
				log.debug("JFA : cargando los datos de la cabecera");
			}
			
			 criteria.put("codigoUsuario", codigoUsuario);
			 criteria.put("codigoBanco", f.getCodigoCuentaCorrienteBancaria());			 	
			
			 if (log.isDebugEnabled()) {				
					log.debug("Parametros " +  criteria.toString());
				}
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : cargando los datos del detalle");
			}
			
			//Carga los detalles
			if(this.cccPagosBancariosManuelesDigitadosList != null)
				detallesCargoAbonoDirecList = this.cccPagosBancariosManuelesDigitadosList;
			if (log.isDebugEnabled()) {
				log.debug("JFA : obteniendo los datos de la lista");
			}
			
			setDetalles(criteria);
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : seteando los datos del detalle");
			}
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : Llamando al Service");
			}
			
			MantenimientoCCCDigitacionExternaPagosBancariosService service = (MantenimientoCCCDigitacionExternaPagosBancariosService)getBean("spusicc.mantenimientoCCCDigitacionExternaPagosBancariosService");
			service.generarPagoBancarioExterno(criteria, this.detallesCargoAbonoDirecList);
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : Generando Mensajes");
			}
			
			mensaje = "mantenimientoCCCDigitacionExternaPagosBancariosForm.msj.registrar";
			this.addInfo("Info : ", mensaje);
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
		
	}
	
	public void eliminar(ActionEvent evt){
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'delete' method");
		}

		try {
			MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.formBusqueda;
			int contador = 0;
			// Lista que esta en memoria
			List originalList = this.cccPagosBancariosManuelesDigitadosList;
			List listaAuxiliar = this.listaCampos1;
			int tamanio = listaCampos1.size();
			
			if(tamanio > 1){
				if (this.listaCampos1.size() == this.columnasSeleccionadas.size()) {
					reiniciarDatatable(f);
				}else{
					this.listaCampos1.removeAll(this.columnasSeleccionadas);
					this.datatableCCCD = new DataTableModel(this.listaCampos1);
				}
			}else{
				this.addWarn("Advertencia: ", "No se puede eliminar cuando existe una sola fila.");
			}
			
			/*for (int i = 0; i < tamanio; i++) {
				MantenimientoCCCDigitacionExternaPagosBancariosForm bean = (MantenimientoCCCDigitacionExternaPagosBancariosForm) listaCampos1.get(i);
				if (StringUtils.isBlank(bean.getNombreConsultora()) || StringUtils.isBlank(bean.getImportePago())) {
					return;
				}
				for (int j = 0; j < columnasSeleccionadas.size(); j++) {
					MantenimientoCCCDigitacionExternaPagosBancariosForm seleccionado = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.columnasSeleccionadas.get(j);
					if (bean.getCodigoConsultora().equals(seleccionado.getCodigoConsultora())) {
						listaAuxiliar.remove(i);
						contador++;
						i--;
						if (contador == columnasSeleccionadas.size()) {
							return;
						}
					}
				}
			}*/
			
			/*this.listaCampos1 = listaAuxiliar;
			this.cccPagosBancariosManuelesDigitadosList = originalList;
			this.datatableCCCD = new DataTableModel(this.cccPagosBancariosManuelesDigitadosList);*/

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
  
	}
	
	/**
	 * @param f
	 * @param detallesList
	 * Setea los datos del Detalle de Pagos Bancarios
	 */
	private void setDetalles(Map criteria)  {
		
		MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.formBusqueda;
		if (log.isDebugEnabled()) {				
			log.debug("Parametros de Cabecera" +  criteria.toString());
		}
		
		int tamanio = listaCampos1.size();
		this.detallesCargoAbonoDirecList = new ArrayList();
		
		for (int i = 0; i < tamanio; i++) {
			log.debug("JFA : Recorriendo lista de codigos");
			
			MantenimientoCCCDigitacionExternaPagosBancariosForm bean = (MantenimientoCCCDigitacionExternaPagosBancariosForm) this.listaCampos1.get(i);
			if(StringUtils.isBlank(bean.getCodigoConsultora())){
				
			}else{
				PagoBancarioExterno pagoBancarioExternoDetalle = new PagoBancarioExterno();
				
				pagoBancarioExternoDetalle.setNumeroLote(criteria.get("numeroLote").toString());
				pagoBancarioExternoDetalle.setCodigoBanco(criteria.get("codigoBanco").toString());
				pagoBancarioExternoDetalle.setCodigoConsultora(bean.getCodigoConsultora().toString());
				
				double importePago = Double.parseDouble(bean.getImportePago().toString());
							
				pagoBancarioExternoDetalle.setImportePago(importePago);	
				
				if(bean.getFechaPagoD() != null){
					bean.setFechaPago(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, bean.getFechaPagoD()));
				}
				pagoBancarioExternoDetalle.setFechaPago(bean.getFechaPago());
				this.detallesCargoAbonoDirecList.add(pagoBancarioExternoDetalle);
				
			}
		}
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCDigitacionExternaPagosBancariosForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCDigitacionExternaPagosBancariosForm form = new MantenimientoCCCDigitacionExternaPagosBancariosForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}


	@Override
	protected boolean setDeleteAttributes() throws Exception {

  
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	
	public void inicializar(){

		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonBuscar = false;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("JFA Entering 'setViewAttributes' method");
        }
		
		inicializar();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.listaCampos1 = new ArrayList();
		MantenimientoCCCDigitacionExternaPagosBancariosForm base = new MantenimientoCCCDigitacionExternaPagosBancariosForm();
		base.setFechaPagoD(new Date());
		this.listaCampos1.add(base);
		this.datatableCCCD = new DataTableModel(this.listaCampos1);
		//Map para almacenar los parametros
		Map criteria = new HashMap();
				
		//Seteando el tama�o del Campo Consultora
		MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm)this.formBusqueda;
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");			
        f.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(pais.getCodigo()).toString());
        if (log.isDebugEnabled()) {
           log.debug("------------------ "+clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
        }
        
		//Obteniedo el listado de las Cuentas Corrientes Bancarias	        		
		criteria.put("codigoPais", pais.getCodigo());
        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");	        
		this.siccCuentaCorrienteList = serviceCCC.getCuentasCorrientesBancariasExternasList(criteria); 
		
		if(this.siccCuentaCorrienteList != null && this.siccCuentaCorrienteList.size() > 0 ){
			for (int i = 0; i < 1; i++) {
				Base baseSicc = (Base) this.siccCuentaCorrienteList.get(i);
				f.setCodigoCuentaCorrienteBancaria(baseSicc.getCodigo());
			}	
		}
		
		//f.setCodigoCuentaCorrienteBancaria(this.siccCuentaCorrienteList[0].)
        
        if (log.isDebugEnabled()) {
		log.debug("JFA Todo Ok: Redireccionando");
        }		
	}
	
	private void reiniciarDatatable(MantenimientoCCCDigitacionExternaPagosBancariosForm f){
		MantenimientoCCCDigitacionExternaPagosBancariosForm nuevo = new MantenimientoCCCDigitacionExternaPagosBancariosForm();
		setearDatos(f, nuevo);
		nuevo.setCodigoConsultora("");
		nuevo.setImportePago("");
		nuevo.setNombreConsultora("");	
		List aux = new ArrayList();
		aux.add(nuevo);
		this.listaCampos1 = aux;
		this.datatableCCCD = new DataTableModel(this.listaCampos1);		
	}
	
	private void setearDatos(MantenimientoCCCDigitacionExternaPagosBancariosForm f, MantenimientoCCCDigitacionExternaPagosBancariosForm nuevo){
		nuevo.setCodigoPais(f.getCodigoPais());
		nuevo.setFechaPago(f.getFechaPago());
		nuevo.setFechaPagoD(f.getFechaPagoD());
		nuevo.setIndicadorHayRegistros(f.getIndicadorHayRegistros());
		nuevo.setCodigoPeriodo(f.getCodigoPeriodo());
		nuevo.setLongitudCampoClientes(f.getLongitudCampoClientes());				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
//	@Override
//	public String setValidarConfirmar(String accion) {
//		MantenimientoCCCDigitacionExternaPagosBancariosForm f = (MantenimientoCCCDigitacionExternaPagosBancariosForm)this.formBusqueda;
//		
//		String mensaje = "";
//		
//		if(StringUtils.equals(accion, "SAVE")){
//			if(StringUtils.isBlank(f.getCodigoCuentaCorrienteBancaria())){
//				mensaje = "'Cuenta Corriente Bancaria' es un campo requerido";
//			}
//			
//			for (int i = 0; i < listaCampos1.size(); i++) {
//				MantenimientoCCCDigitacionExternaPagosBancariosForm listaGrilla = (MantenimientoCCCDigitacionExternaPagosBancariosForm) listaCampos1.get(i);
//				
//				if(listaGrilla.getFechaPagoD() == null){
//					mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
//				}
//				
//				if(StringUtils.isBlank(listaGrilla.getImportePago())){
//					mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
//				}
//			}
//		}
//		
//		if(StringUtils.equals(accion, "ELIMINAR")){
//			
//		}
//		
//		return mensaje;
//	}

	/**
	 * @return the numeroLoteInterno
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}

	/**
	 * @param numeroLoteInterno the numeroLoteInterno to set
	 */
	public void setNumeroLoteInterno(String numeroLoteInterno) {
		this.numeroLoteInterno = numeroLoteInterno;
	}

	/**
	 * @return the cccPagosBancariosManuelesDigitadosList
	 */
	public List getCccPagosBancariosManuelesDigitadosList() {
		return cccPagosBancariosManuelesDigitadosList;
	}

	/**
	 * @param cccPagosBancariosManuelesDigitadosList the cccPagosBancariosManuelesDigitadosList to set
	 */
	public void setCccPagosBancariosManuelesDigitadosList(
			List cccPagosBancariosManuelesDigitadosList) {
		this.cccPagosBancariosManuelesDigitadosList = cccPagosBancariosManuelesDigitadosList;
	}

	/**
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the listaCampos1
	 */
	public List getListaCampos1() {
		return listaCampos1;
	}

	/**
	 * @param listaCampos1 the listaCampos1 to set
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
	 * @param datatableCCCD the datatableCCCD to set
	 */
	public void setDatatableCCCD(DataTableModel datatableCCCD) {
		this.datatableCCCD = datatableCCCD;
	}

	/**
	 * @return the detallesCargoAbonoDirecList
	 */
	public List getDetallesCargoAbonoDirecList() {
		return detallesCargoAbonoDirecList;
	}

	/**
	 * @param detallesCargoAbonoDirecList the detallesCargoAbonoDirecList to set
	 */
	public void setDetallesCargoAbonoDirecList(List detallesCargoAbonoDirecList) {
		this.detallesCargoAbonoDirecList = detallesCargoAbonoDirecList;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}	
}