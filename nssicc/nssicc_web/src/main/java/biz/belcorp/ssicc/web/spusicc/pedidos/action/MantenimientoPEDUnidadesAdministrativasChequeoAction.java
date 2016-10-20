package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConsultoraChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDUnidadesAdministrativasChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDUnidadesAdministrativasChequeoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDUnidadesAdministrativasChequeoAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List pedTipoChequeoList;
	private List unidadesAdministrativasChequeoList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccTerritorioList = {};
	private LabelValue[] siccZonaList = {};

	/**
	 * @param Muestra
	 *            las zonas por regiones escogidas.
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			MantenimientoPEDUnidadesAdministrativasChequeoForm form = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;
			String region = val.getNewValue().toString();
			String regiones[] = new String[1];
			regiones[0] = region;
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL));
				form.setCodigoZona(null);
			} else {
				this.siccSeccionList = null;
				this.siccTerritorioList = null;
				this.siccZonaList = null;
				form.setCodigoZona(null);
				form.setCodigoTerritorio(null);
				form.setCodigoSeccion(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param Muestra
	 *            las secciones por las zonas seleccionadas.
	 */
	public void showSeccionxZona(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			MantenimientoPEDUnidadesAdministrativasChequeoForm form = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;

			String regiones = (String) form.getCodigoRegion();

			String zonas = (String) val.getNewValue();

			if (!val.equals(null) && !StringUtils.isBlank(zonas)) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccSeccionList(aSvc.getSeccionesByPaisMarcaCanalRegionZona(
						form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones, zonas));

				form.setCodigoSeccion(null);
			} else {
				this.siccSeccionList = null;
				this.siccTerritorioList = null;
				form.setCodigoTerritorio(null);
				form.setCodigoSeccion(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param Muestra
	 *            los territorios por la secciones escogidas.
	 */
	public void showTerritorioxSeccion(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			MantenimientoPEDUnidadesAdministrativasChequeoForm form = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;

			String regiones = (String) form.getCodigoRegion();

			String zonas = (String) form.getCodigoZona();

			String secciones = (String) val.getNewValue();

			if (!val.equals(null) && !StringUtils.isBlank(secciones)) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTerritorioList(aSvc
						.getTerritoriosByPaisMarcaCanalRegionZonaSeccion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones, zonas,
								secciones));
				form.setCodigoTerritorio(null);
			} else {
				this.siccTerritorioList = null;
				form.setCodigoTerritorio(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertar(ActionEvent evt) {		
		try {
			String msjeValidacion=this.validarRegistrosUnicos();
			if(StringUtils.isNotBlank(msjeValidacion)){
				this.addError("Error: ", msjeValidacion);
				return;
			}
			MantenimientoPEDUnidadesAdministrativasChequeoForm f = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;			
			MantenimientoPEDUnidadesAdministrativasChequeoService service = (MantenimientoPEDUnidadesAdministrativasChequeoService) getBean("spusicc.pedidos.mantenimientoPEDUnidadesAdministrativasChequeoService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String codZona = f.getCodigoZona();
			String codSeccion = f.getCodigoSeccion();
			String codTerritorio = f.getCodigoTerritorio();

			if (StringUtils.isBlank(f.getCodigoZona()))
				codZona = " ";
			if (StringUtils.isBlank(f.getCodigoSeccion()))
				codSeccion = " ";
			if (StringUtils.isBlank(f.getCodigoTerritorio()))
				codTerritorio = "0";

			Map map = new HashMap();
			map.put("codigoPais", pais.getCodigo());
			map.put("codigoPeriodo", f.getCodigoPeriodo());
			map.put("codigoTipoChequeo", f.getCodigoTipoChequeo());
			map.put("codigoRegion", f.getCodigoRegion());
			map.put("codigoZona", codZona);
			map.put("codigoSeccion", codSeccion);
			map.put("codigoTerritorio", codTerritorio);

			service.insertUnidadesAdministrativasChequeo(map);			
			
			f.setCodigoRegion(null);
			f.setCodigoZona(null);
			f.setCodigoSeccion(null);
			f.setCodigoTerritorio(null);
			f.setSelectedItems(null);
			
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.mostrarListaBusqueda = true;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	public String setValidarConfirmar(String accion){
		MantenimientoPEDUnidadesAdministrativasChequeoForm f = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;		
		if(StringUtils.equals(accion, "GUARDAR_VALI")){
			if(StringUtils.isBlank(f.getCodigoRegion()))
				return "Región: Campo Requerido";
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDUnidadesAdministrativasChequeoForm";
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
		MantenimientoPEDUnidadesAdministrativasChequeoForm f = new MantenimientoPEDUnidadesAdministrativasChequeoForm();
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPEDUnidadesAdministrativasChequeoForm f = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;

		MantenimientoPEDUnidadesAdministrativasChequeoService service = (MantenimientoPEDUnidadesAdministrativasChequeoService) getBean("spusicc.pedidos.mantenimientoPEDUnidadesAdministrativasChequeoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map map = new HashMap();

		map.put("codigoPais", pais.getCodigo());
		map.put("codigoPeriodo", f.getCodigoPeriodo());
		map.put("codigoTipoChequeo", f.getCodigoTipoChequeo());
		map.put("codigoRegion", f.getCodigoRegion());
		map.put("codigoZona", f.getCodigoZona());
		map.put("codigoSeccion", f.getCodigoSeccion());
		map.put("codigoTerritorio", f.getCodigoTerritorio());

		List list = service.getUnidadesAdministrativasChequeoList(map);

		this.unidadesAdministrativasChequeoList = list;

		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoPEDUnidadesAdministrativasChequeoService service = (MantenimientoPEDUnidadesAdministrativasChequeoService) getBean("spusicc.pedidos.mantenimientoPEDUnidadesAdministrativasChequeoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map objSeleccionado = (Map) this.beanRegistroSeleccionado;

		Map map = new HashMap();

		String[] ids = new String[1];
		BigDecimal valor = (BigDecimal) objSeleccionado.get("oidUnidAdmiCheq");
		ids[0] = valor.toString();

		map.put("codigoPais", pais.getCodigo());

		service.deleteUnidadesAdministrativasChequeo(map, ids);
		
		return true;
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoPEDUnidadesAdministrativasChequeoForm f = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;
		f.setCodigoPeriodo(null);
		f.setCodigoRegion(null);
		f.setCodigoSeccion(null);
		f.setCodigoTerritorio(null);
		f.setCodigoTipoChequeo(null);
		f.setCodigoZona(null);
		f.setSelectedItems(null);
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		MantenimientoPEDConsultoraChequeoService mantenimientoPEDConsultoraChequeoService = (MantenimientoPEDConsultoraChequeoService) getBean("spusicc.pedidos.mantenimientoPEDConsultoraChequeoService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.pedTipoChequeoList = mantenimientoPEDConsultoraChequeoService
				.getTipoChequeoPais(criteria);
		
		if(this.pedTipoChequeoList.size()>0){
			f.setCodigoTipoChequeo(((Base)this.pedTipoChequeoList.get(0)).getCodigo());
		}
		this.unidadesAdministrativasChequeoList = new ArrayList();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		InterfazSiCCService interfazService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String codigoPeriodo = interfazService.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoPais(pais.getCodigo());

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}

	}
	
	public String validarRegistrosUnicos(){
		MantenimientoPEDUnidadesAdministrativasChequeoForm f = (MantenimientoPEDUnidadesAdministrativasChequeoForm) this.formBusqueda;
		
		
		List listaValidar=this.unidadesAdministrativasChequeoList;
		Map map = new HashMap();
		for(int i=0;i<listaValidar.size();i++){
			map=(Map)listaValidar.get(i);
			
			String periodo= map.get("codigoPeriodo").toString();
			String tipoChequeo= map.get("codigoTipoChequeo").toString();
			String region= map.get("codigoRegion").toString();
			String zona= map.get("codigoZona").toString();
			String seccion= map.get("codigoSeccion").toString();
			String territo= map.get("codigoTerritorio").toString();
			if(StringUtils.isBlank(zona))
				zona=null;
			if(StringUtils.isBlank(seccion))
				seccion=null;
			if(StringUtils.isBlank(territo))
				territo=null;
			
			if(StringUtils.equals(periodo, f.getCodigoPeriodo()) && StringUtils.equals(tipoChequeo, f.getCodigoTipoChequeo())
					&& StringUtils.equals(region, f.getCodigoRegion()) && StringUtils.equals(zona, f.getCodigoZona())
					&& StringUtils.equals(seccion, f.getCodigoSeccion()) && StringUtils.equals(territo, f.getCodigoTerritorio()))
					return this.getResourceMessage("mantenimientoPEDUnidadesAdministrativasChequeoForm.msgValidaRegistros");
		
		}
		
		return null;
	}

	/**
	 * @return the pedTipoChequeoList
	 */
	public List getPedTipoChequeoList() {
		return pedTipoChequeoList;
	}

	/**
	 * @param pedTipoChequeoList
	 *            the pedTipoChequeoList to set
	 */
	public void setPedTipoChequeoList(List pedTipoChequeoList) {
		this.pedTipoChequeoList = pedTipoChequeoList;
	}

	/**
	 * @return the unidadesAdministrativasChequeoList
	 */
	public List getUnidadesAdministrativasChequeoList() {
		return unidadesAdministrativasChequeoList;
	}

	/**
	 * @param unidadesAdministrativasChequeoList
	 *            the unidadesAdministrativasChequeoList to set
	 */
	public void setUnidadesAdministrativasChequeoList(
			List unidadesAdministrativasChequeoList) {
		this.unidadesAdministrativasChequeoList = unidadesAdministrativasChequeoList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 *            the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}