package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaClasificacionClienteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaClasificacionClienteService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAECargaClasificacionClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAECargaClasificacionClienteServiceImpl extends BaseService implements
	ProcesoMAECargaClasificacionClienteService {
		
	@Resource(name="spusicc.procesoMAECargaClasificacionClienteDAO")
	private ProcesoMAECargaClasificacionClienteDAO procesoMAECargaClasificacionClienteDAO;


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
		
		Map params = new HashMap();
		params.put("oidTipoCliente", criteria.get("oidTipoCliente"));
		params.put("oidSubTipoCliente", criteria.get("oidSubTipoCliente"));
		params.put("oidTipoClasificacion", criteria.get("oidTipoClasificacion"));
		params.put("oidClasificacion", criteria.get("oidClasificacion"));
		params.put("numeroCarga", numeroCarga);

		Map mapRegistros = new HashMap();
		int fila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigoCliente = (String)mapRow.get("0");
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
		    
		    if(StringUtils.isNotEmpty(codigoCliente))
				if(mapRegistros.get(codigoCliente)== null) {
					Map mapRegistro = new HashMap();
			params.put("codigoCliente", codigoCliente);
			params.put("codigoUsuario", usuario.getLogin());
			params.put("numeroFila", fila);
		    
					fila +=1;
					mapRegistros.put(codigoCliente, codigoCliente);
			procesoMAECargaClasificacionClienteDAO.insertCargaClasificacionCliente(params);
				}
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaClasificacionClienteService#executeValidarCargaClasificacionClientes(java.util.Map)
	 */
	public List executeValidarCargaClasificacionClientes(Map params) {
		procesoMAECargaClasificacionClienteDAO.executeValidarCargaClasificacionClientes(params);
		
		List resultados = procesoMAECargaClasificacionClienteDAO.getCargaClasificacionClientesList(params);
		
		return resultados;
	}
	
	public void executeActualizarCargaClasificacionClientes(Map params) {
		params.put("numeroLote", getNumeroLote());
		procesoMAECargaClasificacionClienteDAO.executeActualizarCargaClasificacionClientes(params);
	}
	
	/**
	 * Retorna el numero de carga
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoMAECargaClasificacionClienteDAO.getNumeroCarga();
	}
	
	/**
	 * Retorna el numero de lote
	 * @return
	 */
	private String getNumeroLote() {
		return procesoMAECargaClasificacionClienteDAO.getNumeroLote();
	}

}

