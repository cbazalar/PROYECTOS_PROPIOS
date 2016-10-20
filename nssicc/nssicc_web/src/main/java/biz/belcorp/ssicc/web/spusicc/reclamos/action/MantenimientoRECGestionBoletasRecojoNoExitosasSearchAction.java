package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.lang.ArrayUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoNoExitosa;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoNoExitosasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoRECGestionBoletasRecojoNoExitosasSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2437257627024064340L;
	
	private List mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList;
	private List mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList;
	private DataTableModel tablaModel;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccSeccionList;
	private LabelValue[] siccTerritorioList;

	private String codigoIdiomaISO;

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
		MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm searchForm = new MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		log.debug("Seting find Attributes.");
		MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm f = (MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm) this.formBusqueda;		
		f.setFechaInicial(DateUtil.convertDateToString(f.getFechaInicialDate()));
		f.setFechaFinal(DateUtil.convertDateToString(f.getFechaFinalDate()));
		
		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("numeroBoleta", f.getNumeroBoleta());
		params.put("codigoCliente", f.getCodigoCliente());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fechaInicial", f.getFechaInicial());
		params.put("fechaFinal", f.getFechaFinal());
		params.put("regionList",(f.getRegionList() == null) ? new ArrayList(): Arrays.asList(f.getRegionList()));
		params.put("zonaList",(f.getZonaList() == null) ? new ArrayList(): Arrays.asList(f.getZonaList()));
		params.put("seccionList",(f.getSeccionList() == null) ? new ArrayList(): Arrays.asList(f.getSeccionList()));
		params.put("territorioList",(f.getTerritorioList() == null) ? new ArrayList(): Arrays.asList(f.getTerritorioList()));
		
		
		MantenimientoRECGestionBoletasRecojoNoExitosasService service = (MantenimientoRECGestionBoletasRecojoNoExitosasService) getBean("spusicc.mantenimientoRECGestionBoletasRecojoNoExitosasService");	
		List boletaRecojoNoExitosasList= service.getBoletasRecojoNOExitosasList(params);
		log.debug("List size :  " +boletaRecojoNoExitosasList.size());
		
		this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList = null;
		this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList = boletaRecojoNoExitosasList;
		this.tablaModel = new DataTableModel(this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList);
		
		return boletaRecojoNoExitosasList;
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
		log.debug("Seting Attributes.");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm f = (MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());

		//METODO RESET
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		f.setFechaInicial(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaInicialDate(DateUtil.convertStringToDate(f.getFechaInicial()));
		f.setFechaFinal(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaFinalDate(DateUtil.convertStringToDate(f.getFechaFinal()));
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        f.setNumeroBoleta(null);
        f.setCodigoCliente(null);
        f.setNumeroBoleta(null);
        f.setNumeroBoleta(null);        
    	f.setRegionList(null);
    	
		// FIN METODO RESET
		
		InterfazSiCCService interfazService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPeriodo(interfazService.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		loadCombos();
		this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList = null;		
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarListaBusqueda = false;
	}
	
	/**
	 * Seteando Combos
	 * 
	 * @throws Exception
	 */
	private void loadCombos() throws Exception 
	{
		log.debug("Loading Combos.");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
	}

	public void procesar(ActionEvent event) 
	{
		try {
			if (log.isDebugEnabled())
				log.debug("Entering 'procesar' method");
			MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm f = (MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm) this.formBusqueda;
			MantenimientoRECGestionBoletasRecojoNoExitosasService service = (MantenimientoRECGestionBoletasRecojoNoExitosasService) getBean("spusicc.mantenimientoRECGestionBoletasRecojoNoExitosasService");
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String tipo = externalContext.getRequestParameterMap().get("parametroAccion");
			
			if (tipo != null && tipo.equals("Todos")) 
			{
				for (int i = 0; i < this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList.size(); i++) 
				{
					Map criteria = new HashMap();
					BoletaRecojoNoExitosa boletaRecojoNoExitosa = (BoletaRecojoNoExitosa) this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList.get(i);
					criteria.put("boletaRecojoNoExitosa", boletaRecojoNoExitosa);
					service.executeProcesoBoletaRecojoNoExitosa(criteria);
				}
			} else {
				for (int i = 0; i < this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList.size(); i++) 
				{
					Map criteria = new HashMap();
					BoletaRecojoNoExitosa boletaRecojoNoExitosa = (BoletaRecojoNoExitosa) this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList.get(i);
					criteria.put("boletaRecojoNoExitosa", boletaRecojoNoExitosa);
					service.executeProcesoBoletaRecojoNoExitosa(criteria);
				}
			}

			Map params = new HashMap();
			params.put("codigoPais", f.getCodigoPais());
			params.put("numeroBoleta", f.getNumeroBoleta());
			params.put("codigoCliente", f.getCodigoCliente());
			params.put("codigoPeriodo", f.getCodigoPeriodo());
			params.put("fechaInicial", f.getFechaInicial());
			params.put("fechaFinal", f.getFechaFinal());
			params.put("regionList", (f.getRegionList() == null) ? new ArrayList() : Arrays.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList() : Arrays.asList(f.getZonaList()));
			params.put("seccionList", (f.getSeccionList() == null) ? new ArrayList() : Arrays.asList(f.getSeccionList()));
			params.put("territorioList", (f.getTerritorioList() == null) ? new ArrayList() : Arrays.asList(f.getTerritorioList()));

			List boletaRecojoNoExitosasList = service.getBoletasRecojoNOExitosasList(params);

			this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList = null;
			this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList = boletaRecojoNoExitosasList;
			this.tablaModel = new DataTableModel(this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList);

			this.addInfo("", this.getResourceMessage("mantenimientoRECGestionBoletasRecojoNoExitosasSearchForm.process"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String[] valor = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm f = (MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm) this.formBusqueda;
		
		if(valor.length > 0 && valor != null)
		{
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", valor,"T");
			this.siccSeccionList = null;
			this.siccTerritorioList = null;
		}else
		{
			this.siccZonaList = null;
			this.siccSeccionList = null;
			this.siccTerritorioList = null;			
		}		
	}
	
	public void loadSeccion(ValueChangeEvent event)
	{
		String[] valor = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm f = (MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm) this.formBusqueda;
		
		if (valor.length > 0 && valor != null) 
		{
			this.siccSeccionList = ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(pais.getCodigo(), "T", "VD", f.getRegionList(), valor, "T"); 
			this.siccTerritorioList = null;	
		} else {
			this.siccSeccionList = null;
			this.siccTerritorioList = null;		
		}		
	}
	
	public void loadTerritorios(ValueChangeEvent event)
	{
		String[] valor = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm f = (MantenimientoRECGestionBoletasRecojoNoExitosasSearchForm) this.formBusqueda;
		ArrayList valuesRegion = new ArrayList();
		ArrayList valuesZonas = new ArrayList();
		ArrayList valuesSeccion = new ArrayList();
		
		if (valor.length > 0 && valor != null) 
		{
			
			if(f.getRegionList() != null && f.getRegionList().length > 0) {
				for (String objeto : f.getRegionList()) {
					valuesRegion.add(objeto);
				}
			}
			
			if(f.getZonaList() != null && f.getZonaList().length > 0) {
				for (String objeto : f.getZonaList()) {
					valuesZonas.add(objeto);					
				}
			}
			
			for (String objeto : valor) {
				valuesSeccion.add(objeto);								
			}
			
			if(valuesRegion.size() > 0 && valuesZonas.size() > 0 && valuesSeccion.size() > 0)
				this.siccTerritorioList = ajax.getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(pais.getCodigo(), "T", "VD", 
					valuesRegion, valuesZonas, valuesSeccion,"T") ;
			
		} else {
			this.siccTerritorioList = null;
		}		
	}
	
	public List getMantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList() {
		return mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList;
	}

	public void setMantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList(
			List mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList) {
		this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList = mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList;
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

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	public List getMantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList() {
		return mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList;
	}

	public void setMantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList(
			List mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList) {
		this.mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList = mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraSeleccionadasList;
	}

	public DataTableModel getTablaModel() {
		return tablaModel;
	}

	public void setTablaModel(DataTableModel tablaModel) {
		this.tablaModel = tablaModel;
	}
	

}
