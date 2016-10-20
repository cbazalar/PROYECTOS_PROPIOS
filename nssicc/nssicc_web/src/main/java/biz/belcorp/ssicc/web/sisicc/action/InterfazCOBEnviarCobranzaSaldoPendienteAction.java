package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarCobranzaSaldoPendienteForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazCOBEnviarCobranzaSaldoPendienteAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 5737338555694931929L;
	private List siccSociedadList = new ArrayList();	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		this.siccSociedadList =  service	.getSociedadesByCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		InterfazCOBEnviarCobranzaSaldoPendienteForm f = (InterfazCOBEnviarCobranzaSaldoPendienteForm)this.formInterfaz;
		f.setAnho(sdf1.format(new Date(System.currentTimeMillis())));
		f.setMes(sdf2.format(new Date(System.currentTimeMillis())));
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{
		InterfazCOBEnviarCobranzaSaldoPendienteForm f = (InterfazCOBEnviarCobranzaSaldoPendienteForm)this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		params = super.prepareParamsBeforeExecute(params,form);

		params.put("codigoSociedad", f.getCodigoSociedad());
		params.put("anho",f.getAnho());
		params.put("mes",f.getMes());

		params.put("sociedad",service.getSociedadEquivalenciaSAP(params));
		
		String titulo   = new String();
		String titulo1  = new String();
		String titulo2  = new String();
		String titulo3  = new String();
		String titulo4  = new String();
		String titulo5  = new String();
		String titulo6  = new String();
		String titulo7  = new String();
		String titulo8  = new String();
		String titulo9  = new String();
		String titulo10 = new String();
		String titulo11 = new String();
		String titulo12 = new String();
		String titulo13 = new String();

		
		titulo1  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.ejercicio");
		titulo2  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.perCont");
		titulo3  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.codPeri");
		titulo4  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.socFI");
		titulo5  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.paisSAP");
		titulo6  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.canal");
		titulo7  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.codRegion");
		titulo8  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.codZona");
		titulo9  = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.tCob");
		titulo10 = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.tSaldo");
		titulo11 = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.fecProceso");
		titulo12 = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.fecFactIni");
		titulo13 = this.getResourceMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.fecFactFin");

		titulo = titulo1 + ";" + titulo2 + ";" + titulo3 + ";" + titulo4 + ";" + titulo5 + ";" + titulo6 + ";" + titulo7 + ";" +
			     titulo8 + ";" + titulo9 + ";" + titulo10 + ";" + titulo11 + ";" + titulo12 + ";" + titulo13;

		params.put("titulo", titulo);
		
		if (log.isDebugEnabled()) {
				log.debug(params.toString() );
		}

		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOBEnviarCobranzaSaldoPendienteForm form = new InterfazCOBEnviarCobranzaSaldoPendienteForm();
		return form;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}	
}