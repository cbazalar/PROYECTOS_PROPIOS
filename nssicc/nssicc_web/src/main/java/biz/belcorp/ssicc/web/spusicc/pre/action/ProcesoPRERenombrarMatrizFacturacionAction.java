package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pre.form.ProcesoPRERenombrarMatrizFacturacionForm;

@SessionScoped
@ManagedBean
public class ProcesoPRERenombrarMatrizFacturacionAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -8843847069733096901L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPRERenombrarMatrizFacturacionForm form = new ProcesoPRERenombrarMatrizFacturacionForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Enter setViewAtributes method - ProcesoPRERenombrarMatrizFacturacionAction");
		}
		
		ProcesoPRERenombrarMatrizFacturacionForm f = (ProcesoPRERenombrarMatrizFacturacionForm) this.formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Enter executeProcess method - ProcesoPRERenombrarMatrizFacturacionAction");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		log.debug("Los parametros en el executeProcess son: "+ params.toString());
		
		ProcesoPRERenombrarMatrizFacturacionService service = (ProcesoPRERenombrarMatrizFacturacionService)getBean("spusicc.procesoPRERenombrarMatrizFacturacionService");
		service.executeRenombrarMatrizFacturacion(params);
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarProceso()
	 */
	@Override
	public String setValidarProceso() {
		if(log.isDebugEnabled()){
			log.debug("Enter setValidarProceso method - ProcesoPRERenombrarMatrizFacturacionAction");
		}
		
		ProcesoPRERenombrarMatrizFacturacionForm f = (ProcesoPRERenombrarMatrizFacturacionForm) this.formProceso;
		ProcesoPRERenombrarMatrizFacturacionService service = (ProcesoPRERenombrarMatrizFacturacionService)getBean("spusicc.procesoPRERenombrarMatrizFacturacionService");
		MantenimientoPRECambioCodigoVentaService serviceCCV = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		
		String mensaje = "";
		String oidCabeceraMatrizFactu = "";
		int existeOfertaMatrizFactuDestino = 0;
		
		oidCabeceraMatrizFactu = service.getOidCabeceraCampanaDestino(f.getCodigoPeriodoDestino());
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodoMatriz", f.getCodigoPeriodoOrigen());
		
		Integer cuvFact = serviceCCV.getExisteCUVFacturado(criteria);
		if(cuvFact > 0){
			return mensaje = "No se puede renombrar Matriz de Facturación ya que cuenta con CUVs facturados";
		}
		
		if(StringUtils.isBlank(oidCabeceraMatrizFactu)){
			mensaje = "Campaña Destino aún no tiene Matriz de Facturación";
		}else{
			existeOfertaMatrizFactuDestino = service.getExisteOfertaEnMatrizFacturacionDestino(oidCabeceraMatrizFactu);
			
			if(existeOfertaMatrizFactuDestino > 0){
				mensaje = "Campaña Destino ya tiene ofertas registradas, seleccione otra Campaña Destino";
			}
		}
		
		return mensaje;
	}
}