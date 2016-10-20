package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaNivelRiesgoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaNivelRiesgoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

@Service("spusicc.procesoMAECargaNivelRiesgoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAECargaNivelRiesgoServiceImpl extends BaseService implements
	ProcesoMAECargaNivelRiesgoService {
	
	@Resource(name="spusicc.procesoMAECargaNivelRiesgoDAO")
	private ProcesoMAECargaNivelRiesgoDAO procesoMAECargaNivelRiesgoDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaNivelRiesgoService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));
	
		//recupera el numero de carga
		String numeroCarga = getNumeroCarga();
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int fila=0;
		Map params = new HashMap();
		params.put("numeroCarga", numeroCarga);
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			fila +=1;
			
			String codigoCliente = (String)mapRow.get("0");
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
		    
			params.put("codigoCliente", codigoCliente);
			params.put("codigoUsuario", usuario.getLogin());
			params.put("numeroFila", fila);
		    
			procesoMAECargaNivelRiesgoDAO.insertCargaNivelRiesgo(params);
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaNivelRiesgoService#executeValidarCargaNivelRiesgos(java.util.Map)
	 */
	public List executeValidarCargaNivelRiesgo(Map params) {
		procesoMAECargaNivelRiesgoDAO.executeValidarCargaNivelRiesgo(params);
		
		List resultados = procesoMAECargaNivelRiesgoDAO.getCargaNivelRiesgoList(params);
		
		return resultados;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaNivelRiesgoService#executeActualizarCargaNivelRiesgo(java.util.Map)
	 */
	public void executeActualizarCargaNivelRiesgo(Map params) {
		procesoMAECargaNivelRiesgoDAO.executeActualizarCargaNivelRiesgo(params);
	}
	
	/**
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoMAECargaNivelRiesgoDAO.getNumeroCarga();
	}

}
