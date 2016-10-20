package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.SecuenciaValidacion;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOSecuenciaValidacionDAO;

/**
 * Service con metodos para las consultas invocados por la pantalla de Secuencua Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOParametroValidacionDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
@Repository("sto.mantenimientoSTOSecuenciaValidacionDAO")
public class MantenimientoSTOSecuenciaValidacionDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOSecuenciaValidacionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOSecuenciaValidacionDAO#getValidacionesByCriteria(java.util.Map)
	 */
	public List getValidacionesByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getValidacionesByCriteria", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOSecuenciaValidacionDAO#getTipoDocumentoList(java.util.Map)
	 */
	public List getTipoDocumentoList(Map params) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getTipoDocumentoList", params);
	}


	public boolean insertPametroSecuenciaValidacionSTO(SecuenciaValidacion bean, Usuario usuario) {
		boolean valida=true;
		Integer existe = (Integer)getSqlMapClientTemplate().queryForObject("sto.MantenimientoSTOSQL.validaPametroSecuenciaValidacionSTO", bean);
		if(existe.intValue()!=0){
			valida=false;
		}else{
			bean.setAuditInfo(usuario.getAuditInfo());
			getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertPametroSecuenciaValidacionSTO", bean);	
		}
		return valida;
	}
	
	public boolean updatePametroSecuenciaValidacionSTO(Map criteria,
			Usuario usuario) {
		boolean valida=true;
		List listaSecuencia = (List)criteria.get("listaSecuenciaValidacion");
		
		if(listaSecuencia!=null && listaSecuencia.size()>0){
			for (int i=0;i<listaSecuencia.size();i++){
				SecuenciaValidacion bean = (SecuenciaValidacion)listaSecuencia.get(i);
				if(bean.getCodigoPais()==null){
					bean.setCodigoPais((String)criteria.get("codigoPais"));
				}
				if(bean.getCodigoTipoDocumento()==null){
					bean.setCodigoTipoDocumento((String)criteria.get("codigoTipoDocumentoDigi"));
				}
				bean.setAuditInfo(usuario.getAuditInfo());
				
				
				if(StringUtils.equals(bean.getSecuenciaNueva(), bean.getSecuenciaOriginal())){
					getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updatePametroSecuenciaValidacionSTO", bean);	
				}else{
					Integer existe = (Integer)getSqlMapClientTemplate().queryForObject("sto.MantenimientoSTOSQL.validaPametroSecuenciaValidacionSTO", bean);
					if(existe.intValue()!=0){
						valida=false;
					}else{
						getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updatePametroSecuenciaValidacionSTO", bean);	
					}
				}
				
			}
		}
		
		return valida;
		
	}
	
	public void deletePametroSecuenciaValidacionSTO(SecuenciaValidacion bean) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.deletePametroSecuenciaValidacionSTO", bean);	
		
	}

}