package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.pre.ProcesoPREEliminarMatrizFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pre.form.ProcesoPREEliminarMatrizFacturacionForm;

@SessionScoped
@ManagedBean
public class ProcesoPREEliminarMatrizFacturacionAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -8843847069733096901L;
	
	private List siccCatalogoList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPREEliminarMatrizFacturacionForm form = new ProcesoPREEliminarMatrizFacturacionForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Enter executeProcess method - ProcesoPREEliminarMatrizFacturacionAction");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		log.debug("Los parametros del Reporte en el executeProcess son: "+ params.toString());
		
		ProcesoPREEliminarMatrizFacturacionService service = (ProcesoPREEliminarMatrizFacturacionService)getBean("spusicc.procesoPREEliminarMatrizFacturacionService");
		service.executeEliminarMatrizFacturacion(params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Enter setViewAtributes method - ProcesoPREEliminarMatrizFacturacionAction");
		}
		
		ProcesoPREEliminarMatrizFacturacionForm f = (ProcesoPREEliminarMatrizFacturacionForm) this.formProceso;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		this.siccCatalogoList = reporteService.getListaGenerico("getCatalogoProductos", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarProceso()
	 */
	@Override
	public String setValidarProceso() {
		if(log.isDebugEnabled()){
			log.debug("Enter setValidarProceso method - ProcesoPREEliminarMatrizFacturacionAction");
		}
		
		ProcesoPREEliminarMatrizFacturacionForm f = (ProcesoPREEliminarMatrizFacturacionForm) this.formProceso;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		
		String mensaje = "";
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodoMatriz", f.getCodigoPeriodo());
		
		Integer cuvFact = service.getExisteCUVFacturado(criteria);
		if(cuvFact > 0){
			mensaje = "No se puede eliminar Matriz de Facturación ya que cuenta con CUVs facturados";
		}
		
		return mensaje;
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}
}