package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionSAPFIService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGenerarInformacionSAPFIForm;

@SessionScoped
@ManagedBean
public class ProcesoCCCGenerarInformacionSAPFIAction extends BaseProcesoAbstractAction{

	private String codigoModulo;
	private String codigoInterface;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1727484125813360390L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGenerarInformacionSAPFIForm form = new ProcesoCCCGenerarInformacionSAPFIForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.codigoModulo = (String) this.parametrosPantalla.get("codigoModulo");
		this.codigoInterface = (String) this.parametrosPantalla.get("codigoInterface");
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGenerarInformacionSAPFIForm f = (ProcesoCCCGenerarInformacionSAPFIForm) this.formProceso;
		f.setFechaProcesoHasta(DateUtil.convertDateToString(f.getFechaProcesoHastaD()));
		ProcesoCCCGenerarInformacionSAPFIService service = (ProcesoCCCGenerarInformacionSAPFIService)getBean("spusicc.procesoCCCGenerarInformacionSAPFIService");
		params.put("codigoModulo", this.codigoModulo);
		params.put("codigoInterface", this.codigoInterface);
		params.put("fechaProcesoHasta", DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaProcesoHastaD()));
		service.executeGenerarInformacionSAPFI(params);
		return params;
	}

	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}

	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	/**
	 * @return the codigoInterface
	 */
	public String getCodigoInterface() {
		return codigoInterface;
	}

	/**
	 * @param codigoInterface the codigoInterface to set
	 */
	public void setCodigoInterface(String codigoInterface) {
		this.codigoInterface = codigoInterface;
	}

}
