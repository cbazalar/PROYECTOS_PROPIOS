package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.service.scdf.PremioService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPRIEnviarArchivosOCRForm;

@SessionScoped
@ManagedBean
public class InterfazPRIEnviarArchivosOCRAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4888125645881478923L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazPRIEnviarArchivosOCRForm form = new InterfazPRIEnviarArchivosOCRForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
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
		MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		List periodos = serviceMantPedidoCtrlFact
				.getControlFacturacionByCriteria(criteria);

		// Obtenemos uno de los periodos de la lista
		if (periodos != null && periodos.size() > 0) {
			PedidoControlFacturacion controlFacturacion = (PedidoControlFacturacion) periodos
					.get(0);
			InterfazPRIEnviarArchivosOCRForm actionForm = (InterfazPRIEnviarArchivosOCRForm) this.formInterfaz;
			actionForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		InterfazPRIEnviarArchivosOCRForm f = (InterfazPRIEnviarArchivosOCRForm) this.formInterfaz;

		params = super.prepareParamsBeforeExecute(params, form);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String codigoPais = pais.getCodigo();

		params.put("codigoPais", codigoPais);
		params.put("usuario", usuario);
		params.put("codigoPeriodo", f.getCodigoPeriodo());

		return params;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'beforeExecuteInterfaz'");
            log.debug(params);
        }
        
        // Cargamos la información de las tablas usadas por el SCdF desde SiCC
        String codigoPais = MapUtils.getString(params, "codigoPais");
        String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo");
        Usuario usuario = (Usuario)MapUtils.getObject(params, "usuario");
        
        if(log.isDebugEnabled()) {
            log.debug("Codigo de Pais: " + codigoPais);
        }

        // Obtenemos la referencia al service
        InterfazSiCCService siccService = (InterfazSiCCService) getBean("scdf.interfazSiCCService");
        PaisService paisService = (PaisService)getBean("paisService");
        Pais pais = paisService.getPais(codigoPais);

        if(log.isDebugEnabled()) {
            log.debug("Realizando la carga de la información desde SICC ...");
        }
        siccService.executeCargaSiCC(pais.getCodigo(), codigoPeriodo, usuario.getLogin());
        if(log.isDebugEnabled()) {
            log.debug("Carga de informacion finalizada.");
        }
        
        // Realizamos el procesamiento de los premios
        PremioService premioService = (PremioService) getBean("scdf.premioService");
        if(log.isDebugEnabled()) {
            log.debug("Realizando el procesamiento de premios ...");
        }
        premioService.executeProcesamientoPremios(pais, usuario);
        if(log.isDebugEnabled()) {
            log.debug("Procesamiento de premios finalizado.");
        }
    }
	

}
