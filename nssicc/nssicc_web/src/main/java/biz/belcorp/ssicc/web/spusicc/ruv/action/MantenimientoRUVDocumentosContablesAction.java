package biz.belcorp.ssicc.web.spusicc.ruv.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
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
import biz.belcorp.ssicc.web.spusicc.ruv.form.MantenimientoRUVDocumentosContablesForm;
import biz.belcorp.ssicc.web.spusicc.ruv.form.ReporteRUVDocumentosContablesMantenimientoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRUVDocumentosContablesAction extends BaseMantenimientoSearchAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3917556927000862658L;

	private List ruvTipoDocumentoContableList;
	private List ruvAccesoList;
	private LabelValue[] ruvSubAccesoList;

	private String indEliminarDocCont;

	private LabelValue[] accesoList;

	/* se utiliza en los xhtml insertados */
	private List ruvDocLegalesList;
	private DataTableModel ruvDocLegalesListModel;
	private List ruvDocInternosList;
	private DataTableModel ruvDocInternosListModel;

	private List ruvAsignarNulosNumConList;
	private List ruvSinImpresionList;
	private List ruvDuplicadosList;
	private List ruvSinAsignarList;
	private String ruvCantidadNulosAsignar;
	private String ruvCantidadFilasParamPaginaLegal;
	private String ruvSeccionBusquedaVar;
	private String ruvCantidadFilasParamPaginaInterno;
	private String ruvCantidadFilasParamPaginaControl;
	private List ruvNumControlList;
	private List ruvNumConDuplicadosList;
	private List ruvSinAsignarNumConList;
	
	/* tabla Sin impresion, sin unidades, sin total*/
	private DataTableModel dataModelSinImpresion;
	private List selectedSinImpresion;	
	/**/
	
	private boolean indicadorPorDiasBool;
	private boolean indicadorPorRangoBool;
	private boolean mostrarTodosSubAcceso;
	private boolean mostrarSerieNumerico;
	
	@ManagedProperty(value = "#{reporteRUVDocumentosContablesMantenimientoAction}")
	private ReporteRUVDocumentosContablesMantenimientoAction reporteRUVDocumentosContablesMantenimientoAction;
	

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
		MantenimientoRUVDocumentosContablesForm formSearch = new MantenimientoRUVDocumentosContablesForm();
		return formSearch;
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
		this.mostrarTodosSubAcceso = true;
		this.mostrarSerieNumerico = true;
		
		MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
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

		log.info("Entro a MantenimientoRUVDocumentosContablesAction - setViewAttributes");

		// -- Variables ------------------------------------------
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String codigoSistema = this.getParametrosPantalla().get("codigoSistema");
		String codigoParametro = this.getParametrosPantalla().get("codigoParametro");
		AjaxService ajax = (AjaxService) getBean("ajaxService");

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


		// -- Peticiones -----------------------------------------

		// Setea el combo de Tipos de documento contable
		this.ruvTipoDocumentoContableList = service.getTipoDocumentoContable();
		
		//Cuando indicadorActDocCon == "s", se setea nuevamente el combo de Tipos de documento contable
		if(f.getIndicadorActDocCon().equals("s")){
			List auxLista = new ArrayList();
			
			for (int i = 0; i < this.ruvTipoDocumentoContableList.size(); i++) {
				Base temp = (Base)this.ruvTipoDocumentoContableList.get(i);
				if(temp.getCodigo().equals("1") || temp.getCodigo().equals("32"))
					auxLista.add(temp);			
			}	
			
			this.ruvTipoDocumentoContableList = new ArrayList();
			this.ruvTipoDocumentoContableList = auxLista;
		}
		
		// Setea el combo de Canales
		this.ruvAccesoList = siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		// Setea el combo de Acceso
		if (f.getIndicadorActDocCon().equalsIgnoreCase("S")) {
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			
		}
		else {
			Base aux = (Base) this.ruvAccesoList.get(0);
			f.setCodigoCanal(aux.getCodigo());
		}
		this.accesoList = ajax.getAccesoByCanal(f.getCodigoCanal());
		
		// Setea el combo de SubAcceso
		if (f.getIndicadorActDocCon().equalsIgnoreCase("S")) {
			f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		}
		else {
			String codigoAcceso = this.accesoList[0].getValue();
			f.setCodigoAcceso(codigoAcceso);
		}
		this.ruvSubAccesoList = ajax.getSubaccesoByAcceso(f.getCodigoAcceso());
		if (f.getIndicadorActDocCon().equalsIgnoreCase("S")) {
			f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_000);
		}
		else {
			String codigoSubAcceso = this.ruvSubAccesoList[0].getValue();
			f.setCodigoSubacceso(codigoSubAcceso);
		}
				
		this.indEliminarDocCont = service.getIndicadorRUVEliminarDocumentoContable(pais.getCodigo());
		log.info("Salio a MantenimientoRUVDocumentosContablesAction - setViewAttributes");
		
		
	}

	/**
	 * Construye el Map con los parametros para las consultas
	 * 
	 * @param f
	 * @return
	 */
	private Map getCriteria(MantenimientoRUVDocumentosContablesForm f) {
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

	public void cargarAccesos(ValueChangeEvent event) {
		this.mostrarSerieNumerico = true;
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if (valor.equals("RT")) 
			this.mostrarSerieNumerico = false;
		
		this.accesoList = ajax.getAccesoByCanal(valor);		
		this.ruvSubAccesoList = ajax.getSubaccesoByAcceso(this.accesoList[0].getValue());
	}

	public void cargarSubaccesos(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		this.ruvSubAccesoList = ajax.getSubaccesoByAcceso(valor);
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
	
	
	/**
	 * @param event
	 */
	public void ejecutarReporteInternos(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ejecutarReporteInternos' method");
		}
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
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
			
			String indicadorNegativo = Constants.NUMERO_CERO;
			
			if(StringUtils.equalsIgnoreCase(f.getCodigoTipoDocumentoContable(), "31") || StringUtils.equalsIgnoreCase(f.getCodigoTipoDocumentoContable(), "32") || StringUtils.equalsIgnoreCase(f.getCodigoTipoDocumentoContable(), "33"))
			{
				indicadorNegativo= Constants.NUMERO_UNO;
			}
			criteria.put("indicadorNegativo", indicadorNegativo);
			
			
			if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);
				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);
		    }
			
			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoInicial())) {
				criteria.put("numeroDocumentoInicial", f.getNumeroDocumentoInternoInicial());
			}

			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoFinal())) {
				criteria.put("numeroDocumentoFinal", f.getNumeroDocumentoInternoFinal());
			}

						
			ReporteRUVDocumentosContablesMantenimientoForm reporteForm = new ReporteRUVDocumentosContablesMantenimientoForm();
		    BeanUtils.copyProperties(reporteForm, criteria);
		    reporteForm.setFormatoExportacion("XLS");
		    reporteForm.setPais(this.mPantallaPrincipalBean.getCurrentCountry());
			this.reporteRUVDocumentosContablesMantenimientoAction.setFormReporte(reporteForm);
			this.reporteRUVDocumentosContablesMantenimientoAction.setTipoReporte("I");
			this.reporteRUVDocumentosContablesMantenimientoAction.setFormatoExportacion("XLS");
			this.reporteRUVDocumentosContablesMantenimientoAction.executeReporte();
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * @param event
	 */
	public void ejecutarReporteLegales(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ejecutarReporteLegales' method");
		}
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
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
			
			String indicadorNegativo = Constants.NUMERO_CERO;
			
			if(StringUtils.equalsIgnoreCase(f.getCodigoTipoDocumentoContable(), "31") || StringUtils.equalsIgnoreCase(f.getCodigoTipoDocumentoContable(), "32") || StringUtils.equalsIgnoreCase(f.getCodigoTipoDocumentoContable(), "33"))
			{
				indicadorNegativo= Constants.NUMERO_UNO;
			}
			criteria.put("indicadorNegativo", indicadorNegativo);
			
			
			if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);
				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);
		    }
			
			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoInicial())) {
				criteria.put("numeroDocumentoInicial", f.getNumeroDocumentoInternoInicial());
			}

			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoFinal())) {
				criteria.put("numeroDocumentoFinal", f.getNumeroDocumentoInternoFinal());
			}

						
			ReporteRUVDocumentosContablesMantenimientoForm reporteForm = new ReporteRUVDocumentosContablesMantenimientoForm();
		    BeanUtils.copyProperties(reporteForm, criteria);
		    reporteForm.setFormatoExportacion("XLS");
		    reporteForm.setPais(this.mPantallaPrincipalBean.getCurrentCountry());
			this.reporteRUVDocumentosContablesMantenimientoAction.setFormReporte(reporteForm);
			this.reporteRUVDocumentosContablesMantenimientoAction.setTipoReporte("L");
			this.reporteRUVDocumentosContablesMantenimientoAction.setFormatoExportacion("XLS");
			this.reporteRUVDocumentosContablesMantenimientoAction.executeReporte();
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	public void buscar(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesAction - setFindAttributes");

		// -- Variables
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String tipoAreaFind = externalContext.getRequestParameterMap().get("parametroAccion");

			List lista = new ArrayList();
			String total = new String();
			
			if (tipoAreaFind.equals("docleg")) 
			{
				// -- Logica
				lista = service.getLimitesDocumentosLegales(getCriteria(f));
				total = service.getTotalDocumentosLegales(getCriteria(f));

				// -- Setear resultado
				Map map = (Map) lista.get(0);
				f.setMinimo(map.get("minimo") != null ? map.get("minimo").toString() : Constants.NUMERO_CERO);
				f.setMaximo(map.get("maximo") != null ? map.get("maximo").toString() : Constants.NUMERO_CERO);
				f.setDiferenciaMaxMin(map.get("diferencia") != null ? map.get("diferencia").toString(): Constants.NUMERO_CERO);
				f.setTotalRegistros(total);

			} else 
			{
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
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		log.info("Salio a MantenimientoRUVDocumentosContablesAction - setFindAttributes");
		// return lista;
	}

	/*este esel metodo setDeleteAttributes del SSICC*/
	public void deleteSinImpresion(ActionEvent event) 
	{
		MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		try {
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
						this.addInfo("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.deleted"));
					} else {
						this.addError("", "Seleccione elementos de la Tabla");
					}
				}

				// Eliminar todos
				if (f.getAccion().equals(Constants.NUMERO_UNO)) {
					List impresionList = ruvSinImpresionList;
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

				this.ruvSinImpresionList = service.getSinImpresionSinUnidades(getCriteria(f));
				this.dataModelSinImpresion = new DataTableModel(this.ruvSinImpresionList);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void searchImpresion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchImpresion' method");
		}
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

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
				
				if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
					String descripcionSubAcceso = service .getDescripcionSubAcceso(criteria);
					criteria.put("descripcionSubAcceso", descripcionSubAcceso);
	
					String oidSubAcceso = service.getOidSegSubAcceso(criteria);
					criteria.put("oidSubAcceso", oidSubAcceso);
				}	
				this.ruvSinImpresionList = service .getSinImpresionSinUnidades(criteria);
				this.dataModelSinImpresion = new DataTableModel(this.ruvSinImpresionList);

				if (this.ruvSinImpresionList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void searchDuplicados(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchDuplicados' method");
		}
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
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
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
				
				if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
					String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
					criteria.put("descripcionSubAcceso", descripcionSubAcceso);
	
					String oidSubAcceso = service.getOidSegSubAcceso(criteria);
					criteria.put("oidSubAcceso", oidSubAcceso);
				}
				this.ruvDuplicadosList = service.executeGenerarDataDocumentosLegalDuplicado(criteria);

				if (this.ruvDuplicadosList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
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
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

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
				
				if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
					String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
					criteria.put("descripcionSubAcceso", descripcionSubAcceso);
	
					String oidSubAcceso = service.getOidSegSubAcceso(criteria);
					criteria.put("oidSubAcceso", oidSubAcceso);
				}
				this.ruvSinAsignarList = service.getSinAsignarNumeroDocumento(criteria);

				if (this.ruvSinAsignarList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
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
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
			String cantidad="";

			
				String aux = validarCamposNulos(f);
				if(StringUtils.isNotBlank(aux)){
					this.addWarn("Warn: ", aux);
					return;
				}				
				if (StringUtils.equals(f.getIndicadorPorDias(), "1")) 
					cantidad = service.getAsignarNulosPorDias(getCriteria(f));
				else
					cantidad = service.getAsignarNulosPorRango(getCriteria(f));
				
				String cad1 = this
							.getResourceMessage("mantenimientoRUVDocumentosContablesForm.documentoLegal.asignarNulos.cadena1");
				String cad2 = this
							.getResourceMessage("mantenimientoRUVDocumentosContablesForm.documentoLegal.asignarNulos.cadena2");

				f.setConsultaAsignarResult(cad1 + " " + cantidad + " " + cad2);
				// Flag de Nro de Busqueda 9
				this.ruvCantidadNulosAsignar = cantidad;
				if (this.ruvCantidadNulosAsignar.equals("0"))
					this.addWarn("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
				
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void asignarNulos(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'asignarNulos' method");
		}
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			if (StringUtils.isBlank(f.getSerie())) 
				this.addWarn("Advertencia: ", "'Serie' es un campo requerido");
			else 
			{
				String aux = validarCamposNulos(f);
				if(StringUtils.isNotBlank(aux)){
					this.addWarn("Warn: ", aux);
					return;
				}			
				
					if (StringUtils.equals(f.getIndicadorPorDias(), "1")) 
						service.executeAsignarNulosPorDias(getCriteria(f));
					 else 
						service.executeAsignarNulosPorRango(getCriteria(f));
					
					f.setConsultaAsignarResult("");
					this.addInfo("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.execute.exito"));
				
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void searchDocLegal(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchDocLegal' method");
		}
		this.ruvDocLegalesList =new ArrayList();
		this.ruvDocLegalesListModel = new DataTableModel(this.ruvDocLegalesList);
		
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
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
			criteria.put("entidadSubAcceso",
					Constants.RUV_ENTIDAD_SEG_SUBAC);
			
			if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);
				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);
				
				log.debug("oidAcceso :" + oidAcceso);
				log.debug("codigoSubAcceso :" + f.getCodigoSubacceso());
				log.debug("oidSubAcceso :" + oidSubAcceso);
			}
			

			if (StringUtils.isNotBlank(f.getNumeroDocumentoLegalInicial())) {
				criteria.put("numeroDocumentoInicial", f.getNumeroDocumentoLegalInicial());
			}

			if (StringUtils.isNotBlank(f.getNumeroDocumentoLegalFinal())) {
				criteria.put("numeroDocumentoFinal", f.getNumeroDocumentoLegalFinal());
			}

			if (StringUtils.isNotBlank(f.getNumeroFilasLegal())) {
				if (f.getNumeroFilasLegal().equals("0")) {
					this.ruvCantidadFilasParamPaginaLegal = "10";
				} else {
					this.ruvCantidadFilasParamPaginaLegal = f.getNumeroFilasLegal();
				}
			} else {
				this.ruvCantidadFilasParamPaginaLegal = "10";
			}

			this.ruvDocLegalesList = service.getDocContablesLegales(criteria);
			this.ruvDocLegalesListModel = new DataTableModel(this.ruvDocLegalesList);
			
			f.setNumeroDocumentoLegalInicial(f.getNumeroDocumentoLegalInicial());
			f.setNumeroDocumentoLegalFinal(f.getNumeroDocumentoLegalFinal());
			f.setNumeroFilasLegal(f.getNumeroFilasLegal());

			if(ruvDocLegalesList.isEmpty())
				this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void searchDocInterno(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchDocLegInt' method");
		}
		this.ruvDocInternosList = new ArrayList();
		try {
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
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

			String descripcionAcceso = service
					.getDescripcionAcceso(criteria);
			criteria.put("descripcionAcceso", descripcionAcceso);

			String oidAcceso = service.getOidSegAcceso(criteria);
			criteria.put("oidAcceso", oidAcceso);
			criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
			criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
			
			
			if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
				String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
				criteria.put("descripcionSubAcceso", descripcionSubAcceso);
				String oidSubAcceso = service.getOidSegSubAcceso(criteria);
				criteria.put("oidSubAcceso", oidSubAcceso);
				
				log.debug("oidAcceso :" + oidAcceso);
				log.debug("codigoSubAcceso :" + f.getCodigoSubacceso());
				log.debug("oidSubAcceso :" + oidSubAcceso);
			}
			
			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoInicial())) {
				criteria.put("numeroDocumentoInicial", f.getNumeroDocumentoInternoInicial());
			}

			if (StringUtils.isNotBlank(f.getNumeroDocumentoInternoFinal())) {
				criteria.put("numeroDocumentoFinal", f.getNumeroDocumentoInternoFinal());
			}

			if (StringUtils.isNotBlank(f.getNumeroFilasInterno())) {
				if (f.getNumeroFilasInterno().equals("0")) {
					this.ruvCantidadFilasParamPaginaInterno = "10";
				} else {
					this.ruvCantidadFilasParamPaginaInterno = f.getNumeroFilasInterno();
				}
			} else {
				this.ruvCantidadFilasParamPaginaInterno = "10";
			}

			this.ruvDocInternosList = service.getDocContablesInternos(criteria);
			f.setNumeroDocumentoInternoInicial(f.getNumeroDocumentoInternoInicial());
			f.setNumeroDocumentoInternoFinal(f.getNumeroDocumentoInternoFinal());
			f.setNumeroFilasInterno(f.getNumeroFilasInterno());

			// Flag de Nro de Busqueda 2
			if(this.ruvDocInternosList.isEmpty())
				this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void deleteDocuLegalesLimites(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDocuLegalesLimites' method");
		}

		MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;

		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

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

	public void deleteDocuLegales(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDocuLegales' method");
		}

		MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

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

	public void deleteDocuInternos(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDocuInternos' method");
		}

		MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;

		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

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

	public void searchNumControl(ActionEvent event) {
		log.info("Entro a MantenimientoRUVDocumentosContablesAction - searchNumControl");

		try {
			// -- Variables ------------------------------------------
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			
				// -- Crear pojo -----------------------------------------
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

				if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
					String descripcionSubAcceso = service
							.getDescripcionSubAcceso(criteria);
					criteria.put("descripcionSubAcceso", descripcionSubAcceso);
	
					String oidSubAcceso = service.getOidSegSubAcceso(criteria);
					criteria.put("oidSubAcceso", oidSubAcceso);
				}
				if (StringUtils.isNotBlank(f.getNumeroControlInicial())) {
					criteria.put("numeroControlInicial",
							f.getNumeroControlInicial());
				}
				if (StringUtils.isNotBlank(f.getNumeroControlFinal())) {
					criteria.put("numeroControlFinal",
							f.getNumeroControlFinal());
				}

				if (StringUtils.isNotBlank(f.getNumeroFilasControl())) {
					if (f.getNumeroFilasInterno().equals("0")) {
						this.ruvCantidadFilasParamPaginaControl = "10";
					} else {
						this.ruvCantidadFilasParamPaginaControl = f.getNumeroFilasControl();
					}
				} else {
					this.ruvCantidadFilasParamPaginaControl = "10";
				}

				// -- Peticiones
				this.ruvNumControlList = service.getNumeroControlDocLeg(criteria);
				f.setNumeroControlInicial(f.getNumeroControlInicial());
				f.setNumeroControlFinal(f.getNumeroControlFinal());
				f.setNumeroFilasInterno(f.getNumeroFilasInterno());

				// Flag de Nro de Busqueda 3
				if(ruvNumControlList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesAction - searchNumControl");
	}

	public void searchNumConDuplicados(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesAction - searchNumConDuplicados");

		try {
			// -- Variables ------------------------------------------
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			
				// -- Crear pojo -----------------------------------------
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
				
				if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
					String descripcionSubAcceso = service
							.getDescripcionSubAcceso(criteria);
					criteria.put("descripcionSubAcceso", descripcionSubAcceso);
	
					String oidSubAcceso = service.getOidSegSubAcceso(criteria);
					criteria.put("oidSubAcceso", oidSubAcceso);
				}

				// -- Peticiones
				this.ruvNumConDuplicadosList = service
						.executeGenerarDataNumeroControlDuplicado(criteria);

				if (this.ruvNumConDuplicadosList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesAction - searchNumConDuplicados");
	}

	public void searchNumConSinAsignar(ActionEvent event) {
		log.info("Entro a MantenimientoRUVDocumentosContablesAction - searchNumConSinAsignar");

		try {
			// -- Variables ------------------------------------------
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			
				// -- Crear Pojo -----------------------------------------

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

				String descripcionAcceso = service.getDescripcionAcceso(criteria);
				criteria.put("descripcionAcceso", descripcionAcceso);

				String oidAcceso = service.getOidSegAcceso(criteria);
				criteria.put("oidAcceso", oidAcceso);
				criteria.put("codigoSubAcceso", f.getCodigoSubacceso());
				criteria.put("entidadSubAcceso", Constants.RUV_ENTIDAD_SEG_SUBAC);
				
				if (StringUtils.isNotBlank(f.getCodigoSubacceso())) {
					String descripcionSubAcceso = service.getDescripcionSubAcceso(criteria);
					criteria.put("descripcionSubAcceso", descripcionSubAcceso);
	
					String oidSubAcceso = service.getOidSegSubAcceso(criteria);
					criteria.put("oidSubAcceso", oidSubAcceso);
				}
				this.ruvSinAsignarNumConList = service.getSinAsignarNumeroControl(criteria);

				if (this.ruvSinAsignarNumConList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesAction - searchNumConSinAsignar");
	}

	public void obtenerAsignarNulosNumCon(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesAction - obtenerAsignarNulosNumCon");

		try {
			// -- Variables
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			
				// -- Logica
				List lista = service.getAsignarNulosDiasPais(getCriteria(f));

				// -- Peticiones
				f.setConsultaAsignarResult("");
				this.ruvAsignarNulosNumConList = lista;

				if (this.ruvAsignarNulosNumConList.isEmpty())
					this.addError("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesAction - obtenerAsignarNulosNumCon");
	}

	public void ejecutarAsignarNulosNumCon(ActionEvent event) 
	{
		log.info("Entro a MantenimientoRUVDocumentosContablesAction - ejecutarAsignarNulosNumCon");
		try {
			// -- Variables
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");

			
				List lista = this.ruvAsignarNulosNumConList;

				// -- Logica
				service.updateNulosDiasPais(lista, f.getNumeroControl(), f.getCodigoPais());

				// -- Peticiones
				f.setConsultaAsignarResult("");
				this.addInfo("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.execute.exito"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		log.info("Salio a MantenimientoRUVDocumentosContablesAction - ejecutarAsignarNulosNumCon");
	}

	
	/**
	 * @param valor
	 */
	public void loadFechas() {
		if (log.isDebugEnabled()) {
			log.debug("loadFechas");
		}
		try {
			this.mostrarTodosSubAcceso = false;
			MantenimientoRUVDocumentosContablesForm f = (MantenimientoRUVDocumentosContablesForm) this.formBusqueda;
			String fechaDesde = DateUtil.convertDateToString(f.getFechaDesdeDate());
			String fechaHasta = DateUtil.convertDateToString(f.getFechaHastaDate());
			int resultado = DateUtil.diferenciaFechas(fechaDesde, fechaHasta, 1);
			if (resultado<=30) 
				this.mostrarTodosSubAcceso = true;
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	
	
	private String validarCamposNulos(MantenimientoRUVDocumentosContablesForm f){
		if (this.indicadorPorDiasBool){
			f.setIndicadorPorDias("1");
			f.setIndicadorPorRango("");			
		}else{
			f.setIndicadorPorDias("");
			f.setIndicadorPorRango("1");	
		}		

		if (!this.indicadorPorDiasBool){
			if (StringUtils.isBlank(f.getDocumentoLegalInicial())) 
				return this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.msg.documentoLegalInicial");				
			
			if (StringUtils.isBlank(f.getDocumentoLegalFinal())) 				
				return this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.msg.documentoLegalFinal");	
				
		}
		return "";
	}
	
	public List getRuvTipoDocumentoContableList() {
		return ruvTipoDocumentoContableList;
	}

	public void setRuvTipoDocumentoContableList(
			List ruvTipoDocumentoContableList) {
		this.ruvTipoDocumentoContableList = ruvTipoDocumentoContableList;
	}

	public List getRuvAccesoList() {
		return ruvAccesoList;
	}

	public void setRuvAccesoList(List ruvAccesoList) {
		this.ruvAccesoList = ruvAccesoList;
	}

	public LabelValue[] getRuvSubAccesoList() {
		return ruvSubAccesoList;
	}

	public void setRuvSubAccesoList(LabelValue[] ruvSubAccesoList) {
		this.ruvSubAccesoList = ruvSubAccesoList;
	}

	public String getIndEliminarDocCont() {
		return indEliminarDocCont;
	}

	public void setIndEliminarDocCont(String indEliminarDocCont) {
		this.indEliminarDocCont = indEliminarDocCont;
	}

	public LabelValue[] getAccesoList() {
		return accesoList;
	}

	public void setAccesoList(LabelValue[] accesoList) {
		this.accesoList = accesoList;
	}

	public List getRuvDocLegalesList() {
		return ruvDocLegalesList;
	}

	public void setRuvDocLegalesList(List ruvDocLegalesList) {
		this.ruvDocLegalesList = ruvDocLegalesList;
	}

	public List getRuvDocInternosList() {
		return ruvDocInternosList;
	}

	public void setRuvDocInternosList(List ruvDocInternosList) {
		this.ruvDocInternosList = ruvDocInternosList;
	}

	public List getRuvAsignarNulosNumConList() {
		return ruvAsignarNulosNumConList;
	}

	public void setRuvAsignarNulosNumConList(List ruvAsignarNulosNumConList) {
		this.ruvAsignarNulosNumConList = ruvAsignarNulosNumConList;
	}

	public List getRuvSinImpresionList() {
		return ruvSinImpresionList;
	}

	public void setRuvSinImpresionList(List ruvSinImpresionList) {
		this.ruvSinImpresionList = ruvSinImpresionList;
	}

	public List getRuvDuplicadosList() {
		return ruvDuplicadosList;
	}

	public void setRuvDuplicadosList(List ruvDuplicadosList) {
		this.ruvDuplicadosList = ruvDuplicadosList;
	}

	public List getRuvSinAsignarList() {
		return ruvSinAsignarList;
	}

	public void setRuvSinAsignarList(List ruvSinAsignarList) {
		this.ruvSinAsignarList = ruvSinAsignarList;
	}

	public String getRuvCantidadNulosAsignar() {
		return ruvCantidadNulosAsignar;
	}

	public void setRuvCantidadNulosAsignar(String ruvCantidadNulosAsignar) {
		this.ruvCantidadNulosAsignar = ruvCantidadNulosAsignar;
	}

	public String getRuvCantidadFilasParamPaginaLegal() {
		return ruvCantidadFilasParamPaginaLegal;
	}

	public void setRuvCantidadFilasParamPaginaLegal(
			String ruvCantidadFilasParamPaginaLegal) {
		this.ruvCantidadFilasParamPaginaLegal = ruvCantidadFilasParamPaginaLegal;
	}

	public String getRuvSeccionBusquedaVar() {
		return ruvSeccionBusquedaVar;
	}

	public void setRuvSeccionBusquedaVar(String ruvSeccionBusquedaVar) {
		this.ruvSeccionBusquedaVar = ruvSeccionBusquedaVar;
	}

	public String getRuvCantidadFilasParamPaginaInterno() {
		return ruvCantidadFilasParamPaginaInterno;
	}

	public void setRuvCantidadFilasParamPaginaInterno(
			String ruvCantidadFilasParamPaginaInterno) {
		this.ruvCantidadFilasParamPaginaInterno = ruvCantidadFilasParamPaginaInterno;
	}

	public String getRuvCantidadFilasParamPaginaControl() {
		return ruvCantidadFilasParamPaginaControl;
	}

	public void setRuvCantidadFilasParamPaginaControl(
			String ruvCantidadFilasParamPaginaControl) {
		this.ruvCantidadFilasParamPaginaControl = ruvCantidadFilasParamPaginaControl;
	}

	public List getRuvNumControlList() {
		return ruvNumControlList;
	}

	public void setRuvNumControlList(List ruvNumControlList) {
		this.ruvNumControlList = ruvNumControlList;
	}

	public List getRuvNumConDuplicadosList() {
		return ruvNumConDuplicadosList;
	}

	public void setRuvNumConDuplicadosList(List ruvNumConDuplicadosList) {
		this.ruvNumConDuplicadosList = ruvNumConDuplicadosList;
	}

	public List getRuvSinAsignarNumConList() {
		return ruvSinAsignarNumConList;
	}

	public void setRuvSinAsignarNumConList(List ruvSinAsignarNumConList) {
		this.ruvSinAsignarNumConList = ruvSinAsignarNumConList;
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

	public DataTableModel getRuvDocLegalesListModel() {
		return ruvDocLegalesListModel;
	}

	public void setRuvDocLegalesListModel(DataTableModel ruvDocLegalesListModel) {
		this.ruvDocLegalesListModel = ruvDocLegalesListModel;
	}

	public DataTableModel getRuvDocInternosListModel() {
		return ruvDocInternosListModel;
	}

	public void setRuvDocInternosListModel(DataTableModel ruvDocInternosListModel) {
		this.ruvDocInternosListModel = ruvDocInternosListModel;
	}

	/**
	 * @return the mostrarTodosSubAcceso
	 */
	public boolean isMostrarTodosSubAcceso() {
		return mostrarTodosSubAcceso;
	}

	/**
	 * @param mostrarTodosSubAcceso the mostrarTodosSubAcceso to set
	 */
	public void setMostrarTodosSubAcceso(boolean mostrarTodosSubAcceso) {
		this.mostrarTodosSubAcceso = mostrarTodosSubAcceso;
	}

	/**
	 * @return the mostrarSerieNumerico
	 */
	public boolean isMostrarSerieNumerico() {
		return mostrarSerieNumerico;
	}

	/**
	 * @param mostrarSerieNumerico the mostrarSerieNumerico to set
	 */
	public void setMostrarSerieNumerico(boolean mostrarSerieNumerico) {
		this.mostrarSerieNumerico = mostrarSerieNumerico;
	}

	/**
	 * @return the reporteRUVDocumentosContablesMantenimientoAction
	 */
	public ReporteRUVDocumentosContablesMantenimientoAction getReporteRUVDocumentosContablesMantenimientoAction() {
		return reporteRUVDocumentosContablesMantenimientoAction;
	}

	/**
	 * @param reporteRUVDocumentosContablesMantenimientoAction the reporteRUVDocumentosContablesMantenimientoAction to set
	 */
	public void setReporteRUVDocumentosContablesMantenimientoAction(
			ReporteRUVDocumentosContablesMantenimientoAction reporteRUVDocumentosContablesMantenimientoAction) {
		this.reporteRUVDocumentosContablesMantenimientoAction = reporteRUVDocumentosContablesMantenimientoAction;
	}
	
	
}