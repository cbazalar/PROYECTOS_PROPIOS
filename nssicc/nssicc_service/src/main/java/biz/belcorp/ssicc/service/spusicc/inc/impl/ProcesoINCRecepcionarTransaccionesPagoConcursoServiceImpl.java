package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCRecepcionarTransaccionesPagoConcursoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

@Service("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCRecepcionarTransaccionesPagoConcursoServiceImpl extends BaseService implements
	ProcesoINCRecepcionarTransaccionesPagoConcursoService {
	
	@Resource(name="spusicc.procesoINCRecepcionarTransaccionesPagoConcursoDAO")
	private ProcesoINCRecepcionarTransaccionesPagoConcursoDAO procesoINCRecepcionarTransaccionesPagoConcursoDAO;
	
	/**
	 * @param procesoINCpuntajeDAO the procesoINCpuntajeDAO to set
	 */
	public void setProcesoINCRecepcionarTransaccionesPagoConcursoDAO(ProcesoINCRecepcionarTransaccionesPagoConcursoDAO procesoINCRecepcionarTransaccionesPagoConcursoDAO) {
		this.procesoINCRecepcionarTransaccionesPagoConcursoDAO = procesoINCRecepcionarTransaccionesPagoConcursoDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCRecepcionarTransaccionesPagoConcursoService#getListPagoConcurso()
	 */
	public List getListPagoConcurso() {
		return procesoINCRecepcionarTransaccionesPagoConcursoDAO.getListPagoConcurso();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCRecepcionarTransaccionesPagoConcursoService#getListMotivoPago()
	 */
	public List getListMotivoPago() {
		return procesoINCRecepcionarTransaccionesPagoConcursoDAO.getListMotivoPago();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCRecepcionarTransaccionesPagoConcursoService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
	
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
	        
			String numeroConcurso = (String)mapRow.get("0");
			String numeroDocumento = (String)mapRow.get("1");
			String codigoCliente = (String)mapRow.get("2");
			String montoAbono = (String)mapRow.get("3");
			String fechaAbono = (String)mapRow.get("4");
			String estadoAbono = (String)mapRow.get("5");
			
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
		    
		    params.put("numeroConcurso", numeroConcurso);
		    params.put("numeroDocumento", numeroDocumento);
		    params.put("codigoCliente", codigoCliente);
		    params.put("montoAbono", new BigDecimal(montoAbono));
		    params.put("fechaAbono", fechaAbono);
			params.put("estadoAbono", estadoAbono);
			params.put("codigoUsuario", usuario.getLogin());
			params.put("numeroFila", fila);
		    
			procesoINCRecepcionarTransaccionesPagoConcursoDAO.insertTransaccionPagoConcurso(params);
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCRecepcionarTransaccionesPagoConcursoService#executeActualizarTransaccionesPagoConcurso(java.util.Map)
	 */
	public void executeActualizarTransaccionesPagoConcurso(Map params) {
		procesoINCRecepcionarTransaccionesPagoConcursoDAO.executeActualizarTransaccionesPagoConcurso(params);
	}
	
	/**
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoINCRecepcionarTransaccionesPagoConcursoDAO.getNumeroCarga();
	}

}