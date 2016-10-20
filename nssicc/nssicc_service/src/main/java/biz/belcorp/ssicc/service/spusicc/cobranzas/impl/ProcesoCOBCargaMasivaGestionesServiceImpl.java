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
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBCargaMasivaGestionesDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCOBCargaMasivaGestiones;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargaMasivaGestionesService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author Gonzalo Huertas
 *
 */
@Service("spusicc.procesoCOBCargaMasivaGestionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBCargaMasivaGestionesServiceImpl extends BaseService implements ProcesoCOBCargaMasivaGestionesService {
	
	@Resource(name="spusicc.procesoCOBCargaMasivaGestionesDAO")
	ProcesoCOBCargaMasivaGestionesDAO ProcesoCOBCargaMasivaGestionesDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return this.ProcesoCOBCargaMasivaGestionesDAO.obtenerPathUpload(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#getTiposEtapa(java.util.Map)
	 */
	public List getTiposEtapa(Map datos) {
		return this.ProcesoCOBCargaMasivaGestionesDAO.getTiposEtapa(datos);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#executeValidarCargarCronograma(java.util.Map)
	 */
	public List executeValidarCargaMasivaGestiones(Map datos) throws Exception {
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
		String descripcionEtapa = this.ProcesoCOBCargaMasivaGestionesDAO.getDevuelveDescripcionEtapa(etapaMap);
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
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			EstructuraCOBCargaMasivaGestiones estructura = new EstructuraCOBCargaMasivaGestiones();
			
			fila = fila + 1;
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());
			
			String numeroOrden = (String)mapRow.get("0");
			String codigoCobrador = (String)mapRow.get("1");
			String codigoDeuda = (String)mapRow.get("2");
			String codigoPeriodo = (String)mapRow.get("3");
			String codigoCliente = (String)mapRow.get("4");
			String tipoAccion = (String)mapRow.get("5");
			String codigoAccion = (String)mapRow.get("6");
			String observacion = (String)mapRow.get("7");
			String fechaGestion = (String)mapRow.get("8");
			String fechaCompromisoPago = (String)mapRow.get("9");
			String importeCompromisoPago = (String)mapRow.get("10");
			String nuevaDireccion = (String)mapRow.get("11");
			String nuevoTelefono = (String)mapRow.get("12");
			String tipoTelefono = (String)mapRow.get("13");
			
			numeroOrden = this.devuelveValorFormateado(numeroOrden);
			codigoPeriodo = this.devuelveValorFormateado(codigoPeriodo);
			codigoCliente = this.devuelveValorFormateado(codigoCliente);
			nuevoTelefono = this.devuelveValorFormateado(nuevoTelefono);
			tipoTelefono = this.devuelveValorFormateado(tipoTelefono);
			
			estructura.setNumeroOrden(numeroOrden);
			estructura.setCodigoCobrador(codigoCobrador);
			estructura.setCodigoDeuda(codigoDeuda);
			estructura.setCodigoPeriodo(codigoPeriodo);
			estructura.setCodigoCliente(codigoCliente);
			estructura.setTipoAccion(tipoAccion);
			estructura.setCodigoAccion(codigoAccion);
			estructura.setObservacion(observacion);
			estructura.setFechaGestion(fechaGestion);
			estructura.setFechaCompromisoPago(fechaCompromisoPago);
			estructura.setImporteCompromisoPago(importeCompromisoPago);
			estructura.setNuevaDireccion(nuevaDireccion);
			estructura.setNuevoTelefono(nuevoTelefono);
			estructura.setTipoTelefono(tipoTelefono);
			estructura.setRegistroOK(true);
			estructura.setFila(fila);
	
			///Realizamos el proceso de Validacion de los datos ingresados
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEtapa", codigoEtapa);
			criteria.put("codigoCliente", codigoCliente);
			criteria.put("codigoEtapa", codigoDeuda);
			criteria.put("tipoAccion", tipoAccion);
			criteria.put("codigoAccion", codigoAccion);
			criteria.put("codigoCobrador", codigoCobrador);
			
			
			Integer valor = this.ProcesoCOBCargaMasivaGestionesDAO.getValidarCodigoCliente(criteria);	
			if (valor.intValue() == 0 ) {
				mensajeError = messageSource.getMessage("procesoCOBCargaMasivaGestiones.error.noExisteCliente",null,getLocale(usuario));		
				datos.put("mensajeError", "El registro " + fila + " del archivo, contiene erores: " + mensajeError);
				
				break;
			}
			else {
				valor = this.ProcesoCOBCargaMasivaGestionesDAO.getValidarCodigoEtapaAccion(criteria);	
				if (valor.intValue() == 0 ) {
					mensajeError = messageSource.getMessage("procesoCOBCargaMasivaGestiones.error.noExisteCodigoEtapaAccion",null,getLocale(usuario));		

					datos.put("mensajeError", "El registro " + fila + " del archivo, contiene erores: " + mensajeError);
					
					break;
				}
				else {
					valor = this.ProcesoCOBCargaMasivaGestionesDAO.getValidarCodigoCobrador(criteria);
					if (valor.intValue() == 0 ) {
						mensajeError = messageSource.getMessage("procesoCOBCargaMasivaGestiones.error.noExisteCobrador",null,getLocale(usuario));
						datos.put("mensajeError", "El registro " + fila + " del archivo, contiene erores: " + mensajeError);
						
						break;
						
					}
					else {
						listaRetorno.add(estructura);
					}
				}
			}
		}
		datos.put("cantidadRegistrosCargados", listaRetorno.size());
		datos.put("descripcionEtapa", descripcionEtapa);
		if (StringUtils.isBlank(mensajeError)){
			datos.put("registroOK", "S");
			ingresarTemporal(listaRetorno);
		}
		excelUtil.cerrar();
		
		return listaRetorno;
	}
	
	public void ingresarTemporal(List lista){
		this.ProcesoCOBCargaMasivaGestionesDAO.deleteTemporalCargaMasivaGestiones();
		if(lista!=null){
			for(int i=0;i<lista.size();i++){
				EstructuraCOBCargaMasivaGestiones estructura = (EstructuraCOBCargaMasivaGestiones)lista.get(i);
				this.ProcesoCOBCargaMasivaGestionesDAO.insertCargaMasivaGestiones(estructura);
			}
		}	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBCargarCronogramaService#executeProcesarCargarCronograma(java.util.Map)
	 */
	public String executeProcesarCargaMasivaGestiones(Map datos){		
		return this.ProcesoCOBCargaMasivaGestionesDAO.executeProcesarCargaMasivaGestiones(datos);
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
}
