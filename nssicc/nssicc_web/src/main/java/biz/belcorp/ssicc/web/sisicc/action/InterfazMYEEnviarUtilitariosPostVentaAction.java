/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarUtilitariosPostVentaForm;


@ManagedBean
@SessionScoped
public class InterfazMYEEnviarUtilitariosPostVentaAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;
    private List siccTipoClienteList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazMYEEnviarUtilitariosPostVentaForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		siccMarcaList = svc.getMarcas();		
		siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccTipoClienteList = svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());	
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
		InterfazMYEEnviarUtilitariosPostVentaForm f = (InterfazMYEEnviarUtilitariosPostVentaForm)this.formInterfaz;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		if (StringUtils.isBlank(f.getCodigoPeriodo())){
			String codigoPeriodoActual = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyyyMM");
			f.setCodigoPeriodo(codigoPeriodoActual);
		}
		
		f.setFechaFacturacion(new Date(System.currentTimeMillis()));
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	    f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	    f.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
 
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		InterfazMYEEnviarUtilitariosPostVentaForm f = (InterfazMYEEnviarUtilitariosPostVentaForm) this.formInterfaz;
		
		params = super.prepareParamsBeforeExecute(params, form);
		Date fecha = f.getFechaFacturacion();
		
		params.put("fechaFacturacion", DateFormatUtils.format(fecha, "dd/MM/yyyy"));
		
	   return params;
	}
	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	
	
	
	

	
    

}
