package biz.belcorp.ssicc.web.spusicc.ruv.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVDocumentosContablesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.ruv.form.MantenimientoRUVDocumentosContablesVenezuelaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRUVDocumentosContablesVenezuelaAction extends BaseMantenimientoSearchAbstractAction
{	
	private static final long serialVersionUID = -7500776326574406282L;
	
	
	/*los campos del principio*/
	private List ruvTipoDocumentoContableVenezuelaList; 
	private List ruvAccesoVenezuelaList;
	private LabelValue[] ruvSubAccesoVenezuelaList;
	private LabelValue[] accesoVenezuelaList;
	
	/*indicador de que muestre ciertas tablas*/
	private String indEliminarDocContVenezuela;
	
	/* tabla Sin impresion, sin unidades, sin total*/
	private DataTableModel dataModelSinImpresion;
	private List selectedSinImpresion;	
	/**/
	
	private List ruvSinImpresionVenezuelaList;
	private List ruvDuplicadosVenezuelaList;
	private List ruvSinAsignarVenezuelaList;

	private String ruvCantidadNulosAsignarVenezuela;

	private boolean indicadorPorDiasBool;
	private boolean indicadorPorRangoBool;

	private List ruvDocLegalesVenezuelaList;
	private String ruvCantidadFilasParamPaginaLegalVenezuela;
	private List ruvDocInternosVenezuelaList;
	private String ruvCantidadFilasParamPaginaInternoVenezuela;
	private String ruvCantidadFilasParamPaginaControlVenezuela;
	private List ruvNumControlVenezuelaList;
	private List ruvNumConDuplicadosVenezuelaList;
	private List ruvSinAsignarNumConVenezuelaList;
	private List ruvAsignarNulosNumConVenezuelaList;
	


	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRUVDocumentosContablesVenezuelaForm searchForm = new MantenimientoRUVDocumentosContablesVenezuelaForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonBuscar = false;
		this.listaBusqueda = null;
		this.mostrarListaBusqueda = false;
		this.indicadorPorDiasBool = true;
		
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - setViewAttributes");

		// -- Variables ------------------------------------------
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaHasta(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaDesdeDate(new Date(System.currentTimeMillis()));
		f.setFechaHastaDate(new Date(System.currentTimeMillis()));
		f.setCodigoTipoDocumentoContable("");
		f.setCodigoCanal("");
		f.setCodigoAcceso("");
		f.setCodigoSubacceso("");
		f.setSerie("");						
		f.setAccion("");		
		f.setIndicadorPorDias("");
		f.setIndicadorPorRango("");
		f.setDocumentoLegalInicial("");
		f.setDocumentoLegalFinal("");
		f.setConsultaAsignarResult("");
		f.setNumeroControlInicial("");
		f.setNumeroControlFinal("");
		f.setDescripcionTipoDocumento("");
		f.setDescripcionAcceso("");
		f.setDescripcionSubacceso("");
		f.setDescripcionCanal("");
		f.setNumeroControl(null);
		
		String codigoSistema = this.parametrosPantalla.get("codigoSistema");
		String codigoParametro = this.parametrosPantalla.get("codigoParametro");

		// -- Recuperar indicador de activacion ------------------
		// -- de documento contable

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSistema", codigoSistema);
		criteria.put("codigoParametro", codigoParametro);

		String indicadorActDocCon = service.getIndicadorActivacionDocumentoContable(criteria).toLowerCase().trim();

		// -- Inicializar atributos ------------------------------
		f.setCodigoPais(pais.getCodigo());
		f.setMinimo(Constants.NUMERO_CERO);
		f.setMaximo(Constants.NUMERO_CERO);
		f.setDiferenciaMaxMin(Constants.NUMERO_CERO);
		f.setTotalRegistros(Constants.NUMERO_CERO);
		f.setNumeroDocumentoInternoInicial("");
		f.setNumeroDocumentoInternoFinal("");
		f.setNumeroDocumentoLegalInicial("");
		f.setNumeroDocumentoLegalFinal("");
		f.setNumeroFilasLegal("10");
		f.setNumeroFilasInterno("10");
		f.setNumeroFilasControl("10");
		f.setIndicadorActDocCon(indicadorActDocCon);
		f.setMinimoControl(Constants.NUMERO_CERO);
		f.setMaximoControl(Constants.NUMERO_CERO);
		f.setDiferenciaMaxMinControl(Constants.NUMERO_CERO);
		f.setTotalRegistrosControl(Constants.NUMERO_CERO);

		if (f.getIndicadorActDocCon().equalsIgnoreCase("S")) {
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
			f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_000);
		}

		// -- Peticiones -----------------------------------------

		// Setea el numero de lineas a mostrar en las grillas
//		session.setAttribute(Constants.RUV_NUMERO_REGISTROS_PAGINA_VENEZUELA, "10");
		
		// Setea el combo de Tipos de documento contable
		this.ruvTipoDocumentoContableVenezuelaList = service.getTipoDocumentoContable();
		
		//Cuando indicadorActDocCon == "s", se setea nuevamente el combo de Tipos de documento contable
		if(f.getIndicadorActDocCon().equals("s")){
			List auxLista = new ArrayList();
			
			for (int i = 0; i < this.ruvTipoDocumentoContableVenezuelaList.size(); i++) {
				Base temp = (Base)this.ruvTipoDocumentoContableVenezuelaList.get(i);
				if(temp.getCodigo().equals("1") || temp.getCodigo().equals("32"))
					auxLista.add(temp);			
			}	
					
			this.ruvTipoDocumentoContableVenezuelaList = new ArrayList();
			this.ruvTipoDocumentoContableVenezuelaList = auxLista;
		}
		
		// Setea el combo de Canales
		this.ruvAccesoVenezuelaList = siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		// Setea el combo de Acceso
		Base aux = (Base) this.ruvAccesoVenezuelaList.get(0);
		if(f.getIndicadorActDocCon().equalsIgnoreCase("s"))
			this.accesoVenezuelaList = ajax.getAccesoByCanal(f.getCodigoCanal());
		else
			this.accesoVenezuelaList = ajax.getAccesoByCanal(aux.getCodigo());
		
		// Setea el combo de SubAcceso
		if(f.getIndicadorActDocCon().equalsIgnoreCase("s"))
			this.ruvSubAccesoVenezuelaList = ajax.getSubaccesoByAcceso(f.getCodigoAcceso());
		else{
			this.ruvSubAccesoVenezuelaList = ajax.getSubaccesoByAcceso(this.accesoVenezuelaList[0].getValue());
			f.setCodigoSubacceso("310");
		}
			
		this.indEliminarDocContVenezuela = service.getIndicadorRUVEliminarDocumentoContable(pais.getCodigo());
	
		log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - setViewAttributes");
	}
	
	private Map getCriteria(MantenimientoRUVDocumentosContablesVenezuelaForm f) {
		Map criteria = new HashMap();	
		
		f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeDate()));
		f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaDate()));
		
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("oidTipoDocumento", f.getCodigoTipoDocumentoContable());
		criteria.put("fechaDesde", f.getFechaDesde());
		criteria.put("fechaHasta", f.getFechaHasta());
		criteria.put("codigoSubacceso", f.getCodigoSubacceso());
		criteria.put("serie", f.getSerie());
		criteria.put("codigoCanal", f.getCodigoCanal());
		criteria.put("codigoAcceso", f.getCodigoAcceso());
		criteria.put("rangoInicio", f.getDocumentoLegalInicial());
		criteria.put("rangoFin", f.getDocumentoLegalFinal());
		criteria.put("cantidad", "*");
		return criteria;
	}
	
	/*este esel metodo setDeleteAttributes del SSICC*/
	public void deleteSinImpresion(ActionEvent event) 
	{
		MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		try {
			if (StringUtils.isBlank(f.getSerie())) 
			{
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else 
			{
				// Eliminar seleccionados
				if (f.getAccion().equals(Constants.NUMERO_CERO)) 
				{
					if (this.selectedSinImpresion != null && this.selectedSinImpresion.size() > 0) 
					{
						String[] oidRegi = new String[selectedSinImpresion.size()];
						log.debug("Nro de Seleccionadas: "+ selectedSinImpresion.size());
						for (int i = 0; i < this.selectedSinImpresion.size(); i++) {
							Map map = (Map) this.selectedSinImpresion.get(i);
							oidRegi[i] = map.get("oidRegi").toString();
						}

						service.deleteRegistroUnicoVentasById(oidRegi);
						this.addInfo("", this.getResourceMessage("mantenimientoRUVDocumentosContablesVenezuelaForm.deleted"));
					} else {
						this.addError("", "Seleccione elementos de la Tabla");
					}
				}

				// Eliminar todos
				if (f.getAccion().equals(Constants.NUMERO_UNO)) 
				{
					List impresionList = ruvSinImpresionVenezuelaList;
					String[] params = new String[impresionList.size()];
					
					if (impresionList != null && impresionList.size() > 0) 
					{						
						for (int i = 0; i < impresionList.size(); i++) {
							Map map = (Map) impresionList.get(i);
							params[i] = map.get("oidRegi").toString();
						}
						service.deleteRegistroUnicoVentasById(params);
					
						this.addInfo("",this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.deleted"));
					} else {
						this.addError("", "No se encontraron registros para la operación realizada");
					}
				}

				this.ruvSinImpresionVenezuelaList = service.getSinImpresionSinUnidades(getCriteria(f));
				this.dataModelSinImpresion = new DataTableModel(this.ruvSinImpresionVenezuelaList);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void buscar(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - setFindAttributes");

		try {
			// -- Variables
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String tipoAreaFind = externalContext.getRequestParameterMap().get("parametroAccion");
			
			List lista = new ArrayList();
			String total = new String();

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else 
			{
				if (tipoAreaFind.equals("docleg")) 
				{
					// -- Logica
					lista = service.getLimitesDocumentosLegales(getCriteria(f));
					total = service.getTotalDocumentosLegales(getCriteria(f));

					// -- Setear resultado
					Map map = (Map) lista.get(0);
					f.setMinimo(map.get("minimo") != null ? map.get("minimo").toString() : Constants.NUMERO_CERO);
					f.setMaximo(map.get("maximo") != null ? map.get("maximo").toString() : Constants.NUMERO_CERO);
					f.setDiferenciaMaxMin(map.get("diferencia") != null ? map.get("diferencia").toString() : Constants.NUMERO_CERO);
					f.setTotalRegistros(total);

				} else {

					// -- Logica
					lista = service.getLimitesControlDocumentosLegales(getCriteria(f));
					total = service.getTotalControlDocumentosLegales(getCriteria(f));

					// -- Setear resultado
					Map map = (Map) lista.get(0);
					f.setMinimoControl(map.get("minimo").toString());
					f.setMaximoControl(map.get("maximo").toString());
					f.setDiferenciaMaxMinControl(map.get("diferencia").toString());
					f.setTotalRegistrosControl(total);
				}
			}

			log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - setFindAttributes");			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchImpresion(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchImpresion' method");
		}
		try {
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {
				Map criteria = new HashMap();
				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());
				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());
				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				this.ruvSinImpresionVenezuelaList = service.getSinImpresionSinUnidades(criteria);
				this.dataModelSinImpresion = new DataTableModel(this.ruvSinImpresionVenezuelaList);

				// Flag de Nro de Busqueda 4
				// session.setAttribute(Constants.RUV_SECCION_BUSQUEDA_VAR_VENEZUELA, Constants.RUV_SECCION_BUSQUEDA_4);
				if (this.ruvSinImpresionVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchDuplicados(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchDuplicados' method");
		}
		try {
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			Map criteria = new HashMap();
			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {

				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());
				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());
				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				// session.setAttribute(Constants.RUV_DUPLICADOS_VENEZUELA_LIST, service.getLegalesDuplicados(criteria));
				this.ruvDuplicadosVenezuelaList = service.executeGenerarDataDocumentosLegalDuplicado(criteria);

				// Flag de Nro de Busqueda 5
				// session.setAttribute(Constants.RUV_SECCION_BUSQUEDA_VAR_VENEZUELA, Constants.RUV_SECCION_BUSQUEDA_5);
				if (this.ruvDuplicadosVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchSinAsignar(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchSinAsignar' method");
		}
		try {

			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {
				Map criteria = new HashMap();
				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());
				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());
				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				this.ruvSinAsignarVenezuelaList = service.getSinAsignarNumeroDocumento(criteria);

				// Flag de Nro de Busqueda 7
				if (this.ruvSinAsignarVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchNulos(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchNulos' method");
		}

		try {
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			String cantidad;
			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {
				int aux = validarCamposNulos(f);
				if (aux == 0) 
				{
					Map criteria = new HashMap();
					criteria.put("oidTipoDocumento", f.getCodigoTipoDocumentoContable());
					criteria.put("codigoSubacceso", f.getCodigoSubacceso());
					criteria.put("serie", f.getSerie());

					if (!f.getIndicadorPorDias().equals("")) {
						criteria.put("fechaDesde", f.getFechaDesde());
						criteria.put("fechaHasta", f.getFechaHasta());

						cantidad = service.getAsignarNulosPorDiasVenezuela(criteria);
					} else {
						criteria.put("fechaDesde", f.getFechaDesde());
						criteria.put("fechaHasta", f.getFechaHasta());
						criteria.put("rangoInicio", f.getDocumentoLegalInicial());
						criteria.put("rangoFin", f.getDocumentoLegalFinal());

						cantidad = service.getAsignarNulosPorRangoVenezuela(criteria);
					}

					String cad1 = this.getResourceMessage("mantenimientoRUVDocumentosContablesVenezuelaForm.documentoLegal.asignarNulos.cadena1");
					String cad2 = this.getResourceMessage("mantenimientoRUVDocumentosContablesVenezuelaForm.documentoLegal.asignarNulos.cadena2");

					f.setConsultaAsignarResult(cad1 + " " + cantidad + " " + cad2);

					// Flag de Nro de Busqueda 9
					this.ruvCantidadNulosAsignarVenezuela = cantidad;

					if (this.ruvCantidadNulosAsignarVenezuela.equals("0"))
						this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void asignarNulos(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'asignarNulos' method");
		}
		try {
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else 
			{
				int aux = validarCamposNulos(f);
				if (aux == 0) 
				{
					if (!f.getIndicadorPorDias().equals("")) {
						service.executeAsignarNulosPorDiasVenezuela(getCriteria(f));
					} else {
						service.executeAsignarNulosPorRangoVenezuela(getCriteria(f));
					}

					f.setConsultaAsignarResult("");
					this.addInfo("", this.getResourceMessage("mantenimientoRUVDocumentosContablesVenezuelaForm.execute.exito"));
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchDocLegal(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchDocLegal' method");
		}
		try {
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) 
			{
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else 
			{
				Map criteria = new HashMap();
				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());
				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());
				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess",
						Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service
						.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso",
						Constants.RUV_ENTIDAD_SEG_SUBAC);
				String descripcionSubAcceso = service
						.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				log.debug("oidAcceso :" + oidAcceso);
				log.debug("codigoSubAcceso :" + f.getCodigoSubacceso());
				log.debug("oidSubAcceso :" + oidSubAcceso);

				if (StringUtils.isNotBlank(f.getNumeroDocumentoLegalInicial())) {
					criteria.put("numeroDocumentoInicial", f.getNumeroDocumentoLegalInicial());
				}

				if (StringUtils.isNotBlank(f.getNumeroDocumentoLegalFinal())) {
					criteria.put("numeroDocumentoFinal", f.getNumeroDocumentoLegalFinal());
				}

				if (StringUtils.isNotBlank(f.getNumeroFilasLegal())) {
					if (f.getNumeroFilasLegal().equals("0")) {
						this.ruvCantidadFilasParamPaginaLegalVenezuela = "10";
					} else {
						this.ruvCantidadFilasParamPaginaLegalVenezuela = f.getNumeroFilasLegal();
					}
				} else {
					this.ruvCantidadFilasParamPaginaLegalVenezuela = "10";
				}

				this.ruvDocLegalesVenezuelaList = service.getDocContablesLegales(criteria);
				f.setNumeroDocumentoLegalInicial(f.getNumeroDocumentoLegalInicial());
				f.setNumeroDocumentoLegalFinal(f.getNumeroDocumentoLegalFinal());
				f.setNumeroFilasLegal(f.getNumeroFilasLegal());

				if (ruvDocLegalesVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchDocInterno(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchDocLegInt' method");
		}
		try{
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm)formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			Map criteria = new HashMap();
			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {
			criteria = getCriteria(f);
			criteria.put("codigoCanal", f.getCodigoCanal());
			String descripcionCanal = service.getDescCanal(criteria);
			criteria.put("descripcionCanal", descripcionCanal);
			criteria.put("codCanal", f.getCodigoCanal());
			String oidCanal = service.getOidCanalxCod(criteria);
			criteria.put("oidCanal", oidCanal);
			criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
			criteria.put("codigoAcceso", f.getCodigoAcceso());

			String descripcionAcceso = service.getDescripcionAcceso(criteria);
			criteria.put("descripcionAcceso", descripcionAcceso);
			
			String oidAcceso = service.getOidSegAcceso(criteria);
			criteria.put("oidAcceso", oidAcceso);
			criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
			criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
			String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
			criteria.put("descripcionSubAcceso", descripcionSubAcceso);
			
			String oidSubAcceso = service.getOidSegSubAcceso(criteria);
			criteria.put("oidSubAcceso", oidSubAcceso);
			
			log.debug("oidAcceso :"+oidAcceso);
			log.debug("codigoSubAcceso :"+f.getCodigoSubacceso());
			log.debug("oidSubAcceso :"+oidSubAcceso);
			
			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoInicial())){
				criteria.put("numeroDocumentoInicial", f.getNumeroDocumentoInternoInicial());
			}
			
			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoFinal())){
				criteria.put("numeroDocumentoFinal", f.getNumeroDocumentoInternoFinal());
			}
			
			if (StringUtils.isNotBlank(f.getNumeroFilasInterno())){
				if (f.getNumeroFilasInterno().equals("0")){
					this.ruvCantidadFilasParamPaginaInternoVenezuela = "10";
				}else{
					this.ruvCantidadFilasParamPaginaInternoVenezuela = f.getNumeroFilasInterno();
				}
			}else{
				this.ruvCantidadFilasParamPaginaInternoVenezuela = "10";
			}
			
			this.ruvDocInternosVenezuelaList = service.getDocContablesInternos(criteria);
			f.setNumeroDocumentoInternoInicial(f.getNumeroDocumentoInternoInicial());
			f.setNumeroDocumentoInternoFinal(f.getNumeroDocumentoInternoFinal());
			f.setNumeroFilasInterno(f.getNumeroFilasInterno());
			
			//Flag de Nro de Busqueda 2
			if(this.ruvDocInternosVenezuelaList.isEmpty())
				this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void deleteDocuLegalesLimites(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDocuLegalesLimites' method");
		}
		
		MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm)this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		
		Map criteria = getCriteria(f);
		
		criteria.put("codCanal", f.getCodigoCanal());
		String oidCanal = service.getOidCanalxCod(criteria);
		criteria.put("oidCanal", oidCanal);
		criteria.put("codigoAcceso", f.getCodigoAcceso());
		
		String oidAcceso = service.getOidSegAcceso(criteria);
		criteria.put("oidAcceso", oidAcceso);
		criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
		
		String oidSubAcceso = service.getOidSegSubAcceso(criteria);
		criteria.put("oidSubAcceso", oidSubAcceso);
		
		service.deleteDocuLegalesLimites(criteria);
	}
	
	public void deleteDocuLegales(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDocuLegales' method");
		}
		
		MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm)this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		
		Map criteria = getCriteria(f);
		
		criteria.put("codCanal", f.getCodigoCanal());
		String oidCanal = service.getOidCanalxCod(criteria);
		criteria.put("oidCanal", oidCanal);
		criteria.put("codigoAcceso", f.getCodigoAcceso());
		
		String oidAcceso = service.getOidSegAcceso(criteria);
		criteria.put("oidAcceso", oidAcceso);
		criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
		
		String oidSubAcceso = service.getOidSegSubAcceso(criteria);
		criteria.put("oidSubAcceso", oidSubAcceso);
		
		criteria.put("numeroDocuLegalInicio", f.getNumeroDocumentoLegalInicial());
		criteria.put("numeroDocuLegalFinal", f.getNumeroDocumentoLegalFinal());
		
		service.deleteDocuLegales(criteria);		
	}
	
	public void deleteDocuInternos(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDocuInternos' method");
		}
		
		MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm)this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		
		Map criteria = getCriteria(f);
		
		criteria.put("codCanal", f.getCodigoCanal());
		String oidCanal = service.getOidCanalxCod(criteria);
		criteria.put("oidCanal", oidCanal);
		criteria.put("codigoAcceso", f.getCodigoAcceso());
		
		String oidAcceso = service.getOidSegAcceso(criteria);
		criteria.put("oidAcceso", oidAcceso);
		criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
		
		String oidSubAcceso = service.getOidSegSubAcceso(criteria);
		criteria.put("oidSubAcceso", oidSubAcceso);
		
		criteria.put("numeroDocuInternoInicio", f.getNumeroDocumentoInternoInicial());
		criteria.put("numeroDocuInternoFinal", f.getNumeroDocumentoInternoFinal());
		
		service.deleteDocuInternos(criteria);
	}
	
	public void searchNumControl(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - searchNumControl");

		try {
			// -- Variables ------------------------------------------
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {
				// -- Crear pojo -----------------------------------------
				Map criteria = new HashMap();
				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());

				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());

				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);

				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				if (StringUtils.isNotBlank(f.getNumeroControlInicial())) {
					criteria.put("numeroControlInicial", f.getNumeroControlInicial());
				}
				if (StringUtils.isNotBlank(f.getNumeroControlFinal())) {
					criteria.put("numeroControlFinal", f.getNumeroControlFinal());
				}

				if (StringUtils.isNotBlank(f.getNumeroFilasControl())) {
					if (f.getNumeroFilasInterno().equals("0")) {
						this.ruvCantidadFilasParamPaginaControlVenezuela = "10";
					} else {
						this.ruvCantidadFilasParamPaginaControlVenezuela = f.getNumeroFilasControl();
					}
				} else {
					this.ruvCantidadFilasParamPaginaControlVenezuela = "10";
				}

				// -- Peticiones
				this.ruvNumControlVenezuelaList = service.getNumeroControlDocLeg(criteria);
				// session.setAttribute(Constants.RUV_Pestana_Contable_Venezuela,
				// Constants.RUV_Pestana_ContableControlTab_Venezuela);
				// session.setAttribute(Constants.RUV_Ditch_Tab_Focus_Legal_Venezuela,
				// Constants.RUV_Ditch_Tab_Ditch_Unfocused_Venezuela);
				// session.setAttribute(Constants.RUV_Ditch_Tab_Focus_Interno_Venezuela,
				// Constants.RUV_Ditch_Tab_Ditch_Unfocused_Venezuela);
				// session.setAttribute(Constants.RUV_Ditch_Tab_Focus_Control_Venezuela,
				// Constants.RUV_Ditch_Tab_Ditch_Focused_Venezuela);
				f.setNumeroControlInicial(f.getNumeroControlInicial());
				f.setNumeroControlFinal(f.getNumeroControlFinal());
				f.setNumeroFilasInterno(f.getNumeroFilasInterno());

				// Flag de Nro de Busqueda 3
				if (this.ruvNumControlVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - searchNumControl");
	}
	
	public void searchNumConDuplicados(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - searchNumConDuplicados");

		try {
			// -- Variables ------------------------------------------
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else 
			{
				// -- Crear pojo -----------------------------------------
				Map criteria = new HashMap();
				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());

				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());

				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);

				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				// -- Peticiones
				this.ruvNumConDuplicadosVenezuelaList = service.executeGenerarDataNumeroControlDuplicado(criteria);

				// Flag de Nro de Busqueda 6
				if (this.ruvNumConDuplicadosVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - searchNumConDuplicados");
	}
	
	public void searchNumConSinAsignar(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - searchNumConSinAsignar");
		try {
			// -- Variables ------------------------------------------
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else 
			{
				// -- Crear Pojo -----------------------------------------
				Map criteria = new HashMap();
				criteria = getCriteria(f);
				criteria.put("codigoCanal", f.getCodigoCanal());

				String descripcionCanal = service.getDescCanal(criteria);
				criteria.put("descripcionCanal", descripcionCanal);
				criteria.put("codCanal", f.getCodigoCanal());

				String oidCanal = service.getOidCanalxCod(criteria);
				criteria.put("oidCanal", oidCanal);
				criteria.put("entidadSegAccess", Constants.RUV_ENTIDAD_SEG_ACESS);
				criteria.put("codigoAcceso", f.getCodigoAcceso());

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);

				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);

				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);

				this.ruvSinAsignarNumConVenezuelaList = service.getSinAsignarNumeroControl(criteria);

				// Flag de Nro de Busqueda 8
				if (this.ruvSinAsignarNumConVenezuelaList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - searchNumConSinAsignar");
	}
	
	public void obtenerAsignarNulosNumCon(ActionEvent event)
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - obtenerAsignarNulosNumCon");
				
		try{
			//-- Variables
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm)this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			
			if (StringUtils.isBlank(f.getSerie())) {
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			} else {
			//-- Logica
			List lista = service.getAsignarNulosDiasVenezuela(getCriteria(f));
			//cargar adicionales 
			this.cargarAuxLista();
			//-- Peticiones
			f.setConsultaAsignarResult("");
			this.ruvAsignarNulosNumConVenezuelaList = lista;
			
			
			//Flag de Nro de Busqueda 10
			if (this.ruvAsignarNulosNumConVenezuelaList.isEmpty())
				this.addWarn("Aviso : ", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			}
		}
		catch (Exception e) {			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - obtenerAsignarNulosNumCon");
	}
	
	public void ejecutarAsignarNulosNumCon(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesVenezuelaAction - ejecutarAsignarNulosNumCon");
		try {
			// -- Variables
			MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			String[] numeroControl = new String[this.ruvAsignarNulosNumConVenezuelaList.size()];
			List lista = this.ruvAsignarNulosNumConVenezuelaList;
			for (int i = 0; i < lista.size(); i++) {
				HashMap mapa = (HashMap) this.ruvAsignarNulosNumConVenezuelaList.get(i);
				numeroControl[i] = mapa.get("numeroControl").toString();
			}
			f.setNumeroControl(numeroControl);

			// -- Logica
			service.updateNulosDiasVenezuela(lista, f.getNumeroControl(), f.getCodigoPais());

			// -- Peticiones
			f.setConsultaAsignarResult("");
			this.ruvAsignarNulosNumConVenezuelaList = new ArrayList();

			this.addInfo("", this.getResourceMessage("mantenimientoRUVDocumentosContablesVenezuelaForm.execute.exito"));
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesVenezuelaAction - ejecutarAsignarNulosNumCon");
	}
	
	private int validarCamposNulos(MantenimientoRUVDocumentosContablesVenezuelaForm f) 
	{
		if (this.indicadorPorDiasBool) f.setIndicadorPorDias("1");
		else f.setIndicadorPorDias("");

		if (this.indicadorPorRangoBool) f.setIndicadorPorRango("1");
		else f.setIndicadorPorRango("");

		if (f.getIndicadorPorDias().equals("")) 
		{
			if (StringUtils.isBlank(f.getDocumentoLegalInicial())) 
			{
				this.addWarn("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.msg.documentoLegalInicial"));
				return 1;
			} else 
			{
				if (StringUtils.isBlank(f.getDocumentoLegalFinal())) 
				{
					this.addWarn("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.msg.documentoLegalFinal"));
					return 1;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}
	
	public void cargarAccesos(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		this.accesoVenezuelaList = ajax.getAccesoByCanal(valor);
		this.ruvSubAccesoVenezuelaList = ajax.getSubaccesoByAcceso(this.accesoVenezuelaList[0].getValue());
	}

	public void cargarSubaccesos(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		this.ruvSubAccesoVenezuelaList = ajax.getSubaccesoByAcceso(valor);
	}

	public void cambiarChecksRango(ValueChangeEvent event)
	{
		Boolean valor =(Boolean) event.getNewValue();
		if(valor)
			this.indicadorPorRangoBool = false;
		else
			this.indicadorPorRangoBool = true;
	}
	
	public void cambiarChecksDias(ValueChangeEvent event)
	{
		Boolean valor =(Boolean) event.getNewValue();
		if(valor)
			this.indicadorPorDiasBool = false;
		else
			this.indicadorPorDiasBool = true;
	}
	
	//Permite recorrer las listas de Tipo Docuemnto Contable
	//Canal, Acceso, Subacceso para obtener su descripcion
	public void cargarAuxLista(){
		MantenimientoRUVDocumentosContablesVenezuelaForm f = (MantenimientoRUVDocumentosContablesVenezuelaForm) this.formBusqueda;
		//Documento Contable
		for(int i=0;i<this.ruvTipoDocumentoContableVenezuelaList.size();i++){
			Base temp = (Base)this.ruvTipoDocumentoContableVenezuelaList.get(i);
			if(StringUtils.equals(f.getCodigoTipoDocumentoContable(), temp.getCodigo())){				
				f.setDescripcionTipoDocumento(temp.getDescripcion());
				break;
			}				
		}
		
		//Canal
		for(int i=0;i<this.ruvAccesoVenezuelaList.size();i++){
			Base temp = (Base)this.ruvAccesoVenezuelaList.get(i);
			if(StringUtils.equals(f.getCodigoCanal(), temp.getCodigo())){				
				f.setDescripcionCanal(temp.getDescripcion());
				break;
			}				
		}
		
		//Acceso
		for(int i=0;i<this.accesoVenezuelaList.length;i++){
			LabelValue valor=(LabelValue)this.accesoVenezuelaList[i];			
			if(StringUtils.equals(f.getCodigoAcceso(),valor.getValue())){				
				f.setDescripcionAcceso(valor.getLabel());
				break;
			}				
		}
		
		//Subacceso
		
		for(int i=0;i<this.ruvSubAccesoVenezuelaList.length;i++){
			LabelValue valor=(LabelValue)this.ruvSubAccesoVenezuelaList[i];			
			if(StringUtils.equals(f.getCodigoSubacceso(),valor.getValue())){				
				f.setDescripcionSubacceso(valor.getLabel());
				break;
			}				
		}		
		
	}

	public List getRuvTipoDocumentoContableVenezuelaList() {
		return ruvTipoDocumentoContableVenezuelaList;
	}

	public void setRuvTipoDocumentoContableVenezuelaList(
			List ruvTipoDocumentoContableVenezuelaList) {
		this.ruvTipoDocumentoContableVenezuelaList = ruvTipoDocumentoContableVenezuelaList;
	}

	public List getRuvAccesoVenezuelaList() {
		return ruvAccesoVenezuelaList;
	}

	public void setRuvAccesoVenezuelaList(List ruvAccesoVenezuelaList) {
		this.ruvAccesoVenezuelaList = ruvAccesoVenezuelaList;
	}

	public LabelValue[] getRuvSubAccesoVenezuelaList() {
		return ruvSubAccesoVenezuelaList;
	}

	public void setRuvSubAccesoVenezuelaList(LabelValue[] ruvSubAccesoVenezuelaList) {
		this.ruvSubAccesoVenezuelaList = ruvSubAccesoVenezuelaList;
	}

	public LabelValue[] getAccesoVenezuelaList() {
		return accesoVenezuelaList;
	}

	public void setAccesoVenezuelaList(LabelValue[] accesoVenezuelaList) {
		this.accesoVenezuelaList = accesoVenezuelaList;
	}

	public String getIndEliminarDocContVenezuela() {
		return indEliminarDocContVenezuela;
	}

	public void setIndEliminarDocContVenezuela(String indEliminarDocContVenezuela) {
		this.indEliminarDocContVenezuela = indEliminarDocContVenezuela;
	}

	public List getRuvSinImpresionVenezuelaList() {
		return ruvSinImpresionVenezuelaList;
	}

	public void setRuvSinImpresionVenezuelaList(List ruvSinImpresionVenezuelaList) {
		this.ruvSinImpresionVenezuelaList = ruvSinImpresionVenezuelaList;
	}

	public List getRuvDuplicadosVenezuelaList() {
		return ruvDuplicadosVenezuelaList;
	}

	public void setRuvDuplicadosVenezuelaList(List ruvDuplicadosVenezuelaList) {
		this.ruvDuplicadosVenezuelaList = ruvDuplicadosVenezuelaList;
	}

	public List getRuvSinAsignarVenezuelaList() {
		return ruvSinAsignarVenezuelaList;
	}

	public void setRuvSinAsignarVenezuelaList(List ruvSinAsignarVenezuelaList) {
		this.ruvSinAsignarVenezuelaList = ruvSinAsignarVenezuelaList;
	}

	public String getRuvCantidadNulosAsignarVenezuela() {
		return ruvCantidadNulosAsignarVenezuela;
	}

	public void setRuvCantidadNulosAsignarVenezuela(
			String ruvCantidadNulosAsignarVenezuela) {
		this.ruvCantidadNulosAsignarVenezuela = ruvCantidadNulosAsignarVenezuela;
	}

	public boolean isIndicadorPorDiasBool() {
		return indicadorPorDiasBool;
	}

	public void setIndicadorPorDiasBool(boolean indicadorPorDiasBool) {
		this.indicadorPorDiasBool = indicadorPorDiasBool;
	}

	public boolean isIndicadorPorRangoBool() {
		return indicadorPorRangoBool;
	}

	public void setIndicadorPorRangoBool(boolean indicadorPorRangoBool) {
		this.indicadorPorRangoBool = indicadorPorRangoBool;
	}

	public List getRuvDocLegalesVenezuelaList() {
		return ruvDocLegalesVenezuelaList;
	}

	public void setRuvDocLegalesVenezuelaList(List ruvDocLegalesVenezuelaList) {
		this.ruvDocLegalesVenezuelaList = ruvDocLegalesVenezuelaList;
	}

	public String getRuvCantidadFilasParamPaginaLegalVenezuela() {
		return ruvCantidadFilasParamPaginaLegalVenezuela;
	}

	public void setRuvCantidadFilasParamPaginaLegalVenezuela(
			String ruvCantidadFilasParamPaginaLegalVenezuela) {
		this.ruvCantidadFilasParamPaginaLegalVenezuela = ruvCantidadFilasParamPaginaLegalVenezuela;
	}

	public List getRuvDocInternosVenezuelaList() {
		return ruvDocInternosVenezuelaList;
	}

	public void setRuvDocInternosVenezuelaList(List ruvDocInternosVenezuelaList) {
		this.ruvDocInternosVenezuelaList = ruvDocInternosVenezuelaList;
	}

	public String getRuvCantidadFilasParamPaginaInternoVenezuela() {
		return ruvCantidadFilasParamPaginaInternoVenezuela;
	}

	public void setRuvCantidadFilasParamPaginaInternoVenezuela(
			String ruvCantidadFilasParamPaginaInternoVenezuela) {
		this.ruvCantidadFilasParamPaginaInternoVenezuela = ruvCantidadFilasParamPaginaInternoVenezuela;
	}

	public String getRuvCantidadFilasParamPaginaControlVenezuela() {
		return ruvCantidadFilasParamPaginaControlVenezuela;
	}

	public void setRuvCantidadFilasParamPaginaControlVenezuela(
			String ruvCantidadFilasParamPaginaControlVenezuela) {
		this.ruvCantidadFilasParamPaginaControlVenezuela = ruvCantidadFilasParamPaginaControlVenezuela;
	}

	public List getRuvNumControlVenezuelaList() {
		return ruvNumControlVenezuelaList;
	}

	public void setRuvNumControlVenezuelaList(List ruvNumControlVenezuelaList) {
		this.ruvNumControlVenezuelaList = ruvNumControlVenezuelaList;
	}

	public List getRuvNumConDuplicadosVenezuelaList() {
		return ruvNumConDuplicadosVenezuelaList;
	}

	public void setRuvNumConDuplicadosVenezuelaList(
			List ruvNumConDuplicadosVenezuelaList) {
		this.ruvNumConDuplicadosVenezuelaList = ruvNumConDuplicadosVenezuelaList;
	}

	public List getRuvSinAsignarNumConVenezuelaList() {
		return ruvSinAsignarNumConVenezuelaList;
	}

	public void setRuvSinAsignarNumConVenezuelaList(
			List ruvSinAsignarNumConVenezuelaList) {
		this.ruvSinAsignarNumConVenezuelaList = ruvSinAsignarNumConVenezuelaList;
	}

	public List getRuvAsignarNulosNumConVenezuelaList() {
		return ruvAsignarNulosNumConVenezuelaList;
	}

	public void setRuvAsignarNulosNumConVenezuelaList(
			List ruvAsignarNulosNumConVenezuelaList) {
		this.ruvAsignarNulosNumConVenezuelaList = ruvAsignarNulosNumConVenezuelaList;
	}

	public DataTableModel getDataModelSinImpresion() {
		return dataModelSinImpresion;
	}

	public void setDataModelSinImpresion(DataTableModel dataModelSinImpresion) {
		this.dataModelSinImpresion = dataModelSinImpresion;
	}

	public List getSelectedSinImpresion() {
		return selectedSinImpresion;
	}

	public void setSelectedSinImpresion(List selectedSinImpresion) {
		this.selectedSinImpresion = selectedSinImpresion;
	}	
}