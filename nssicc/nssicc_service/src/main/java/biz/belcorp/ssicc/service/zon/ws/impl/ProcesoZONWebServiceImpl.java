/**
 * 
 */
package biz.belcorp.ssicc.service.zon.ws.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.ParametroInterfazService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pej.ConsultaPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.zon.ws.ProcesoZONWebService;
import biz.belcorp.ssicc.service.zon.ws.beans.ResponsableZONWebServiceResultado;
import biz.belcorp.ssicc.service.zon.ws.beans.TerritZONWebServiceResultado;
import biz.belcorp.ssicc.service.zon.ws.beans.TerritoriosZONWebService;
import biz.belcorp.ssicc.service.zon.ws.beans.UbigZONWebServiceResultado;
import biz.belcorp.ssicc.service.zon.ws.beans.UbigeosZONWebService;

/**
 * @author itocto
 *
 */
/**
 * @author jpjimenez
 *
 */
/**
 * @author jpjimenez
 * 
 */
public class ProcesoZONWebServiceImpl extends ServletEndpointSupport implements
		ProcesoZONWebService {

	Log log = LogFactory.getLog(ProcesoZONWebServiceImpl.class);

	ProcesoZONActualizarUnidadesGeograficasService service;
	ParametroInterfazService parametroService;
	AjaxService ajaxService;
	PaisService paisService;

	MantenimientoLETLideresService lideresService;
	MantenimientoMAEClienteService clienteService;
	InterfazSiCCService interfazSiCCService;
	ConsultaPEJProgramaEjecutivasService servicePEJ;
	ReporteService reporteService;
	MantenimientoZONDirectorioService mantenimientoService;
	UsuarioService usuarioService;
	GenericoService genericoService;
	MantenimientoPEJProgramaEjecutivasService mantenimientoPEJProgramaEjecutivasService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		service = (ProcesoZONActualizarUnidadesGeograficasService) getWebApplicationContext()
				.getBean(
						"spusicc.procesoZONActualizarUnidadesGeograficasService");
		parametroService = (ParametroInterfazService) getWebApplicationContext()
				.getBean("sisicc.parametroInterfazService");
		ajaxService = (AjaxService) getWebApplicationContext().getBean(
				"ajaxService");
		paisService = (PaisService) getWebApplicationContext().getBean(
				"paisService");

		lideresService = (MantenimientoLETLideresService) getWebApplicationContext()
				.getBean("spusicc.mantenimientoLETLideresService");
		clienteService = (MantenimientoMAEClienteService) getWebApplicationContext()
				.getBean("spusicc.mantenimientoMAEClienteService");
		interfazSiCCService = (InterfazSiCCService) getWebApplicationContext()
				.getBean("sisicc.interfazSiCCService");
		servicePEJ = (ConsultaPEJProgramaEjecutivasService) getWebApplicationContext()
				.getBean("spusicc.consultaPEJProgramaEjecutivasService");
		reporteService = (ReporteService) getWebApplicationContext().getBean(
				"scsicc.reporteService");
		mantenimientoService = (MantenimientoZONDirectorioService) getWebApplicationContext()
				.getBean("spusicc.mantenimientoZonDirectorioService");
		usuarioService = (UsuarioService) getWebApplicationContext().getBean(
				"usuarioService");

		genericoService = (GenericoService) getWebApplicationContext().getBean(
				"genericoService");
		mantenimientoPEJProgramaEjecutivasService = (MantenimientoPEJProgramaEjecutivasService) getWebApplicationContext()
				.getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.zon.ws.ProcesoZONWebService#consultarTerritorio()
	 */
	public TerritZONWebServiceResultado consultarTerritorio() {

		TerritZONWebServiceResultado resultado = new TerritZONWebServiceResultado();
		resultado.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_OK);

		if (log.isDebugEnabled())
			log.debug("Entrando al metodo consultarTerritorio");

		try {
			// Obtenemos la subgerencia
			List subgerencias = service.getSubgerencias();
			if (subgerencias != null && subgerencias.size() > 0) {
				Map sg = (Map) subgerencias.get(0);
				String oidSubgerencia = MapUtils.getString(sg,
						"oidSubgerencia", "");

				// Obtenemos el pais desde la configuracion
				ParametroInterfaz criteria = new ParametroInterfaz();

				criteria.setCodigoSistema("ZON");
				criteria.setNombre("CodigoPais");

				ParametroInterfaz paramPais = parametroService
						.getParametroByCriteria(criteria);

				if (paramPais != null) {
					// Obtenemos la compa�ia
					criteria.setNombre("CodigoCompania");
					ParametroInterfaz paramCompania = parametroService
							.getParametroByCriteria(criteria);

					if (paramCompania != null) {
						Map params = new HashMap();
						params.put("codigoPais", paramPais.getValor());
						params.put("oidSubgerencia", oidSubgerencia);
						params.put("codigoCompania", paramCompania.getValor());

						TerritoriosZONWebService[] territorios = castTerritorios(service
								.getTerritoriosByCriteria(params));

						resultado.setMensaje(Integer
								.toString(territorios.length));
						resultado.setTerritorios(territorios);
					} else {
						resultado
								.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
						resultado.setMensaje("No existe c�digo de compa��a.");
					}
				} else {
					resultado
							.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
					resultado.setMensaje("No existe c�digo de pa�s.");
				}
			} else {
				resultado.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
				resultado.setMensaje("No existe Subgerencia.");
			}
		} catch (Exception ex) {
			resultado.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
			resultado.setMensaje(ex.getMessage());
			log.error(ex.getMessage(), ex);
		}

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.zon.ws.ProcesoZONWebService#consultarUbigeo()
	 */
	public UbigZONWebServiceResultado consultarUbigeo() {

		UbigZONWebServiceResultado resultado = new UbigZONWebServiceResultado();
		resultado.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_OK);

		if (log.isDebugEnabled())
			log.debug("Entrando al metodo consultarUbigeo");

		try {
			// Verificar la exitencia del pais en la configuracion
			// Obtenemos el pais desde la configuracion
			ParametroInterfaz criteria = new ParametroInterfaz();

			criteria.setCodigoSistema("ZON");
			criteria.setNombre("CodigoPais");

			ParametroInterfaz paramPais = parametroService
					.getParametroByCriteria(criteria);

			if (paramPais != null) {
				// Obtenemos la compa�ia
				criteria.setNombre("CodigoCompania");
				ParametroInterfaz paramCompania = parametroService
						.getParametroByCriteria(criteria);

				if (paramCompania != null) {
					Map params = new HashMap();
					params.put("codigoPais", paramPais.getValor());
					params.put("codigoCompania", paramCompania.getValor());

					UbigeosZONWebService[] ubigeos = castUbigeos(service
							.getUbigeosByCriteria(params));

					resultado.setMensaje(Integer.toString(ubigeos.length));
					resultado.setUbigeos(ubigeos);
				} else {
					resultado
							.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
					resultado.setMensaje("No existe c�digo de compa��a.");
				}

			} else {
				resultado.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
				resultado.setMensaje("No existe c�digo de pa�s.");
			}
		} catch (Exception ex) {
			resultado.setCodigo(Constants.ZON_WEBSERVICE_RESULTADO_ERROR);
			resultado.setMensaje(ex.getMessage());
			log.error(ex.getMessage(), ex);
		}

		return resultado;
	}

	private TerritoriosZONWebService[] castTerritorios(List territorios) {
		TerritoriosZONWebService[] listTerritorios;
		Map terrMap;
		listTerritorios = new TerritoriosZONWebService[territorios.size()];
		Iterator it = territorios.iterator();
		int i = 0;

		try {
			while (it.hasNext()) {
				terrMap = (HashMap) it.next();
				TerritoriosZONWebService territorio = new TerritoriosZONWebService();
				BeanUtils.copyProperties(territorio, terrMap);
				territorio.setCodigoSector(0);
				listTerritorios[i++] = territorio;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return listTerritorios;
	}

	private UbigeosZONWebService[] castUbigeos(List ubigeos) {
		UbigeosZONWebService[] listUbigeos;
		Map ubigeoMap;
		listUbigeos = new UbigeosZONWebService[ubigeos.size()];
		Iterator it = ubigeos.iterator();
		int i = 0;

		try {
			while (it.hasNext()) {
				ubigeoMap = (HashMap) it.next();
				UbigeosZONWebService ubigeo = new UbigeosZONWebService();
				BeanUtils.copyProperties(ubigeo, ubigeoMap);
				ubigeo.setCodigoSector(0);
				listUbigeos[i++] = ubigeo;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return listUbigeos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.zon.ws.ProcesoZONWebService#asignarResponsable(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public ResponsableZONWebServiceResultado asignarResponsable(
			String codigoUsuario, String codigoPais, String codigoConsultora,
			String codigoRegion, String codigoZona, String codigoSeccion,
			String campaniaNombramiento, String indUA, String indOperacion) {

		ResponsableZONWebServiceResultado resultado = new ResponsableZONWebServiceResultado();
		Pais pais = this.paisService.getPais(codigoPais);
		String mensajeError = "";
		Integer nCantFinales = null;
		try {
			if (StringUtils.isBlank(codigoUsuario)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Usuario";				
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.codigoUsuario", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(codigoRegion)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Regi�n";				
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.codigoRegion", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));					
				}
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(codigoZona)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Zona";				
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.codigoZona", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(codigoSeccion)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Secci�n";				
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.codigoSeccion", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}				
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(campaniaNombramiento)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Campa�a Nombramiento";
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.codigoCampaniaNombramiento",null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));				
				}
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(indUA)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Indicador UA";				
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.indUA", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(indOperacion)) {
				if(getWebApplicationContext()==null){
					mensajeError="Debe Ingresar Indicador Operacion";
				}else{
					mensajeError = getWebApplicationContext().getMessage("responsableZONWebService.msg.indOperacion", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));					
				}
				throw new Exception(mensajeError);
			}

			int estadoCampania = lideresService.getCampanaCerrada(campaniaNombramiento);
			if (estadoCampania == 1) {
				if(getWebApplicationContext()==null){
					mensajeError=" Campa�a ya est� cerrada";				
				}else{
					mensajeError = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.msg.campanaCerrada", null,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			String oidPais = lideresService.getOidPaisByCodigoPaisLET(criteria);
			String oidMarca = lideresService.getOidMarcaByCodigoMarcaGenericoLET(criteria);
			String oidCanal = lideresService.getOidCanalByCodigoCanalGenericoLET(criteria);
			

			/*
			 * par�metro de validaci�n se lee par�metro ( ZON-004), si est� en
			 * 1, se realizan las validaciones ya existentes en mantenimiento,
			 * de lo contrario, si est� en 0, no se realiza ninguna validaci�n
			 */
			Map paramValidacion = new HashMap();
			paramValidacion.put("codigoPais", codigoPais);
			paramValidacion.put("codigoSistema", Constants.ZON_CODIGO_SISTEMA);
			paramValidacion.put("nomPara", "indValidWeb");
			
			String vsParamValid = lideresService.getIndicadorAsignarLider(paramValidacion);

			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				// valida correo Asignado de Lider
				String validarCorreoLider = ajaxService.getValidarCorreoLider(
						codigoConsultora, codigoPais);
				if (!validarCorreoLider.equals(Constants.NRO_UNO)) {
					throw new Exception(validarCorreoLider);
				}
			}
			
			// valida cierre region 
			String validarAsignarLideres = this.validarAsignarLideres(codigoPais, Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, 
																	  codigoRegion,campaniaNombramiento);
			if (!validarAsignarLideres.equals(Constants.NRO_UNO)) {
				throw new Exception(validarAsignarLideres);
			}

			
			// -- Capturar Periodo Actual
			List lista = interfazSiCCService.getPeriodoFechaProcesoActual(criteria);
			String periodoActual = ((HashMap) lista.get(0)).get("cod_peri").toString();
			criteria.put("codigoPeriodoActual", periodoActual);

			Integer indicadorProgramaLet = lideresService.getIndicadorProgramaLet(codigoPais);
			//Ajuste PER-SiCC-2014-0228 LET
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				if (indicadorProgramaLet != null && indicadorProgramaLet.intValue() == 2 
						&& lideresService.getEncontrarConcursoLet(periodoActual).intValue() == 0) {
						if(getWebApplicationContext()==null){
						mensajeError="No existe concurso Let para la campa�a";							
						}else{
							mensajeError = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.mensaje.error.NoEncontroConcurso",null,
									getLocaleIdioma(pais.getCodigoIdiomaIso()));
						}					
					throw new Exception(mensajeError);
				}
			}
			
			//Ajuste PER-SiCC-2014-0228 LET
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				if (indicadorProgramaLet != null && indicadorProgramaLet.intValue() == 3 
						&& lideresService.getEncontrarProgramaLetCorporativo(periodoActual).intValue() == 0) {
					if(getWebApplicationContext()==null){
						mensajeError="No existe concurso Let para la campa�a";					
					}else{
						mensajeError = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.mensaje.error.NoEncontroConcurso",null,
								getLocaleIdioma(pais.getCodigoIdiomaIso()));
					}
					throw new Exception(mensajeError);
				}
			}
				
		    //Ajuste PER-SiCC-2014-0228 LET	
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				if (indicadorProgramaLet != null && indicadorProgramaLet.intValue() == 4) {
					Map result = new HashMap();
					result = lideresService.getEncontrarProgramaLecCorporativo(periodoActual);
					String codProg = result.get("codigoPrograma").toString();					
					Integer iiport=null;
					Integer iicome=null;
					if(result.get("indInscPort")!=null)
					iiport= Integer.parseInt(result.get("indInscPort").toString());
					if(result.get("indInscCome")!=null)
					iicome = Integer.parseInt(result.get("indInscCome").toString());
					/*Integer iplide = Integer.parseInt(result.get("indPosiLide").toString());*/

					if (StringUtils.isBlank(codProg)) {
						if(getWebApplicationContext()==null){
							mensajeError="No existe concurso Let para la campa�a";							
						}else{
							mensajeError = getWebApplicationContext()
									.getMessage("mantenimientoLECLideresForm.mensaje.error.NoEncontroConcurso",null,
											getLocaleIdioma(pais.getCodigoIdiomaIso()));
						} 
						throw new Exception(mensajeError);
					}else if (iiport == 0) {
						if(getWebApplicationContext()==null){
							mensajeError="No se permiten Nombramientos por Web";
						}else{
							mensajeError = getWebApplicationContext()
									.getMessage("mantenimientoLECLideresForm.mensaje.error.NoNombramientosWeb",null,
											getLocaleIdioma(pais.getCodigoIdiomaIso()));								
						}
						throw new Exception(mensajeError);
					} else if (iicome == 0) {
						if(getWebApplicationContext()==null){
							mensajeError="No se permiten Nombramientos por Comercial";						
						}else{
							mensajeError = getWebApplicationContext()
									.getMessage("mantenimientoLECLideresForm.mensaje.error.NoNombramientosCome",null,
											getLocaleIdioma(pais.getCodigoIdiomaIso()));
						}
						throw new Exception(mensajeError);
					}
					
				}
			}

			

			criteria.put("oidPais", oidPais);
			criteria.put("oidMarca", oidMarca);
			criteria.put("oidCanal", oidCanal);
			criteria.put("codigoPeriodo", periodoActual);
			criteria.put("codigoZona", codigoZona);

			criteria.put("permitirIngresoCodigoCliente", true);
			criteria.put("mostrarMensajeReingreo", false);
			criteria.put("mostrarMensajeReingreo", Constants.NO);
			criteria.put("indicadorUnicoLiderSeccion", null);
			criteria.put("tipo", indUA);
			criteria.put("indicadorNombramiento", Constants.NUMERO_CERO);
			criteria.put("realizarEliminacionClasificacion", Constants.SI);
						

			// -- Obtenemos listado general
			List lideresList = lideresService.getSeccionesByCriteria(criteria);
			String unidadAdministrativa = "";
			String unidadAdministrativaEval = "";
			String codigoLider = "";
			String codigoLiderEval = "";
			String numeroActivasFinalesZona = "";
			String promedioActivasFinalesSeccion = "";
			String indicadorZona = "";
			String indicadorSeccion = "";

			String codigoSeccionDesvincular = "";
			BigDecimal oidSeccion = null;
			boolean flagAsignar = false;
			boolean flagDesvincular = false;
			for (Object object : lideresList) {
				Map mapLider = (HashMap) object;

				unidadAdministrativaEval = (String) mapLider.get("unidadAdministrativa");
				String codigoSeccion2 = unidadAdministrativaEval.substring(8, 9);
				String codigoZona2 = unidadAdministrativaEval.substring(4, 8);
				String codigoRegion2 = unidadAdministrativaEval.substring(2, 4);
				codigoLiderEval = (String) mapLider.get("codigoLider");
				if (codigoSeccion.equals(codigoSeccion2) && codigoZona.equals(codigoZona2) && codigoRegion.equals(codigoRegion2)) {
					codigoLider = codigoLiderEval;
					unidadAdministrativa = unidadAdministrativaEval;
					numeroActivasFinalesZona = (String) mapLider.get("numeroActivasFinalesZona");
					promedioActivasFinalesSeccion = (String) mapLider.get("promedioActivasFinalesSeccion");
					indicadorZona = (String) mapLider.get("indicadorZona");
					indicadorSeccion = (String) mapLider.get("promedioActivasFinalesSeccion");
					oidSeccion = (BigDecimal) mapLider.get("oidSeccion");
					flagAsignar = true;
				}
				if (codigoLiderEval != null) {
					if (codigoLiderEval.equals(codigoConsultora)) {
						codigoSeccionDesvincular = codigoSeccion2;
						flagDesvincular = true;
					}
				}

				if (flagAsignar && flagDesvincular) {
					break;
				}
			}

			// -- Obtenemos las propiedades del bean como un 'Map' ---
			Map params = new HashMap();
			params.put("codigoLider", codigoLider);
			params.put("codigoPais", codigoPais);
			params.put("codigoMarca", criteria.get("codigoMarca"));
			params.put("codigoCanal", criteria.get("codigoCanal"));
			params.put("codigoCliente", codigoConsultora);
			params.put("indicadorReingreso", Constants.NO);
			params.put("indicadorNoValidaUnicoLider", Constants.NO);
			params.put("numeroActivasFinalesZona", numeroActivasFinalesZona);
			params.put("promedioActFinalesSeccion",promedioActivasFinalesSeccion);
			params.put("unidadAdministrativa", unidadAdministrativa);
			params.put("oidSeccion", oidSeccion);
			params.put("codigoUsuario", codigoUsuario);
			params.put("codigoRegion", codigoRegion);
			params.put("codigoZona", codigoZona);
			params.put("codigoSubgerencia", "01");

			params.put("indicadorZona", indicadorZona);
			params.put("indicadorSeccion", indicadorSeccion);
			params.put("oidPais", oidPais);
			params.put("oidMarca", oidMarca);
			params.put("oidCanal", oidCanal);

			params.put("usuario", codigoUsuario);
			params.put("codigoCliente", codigoConsultora);
			params.put("tipo", indUA);

			String codigoSeccionInicial = codigoSeccion;
			params.put("codigoSeccionInicial", codigoSeccionInicial);
			params.put("codigoSeccion",!codigoSeccionDesvincular.equals("") ? codigoSeccionDesvincular : codigoSeccion);
			params.put("campaniaNombramiento", campaniaNombramiento);
			params.put("realizarEliminacionClasificacion", Constants.SI);
			String[] mensaje = asignarResponsablesUA(params, vsParamValid);

			resultado.setCodigoResultado(Constants.LET_WEBSERVICE_RESULTADO_OK);
			resultado.setMensajeResultado(mensaje[0]);
			resultado.setCodigoCampaniaAsign(mensaje[1]);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resultado.setCodigoResultado(Constants.LET_WEBSERVICE_RESULTADO_ERROR);
			resultado.setMensajeResultado(e.getMessage());
		}
		return resultado;
	}

	/**
	 * @param params
	 * @param vsParamValid
	 * @return
	 * @throws Exception
	 */
	private String[] asignarResponsablesUA(Map params, String vsParamValid) throws Exception {
		String[] mensajeRpta = { "", "" };
		String codigoPais = (String) params.get("codigoPais");
		Pais pais = this.paisService.getPais(codigoPais);
		params.put("indicadorProgramaLet",lideresService.getIndicadorProgramaLet(codigoPais));
		// -- Capturar Periodo Actual
		List lista = interfazSiCCService.getPeriodoFechaProcesoActual(params);
		String periodoActual = ((HashMap) lista.get(0)).get("cod_peri").toString();
		params.put("codigoPeriodoActual", periodoActual);

		// -- Colocar Fecha y Periodo de Proceso
		Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCService.getPeriodoFechaProcesoActual(params).get(0);

		String codigoPeriodoProceso = (String) obtenerFechaPeriodoProceso.get("cod_peri");
		String fechaProceso = (String) obtenerFechaPeriodoProceso.get("fec_proc");

		params.put("fechaProceso", fechaProceso);
		params.put("codigoPeriodoProceso", codigoPeriodoProceso);
		
		ParametroPais paramPais = new ParametroPais();
		paramPais.setCodigoPais(codigoPais);
		paramPais.setCodigoSistema(Constants.LET_CODIGO_SISTEMA);
		paramPais.setCodigoParametro(Constants.LET_CODIGO_PARAM_001);
		paramPais.setNombreParametro(Constants.LET_NOMBRE_PARAM_001);
		List lstParametrosLET = genericoService.getParametrosPais(paramPais);
		int indicadorAsignarLider = 0;
		if (lstParametrosLET != null && lstParametrosLET.size() > 0) {
			ParametroPais result = (ParametroPais) lstParametrosLET.get(0);
			indicadorAsignarLider = Integer.parseInt(result.getValorParametro());
		}
			

		// -- Validar Indicador Asignar Lider --------------------
		indicadorAsignarLider = 0;
		String codigoPeriodoProcesoSgte = servicePEJ.getObtienePeriodo(codigoPais, codigoPeriodoProceso, 1);

		// -- captura periodo de parametria pais
		// punto 28
		// String periodoAsignarLider = (indicadorAsignarLider == 0) ?
		// codigoPeriodoProceso : codigoPeriodoProcesoSgte;
		String periodoAsignarLider = "";
		
		if (indicadorAsignarLider == 0) {
			periodoAsignarLider = codigoPeriodoProceso;
		} else if (indicadorAsignarLider == 1) {
			periodoAsignarLider = codigoPeriodoProcesoSgte;
		}
 		periodoAsignarLider = (String) params.get("campaniaNombramiento");
 		params.put("realizarEliminacionClasificacion", Constants.SI);
 		if (!periodoAsignarLider.equals(codigoPeriodoProceso))
 			params.put("realizarEliminacionClasificacion", Constants.NO);
		
 		
        String codigoPeriodoQuiebre18 = servicePEJ.getObtienePeriodo(codigoPais, periodoAsignarLider, -18);
		params.put("periodoAsignarLider", periodoAsignarLider);
		params.put("periodoAsignarLiderSgte", codigoPeriodoProcesoSgte);
		if (!vsParamValid.equals(Constants.NUMERO_UNO)) {
			params.put("indRealizarValidaciones", "N");
		}

		// -- Obtener oid campa�a anterior -----------------------
		// params: codigoPeriodo, oidPais, oidMarca, oidCanal
		Map obtenerPeriodoAnterior = new HashMap();
		obtenerPeriodoAnterior.put("codigoPeriodo", codigoPeriodoProceso);
		obtenerPeriodoAnterior.put("oidPais", params.get("oidPais"));
		obtenerPeriodoAnterior.put("oidMarca", params.get("oidMarca"));
		obtenerPeriodoAnterior.put("oidCanal", params.get("oidCanal"));
		params.put("oidPeriodoAnterior", lideresService.getOidPeriodoAnteriorByCodigoPeriodo(obtenerPeriodoAnterior));

		// -- realizamos las validaciones
		// ------------------------------------------------------
		String respuesta = "0__OK";
		boolean bucarOidCliente = false;
		params.put("indicadorWEB", Constants.SI);
		respuesta = lideresService.validarAsignacionLiderSeccion(params);
		StringTokenizer stRespuesta = new StringTokenizer(respuesta, "__");
		log.debug("Respuesta Validacion: " + respuesta);
		String codigoRespuesta = stRespuesta.nextToken();
		String valorRespuesta = stRespuesta.nextToken();

		// De acuerdo a las validaciones realizadas, para ver si el cliente ingresado se puede asignar
		// a la seccion como lider, y si todo esta ok, codigoRespuesta=0, se actualiza la seccion
		String codigoClienteBuscar = (String) params.get("codigoCliente");
		String unidadAdministrativa = (String) params
				.get("unidadAdministrativa");
		String	mensajeError="";
		if (codigoRespuesta.equals("1")) {
			String[] datos = new String[] { codigoClienteBuscar };
			if(getWebApplicationContext()==null){
				mensajeError="La consultora  no fue encontrado.";
			}else{
				mensajeError = getWebApplicationContext().getMessage(
						"mantenimientoLETLideresForm.msg.clienteNoExiste", datos,
						getLocaleIdioma(pais.getCodigoIdiomaIso()));					
			}
			throw new Exception(mensajeError);
		} else if (codigoRespuesta.equals("2")) {
			String[] datos = new String[] { codigoClienteBuscar };
			if(getWebApplicationContext()==null){
				mensajeError="La consultora no tiene el tipo de cliente [Consultora] requerido para ser l�der.";
			
			}else{
				mensajeError = getWebApplicationContext().getMessage(
						"mantenimientoLETLideresForm.msg.tipoClienteConsultora",
						datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
			}
			throw new Exception(mensajeError);
		} else if (codigoRespuesta.equals("3")) {
			String arrCodigo[] = valorRespuesta.trim().split(";"); //3__n;01033151A;201404
			String codigo = arrCodigo[0];
			String valor = arrCodigo[1];

			if (codigo.equals("n")) {
				/*String valor2 = arrCodigo[2];
				String[] datos = new String[] {codigoClienteBuscar, valor,valor2};
				String mensajeError = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.msg.liderOtraSeccion", datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);*/
				
				Map paramsDesvincular = params;
				String codigoRegion = valor.substring(2, 4);
				String codigoZona = valor.substring(4, 8);
				String codigoSeccion = valor.substring(8, 9);
				String codigoRegionAnterior = (String) params.get("codigoRegion");
				String codigoZonaAnterior = (String) params.get("codigoZona");
				String codigoSeccionAnterior = (String) params.get("codigoSeccion");
				paramsDesvincular.put("codigoRegion", codigoRegion);
				paramsDesvincular.put("codigoZona", codigoZona);
				paramsDesvincular.put("codigoSeccion", codigoSeccion);

				boolean msjRespuesta = desvincularResponsablesUA(paramsDesvincular);
				params.put("codigoRegion", codigoRegionAnterior);
				params.put("codigoZona", codigoZonaAnterior);
				params.put("codigoSeccion", codigoSeccionAnterior);
				return asignarResponsablesUA(params, vsParamValid);

			} else if (codigo.equals("n2")) {
				String valor2 = arrCodigo[2];
				log.debug("Respuesta Validacion 3 substr: " + codigo + "-"
						+ valor + "-" + valor2);
				lideresService.deleteHistoricoGerente(Integer.parseInt(valor2));
				Map paramsDesvincular = params;
				if (StringUtils.isNotBlank(valor)) {
					String codigoRegion = valor.substring(2, 4);
					String codigoZona = valor.substring(4, 8);
					String codigoSeccion = valor.substring(8, 9);
					String codigoRegionAnterior = (String) params.get("codigoRegion");
					String codigoZonaAnterior = (String) params.get("codigoZona");
					String codigoSeccionAnterior = (String) params.get("codigoSeccion");
	
					paramsDesvincular.put("codigoRegion", codigoRegion);
					paramsDesvincular.put("codigoZona", codigoZona);
					paramsDesvincular.put("codigoSeccion", codigoSeccion);
					boolean msjRespuesta = desvincularResponsablesUA(paramsDesvincular);
	
					params.put("codigoRegion", codigoRegionAnterior);
					params.put("codigoZona", codigoZonaAnterior);
					params.put("codigoSeccion", codigoSeccionAnterior);
				}
				return asignarResponsablesUA(params, vsParamValid);
			} else {
				String valor2 = arrCodigo[2];
				String valor3 = null;
				String valor4 = null;
				periodoAsignarLider = valor;
				params.put("periodoAsignarLider", valor);
				if (StringUtils.isNotBlank(codigoClienteBuscar)) {
					if (arrCodigo.length == 5) {
						valor3 = arrCodigo[3];
						valor4 = arrCodigo[4];
					} else {
						valor3 = "0";
						valor4 = "0";
					}
					params.put("minActivasFinalesZona", valor3);
					params.put("minActivasFinalesSeccion", valor4);
				}
				codigoRespuesta = "0";
				valorRespuesta = valor2;
			}
		} else if (codigoRespuesta.equals("3B")) {
			String[] datos = new String[] { codigoClienteBuscar, valorRespuesta };
			if(getWebApplicationContext()==null){
				mensajeError="La consultora  ya es l\u00EDder de la secci\u00F3n {1}. \u00BFDesea Asignarle de todas formas \n en la secci\u00F3n actual tambi\u00E9n?";
			
			}else{
				mensajeError = getWebApplicationContext()
						.getMessage("mantenimientoLETLideresForm.msg.liderOtraSeccion.asignacion",
								datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));				
			}
			throw new Exception(mensajeError);

		} else if (codigoRespuesta.equals("4")) {
			//Ajuste PER-SiCC-2014-0228 LET(SSICC-DECU-ZON-Zonificacion v17.0)
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				String respuestas[] = valorRespuesta.split(";");
				String[] datos = new String[] { respuestas[0] };
				String texto;
				if (respuestas[1].equals("0")) {
					if(getWebApplicationContext()==null){
						mensajeError="Consultora no cumple m\u00EDnimo de campa\u00F1as para reingreso. Numero de campa\u00F1as de reingreso, \u00BFDesea continuar con asignaci\u00F3n?.";
					
					}else{
						mensajeError = getWebApplicationContext()
								.getMessage("mantenimientoLETLideresForm.msg.campanasReingreso",datos,
										getLocaleIdioma(pais.getCodigoIdiomaIso()));
						}

				} else {
					if(getWebApplicationContext()==null){
						mensajeError="Consultora no cumple m\u00EDnimo de campa\u00F1as para reingreso. Numero de campa\u00F1as de reingreso.";
					
					}else{
						mensajeError = getWebApplicationContext()
								.getMessage("mantenimientoLETLideresForm.msg.campanasReingresoError",datos,
										getLocaleIdioma(pais.getCodigoIdiomaIso()));
					}
					throw new Exception(mensajeError);
				}
			}

		} else if (codigoRespuesta.equals("5")) {
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				String indicadorZona = (String) params.get("indicadorZona");
				String[] datos = new String[] { unidadAdministrativa, codigoClienteBuscar };

				String mensajeZona = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxZona";
				if (indicadorZona.equals("0"))
					mensajeZona = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxZonaAux";
				if(getWebApplicationContext()==null){
					mensajeError="La secci�n nueva  no cumple con el m�nimo de activas requeridas por Zona. Por lo tanto se rechaza la asignaci�n de la consultora  como l�der.";
					if (indicadorZona.equals("0"))
						mensajeError = "La secci�n nueva  no cumple con el m�nimo de activas requeridas por Zona. Por lo tanto se rechaza la asignaci�n de la consultora  como l�der.";
				}else{
					mensajeError = getWebApplicationContext().getMessage(mensajeZona, datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}

		} else if(codigoRespuesta.equals("6A") || codigoRespuesta.equals("6B") || codigoRespuesta.equals("6C")) {
			//Ajuste PER-SiCC-2014-0228 LET(SSICC-DECU-ZON-Zonificacion v17.0)
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				String mensajeSeccion = "";
				String[] datos = new String[] {unidadAdministrativa, valorRespuesta, codigoClienteBuscar};
				if(codigoRespuesta.equals("6A")){
					String indicadorSeccion = (String) params.get("indicadorSeccion");
					mensajeSeccion = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxSeccion";
					if (indicadorSeccion.equals("0"))
						mensajeSeccion = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxSeccionAux";
				}else{
					mensajeSeccion = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxSeccion";
				}
				if(getWebApplicationContext()==null){
					mensajeError ="La secci�n no cumple"; 
				}else{
					mensajeError = getWebApplicationContext().getMessage(mensajeSeccion, datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}

		}else if (codigoRespuesta.equals("7")) {
			if (vsParamValid.equals(Constants.NUMERO_UNO)) {
				String[] datos = new String[] { valorRespuesta };
				if(getWebApplicationContext()==null){
					mensajeError="Estatus de consultora no permitido "; 
				}else{
					mensajeError = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.msg.estatusLider", datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				throw new Exception(mensajeError);
			}
		} else if (codigoRespuesta.equals("9")) {
			String[] datos = new String[] { valorRespuesta };			
			if(getWebApplicationContext()== null){
				mensajeError="Hubo problemas al validar la asignaci�n de la l�der";}
			 else{	
			 mensajeError = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.msg.errorValidacion", datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
			 }
			throw new Exception(mensajeError);

		}
		if (codigoRespuesta.equals("0")) {
			if (!codigoClienteBuscar.equals(""))
				params.put("oidCliente", valorRespuesta);
			else
				params.put("oidCliente", "");

			// -- obtenemos el periodo actual
			LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(params);
			// -- seteamos el periodo actual
			if (periodos != null && periodos.length > 0) {
				LabelValue base = periodos[periodos.length - 1];
				params.put("oidPeriodo", base.getValue());
			}
			// -- Setea la clasificacion de lideres
			params.put("codigoClasificacionLideres",Constants.CODIGO_CLASIFICACION_LIDERES);
			// -- captura fecha inicial por periodo de parametria pais
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", codigoPais);
			criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteriaOperacion.put("codigoPeriodo", periodoAsignarLider);
			String fechaAsignarLider = ((Base) reporteService.getListaGenerico(
					"getFechaInicioPeriodoByPaisMarcaCanal", criteriaOperacion).get(0)).getCodigo();

			String fechaFinal = periodoAsignarLider.equals(codigoPeriodoProceso) ? fechaProceso: fechaAsignarLider;
			if (StringUtils.equals(periodoAsignarLider, codigoPeriodoProceso)) {
				params.put("fechaFinal", fechaFinal);
				params.put("fechaFinalPrAl", Constants.NUMERO_UNO);
			} else {
				params.put("fechaFinal", fechaFinal);
				params.put("fechaFinalPrAl", null);
			}

			params.put("codigoSubgerencia",unidadAdministrativa.substring(0, 2));

			// -- ejecutamos la asignacion del lider a la seccion
			params.put("indOrigRegi", Constants.ZON_ORIGEN_WEB);
			params.put("indicadorNombramiento", Constants.NUMERO_UNO);
			
			lideresService.execAsignacionLiderSeccion(params);

			if (codigoClienteBuscar.equals("")) {
				String[] datos = new String[] { unidadAdministrativa };
				String mensaje="";
				if(getWebApplicationContext()==null){
					 mensaje="La desasignaci�n de la l�der de la secci�n  se realizo correctamente";
				}else{
				      mensaje = getWebApplicationContext().getMessage("mantenimientoLETLideresForm.msg.deasignacionCorrecta",
						datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				}
				mensajeRpta[0] = mensaje;
				mensajeRpta[1] = "";
			} else {
				String[] datos = new String[] { codigoClienteBuscar, unidadAdministrativa, periodoAsignarLider };
				String mensaje ="La asignaci�n de la consultora "+ datos[0] +"como l�der de la secci�n "+ datos[1] +"se realizo correctamente desde campa�a "+datos[2]; 
					//getWebApplicationContext().getMessage("mantenimientoLETLideresForm.msg.asignacionCorrecta",
						//datos, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				mensajeRpta[0] = mensaje;
				mensajeRpta[1] = periodoAsignarLider;
			}
		}
		return mensajeRpta;
	}

	private boolean desvincularResponsablesUA(Map params) throws Exception {
		boolean mensajeRpta = false;
		params.put("subGer", "01");
		params.put("indOrigRegi", Constants.ZON_ORIGEN_WEB);
		
		String campaniaNombramiento = (String) params.get("campaniaNombramiento");

		// -- Colocar Fecha y Periodo de Proceso -----------------
		Map criteriaPeriodo = new HashMap();
		String codigoPais = (String) params.get("codigoPais");

		criteriaPeriodo.put("codigoPais", codigoPais);
		//criteriaPeriodo.put("codigoPeriodo", campaniaNombramiento);
		Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCService.getPeriodoFechaProcesoActual(criteriaPeriodo).get(0);
		String codigoPeriodoProceso = (String) obtenerFechaPeriodoProceso
				.get("cod_peri");
		String fechaProceso = (String) obtenerFechaPeriodoProceso
				.get("fec_proc");
		params.put("fechaProceso", fechaProceso);

		// -- Capturar Periodo Actual ----------------------------
		List lista = interfazSiCCService.getPeriodoFechaProcesoActual(params);
		String periodoActual = ((HashMap) lista.get(0)).get("cod_peri")
				.toString();
		params.put("codigoPeriodoActual", periodoActual);

		// -- Validar Indicador Asignar Lider --------------------
		// Punto 5.1

		ParametroPais paramPais = new ParametroPais();
		paramPais.setCodigoPais(codigoPais);
		paramPais.setCodigoSistema(Constants.LET_CODIGO_SISTEMA);
		paramPais.setCodigoParametro(Constants.LET_CODIGO_PARAM_001);
		paramPais.setNombreParametro(Constants.LET_NOMBRE_PARAM_001);

		List lstParametrosLET = genericoService.getParametrosPais(paramPais);
		int indicadorAsignarLider = 0;
		if (lstParametrosLET != null && lstParametrosLET.size() > 0) {
			ParametroPais result = (ParametroPais) lstParametrosLET.get(0);
			indicadorAsignarLider = Integer
					.parseInt(result.getValorParametro());
		}
		String codigoPeriodoProcesoSgte = servicePEJ.getObtienePeriodo(
				codigoPais, codigoPeriodoProceso, indicadorAsignarLider);

		// -- captura periodo de parametria pais	
		/*
		String periodoAsignarLider = "";
		if (indicadorAsignarLider == 0) {
			periodoAsignarLider = codigoPeriodoProceso;
		} else if (indicadorAsignarLider == 1) {
			periodoAsignarLider = codigoPeriodoProcesoSgte;
		} else if (indicadorAsignarLider == 2) {
			boolean esRegionCerrada = false;
			Map criteria = new HashMap();
			// criteria.put("codigoPeriodoActual", codigoPeriodoProceso);
			criteria.put("codigoRegion", params.get("codigoRegion"));

			esRegionCerrada = clienteService.esRegionCerradaxSeccion(criteria);

			if (esRegionCerrada) {
				codigoPeriodoProcesoSgte = servicePEJ.getObtienePeriodo(
						codigoPais, codigoPeriodoProceso, 1);
				periodoAsignarLider = codigoPeriodoProcesoSgte;
			} else {
				periodoAsignarLider = codigoPeriodoProceso;
			}
		}
		*/
		String periodoAsignarLider = "";
		periodoAsignarLider = campaniaNombramiento;
		params.put("periodoAsignarLider", periodoAsignarLider);
		// params.put("periodoAsignarLiderSgte",codigoPeriodoProcesoSgte);
		params.put("periodoAsignarLiderSgte", periodoAsignarLider);

		lideresService.executeProcesoDesvinculacion(params);
		mensajeRpta = true;
		return mensajeRpta;
	}

	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	private final Locale getLocaleIdioma(String codigoIsoIdioma) {
		if (StringUtils.isNotEmpty(codigoIsoIdioma)) {
			if (Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma
					.toLowerCase()))
				return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			else {
				log.debug("codigoIsoIdioma " + codigoIsoIdioma);
				return new Locale(codigoIsoIdioma.toLowerCase());
			}
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}

	/**
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoRegion
	 * @return
	 */
	private String validarAsignarLideres(String codigoPais, String codigoMarca,
			String codigoCanal, String codigoRegion, String codigoPeriodoActual) {
		String resultado = "";
		String indicadorValidaCierreRegion = "";
		boolean esRegionCerrada = false;
		try {

			// -- Obtenemos los datos de pais, marca y canal
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			// -- obtenemos la region
			criteria.put("codigoRegion", codigoRegion);

			// Map result = new HashMap();
			// result =
			// mantenimientoPEJProgramaEjecutivasService.getPeriodoDefault();
			// String codigoPeriodoActual = (String)
			// result.get("codigoPeriodo");

			criteria.put("codigoPeriodoActual", codigoPeriodoActual);

			// -- Recuperar indicador de AsignarLider --------------------
			Map criteriaParametrosPais = new HashMap();
			criteriaParametrosPais.put("codigoPais", codigoPais);
			criteriaParametrosPais.put("codigoSistema",Constants.LET_CODIGO_SISTEMA);
			criteriaParametrosPais.put("codigoParametro",Constants.LET_CODIGO_PARAM_005);

			indicadorValidaCierreRegion = lideresService.getIndicadorAsignarLider(criteriaParametrosPais);

			esRegionCerrada = clienteService.esRegionCerradaxSeccion(criteria);

			if (esRegionCerrada && indicadorValidaCierreRegion.equals(Constants.NRO_UNO)) {
				resultado = "Ya se realiz� el cierre de regi�n para la campa�a actual y no se puede realizar la asignaci�n.";
			} else {
				resultado = "1";
			}

			log.info("Salio MantenimientoLETLideresAction - asignar");

		} catch (Exception e) {
			return e.getMessage();
		}
		return resultado;
	}

	public void setService(
			ProcesoZONActualizarUnidadesGeograficasService service) {
		this.service = service;
	}

	public void setParametroService(ParametroInterfazService parametroService) {
		this.parametroService = parametroService;
	}

	public void setAjaxService(AjaxService ajaxService) {
		this.ajaxService = ajaxService;
	}

	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}

	public void setLideresService(MantenimientoLETLideresService lideresService) {
		this.lideresService = lideresService;
	}

	public void setClienteService(MantenimientoMAEClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public void setInterfazSiCCService(InterfazSiCCService interfazSiCCService) {
		this.interfazSiCCService = interfazSiCCService;
	}

	public void setServicePEJ(ConsultaPEJProgramaEjecutivasService servicePEJ) {
		this.servicePEJ = servicePEJ;
	}

	public void setReporteService(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	public void setMantenimientoService(
			MantenimientoZONDirectorioService mantenimientoService) {
		this.mantenimientoService = mantenimientoService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setGenericoService(GenericoService genericoService) {
		this.genericoService = genericoService;
	}

	public void setMantenimientoPEJProgramaEjecutivasService(
			MantenimientoPEJProgramaEjecutivasService mantenimientoPEJProgramaEjecutivasService) {
		this.mantenimientoPEJProgramaEjecutivasService = mantenimientoPEJProgramaEjecutivasService;
	}
}
