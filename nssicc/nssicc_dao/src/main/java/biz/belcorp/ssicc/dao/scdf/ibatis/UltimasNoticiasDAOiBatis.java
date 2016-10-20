/*
 * Created on 26/12/2005 11:34:11 AM
 *
 * biz.belcorp.ssicc.dao.ibatis.UltimasNoticiasDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scdf.UltimasNoticiasDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.ultimasNoticiasDAO")
public class UltimasNoticiasDAOiBatis extends BaseDAOiBatis implements
        UltimasNoticiasDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UltimasNoticiasDAO#getConsultoras(java.util.Map)
     */
    public List getConsultoras(Map criteria) {
        List consultoras = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getConsultoras", criteria);
        return consultoras;
    }

    public List getMensajesPrivilege(Map mensajesCriteria){
    	 List consultoras = getSqlMapClientTemplate().queryForList(
                 "UltimasNoticiasSQL.getMensajes", mensajesCriteria);
         return consultoras;
    	
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UltimasNoticiasDAO#getFichasInscripcion(java.util.Map)
     */
    public List getFichasInscripcion(Map criteria) {
        List fichas = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getFichasInscripcion", criteria);
        return fichas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UltimasNoticiasDAO#getTarjetasPuntos(java.util.Map)
     */
    public List getTarjetasPuntos(Map criteria) {
        List tarjetas = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getTarjetasPuntos", criteria);
        return tarjetas;
    }

    public List getCarneBeneficios(Map carneBeneficiosCriteria){
    	List carneBeneficios = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getCarneBeneficios", carneBeneficiosCriteria);
        return carneBeneficios;
    }
    
    public List getIndicadorLEbel(Map criteria){
    	List listIndicadorLEbel = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getIndicadorLEbel", criteria);
        return listIndicadorLEbel;
    }
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#getPremiosSolicitados(java.util.Map)
     */
    public List getPremiosSolicitados(Map criteria) {
        List premios = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getPremiosSolicitados", criteria);
        return premios;
    }

    public List getPremiosAcumulados(Map premiosAcumCriteria){
    	List premios = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getPremiosAcumulados", premiosAcumCriteria);
        return premios;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#getClientesCumpleaos(java.util.Map)
     */
    public List getClientesCumpleaños(Map criteria) {
        List clientes = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getClientesCumpleanyos", criteria);
        return clientes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#insertUltimasNoticiasConsultora(java.lang.String,
     *      java.lang.String)
     */
    public void insertUltimasNoticiasConsultora(String codigoConsultora,
            String archivoXML) {
        Map map = new HashMap();
        map.put("codigoConsultora", codigoConsultora);
        map.put("archivoXML", archivoXML);

        getSqlMapClientTemplate().insert(
                "UltimasNoticiasSQL.insertUltimasNoticiasConsultora", map);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#executeEliminarUltimasNoticias()
     */
    public void executeEliminarUltimasNoticias() {
        getSqlMapClientTemplate().update(
                "UltimasNoticiasSQL.executeEliminarUltimasNoticias", null);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#getClientesRechazados(java.util.Map)
     */
    public List getClientesRechazados(Map criteria) {
        List clientesRechazados = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getClientesRechazados", criteria);
        return clientesRechazados;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#getMovimientoClientes(java.util.Map)
     */
    public List getMovimientoClientes(Map criteria) {
        List movimientos = getSqlMapClientTemplate().queryForList(
                "UltimasNoticiasSQL.getMovimientoClientes", criteria);
        return movimientos;
    }

}
