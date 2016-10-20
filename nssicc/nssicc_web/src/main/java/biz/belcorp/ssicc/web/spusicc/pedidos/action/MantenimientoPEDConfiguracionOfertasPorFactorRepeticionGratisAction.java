package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService;

import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm;


/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisAction extends BasePopupAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8413775548396016101L;
	
	
	private String verificaGrabado;
	private List pedOfertasPorFactorRepeticionRangosGratisList;
	private Boolean mostrarBotonGratisEliminar;
	private String oidOferta;
	private String oidRango;
	private String codigoPeriodo;
	private String precioUnitario;
	private String factorRepeticion;
	private DataTableModel dataModelGratisList;
	private Object beanGratisList;
	private List pedOfertasPorFactorRepeticionRegalosList;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm f = new MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm();
		return f;
	}


	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		try {
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm) this.formBusqueda;

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			this.oidOferta = externalContext.getRequestParameterMap()
					.get("oidOferta").toString();
			this.oidRango = externalContext.getRequestParameterMap()
					.get("oidRango").toString();
			this.factorRepeticion = externalContext.getRequestParameterMap()
					.get("factorRepeticion").toString();
			this.precioUnitario = externalContext.getRequestParameterMap()
					.get("precioUnitario").toString();
			
			this.codigoPeriodo = externalContext.getRequestParameterMap()
					.get("codigoPeriodo").toString();

			gratisForm.setCodigoPeriodo(codigoPeriodo);

			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			

			if (log.isDebugEnabled())
				log.debug("oidOferta: " + oidOferta);

			Map params = new HashMap();
			params.put("oidOferta", this.oidOferta);
			params.put("oidRango", this.oidRango);
			List rangos = service.getRangoOfertaFactorRepeticionList(params);

			if (rangos != null && rangos.size() > 0) {
				Map rango = (Map) rangos.get(0);

				BeanUtils.copyProperties(gratisForm, rango);
			}

			// Lista
			List gratis = service
					.getRangoGratisOfertaFactorRepeticionList(params);
			this.pedOfertasPorFactorRepeticionRangosGratisList = gratis;
			this.dataModelGratisList = new DataTableModel(
					this.pedOfertasPorFactorRepeticionRangosGratisList);
			
			
			gratisForm.setCodigoPeriodo(this.codigoPeriodo);
			gratisForm.setOidRango(this.oidRango);

			gratisForm.setCuv("");
			gratisForm.setUnidades("");

			this.mostrarBotonGratisEliminar = this
					.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRangosGratisList);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
	}

	public boolean mostrarBotonGratisEliminar(List lista) {
		boolean mostrar = false;
		if (lista != null && lista.size() > 0) {
			mostrar = true;
		}
		return mostrar;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @return the verificaGrabado
	 */
	public String getVerificaGrabado() {
		return verificaGrabado;
	}


	/**
	 * @param verificaGrabado the verificaGrabado to set
	 */
	public void setVerificaGrabado(String verificaGrabado) {
		this.verificaGrabado = verificaGrabado;
	}


	/**
	 * @return the pedOfertasPorFactorRepeticionRangosGratisList
	 */
	public List getPedOfertasPorFactorRepeticionRangosGratisList() {
		return pedOfertasPorFactorRepeticionRangosGratisList;
	}


	/**
	 * @param pedOfertasPorFactorRepeticionRangosGratisList the pedOfertasPorFactorRepeticionRangosGratisList to set
	 */
	public void setPedOfertasPorFactorRepeticionRangosGratisList(
			List pedOfertasPorFactorRepeticionRangosGratisList) {
		this.pedOfertasPorFactorRepeticionRangosGratisList = pedOfertasPorFactorRepeticionRangosGratisList;
	}


	/**
	 * @return the mostrarBotonGratisEliminar
	 */
	public Boolean getMostrarBotonGratisEliminar() {
		return mostrarBotonGratisEliminar;
	}


	/**
	 * @param mostrarBotonGratisEliminar the mostrarBotonGratisEliminar to set
	 */
	public void setMostrarBotonGratisEliminar(Boolean mostrarBotonGratisEliminar) {
		this.mostrarBotonGratisEliminar = mostrarBotonGratisEliminar;
	}


	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}


	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}


	/**
	 * @return the oidRango
	 */
	public String getOidRango() {
		return oidRango;
	}


	/**
	 * @param oidRango the oidRango to set
	 */
	public void setOidRango(String oidRango) {
		this.oidRango = oidRango;
	}


	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}


	/**
	 * @return the precioUnitario
	 */
	public String getPrecioUnitario() {
		return precioUnitario;
	}


	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	/**
	 * @return the factorRepeticion
	 */
	public String getFactorRepeticion() {
		return factorRepeticion;
	}


	/**
	 * @param factorRepeticion the factorRepeticion to set
	 */
	public void setFactorRepeticion(String factorRepeticion) {
		this.factorRepeticion = factorRepeticion;
	}


	/**
	 * @return the dataModelGratisList
	 */
	public DataTableModel getDataModelGratisList() {
		return dataModelGratisList;
	}


	/**
	 * @param dataModelGratisList the dataModelGratisList to set
	 */
	public void setDataModelGratisList(DataTableModel dataModelGratisList) {
		this.dataModelGratisList = dataModelGratisList;
	}

	/**
	 * @return the beanGratisList
	 */
	public Object getBeanGratisList() {
		return beanGratisList;
	}


	/**
	 * @param beanGratisList the beanGratisList to set
	 */
	public void setBeanGratisList(Object beanGratisList) {
		this.beanGratisList = beanGratisList;
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminargratis(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminargratis' method");
		}
		String mensaje = "";
		try {

			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm)this.formBusqueda;
			HashMap objetosGratisSeleccionados = (HashMap) this.beanGratisList;
			if (objetosGratisSeleccionados != null) {
				String id = objetosGratisSeleccionados.get("oidGratis")
						.toString();
				// String[] seleccionados = gratisForm.getSelectedItems();

				if (!StringUtils.isBlank(id)) {
					String oidGratis = id;
					if (log.isDebugEnabled())
						log.debug("oidGratis: " + oidGratis);

					Map params = BeanUtils.describe(gratisForm);
					String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
					service.removeRangoGratisOfertaFactorRepeticion(oidGratis, codigoUsuario);

					// Lista
					List regalos = service
							.getRangoGratisOfertaFactorRepeticionList(params);
					this.pedOfertasPorFactorRepeticionRegalosList = regalos;
				}
				mensaje = this
						.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm.regalo.eliminado");
				this.addInfo("Info : ", mensaje);
				this.dataModelGratisList = new DataTableModel(
						this.pedOfertasPorFactorRepeticionRegalosList);
				this.mostrarBotonGratisEliminar = this
						.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRegalosList);
			} else {
				mensaje = this.getResourceMessage("errors.select.item");
				this.addWarn("Info : ", mensaje);
			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void agregargratis(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregargratis' method");
		}

		String mensaje = "";
		try {

			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm)this.formBusqueda;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			List regaloList = this.pedOfertasPorFactorRepeticionRegalosList;

			if (regaloList == null)
				regaloList = new ArrayList();

			Map regalo = new HashMap();
			regalo.put("oidRango", gratisForm.getOidRango());
			regalo.put("unidades", gratisForm.getUnidades());
			regalo.put("correlativo", regaloList.size() + 1);
			regalo.put("codigoUsuario", usuario.getLogin());
			
			if(StringUtils.isBlank(gratisForm.getCuv()))
			{
				mensaje = "CUV es un campo requerido";
				this.addError("Error : ", mensaje);

				return;
			}
			
			if(StringUtils.isBlank(gratisForm.getUnidades()))
			{
				mensaje = "Unidades a entregar es un campo requerido";
				this.addError("Error : ", mensaje);

				return;
			}

			// Buscamos el CUV
			Map params = new HashMap();
			params.put("codigoPeriodo", gratisForm.getCodigoPeriodo());
			params.put("cuv", gratisForm.getCuv());

			List cuvList = service.getCuvs(params);

			if (cuvList != null && cuvList.size() > 0) {
				Map cuv = (Map) cuvList.get(0);
				regalo.put("oidDetalleOferta",
						MapUtils.getString(cuv, "oidDetalleOferta"));
			} else {
				mensaje = this
						.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm.error.cuv.noexiste");
				this.addError("Error : ", mensaje);

				return;
			}
			
			//

			// Insertamos en BD
			service.insertRangoGratisOfertaFactorRepeticion(regalo);
			//

			regaloList = service
					.getRangoGratisOfertaFactorRepeticionList(regalo);
			this.pedOfertasPorFactorRepeticionRegalosList = regaloList;
			this.dataModelGratisList = new DataTableModel(
					this.pedOfertasPorFactorRepeticionRegalosList);

			this.mostrarBotonGratisEliminar = this
					.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRegalosList);

			gratisForm.setCuv("");
			gratisForm.setUnidades("");

			mensaje = this
					.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm.regalo.agregado");
			this.addInfo("Info : ", mensaje);
		} catch (Exception e) {
			this.mostrarBotonGratisEliminar = this
					.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRegalosList);

			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
}
