package biz.belcorp.ssicc.web.spncd.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProgramaNuevCuponesNivelForm;

@SessionScoped
@ManagedBean
public class MantenimientoCUPProgramaNuevCuponesNivelAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2714342400045815475L;
	ProgramaCupon data;
	private List cupNivelList;
	private List<Base> noAsignados = new ArrayList();
	private List<Base> asignados = new ArrayList();
	private String valorVigencia = Constants.CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPProgramaNuevCuponesList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPProgramaNuevCuponesNivelForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/**
	 * Inicializando valores de la pantalla
	 */
	public void inicializando() {
		this.asignados = new ArrayList();
		this.noAsignados = new ArrayList();
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		MantenimientoCUPProgramaNuevCuponesNivelForm f = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.formMantenimiento;
		f.setCodigoPrograma(getData().getCodigoPrograma());
		f.setCodigoMarca(getData().getCodigoMarca());
		f.setCodigoCanal(getData().getCodigoCanal());
		f.setCampanhaInicial(getData().getCampanhaInicial());
		f.setCampanhaFinal(getData().getCampanhaFinal());
		f.setCodigoVentCupIni(getData().getCodigoVentCupIni());
		f.setCodigoVentCupFin(getData().getCodigoVentCupFin());
		f.setNivel(null);
		f.setNumVigencia(getData().getNumVigencia());
		if (StringUtils.equals(getData().getEstadoProg(), "S"))
			f.setEditable(true);
		else
			f.setEditable(false);

		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.cupNivelList = service.getNivelbyPais(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoCUPProgramaNuevCuponesNivelForm f = new MantenimientoCUPProgramaNuevCuponesNivelForm();
		this.formMantenimiento = f;

	}

	/**
	 * Cargando cupones no asignados y asignados
	 * 
	 * @param val
	 */
	public void loadCupones(ValueChangeEvent val) {
		MantenimientoCUPProgramaNuevCuponesNivelForm f = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.noAsignados = new ArrayList();
		this.asignados = new ArrayList();
		f.setNumVigencia("");
		f.setCuponesAsignados(null);
		f.setCuponesNoAsignados(null);
		if (val.getNewValue() != null) {
			String valor = val.getNewValue().toString();

			String numVig = ajax.getVigenciaCuponPorNivel(pais.getCodigo(),
					f.getCodigoPrograma(), valor);
			f.setNumVigencia(numVig);
			LabelValue[] list = ajax.getCuponesFaltantesByNivel(
					pais.getCodigo(), f.getCodigoPrograma(), valor, "");
			LabelValue[] listAsignados = ajax.getCuponesByNivel(
					pais.getCodigo(), f.getCodigoPrograma(), valor, "");
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					Base b = new Base();
					b.setCodigo(list[i].getValue());
					b.setDescripcion(list[i].getLabel());
					this.noAsignados.add(b);
				}
			}

			if (listAsignados != null) {
				for (int i = 0; i < listAsignados.length; i++) {
					Base b = new Base();
					b.setCodigo(listAsignados[i].getValue());
					b.setDescripcion(listAsignados[i].getLabel());
					this.asignados.add(b);
				}
			}
		} else {
			this.noAsignados = new ArrayList<Base>();
			this.asignados = new ArrayList<Base>();
		}
	}

	public void ordenarDerecha(ActionEvent event) {
		MantenimientoCUPProgramaNuevCuponesNivelForm f = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.formMantenimiento;
		String[] val = f.getCuponesNoAsignados();
		List listAsignados = this.asignados;
		for (int i = 0; i < val.length; i++) {
			Base b = new Base();
			b.setCodigo(val[i]);
			b.setDescripcion(val[i]);
			listAsignados.add(b);
			for (int j = 0; j < noAsignados.size(); j++) {
				Base data = (Base) noAsignados.get(j);
				if(data.getCodigo().equals(val[i]))
					noAsignados.remove(data);
			}
		}
	}
	
	public void ordenarIzquierda(ActionEvent event) {
		MantenimientoCUPProgramaNuevCuponesNivelForm f = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.formMantenimiento;
		String[] val = f.getCuponesAsignados();
		List listNoAsignados = this.noAsignados;
		for (int i = 0; i < val.length; i++) {
			Base b = new Base();
			b.setCodigo(val[i]);
			b.setDescripcion(val[i]);
			listNoAsignados.add(b);
			for (int j = 0; j < asignados.size(); j++) {
				Base data = (Base) asignados.get(j);
				if(data.getCodigo().equals(val[i]))
					asignados.remove(data);
			}
		}
	}

	/*
	 * (non-Javadoc) Guardando los cupones asignados
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#save(javax.faces.event.ActionEvent)
	 */
	public void save(ActionEvent event) {
		if (StringUtils.isNotBlank(guardarValida())) {
			this.setMensajeAlertaDefault(guardarValida());
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCUPProgramaNuevCuponesNivelForm cuponForm = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.formMantenimiento;
		String[] _asignados = new String[this.asignados.size()];
		int i = 0;
		for (Base object : this.asignados) {
			_asignados[i] = object.getCodigo();
			i++;
		}

		String[] _noAsignados = new String[this.noAsignados.size()];
		int j = 0;
		for (Base object : this.noAsignados) {
			_noAsignados[j] = object.getCodigo();
			j++;
		}
		cuponForm.setCuponesAsignados(_asignados);
		cuponForm.setCuponesNoAsignados(_noAsignados);

		Map criteria;
		try {
			criteria = BeanUtils.describe(cuponForm);
			criteria.put("cuponesAsignados", _asignados);
			criteria.put("cuponesNoAsignados", _noAsignados);
			criteria.put("loginUsuario", usuario.getLogin());

			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

			if (validationSuccessful(cuponForm)) {
				service.actualizarCuponNivel(criteria);
				this.addInfo(
						"Info : ",
						this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesNivelForm.add.success"));
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validando la asignacion de cupones
	 * 
	 * @param cuponForm
	 * @return
	 */
	private boolean validationSuccessful(
			MantenimientoCUPProgramaNuevCuponesNivelForm cuponForm) {

		boolean isOk = true;
		Map map = new HashMap();
		String mensaje = "";
		int tamanioCuponesNoAsignados = cuponForm.getCuponesNoAsignados().length;
		if (tamanioCuponesNoAsignados > 0) {
			map.put("cuponesNoAsignados", cuponForm.getCuponesNoAsignados());
			map.put("codigoPrograma", cuponForm.getCodigoPrograma());
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			List cupNoAsignadosUtilizados = new ArrayList();
			cupNoAsignadosUtilizados = service.getCupNoAsignadosUtilizados(map);
			String cupUtilizados = "  ";
			if (cupNoAsignadosUtilizados.size() > 0) {
				mensaje = this
						.getResourceMessage("errors.codigo.cupon.utilizados");
				for (int i = 0; i < cupNoAsignadosUtilizados.size(); i++)
					cupUtilizados = cupUtilizados
							+ cupNoAsignadosUtilizados.get(i) + "  ";
				this.addError("Error : ", mensaje);
			}

			if (!mensaje.isEmpty()) {
				isOk = false;
			}
		}
		return isOk;
	}

	private String guardarValida() {
		MantenimientoCUPProgramaNuevCuponesNivelForm f = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.formMantenimiento;
		String msje = "";
		if (StringUtils.isBlank(f.getNivel()))
			return msje = "Nivel: Campo Requerido";
		if (StringUtils.equals(f.getIndicadorVigencia(), this.valorVigencia)) {
			if (StringUtils.isBlank(f.getNumVigencia()))
				return msje = "Vigencia cupones: Campo Requerido";
			else {
				int valor = new Integer(f.getNumVigencia());
				if (valor <= 0)
					return msje = "'Vigencia cupones' debe de ser mayor a cero.";
			}
		}
		return msje;

	}

	/**
	 * @return the data
	 */
	public ProgramaCupon getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(ProgramaCupon data) {
		this.data = data;
	}

	/**
	 * @return the cupNivelList
	 */
	public List getCupNivelList() {
		return cupNivelList;
	}

	/**
	 * @param cupNivelList
	 *            the cupNivelList to set
	 */
	public void setCupNivelList(List cupNivelList) {
		this.cupNivelList = cupNivelList;
	}

	/**
	 * @return the noAsignados
	 */
	public List<Base> getNoAsignados() {
		return noAsignados;
	}

	/**
	 * @param noAsignados
	 *            the noAsignados to set
	 */
	public void setNoAsignados(List<Base> noAsignados) {
		this.noAsignados = noAsignados;
	}

	/**
	 * @return the asignados
	 */
	public List<Base> getAsignados() {
		return asignados;
	}

	/**
	 * @param asignados
	 *            the asignados to set
	 */
	public void setAsignados(List<Base> asignados) {
		this.asignados = asignados;
	}

	public String getValorVigencia() {
		return valorVigencia;
	}

	public void setValorVigencia(String valorVigencia) {
		this.valorVigencia = valorVigencia;
	}

}