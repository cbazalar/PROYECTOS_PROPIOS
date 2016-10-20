package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.service.util.ExcelUtil;


@Service("spusicc.procesoMENCargaMasivaInformacionMensajesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMENCargaMasivaInformacionMensajesServiceImpl extends BaseService implements ProcesoMENCargaMasivaInformacionMensajesService {
	
	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
		
	
	/**
	 * @param procesoMENGenerarMensajesDAO the procesoMENGenerarMensajesDAO to set
	 */
	public void setProcesoMENGenerarMensajesDAO(
			ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO) {
		this.procesoMENGenerarMensajesDAO = procesoMENGenerarMensajesDAO;
	}

	/**
	 * @param interfazSiCCDAO the interfazSiCCDAO to set
	 */
	public void setInterfazSiCCDAO(InterfazSiCCDAO interfazSiCCDAO) {
		this.interfazSiCCDAO = interfazSiCCDAO;
	}



	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaBloqueoDesbloqueoMasivoService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		List listRegistros = new ArrayList();
		int fila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigo = (String)mapRow.get("0");
			if (StringUtils.contains(codigo, "."))
				codigo = StringUtils.remove(codigo, ".0");
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data

			Map mapRegistro = new HashMap();
			mapRegistro.put("numeroFila", filaExcel);
			mapRegistro.put("codigoCUV", codigo);

			fila +=1;
			listRegistros.add(mapRegistro);				
		    
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("archivoConRegistros", Constants.SI);
		resultado.put("listRegistros", listRegistros);
		resultado.put("totalRegistros", String.valueOf(fila));
		if (listRegistros.size() == 0)
			resultado.put("archivoConRegistros", Constants.NO);
		
		return resultado;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#cargarArchivoExcelConferencias(java.util.Map)
	 */
	public Map cargarArchivoExcelConferencias(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		List listRegistros = new ArrayList();
		int fila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigoRegion = (String)mapRow.get("0");
			if (StringUtils.contains(codigoRegion, "."))
				codigoRegion = StringUtils.remove(codigoRegion, ".0");
			String codigoZona = (String)mapRow.get("1");
			String local = (String)mapRow.get("2");
			String direccion = (String)mapRow.get("3");
			String fecha = (String)mapRow.get("4");
			String hora = (String)mapRow.get("5");
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data

			Map mapRegistro = new HashMap();
			mapRegistro.put("numeroFila", filaExcel);
			mapRegistro.put("codigoRegion", codigoRegion);
			mapRegistro.put("codigoZona", codigoZona);
			mapRegistro.put("local", local);
			mapRegistro.put("direccion", direccion);
			mapRegistro.put("fecha", fecha);
			mapRegistro.put("hora", hora);

			fila +=1;
			listRegistros.add(mapRegistro);				
		    
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("archivoConRegistros", Constants.SI);
		resultado.put("listRegistros", listRegistros);
		resultado.put("totalRegistros", String.valueOf(fila));
		if (listRegistros.size() == 0)
			resultado.put("archivoConRegistros", Constants.NO);
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#executeValidarCargaMasiva(java.util.Map)
	 */
	public List executeValidarCargaMasiva(Map params) {
		Usuario usuario = (Usuario)params.get("usuario");
		String codigoPeriodo = (String)params.get("codigoPeriodo");
		String codigoPeriodoSiguiente = (String)params.get("codigoPeriodoSiguiente");
		List listRegistros = (List)params.get("listRegistros");
		
		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPeriodo", codigoPeriodo);
		criteriaAux.put("codigoPeriodoSiguiente", codigoPeriodoSiguiente);
		List listaProducto = new ArrayList();
				
		for(int i=0; i<listRegistros.size(); i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			String mensajeError = new String();
			String codigoCUV = (String)mapRegistro.get("codigoCUV");
			criteriaAux.put("codigoCUV", codigoCUV);
			
			Map producto = new HashMap();
			String numeroFila = new Integer(i + 1).toString().trim();
			
			if (StringUtils.isBlank(codigoCUV)) {
				mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVEnBlanco",
						null,getLocale(usuario));
				producto.put("codigoCUV", "");
				producto.put("codigoPeriodo", codigoPeriodo);
				producto.put("descripcionError", mensajeError);
			}
			else {
				boolean continuar = true;
				if (listaProducto.size() > 0 ) {
					for (int x= 0; x < listaProducto.size(); x ++) {
						Map productoTempo = (Map)listaProducto.get(x);
						String codigoCUVTempo = (String)productoTempo.get("codigoCUV");
						if (StringUtils.isNotBlank(codigoCUVTempo)) {
							if (codigoCUVTempo.equals(codigoCUV)) {
								mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVRepetido",
										null,getLocale(usuario));
								producto = new HashMap();
								producto.put("codigoCUV", codigoCUV);
								producto.put("codigoPeriodo", codigoPeriodo);
								producto.put("descripcionError", mensajeError);
								producto.put("precio", new BigDecimal(0));
								producto.put("pagina", new BigDecimal(0));
								producto.put("descripcionProducto", " ");
								producto.put("descripcionCatalogo", " ");
								producto.put("catalogo", " ");
								continuar = false;
								break;
							}
						}
					}
		
				}
				if (continuar) {
					producto = procesoMENGenerarMensajesDAO.getDatosProductoCUVPeriodo(criteriaAux);
					if (producto == null) {
						mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVNoExiste",
								null,getLocale(usuario));
						producto = new HashMap();
						producto.put("codigoCUV", codigoCUV);
						producto.put("codigoPeriodo", codigoPeriodo);
						producto.put("descripcionError", mensajeError);
						producto.put("precio", new BigDecimal(0));
						producto.put("pagina", new BigDecimal(0));
						producto.put("descripcionProducto", " ");
						producto.put("descripcionCatalogo", " ");
						producto.put("catalogo", " ");
					}
				}	
			}
			producto.put("numeroFila", numeroFila);
			listaProducto.add(producto);
		}
		
		return listaProducto;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#executeValidarCargaMasivaConferencias(java.util.Map)
	 */
	public List executeValidarCargaMasivaConferencias(Map params) {
		Usuario usuario = (Usuario)params.get("usuario");
		String codigoPeriodo = (String)params.get("codigoPeriodo");
		String codigoPeriodoSiguiente = (String)params.get("codigoPeriodoSiguiente");
		List listRegistros = (List)params.get("listRegistros");
		                                       
		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPeriodo", codigoPeriodo);
		criteriaAux.put("codigoPeriodoSiguiente", codigoPeriodoSiguiente);
		List listaProducto = new ArrayList();
		
		for(int i=0; i<listRegistros.size(); i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			String mensajeError = new String();
			String codigoRegion = (String)mapRegistro.get("codigoRegion");
			String codigoZona = (String)mapRegistro.get("codigoZona");
			String local = (String)mapRegistro.get("local");
			String direccion = (String)mapRegistro.get("direccion");
			String fecha = (String)mapRegistro.get("fecha");
			String hora = (String)mapRegistro.get("hora");
			criteriaAux.put("codigoRegion", codigoRegion);
			criteriaAux.put("codigoZona", codigoZona);
			
			Map region = new HashMap();
			String numeroFila = new Integer(i + 1).toString().trim();
			
			if (StringUtils.isBlank(codigoRegion)) {
				mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoRegionEnBlanco",
						null,getLocale(usuario));
				region.put("codigoRegion", "");
				region.put("codigoZona", "");
				region.put("codigoPeriodo", codigoPeriodo);
				region.put("descripcionError", mensajeError);
			}
			else {
				boolean continuar = true;
				if (listaProducto.size() > 0 ) {
					for (int x= 0; x < listaProducto.size(); x ++) {
						Map productoTempo = (Map)listaProducto.get(x);
						String codigoRegionTempo = (String)productoTempo.get("codigoRegion");
						/*if (StringUtils.isNotBlank(codigoRegionTempo)) {
							if (codigoRegionTempo.equals(codigoRegion)) {
								mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoRegionRepetido",
										null,getLocale(usuario));
								region = new HashMap();
								region.put("codigoRegion", codigoRegionTempo);
								region.put("codigoPeriodo", codigoPeriodo);
								region.put("descripcionError", mensajeError);
								region.put("precio", new BigDecimal(0));
								region.put("pagina", new BigDecimal(0));
								region.put("descripcionProducto", " ");
								region.put("descripcionCatalogo", " ");
								region.put("catalogo", " ");
								continuar = false;
								break;
							}
						}*/
					}
		
				}
				if (continuar) {
					region = procesoMENGenerarMensajesDAO.getDatosRegion(criteriaAux);
					
					if (region == null) {
						mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoRegionNoExiste",
								null,getLocale(usuario));
						region = new HashMap();
						region.put("codigoRegion", codigoRegion);
						region.put("codigoPeriodo", codigoPeriodo);
						region.put("descripcionError", mensajeError);
						region.put("local", " ");
						region.put("direccion", " ");
						region.put("fecha", " ");
						region.put("hora", " ");
					}else{
						Map zona = new HashMap();
						region.put("codigoZona", codigoZona);
						zona = procesoMENGenerarMensajesDAO.getDatosZonaByRegion(region);
						
						if(zona == null){
							mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoZonaNoExiste",
									null,getLocale(usuario));
							region = new HashMap();
							region.put("codigoRegion", codigoRegion);
							region.put("codigoZona", codigoZona);
							region.put("codigoPeriodo", codigoPeriodo);
							region.put("descripcionError", mensajeError);
							region.put("local", " ");
							region.put("direccion", " ");
							region.put("fecha", " ");
							region.put("hora", " ");
						}else{
							region.put("codigoRegion", MapUtils.getString(zona, "codigoRegion"));
							region.put("codigoZona", MapUtils.getString(zona, "codigoZona"));
							region.put("gerente", MapUtils.getString(zona, "gerente"));
							region.put("local", local);
							region.put("direccion", direccion);
							region.put("fecha", fecha);
							region.put("hora", hora);
						}
					}
				}	
			}
			region.put("numeroFila", numeroFila);
			listaProducto.add(region);
		}
		
		return listaProducto;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#executeActualizarCargaMasiva(java.util.Map)
	 */
	public void executeActualizarCargaMasiva(Map params) {
		Usuario usuario = (Usuario)params.get("usuario");
		String codigoPais = (String)params.get("codigoPais");
		List listRegistros = (List)params.get("listRegistros");
		String codigoPeriodo = (String)params.get("codigoPeriodo");

		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPeriodo", codigoPeriodo);
		criteriaAux.put("codigoPais", codigoPais);
		criteriaAux.put("codigoUsuario", usuario.getLogin());
		Boolean validarEsMenosMaximoRegistrosOK = true;
		String temp = (String) params.get("registrosGrabar");
		Integer registrosGrabar = new Integer(temp.trim());
		temp = (String) params.get("registrosValidos");
		Integer registrosValidos =  new Integer(temp.trim());
		if (registrosValidos > registrosGrabar) {
			validarEsMenosMaximoRegistrosOK = false;
		}
		params.put("validarEsMenosMaximoRegistrosOK", validarEsMenosMaximoRegistrosOK);
		
		Integer contador = 0;
	    for(int i=0; i<listRegistros.size();i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			String codigoCUV= (String)mapRegistro.get("codigoCUV");
			if(StringUtils.isNotBlank(codigoCUV)) {
				String descripcionError = (String)mapRegistro.get("descripcionError");
				String descripcionProducto = (String)mapRegistro.get("descripcionProducto");
				BigDecimal precioTemp = (BigDecimal)mapRegistro.get("precio");
				String precio = precioTemp.toString().trim();
				String descripcionCatalogo = (String)mapRegistro.get("descripcionCatalogo");
				BigDecimal paginaTemp = (BigDecimal)mapRegistro.get("pagina");
				String pagina = paginaTemp.toString().trim();;
				
				if(StringUtils.isBlank(descripcionError)) {
					criteriaAux.put("codigoCUV", codigoCUV);
					criteriaAux.put("descripcionProducto", descripcionProducto);
					criteriaAux.put("precio", precio);
					criteriaAux.put("descripcionCatalogo", descripcionCatalogo);
					criteriaAux.put("pagina", pagina);
					this.procesoMENGenerarMensajesDAO.executeProcesoActualizarFaltanteAnunciado(criteriaAux);
					contador++;
					if (contador >= registrosGrabar) {
						return;
					}
				}
			}	
		}	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#executeActualizarCargaMasivaConferencias(java.util.Map)
	 */
	public void executeActualizarCargaMasivaConferencias(Map params) {
		Usuario usuario = (Usuario)params.get("usuario");
		String codigoPais = (String)params.get("codigoPais");
		List listRegistros = (List)params.get("listRegistros");
		String codigoPeriodo = (String)params.get("codigoPeriodo");

		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPeriodo", codigoPeriodo);
		criteriaAux.put("codigoPais", codigoPais);
		criteriaAux.put("codigoUsuario", usuario.getLogin());

		String temp = (String) params.get("registrosValidos");
		Integer registrosValidos = new Integer(temp.trim());
		
	    for(int i=0; i<listRegistros.size();i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			String codigoRegion = (String)mapRegistro.get("codigoRegion");
			
			if(StringUtils.isNotBlank(codigoRegion)) {
				String descripcionError = (String)mapRegistro.get("descripcionError");
				String codigoZona = (String)mapRegistro.get("codigoZona");
				String gerente = (String)mapRegistro.get("gerente");
				String local = (String)mapRegistro.get("local");
				String direccion = (String)mapRegistro.get("direccion");
				String fecha = (String)mapRegistro.get("fecha");
				String hora = (String)mapRegistro.get("hora");
				
				if(StringUtils.isBlank(descripcionError)) {
					criteriaAux.put("codigoRegion", codigoRegion);
					criteriaAux.put("codigoZona", codigoZona);
					criteriaAux.put("gerente", gerente);
					criteriaAux.put("local", local);
					criteriaAux.put("direccion", direccion);
					criteriaAux.put("fecha", fecha);
					criteriaAux.put("hora", hora);
					this.procesoMENGenerarMensajesDAO.executeProcesoActualizarConferencias(criteriaAux);
				}
			}	
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#deleteCargaMasivaFaltanteAnunciado(java.util.Map)
	 */
	public void deleteCargaMasivaFaltanteAnunciado(Map params) {
		this.procesoMENGenerarMensajesDAO.deleteCargaMasivaFaltanteAnunciado(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#deleteCargaMasivaConferencias(java.util.Map)
	 */
	public void deleteCargaMasivaConferencias(Map params) {
		this.procesoMENGenerarMensajesDAO.deleteCargaMasivaConferencias(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#getDevuelveFaltanteAnunciado(java.util.Map)
	 */
	public List getDevuelveFaltanteAnunciado(Map params) {
		return this.procesoMENGenerarMensajesDAO.getDevuelveFaltanteAnunciado(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#getDevuelveConferencias(java.util.Map)
	 */
	public List getDevuelveConferencias(Map params) {
		return this.procesoMENGenerarMensajesDAO.getDevuelveConferencias(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#deleteFaltanteAnunciado(java.util.Map)
	 */
	public void deleteFaltanteAnunciado(Map params) {
		this.procesoMENGenerarMensajesDAO.deleteFaltanteAnunciado(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#deleteConferencias(java.util.Map)
	 */
	public void deleteConferencias(Map params) {
		this.procesoMENGenerarMensajesDAO.deleteConferencias(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#getDevuelveFaltanteAnunciadoCodigoCUV(java.util.Map)
	 */
	public Map getDevuelveFaltanteAnunciadoCodigoCUV(Map params) {
		return this.procesoMENGenerarMensajesDAO.getDevuelveFaltanteAnunciadoCodigoCUV(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#insertFaltanteAnunciado(java.util.Map)
	 */
	public void insertFaltanteAnunciado(Map params) {
		this.procesoMENGenerarMensajesDAO.executeProcesoActualizarFaltanteAnunciado(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#insertConferencias(java.util.Map)
	 */
	public void insertConferencias(Map params) {
		this.procesoMENGenerarMensajesDAO.executeProcesoActualizarConferencias(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#getDevuelveCodigoCampanha(java.util.Map)
	 */
	public String getDevuelveCodigoCampanha(Map params) {
		return this.procesoMENGenerarMensajesDAO.getDevuelveCodigoCampanha(params);
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#getGerenteZona(java.util.Map)
	 */
	public Map getGerenteZona(Map criteria) {
		return procesoMENGenerarMensajesDAO.getDatosZonaByRegion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMENCargaMasivaInformacionMensajesService#getDevuelveCodigoRegionZona(java.util.Map)
	 */
	public String getDevuelveCodigoRegionZona(Map criteria) {
		return procesoMENGenerarMensajesDAO.getDevuelveCodigoRegionZona(criteria);
	}
}