package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCMigracionPuntosConsultoraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCMigracionPuntosConsultoraService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

@Service("spusicc.procesoINCMigracionPuntosConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCMigracionPuntosConsultoraServiceImpl extends BaseService implements
	ProcesoINCMigracionPuntosConsultoraService {
	
	@Resource(name="spusicc.procesoINCMigracionPuntosConsultoraDAO")
	private ProcesoINCMigracionPuntosConsultoraDAO procesoINCMigracionPuntosConsultoraDAO;
	

	
	/**
	 * Retorna la lista de Concursos Migracion Puntos
	 * @return
	 */
	public List getListConcursosMigracionPuntos() {
		return procesoINCMigracionPuntosConsultoraDAO.getListConcursosMigracionPuntos();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCMigracionPuntosConsultoraService#cargarArchivoExcel(java.util.Map)
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
		    
			procesoINCMigracionPuntosConsultoraDAO.insertMigracionPuntosConsultora(params);
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoINCMigracionPuntosConsultoraService#executeValidarMigracionPuntosConsultoras(java.util.Map)
	 */
	public List executeValidarMigracionPuntosConsultora(Map params) {
		procesoINCMigracionPuntosConsultoraDAO.executeValidarMigracionPuntosConsultora(params);
		
		List resultados = procesoINCMigracionPuntosConsultoraDAO.getMigracionPuntosConsultoraList(params);
		
		return resultados;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoINCMigracionPuntosConsultoraService#executeActualizarMigracionPuntosConsultora(java.util.Map)
	 */
	public void executeActualizarMigracionPuntosConsultora(Map params) {
		String indicadorMigracion = (String)params.get("indicadorMigracion");
		
		if("1".equals(indicadorMigracion)) { //POR REGIONES Y ZONAS
			String numeroCarga = getNumeroCarga();
			
			List listAmbitos = (List)params.get("listAmbitos");
			if(listAmbitos != null) {
				for(int i=0; i<listAmbitos.size(); i++) {
					Map mapAmbito = (Map)listAmbitos.get(i);
					mapAmbito.put("numeroCarga", numeroCarga);
					procesoINCMigracionPuntosConsultoraDAO.insertMigracionPuntosAmbito(mapAmbito);
				}
			}
			
			params.put("numeroCarga", numeroCarga);
		}
		
		if("2".equals(indicadorMigracion)) { //POR CODIGO DE CLIENTE
			String numeroCarga = getNumeroCarga();
			
			params.put("numeroCarga", numeroCarga);
			params.put("numeroFila", "1");
			
			procesoINCMigracionPuntosConsultoraDAO.insertMigracionPuntosConsultora(params);
			procesoINCMigracionPuntosConsultoraDAO.executeValidarMigracionPuntosConsultora(params);
		}
		
		procesoINCMigracionPuntosConsultoraDAO.executeActualizarMigracionPuntosConsultora(params);
	}
	
	/**
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoINCMigracionPuntosConsultoraDAO.getNumeroCarga();
	}

}