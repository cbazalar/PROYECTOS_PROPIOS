package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
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
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSABInterfacesFuenteVentasRealForm;

@ManagedBean
@SessionScoped
public class InterfazSABInterfacesFuenteVentasRealAction extends
		BaseInterfazAbstractAction {

	private List siccMarcaList = new  ArrayList();
	private List siccCanalList = new  ArrayList();
	
	private static final long serialVersionUID = 3629799014066947312L;

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccMarcaList = svc.getMarcas();
		siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		InterfazSABInterfacesFuenteVentasRealForm f = (InterfazSABInterfacesFuenteVentasRealForm) this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
				Constants.CODIGO_CANAL_DEFAULT));


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
		MantenimientoOCRPedidoControlFacturacionService servicePedido = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = servicePedido
				.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		 f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		 f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		

		//log.debug(getViewForward());
		
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSABInterfacesFuenteVentasRealForm form =  new InterfazSABInterfacesFuenteVentasRealForm();
		return form;
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{
		params = super.prepareParamsBeforeExecute(params,form);		
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
}