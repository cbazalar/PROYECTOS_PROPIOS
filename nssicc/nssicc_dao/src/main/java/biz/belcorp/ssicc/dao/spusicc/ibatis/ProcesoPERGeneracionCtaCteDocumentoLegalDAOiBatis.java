package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERGeneracionCtaCteDocumentoLegalDAO;

/**
 * Implementacion del DAO que ejecutara la Generacion de Cuenta Corriente por Documento Legal
 * <p>
 * <a href="ProcesoPERGeneracionCtaCteDocumentoLegalDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes</a>
 */
@Repository("sisicc.procesoPERGeneracionCtaCteDocumentoLegalDAO")
public class ProcesoPERGeneracionCtaCteDocumentoLegalDAOiBatis extends BaseDAOiBatis implements ProcesoPERGeneracionCtaCteDocumentoLegalDAO {


    public Map executeGeneracionCtaCteDocumentoLegal(Pais pais, Usuario usuario, String codigoInterfaz, String tipOrigenDatos) {
    	log.debug("ProcesoPERGeneracionCtaCteDocumentoLegalDAOiBatis.executeGeneracionCtaCteDocumentoLegal");
    	Map resultado = new HashMap();
        String numeroLote = "";
    	Map map = new HashMap();
        map.put("codigoPais", pais.getCodigo());
        map.put("usuDigi", usuario.getLogin());
        map.put("tipOrigenDatos", tipOrigenDatos);
        map.put("codigoInterfaz", codigoInterfaz);
        map.put("numeroLote", numeroLote);
        
        int val = getSqlMapClientTemplate().update("sisicc.GeneracionCtaCteDocumentoLegalSQL.generaCtaCteDocumentoLegal", map);
        resultado.put("numeroLote", (String) map.get("numeroLote"));
        resultado.put("numeroLoteSolicitud", (String) map.get("numeroLote"));
        resultado.put("codigoTipoOrigenDatos", (String) map.get("tipOrigenDatos"));
        log.debug("Mostrando resultado "+resultado.toString()+" "+val);
        
        return resultado;
    }

}
