/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ComConsMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.DireccionMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.EncuestaConsLoveMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.PrefComMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ProcesoMAEWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ResultadoMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.SgmtoGrupLoveMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.TipLogroLoveMAEWebService;


/**
 * <p>
 * <a href="ProcesoMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ProcesoMAEWebService {

 
    /**
     * permite realizar la actualizaci�n (modificaci�n e inserci�n) de la informaci�n de la consultora
     *  registrada en la encuesta del formato typing Tool (love) metodo publicado en web service.
     * @param codigoCliente
     * @param encuesta
     * @param comunicacionConsultora
     * @param preferenciaContacto
     * @param segmentoGrupo3
     * @param segmentoGrupo6
     * @param segmentoEstablecida
     * @param tipoLogro
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ProcesoMAEWebServiceResultado saveEncuesta(String codigoCliente,
    		 EncuestaConsLoveMAEWebService encuesta,
    		 ComConsMAEWebService [] comunicacionConsultora,
    		 PrefComMAEWebService [] preferenciaContacto,
    		 SgmtoGrupLoveMAEWebService segmentoGrupo3,
    		 SgmtoGrupLoveMAEWebService segmentoGrupo6,
    		 SgmtoGrupLoveMAEWebService segmentoEstablecida,
    		 TipLogroLoveMAEWebService  tipoLogro,
    		 String codigoIsoIdioma) throws RemoteException;
    
    
    /**
     * permite realizar la actualizaci�n (modificaci�n e inserci�n) de la informaci�n de la consultora
     *  registrada en la encuesta del formato typing Tool (love) y mantine la intregridad de los datos.
     * @param codigoCliente
     * @param encuesta
     * @param comunicacionConsultora
     * @param preferenciaContacto
     * @param segmentoGrupo3
     * @param segmentoGrupo6
     * @param segmentoEstablecida
     * @param tipoLogro
     * @param codigoIsoIdioma
     * @throws Exception
     */
    public void saveOrUpdateAll(String codigoCliente,
    		 EncuestaConsLoveMAEWebService encuesta,
    		 ComConsMAEWebService [] comunicacionConsultora,
    		 PrefComMAEWebService [] preferenciaContacto,
    		 SgmtoGrupLoveMAEWebService segmentoGrupo3,
    		 SgmtoGrupLoveMAEWebService segmentoGrupo6,
    		 SgmtoGrupLoveMAEWebService segmentoEstablecida,
    		 TipLogroLoveMAEWebService  tipoLogro,
    		 String codigoIsoIdioma) throws Exception;
    
    /**
     * Este servicio web permite insertar / actualizar los datos de una consultora registrada en MAE
     * 
     * @param indAccion
     * @param indLlave
     * @param codConsultora
     * @param tipoCliente
     * @param subCliente
     * @param codZona
     * @param codTerri
     * @param fecIng
     * @param paqDoc
     * @param apePat
     * @param apeMat
     * @param priNom
     * @param segNom
     * @param nacCliente
     * @param tipoDoc
     * @param nroDoc
     * @param fecNac
     * @param sexo
     * @param estCivil
     * @param gradIns
     * @param nomAbrev
     * @param CUB
     * @param codPais
     * @param indActiva
     * @param indOrigen
     * @param codGrupoFuncional
     * @param desGrupoFuncional
     * @param usuRed
     * @param codJefeCUB
     * @param valRelContr
     * @param mailBelcorp
     * @param nomJefeDir
     * @param valPueOrg
     * @param lsDireccion
     * @return
     */
    public ResultadoMAEWebService mantenerCliente(String indAccion,
    		String indLlave,
    		String codConsultora,
    		String tipoCliente,
     		String subCliente,
     		String codZona,
     		String codTerri,
     		String fecIng,
     		String paqDoc,
     		String apePat,
     		String apeMat,
     		String priNom,
     		String segNom,
     		String nacCliente,
     		String tipoDoc,
     		String nroDoc,
     		String fecNac,
     		String sexo,
     		String estCivil,
     		String gradIns,
     		String nomAbrev,
     		String CUB,
     		String codPais,
     		String indActiva,
     		String indOrigen,
     		String codGrupoFuncional,
     		String desGrupoFuncional,
     		String usuRed,
     		String codJefeCUB,
     		String valRelContr,
     		String mailBelcorp,
     		String nomJefeDir,
     		String valPueOrg,
     		String codEmpleado,
     		DireccionMAEWebService[] lsDireccion);
    
    
    /**
     * Este servicio web permite buscar una consultora registrada en MAE
     * 
     * @param codConsultora
     * @param CUB
     * @param usuRed
     * @param tipoDoc
     * @param nroDoc
     * @return
     */
    public ResultadoMAEWebService buscarConsultora(String codPais, String codConsultora, String CUB, String usuRed, String tipoDoc, String nroDoc);

}
