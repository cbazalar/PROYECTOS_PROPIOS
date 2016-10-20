
package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaInformacionMasivoDAO;

@Repository("spusicc.procesoMAECargaInformacionMasivoDAO")
public class ProcesoMAECargaInformacionMasivoDAOIbatis  extends BaseDAOiBatis implements
ProcesoMAECargaInformacionMasivoDAO {
 
	public String validarCliente(Map params){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.validarCliente",params);		
	}
	public String validarDireccion(Map params){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.validarDireccion",params);		
	}
	public int executeActualizarDirecciones(Map params){
		return (int)getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarDirecciones",params);		
	}
	public int executeActualizarTelefonos(Map params){
		return (int)getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarTelefonos",params);		
	}
	public int insertClienteComunicacion(Map params){
		return (int)getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.insertClienteComunicacion",params);		
	}
	public void executeInsertActualizarDirecciones(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeInsertUpdateDirecEntrega",params);	
		
	}
}
