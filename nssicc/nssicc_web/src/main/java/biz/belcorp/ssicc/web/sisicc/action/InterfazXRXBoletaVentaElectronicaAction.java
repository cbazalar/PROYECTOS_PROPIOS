package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazXRXBoletaVentaElectronicaForm;

@ManagedBean
@SessionScoped
public class InterfazXRXBoletaVentaElectronicaAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 2657051535312317756L;
	
	private List xrxTipoRecepcionList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazXRXBoletaVentaElectronicaForm interfazForm= new InterfazXRXBoletaVentaElectronicaForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("Debug. Entry my method");		
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		this.xrxTipoRecepcionList=interfazSiCCService.getTiposRecepcionXRX(Constants.XRX_CODIGO_INTERFAZ_XRX1, Constants.XRX_CODIGO_INTERFAZ_XRX2);		
		InterfazXRXBoletaVentaElectronicaForm f = (InterfazXRXBoletaVentaElectronicaForm) this.formInterfaz ; 
   
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();
		f.setFechaProcesoDate(DateUtil.convertStringToDate(fecha));

	}	
	

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazXRXBoletaVentaElectronicaForm f = (InterfazXRXBoletaVentaElectronicaForm) this.formInterfaz;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params = super.prepareParamsBeforeExecute(params, form);
		
		params.put("codigoInterfaz", f.getTipoRecepcion());		
		params.put("fechaProcesoArchivo", DateUtil.convertDateToString("yyyyMMdd", f.getFechaProcesoDate()));
		params.put("fechaProceso", DateUtil.convertDateToString("yyyyMMdd", f.getFechaProcesoDate()));
		String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		params.put("fechaProcesoHora", hora);	
		params.put("usuario",usuario);	
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);		
		return params;
	}	

	/**
	 * @param val
	 */
	public void showCargarInterfaz(ValueChangeEvent val) {

		log.debug(">>showCargarInterfaz ");
		String codigoInterfaz =  val.getNewValue().toString();
		InterfazXRXBoletaVentaElectronicaForm f = (InterfazXRXBoletaVentaElectronicaForm) this.formInterfaz;
		
		this.parametrosPantalla = new HashMap();
		this.parametrosPantalla.put("codigoInterfaz", codigoInterfaz);
		this.parametrosPantalla.put("tipoRecepcion", codigoInterfaz);
		this.parametrosPantalla.put("codigoProcesoBatch", f.getCodigoProcesoBatch());
		try {
			this.setBeforeViewAtributes();
			this.setViewAtributes();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}	

	public List getXrxTipoRecepcionList() {
		return xrxTipoRecepcionList;
	}

	public void setXrxTipoRecepcionList(List xrxTipoRecepcionList) {
		this.xrxTipoRecepcionList = xrxTipoRecepcionList;
	}
	

}
