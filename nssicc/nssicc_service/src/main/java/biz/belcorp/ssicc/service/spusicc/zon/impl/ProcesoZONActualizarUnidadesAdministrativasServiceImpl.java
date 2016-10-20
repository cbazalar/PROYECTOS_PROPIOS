package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.ConsultoraATrasvasar;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadAdministrativaACrear;
import biz.belcorp.ssicc.service.edu.gen.GenericoEDUFacadeService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.service.util.DBFUtil;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONActualizarUnidadesAdministrativasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoZONUniAdmService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoZONActualizarUnidadesAdministrativasServiceImpl extends BaseService implements ProcesoZONActualizarUnidadesAdministrativasService {
	
	@Resource(name="spusicc.procesoZONUniAdmDAO")
	private ProcesoZONActualizarUnidadesAdministrativasDAO procesoZONActualizarUnidadesAdministrativasDAO;
	
	@Resource(name="edu.genericoEDUFacadeService")
	private GenericoEDUFacadeService genericoEDUFacadeService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#validarRegiones(java.util.Map)
	 */
	public void validarRegiones(Map params) {
		
		String[] regiones = (String[])params.get("codigosRegion");
		String validarRegionCerrada = (String)params.get("validarRegionCerrada");
		String validarRegionFacturando = (String)params.get("validarRegionFacturando");
		String mensajeRetorno = "";
		
		StringBuffer regionesCerradas = new StringBuffer(); 
		StringBuffer regionesFacturando = new StringBuffer(); 
		
		for(int i=0; i<regiones.length; i++){
			log.debug("Validando region... "+regiones[i]);
			params.put("codigoRegion", regiones[i]);
			String codigoRetorno = null;
			
			if(validarRegionCerrada!=null) {
				this.procesoZONActualizarUnidadesAdministrativasDAO.validarRegionCerrada(params);
				codigoRetorno = (String)params.get("codRetorno");
				if(StringUtils.equals(codigoRetorno, Constants.NUMERO_UNO)){
					regionesCerradas.append(",");
					regionesCerradas.append(regiones[i]);
				}
			}	
			
			if(validarRegionFacturando!=null) {
				this.procesoZONActualizarUnidadesAdministrativasDAO.validarRegionFacturando(params);
				codigoRetorno = (String)params.get("codRetorno");
				if(StringUtils.equals(codigoRetorno, Constants.NUMERO_UNO)){
					regionesFacturando.append(",");
					regionesFacturando.append(regiones[i]);
				}
			}	
		}
		
		if(regionesCerradas.length()>0){
			mensajeRetorno = " CERRADAS: "+regionesCerradas.substring(1).toString();
		}
		if(regionesFacturando.length()>0){
			mensajeRetorno = mensajeRetorno + " FACTURANDO: "+regionesFacturando.substring(1).toString();
		}
		
		if(mensajeRetorno.length()>0){
			params.put("codRetorno", "1");
			params.put("msgRetorno", mensajeRetorno);
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#cargarArchivos(java.util.Map)
	 */
	public void cargarArchivos(Map params) {
		log.debug("Parametros para 'cargarArchivos'"+params);
		String varError = "00";
		try{
			String[] archivos = (String[])params.get("archivos");
			for(int i=0; i<archivos.length; i++){
				this.procesarArchivo(params, archivos[i]);
			}
			
			varError= "01";
			String indRegionZona=params.get("indRegionZona").toString();
			if(StringUtils.equals(indRegionZona, Constants.ZON_UNID_ADMIN_IND_REGION))
				this.eliminarRegionesNoEnviadas((List)params.get("codigosRegion"));
			if(StringUtils.equals(indRegionZona, Constants.ZON_UNID_ADMIN_IND_ZONA))
				this.eliminarZonasNoEnviadas((List)params.get("codigosZona"));
			
			varError= "02";
			this.completarSGV(params);
			varError= "03";
			this.primeraValidacion(params);
			
		}catch(Exception e){
			log.error(" e :'"+ varError + " - " + e );
			params.put("codRetorno", "-1");
			params.put("msgRetorno", e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#iniciarTablas(java.util.Map)
	 */
	public void iniciarTablas(Map params) {
		this.procesoZONActualizarUnidadesAdministrativasDAO.iniciarTablasUnidadAdministrativa(params);
	}
	
	/**
	 * Lee el archivo excel, recuperando la informacion de las unidades administrativas y seran
	 * cargadas en una tabla temporal para su validacion y procesamiento
	 * 
	 * @param params
	 * @param nombreArchivo
	 * @throws Exception
	 */
	private void procesarArchivo(Map params, String nombreArchivo) throws Exception{
		
		String directorioTemporal = (String)params.get("directorioTemporal");
		String extensionArchivo = (String)params.get("extensionArchivo");
		String codigoPais = (String)params.get("codigoPais");
		String codigoMarca = (String)params.get("codigoMarca");
		String codigoCanal = (String)params.get("codigoCanal");
		String codigoPeriodo = (String)params.get("codigoPeriodo");
		String colSgv = (String)params.get("colSgv");
		String colReg = (String)params.get("colReg");
		String colZon = (String)params.get("colZon");
		String colSec = (String)params.get("colSec");
		String colTer = (String)params.get("colTer");
		String colUbi = (String)params.get("colUbi");
		String colNse = (String)params.get("colNse");
		
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL)){
			//Abrimos el archivo Excel para su procesamiento		
			ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
			//nos colocamos en la primera hoja del documento Excel
			excelUtil.initSheet(0);
			//nos pasamos a la segunda fila, ya que el primero se encuentra los campos que
			//fueron presentados para ser seleccionadas en la pantalla
			excelUtil.next();
			int fila = 2;
			// ... a leer el archivo
			while(excelUtil.hasNext()) {
				String varStr=null;
				Map mapRow = excelUtil.next();
				UnidadAdministrativa unidAdmi = new UnidadAdministrativa();
				unidAdmi.setNombreArchivo(nombreArchivo);
				unidAdmi.setCodigoPais(codigoPais);
				unidAdmi.setCodigoMarca(codigoMarca);
				unidAdmi.setCodigoCanal(codigoCanal);
				unidAdmi.setCodigoPeriodo(codigoPeriodo);
				
				if(colSgv!=null && colSgv.trim().length()>0){
					if((String)mapRow.get(colSgv) != null ){
						int varIntDato = ((String)mapRow.get(colSgv)).indexOf('.');
						if (varIntDato >= 0 ){
							varStr = ((String)mapRow.get(colSgv)).substring(0, varIntDato);
							 }
						else {
							varStr = (String)mapRow.get(colSgv);
						}
						unidAdmi.setCodigoSGV(varStr);
					}
					else {
						unidAdmi.setCodigoSGV((String)mapRow.get(colSgv));
					}
				}
				
				// Si no se rellena el codigo de SGV se actualiza con un SP masivamente con la SGV de la region
				if((String)mapRow.get(colReg) != null ){
					int varIntDato = ((String)mapRow.get(colReg)).indexOf('.');
					if (varIntDato >= 0 ){
						varStr = ((String)mapRow.get(colReg)).substring(0, varIntDato);
						 }
					else {
						varStr = (String)mapRow.get(colReg);
					}
					unidAdmi.setCodigoRegion(varStr);	
				}
				else {
					unidAdmi.setCodigoRegion((String)mapRow.get(colReg));
				}
				
				//recuperamos el codigo de zona	
				if((String)mapRow.get(colZon) != null ){
					int varIntDato = ((String)mapRow.get(colZon)).indexOf('.');
					if (varIntDato >= 0 ){
						varStr = ((String)mapRow.get(colZon)).substring(0, varIntDato);
						 }
					else {
						varStr = (String)mapRow.get(colZon);
					}
					unidAdmi.setCodigoZona(varStr);	
				}
				else {
					unidAdmi.setCodigoZona((String)mapRow.get(colZon));
				}
				
				log.debug("(String)mapRow.get(colSec): "+ (String)mapRow.get(colSec));

				unidAdmi.setCodigoSeccion((String)mapRow.get(colSec));

				if((String)mapRow.get(colTer) != null ){
					int varIntDato = ((String)mapRow.get(colTer)).indexOf('.');
					if (varIntDato >= 0 ){
						varStr = ((String)mapRow.get(colTer)).substring(0, varIntDato);
						 }
					else {
						varStr = (String)mapRow.get(colTer);
					}
					unidAdmi.setCodigoTerritorio(varStr);	
				}
				else {
					unidAdmi.setCodigoTerritorio((String)mapRow.get(colTer));
				}

				unidAdmi.setUbigeo((String)mapRow.get(colUbi));

				if((String)mapRow.get(colNse) != null ){
					int varIntDato = ((String)mapRow.get(colNse)).indexOf('.');
					if (varIntDato >= 0 ){
						varStr = ((String)mapRow.get(colNse)).substring(0, varIntDato);
						 }
					else {
						varStr = (String)mapRow.get(colNse);
					} 
					unidAdmi.setCodigoNSE(varStr);	
				}
				else{
					unidAdmi.setCodigoNSE((String)mapRow.get(colNse));
				}

				
				//Validamos las longitudes de los campos SGV, Region, Zona, Seccion, Ubigeo, Territorio, NSE
				if(colSgv!=null && colSgv.trim().length()>0)
					validarLongitudesCamposUA(nombreArchivo, fila, "SGV", unidAdmi.getCodigoSGV());
				
				validarLongitudesCamposUA(nombreArchivo, fila, "Region", unidAdmi.getCodigoRegion());
				validarLongitudesCamposUA(nombreArchivo, fila, "Zona", unidAdmi.getCodigoZona());
				validarLongitudesCamposUA(nombreArchivo, fila, "Seccion", unidAdmi.getCodigoSeccion());
				validarLongitudesCamposUA(nombreArchivo, fila, "Territorio", unidAdmi.getCodigoTerritorio());
				
				if((unidAdmi.getCodigoTerritorio()!=null) && (unidAdmi.getCodigoTerritorio().length()>0)) 
					validarLongitudesCamposUA(nombreArchivo, fila, "Ubigeo", unidAdmi.getUbigeo());
				
				if(colNse!=null && colNse.trim().length()>0)
					validarLongitudesCamposUA(nombreArchivo, fila, "NSE", unidAdmi.getCodigoNSE());
								
				procesoZONActualizarUnidadesAdministrativasDAO.insertUnidadAdministrativa(unidAdmi);
				fila = fila + 1;
			}
			
			// Se verifica si es la(s) region(es) seleccionada(s) se encuentran en el archivo
			// Si es que no se encuentran en el archivo se trata de una eliminacion de la region(es) seleccionada(s)
			String indRegionZona = (String)params.get("indRegionZona");
			if(StringUtils.equals(indRegionZona, Constants.ZON_UNID_ADMIN_IND_REGION)){
				String nContadorRegistros = Constants.NUMERO_CERO;
				List regiones = (List)params.get("codigosRegion");
				String sCodRegion = new String();
				
				for(int i=0; i<regiones.size(); i++){
					sCodRegion = (String)regiones.get(i);
					nContadorRegistros = procesoZONActualizarUnidadesAdministrativasDAO.obtenerRegionProcesar(sCodRegion);
					
					if (Constants.NUMERO_CERO.equals(nContadorRegistros)){
						UnidadAdministrativa unidAdmi = new UnidadAdministrativa();
						unidAdmi.setNombreArchivo(nombreArchivo);
						unidAdmi.setCodigoPais(codigoPais);
						unidAdmi.setCodigoMarca(codigoMarca);
						unidAdmi.setCodigoCanal(codigoCanal);
						unidAdmi.setCodigoPeriodo(codigoPeriodo);
						
						log.debug("Insertando region... "+ sCodRegion);
						unidAdmi.setCodigoRegion(sCodRegion);
						procesoZONActualizarUnidadesAdministrativasDAO.insertUnidadAdministrativa(unidAdmi);
					}	
				}
			}
			
			// Se verifica si es la(s) region(es) seleccionada(s) se encuentran en el archivo
			// Si es que no se encuentran en el archivo se trata de una eliminacion de la region(es) seleccionada(s)
			if(StringUtils.equals(indRegionZona, Constants.ZON_UNID_ADMIN_IND_ZONA)){
				String nContadorRegistros = Constants.NUMERO_CERO;
				List zonas = (List)params.get("codigosZona");
				String sCodZona = new String();
				
				for(int i=0; i<zonas.size(); i++){
					sCodZona = (String)zonas.get(i);
					nContadorRegistros = procesoZONActualizarUnidadesAdministrativasDAO.obtenerZonaProcesar(sCodZona);
					
					if (Constants.NUMERO_CERO.equals(nContadorRegistros)){
						UnidadAdministrativa unidAdmi = new UnidadAdministrativa();
						unidAdmi.setNombreArchivo(nombreArchivo);
						unidAdmi.setCodigoPais(codigoPais);
						unidAdmi.setCodigoMarca(codigoMarca);
						unidAdmi.setCodigoCanal(codigoCanal);
						unidAdmi.setCodigoPeriodo(codigoPeriodo);
						
						log.debug("Insertando zona... "+ sCodZona);						
						unidAdmi.setCodigoZona(sCodZona);
						procesoZONActualizarUnidadesAdministrativasDAO.insertUnidadAdministrativa(unidAdmi);
					}	
				}
			}
			
			excelUtil.cerrar();

		}else if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_DBF)){
			DBFUtil dbfUtil = new DBFUtil(directorioTemporal, nombreArchivo);
			
			while(dbfUtil.hasNext()) {
				Map mapRow = dbfUtil.next();
				UnidadAdministrativa unidAdmi = new UnidadAdministrativa();
				unidAdmi.setCodigoPais(codigoPais);
				unidAdmi.setCodigoMarca(codigoMarca);
				unidAdmi.setCodigoCanal(codigoCanal);
				unidAdmi.setCodigoPeriodo(codigoPeriodo);
				if(colSgv!=null && colSgv.trim().length()>0){
					unidAdmi.setCodigoSGV((String)mapRow.get(colSgv));
				}
				// Si no se rellena el codigo de SGV se actualiza con un SP masivamente con la SGV de la region
				unidAdmi.setCodigoRegion((String)mapRow.get(colReg));
				unidAdmi.setCodigoZona((String)mapRow.get(colZon));
				unidAdmi.setCodigoSeccion((String)mapRow.get(colSec));
				unidAdmi.setCodigoTerritorio((String)mapRow.get(colTer));
				unidAdmi.setUbigeo((String)mapRow.get(colUbi));
				unidAdmi.setCodigoNSE((String)mapRow.get(colNse));
				procesoZONActualizarUnidadesAdministrativasDAO.insertUnidadAdministrativa(unidAdmi);
			}
			dbfUtil.cerrar();
		}
		
	}

	/**
	 * Validamos las longitudes de los valores enviados en el archivo Excel, de acuero
	 * al tipo de Unidad Administrativa
	 * 
	 * @param archivo
	 * @param fila
	 * @param campoUA
	 * @param valorUA
	 * @throws Exception
	 */
	private void validarLongitudesCamposUA(String archivo, int fila, String campoUA, String valorUA) throws Exception {
		boolean validoLongitud = false;
		boolean validoTipo = false;
		
		if(StringUtils.equals(campoUA, "SGV")) {
			if(valorUA!=null && (valorUA.length()==2)) {
				validoLongitud = true;
				
				if (NumberUtils.isNumber(valorUA))
					validoTipo = true;
			}
			
			if(valorUA==null || (valorUA.length()==0)) {
				validoTipo = true;
				validoLongitud = true;
			}
		}
		
		if(StringUtils.equals(campoUA, "Region")) {
			if(valorUA!=null && (valorUA.length()==2)) {
				validoLongitud = true;
				
				if (NumberUtils.isNumber(valorUA))
					validoTipo = true;
			}
		}
		
		if(StringUtils.equals(campoUA, "Seccion")) {
			validoTipo = true;
			if(valorUA!=null && (valorUA.length()==1)) {
				validoLongitud = true;
			}
		}

		if(StringUtils.equals(campoUA, "NSE")) {
			validoTipo = true;
			if(valorUA!=null && (valorUA.length()==1)) {
				validoLongitud = true;
			}
			if(valorUA==null || (valorUA.length()==0)) {
				validoLongitud = true;
			}
		}
		
		if(StringUtils.equals(campoUA, "Zona")) {
			if(valorUA!=null && (valorUA.length()==4)) {
				validoLongitud = true;
				
				if (NumberUtils.isNumber(valorUA))
					validoTipo = true;
			}
		}

		if(StringUtils.equals(campoUA, "Ubigeo")) {
			
			if(valorUA!=null && (valorUA.length()%6==0)) {
				validoLongitud = true;
				
				if (NumberUtils.isNumber(valorUA))
					validoTipo = true;
			}
		}

		if(StringUtils.equals(campoUA, "Territorio")) {
			validoTipo = true;
			
			if(valorUA==null || valorUA.length()==0) {
				validoLongitud = true;
			}

			if(valorUA!=null && (valorUA.length()<=6)) {
				validoLongitud = true;
			}
		}
		
		if (!validoLongitud) {
			throw new Exception ("Error en la longitud del campo [" + campoUA + "], de la fila (" + fila + ") del archivo:" + archivo);
		}
		
		if (!validoTipo) {
			throw new Exception ("Error en el tipo del campo [" + campoUA + "], de la fila (" + fila + ") del archivo:" + archivo);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#eliminarRegionesNoEnviadas(java.util.List)
	 */
	public void eliminarRegionesNoEnviadas(List params) {
		procesoZONActualizarUnidadesAdministrativasDAO.eliminarRegiones(params);
	}
	
	public void eliminarZonasNoEnviadas(List params) {
		procesoZONActualizarUnidadesAdministrativasDAO.eliminarZonas(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#completarSGV(java.util.Map)
	 */
	public void completarSGV(Map params) {
		procesoZONActualizarUnidadesAdministrativasDAO.completarSGV(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#primeraValidacion(java.util.Map)
	 */
	public void primeraValidacion(Map params) {
		procesoZONActualizarUnidadesAdministrativasDAO.primeraValidacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#ejecutarValidacion(java.util.Map)
	 */
	public void ejecutarValidacion(Map params) {
		procesoZONActualizarUnidadesAdministrativasDAO.ejecutarValidacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#obtenerUniAdmACrear()
	 */
	public List obtenerUniAdmACrear() {
		return procesoZONActualizarUnidadesAdministrativasDAO.obtenerUniAdmACrear();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#grabarDescripcionesUniAdmACrear(java.util.List)
	 */
	public void grabarDescripcionesUniAdmACrear(List unidaes){
		if(unidaes != null && unidaes.size()>0){
			for(int i=0; i<unidaes.size(); i++){
				UnidadAdministrativaACrear uniAdm = (UnidadAdministrativaACrear) unidaes.get(i);
				procesoZONActualizarUnidadesAdministrativasDAO.grabaDescripcionUniAdmACrear(uniAdm);
			} 
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#grabarNuevosTerritorioConsultoraATrasvasar(java.util.List, java.util.Map)
	 */
	public void grabarNuevosTerritorioConsultoraATrasvasar(List consultorasATrasvasar, Map params) throws Exception {
		if(consultorasATrasvasar != null && consultorasATrasvasar.size()>0){
			for(int i=0; i<consultorasATrasvasar.size(); i++){
				procesoZONActualizarUnidadesAdministrativasDAO.grabaCodigoTerritorioNuevoConsultoraTrasvasada((ConsultoraATrasvasar) consultorasATrasvasar.get(i));
			} 
		}
		
		this.ejecutarTrasvaseConsultorasErrorEliminacion(params);
		
		this.ejecutarActualizacionUnidadesAdministrativas(params);
		String codRetorno = (String) params.get("codRetorno");
		
		if("3".equals(codRetorno)) {
			throw new Exception("Hubo Problemas al procesar Eliminacion de Unidades Administrativas");
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#ejecutarActualizacionUnidadesAdministrativas(java.util.Map)
	 */
	public void ejecutarActualizacionUnidadesAdministrativas(Map params){
		procesoZONActualizarUnidadesAdministrativasDAO.ejecutarActualizacionUnidadesAdministrativas(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#obtenerConsultorasATrasvasar()
	 */
	public List obtenerConsultorasATrasvasar() {
		return procesoZONActualizarUnidadesAdministrativasDAO.obtenerConsultorasATrasvasar();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#ejecutarTrasvaseConsultorasErrorEliminacion(java.util.Map)
	 */
	public void ejecutarTrasvaseConsultorasErrorEliminacion(Map params) {
		procesoZONActualizarUnidadesAdministrativasDAO.ejecutarTrasvaseConsultorasErrorEliminacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#obtenerZonasDefinitivas(java.util.Map)
	 */
	public List obtenerZonasDefinitivas(Map params) {
		return procesoZONActualizarUnidadesAdministrativasDAO.obtenerZonasDefinitivas(params);
	}

	/**
	 * @param params
	 * @return
	 */
	public String obtenerCantidadRegistrosOperacion(Map params) {
		return procesoZONActualizarUnidadesAdministrativasDAO.obtenerCantidadRegistrosOperacion(params);
	}
	
	/**
	 * @param procesoZONActualizarUnidadesAdministrativasDAO
	 */
	public void setProcesoZONUniAdmDAO(ProcesoZONActualizarUnidadesAdministrativasDAO procesoZONActualizarUnidadesAdministrativasDAO) {
		this.procesoZONActualizarUnidadesAdministrativasDAO = procesoZONActualizarUnidadesAdministrativasDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#insertTerritorioConsultora(java.util.Map)
	 */
	public void insertTerritorioConsultora(Map params) throws Exception {
		String directorioTemporal = (String)params.get("directorioTemporal");
		String nombreArchivo = (String)params.get("nombreArchivo");
		
		String codigoPais = (String)params.get("codigoPais");
		String colCliente = "0";
		String colTerritorio = "1";
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		//nos colocamos en la primera hoja del documento Excel
		excelUtil.initSheet(0);
		
		//nos pasamos a la segunda fila, ya que el primero se encuentra los campos que
		//fueron presentados para ser seleccionadas en la pantalla

		int fila = 1;
		// ... a leer el archivo
		while(excelUtil.hasNext()) {
			String varStr=null;
			Map mapRow = excelUtil.next();
			Map mapRegistro = new HashMap();
			
			mapRegistro.put("codigoPais", codigoPais);

			//cargamos el codigo de cliente	
			if((String)mapRow.get(colCliente) != null ){
				int varIntDato = ((String)mapRow.get(colCliente)).indexOf('.');
				if (varIntDato >= 0 ){
					varStr = ((String)mapRow.get(colCliente)).substring(0, varIntDato);
				 }
				else {
					varStr = (String)mapRow.get(colCliente);
				}
				
				mapRegistro.put("codigoCliente", varStr);
			}

			//cargamos el codigo de territorio	
			if((String)mapRow.get(colTerritorio) != null ){
				int varIntDato = ((String)mapRow.get(colTerritorio)).indexOf('.');
				if (varIntDato >= 0 ){
					varStr = ((String)mapRow.get(colTerritorio)).substring(0, varIntDato);
				 }
				else {
					varStr = (String)mapRow.get(colTerritorio);
				}
				
				mapRegistro.put("codigoTerritorio", varStr);
			}

			
			if (StringUtils.isNotBlank(varStr)) {
				mapRegistro.put("numeroFila", String.valueOf(fila));	
				procesoZONActualizarUnidadesAdministrativasDAO.insertTerritorioConsultora(mapRegistro);
				
				fila = fila + 1;
			}	
		
		}
	
		excelUtil.cerrar();

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#deleteTerritorioConsultoraTmp()
	 */
	public void deleteTerritorioConsultoraTmp() {
		this.procesoZONActualizarUnidadesAdministrativasDAO.deleteTerritorioConsultoraTmp();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#execValidacionesModTerritorioConsultoras(java.util.Map)
	 */
	public void execValidacionesModTerritorioConsultoras(Map params) {
		this.procesoZONActualizarUnidadesAdministrativasDAO.execValidacionesModTerritorioConsultoras(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#execActualizarModTerritorioConsultoras(java.util.Map)
	 */
	public void execActualizarModTerritorioConsultoras(Map params) {
		this.procesoZONActualizarUnidadesAdministrativasDAO.execActualizarModTerritorioConsultoras(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#validarFormatoArchivoExcel(java.util.Map)
	 */
	public boolean validarFormatoArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Integer numeroCampos = (Integer)criteria.get("numeroCampos");
		
		boolean valor = true;
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();		
			 if(mapRow.size() != (numeroCampos.intValue() + 1)) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }			
		}
		excelUtil.cerrar();
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#getRegionesDemandaByPais(java.util.Map)
	 */
	public List getRegionesDemandaByPais(Map params) {
		return procesoZONActualizarUnidadesAdministrativasDAO.getRegionesDemandaByPais(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#getUnidadAdministrativaDemanda(java.util.Map)
	 */
	public List getUnidadAdministrativaDemanda(Map params) {
		return procesoZONActualizarUnidadesAdministrativasDAO.getUnidadAdministrativaDemanda(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#executeValidarUnidadAdministrativaDemanda(java.util.Map)
	 */
	public boolean executeValidarUnidadAdministrativaDemanda(Map params) throws Exception {
		String directorioTemporal = (String)params.get("directorioTemporal");
		String nombreArchivo = (String)params.get("nombreArchivo");
		String codigoUsuario = (String)params.get("codigoUsuario");
		
		String codigoPais = (String)params.get("codigoPais");
		String codigoMarca = (String)params.get("codigoMarca");
		String codigoCanal = (String)params.get("codigoCanal");
		String codigoPeriodo = (String)params.get("codigoPeriodo");

	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		//eliminamos los datos de la tabla temporal
		procesoZONActualizarUnidadesAdministrativasDAO.deleteUnidadAdministrativaDemanda();
		
		List list = new ArrayList();
		int fila = 1;
		while(excelUtil.hasNext()) {
			Map mapRow = getParseRowUnidadAdministrativaDemanda(excelUtil.next());
			
			mapRow.put("nombreArchivo", nombreArchivo);
			mapRow.put("codigoPais", codigoPais);
			mapRow.put("codigoMarca", codigoMarca);
			mapRow.put("codigoCanal", codigoCanal);
			mapRow.put("codigoPeriodo", codigoPeriodo);
			mapRow.put("codigoUsuario",codigoUsuario);
			
			mapRow.put("fila", String.valueOf(fila));
			fila = fila + 1;
			
			procesoZONActualizarUnidadesAdministrativasDAO.insertarUnidadAdministrativaDemanda(mapRow);
		}
		excelUtil.cerrar();
		
		//ejecutamos la validacion
		procesoZONActualizarUnidadesAdministrativasDAO.executeValidarUnidadAdministrativaDemanda(params);
		
		//recuperamos los posibles errores surgidos en el proceso de Validacion
		List listaErrores = procesoZONActualizarUnidadesAdministrativasDAO.getErroresUnidadAdministrativaDemanda(params);
		
		if(listaErrores.size() == 0) {
			return true;
		} else {
			params.put("listaErrores", listaErrores);
			return false;
		}
	}	
	
	/**
	 * se encarga de parsear el valor correcto de los campos codigos que vengan con (.)
	 * @param next
	 * @return
	 */
	private Map getParseRowUnidadAdministrativaDemanda(Map mapRow) {
		 String codigoRegion =(String)mapRow.get("1");
		 String codigoZona =(String)mapRow.get("2");
		 String codigoTerritorio =(String)mapRow.get("4");

		 mapRow.put("1",codigoRegion.substring(0,codigoRegion.indexOf(".")!=-1?codigoRegion.indexOf("."):codigoRegion.length()));
		 mapRow.put("2",codigoZona.substring(0,codigoZona.indexOf(".")!=-1?codigoZona.indexOf("."):codigoZona.length()));
		 mapRow.put("4",codigoTerritorio.substring(0,codigoTerritorio.indexOf(".")!=-1?codigoTerritorio.indexOf("."):codigoTerritorio.length()));
		 
		 return mapRow;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#executeProcesarUnidadAdministrativaDemanda(java.util.Map)
	 */
	public void executeProcesarUnidadAdministrativaDemanda(Map params) {
		this.procesoZONActualizarUnidadesAdministrativasDAO.executeProcesarUnidadAdministrativaDemanda(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#getUnidadAdministrativaConsultora(java.util.Map)
	 */
	public List getUnidadAdministrativaConsultora(Map params) {
		return this.procesoZONActualizarUnidadesAdministrativasDAO.getUnidadAdministrativaConsultora(params);
	}
	
   /* (non-Javadoc)
    * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#getUnidadAdministrativaConsultoraTotal(java.util.Map)
    */
   public Integer getUnidadAdministrativaConsultoraTotal(Map params) {
	   return this.procesoZONActualizarUnidadesAdministrativasDAO.getUnidadAdministrativaConsultoraTotal(params);
   }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#getUnidadAdministrativaConsultoraTotalError(java.util.Map)
	 */
	public Integer getUnidadAdministrativaConsultoraTotalError(Map params) {
		   return this.procesoZONActualizarUnidadesAdministrativasDAO.getUnidadAdministrativaConsultoraTotalError(params);
	 }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#getUnidadAdministrativaConsultoraTotalOK(java.util.Map)
	 */
	public Integer getUnidadAdministrativaConsultoraTotalOK(Map params) {
		   return this.procesoZONActualizarUnidadesAdministrativasDAO.getUnidadAdministrativaConsultoraTotalOK(params);
	 } 
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#executeProcesoZONProcesoCargaRecepcionMaestros(java.lang.String, java.util.Map)
	 */
	public void executeProcesoZONProcesoCargaRecepcionMaestros(String codigoPais, Map params) throws Exception {
				
		this.executeProcesoZONCargarConsultoras(codigoPais, params);
		this.executeProcesoZONCargarRegiones(codigoPais, params);
		this.executeProcesoZONCargarZonas(codigoPais, params);
		this.executeProcesoZONCargarCampanha(codigoPais, params);
		this.executeProcesoZONCargarControlFacturacion(codigoPais, params);
		
		return;
	}
	
	
	/**
	 * Ejecuta Carga de Regiones (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONCargarRegiones(String codigoPais, Map params)
			throws Exception {

		List list = genericoEDUFacadeService.getZONRegionesComercial(codigoPais, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.deleteTemporalRegiones();
		this.procesoZONActualizarUnidadesAdministrativasDAO.insertTemporalRegiones(list, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.updateMaestroRegiones(codigoPais, params);
	}

	/**
	 * Ejecuta Carga de Zonas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONCargarZonas(String codigoPais, Map params)
			throws Exception {

		List list = genericoEDUFacadeService.getZONZonasComercial(codigoPais, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.deleteTemporalZonas();
		this.procesoZONActualizarUnidadesAdministrativasDAO.insertTemporalZonas(list, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.updateMaestroZonas(codigoPais, params);
	}
	
	/**
	 * Ejecuta Carga de Campaas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONCargarCampanha(String codigoPais, Map params)
			throws Exception {

		List list = genericoEDUFacadeService.getZONCampanhaComercial(codigoPais, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.deleteTemporalCampanha();
		this.procesoZONActualizarUnidadesAdministrativasDAO.insertTemporalCampanha(list, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.updateMaestroCampanha(codigoPais, params);
	}
	
	/**
	 * Ejecuta Carga de Control de Facturacin (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONCargarControlFacturacion(String codigoPais, Map params)
			throws Exception {

		List list = genericoEDUFacadeService.getZONControlFacturacionComercial(codigoPais, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.deleteTemporalControlFacturacion();
		this.procesoZONActualizarUnidadesAdministrativasDAO.insertTemporalControlFacturacion(list, params);
		this.procesoZONActualizarUnidadesAdministrativasDAO.updateMaestroControlFacturacion(codigoPais, params);
	}
	
	/**
	 * Ejecuta Carga de Consultoras (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONCargarConsultoras(String codigoPais, Map params)
			throws Exception {
		int contador = 0;
		int cantidadRegistros= 1000;
		while(true) {
			List list = genericoEDUFacadeService.getZONConsultorasComercial(codigoPais, params, contador, cantidadRegistros);
			if (list == null || list.size() <= 0) break;
			this.procesoZONActualizarUnidadesAdministrativasDAO.deleteTemporalConsultoras();
			this.procesoZONActualizarUnidadesAdministrativasDAO.insertTemporalConsultoras(list, params);
			this.procesoZONActualizarUnidadesAdministrativasDAO.updateMaestroConsultoras(codigoPais, params);
			contador += cantidadRegistros;
			if (list.size() < cantidadRegistros) break;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesAdministrativasService#executeProcesoZONProcesoEnviarEntidadesDirectorio(java.lang.String, java.util.Map)
	 */
	public void executeProcesoZONProcesoEnviarEntidadesDirectorio(String codigoPais, Map params) throws Exception {
		this.executeProcesoZONEnviarRegiones(codigoPais, params);
		this.executeProcesoZONEnviarZonas(codigoPais, params);
		this.executeProcesoZONEnviarHistorialGerente(codigoPais, params);
	}
	

	/**
	 * Ejecuta Envio de Regiones (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONEnviarRegiones(String codigoPais, Map params)
			throws Exception {
		
		List list = this.procesoZONActualizarUnidadesAdministrativasDAO.getZONRegionesDirectorioVentas(codigoPais, params);
		this.genericoEDUFacadeService.insertZONRegionesComercial(codigoPais, params, list);
		
	}

	/**
	 * Ejecuta Envio de Zonas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONEnviarZonas(String codigoPais, Map params)
			throws Exception {
		List list = this.procesoZONActualizarUnidadesAdministrativasDAO.getZONZonasDirectorioVentas(codigoPais, params);
		this.genericoEDUFacadeService.insertZONZonasComercial(codigoPais, params, list);
		
	}
	
	/**
	 * Ejecuta Envio de Historial Gerentes (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoZONEnviarHistorialGerente(String codigoPais, Map params)
			throws Exception {
		int contador = 0;
		int cantidadRegistros= 1000;
		while(true) {
			List list = this.procesoZONActualizarUnidadesAdministrativasDAO.getZONHistorialGerentesDirectorioVentas(codigoPais, params, contador, cantidadRegistros);
			if (list == null || list.size() <= 0) break;
			this.genericoEDUFacadeService.insertZONHistorialGerentesComercial(codigoPais, params, list);
			
			contador += cantidadRegistros;
			if (list.size() < cantidadRegistros) break;
		}
	}

	@Override
	public void validarZonas(Map params) {
		String[] zonas = (String[])params.get("codigosZona");	
		String mensajeRetorno = "";
		
		StringBuffer zonasCerradas = new StringBuffer(); 
		StringBuffer zonasFacturando = new StringBuffer(); 
		
		for(int i=0; i<zonas.length; i++){			
			params.put("codigoZona", zonas[i]);
			String codCerrado = "";
			String codFactura = "";
			
			this.procesoZONActualizarUnidadesAdministrativasDAO.validarZonaCerrada(params);
			codCerrado = (String)params.get("codRetorno");
			if(StringUtils.equals(codCerrado, Constants.NUMERO_UNO)){
				zonasCerradas.append(",");
				zonasCerradas.append(zonas[i]);
			}	
			
			this.procesoZONActualizarUnidadesAdministrativasDAO.validarZonaFacturando(params);
			codFactura = (String)params.get("codRetorno");	
			if(StringUtils.equals(codFactura, Constants.NUMERO_UNO)){
				zonasFacturando.append(",");
				zonasFacturando.append(zonas[i]);
			}					
		}
		
		if(zonasCerradas.length()>0){
			mensajeRetorno = " CERRADAS: "+zonasCerradas.substring(1).toString();
		}
		if(zonasFacturando.length()>0){
			mensajeRetorno = mensajeRetorno + " FACTURANDO: "+zonasFacturando.substring(1).toString();
		}
		
		if(StringUtils.isNotBlank(mensajeRetorno)){
			params.put("codRetorno", "1");
			params.put("msgRetorno", mensajeRetorno);
		}	
		
	}	
	
}
