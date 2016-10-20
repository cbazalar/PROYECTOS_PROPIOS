package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.FtpCobrador;

/**
 * Implementacion del DAO que ejecutara la Consulta de Telecobro
 * <p>
 * <a href="ConsultarCOBGenericoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
@Repository("spusicc.consultaCOBGenericoDAO")
public class ConsultaCOBGenericoDAOiBatis extends BaseDAOiBatis implements ConsultaCOBGenericoDAO {
    	
			
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getParametroPais(java.lang.String)
 	*/
	public String getParametroPais(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.consultaCOBSQL.getParametroPais", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getEtapasDeuda()
	 */
	public List getEtapasDeuda(){
		return getSqlMapClientTemplate().queryForList("spusicc.cobranzas.consultaCOBSQL.getEtapasDeuda");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getHistorialGestionesCobranza(java.util.Map)
	 */
	public List getHistorialGestionesCobranza(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getHistorialGestionesCobranza",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getHistorialGestionesCobranza(java.util.Map)
	 */
	public Integer getPorcentajeMetaEtapaVentas(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getPorcentajeMetaEtapaVentas",criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getCarterasConsultoraList(java.util.Map)
	 */
	public List getCarterasConsultoraList(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getHistorialGestionesCobranza",criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getGestionesCobranzaConsultora(java.util.Map)
	 */
	public List getGestionesCobranzaConsultora(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getHistorialGestionesCobranza",criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getCobradoresActivos()
	 */
	public List getCobradoresActivos(){
		return getSqlMapClientTemplate().queryForList("spusicc.cobranzas.consultaCOBSQL.getCobradoresActivos");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getSupervisores()
	 */
	public List getSupervisores(){
		return getSqlMapClientTemplate().queryForList("spusicc.cobranzas.consultaCOBSQL.getSupervisores");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#getSCCarteraList(java.util.Map)
	 */
	public List getSCCarteraList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cobranzas.consultaCOBSQL.getSCCarteraList",criteria);
	}		
		
    public FtpCobrador getFTPCobrador(String codigoCobrador){    	
    	FtpCobrador resultado = (FtpCobrador) getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.consultaCOBSQL.getFTPCobrador",
				codigoCobrador);
		return resultado;
	}
    
    
	public String getDirectorioTemporal(){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.cobranzas.consultaCOBSQL.getDirectorioTemporal");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBGenericoDAO#generarComisionAbogados(java.util.Map)
	 */
	public void generarComisionAbogados(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cobranzas.consultaCOBSQL.executeRepoComisionAbogados", criteria);
	}
}
