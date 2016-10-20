/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazEVIEnviarCabecerasPedidosDigitadosForm;


@ManagedBean
@SessionScoped
public class InterfazEVIEnviarCabecerasPedidosDigitadosAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazEVIEnviarCabecerasPedidosDigitadosForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		//Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
        InterfazEVIEnviarCabecerasPedidosDigitadosForm enviarCabecerasPedidosDigitadosForm = (InterfazEVIEnviarCabecerasPedidosDigitadosForm)this.formInterfaz; 
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
        enviarCabecerasPedidosDigitadosForm.setFechaFacturacion(controlFacturacion.getFechaProceso());
        enviarCabecerasPedidosDigitadosForm.setPeriodo(controlFacturacion.getCodigoPeriodo());

	}

	
	

	
    

}
