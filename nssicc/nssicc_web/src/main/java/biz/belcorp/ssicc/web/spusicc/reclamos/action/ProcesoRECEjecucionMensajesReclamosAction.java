package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECEjecucionMensajesReclamosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.sisicc.form.ProcesoRUVReprocesarInterfaseForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECEjecucionMensajesReclamosForm;


@ManagedBean
@SessionScoped
public class ProcesoRECEjecucionMensajesReclamosAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -1693066531893660281L;
	
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRECEjecucionMensajesReclamosForm procesoForm =new ProcesoRECEjecucionMensajesReclamosForm();		
		return procesoForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {	
		
    	
    	ProcesoRECEjecucionMensajesReclamosService service = (ProcesoRECEjecucionMensajesReclamosService) getBean("spusicc.procesoRECEjecucionMensajesReclamosService");    	
    	ProcesoRECEjecucionMensajesReclamosForm f = ( ProcesoRECEjecucionMensajesReclamosForm)this.formProceso;
    	
    	Map criteria = params;
    	String codigoPeriodo =f.getCodigoPeriodo();
    	params.put("codigoPeriodo", codigoPeriodo); 	
    		    		
    	service.executeRECEjecucionMensajesReclamos(criteria);    
    	return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList= service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		MantenimientoOCRPedidoControlFacturacionService servicePedidos = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = servicePedidos.getControlFacturacionById(criteria);
		
		ProcesoRECEjecucionMensajesReclamosForm f = ( ProcesoRECEjecucionMensajesReclamosForm)this.formProceso;
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
				
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
	

}
