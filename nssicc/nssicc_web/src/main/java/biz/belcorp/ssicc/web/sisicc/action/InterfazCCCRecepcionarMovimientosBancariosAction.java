package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCCCRecepcionarMovimientosBancariosForm;

@ManagedBean
@SessionScoped
public class InterfazCCCRecepcionarMovimientosBancariosAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7128539098841513359L;
	boolean estadoInterface;
	private List siccSociedadList; 
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazCCCRecepcionarMovimientosBancariosForm formInterfaz = new InterfazCCCRecepcionarMovimientosBancariosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazCCCRecepcionarMovimientosBancariosForm f = (InterfazCCCRecepcionarMovimientosBancariosForm) this.formInterfaz;
		// Obtenemos los beans básicos de la sesión
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por Pais
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaGenerar(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaGenerarDate(DateUtil.convertStringToDate(f.getFechaGenerar()));
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazCCCRecepcionarMovimientosBancariosForm f = (InterfazCCCRecepcionarMovimientosBancariosForm) this.formInterfaz;
		f.setFechaGenerar(DateUtil.convertDateToString(f.getFechaGenerarDate()));
		
		ConsultaCCCGenericoService service = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String numeroLoteInterno;
		Map criteria = new HashMap();

		service.getNumeroLote(criteria);

		numeroLoteInterno = criteria.get("numeroLote").toString();

		if (log.isDebugEnabled()) {
			log.debug(numeroLoteInterno);
		}

		params = super.prepareParamsBeforeExecute(params, form);
		params.put("numeroLoteInterno", numeroLoteInterno);
		params.put("codigoUsuario", usuario.getLogin());

		return params;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception{
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (log.isDebugEnabled()) {
			log.debug("JFA getExecuteForward' method");
		}
						
		estadoInterface = interfazExecutionResult.ejecucionCompletada();
		
		if (log.isDebugEnabled()) {			
			log.debug( new Boolean(estadoInterface).toString()) ;
		}										
	}	
	
	
	
	/**
	 * @return the estadoInterface
	 */
	public boolean isEstadoInterface() {
		return estadoInterface;
	}

	/**
	 * @param estadoInterface the estadoInterface to set
	 */
	public void setEstadoInterface(boolean estadoInterface) {
		this.estadoInterface = estadoInterface;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
}