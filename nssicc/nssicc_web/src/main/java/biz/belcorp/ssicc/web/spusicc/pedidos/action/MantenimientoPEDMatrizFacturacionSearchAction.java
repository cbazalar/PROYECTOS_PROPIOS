package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMatrizFacturacionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMatrizFacturacionSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDMatrizFacturacionSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -3985505645316226813L;
	private List pedOfertaMatrizFacturacionEstimadoList;

	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDMatrizFacturacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPEDMatrizFacturacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDMatrizFacturacionSearchForm form = new MantenimientoPEDMatrizFacturacionSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {

		MantenimientoPEDMatrizFacturacionSearchForm searchForm = (MantenimientoPEDMatrizFacturacionSearchForm) this.formBusqueda;
		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService) getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		searchForm.setCodigoPais(pais.getCodigo());
		Map params = BeanUtils.describe(searchForm);

		List lista = (List) service.getEstimadosMatrizFacturacion(params);

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService) getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
		MantenimientoPEDMatrizFacturacionForm editForm = (MantenimientoPEDMatrizFacturacionForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MatrizFacturacion matriz = new MatrizFacturacion();
		BeanUtils.copyProperties(matriz, editForm);

		if (editForm.isNewRecord()) {
			service.insertMatrizFacturacion(matriz, usuario);
			this.beanRegistroSeleccionado = null;
		} else {
			service.updateMatrizFacturacion(matriz, usuario);
			this.beanRegistroSeleccionado = null;
		}
		//this.redireccionarPagina(this.getSalirForward());
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService) getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
		MantenimientoPEDMatrizFacturacionForm editForm = new MantenimientoPEDMatrizFacturacionForm();
		MatrizFacturacion objetoSeleccionado = (MatrizFacturacion) this.beanRegistroSeleccionado;
		editForm.setNewRecord(true);
		if (objetoSeleccionado != null) {
			String oidMatriz = objetoSeleccionado.getOidMatriz();			
			if (!StringUtils.isBlank(oidMatriz)) {
				editForm.setNewRecord(false);
				MatrizFacturacion matriz = service
						.getMatrizFacturacion(oidMatriz);

				BeanUtils.copyProperties(editForm, matriz);
				this.beanRegistroSeleccionado = null;
			}
		}
		return editForm;
	}

	public void inicializar() {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarListaBusqueda = true;
		this.beanRegistroSeleccionado = null;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.inicializar();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDMatrizFacturacionSearchForm searchForm = (MantenimientoPEDMatrizFacturacionSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(pais.getCodigo());

		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService) getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");

		List periodos = service.getPeriodosMatrizFacturacion();
		this.pedOfertaMatrizFacturacionEstimadoList = periodos;
		searchForm.setCodigoPeriodo("");
		List listaInicial = new ArrayList();
		// this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(listaInicial);
	}

	/**
	 * @return the pedOfertaMatrizFacturacionEstimadoList
	 */
	public List getPedOfertaMatrizFacturacionEstimadoList() {
		return pedOfertaMatrizFacturacionEstimadoList;
	}

	/**
	 * @param pedOfertaMatrizFacturacionEstimadoList
	 *            the pedOfertaMatrizFacturacionEstimadoList to set
	 */
	public void setPedOfertaMatrizFacturacionEstimadoList(
			List pedOfertaMatrizFacturacionEstimadoList) {
		this.pedOfertaMatrizFacturacionEstimadoList = pedOfertaMatrizFacturacionEstimadoList;
	}
}