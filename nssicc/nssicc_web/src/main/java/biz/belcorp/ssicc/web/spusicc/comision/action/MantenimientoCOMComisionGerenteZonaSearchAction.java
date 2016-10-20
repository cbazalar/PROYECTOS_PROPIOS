package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ComisionPeriodoGerenteZona;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMComisionGerenteZonaForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMComisionGerenteZonaSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCOMComisionGerenteZonaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -230255405040196756L;
	private List mantenimientoCOMComisionGerenteZonaList;
	private List siccComisionList;
	private boolean consulta;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCOMComisionGerenteZonaList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOMComisionGerenteZonaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOMComisionGerenteZonaSearchForm form = new MantenimientoCOMComisionGerenteZonaSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		MantenimientoCOMComisionGerenteZonaSearchForm f = (MantenimientoCOMComisionGerenteZonaSearchForm) this.formBusqueda;

		/* obteniendo valores */
		Map criteria = new HashMap();
		//criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoComision", f.getCodigoComision());
		criteria.put("tipoComision", Constants.TIPO_COMISION_GERENCIA);

		// Se añadio ya que el método necesita de codigoPeriodoIni y
		// codigoPeriodoFin
		criteria.put("codigoPeriodoIni", f.getCodigoPeriodo());
		criteria.put("codigoPeriodoFin", f.getCodigoPeriodo());

		/* Obteniendo Lista */
		List resultado = service
				.getComisionPeriodoGerenteZonaByCriteria(criteria);
		this.mantenimientoCOMComisionGerenteZonaList = resultado;
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		MantenimientoCOMComisionGerenteZonaForm f = (MantenimientoCOMComisionGerenteZonaForm) this.formMantenimiento;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ComisionPeriodoGerenteZona bean = new ComisionPeriodoGerenteZona();
		BeanUtils.copyProperties(bean, f);
		bean.setImporteComisionTramo1(new Double(f.getImporteComisionTramo1()));
		bean.setImporteComisionTramo2(new Double(f.getImporteComisionTramo2()));

		service.updateComisionPeriodoGerenteZona(bean, usuario);
		this.salirGrabarPantallaPadre = true;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOMComisionGerenteZonaForm form = (MantenimientoCOMComisionGerenteZonaForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoCOMComisionGerenteZona.updated";
		} else {
			return "mantenimientoCOMComisionGerenteZona.updated";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		MantenimientoCOMComisionGerenteZonaForm f = new MantenimientoCOMComisionGerenteZonaForm();
		ComisionPeriodoGerenteZona data = (ComisionPeriodoGerenteZona) this.beanRegistroSeleccionado;
		ComisionPeriodoGerenteZona bean = service
				.getComisionPeriodoGerenteZona(data);
		BeanUtils.copyProperties(f, bean);
		formMantenimiento = f;
		this.mostrarBotonSave = true;
		if (accion.equals(this.ACCION_MODIFICAR)) {
			consulta = false;
		} else if (accion.equals(this.ACCION_CONSULTAR)) {
			this.mostrarBotonSave = false;
			consulta = true;
		}

		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoCOMComisionGerenteZonaSearchForm f = (MantenimientoCOMComisionGerenteZonaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccComisionList = service.getComision();
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(codigoPeriodo);
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		log.debug("Todo Ok: Redireccionando");
	}

	/**
	 * @return the mantenimientoCOMComisionGerenteZonaList
	 */
	public List getMantenimientoCOMComisionGerenteZonaList() {
		return mantenimientoCOMComisionGerenteZonaList;
	}

	/**
	 * @param mantenimientoCOMComisionGerenteZonaList
	 *            the mantenimientoCOMComisionGerenteZonaList to set
	 */
	public void setMantenimientoCOMComisionGerenteZonaList(
			List mantenimientoCOMComisionGerenteZonaList) {
		this.mantenimientoCOMComisionGerenteZonaList = mantenimientoCOMComisionGerenteZonaList;
	}

	/**
	 * @return the siccComisionList
	 */
	public List getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList
	 *            the siccComisionList to set
	 */
	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	/**
	 * @return the consulta
	 */
	public boolean isConsulta() {
		return consulta;
	}

	/**
	 * @param consulta
	 *            the consulta to set
	 */
	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}

}
