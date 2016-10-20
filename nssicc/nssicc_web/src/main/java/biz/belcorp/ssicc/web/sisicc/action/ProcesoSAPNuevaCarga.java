package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sap.ProcesoSAPNuevaCargaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.sisicc.form.ProcesoSAPNuevaCargaForm;

@SessionScoped
@ManagedBean
public class ProcesoSAPNuevaCarga extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5641216342572257004L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoSAPNuevaCargaForm form = new ProcesoSAPNuevaCargaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoSAPNuevaCargaForm f = (ProcesoSAPNuevaCargaForm) this.formProceso;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		ProcesoSAPNuevaCargaService procesoSAPNuevaCargaService = (ProcesoSAPNuevaCargaService) getBean("spusicc.procesoSAPNuevaCargaService");
		
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("fechaInicio",f.getFechaInicio());
		criteria.put("fechaFinal",f.getFechaFin());
		
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		
		if (f.getTipoCambio().equalsIgnoreCase("T"))
			criteria.put("tipoCambio","");
		else
			criteria.put("tipoCambio",f.getTipoCambio());
			
		procesoSAPNuevaCargaService.executeUpdateRegistros(criteria);
	
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing action : setViewAttributes.");
//		Obteniendo valores de la sesion
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoUsuario= usuario.getLogin();
		String codigoPais   =  pais.getCodigo();
		
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", codigoUsuario);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
//		request.setAttribute("flagTipoCambioDefecto", "1");
		
	}

}
