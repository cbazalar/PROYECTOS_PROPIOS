package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCLiquidacionLoteBancarioService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCLiquidacionLoteBancarioSearchForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCParametrosGeneralesForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCParametrosGeneralesSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCCCParametrosGeneralesSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List cccParametrosGeneralesList;
	private boolean mostrarConsultar;
	private boolean mostrarCodigoParametro;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCParametrosGeneralesSearchForm form = new MantenimientoCCCParametrosGeneralesSearchForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCCCParametrosGeneralesForm";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entró 'setViewAtributes()' method - MantenimientoCCCParametrosGeneralesSearchAction");
		}
		
		MantenimientoCCCParametrosGeneralesSearchForm f = (MantenimientoCCCParametrosGeneralesSearchForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		this.mostrarConsultar = false;
		this.mostrarCodigoParametro = false;
		
		this.find();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entró 'setFindAttributes()' method - MantenimientoCCCParametrosGeneralesSearchAction");
		}

		MantenimientoCCCParametrosGeneralesSearchForm f = (MantenimientoCCCParametrosGeneralesSearchForm) this.formBusqueda;
		MantenimientoCCCParametrosGeneralesService service = (MantenimientoCCCParametrosGeneralesService) getBean("spusicc.mantenimientoCCCParametrosGeneralesService");

		Map criteria = new HashMap();
		criteria.put("codigoParametro", f.getCodigoParametro());
		criteria.put("descripcionParametro", f.getDescripcionParametro());

		List resultado = service.getParametroGeneralByCriteria(criteria);

		this.cccParametrosGeneralesList = new ArrayList();
		this.cccParametrosGeneralesList = resultado;

		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entró 'setObtenerRegistroAttributes()' method - MantenimientoCCCParametrosGeneralesSearchAction");
		}
		
		MantenimientoCCCParametrosGeneralesForm f = new MantenimientoCCCParametrosGeneralesForm();
		MantenimientoCCCParametrosGeneralesService service = (MantenimientoCCCParametrosGeneralesService) getBean("spusicc.mantenimientoCCCParametrosGeneralesService");
		
		Map valorSeleccionado = (Map) this.beanRegistroSeleccionado;
		String id = MapUtils.getString(valorSeleccionado, "codigoParametro");
		
		this.mostrarBotonSave = true;
		
		if(!StringUtils.equals(this.accion, this.ACCION_NUEVO)){
			List listRegistro = service.getParametroGeneralByCriteria(valorSeleccionado);
			BeanUtils.copyProperties(f, listRegistro.get(0));
			
			if(StringUtils.equals(this.accion, this.ACCION_CONSULTAR)){				
				this.mostrarConsultar = true;
				this.mostrarCodigoParametro = true;
				this.mostrarBotonSave = false;
			}
			
			if(StringUtils.equals(this.accion, this.ACCION_MODIFICAR)){
				this.mostrarConsultar = false;
				this.mostrarCodigoParametro = true;
				this.mostrarBotonSave = true;
			}
		}else{
			this.mostrarConsultar = false;
			this.mostrarCodigoParametro = false;
			this.mostrarBotonSave = true;
		}
		
		return f;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entró 'setSaveAttributes()' method - MantenimientoCCCParametrosGeneralesSearchAction");
		}
		
		MantenimientoCCCParametrosGeneralesForm f = (MantenimientoCCCParametrosGeneralesForm) this.formMantenimiento;
		MantenimientoCCCParametrosGeneralesService service = (MantenimientoCCCParametrosGeneralesService) getBean("spusicc.mantenimientoCCCParametrosGeneralesService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteria = BeanUtils.describe(f);
		criteria.put("codigoUsuario", usuario.getLogin());
		
		if(StringUtils.equals(this.accion, this.ACCION_NUEVO)){
			criteria.put("descripcionParametro", "");
			List existeCodPara = service.getParametroGeneralByCriteria(criteria);
			
			if(existeCodPara.size() > 0){
				this.addError("", "El Código Parámetro ya existe");
				return false;
			}else{
				criteria.put("descripcionParametro", f.getDescripcionParametro());
				service.insertParametroGeneral(criteria);
			}
		} else if(StringUtils.equals(this.accion, this.ACCION_MODIFICAR)){
			service.updateParametroGeneral(criteria);
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveFindBeforeAttributes()
	 */
	@Override
	public void setSaveFindBeforeAttributes() throws Exception {
		MantenimientoCCCParametrosGeneralesSearchForm f = (MantenimientoCCCParametrosGeneralesSearchForm) this.formBusqueda;
		
		f.setCodigoParametro("");
		
		super.setSaveFindBeforeAttributes();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entró 'setDeleteAttributes()' method - MantenimientoCCCParametrosGeneralesSearchAction");
		}

		MantenimientoCCCParametrosGeneralesSearchForm f = (MantenimientoCCCParametrosGeneralesSearchForm) this.formBusqueda;
		MantenimientoCCCParametrosGeneralesService service = (MantenimientoCCCParametrosGeneralesService) getBean("spusicc.mantenimientoCCCParametrosGeneralesService");
		
		Map valorSeleccionado = (Map) this.beanRegistroSeleccionado;
		String id = MapUtils.getString(valorSeleccionado, "codigoParametro");

		if (!StringUtils.isBlank(id)) {
			Map criteria = new HashMap();
			criteria.put("codigoParametro", id);

			service.deleteParametroGeneral(criteria);
			
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(listaBusqueda);
			
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion){
		if (log.isDebugEnabled()) {
			log.debug("Entró 'setValidarConfirmar()' method - MantenimientoCCCParametrosGeneralesSearchAction");
		}
		
		if (this.beanRegistroSeleccionado == null) {
			String mensaje = this.getResourceMessage("errors.select.item");
			return mensaje;
		}
		
		return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCParametrosGeneralesList";
	}

	/**
	 * @return the cccParametrosGeneralesList
	 */
	public List getCccParametrosGeneralesList() {
		return cccParametrosGeneralesList;
	}

	/**
	 * @param cccParametrosGeneralesList the cccParametrosGeneralesList to set
	 */
	public void setCccParametrosGeneralesList(List cccParametrosGeneralesList) {
		this.cccParametrosGeneralesList = cccParametrosGeneralesList;
	}

	/**
	 * @return the mostrarConsultar
	 */
	public boolean isMostrarConsultar() {
		return mostrarConsultar;
	}

	/**
	 * @param mostrarConsultar the mostrarConsultar to set
	 */
	public void setMostrarConsultar(boolean mostrarConsultar) {
		this.mostrarConsultar = mostrarConsultar;
	}

	/**
	 * @return the mostrarCodigoParametro
	 */
	public boolean isMostrarCodigoParametro() {
		return mostrarCodigoParametro;
	}

	/**
	 * @param mostrarCodigoParametro the mostrarCodigoParametro to set
	 */
	public void setMostrarCodigoParametro(boolean mostrarCodigoParametro) {
		this.mostrarCodigoParametro = mostrarCodigoParametro;
	}
}