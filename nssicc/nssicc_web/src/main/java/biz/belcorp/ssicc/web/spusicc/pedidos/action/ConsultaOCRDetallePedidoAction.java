package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.RecepcionPedido;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ConsultaOCRRecepcionPedidosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPSecuenciarZonaTerritorioPopupForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ConsultaOCRDetallePedidoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ConsultaOCRRecepcionPedidosForm;
import biz.belcorp.ssicc.web.spusicc.sto.action.MantenimientoSTOOrdenCompraCabeceraAction;

@SessionScoped
@ManagedBean
public class ConsultaOCRDetallePedidoAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8271679937013922665L;
	private List ocrRecepcionPedidosList;
	private List ocrDetallePedidoList;
	private DataTableModel detallePopupTableModel;

	protected void inicializarValores() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		ConsultaOCRDetallePedidoForm f = (ConsultaOCRDetallePedidoForm) this.formBusqueda;
		ConsultaOCRRecepcionPedidosService service = (ConsultaOCRRecepcionPedidosService) getBean("spusicc.pedidos.consultaOCRRecepcionPedidosService");
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String id = externalContext.getRequestParameterMap().get("id")
				.toString();
		// String id = f.getCodigoCliente()
		List pedidosList = this.getOcrRecepcionPedidosList();// (List)consultaORCRRecepcionPedidos.getOcrRecepcionPedidosList();
																// //request.getSession().getAttribute(Constants.OCR_RECEPCION_PEDIDOS_LIST)
		RecepcionPedido pedido = (RecepcionPedido) pedidosList.get(Integer.parseInt(id));
		f.setCodigoCliente(pedido.getCodigoCliente());
		f.setNombreCliente(pedido.getNombreCliente());
		f.setDescripcionRegion(pedido.getDescripcionRegion());
		f.setDescripcionZona(pedido.getDescripcionZona());
		f.setNumeroLote(pedido.getNumeroLote());
		f.setCodigoSeccion(pedido.getDescripcionSeccion());
		f.setFechaRecepcion(pedido.getFechaRecepcion());

		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", pedido.getCodigoPeriodo());
		criteria.put("codigoCliente", pedido.getCodigoCliente());
		criteria.put("numeroLote", pedido.getNumeroLote());

		List lista = new ArrayList();
		if (pedido.getTipoConsulta().compareToIgnoreCase("0") == 0) {
			lista = service.getDetalleRecepcionPedidosHistorico(criteria);
		} else {
			lista = service.getDetalleRecepcionPedidosActual(criteria);
		}

		ocrRecepcionPedidosList = null;
		ocrRecepcionPedidosList = lista;
       this.detallePopupTableModel=new DataTableModel(lista) ;
		String ventana = "PF('dialogMantenimientoDetalleRecepcion').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		return;
	}

	// metodo que sale del popup
	public void salirPopup(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering my method 'salirUA'");
			}

			String ventana = "PF('dialogMantenimientoDetalleRecepcion').hide()";
			this.getRequestContext().execute(ventana);
			this.mostrarBotonSalir = true;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

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
		ConsultaOCRDetallePedidoForm searchForm = new ConsultaOCRDetallePedidoForm();
		
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the ocrRecepcionPedidosList
	 */
	public List getOcrRecepcionPedidosList() {
		return ocrRecepcionPedidosList;
	}

	/**
	 * @param ocrRecepcionPedidosList
	 *            the ocrRecepcionPedidosList to set
	 */
	public void setOcrRecepcionPedidosList(List ocrRecepcionPedidosList) {
		this.ocrRecepcionPedidosList = ocrRecepcionPedidosList;
	}

	/**
	 * @return the ocrDetallePedidoList
	 */
	public List getOcrDetallePedidoList() {
		return ocrDetallePedidoList;
	}

	/**
	 * @param ocrDetallePedidoList
	 *            the ocrDetallePedidoList to set
	 */
	public void setOcrDetallePedidoList(List ocrDetallePedidoList) {
		this.ocrDetallePedidoList = ocrDetallePedidoList;
	}

	/**
	 * @return the detallePopupTableModel
	 */
	public DataTableModel getDetallePopupTableModel() {
		return detallePopupTableModel;
	}

	/**
	 * @param detallePopupTableModel
	 *            the detallePopupTableModel to set
	 */
	public void setDetallePopupTableModel(DataTableModel detallePopupTableModel) {
		this.detallePopupTableModel = detallePopupTableModel;
	}

}
