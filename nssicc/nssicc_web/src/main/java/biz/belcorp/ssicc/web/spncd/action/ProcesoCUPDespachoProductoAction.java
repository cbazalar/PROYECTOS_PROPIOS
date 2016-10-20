package biz.belcorp.ssicc.web.spncd.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spncd.form.ProcesoCUPDespachoProductoForm;

@SessionScoped
@ManagedBean
public class ProcesoCUPDespachoProductoAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6909321956834916612L;
	private List cupProgramasList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCUPDespachoProductoForm form = new ProcesoCUPDespachoProductoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ejecutar' method");
		}
		MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		dsctoService.executeProcesoCUPDespachoProductos(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		ProcesoCUPDespachoProductoForm f = (ProcesoCUPDespachoProductoForm) this.formProceso;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		f.setFechaFact(controlFacturacion.getFechaProceso());
		f.setFechaFactD(DateUtil.convertStringToDate(f.getFechaFact()));
		f.setPeriodo(controlFacturacion.getCodigoPeriodo());

		MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Map map = new HashMap();
		map.put("codigoPais", pais.getCodigo());
		this.cupProgramasList = dsctoService.getProgramasDescuentosbyPais(map);

	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList
	 *            the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}

}
