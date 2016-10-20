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
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaImpresionPaqueteDocumentarioDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaImpresionPaqueteDocumentarioService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

@Service("spusicc.procesoMAECargaImpresionPaqueteDocumentarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAECargaImpresionPaqueteDocumentarioServiceImpl extends BaseService implements
	ProcesoMAECargaImpresionPaqueteDocumentarioService {
	
	@Resource(name="spusicc.procesoMAECargaImpresionPaqueteDocumentarioDAO")
	private ProcesoMAECargaImpresionPaqueteDocumentarioDAO procesoMAECargaImpresionPaqueteDocumentarioDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#cargarArchivoExcel(java.util.Map)
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
		    
			procesoMAECargaImpresionPaqueteDocumentarioDAO.insertCargaImpresionPaqueteDocumentario(params);
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaImpresionPaqueteDocumentarioService#executeInsertarCargaImpresionPaqueteDocumentario(java.util.Map)
	 */
	public void executeInsertarCargaImpresionPaqueteDocumentario(Map params) {
		//recupera el numero de carga
		String numeroCarga = getNumeroCarga();
		params.put("numeroCarga", numeroCarga);
		
		procesoMAECargaImpresionPaqueteDocumentarioDAO.executeInsertarCargaImpresionPaqueteDocumentario(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaImpresionPaqueteDocumentarioService#executeValidarCargaImpresionPaqueteDocumentarios(java.util.Map)
	 */
	public List executeValidarCargaImpresionPaqueteDocumentario(Map params) {
		procesoMAECargaImpresionPaqueteDocumentarioDAO.executeValidarCargaImpresionPaqueteDocumentario(params);
		
		List resultados = procesoMAECargaImpresionPaqueteDocumentarioDAO.getCargaImpresionPaqueteDocumentarioList(params);
		
		return resultados;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaImpresionPaqueteDocumentarioService#executeActualizarCargaImpresionPaqueteDocumentario(java.util.Map)
	 */
	public void executeActualizarCargaImpresionPaqueteDocumentario(Map params) {
		procesoMAECargaImpresionPaqueteDocumentarioDAO.executeActualizarCargaImpresionPaqueteDocumentario(params);
	}
	
	/**
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoMAECargaImpresionPaqueteDocumentarioDAO.getNumeroCarga();
	}

}
