package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.BonoPremioPago;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCPagoConcursoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCPagoConcursoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCPagoConcursoSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCCargaPuntajeBonificadoForm;

@SessionScoped
@ManagedBean
public class MantenimientoINCPagoConcursoSearchAction extends BaseMantenimientoSearchAbstractAction {

	private List incPagoConcursoList;
	private List incPagoConcursoClasificacionesList;
	private List incConcursoList;
	private List incPagoBonoPremioList;
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		MantenimientoINCPagoConcursoSearchForm f = (MantenimientoINCPagoConcursoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());	
		
		this.incPagoConcursoList = new ArrayList();
		this.mostrarBotonEliminar = false;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub

		MantenimientoINCPagoConcursoSearchForm  f = (MantenimientoINCPagoConcursoSearchForm) this.formBusqueda;
		MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
					this.getBean("spusicc.mantenimientoINCPagoConcursoService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		List resultado = service.getListPagoConcurso(criteria);
		this.incPagoConcursoList = resultado;
		return resultado;
		
	}
	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoINCPagoConcursoForm";
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoINCPagoConcursoSearchForm form = new MantenimientoINCPagoConcursoSearchForm();
		return form;
	}
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoINCPagoConcursoList";
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String setValidarConfirmar(String accion) {
		Map data = (Map) this.beanRegistroSeleccionado;
		
		// ############### VALIDAR CAMPOS REQUERIDOS #######################
		if (datatableBusqueda == null)
			return this.getResourceMessage("errors.sin.registros");
		if (beanRegistroSeleccionado == null)
			return this.getResourceMessage("errors.select.item");
		
		if (accion.equals("ELIMINAR")) {
			String totalProcesado = MapUtils.getString(data, "totalProcesado");
			
			if(Integer.parseInt(totalProcesado)>0) {
				return this.getResourceMessage("mantenimientoINCPagoConcursoSearchForm.msg.validarTotalProcesados");
			}
		}
		
		if (accion.equals("CERRAR")) {
			String estadoPago = MapUtils.getString(data, "estadoPago");
			
			if(estadoPago.equals("0")) {
				return this.getResourceMessage("mantenimientoINCPagoConcursoSearchForm.msg.validarPagoCerrado");
			}
		}

		return null;
	}
	
	public void eliminar(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminar ' method");
		}
		
		Map data = (Map) this.beanRegistroSeleccionado;
		
		if (data != null) {
			try {			
				MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
						this.getBean("spusicc.mantenimientoINCPagoConcursoService");
								
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();						
				data.put("codigoUsuario", usuario.getLogin());
				
				service.deletePagoConcurso(data);
				
				//enviamos el mensaje de satisfaccion
				this.addInfo("", this.getResourceMessage("mantenimientoINCPagoConcursoSearchForm.deleted"));
				
				this.find(event);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				
				addError("Error",
						this.getResourceMessage("errors.detail", new Object[] {error}));	
			}
		}

	}
	
	public void cerrar(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerrar ' method");
		}
		
		Map data = (Map) this.beanRegistroSeleccionado;
		
		if (data != null) {
			try {			
				MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
						this.getBean("spusicc.mantenimientoINCPagoConcursoService");
								
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();						
				data.put("codigoUsuario", usuario.getLogin());
				
				//Obtenemos el Periodo Proceso
				Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				Map criteria= new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				
		        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
				MantenimientoOCRPedidoControlFacturacionService serviceOCR = 
						(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

				// Carga el periodo proceso
				data.put("codigoPeriodoProceso", controlFacturacion.getCodigoPeriodo());
				
				service.cerrarPagoConcurso(data);
				
				//enviamos el mensaje de satisfaccion
				this.addInfo("", this.getResourceMessage("mantenimientoINCPagoConcursoSearchForm.cerrado"));
				
				this.find(event);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				
				addError("Error",
						this.getResourceMessage("errors.detail", new Object[] {error}));	
			}
		}
		
	}
	
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoINCPagoConcursoForm f = new MantenimientoINCPagoConcursoForm();
		f.reset();
		
		MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
				getBean("spusicc.mantenimientoINCPagoConcursoService");
		
		this.incPagoConcursoClasificacionesList = service.getListClasificacionesPagoConcurso(null);
		this.incConcursoList = service.getListConcursosPago(null);
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setAddAttributes ' method");
			}
			
			f.setCodigoPago(service.getMaxPagoConcurso());
			f.setEstadoPago("1");
			
			this.incPagoBonoPremioList = new ArrayList();
			f.setListBonoPremio(this.incPagoBonoPremioList);
			
		} else {
			Map map = (Map) this.beanRegistroSeleccionado;
			
			f.setCodigoPais(String.valueOf(map.get("codigoPais")));
			f.setCodigoPago(String.valueOf(map.get("codigoPago")));
			f.setNumeroConcurso(String.valueOf(map.get("numeroConcurso")));
			f.setNombrePago(String.valueOf(map.get("nombrePago")));
			f.setCodigoPeriodoInicio(String.valueOf(map.get("codigoPeriodoInicio")));
			f.setCodigoPeriodoFin(String.valueOf(map.get("codigoPeriodoFin")));
			f.setCodigoClasificacion(String.valueOf(map.get("codigoClasificacion")));
			f.setIndicePeriodo(String.valueOf(map.get("indicePeriodo")));
			f.setEstadoPago(String.valueOf(map.get("estadoPago")));
			
			//Obtenemos la lista de Premios del Concurso
			Map criteria= new HashMap();
			criteria.put("codigoPago", f.getCodigoPago());
			criteria.put("numeroConcurso", f.getNumeroConcurso());
			List listPagoBonoPremio = service.getListPagoBonoPremio(criteria);
			
			for(int i=0; i<listPagoBonoPremio.size(); i++) {
				BonoPremioPago mapBono = (BonoPremioPago)listPagoBonoPremio.get(i);
				
				if(mapBono.getIndicadorBonoCCC().equals("0"))
					mapBono.setIndicadorBonoCCC("false");
				else
					mapBono.setIndicadorBonoCCC("true");
				
				if(mapBono.getIndicadorBonoBanco().equals("0"))
					mapBono.setIndicadorBonoBanco("false");
				else
					mapBono.setIndicadorBonoBanco("true");
				
			}
			
			f.setListBonoPremio(listPagoBonoPremio);
			this.incPagoBonoPremioList = listPagoBonoPremio;

			f.setIndActualizarBonoPremio(false);
		}
		
		return f;
	}
	
	@Override
	public String setValidarMantenimiento() {
		MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
				getBean("spusicc.mantenimientoINCPagoConcursoService");
		MantenimientoINCPagoConcursoForm f = (MantenimientoINCPagoConcursoForm) this.formMantenimiento;

		if(f.getCodigoPeriodoInicio().compareTo(f.getCodigoPeriodoFin())>0) {
			return getResourceMessage("mantenimientoINCPagoConcursoForm.msg.rangoPeriodos");
		}
    	
		Map mapConcurso = new HashMap();
		mapConcurso.put("numeroConcurso", f.getNumeroConcurso());
		mapConcurso = service.getPeriodosDespachoConcurso(mapConcurso);
		
		//Validamos si existe el Concurso Ingresado
		if(mapConcurso == null) {
			return getResourceMessage("mantenimientoINCPagoConcursoForm.msg.concursoNoExiste");
		}

		//Validamos si existe el Periodo Inicio
		if(service.getOidPeriodoByCodigoPeriodo(f.getCodigoPeriodoInicio())==null) {
			return getResourceMessage("mantenimientoINCPagoConcursoForm.msg.existePeriodoInicio");
		}
		
		//Validamos si existe el Periodo Fin
		if(service.getOidPeriodoByCodigoPeriodo(f.getCodigoPeriodoFin())==null) {
			return getResourceMessage("mantenimientoINCPagoConcursoForm.msg.existePeriodoFin");
		}
		
		String periodoDespachoInicio = (String)mapConcurso.get("PERIODOINICIO");
		String periodoDespachoFin = (String)mapConcurso.get("PERIODOFIN");
		
		//Validamos el Periodo Inicio con Periodo Inicio Despacho del Concurso
		if(f.getCodigoPeriodoInicio().compareTo(periodoDespachoInicio)<0) {
			return getResourceMessage("mantenimientoINCPagoConcursoForm.msg.periodoInicioDespacho");
		}
		
		//Validamos el Periodo Fin con Periodo Fin Despacho del Concurso
		if(f.getCodigoPeriodoFin().compareTo(periodoDespachoFin)>0) {
			return getResourceMessage("mantenimientoINCPagoConcursoForm.msg.periodoFinDespacho");
		}
				
		return "";

	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
												getBean("spusicc.mantenimientoINCPagoConcursoService");
		MantenimientoINCPagoConcursoForm f = (MantenimientoINCPagoConcursoForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("listBonoPremio", this.incPagoBonoPremioList);
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
		    service.insertPagoConcurso(params);//inserta
		    
		    addInfo("",	getResourceMessage("mantenimientoINCPagoConcursoForm.insert"));

		}
		else{
			service.updatePagoConcurso(params);//update
			
			//enviamos el mensaje de satisfaccion
			addInfo("",	getResourceMessage("mantenimientoINCPagoConcursoForm.update"));
		}	

		return true;
	}

	public void selectConcurso(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("selectConcurso");
		}
		MantenimientoINCPagoConcursoService service = (MantenimientoINCPagoConcursoService) 
				this.getBean("spusicc.mantenimientoINCPagoConcursoService");
		MantenimientoINCPagoConcursoForm f = (MantenimientoINCPagoConcursoForm) this.formMantenimiento;
		
		String numeroConcurso = (String) val.getNewValue();
		if (StringUtils.isNotBlank(numeroConcurso)) {
			//Obtenemos la lista de Premios del Concurso
			Map criteria= new HashMap();
			criteria.put("numeroConcurso", numeroConcurso);
			List listPagoBonoPremio = service.getListPagoBonoPremio(criteria);
			
			f.setListBonoPremio(listPagoBonoPremio);
			this.incPagoBonoPremioList = listPagoBonoPremio;
			f.setIndActualizarBonoPremio(true);
 	
		} else {
			this.incPagoBonoPremioList = new ArrayList();
		}
		
	}

	public void actualizarBonoCCC(ValueChangeEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("actualizarBonoCCC");
		}
		
		try {
			MantenimientoINCPagoConcursoForm f = (MantenimientoINCPagoConcursoForm) this.formMantenimiento;
			Boolean indicadorBonoCCC = (Boolean) event.getNewValue();
			
			SelectBooleanCheckbox checkbox = (SelectBooleanCheckbox) event.getSource();
			String id = checkbox.getClientId();
			String posicion = id.split(":")[1];
			
			if(indicadorBonoCCC) {
				BonoPremioPago mapBono = (BonoPremioPago)this.incPagoBonoPremioList.get(Integer.parseInt(posicion));
				mapBono.setIndicadorBonoBanco("false");
			}
			
			f.setIndActualizarBonoPremio(true);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void actualizarBonoBanco(ValueChangeEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("actualizarBonoBanco");
		}
		
		try {
			MantenimientoINCPagoConcursoForm f = (MantenimientoINCPagoConcursoForm) this.formMantenimiento;
			Boolean indicadorBonoBanco = (Boolean) event.getNewValue();
			
			SelectBooleanCheckbox checkbox = (SelectBooleanCheckbox) event.getSource();
			String id = checkbox.getClientId();
			String posicion = id.split(":")[1];
			
			if(indicadorBonoBanco) {
				BonoPremioPago mapBono = (BonoPremioPago)this.incPagoBonoPremioList.get(Integer.parseInt(posicion));
				mapBono.setIndicadorBonoCCC("false");
			}
			
			f.setIndActualizarBonoPremio(true);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @return the incPagoConcursoList
	 */
	public List getIncPagoConcursoList() {
		return incPagoConcursoList;
	}

	/**
	 * @param incPagoConcursoList the incPagoConcursoList to set
	 */
	public void setIncPagoConcursoList(List incPagoConcursoList) {
		this.incPagoConcursoList = incPagoConcursoList;
	}

	/**
	 * @return the incPagoConcursoClasificacionesList
	 */
	public List getIncPagoConcursoClasificacionesList() {
		return incPagoConcursoClasificacionesList;
	}

	/**
	 * @param incPagoConcursoClasificacionesList the incPagoConcursoClasificacionesList to set
	 */
	public void setIncPagoConcursoClasificacionesList(
			List incPagoConcursoClasificacionesList) {
		this.incPagoConcursoClasificacionesList = incPagoConcursoClasificacionesList;
	}

	/**
	 * @return the incConcursoList
	 */
	public List getIncConcursoList() {
		return incConcursoList;
	}

	/**
	 * @param incConcursoList the incConcursoList to set
	 */
	public void setIncConcursoList(List incConcursoList) {
		this.incConcursoList = incConcursoList;
	}

	/**
	 * @return the incPagoBonoPremioList
	 */
	public List getIncPagoBonoPremioList() {
		return incPagoBonoPremioList;
	}

	/**
	 * @param incPagoBonoPremioList the incPagoBonoPremioList to set
	 */
	public void setIncPagoBonoPremioList(List incPagoBonoPremioList) {
		this.incPagoBonoPremioList = incPagoBonoPremioList;
	}
	
}
