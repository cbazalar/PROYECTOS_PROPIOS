package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERGeneracionCtaCteDocumentoLegalService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

@ManagedBean
@SessionScoped
public class ProcesoPERGeneracionCtaCteDocumentoLegalAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7887034311729808722L;
	private String tipOrigenDatos;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoPERGeneracionCtaCteDocumentoLegalForm form=new ProcesoPERGeneracionCtaCteDocumentoLegalForm();
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPERGeneracionCtaCteDocumentoLegalForm form=(ProcesoPERGeneracionCtaCteDocumentoLegalForm)this.formInterfaz;
		form.setNumeroLote(null);
		this.tipOrigenDatos = (String) this.parametrosPantalla.get("tipOrigenDatos");
		this.validarListaInterfaceSalida = false;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		params=super.prepareParamsBeforeExecute(params, form);
		params.put("tipOrigenDatos", this.tipOrigenDatos);
		return params;
	}
	
	
	protected Map<String, Object> executeProcessBeforeInterfaz(Map<String, Object> params) throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = (Usuario) params.get("usuario");
		String codigo   = (String) params.get("codigoInterfaz");
		String tipo     = (String) params.get("tipOrigenDatos"); 
		String exito    = "-1";
		params.put("exito", exito);

		InterfazService interfazService = (InterfazService) this.getBean("sisicc.interfazService");
		InterfazPK interfazPK = new InterfazPK(pais.getCodigo(), codigo
				.substring(0, 3), codigo);
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		params.put("descripcion", interfaz.getDescripcion());

		ProcesoPERGeneracionCtaCteDocumentoLegalService service = (ProcesoPERGeneracionCtaCteDocumentoLegalService) getBean("spusicc.procesoPERGeneracionCtaCteDocumentoLegalService");
		Map resultado = service.executeGeneracionCtaCteDocumentoLegal(pais,	usuario, codigo, tipo);
		params.put("numeroLote", resultado.get("numeroLote"));
		params.put("numeroLoteSolicitud", resultado.get("numeroLote"));
		params.put("codigoTipoOrigenDatos", resultado.get("codigoTipoOrigenDatos"));
		log.debug("Mostrando el numero de Lote " + resultado.get("numeroLote"));
		exito = "1";
		params.put("exito", exito);	
		return params;
	}
	
	

}
