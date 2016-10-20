package biz.belcorp.ssicc.web.scsicc.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaCOMResponsablesUASearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaZonaPOPUPSearchAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaCOMResponsablesUASearchAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = 1L;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private List siccTipoUnidadnList = new ArrayList();
	private List  listaTipoConsulta = new ArrayList();
	private List consultaCOMResponsablesUAList = new ArrayList();
	private List colDinUnionExt = new ArrayList();
	private List colDinUnion = new ArrayList();
	private boolean mostrarGrupo = false;
	private String columnasPanel = "2";
	
	private static final String POPUP_OCRZONA = "OCRZONA";
	private DataTableModel dtCOMResponsableUA;
	
	@ManagedProperty(value="#{busquedaZonaPOPUPSearchAction}")
	private BusquedaZonaPOPUPSearchAction busquedaZonaPOPUPSearchAction;
	
	private boolean mostrarPopupOCRZona = false;
	private boolean mostrarDaTable = false;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupOCRZona = false;
		this.busquedaZonaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_OCRZONA)){ 
			this.mostrarPopupOCRZona = true;			
		}
	}
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		ConsultaCOMResponsablesUASearchForm form = (ConsultaCOMResponsablesUASearchForm) this.formBusqueda;
		this.mostrarProcesoBatch = true;
		if (accion.equals(this.POPUP_OCRZONA)) {
			this.mostrarPopupOCRZona = false;
			this.busquedaZonaPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaZonaPOPUPSearchAction.isSeleccionoRegistro()) {
				GrupoZona zona = (GrupoZona)this.busquedaZonaPOPUPSearchAction.getBeanRegistroSeleccionado();
				String codigoZona = zona.getCodigoZona();
				form.setCodigoZona(codigoZona);
			}
		}
	}
	
	public void visualizarPopup(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("visualizarPopup");
		}
		ConsultaCOMResponsablesUASearchForm form = (ConsultaCOMResponsablesUASearchForm) this.formBusqueda;
		String valor = val.getNewValue().toString();
		if(StringUtils.isNotBlank(valor)
				&& StringUtils.isNotEmpty(valor)){
			log.debug(valor);
			if(StringUtils.equals(valor, "3") || StringUtils.equals(valor, "4")){
				this.columnasPanel = "3";
				this.mostrarGrupo = true;
			}else{
				this.columnasPanel = "2";
				this.mostrarGrupo = false;
			}
			form.setCodigoZona(null);
		}
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaCOMResponsablesUASearchForm f = new ConsultaCOMResponsablesUASearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		ConsultaCOMResponsablesUASearchForm form = (ConsultaCOMResponsablesUASearchForm) this.formBusqueda;
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		
		/* obteniendo valores */
		Map criterios = new HashMap();
		criterios.put("codigoPais", form.getCodigoPais());
		criterios.put("codigoMarca", form.getCodigoMarca());
		criterios.put("codigoCanal", form.getCodigoCanal());
		criterios.put("codigoPeriodo", form.getCodigoPeriodo());
		criterios.put("tipoUnidad", form.getTipoUnidad());
		criterios.put("codigoZona", form.getCodigoZona());
		criterios.put("tipoConsulta", form.getTipoConsulta());
		
		/* Columnas dinamicas */		
		List lista = obtenerColumnasDinamicas();

		List lista2 = obtenerColumnasDinamicasExt();
		
		boolean valor = false;
		valor = lista.addAll(lista2);
		this.colDinUnion = lista;
		//this.colDinUnionExt = lista2;
		if(valor){
			this.colDinUnionExt = new ArrayList();
			this.colDinUnionExt.addAll(lista);
		}
		
		/* Obteniendo Lista */
		List resultado = null;
		try {
			resultado = service.getConsultaResponsablesUA(criterios);
		} catch(Exception ex) {
			log.error("Error ConsultaResponsableUA : " + ex.getMessage());
			
			if(ex.getMessage()!=null) {
				int puntoInicial = ex.getMessage().indexOf("Unidad Administrativa tiene mas de 1 responsable");
				if(puntoInicial>0) {
					int puntoFinal = ex.getMessage().indexOf("ORA-06512");
					
					log.error("Error ConsultaResponsableUA : " + ex.getMessage().substring(puntoInicial, puntoFinal));
					this.addError("", ex.getMessage().substring(puntoInicial, puntoFinal));
					return null;
				}
			}
			
			throw ex;
			
		}
		
		String ncodigoSubgerencia = "-1";
		String ncodigoRegion = "-1";
		String ncodigoZona = "-1";
		int contador=0;
		for(int i=0; i < resultado.size(); i++ ) {
			Map params = (Map) resultado.get(i);
			String codigoSubgerencia = (String)params.get("codigoSubgerencia");
			String codigoRegion = (String)params.get("codigoRegion");
			String codigoZona = (String)params.get("codigoZona");
			String descripcionSubgerencia = (String)params.get("descripcionSubgerencia");
			String descripcionRegion = (String)params.get("descripcionRegion");
			String descripcionZona = (String)params.get("descripcionZona");
			
			params.put("descripcionSubgerenciaExcel", descripcionSubgerencia);
			params.put("descripcionRegionExcel", descripcionRegion);
			params.put("descripcionZonaExcel", descripcionZona);
			contador ++;
			if (contador == this.nroPaginacionDatatable + 1) {
				contador = 1;
			}
			else {	
		
			if (StringUtils.isNotBlank(codigoZona)) {
	            if (codigoZona.equals(ncodigoZona)) {
	            	codigoZona = "";
	            	descripcionZona = "";
				}
	            else {
	            	ncodigoZona = codigoZona;
	            }
			}
			
			if (StringUtils.isNotBlank(codigoRegion)) {
	            if (codigoRegion.equals(ncodigoRegion)) {
	            	codigoRegion = "";
	            	descripcionRegion = "";
				}
	            else {
	            	ncodigoRegion = codigoRegion;
	            }
			}
				
			if (StringUtils.isNotBlank(ncodigoSubgerencia)) {
	            if (codigoSubgerencia.equals(ncodigoSubgerencia)) {
	            	codigoSubgerencia = "";
	            	descripcionSubgerencia = "";
				}
	            else {
	            	ncodigoSubgerencia = codigoSubgerencia;
	            }
			}
		
			}
			params.put("descripcionSubgerencia", descripcionSubgerencia);
			params.put("descripcionRegion", descripcionRegion);
			params.put("descripcionZona", descripcionZona);
			resultado.set(i, params);
		}
		
		
		this.setConsultaCOMResponsablesUAList(resultado);
		this.dtCOMResponsableUA = new DataTableModel(resultado);
		
		if(resultado!=null && resultado.size()>0){
			this.mostrarDaTable=true;
		}else{
			this.mostrarDaTable=false;
		}
		
		return resultado;
	}	

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ConsultaCOMResponsablesUASearchForm f = (ConsultaCOMResponsablesUASearchForm) this.formBusqueda;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		
		this.setSiccMarcaList(service.getMarcas());
		this.setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		f.setCodigoPeriodo(codigoPeriodo);
		
		ArrayList resultadoUnidad = new ArrayList();
		Base[] mesUnidad = new Base[4];
		String[] presentacionesUnidad = { 
				"SubGerencia",
				"Region",
				"Zona",
				"Seccion"
				};
		for (int i = 0; i < mesUnidad.length ; i++) {
			mesUnidad[i] = new Base();
			mesUnidad[i].setCodigo("" + (i + 1));
			mesUnidad[i].setDescripcion(presentacionesUnidad[i]);
			resultadoUnidad.add(mesUnidad[i]);
		}
		this.setSiccTipoUnidadnList(resultadoUnidad);
				
		
		String descripcionTipoConsulta = this.getResourceMessage("consultaCOMResponsablesUASearchForm.tipoConsulta01");
		ArrayList resultadoTipoConsulta = new ArrayList();
		Base tipoConsulta = new Base();
		tipoConsulta.setCodigo("T");
		tipoConsulta.setDescripcion(descripcionTipoConsulta);
		resultadoTipoConsulta.add(tipoConsulta);
		
		tipoConsulta = new Base();
		descripcionTipoConsulta = this.getResourceMessage("consultaCOMResponsablesUASearchForm.tipoConsulta02");
		tipoConsulta.setCodigo("C");
		tipoConsulta.setDescripcion(descripcionTipoConsulta);
		resultadoTipoConsulta.add(tipoConsulta);
		
		tipoConsulta = new Base();
		descripcionTipoConsulta = this.getResourceMessage("consultaCOMResponsablesUASearchForm.tipoConsulta03");
		tipoConsulta.setCodigo("S");
		tipoConsulta.setDescripcion(descripcionTipoConsulta);
		resultadoTipoConsulta.add(tipoConsulta);
		
		
		this.setListaTipoConsulta(resultadoTipoConsulta);
		
		f.setTipoUnidad("1");
		List lista = obtenerColumnasDinamicas();
		log.debug("Todo Ok: Redireccionando");
		
		/* Borrando Lista */
		//this.setConsultaCOMResponsablesUAList(new ArrayList());
		this.setColumnasPanel("2");
	
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		this.mostrarListaBusqueda=false;
		this.mostrarDaTable=false;
	}
	
	/**
	 * Obtener columnas dinamicas.
	 *
	 * @param form the form
	 * @return the list
	 */
	private List obtenerColumnasDinamicas() {
		if(log.isDebugEnabled()){
			log.debug("obtenerColumnasDinamicas");
		}
		ConsultaCOMResponsablesUASearchForm f = (ConsultaCOMResponsablesUASearchForm) this.formBusqueda;
		Map columLista = new HashMap();
		List lista = new ArrayList();
		
		columLista.put("property", "descripcionSubgerencia");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".codigoSubgerencia"));
		columLista.put("width", "25");
		columLista.put("group","1");
		columLista.put("exportable","false");
		columLista.put("style","display:''");
		lista.add(columLista);
		
		columLista = new HashMap();
		columLista.put("property", "descripcionSubgerenciaExcel");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".codigoSubgerencia"));
		columLista.put("width", "25");
		columLista.put("group","1");
		columLista.put("exportable","true");
		columLista.put("style","display:none");
		lista.add(columLista);
		
		if (StringUtils.isNotBlank(f.getTipoUnidad())) {
			if (!Constants.TIPO_UA_SUBGERENCIA.equals(f.getTipoUnidad())) {
				columLista = new HashMap();
				columLista.put("property", "descripcionRegion");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionRegion"));
				columLista.put("group","2");
				columLista.put("width", "70");
				columLista.put("exportable","false");
				columLista.put("style","display:''");
				lista.add(columLista);
				
				columLista = new HashMap();
				columLista.put("property", "descripcionRegionExcel");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionRegion"));
				columLista.put("group","2");
				columLista.put("width", "70");
				columLista.put("exportable","true");
				columLista.put("style","display:none");
				lista.add(columLista);
				
				if (!Constants.TIPO_UA_REGION.equals(f.getTipoUnidad())) {
					columLista = new HashMap();
					columLista.put("property", "descripcionZona");
					columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionZona"));
					columLista.put("group","3");
					columLista.put("width", "70");
					columLista.put("exportable","false");
					columLista.put("style","display:''");
					lista.add(columLista);
					
					columLista = new HashMap();
					columLista.put("property", "descripcionZonaExcel");
					columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionZona"));
					columLista.put("group","3");
					columLista.put("width", "70");
					columLista.put("exportable","true");
					columLista.put("style","display:none");
					lista.add(columLista);
					
					
					if (!Constants.TIPO_UA_ZONA.equals(f.getTipoUnidad())) {
						columLista = new HashMap();
						columLista.put("property", "codigoSeccion");
						columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".codigoSeccion"));
						columLista.put("group","4");
						columLista.put("width", "30");
						columLista.put("exportable","true");
						columLista.put("style","display:''");
						lista.add(columLista);
					}	
				}
			}
			if (Constants.TIPO_UA_SUBGERENCIA.equals(f.getTipoUnidad())) {
				columLista = new HashMap();
				columLista.put("property", "descripcionRegion");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionRegion"));
				columLista.put("group","2");
				columLista.put("width", "70");
				columLista.put("exportable","false");
				columLista.put("style","display:''");
				lista.add(columLista);
				
				columLista = new HashMap();
				columLista.put("property", "descripcionRegionExcel");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionRegion"));
				columLista.put("group","2");
				columLista.put("width", "70");
				columLista.put("exportable","true");
				columLista.put("style","display:none");
				lista.add(columLista);
				
				columLista = new HashMap();
				columLista.put("property", "descripcionZona");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionZona"));
				columLista.put("group","3");
				columLista.put("width", "70");
				columLista.put("exportable","false");
				columLista.put("style","display:''");
				lista.add(columLista);
				
				columLista = new HashMap();
				columLista.put("property", "descripcionZonaExcel");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionZona"));
				columLista.put("group","3");
				columLista.put("width", "70");
				columLista.put("exportable","true");
				columLista.put("style","display:none");
				lista.add(columLista);
				
				columLista = new HashMap();
				columLista.put("property", "codigoSeccion");
				columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".codigoSeccion"));
				columLista.put("group","4");
				columLista.put("width", "30");
				columLista.put("exportable","false");
				columLista.put("style","display:''");
				lista.add(columLista);
	
			}
		}	
		
		columLista = new HashMap();
		columLista.put("property", "codigoCliente");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".codigoCliente"));
		columLista.put("width", "40");
		columLista.put("exportable","true");
		columLista.put("style","display:''");
		lista.add(columLista);
		
		columLista = new HashMap();
		columLista.put("property", "descripcionCliente");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".descripcionCliente"));
		columLista.put("width", "100");
		columLista.put("exportable","true");
		columLista.put("style","display:''");
		lista.add(columLista);
		
		return lista;
	}
	
	/**
	 * Obtener columnas dinamicas ext.
	 *
	 * @return the list
	 */
	private List obtenerColumnasDinamicasExt() {
		if(log.isDebugEnabled()){
			log.debug("obtenerColumnasDinamicas");
		}
		ConsultaCOMResponsablesUASearchForm f = (ConsultaCOMResponsablesUASearchForm) this.formBusqueda;
		Map columLista = new HashMap();
		List lista = new ArrayList();
		
		columLista.put("property", "numeroDocumentoIdentidad");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".numeroDocumentoIdentidad"));
		columLista.put("width", "50");
		columLista.put("exportable","true");
		columLista.put("style","display:''");
		lista.add(columLista);
		
		columLista = new HashMap();
		columLista.put("property", "direccion");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".direccion"));
		columLista.put("width", "100");
		columLista.put("exportable","true");
		columLista.put("style","display:''");
		lista.add(columLista);
		
		columLista = new HashMap();
		columLista.put("property", "fechaNacimiento");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".fechaNacimiento"));
		columLista.put("width", "50");
		columLista.put("exportable","true");
		columLista.put("style","display:''");
		lista.add(columLista);
		
		/* INI JJ VEN-SiCC-2012-0125 */
		//Agregamos telefono para Region, Zona y Seccion
		//if ((Constants.TIPO_UA_REGION.equals(f.getTipoUnidad())) || (Constants.TIPO_UA_ZONA.equals(f.getTipoUnidad())) || (Constants.TIPO_UA_SECCION.equals(f.getTipoUnidad())) ) {
			columLista = new HashMap();
			columLista.put("property", "telefonoCliente");
			columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".telefonoCliente"));
			columLista.put("width", "80");
			columLista.put("exportable","true");
			columLista.put("style","display:''");
			lista.add(columLista);
			
			columLista = new HashMap();
			columLista.put("property", "telefonoMovil");
			columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".telefonoMovil"));
			columLista.put("width", "60");
			columLista.put("exportable","true");
			columLista.put("style","display:''");
			lista.add(columLista);
			
			columLista = new HashMap();
			columLista.put("property", "telefonoTrabajo");
			columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".telefonoTrabajo"));
			columLista.put("width", "60");
			columLista.put("exportable","true");
			columLista.put("style","display:''");
			lista.add(columLista);

		//}
		/* FIN JJ VEN-SiCC-2012-0125 */
		
		//Agregamos Numero Activas Iniciales, Finales para las Secciones
		if (Constants.TIPO_UA_SECCION.equals(f.getTipoUnidad())) {
			columLista = new HashMap();
			columLista.put("property", "numeroActivasIniciales");
			columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".numeroActivasIniciales"));
			columLista.put("width", "30");
			columLista.put("exportable","true");
			columLista.put("style","display:''");
			lista.add(columLista);
			
			columLista = new HashMap();
			columLista.put("property", "numeroActivasFinales");
			columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".numeroActivasFinales"));
			columLista.put("width", "30");
			columLista.put("exportable","true");
			columLista.put("style","display:''");
			lista.add(columLista);
			
			columLista = new HashMap();
			columLista.put("property", "promedioActivasFinales");
			columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".promedioActivasFinales"));
			columLista.put("width", "30");
			columLista.put("exportable","true");
			columLista.put("style","display:''");
			lista.add(columLista);
		}
		
		columLista = new HashMap();
		columLista.put("property", "correoElectronico");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".correoElectronico"));
		columLista.put("width", "100");
		columLista.put("exportable","true");
		columLista.put("style","display:''");
		lista.add(columLista);
		

		columLista = new HashMap();
		columLista.put("property", "campoUa");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".campo_ua"));
		//columLista.put("width", "100");
		columLista.put("exportable","true");
		columLista.put("style","display:'none'");
		lista.add(columLista);
		
		columLista = new HashMap();
		columLista.put("property", "fecDesd");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".fec_desd"));
		//columLista.put("width", "100");
		columLista.put("exportable","true");
		columLista.put("style","display:'none'");
		lista.add(columLista);
		
		columLista = new HashMap();
		columLista.put("property", "perdOidPeriDesd");
		columLista.put("titleKey", this.getResourceMessage(Constants.CONSULTA_COM_RESPONSABLES_UA + ".perd_oid_peri_desd"));
		//columLista.put("width", "100");
		columLista.put("exportable","true");
		columLista.put("style","display:'none'");
		lista.add(columLista);
		
		return lista;
	}


	public List getConsultaCOMResponsablesUAList() {
		return consultaCOMResponsablesUAList;
	}

	public void setConsultaCOMResponsablesUAList(List consultaCOMResponsablesUAList) {
		this.consultaCOMResponsablesUAList = consultaCOMResponsablesUAList;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccTipoUnidadnList() {
		return siccTipoUnidadnList;
	}

	public void setSiccTipoUnidadnList(List siccTipoUnidadnList) {
		this.siccTipoUnidadnList = siccTipoUnidadnList;
	}

	public List getListaTipoConsulta() {
		return listaTipoConsulta;
	}

	public void setListaTipoConsulta(List listaTipoConsulta) {
		this.listaTipoConsulta = listaTipoConsulta;
	}	

	public BusquedaZonaPOPUPSearchAction getBusquedaZonaPOPUPSearchAction() {
		return busquedaZonaPOPUPSearchAction;
	}

	public void setBusquedaZonaPOPUPSearchAction(
			BusquedaZonaPOPUPSearchAction busquedaZonaPOPUPSearchAction) {
		this.busquedaZonaPOPUPSearchAction = busquedaZonaPOPUPSearchAction;
	}

	public boolean isMostrarPopupOCRZona() {
		return mostrarPopupOCRZona;
	}

	public void setMostrarPopupOCRZona(boolean mostrarPopupOCRZona) {
		this.mostrarPopupOCRZona = mostrarPopupOCRZona;
	}


	public boolean isMostrarGrupo() {
		return mostrarGrupo;
	}

	public void setMostrarGrupo(boolean mostrarGrupo) {
		this.mostrarGrupo = mostrarGrupo;
	}

	public String getColumnasPanel() {
		return columnasPanel;
	}

	public void setColumnasPanel(String columnasPanel) {
		this.columnasPanel = columnasPanel;
	}

	/**
	 * @return the colDinUnionExt
	 */
	public List getColDinUnionExt() {
		return colDinUnionExt;
	}

	/**
	 * @param colDinUnionExt the colDinUnionExt to set
	 */
	public void setColDinUnionExt(List colDinUnionExt) {
		this.colDinUnionExt = colDinUnionExt;
	}

	/**
	 * @return the colDinUnion
	 */
	public List getColDinUnion() {
		return colDinUnion;
	}

	/**
	 * @param colDinUnion the colDinUnion to set
	 */
	public void setColDinUnion(List colDinUnion) {
		this.colDinUnion = colDinUnion;
	}

	/**
	 * @return the dtCOMResponsableUA
	 */
	public DataTableModel getDtCOMResponsableUA() {
		return dtCOMResponsableUA;
	}

	/**
	 * @param dtCOMResponsableUA the dtCOMResponsableUA to set
	 */
	public void setDtCOMResponsableUA(DataTableModel dtCOMResponsableUA) {
		this.dtCOMResponsableUA = dtCOMResponsableUA;
	}

	/**
	 * @return the mostrarDaTable
	 */
	public boolean isMostrarDaTable() {
		return mostrarDaTable;
	}

	/**
	 * @param mostrarDaTable the mostrarDaTable to set
	 */
	public void setMostrarDaTable(boolean mostrarDaTable) {
		this.mostrarDaTable = mostrarDaTable;
	}	
	
	
}
