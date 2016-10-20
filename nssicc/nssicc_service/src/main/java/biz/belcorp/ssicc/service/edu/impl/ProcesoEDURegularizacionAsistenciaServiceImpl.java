package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ProcesoEDURegularizacionAsistenciaDAO;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.ProcesoEDURegularizacionAsistenciaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author peextsbuchelli
 *
 */
@Service("edu.procesoEDURegularizacionAsistenciaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDURegularizacionAsistenciaServiceImpl extends 
	BaseService implements ProcesoEDURegularizacionAsistenciaService{
	
	@Resource(name="edu.procesoEDURegularizacionAsistenciaDAO")
	ProcesoEDURegularizacionAsistenciaDAO dao;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegularizacionAsistenciaService#confirmarRegularizacion(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public String confirmarRegularizacion(HistoricoAptas historicoAptas, Usuario usuario) {
		return dao.confirmarRegularizacion(historicoAptas,usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegularizacionAsistenciaService#eliminarAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public String eliminarAsistencia(HistoricoAptas historicoAptas, Usuario usuario) {
		return dao.eliminarAsistencia(historicoAptas,usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegularizacionAsistenciaService#confirmarExoneracion(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public String confirmarExoneracion(HistoricoAptas historicoAptas, Usuario usuario){
		return dao.confirmarExoneracion(historicoAptas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegularizacionAsistenciaService#inserTemporalExoneradas(java.util.Map)
	 */
	public void inserTemporalExoneradas(Map criteria) {
		try{
			dao.inserTemporalExoneradas(criteria);
		}catch(Exception e){
			log.debug("error en duplicidad "+ criteria.get("codigoCliente") + " message" + e.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegularizacionAsistenciaService#cargarArchivoExcel(java.util.Map)
	 */
	public void cargarArchivoExcel(Map criteria) throws Exception {
		
		String  directorioTemporal = (String)criteria.get("directorioTemporal");
		String  nombreArchivo = (String)criteria.get("nombreArchivo");
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		//elimnamos la tabla temporal
		deleteTemporalExoneradas(criteria);
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();									
			//Recuperamos el codigo de consultora y el puntaje 
			 String codigoCliente = (String)mapRow.get("0");
			 criteria.put("codigoCliente", codigoCliente);
			 //validaciones
			//insert temporal
			 inserTemporalExoneradas(criteria);
			
			//						
		}
		excelUtil.cerrar();
	}

	/**elimna la tabla temporal
	 * @param criteria
	 */
	public void deleteTemporalExoneradas(Map criteria) {
		dao.deleteTemporalExoneradas(criteria);
		
	}

	public String confirmarExoneracionMasiva(HistoricoAptas historicoAptas,
			Usuario usuario) {
		return dao.confirmarExoneracionMasiva(historicoAptas,usuario);
	}	
}