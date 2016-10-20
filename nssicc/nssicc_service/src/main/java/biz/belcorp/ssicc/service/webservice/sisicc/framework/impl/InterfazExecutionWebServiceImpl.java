package biz.belcorp.ssicc.service.webservice.sisicc.framework.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.webservice.sisicc.framework.InterfazExecutionWebService;

/**
 *  @author Carlos Bazalar
 *
 */
@Service("InterfazExecutionWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class InterfazExecutionWebServiceImpl extends BaseService implements InterfazExecutionWebService {

	@Resource(name="sisicc.interfazExecutionService")
	protected InterfazExecutionService interfazExecutionService;
	
	@Resource(name="sisicc.interfazService")
	protected InterfazService interfazService;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service._ejemplos.webservice.sisicc.framework.InterfazExecutionWebService#executeInterfaz(java.util.Map)
	 */
	public InterfazExecutionResult executeInterfaz(Map<String, Object> params) throws Exception {
		
		/* Ejecutando las validaciones previas antes de la Ejecución de la Interfaz */
		params = this.executeInterfazValidacionesPrevias(params);
		params = this.interfazExecutionService.executeInterfazValidacionesPrevias(params);
		
		/* Insertando Registro en Tabla de PROCESO BATCH */
		params = this.interfazExecutionService.insertProceBatch(params);
		
		log.info("LANZADO DESDE WS: params=" + params);
		
		/* Ejecución de la Interfaz */
		InterfazExecutionResult result = this.interfazExecutionService.executeInterfaz(params);
		return result;
	}

	/**
	 * Realiza validaciones previas antes de la Ejecución de la Interfaz
	 * @param params
	 * @return
	 */
	private Map<String, Object> executeInterfazValidacionesPrevias(Map<String, Object> params) throws Exception {
		Usuario usuario = (Usuario) params.get("usuario");
		if (usuario == null) {
			String codigoUsuario = (String) params.get("codigoUsuario");
			usuario =  this.obtenerUsuarioByDefault(codigoUsuario);
			params.put("usuario", usuario);
		}
		String codigoPais = (String) params.get("codigoPais");
		if (StringUtils.isBlank(codigoPais)) {
		    String error = this.getKeyMessage("interfaz.error.codigoPais", usuario);
			throw new Exception(error);
		}
		String codigoSistema = (String) params.get("codigoSistema");
		if (StringUtils.isBlank(codigoSistema)) {
		    String error = this.getKeyMessage("interfaz.error.codigoSistema", usuario);
			throw new Exception(error);
		}
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		if (StringUtils.isBlank(codigoInterfaz)) {
			String error = this.getKeyMessage("interfaz.error.codigoInterfaz", usuario);
			throw new Exception(error);
		}
		
		/* Obteniendo interfaz */
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,	codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
		String descripcion = interfazEjecucion.getDescripcion();
		params.put("descripcion", "(WS) - " + descripcion);
		return params;
	}
	
	/**
	 * Obtiene el nomnbre del Archivo generado por la Interfaz
	 * @param interfazResult
	 * @return
	 */
	protected final String getNombreArchivoEntradaSalida(InterfazResult interfazResult) {
		String nombreArchivo;

		Interfaz interfaz = interfazResult.getInterfaz();
		String numeroLote = interfazResult.getNumeroLote();

		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
			nombreArchivo = interfaz.getNombreArchivo(interfaz.getNombreArchivoEntradaSalida()
					+ numeroLote);

		}
		// Tipo de nombre variable
		else {
			String nombreArchivoSalida = interfaz.getNombreArchivoEntradaSalida();
			if (StringUtils.isEmpty(nombreArchivoSalida)
					|| StringUtils.isEmpty(nombreArchivoSalida)) {
				nombreArchivoSalida = interfaz.getCodigo() + "_";
			}
			nombreArchivo = interfaz.getNombreArchivo(nombreArchivoSalida
					+ numeroLote);
		}
		return nombreArchivo;
	}
	 
}
