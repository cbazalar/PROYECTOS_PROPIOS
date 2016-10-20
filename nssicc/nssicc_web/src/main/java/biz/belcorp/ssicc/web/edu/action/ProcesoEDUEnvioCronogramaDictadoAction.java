package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDUEnvioCronogramaDictadoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECCalcularRecuperacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProcesoEDUEnvioCronogramaDictadoAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6381633185406004494L;

	private List eduEmpresaComercializadoraList;
	private LabelValue[] eduRegionesCursosList = {};
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception
	{
		ProcesoEDUEnvioCronogramaDictadoForm formProceso = new ProcesoEDUEnvioCronogramaDictadoForm(); 
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");		
		service.enviarCronogramaDictado(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoEDUEnvioCronogramaDictadoForm f = (ProcesoEDUEnvioCronogramaDictadoForm) this.formProceso;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		f.setCodigoPais(pais.getCodigo());
				
		/*METODO RESET*/
		f.setCodigoPeriodo(null);
		/*FIN METODO RESET*/
		
		loadCombos(f);
		
		 if (f.getCodigoPeriodo() == null || StringUtils.isBlank(f.getCodigoPeriodo()))
			 ajax.getCampannaSgteEDUByPaisEmpresa(f.getCodigoPais(), f.getCodigoEmpresa());
		 this.eduRegionesCursosList = ajax.getRegionesEDUByPaisEmpresa(f.getCodigoPais(), f.getCodigoEmpresa());	
		 
			 
	}
	
	/**
	 * Seteando Combos
	 * @param request
	 * @throws Exception
	 */

	private void loadCombos(ProcesoEDUEnvioCronogramaDictadoForm f) throws Exception
	{
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		List listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		
		this.eduEmpresaComercializadoraList = listaEmpresa;
		
		/* Obteniendo Campaña de Proceso + 1 */
		if (listaEmpresa != null && listaEmpresa.size() > 0) 
		{
			EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
			 
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoEmpresa", f.getCodigoEmpresa());
			String codigoPeriodo = service.getCampannaActualProceso(criteria);
			if (StringUtils.isNotBlank(codigoPeriodo)) 
			{
				criteria.put("campannaProceso", codigoPeriodo);
				criteria.put("numeroCampanna", new Integer("1"));
				String campannaSgte = siccService.getDevuelveCampanna(criteria);
				f.setCodigoPeriodo(campannaSgte);
			}
			else 
				f.setCodigoPeriodo(null);
		}
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception 
	{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoEDUEnvioCronogramaDictadoForm f = (ProcesoEDUEnvioCronogramaDictadoForm) this.formProceso;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		String codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoEmpresa", f.getCodigoEmpresa());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("usuarioLogin", usuario.getLogin());
		params.put("regionList",(f.getRegiones() == null) ? new ArrayList() : Arrays.asList(f.getRegiones()));
		
		return params;
	}
	
	public void loadCargarDatos(ValueChangeEvent value)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoEDUEnvioCronogramaDictadoForm f = (ProcesoEDUEnvioCronogramaDictadoForm) this.formProceso;
		String valor = value.getNewValue().toString();
		
		 this.eduRegionesCursosList = ajax.getRegionesEDUByPaisEmpresa(f.getCodigoPais(), valor);
	}

	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	public LabelValue[] getEduRegionesCursosList() {
		return eduRegionesCursosList;
	}

	public void setEduRegionesCursosList(LabelValue[] eduRegionesCursosList) {
		this.eduRegionesCursosList = eduRegionesCursosList;
	}
}
