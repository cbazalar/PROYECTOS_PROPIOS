package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREValidacionMatrizService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREValidacionMatrizForm;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREValidacionMatrizSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoPREValidacionMatrizSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List preValidacionMatrizList;
	private Boolean valorInactivo;

	public void inicializar() {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.preValidacionMatrizList = new ArrayList();
		this.mostrarListaBusqueda = true;
		
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoPREValidacionMatrizList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPREValidacionMatrizForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoPREValidacionMatrizSearchForm form = new MantenimientoPREValidacionMatrizSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPREValidacionMatrizSearchForm f = (MantenimientoPREValidacionMatrizSearchForm) this.formBusqueda;
		MantenimientoPREValidacionMatrizService service = (MantenimientoPREValidacionMatrizService) this
				.getBean("spusicc.mantenimientoPREValidacionMatrizService");

		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);

		/* Obteniendo Lista */
		List resultado = service.getListValidacionMatriz(criteria);
		this.preValidacionMatrizList = resultado;
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoPREValidacionMatrizForm f = (MantenimientoPREValidacionMatrizForm) this.formMantenimiento;

		if(this.valorInactivo.equals(false)){
			f.setIndActividad("0");
		}else{
			f.setIndActividad("1");
		}
		MantenimientoPREValidacionMatrizService service = (MantenimientoPREValidacionMatrizService) 
													getBean("spusicc.mantenimientoPREValidacionMatrizService");
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		service.updateValidacionMatriz(params);//upadte
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(listaBusqueda);
		this.redireccionarPagina(getSalirForward());
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoPREValidacionMatrizForm f = new MantenimientoPREValidacionMatrizForm();
		HashMap objetoSeleccionado = (HashMap) this.beanRegistroSeleccionado;
		String id = objetoSeleccionado.get("oid").toString();
		MantenimientoPREValidacionMatrizService service = (MantenimientoPREValidacionMatrizService) getBean("spusicc.mantenimientoPREValidacionMatrizService");
		
		log.debug("row id " + id);
		if (!StringUtils.isBlank(id)) {
			List list = this.preValidacionMatrizList;
			log.debug("map " + objetoSeleccionado);
			f.setOid(objetoSeleccionado.get("oid").toString());
			f.setDescripcion(objetoSeleccionado.get("descripcion").toString());
			f.setMensaje(objetoSeleccionado.get("mensaje").toString());
			f.setIndActividad(objetoSeleccionado.get("indActividad").toString());

			log.debug("enviando para editar");
			if(f.getIndActividad().equals("0")){
				 this.valorInactivo = false;
			}else{
				this.valorInactivo = true;
			}

		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.inicializar();

		MantenimientoPREValidacionMatrizSearchForm f = (MantenimientoPREValidacionMatrizSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(listaBusqueda);
	}

	/**
	 * @return the preValidacionMatrizList
	 */
	public List getPreValidacionMatrizList() {
		return preValidacionMatrizList;
	}

	/**
	 * @param preValidacionMatrizList the preValidacionMatrizList to set
	 */
	public void setPreValidacionMatrizList(List preValidacionMatrizList) {
		this.preValidacionMatrizList = preValidacionMatrizList;
	}

	/**
	 * @return the valorInactivo
	 */
	public Boolean getValorInactivo() {
		return valorInactivo;
	}

	/**
	 * @param valorInactivo the valorInactivo to set
	 */
	public void setValorInactivo(Boolean valorInactivo) {
		this.valorInactivo = valorInactivo;
	}
}
