package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoMAECargaInformacionMasivoDAO  extends DAO {
	/**
	 * Ejecura el proceso de validar cliente
	 * 
	 * @param params
	 */
	public String validarCliente(Map params);
	
	public String validarDireccion(Map params);
	
	public void executeInsertActualizarDirecciones(Map params);
	
	public int executeActualizarDirecciones(Map params);
	
	public int executeActualizarTelefonos(Map params);
	
	public int insertClienteComunicacion(Map params);
}
