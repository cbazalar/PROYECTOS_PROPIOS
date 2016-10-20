package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scdf.ProductoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ConsultaPREMatrizFacturacionNulosDuplicadosForm;

@ManagedBean
@SessionScoped
public class ConsultaPREMatrizFacturacionNulosDuplicadosAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = -8271679937013922665L;
	
	private List preMatrizFacturacionNulosDuplicadosList;
    
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaPREMatrizFacturacionNulosDuplicadosForm f = new ConsultaPREMatrizFacturacionNulosDuplicadosForm();
		
		return f;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes()... - ConsultaPREMatrizFacturacionNulosDuplicadosAction");
		}
		
		ConsultaPREMatrizFacturacionNulosDuplicadosForm f = (ConsultaPREMatrizFacturacionNulosDuplicadosForm) formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", f.getCodigoPais());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes()... - ConsultaPREMatrizFacturacionNulosDuplicadosAction");
		}
		
		ConsultaPREMatrizFacturacionNulosDuplicadosForm f = (ConsultaPREMatrizFacturacionNulosDuplicadosForm) formBusqueda;
		ProductoService service = (ProductoService) getBean("scdf.productoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("oidPeriodo", oidPeriodo);
		
		List lista = service.getProductoStockList(criteria);
		this.preMatrizFacturacionNulosDuplicadosList = lista;

		return lista;
	}

	/**
	 * @return the preMatrizFacturacionNulosDuplicadosList
	 */
	public List getPreMatrizFacturacionNulosDuplicadosList() {
		return preMatrizFacturacionNulosDuplicadosList;
	}

	/**
	 * @param preMatrizFacturacionNulosDuplicadosList the preMatrizFacturacionNulosDuplicadosList to set
	 */
	public void setPreMatrizFacturacionNulosDuplicadosList(List preMatrizFacturacionNulosDuplicadosList) {
		this.preMatrizFacturacionNulosDuplicadosList = preMatrizFacturacionNulosDuplicadosList;
	}
}