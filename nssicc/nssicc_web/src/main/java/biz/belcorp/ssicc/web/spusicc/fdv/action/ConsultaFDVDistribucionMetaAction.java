package biz.belcorp.ssicc.web.spusicc.fdv.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;
import biz.belcorp.ssicc.service.spusicc.fdv.ConsultaFDVDistribucionMetaService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVClusterizacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.fdv.form.ConsultaFDVDistribucionMetaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class ConsultaFDVDistribucionMetaAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4690708550589700319L;
	
	private List consultaDistribucionMetaFdvList;
	private ProcesoFDVClusterizacion registroSeleccionado;

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
		ConsultaFDVDistribucionMetaForm searchForm = new ConsultaFDVDistribucionMetaForm();
		return searchForm;
	}
	
	@Override
	protected List setFindAttributes() throws Exception 
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ConsultaFDVDistribucionMetaForm searchForm = (ConsultaFDVDistribucionMetaForm) this.formBusqueda;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		criteria.put("codigoProceso", searchForm.getCodProc());

		if (StringUtils.isNotBlank(searchForm.getCodigoPais())) {
			criteria.put("codigoPais", searchForm.getCodigoPais());
		} else {
			criteria.put("codigoPais", pais.getCodigo());
		}

		ConsultaFDVDistribucionMetaService service = (ConsultaFDVDistribucionMetaService) getBean("spusicc.consultaFDVDistribucionMetaService");

		List list = service.getDistribucionMetaFDVByCriteria(criteria);
		this.consultaDistribucionMetaFdvList = list;
		return list;
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
		
	}

	public void regresar(ActionEvent event) throws Exception
	{
		this.redireccionarPagina("mantenimientoFDVClusterizacionList");
	}
	
	public void cargar() throws Exception
	{
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		if (log.isDebugEnabled()) {
			log.debug("Id seleccionado de la lista: " + this.registroSeleccionado.getCodProc());
		}
		
		String codProc = this.registroSeleccionado.getCodProc();

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) getBean("spusicc.procesoFDVClusterizacionService");

		ConsultaFDVDistribucionMetaService serviceConsulta = (ConsultaFDVDistribucionMetaService) getBean("spusicc.consultaFDVDistribucionMetaService");

		ConsultaFDVDistribucionMetaForm searchForm = (ConsultaFDVDistribucionMetaForm) this.formBusqueda;
		ProcesoFDVClusterizacion procesoFDVClusterizacion = service.getProcesoCluster(codProc);
		BeanUtils.copyProperties(searchForm, procesoFDVClusterizacion);

		String camAnyProc = searchForm.getAnyProc() + searchForm.getCamProc();
		searchForm.setCamAnyProc(camAnyProc);

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoProceso", codProc);

		// Obteniendo el listado de las zonas consolidadas
		List list = serviceConsulta.getDistribucionMetaFDVByCriteria(criteria);

		// Obteniendo el listado de campañas de acuerdo a la campaña corte del
		// proceso
		List listCamp = service.getCampaniaCambioList(procesoFDVClusterizacion);

		for (int i = 0; i < listCamp.size(); i++) {
			GenericBean genericBean = (GenericBean) listCamp.get(i);
			searchForm.setDesCx(genericBean.getDescripcion(), i);
		}

//		session.setAttribute("consultaFDVDistribucionMetaForm", searchForm);
//		session.removeAttribute(Constants.CONSULTA_DISTRIBUCION_META_FDV_LIST);
		this.consultaDistribucionMetaFdvList = list;
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.consultaDistribucionMetaFdvList);		
	}
	
	public List getConsultaDistribucionMetaFdvList() {
		return consultaDistribucionMetaFdvList;
	}

	public void setConsultaDistribucionMetaFdvList(
			List consultaDistribucionMetaFdvList) {
		this.consultaDistribucionMetaFdvList = consultaDistribucionMetaFdvList;
	}

	public ProcesoFDVClusterizacion getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(
			ProcesoFDVClusterizacion registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}
}
