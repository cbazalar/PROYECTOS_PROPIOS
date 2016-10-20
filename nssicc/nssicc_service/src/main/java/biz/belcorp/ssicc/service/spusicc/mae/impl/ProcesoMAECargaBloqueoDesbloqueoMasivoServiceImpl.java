package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaBloqueoDesbloqueoMasivoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * Service que executa las metodos de Bloqueo/Desbloqueo Masivo de Clientes
 *  
 * <p>
 * <a href="ProcesoMAECargaBloqueoDesbloqueoMasivoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */ 
@Service("spusicc.procesoMAECargaBloqueoDesbloqueoMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAECargaBloqueoDesbloqueoMasivoServiceImpl extends BaseService implements ProcesoMAECargaBloqueoDesbloqueoMasivoService {

	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaBloqueoDesbloqueoMasivoService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));

		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		List listRegistros = new ArrayList();
		Map mapRegistros = new HashMap();
		int fila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigoCliente = (String)mapRow.get("0");
		    String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data

			if(mapRegistros.get(codigoCliente)== null) {
				Map mapRegistro = new HashMap();
				mapRegistro.put("numeroFila", filaExcel);
				mapRegistro.put("codigoCliente", codigoCliente);

				fila +=1;
				mapRegistros.put(codigoCliente, codigoCliente);
				listRegistros.add(mapRegistro);				
			} 
		    
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("listRegistros", listRegistros);
		resultado.put("totalRegistros", String.valueOf(fila));
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaBloqueoDesbloqueoMasivoService#executeValidarCargaBloqueoDesbloqueoMasivos(java.util.Map)
	 */
	public List executeValidarCargaBloqueoDesbloqueoMasivos(Map params) {
		String accionBloqueo = String.valueOf(params.get("accionBloqueo"));
		String tipoBloqueo = String.valueOf(params.get("tipoBloqueo"));
		String codigoPais = String.valueOf(params.get("codigoPais"));
		String codigoPeriodo = String.valueOf(params.get("codigoPeriodo"));
		Usuario usuario = (Usuario)params.get("usuario");
		List listRegistros = (List)params.get("listRegistros");
		                                       
		Map criteriaAux = new HashMap();
		criteriaAux.put("accionBloqueo", accionBloqueo);
		criteriaAux.put("tipoBloqueo", tipoBloqueo);
		
		for(int i=0; i<listRegistros.size(); i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			String codigoCliente = (String)mapRegistro.get("codigoCliente");
			criteriaAux.put("codigoCliente", codigoCliente);
			
			String resultValidacion = mantenimientoMAEClienteDAO.getValidarBloqueoDesbloqueoCliente(criteriaAux);
			String mensajeError = "";
			
			if(!resultValidacion.equals("00"))  {//
				if(resultValidacion.equals("01")) {
					mensajeError = messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.noExisteCliente",
												null,getLocale(usuario));
	 		    }
				if(resultValidacion.equals("05")) {
					mensajeError =  messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.clienteYaBloqueado",
						  						null,getLocale(usuario));
			    }
				if(resultValidacion.equals("06")) {
					mensajeError = messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.existeBloqueoMayorGravedad",
			  									null,getLocale(usuario));
		    	}
				if(resultValidacion.equals("07")) {
					mensajeError = messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.existeOtroBloqueo",
			  									null,getLocale(usuario));
		    	}
				if(resultValidacion.equals("08")) {
					mensajeError = messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.noExisteBloqueo",
			  									null,getLocale(usuario));
		    	}
				if(resultValidacion.equals("09")) {
					mensajeError = messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.clienteInactivo",
												null,getLocale(usuario));
		    	}
				
				if(resultValidacion.equals("10")) {
					mensajeError = messageSource.getMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.error.procesosCliente",
												null,getLocale(usuario));
		    	}
				
				if(resultValidacion.equals("05") || resultValidacion.equals("06") || resultValidacion.equals("07")) {
					String descripcionTipoBloqueo =  mantenimientoMAEClienteDAO.getDevuelveBloqueoDesbloqueoCliente(criteriaAux);
					if (StringUtils.isNotBlank(descripcionTipoBloqueo))
						mensajeError = mensajeError + " - " + descripcionTipoBloqueo;
				}
				
				mapRegistro.put("codigoMotivo", resultValidacion);
				mapRegistro.put("motivoRechazo", mensajeError);

			} else {
				Cliente cliente = mantenimientoMAEClienteDAO.getDatosBasicosCliente(codigoPais, codigoCliente);
				mapRegistro.put("oidCliente", cliente.getOid().toString());
			}

		}
		
		return listRegistros;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaBloqueoDesbloqueoMasivoService#executeActualizarBloqueoDesbloqueoMasivos(java.util.Map)
	 */
	public void executeActualizarBloqueoDesbloqueoMasivos(Map params) {
		String accionBloqueo = String.valueOf(params.get("accionBloqueo"));
		String tipoBloqueo = String.valueOf(params.get("tipoBloqueo"));
		String motivoBloqueo = String.valueOf(params.get("motivoBloqueo"));
		String observacionesBloqueo = String.valueOf(params.get("observacionesBloqueo"));
		String codigoPais = String.valueOf(params.get("codigoPais"));
		String codigoArea = String.valueOf(params.get("codigoArea"));
		String indicadorDesbloqueo = String.valueOf(params.get("indicadorDesbloqueo"));
		
		Usuario usuario = (Usuario)params.get("usuario");
		List listRegistros = (List)params.get("listRegistros");

		Map criteriaAux = new HashMap();
		criteriaAux.put("tipoBloqueo", tipoBloqueo);
		criteriaAux.put("motivoBloqueo", motivoBloqueo);
		criteriaAux.put("observacionesBloqueo", observacionesBloqueo);
		criteriaAux.put("usuarioBloqueo", usuario.getLogin());
		criteriaAux.put("codigoArea", codigoArea);
		criteriaAux.put("codigoPais", codigoPais);
		criteriaAux.put("indicadorDesbloqueo", indicadorDesbloqueo);
		
		for(int i=0; i<listRegistros.size();i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			String oidCliente = (String)mapRegistro.get("oidCliente");
			
			if(oidCliente != null) {
				criteriaAux.put("oidCliente", oidCliente);
				
				if(accionBloqueo.equals("B")) //Bloquear
					mantenimientoMAEClienteDAO.insertBloqueoCliente(criteriaAux);
				else
					mantenimientoMAEClienteDAO.updateDesbloqueoCliente(criteriaAux);					
			}
		}	
	}

	
	
	
}
