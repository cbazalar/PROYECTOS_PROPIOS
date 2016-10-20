package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.PremioDAO;
import biz.belcorp.ssicc.dao.scdf.model.Premio;

@Repository("scdf.premioDAO")
public class PremioDAOiBatis extends BaseDAOiBatis implements PremioDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getPremios(biz.belcorp.ssicc.scdf.model.Premio)
     */
    public List getPremios(Premio premio) {
        List premios = getSqlMapClientTemplate().queryForList(
                "scdf.PremioSQL.getPremiosByPais", premio);
        return premios;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getPremiosMapByPais(java.lang.String)
     */
    public List getPremiosMapByPais(String codigoPais) {
        List premios = getSqlMapClientTemplate().queryForList(
                "scdf.PremioSQL.getPremiosMapByPais", codigoPais);
        return premios;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getPremiosByCriteria(java.util.Map)
     */
    public List getPremiosByCriteria(Map criteria) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#insertPremio(biz.belcorp.ssicc.scdf.model.Premio,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertPremio(Premio premio, Usuario usuario) {
        getSqlMapClientTemplate().insert("scdf.PremioSQL.insertPremio", premio);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#updatePremio(biz.belcorp.ssicc.scdf.model.Premio,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updatePremio(Premio premio, Usuario usuario) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#removePremio(biz.belcorp.ssicc.scdf.model.Premio)
     */
    public void removePremio(Premio premio) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#removePremioByPais(java.lang.String)
     */
    public void removePremioByPais(String codigoPais) {
        getSqlMapClientTemplate().insert("scdf.PremioSQL.removePremioByPais",
                codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getPremiosWithTarjetas(java.lang.String)
     */
    public List getPremiosWithTarjetas(String codigoPais) {
        List premios = getSqlMapClientTemplate().queryForList(
                "scdf.PremioSQL.getPremiosWithTarjetas", codigoPais);
        return premios;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#executeProcesaPremios(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public int executeProcesaPremios(Pais pais, Usuario usuario) {
        Map map = new HashMap();
        map.put("codigoPais", pais.getCodigo());
        map.put("totalPremios", "0");
        getSqlMapClientTemplate().update("scdf.PremioSQL.procesaPremios", map);
        return Integer.parseInt((String) map.get("totalPremios"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getEstadosPremiosByPais(java.lang.String)
     */
    public List getEstadosPremiosByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.PremioSQL.getEstadosPremiosByPais", codigoPais);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getPremiosCabecera(java.util.Map)
     */
    public List getPremiosCabecera(Map criteria) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.PremioSQL.getCabeceraOCR", criteria);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getPremiosDetalle(java.util.Map)
     */
    public List getPremiosDetalle(Map criteria) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.PremioSQL.getDetalleOCR", criteria);
        return resultados;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#executeGenerarSolicitudesPremiosPrivilege(java.util.Map)
     */
    public void executeGenerarSolicitudesPremiosPrivilege(Map criteria){
    	getSqlMapClientTemplate().update("scdf.PremioSQL.executeGenerarSolicitudesPremiosPrivilege", criteria);
    }
    

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getCantidadPremiosxEstado(java.util.Map)
     */
    public List getCantidadPremiosxEstado(Map params){
    	 List resultados = getSqlMapClientTemplate().queryForList(
                 "scdf.PremioSQL.getCantidadPremiosxEstado", params);
         return resultados;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.PremioDAO#getNumeroSolicitudesGeneradas()
     */
    public Integer getNumeroSolicitudesGeneradas(){
    	return(Integer)getSqlMapClientTemplate().queryForObject("scdf.PremioSQL.getNumeroSolicitudesGeneradas",null);
    }
}