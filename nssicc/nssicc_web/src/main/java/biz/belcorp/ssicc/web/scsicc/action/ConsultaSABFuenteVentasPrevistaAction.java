package biz.belcorp.ssicc.web.scsicc.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAErrorService;
import biz.belcorp.ssicc.service.FuenteVentasService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaSABFuenteVentasPrevistaForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMCalificacionComisionForm;


/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaSABFuenteVentasPrevistaAction extends BaseConsultaAbstractAction {

   private List siccSociedadList;
   private List siccAlmacenList;
   private List siccMarcaList;
   private List siccCanalList;
   private List siccRangoPeriodoList;
   private LabelValue[] siccRegionList;
   private LabelValue[] siccZonaList;
   private String[][] listaArray;
   private String[] cols;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		
		return new ConsultaSABFuenteVentasPrevistaForm();
	}

	@Override
	protected List setFindAttributes() throws Exception {
		//Limpiamos Data
		this.listaArray = null;
		this.cols = null;
		
		log.debug(" ------------>  ConsultaSABFuenteVentasPrevistaAction, search method");
		
		ConsultaSABFuenteVentasPrevistaForm consultaForm = (ConsultaSABFuenteVentasPrevistaForm) this.formBusqueda;
		
		// Obtenemos los beans b�sicos de la sesi�n
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Map params = BeanUtils.describe(consultaForm);
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", consultaForm.getCodigoPais());
		String tipoSeleccion = (String) params.get("tipoSeleccion");
		log.debug("-------->" + tipoSeleccion);
		
		// Se interactuas con la BD hasta obtener la lista con 7 objetos
		
		FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");
		// Obtenemmos el periodo Inicio y Fin
		String periodoInicio = service.getPeriodoInicio(consultaForm
				.getCodigoRangoPeriodo());
		String periodoFin = service.getPeriodoFin(consultaForm
				.getCodigoRangoPeriodo());
		String periodoMenor = consultaForm.getCodigoAnio() + periodoInicio;
		String periodoMayor = consultaForm.getCodigoAnio() + periodoFin;
		
		params.put("periodoMenor", periodoMenor);
		params.put("periodoMayor", periodoMayor);
		params.put("periodoInicio", periodoInicio);
		params.put("periodoFin", periodoFin);
		
		String codigoRegionElegido = consultaForm.getCodigoRegion();
		String codigoZonaElegida = consultaForm.getCodigoZona();
		
		/*session.setAttribute("codigoRegionElegido", codigoRegionElegido);
		session.setAttribute("codigoZonaElegida", codigoZonaElegida);
		session.setAttribute("tipoSeleccion", tipoSeleccion);*/
		
		// Realizamos la consulta
		// List listaFuenteVentas = service.getFuenteVentasByCriteria(params);
		ArrayList listaFuenteVentas = service
				.getConsultaFuenteVentasPrevistaByCriteria(params);
		
		log.debug("TAMANO DE LA LISTA DE FUENTE DE VENTAS "
				+ listaFuenteVentas.size());
		
		// Guardamos en sesi�n los criterios
		//session.setAttribute("criteria", params);
		
		if (listaFuenteVentas.size() < 7){
			return null;
		}
		
		/*if (listaFuenteVentas.size() < 7)
		
		
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.datos.fuentes.consulta"));
			saveMessages(request, messages);
			return mapping.findForward("view");
			String msg = this.getResourceMessage("errors.datos.fuentes.consulta");
			addInfo("", msg);
			return null;
		}*/
		
		consultaForm.listaACampos(listaFuenteVentas);
		
        List list = new ArrayList();
        int columns = 8;
        int rows = 28;
        String[][] grid = new String[columns][rows];
        String[] cols = new String[7];
		Field[] variables = consultaForm.getClass().getDeclaredFields();
	    String[] camposDefecto = {"c0","c1","c2","c3","c4","c5","c6","c7"};
	    int i = 0;
	
		for (Field field : variables) {
			 String campo = field.getName();
			
			 String nameMethod = "getC".concat(StringUtils.substring(campo, 1, campo.length()));
			 Method m = null;
			 String value = null;
			
			 if(this.containsArray(camposDefecto, nameMethod.toLowerCase())){
				 
				  m = consultaForm.getClass().getMethod(nameMethod);
				  value = (String) m.invoke(consultaForm);
				 
				  if (ArrayUtils.contains(camposDefecto, campo.toLowerCase())) {
						if(campo.equals(camposDefecto[0])){
					    	 
							//Cabeceras Filas
							 for (int j = 0; j < rows; j++) {
									
									String titles = getResourceMessage("consultaSABFuenteVentasPrevistaForm.f"+(j+1));
									grid[0][j] = titles;
									
								}
						 }else if(campo.equals(camposDefecto[1])){
							 cols[0] = value;
						 }else if(campo.equals(camposDefecto[2])){
							 cols[1] = value;
						 }else if(campo.equals(camposDefecto[3])){
							 cols[2] = value;
						 }else if(campo.equals(camposDefecto[4])){
							 cols[3] = value;
						 }else if(campo.equals(camposDefecto[5])){
							 cols[4] = value;
						 }else if(campo.equals(camposDefecto[6])){
							 cols[5] = value;
						 }else if(campo.equals(camposDefecto[7])){
							 cols[6] = value;
						 }
				  }else{
					  
						 if (i==rows) {
							  i=0;
						 }
						 //Detalle
					     if (nameMethod.toLowerCase().indexOf(camposDefecto[1]) > 0) {
							 
							 grid[1][i] = value;

						 }else if (nameMethod.toLowerCase().indexOf(camposDefecto[2]) > 0) {
							
							 grid[2][i] = value;
					
						 }else	if (nameMethod.toLowerCase().indexOf(camposDefecto[3]) > 0) {

							 grid[3][i] = value;
		
						 }else	if (nameMethod.toLowerCase().indexOf(camposDefecto[4]) > 0) {

							 grid[4][i] = value;
				
						 }else	if (nameMethod.toLowerCase().indexOf(camposDefecto[5]) > 0) {

							 grid[5][i] = value;

						 }else	if (nameMethod.toLowerCase().indexOf(camposDefecto[6]) > 0) {

							 grid[6][i] = value;
			
						 }else	if (nameMethod.toLowerCase().indexOf(camposDefecto[7]) > 0) {

							 grid[7][i] = value;

						 }	
					     
					   i++;
				  }
			 }

		}
		
		this.listaArray = grid;
		this.cols = cols;

		this.setMostrarListaBusqueda(false);
			
		return listaFuenteVentas;
	}

	public boolean containsArray(String[] cads,String searchValue){
	    for (String cad : cads) {
			 if (StringUtils.indexOf(searchValue,cad) >= 0) {
				return true;
			  }
		}
		return false;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug(" ------------>  ConsultaSABFuenteVentasPrevistaAction, view method");
		}
			
		ConsultaSABFuenteVentasPrevistaForm consultaForm = (ConsultaSABFuenteVentasPrevistaForm) this.formBusqueda;
		
		// Obtenemos los beans b�sicos de la sesi�n
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map params = BeanUtils.describe(consultaForm);
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		
		consultaForm.setCodigoPais(pais.getCodigo());
		
		// Cargamos los combos a utilizar
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccSociedadList = svc.getSociedadesByCodigoPais(pais.getCodigo());
		siccAlmacenList = svc.getAlmacenesByCodigoISOPais(params);
		siccMarcaList = svc.getMarcas();
		siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccRangoPeriodoList = svc.getRangosPeriodo();
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
        
		siccRegionList = ajax.getRegionesByPaisMarcaCanal( consultaForm.getCodigoPais(), 
				consultaForm.getCodigoMarca(), consultaForm.getCodigoCanal());

		siccZonaList = ajax.getZonasByPaisMarcaCanalRegion( consultaForm.getCodigoPais(), 
				consultaForm.getCodigoMarca(), consultaForm.getCodigoCanal(), this.siccRegionList[0].getValue());
	
		// searchForm.inicializar();
		// La busqueda solo la realizaremos en los sistemas activos
          this.setMostrarListaBusqueda(false);
	}
	
	/**
	 * Metodo para obtener Lista de Regiones por Marca
	 * 
	 * @param val
	 */
	public void loadRegionesByMarca(ValueChangeEvent val) {
		if(log.isDebugEnabled()){
			log.debug("loadRegionesByMarca...");
		}

		ConsultaSABFuenteVentasPrevistaForm consultaForm = (ConsultaSABFuenteVentasPrevistaForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String marca = (String) val.getNewValue();
		consultaForm.setCodigoMarca(marca);
		
		this.siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(consultaForm.getCodigoPais(), consultaForm.getCodigoMarca(), consultaForm.getCodigoCanal());
		if (StringUtils.isBlank(getSiccRegionList()[0].getValue())) {
			this.siccZonaList = null;
		}else{
			this.siccZonaList = ajaxService.getZonasByPaisCanalRegion(consultaForm.getCodigoPais(), consultaForm.getCodigoCanal(), this.siccRegionList[0].getValue());
		}
	}
	
	/**
	 * Metodo para obtener Lista de Regiones por Canal
	 * 
	 * @param val
	 */
	public void loadRegionesByCanal(ValueChangeEvent val) {
		if(log.isDebugEnabled()){
			log.debug("loadRegionesByCanal...");
		}

		ConsultaSABFuenteVentasPrevistaForm consultaForm = (ConsultaSABFuenteVentasPrevistaForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String canal = (String) val.getNewValue();
		consultaForm.setCodigoCanal(canal);
		
		this.siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(consultaForm.getCodigoPais(), consultaForm.getCodigoMarca(), consultaForm.getCodigoCanal());
		if (StringUtils.isBlank(getSiccRegionList()[0].getValue())) {
			this.siccZonaList = null;
		}else{
			this.siccZonaList = ajaxService.getZonasByPaisCanalRegion(consultaForm.getCodigoPais(), consultaForm.getCodigoCanal(), this.siccRegionList[0].getValue());
		}
	}
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas...");
		}
		
		ConsultaSABFuenteVentasPrevistaForm consultaForm = (ConsultaSABFuenteVentasPrevistaForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String valor = (String) val.getNewValue();
		consultaForm.setCodigoRegion(valor);
		
		this.siccZonaList = ajaxService.getZonasByPaisCanalRegion(consultaForm.getCodigoPais(), consultaForm.getCodigoCanal(), consultaForm.getCodigoRegion());
		if (StringUtils.isBlank(consultaForm.getCodigoRegion()))
			setSiccZonaList(null);
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
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

	public List getSiccRangoPeriodoList() {
		return siccRangoPeriodoList;
	}

	public void setSiccRangoPeriodoList(List siccRangoPeriodoList) {
		this.siccRangoPeriodoList = siccRangoPeriodoList;
	}
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}
	
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public String[][] getListaArray() {
		return listaArray;
	}

	public void setListaArray(String[][] listaArray) {
		this.listaArray = listaArray;
	}

	public String[] getCols() {
		return cols;
	}

	public void setCols(String[] cols) {
		this.cols = cols;
	}
}