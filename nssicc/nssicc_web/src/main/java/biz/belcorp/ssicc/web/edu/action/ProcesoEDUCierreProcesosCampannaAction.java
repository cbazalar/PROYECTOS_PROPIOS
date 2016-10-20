package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCierreProcesosCampannaService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDUCierreProcesosCampannaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;

@SessionScoped
@ManagedBean
public class ProcesoEDUCierreProcesosCampannaAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5115421717264626319L;
	private List eduEmpresaComercializadoraList;
	private String indicadorPlanillaNoProcesadas;
	private List cargaArchivos;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoEDUCierreProcesosCampannaForm form = new ProcesoEDUCierreProcesosCampannaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());

		ProcesoEDUCierreProcesosCampannaService service = (ProcesoEDUCierreProcesosCampannaService) getBean("edu.procesoEDUCierreProcesosCampannaService");
		String codigoPais = (String) params.get("codigoPais");

		// Invocando al Proceso de Cerrar Cursos Vigentes / Actualizar
		// Parametros Cursos
		service.executeCierreProcesosCampanna(codigoPais, params);
		return params;
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		ProcesoEDUCierreProcesosCampannaForm f = (ProcesoEDUCierreProcesosCampannaForm) this.formProceso;
		params = super.prepareParamsBeforeExecute(params, form);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] lv = ajaxService.getRegionesEDUByPaisEmpresa(codigoPais,
				f.getCodigoEmpresa());
		String[] regionList = getRegionList(lv);

		params.put("codigoPais", codigoPais);
		params.put("codigoEmpresa", f.getCodigoEmpresa());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("usuarioLogin", usuario.getLogin());
		params.put("regionList", regionList);
		return params;
	}

	/**
	 * Obtengo la lista de solo codgos de regiones
	 * 
	 * @param lv
	 * @return
	 */
	private String[] getRegionList(LabelValue[] lv) {
		String[] regionList = new String[lv.length];
		for (int i = 0; i < lv.length; i++) {
			LabelValue l = lv[i];
			regionList[i] = l.getValue();
		}
		log.debug("list region " + regionList);
		return regionList;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoEDUCierreProcesosCampannaForm f = (ProcesoEDUCierreProcesosCampannaForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());
		loadCombos(f);

		/* Colocando procesos a ejecutar */
		ArrayList resultado = new ArrayList();
		for (int i = 1, j = 1; i <= 7; i++) {
			String cadena = new Integer(i).toString().trim();
			String cadenaMostrar = new Integer(j).toString().trim();// imprime
																	// el numero
			switch (i) {
			case 1:
				if (Constants.NUMERO_UNO.equals(f
						.getIndicadorConsultoraRezagada())) {
					this.adicionarProceso(resultado, cadenaMostrar,
							"procesoEDUCierreProcesosCampannaForm.proceso0"
									+ cadena);
					j++;
				}
				break;
			case 6:
				if (Constants.EDU_INDICADOR_BLOQUEO_SI.equals(f
						.getIndicadorBloqueo())) {
					this.adicionarProceso(resultado, cadenaMostrar,
							"procesoEDUCierreProcesosCampannaForm.proceso0"
									+ cadena);
					j++;
				}
				break;
			case 7:
				if (Constants.NUMERO_UNO.equals(indicadorPlanillaNoProcesadas)) {
					this.adicionarProceso(resultado, cadenaMostrar,
							"procesoEDUCierreProcesosCampannaForm.proceso0"
									+ cadena);
					j++;
				}
				break;
			default:
				this.adicionarProceso(resultado, cadenaMostrar,
						"procesoEDUCierreProcesosCampannaForm.proceso0"
								+ cadena);
				j++;
			}
		}
		this.cargaArchivos = resultado;
	}

	/**
	 * Seteando Combos
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void loadCombos(ProcesoEDUCierreProcesosCampannaForm f)
			throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);

		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		List listaEmpresa = siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);
		this.eduEmpresaComercializadoraList = listaEmpresa;

		/* Obteniendo Campaña de Proceso */
		if (listaEmpresa != null && listaEmpresa.size() > 0) {
			EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa
					.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoEmpresa", f.getCodigoEmpresa());
			String codigoPeriodo = service.getCampannaActualProceso(criteria);
			f.setCodigoPeriodo(codigoPeriodo);

			/* Obteniendo parametros del Pais y Empresa */
			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(f.getCodigoPais());
			parametro.setCodigoEmpresa(f.getCodigoEmpresa());
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro = siccService.getParametroCurso(parametro);
			f.setIndicadorBloqueo(parametro.getIndicadorBloqueo());
			f.setIndicadorConsultoraRezagada(parametro
					.getIndicadorConsultoraRezagada());
			indicadorPlanillaNoProcesadas = parametro
					.getIndicadorRegistroPlanillasNoProcesadas();
		}
	}

	/**
	 * Obtiene descripcion del proceso del archivo de recursos
	 * 
	 * @param resultado
	 * @param messageResources
	 * @param codigo
	 * @param keyMensaje
	 */
	private void adicionarProceso(ArrayList resultado, String codigo,
			String keyMensaje) {
		Base bean = new Base();
		String proceso = getResourceMessage(keyMensaje);
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);
		resultado.add(bean);
	}

	/**
	 * @return the eduEmpresaComercializadoraList
	 */
	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	/**
	 * @param eduEmpresaComercializadoraList
	 *            the eduEmpresaComercializadoraList to set
	 */
	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	/**
	 * @return the indicadorPlanillaNoProcesadas
	 */
	public String getIndicadorPlanillaNoProcesadas() {
		return indicadorPlanillaNoProcesadas;
	}

	/**
	 * @param indicadorPlanillaNoProcesadas
	 *            the indicadorPlanillaNoProcesadas to set
	 */
	public void setIndicadorPlanillaNoProcesadas(
			String indicadorPlanillaNoProcesadas) {
		this.indicadorPlanillaNoProcesadas = indicadorPlanillaNoProcesadas;
	}

	/**
	 * @return the cargaArchivos
	 */
	public List getCargaArchivos() {
		return cargaArchivos;
	}

	/**
	 * @param cargaArchivos
	 *            the cargaArchivos to set
	 */
	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}

}
