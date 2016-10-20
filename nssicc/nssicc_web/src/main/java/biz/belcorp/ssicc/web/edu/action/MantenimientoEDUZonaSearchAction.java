package biz.belcorp.ssicc.web.edu.action;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.web.edu.form.MantenimientoEDUZonaSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import org.apache.commons.beanutils.BeanUtils;

@ManagedBean
@SessionScoped
public class MantenimientoEDUZonaSearchAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9082813603082928337L;
	private List eduEmpresaComercializadoraList;
	private List eduRegionList;
	private List eduZonaList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		MantenimientoEDUZonaSearchForm form = new MantenimientoEDUZonaSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		MantenimientoEDUZonaSearchForm f = (MantenimientoEDUZonaSearchForm) this.formReporte;
		RegionCursoCapacitacion region = new RegionCursoCapacitacion();
		MantenimientoEDUCursoCapacitacionService service = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");
		BeanUtils.copyProperties(region, f);
		// List resultado = service.getRegion(region);
		Map criteria = BeanUtils.describe(region);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String[] regiones = f.getRegiones();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoEmpresa", f.getCodigoEmpresa());
		criteria.put("codigoRegion", "");
		if (regiones != null && !regiones[0].equalsIgnoreCase("")) {
			criteria.put("codigoRegion", regiones);
		}
		List resultado = service.getZonaByRegionSelected(criteria);
		this.eduZonaList = resultado;
		// session.setAttribute(Constants.EDU_ZONA_LIST,
		// resultado);
		return resultado;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		// Cargamos los combos a utilizar

		// TODO Auto-generated method stub
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoEDUZonaSearchForm f = (MantenimientoEDUZonaSearchForm) this.formReporte;
		MantenimientoEDUCursoCapacitacionService service = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");
		f.setCodigoPais(pais.getCodigo());
		RegionCursoCapacitacion region = new RegionCursoCapacitacion();
		loadCombos();
		/* Inicializamos la Empresa, siempre despues de LoadCombos */
		List listaEmpresa = this.eduEmpresaComercializadoraList;
		if ((listaEmpresa != null) && (listaEmpresa.size() > 0)) {
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}
		BeanUtils.copyProperties(region, f);
		List lista = service.getRegion(region);
		this.eduRegionList = lista;

	}

	private void loadCombos() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		this.eduEmpresaComercializadoraList = siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/**
	 * @return the eduEmpresaComercializadoraList
	 */
	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	/**
	 * @param eduEmpresaComercializadoraList the eduEmpresaComercializadoraList to set
	 */
	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	/**
	 * @return the eduRegionList
	 */
	public List getEduRegionList() {
		return eduRegionList;
	}

	/**
	 * @param eduRegionList the eduRegionList to set
	 */
	public void setEduRegionList(List eduRegionList) {
		this.eduRegionList = eduRegionList;
	}

	/**
	 * @return the eduZonaList
	 */
	public List getEduZonaList() {
		return eduZonaList;
	}

	/**
	 * @param eduZonaList the eduZonaList to set
	 */
	public void setEduZonaList(List eduZonaList) {
		this.eduZonaList = eduZonaList;
	}
	
	
	
	



}
