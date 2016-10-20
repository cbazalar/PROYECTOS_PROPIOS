package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPRYProyeccionFaltanteDiaDetalleForm;

@ManagedBean  
@SessionScoped
public class ProcesoPRYProyeccionFaltanteDiaPopupAction extends BasePopupAbstractAction{
	
	private static final long serialVersionUID = -6048435060620880744L;
	
	private List detallePRYProyeccionFaltanteList = new ArrayList();
	private DataTableModel dtDetallePRYProyeccionFaltanteList; 
	
	

	/**
	 * @return the detallePRYProyeccionFaltanteList
	 */
	public List getDetallePRYProyeccionFaltanteList() {
		return detallePRYProyeccionFaltanteList;
	}

	/**
	 * @param detallePRYProyeccionFaltanteList the detallePRYProyeccionFaltanteList to set
	 */
	public void setDetallePRYProyeccionFaltanteList(
			List detallePRYProyeccionFaltanteList) {
		this.detallePRYProyeccionFaltanteList = detallePRYProyeccionFaltanteList;
	}

	/**
	 * @return the dtDetallePRYProyeccionFaltanteList
	 */
	public DataTableModel getDtDetallePRYProyeccionFaltanteList() {
		return dtDetallePRYProyeccionFaltanteList;
	}

	/**
	 * @param dtDetallePRYProyeccionFaltanteList the dtDetallePRYProyeccionFaltanteList to set
	 */
	public void setDtDetallePRYProyeccionFaltanteList(
			DataTableModel dtDetallePRYProyeccionFaltanteList) {
		this.dtDetallePRYProyeccionFaltanteList = dtDetallePRYProyeccionFaltanteList;
	}
	
	
	/**
	 * Generación del detalle de Resultado de la Proyeccion de Faltante x Producto
	 * @param event
	 */
	public void detalle() { 
		try {
			
			
			
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();

			String codigoGrupo = externalContext.getRequestParameterMap().get("codigoGrupo");
			String numeroVersion = externalContext.getRequestParameterMap().get("numeroVersion");
			String fechaFacturacion = externalContext.getRequestParameterMap().get("fechaFacturacion");
			String codigoPeriodo = externalContext.getRequestParameterMap().get("codigoPeriodo");
			String descripcionGrupo = externalContext.getRequestParameterMap().get("descripcionGrupo");
			String descripcionPeriodo = externalContext.getRequestParameterMap().get("descripcionPeriodo");
			String totalSolicitud = externalContext.getRequestParameterMap().get("totalSolicitud");
			
			/* obteniendo valores*/
			String codigoSistema = Constants.PRY_CODIGO_SISTEMA;
			
			/* Grabando en el form */
			ProcesoPRYProyeccionFaltanteDiaDetalleForm f = (ProcesoPRYProyeccionFaltanteDiaDetalleForm) this.formBusqueda;
//			f.setCodigoSistema(codigoSistema);
			f.setCodigoGrupo(codigoGrupo);
			f.setNumeroVersion(Integer.parseInt(numeroVersion));
			f.setFechaFacturacion(fechaFacturacion);
			f.setDescripcionGrupo(descripcionGrupo);
			f.setDescripcionPeriodo(descripcionPeriodo);
			f.setCodigoPeriodo(codigoPeriodo);
			f.setTotalSolicitud(Integer.parseInt(totalSolicitud));
			
			Map criteria = BeanUtils.describe(f);
			criteria.put("codigoSistema", codigoSistema);
			/* obteniendo detalle */
			this.detallePRYProyeccionFaltanteList.clear();
			dtDetallePRYProyeccionFaltanteList = new DataTableModel(detallePRYProyeccionFaltanteList);
			
			ProcesoPRYProyeccionFaltanteDiaService serviceProy = (ProcesoPRYProyeccionFaltanteDiaService) 
		      	getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
			
			List lista = serviceProy.getProyeccionFaltanteProducto(criteria);
			dtDetallePRYProyeccionFaltanteList = new DataTableModel(lista);


		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Generación del detalle de Resultado de la Proyeccion de Faltante x Producto
	 * @param event
	 */
	public void generaldetalle(){

		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			
			String fechaFacturacion = externalContext.getRequestParameterMap().get("fechaFacturacion");
			String codigoPeriodo = externalContext.getRequestParameterMap().get("codigoPeriodo");
			String totalSolicitud = externalContext.getRequestParameterMap().get("totalSolicitud");
			String numeroVersion = externalContext.getRequestParameterMap().get("numeroVersion");
			
			
			
			/* obteniendo valores*/
			String codigoSistema = Constants.PRY_CODIGO_SISTEMA;
			
			/* Grabando en el form */
			ProcesoPRYProyeccionFaltanteDiaDetalleForm f = (ProcesoPRYProyeccionFaltanteDiaDetalleForm) this.formBusqueda;
//			f.setCodigoSistema(codigoSistema);
			f.setFechaFacturacion(fechaFacturacion);
			f.setCodigoPeriodo(codigoPeriodo);
			f.setTotalSolicitud(Integer.parseInt(totalSolicitud));
			f.setNumeroVersion(Integer.parseInt(numeroVersion));
			f.setDescripcionGrupo("General");
			f.setDescripcionPeriodo("");
			
			Map criteria = BeanUtils.describe(f);
			criteria.put("codigoSistema", codigoSistema);
			/* obteniendo detalle */
			this.detallePRYProyeccionFaltanteList.clear();
			dtDetallePRYProyeccionFaltanteList = new DataTableModel(detallePRYProyeccionFaltanteList);
			
			ProcesoPRYProyeccionFaltanteDiaService serviceProy = (ProcesoPRYProyeccionFaltanteDiaService) 
		      	getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
			
			List lista = serviceProy.getProyeccionFaltanteProductoTodos(criteria);
			dtDetallePRYProyeccionFaltanteList = new DataTableModel(lista);
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoPRYProyeccionFaltanteDiaDetalleForm form = new ProcesoPRYProyeccionFaltanteDiaDetalleForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
	}


	
	

}
