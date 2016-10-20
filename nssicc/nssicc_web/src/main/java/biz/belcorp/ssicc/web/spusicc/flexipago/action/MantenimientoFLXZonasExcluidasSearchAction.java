package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXZonasExcluidasForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXZonasExcluidasSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXZonasExcluidasSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List siccRegionList;
	private List flxZonasExcluidasList;
	private LabelValue[] siccZonaList;
	private Object[] beanMantenimientoFLXZonasExcluidas;
	private DataTableModel dataModel;
	
	public void loadRegiones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}
		String objeto =  (String) val.getNewValue();
		String[] valor = new String[1];
		valor[0] = objeto;		
		if (valor.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(),Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, valor,"");
		} else {
			setSiccZonaList(null);
		}
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoFLXZonasExcluidasList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoFLXZonasExcluidasForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXZonasExcluidasSearchForm form = new MantenimientoFLXZonasExcluidasSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoFLXZonasExcluidasSearchAction");
		}

		MantenimientoFLXZonasExcluidasSearchForm f = (MantenimientoFLXZonasExcluidasSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");

		Map params = BeanUtils.describe(f);

		List zonas = (List) service.getZonasExcluidasByCriteria(params);
		this.flxZonasExcluidasList = zonas;
		int contador = 0;
		for (int i = 0; i < this.flxZonasExcluidasList.size(); i++) {
			HashMap contenedor = (HashMap) this.flxZonasExcluidasList.get(i);
			contenedor.put("id", i);
		}
		
		this.dataModel = new DataTableModel(this.flxZonasExcluidasList);

		return zonas;
	}

	public void eliminar(ActionEvent evt) {
		try {
			MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			int tamanioBean = this.beanMantenimientoFLXZonasExcluidas.length;
			for (int i = 0; i < tamanioBean; i++) {

				HashMap contenedor = (HashMap) this.beanMantenimientoFLXZonasExcluidas[i];
				Map params = new HashMap();
				params.put("codigoRegion", contenedor.get("codigoRegion"));
				params.put("codigoZona", contenedor.get("codigoZona"));

				service.removeZonaExcluida(params);
				
				List listaDespuesdeEliminar = this.setFindAttributes();
				this.dataModel = new DataTableModel(listaDespuesdeEliminar);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoFLXZonasExcluidasForm f = (MantenimientoFLXZonasExcluidasForm) this.formMantenimiento;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		boolean isNew = f.isNewRecord();

		Map zonaExcluida = new HashMap();
		zonaExcluida.put("codigoRegion", f.getCodigoRegion());
		zonaExcluida.put("codigoZona", f.getCodigoZona());

		boolean error = false;
		String mensaje = "";
		try {
			// agregamos los mensajes de exito
			if (isNew) {
				service.insertZonaExcluida(zonaExcluida, usuario);
				this.redireccionarPagina("mantenimientoFLXZonasExcluidasList");
			}
		} catch (InvalidDescriptionException iie) {
			String codigo = iie.getDescription();

			mensaje = this
					.getResourceMessage("mantenimientoFLXZonasExcluidasForm.errors.invalid.id");
			this.addError("Error : ", mensaje);
			error = true;
			return false;
		} catch (Exception ex) {
			mensaje = this.getResourceMessage("errors.detail");
			this.addError("Error : ", mensaje);
			error = true;
			return false;
		}

		if (error) {

			this.siccZonaList = ajaxService
					.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT,
							new String[] { f.getCodigoRegion() }, "");
			return false;
		}
		
		List listaDespuesdeEliminar = this.setFindAttributes();
		this.dataModel = new DataTableModel(listaDespuesdeEliminar);
		return !error;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoFLXZonasExcluidasForm form = new MantenimientoFLXZonasExcluidasForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("setViewAttributes - MantenimientoFLXZonasExcluidasSearchAction");
		}
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		MantenimientoFLXZonasExcluidasSearchForm f = (MantenimientoFLXZonasExcluidasSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		f.setCodigoPais(pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		this.flxZonasExcluidasList = new ArrayList();
		this.mostrarListaBusqueda = false;
		this.setFindAttributes();
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the flxZonasExcluidasList
	 */
	public List getFlxZonasExcluidasList() {
		return flxZonasExcluidasList;
	}

	/**
	 * @param flxZonasExcluidasList
	 *            the flxZonasExcluidasList to set
	 */
	public void setFlxZonasExcluidasList(List flxZonasExcluidasList) {
		this.flxZonasExcluidasList = flxZonasExcluidasList;
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

	/**
	 * @return the beanMantenimientoFLXZonasExcluidas
	 */
	public Object[] getBeanMantenimientoFLXZonasExcluidas() {
		return beanMantenimientoFLXZonasExcluidas;
	}

	/**
	 * @param beanMantenimientoFLXZonasExcluidas
	 *            the beanMantenimientoFLXZonasExcluidas to set
	 */
	public void setBeanMantenimientoFLXZonasExcluidas(
			Object[] beanMantenimientoFLXZonasExcluidas) {
		this.beanMantenimientoFLXZonasExcluidas = beanMantenimientoFLXZonasExcluidas;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the dataModel
	 */
	public DataTableModel getDataModel() {
		return dataModel;
	}

	/**
	 * @param dataModel
	 *            the dataModel to set
	 */
	public void setDataModel(DataTableModel dataModel) {
		this.dataModel = dataModel;
	}

}