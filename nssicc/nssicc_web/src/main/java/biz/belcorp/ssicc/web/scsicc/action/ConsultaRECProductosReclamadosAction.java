package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaRECProductosReclamadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaRECProductosReclamadosAction extends
		BaseConsultaAbstractAction {

	private static final long serialVersionUID = 1L;
	private List consultaProductReclamList;
	private List siccCentrodList;
	private LabelValue[] siccRegionList;
	private List siccSubAccesoList;
	private List siccOperacionesList;
	private List siccTipoIngresoList;

	/**
	 * @return
	 */
	public List getSiccTipoIngresoList() {
		return siccTipoIngresoList;
	}

	/**
	 * @param siccTipoIngresoList
	 */
	public void setSiccTipoIngresoList(List siccTipoIngresoList) {
		this.siccTipoIngresoList = siccTipoIngresoList;
	}

	/**
	 * @return
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	/**
	 * @param siccSubAccesoList
	 */
	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}

	/**
	 * @return
	 */
	public List getSiccCentrodList() {
		return siccCentrodList;
	}

	/**
	 * @param siccCentrodList
	 */
	public void setSiccCentrodList(List siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
	}

	/**
	 * @return
	 */
	public List getConsultaProductReclamList() {
		return consultaProductReclamList;
	}

	/**
	 * @param consultaProductReclamList
	 */
	public void setConsultaProductReclamList(List consultaProductReclamList) {
		this.consultaProductReclamList = consultaProductReclamList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaRECProductosReclamadosForm reporteForm = new ConsultaRECProductosReclamadosForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	protected void setViewAtributes() throws Exception {
		// Removemos el form bean

		ConsultaRECProductosReclamadosForm f = (ConsultaRECProductosReclamadosForm) formBusqueda;
		f.setFechaInicio(new Date(System.currentTimeMillis()));
		f.setFechaFin(new Date(System.currentTimeMillis()));
		// Obtenemos los beans básicos de la sesión
		Usuario usuario = (mPantallaPrincipalBean.getCurrentUser());
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();


		// Carga de los combos
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteriaSubAcceso = new HashMap();
		this.siccSubAccesoList=interfazSiCCService
				.getInterfazRECProductosReclamadosSubAccesos(criteriaSubAcceso);
		

		Map criteriaTipoIngreso = new HashMap();
		criteriaTipoIngreso
				.put("codigoISO", usuario.getIdioma().getCodigoISO());
		criteriaTipoIngreso.put("codigoTipoIng",
				Constants.CODIGO_TIPO_INGRESO_M);
		
		this.siccTipoIngresoList=interfazSiCCService
				.getTiposIngresoByCodigoISO(criteriaTipoIngreso);
	

		MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccOperacionesList=operacionService
				.getOperacionesHomologadasByCodigoPais(criteriaOperacion);

		/*
		 * Map criteriaOperacion = new HashMap();
		 * criteriaOperacion.put("codigoPais", pais.getCodigo());
		 * request.getSession().setAttribute( Constants.SICC_OPERACIONES_LIST,
		 * interfazSiCCService .getOperacionesByCodigoPais(criteriaOperacion));
		 */

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList=aSvc.getRegionesByPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());


	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#setFindAttributes()
	 */
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		
		ConsultaRECProductosReclamadosForm f = (ConsultaRECProductosReclamadosForm) formBusqueda;
		List listProdREC = null;
		f.setOperacion("");
		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);
		
		criteria.put("fechaInicio", DateUtil.getDate(f.getFechaInicio()));
		criteria.put("fechaFin", DateUtil.getDate(f.getFechaFin()));
		
		// La busqueda solo la realizaremos en los sistemas activos
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("codigoLineaList", new ArrayList());
		criteria.put("regionList", f.getRegiones() == null ? new ArrayList()
				: Arrays.asList(f.getRegiones()));

		if (f.getSubAccesos().length > 0
				&& f.getSubAccesos()[0]
						.equalsIgnoreCase(Constants.REC_SUBACCESO_DEFAULT)) {
			criteria.put("subacList", new ArrayList());
			criteria.put("subacGZ", Constants.NUMERO_UNO);
		} else {
			criteria.put(
					"subacList",
					(f.getSubAccesos() == null) ? new ArrayList() : Arrays
							.asList(f.getSubAccesos()));
			criteria.put("subacGZ", null);
		}
		/*
		 * criteria.put("subacList", f.getSubAccesos() == null ? new ArrayList()
		 * : Arrays.asList(f.getSubAccesos()));
		 */

		MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais",
				(mPantallaPrincipalBean.getCurrentCountry().getCodigo()));
		criteriaOperacion.put("codigoOperacionHomologada", f.getOperacion());

		
		if (f.getOperacion().equalsIgnoreCase("E")) {
			criteria.put("errorSacado", "SI");
		} else {
			criteria.put("errorSacado", null);
		}

		criteria.put("operacionList", operacionService
				.getOperacionesByOperacionHomologada(criteriaOperacion));

		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getCodigoSistema())) {
			criteria.put("codigoSistema", f.getCodigoSistema() + "%");
		}
		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getDocRef())) {
			criteria.put("docRef", f.getDocRef() + "%");
		}
		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getCliente())) {
			criteria.put("cliente", f.getCliente() + "%");
		}
		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getCodSap())) {
			criteria.put("codSap", f.getCodSap() + "%");
		}
		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getCodVenta())) {
			criteria.put("codVenta", f.getCodVenta() + "%");
		}
		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getDescProd())) {
			criteria.put("descProd", f.getDescProd() + "%");
		}

		if (log.isDebugEnabled()) {
			log.debug("criteria search " + criteria.toString());
		}
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		listProdREC = interfazSiCCService.getConsultaRECProductosList(criteria);
		this.consultaProductReclamList = listProdREC;

		if (log.isDebugEnabled()) {
			log.debug("******* listProdREC " + listProdREC);
		}

		return this.consultaProductReclamList;
	}

}