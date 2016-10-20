/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.EstructuraGeografica;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.DBFUtil;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONActualizarUnidadesGeograficasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoZONActualizarUnidadesGeograficasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoZONActualizarUnidadesGeograficasServiceImpl extends BaseService
		implements ProcesoZONActualizarUnidadesGeograficasService {
	
	@Resource(name="spusicc.procesoZONActualizarUnidadesGeograficasDAO")
	ProcesoZONActualizarUnidadesGeograficasDAO procesoZONActualizarUnidadesGeograficasDAO;

	public String obtenerPathUpload(String codigoPais) {
		return procesoZONActualizarUnidadesGeograficasDAO.obtenerPathUpload(codigoPais);
	}
	
	public void deleteTablasUnidadesGeograficas() {
		procesoZONActualizarUnidadesGeograficasDAO.deleteTablasUnidadesGeograficas();
	}
	
	public List getEstructurasGeopoliticas(String pais) {
		return procesoZONActualizarUnidadesGeograficasDAO.getEstructurasGeopoliticas(pais);
	}
	
	public void executeCargarUnidadesGeograficas(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String extensionArchivo = (String)criteria.get("extensionArchivo");
		
		List listEstructuraGeopoliticas = (List)criteria.get("listEstructuraGeopoliticas");
		
		//Eliminamos la data de las tablas de las unidades Geograficas, ya que seran
		//cargadas con los datos del archivo excel
		deleteTablasUnidadesGeograficas();

		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			procesarArchivoExcel(directorioTemporal, nombreArchivo, listEstructuraGeopoliticas);
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_DBF))
			procesarArchivoDBF(directorioTemporal, nombreArchivo, listEstructuraGeopoliticas);
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoZONActualizarUnidadesGeograficasDAO.executeValidarEstructurasGeograficas(criteria);
	}

	private void procesarArchivoExcel(String directorioTemporal, String nombreArchivo, 
				List listEstructuraGeopoliticas) throws Exception {

		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		//nos pasamos a la segunda fila, ya que el primero se encuentra los campos que
		//fueron presentados para ser seleccionadas en la pantalla
		excelUtil.next();
		int fila = 0;
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			EstructuraGeografica estructuraGeografica = new EstructuraGeografica();
			fila = fila + 1;
			
			Iterator itEstru = listEstructuraGeopoliticas.iterator();
			while(itEstru.hasNext()) {
				UnidadGeografica unidadGeografica = (UnidadGeografica)itEstru.next();
				
				//Nivel 1 Geografico
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("1")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel1((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel1((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel1(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel1(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}
				
				//Nivel 2 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("2")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel2((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel2((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel2(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel2(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}
				
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("3")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel3((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel3((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel3(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel3(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					

				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("4")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel4((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel4((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel4(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel4(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					

				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("5")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel5((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel5((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel5(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel5(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					

				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("6")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel6((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel6((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel6(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel6(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					

				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("7")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel7((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel7((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel7(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel7(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					

				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("8")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel8((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel8((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel8(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel8(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					

				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("9")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel9((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel9((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel9(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel9(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}
				
				estructuraGeografica.setIndicadorGeoreferencia(StringUtils.remove((String)mapRow.get(unidadGeografica.getIndicadorGeoreferenciaColArchivo()), ".0"));
			}
			
			//insertamos en BD la estructura Geografica recuperada del Excel
			estructuraGeografica.setFila(fila);
			procesoZONActualizarUnidadesGeograficasDAO.insertEstructuraGeografica(estructuraGeografica);
		}
		
		excelUtil.cerrar();
	}	

	private void procesarArchivoDBF(String directorioTemporal, String nombreArchivo, 
			List listEstructuraGeopoliticas) throws Exception {

		//Abrimos el archivo DBF para su procesamiento
		DBFUtil dbfUtil = new DBFUtil(directorioTemporal, nombreArchivo);
		int fila = 0;
		
		while(dbfUtil.hasNext()) {
			Map mapRow = dbfUtil.next();
			EstructuraGeografica estructuraGeografica = new EstructuraGeografica();
			fila = fila + 1;
			
			Iterator itEstru = listEstructuraGeopoliticas.iterator();
			while(itEstru.hasNext()) {
				UnidadGeografica unidadGeografica = (UnidadGeografica)itEstru.next();
				
				//Nivel 1 Geografico
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("1")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel1((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel1((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel1(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel1(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}
				
				//Nivel 2 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("2")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel2((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel2((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel2(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel2(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}
				
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("3")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel3((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel3((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel3(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel3(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					
		
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("4")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel4((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel4((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel4(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel4(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					
		
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("5")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel5((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel5((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel5(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel5(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					
		
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("6")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel6((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel6((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel6(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel6(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					
		
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("7")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel7((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel7((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel7(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel7(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					
		
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("8")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel8((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel8((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel8(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel8(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}					
		
				//Nivel 3 Geografico					
				if(unidadGeografica.getCodigoOrdenEstruGeopo().equalsIgnoreCase("9")) {
					//codigo de Estructura Geopolitica
					estructuraGeografica.setCodigoNivel9((String)mapRow.get(unidadGeografica.getCodigoColArchivo()));
					
					//descripcion de Estructura Geopolitica
					estructuraGeografica.setDescripcionNivel9((String)mapRow.get(unidadGeografica.getDescripcionColArchivo()));
					
					//codigo de SubEstructura Geopolitica
					if (StringUtils.isNotBlank(unidadGeografica.getDescripcionSubEstruGeopo())) 
						estructuraGeografica.setCodigoSubEstructuraNivel9(unidadGeografica.getCodigoSubEstruGeopo());
					else	
						estructuraGeografica.setCodigoSubEstructuraNivel9(StringUtils.remove((String)mapRow.get(unidadGeografica.getCodigoSubEstruGeopoColArchivo()), ".0"));
					
				}
				
				//Indicador de georeferencia
				estructuraGeografica.setIndicadorGeoreferencia(StringUtils.remove((String)mapRow.get(unidadGeografica.getIndicadorGeoreferenciaColArchivo()), ".0"));				
			}
			
			//insertamos en BD la estructura Geografica recuperada del Excel
			estructuraGeografica.setFila(fila);
			procesoZONActualizarUnidadesGeograficasDAO.insertEstructuraGeografica(estructuraGeografica);
		}
		
		dbfUtil.cerrar();
	}
	
	public void executeValidarDatosGeograficos(Map criteria) {
		procesoZONActualizarUnidadesGeograficasDAO.executeValidarDatosGeograficos(criteria);
	}

	public void setProcesoZONActualizarUnidadesGeograficasDAO(
			ProcesoZONActualizarUnidadesGeograficasDAO procesoZONActualizarUnidadesGeograficasDAO) {
		this.procesoZONActualizarUnidadesGeograficasDAO = procesoZONActualizarUnidadesGeograficasDAO;
	}
	
	public void executeActualizarUnidadesGeograficas(Map criteria) {
		procesoZONActualizarUnidadesGeograficasDAO.executeActualizarUnidadesGeograficas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesGeograficasService#getSubgerencias()
	 */
	public List getSubgerencias() {
		return procesoZONActualizarUnidadesGeograficasDAO.getSubgerencias();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesGeograficasService#getTerritoriosByCriteria(java.util.Map)
	 */
	public List getTerritoriosByCriteria(Map criteria) {
		return procesoZONActualizarUnidadesGeograficasDAO.getTerritoriosByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONActualizarUnidadesGeograficasService#getUbigeosByCriteria(java.util.Map)
	 */
	public List getUbigeosByCriteria(Map criteria) {
		return procesoZONActualizarUnidadesGeograficasDAO.getUbigeosByCriteria(criteria);
	}	
}
