/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBCargarCronogramaDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCOBCargarCronograma;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargarCronogramaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;


/**
 * @author Carlos Bazalar
 *
 */

@Service("spusicc.procesoCOBCargarCronogramaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBCargarCronogramaServiceImpl extends BaseService implements ProcesoCOBCargarCronogramaService {
	
	@Resource(name="spusicc.procesoCOBCargarCronogramaDAO")
	ProcesoCOBCargarCronogramaDAO procesoCOBCargarCronogramaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return this.procesoCOBCargarCronogramaDAO.obtenerPathUpload(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#getTiposEtapa(java.util.Map)
	 */
	public List getTiposEtapa(Map datos) {
		return this.procesoCOBCargarCronogramaDAO.getTiposEtapa(datos);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#executeValidarCargarCronograma(java.util.Map)
	 */
	public List executeValidarCargarCronograma(Map datos) throws Exception {
		String directorioTemporal = (String)datos.get("directorioTemporal");
		String nombreArchivo = (String)datos.get("nombreArchivo");
		String extensionArchivo = (String)datos.get("extensionArchivo");	
		
		log.debug("Inicia Procesar Archivo Excel");
		List listaRetorno = new ArrayList();
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL) )
			listaRetorno = this.procesarArchivoExcel(directorioTemporal, nombreArchivo, datos);
		log.debug("Finalizando Procesar Archivo Excel");
		return listaRetorno;
	
	}
	
	
	/**
	 * Metodo que procesa el archivo excel
	 * @param directorioTemporal
	 * @param nombreArchivo
	 * @throws Exception
	 */
	private List procesarArchivoExcel(String directorioTemporal, String nombreArchivo, Map datos) throws Exception {
		List listaRetorno = new ArrayList();
		String codigoPais = (String) datos.get("codigoPais");
		String codigoEtapa = (String) datos.get("codigoEtapa");
		
		Map etapaMap = new HashMap();
		etapaMap.put("codigoPais", codigoPais);
		etapaMap.put("codigoEtapa", codigoEtapa);
		String descripcionEtapa = this.procesoCOBCargarCronogramaDAO.getDevuelveDescripcionEtapa(etapaMap);
		Usuario usuario = (Usuario)datos.get("usuario");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		
		//nos colocamos en la primera hoja del documento Excel
		excelUtil.initSheet(0);
		
		//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
		excelUtil.next();
		int fila = 0;
		Map criteria = new HashMap();
		String mensajeError = "";
		boolean registroOK = false;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			EstructuraCOBCargarCronograma estructura = new EstructuraCOBCargarCronograma();
			
			fila = fila + 1;
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());
			String codigoPeriodo = (String)mapRow.get("0");
			String codigoRegion = (String)mapRow.get("1");
			String fechaInicio = (String)mapRow.get("2");
			String fechaCierre = (String)mapRow.get("3");
			codigoPeriodo = this.devuelveValorFormateado(codigoPeriodo);
			codigoRegion = this.devuelveValorFormateado(codigoRegion);
			
			estructura.setCodigoPais(codigoPais);
			estructura.setCodigoEtapa(codigoEtapa);
			estructura.setDescripcionEtapa(descripcionEtapa);
			estructura.setCodigoPeriodo(codigoPeriodo);
			estructura.setCodigoRegion(codigoRegion);
			estructura.setFechaInicio(fechaInicio);
			estructura.setFechaCierre(fechaCierre);
			estructura.setRegistroOK(true);
			estructura.setObservacion("");
			estructura.setFila(fila);
	
			///Realizamos el proceso de Validacion de los datos ingresados
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEtapa", codigoEtapa);
			criteria.put("codigoRegion", codigoRegion);
			Integer valor = this.procesoCOBCargarCronogramaDAO.getValidarRegistroCargarCronograma(criteria);	
			if (valor.intValue() == 0 ) {
				estructura.setRegistroOK(false);
				mensajeError = messageSource.getMessage("procesoCOBCargarCronograma.error.noExisteRegistro",null,getLocale(usuario));		
				estructura.setObservacion(mensajeError);
			}
			else {
				valor = this.procesoCOBCargarCronogramaDAO.getValidarCargarCronograma(criteria);	
				if (valor.intValue() == 0 ) {
					estructura.setRegistroOK(false);
					mensajeError = messageSource.getMessage("procesoCOBCargarCronograma.registroAsignado",null,getLocale(usuario));		
					estructura.setObservacion(mensajeError);
				}
				else {
					registroOK = true;
				}
			}
			listaRetorno.add(estructura);

		}
		datos.put("cantidadRegistrosCargados", listaRetorno.size());
		datos.put("descripcionEtapa", descripcionEtapa);
		if (registroOK)
			datos.put("registroOK", "S");
		
		excelUtil.cerrar();
		return listaRetorno;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#executeProcesarCargarCronograma(java.util.Map)
	 */
	public List executeProcesarCargarCronograma(Map datos){		
		List listaRetorno = (ArrayList) datos.get("lista");
		Usuario usuario = (Usuario)datos.get("usuario");
		String mensaje = messageSource.getMessage("procesoCOBCargarCronograma.registroActualizado",null,getLocale(usuario));		
		
		for (int i= 0; i < listaRetorno.size(); i++) {
			EstructuraCOBCargarCronograma estructura = (EstructuraCOBCargarCronograma) listaRetorno.get(i);
			if (estructura.isRegistroOK()) {
				this.procesoCOBCargarCronogramaDAO.executeProcesarCargarCronograma(estructura);
				estructura.setObservacion(mensaje);
				 listaRetorno.set(i, estructura);
			}
			
		}
		return listaRetorno;
	}
	
	
	
	/**
	 * @param valor
	 * @return
	 */
	private String devuelveValorFormateado(String valor) {
		boolean encontro = StringUtils.contains(valor, ".");
		if (encontro) {
			String lista[] = StringUtils.split(valor, ".");
			return lista[0];
		}
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargarCronogramaService#obtenerPathUploadEstandar(java.util.Map)
	 */
	public String obtenerPathUploadEstandar(Map datos){
		return this.procesoCOBCargarCronogramaDAO.obtenerPathUploadEstandar(datos);
	}
}
