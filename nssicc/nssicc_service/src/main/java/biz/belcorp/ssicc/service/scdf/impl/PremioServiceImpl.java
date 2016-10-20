package biz.belcorp.ssicc.service.scdf.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.scdf.PremioDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.PremioService;

@Service("scdf.premioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PremioServiceImpl extends BaseService implements PremioService {

	@Resource(name="scdf.premioDAO")
    private PremioDAO premioDAO;
 
	@Resource(name="scdf.interfazSiCCDAO")
    private InterfazSiCCDAO interfazSiCCDAO;
    

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.PremioService#executeObtienePremios(java.lang.String)
     */
    public List executeObtienePremios(String codigoPais) {
        List resultado = premioDAO.getPremiosWithTarjetas(codigoPais);
        return resultado;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.PremioService#executeProcesamientoPremios(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public int executeProcesamientoPremios(Pais pais, Usuario usuario) {
        return premioDAO.executeProcesaPremios(pais, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.PremioService#selectEstadosPremios(biz.belcorp.ssicc.model.Pais)
     */
    public List selectEstadosPremios(Pais pais) {
        return premioDAO.getEstadosPremiosByPais(pais.getCodigo());
    }

   /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.PremioService#executeGenerarSolicitudesPremiosPrivilege(java.util.Map)
     */
    public void executeGenerarSolicitudesPremiosPrivilege(Map params){
    	premioDAO.executeGenerarSolicitudesPremiosPrivilege(params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.PremioService#getCantidadPremiosxEstado(java.util.Map)
     */
    public List getCantidadPremiosxEstado(Map params){
    	return premioDAO.getCantidadPremiosxEstado(params);
    }
    
   /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.PremioService#executeCargaSiCC(java.lang.String, java.lang.String, java.lang.String)
     */
    public int executeCargaSiCC(String codigoPais, String codigoPeriodo, String usuario) {
        interfazSiCCDAO.executeCargaSiCC(codigoPais, codigoPeriodo, usuario);
        return 0;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.PremioService#generarSolicitudesAtencionPrivilege(biz.belcorp.ssicc.model.Pais, java.lang.String, biz.belcorp.ssicc.model.Usuario, java.util.Map)
     */
    public void generarSolicitudesAtencionPrivilege(Pais pais, String codigoPeriodo, Usuario usuario, Map paramsBatch){
    	// Realizamos la carga de informacion
        if(log.isDebugEnabled()) {
            log.debug("Realizando la carga de la informacin desde SICC ...");
        }
    	executeCargaSiCC(pais.getCodigo(), codigoPeriodo, usuario.getLogin());
    	 // Realizamos el procesamiento de los premios        
        if(log.isDebugEnabled()) {
            log.debug("Realizando el procesamiento de premios ...");
        }
    	executeProcesamientoPremios(pais, usuario); 
    	executeGenerarSolicitudesPremiosPrivilege(paramsBatch);
    }    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.PremioService#getNumeroSolicitudesGeneradas()
     */
    public Integer getNumeroSolicitudesGeneradas(){
    	return premioDAO.getNumeroSolicitudesGeneradas();
    }
}
