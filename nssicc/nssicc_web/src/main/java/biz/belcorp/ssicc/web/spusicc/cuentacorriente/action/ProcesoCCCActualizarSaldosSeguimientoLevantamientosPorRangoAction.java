package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108061335395996282L;
	private String codigoProcesoBatch = "";
	private String codigoSistema = "";
	private List siccSociedadList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoForm p = new ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoForm();
		return p;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
				
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoForm f = (ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoForm) this.formProceso;
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService service = (ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService) 
														getBean("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoSociedad",f.getCodigoSociedad());
		criteria.put("campanaInicio",f.getCodigoPeriodoInicial());
		criteria.put("campanaFin",f.getCodigoPeriodoFinal());
		criteria.put("codigoTipoEjecucion","DEP");	
		
		List periodos= service.getRangoPeriodos(criteria);
		
		
		Map criteriaFC = new HashMap();
		criteriaFC.put("codigoPais", pais.getCodigo());
		criteriaFC.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteriaFC.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		criteriaFC.put("estado", Constants.ESTADO_ACTIVO);		
        
		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		PedidoControlFacturacion controlFacturacion = serviceCF.getControlFacturacionById(criteriaFC);
		
		for(int i=0; i<periodos.size(); i++) {
			Base basePeriodo = (Base)periodos.get(i);
			
			if(basePeriodo.getDescripcion().equals(controlFacturacion.getCodigoPeriodo())){
				criteria.put("codigoPeriodo",basePeriodo.getDescripcion());	
				service.executeProcesarSaldosActual(criteria); 
			}else{
				criteria.put("codigoPeriodo",basePeriodo.getDescripcion());	
				service.executeProcesarSaldosSeguimientoLevantamientosPorRango(criteria); 
			}
			
		}
		
		
		return params;
	
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		this.mostrarBotonExecute = true;
		this.siccSociedadList = svc.getSociedadesByCodigoPais(pais.getCodigo());
	
	}

	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
	
	
	
}
