package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDURegularizacionAsistenciaDAO;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;


/**
 * @author peextsbuchelli
 *
 */
@Repository("edu.procesoEDURegularizacionAsistenciaDAO")
public class ProcesoEDURegularizacionAsistenciaDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDURegularizacionAsistenciaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegularizacionAsistenciaDAO#confirmarRegularizacion(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public String confirmarRegularizacion(HistoricoAptas historicoAptas, Usuario usuario) {
	
		Map params= new  HashMap();
		params.put("codigoPais",historicoAptas.getCodigoPais());
		params.put("codigoEmpresa",historicoAptas.getCodigoEmpresa());
		params.put("codigoCurso",historicoAptas.getCodigoCurso());
		params.put("codigoPlanilla",historicoAptas.getCodigoPlanillaProgramacion());
		params.put("codigoCliente",historicoAptas.getCodigoCliente());
		params.put("usuarioLogin",usuario.getLogin());
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.confirmarRegularizacion", params);
		String mensaje=(String)params.get("mensajeError");
		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegularizacionAsistenciaDAO#eliminarAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public String eliminarAsistencia(HistoricoAptas historicoAptas, Usuario usuario) {
		Map params= new  HashMap();
		params.put("codigoPais",historicoAptas.getCodigoPais());
		params.put("codigoEmpresa",historicoAptas.getCodigoEmpresa());
		params.put("codigoCurso",historicoAptas.getCodigoCurso());
		params.put("codigoPlanilla",historicoAptas.getCodigoPlanillaProgramacion());
		params.put("codigoCliente",historicoAptas.getCodigoCliente());
		params.put("usuarioLogin",usuario.getLogin());
	
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.eliminarAsistencia", params);
		String mensaje=(String)params.get("mensajeError");
		return mensaje;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegularizacionAsistenciaDAO#confirmarExoneracion(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public String confirmarExoneracion(HistoricoAptas historicoAptas, Usuario usuario) {
		Map params= new  HashMap();
		params.put("codigoPais",historicoAptas.getCodigoPais());
		params.put("codigoEmpresa",historicoAptas.getCodigoEmpresa());
		params.put("codigoCurso",historicoAptas.getCodigoCurso());		
		params.put("codigoCliente",historicoAptas.getCodigoCliente());
		params.put("usuarioLogin",usuario.getLogin());
		getSqlMapClientTemplate().update("edu.ProcesosEDUSQL.confirmarExoneracion", params);
		String mensaje=(String)params.get("mensajeError");
		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegularizacionAsistenciaDAO#inserTemporalExoneradas(java.util.Map)
	 */
	public void inserTemporalExoneradas(Map criteria) {
		getSqlMapClientTemplate().insert("edu.ProcesosEDUSQL.insertTemporalExoneradas", criteria);
		
	}

	public void deleteTemporalExoneradas(Map criteria) {
		getSqlMapClientTemplate().insert("edu.ProcesosEDUSQL.deleteTemporalExoneradas", criteria);		
	}

	public String confirmarExoneracionMasiva(HistoricoAptas historicoAptas,
			Usuario usuario) {
		Map params= new  HashMap();
		params.put("codigoPais",historicoAptas.getCodigoPais());
		params.put("codigoEmpresa",historicoAptas.getCodigoEmpresa());
		params.put("codigoCurso",historicoAptas.getCodigoCurso());		
		params.put("usuarioLogin",usuario.getLogin());
		getSqlMapClientTemplate().update("edu.ProcesosEDUSQL.confirmarExoneracionMasiva", params);
		String mensaje=(String)params.get("mensajeError");
		return mensaje;
	}
}
