/*
 * Created on 26/09/2006 09:53:35 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarCronogramaFacturacionServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazREUEnviarMatrizCampanyaServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius </a>
 */
@Service("sisicc.interfazREUEnviarMatrizCampanyaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazREUEnviarMatrizCampanyaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	
	@Resource(name="sisicc.interfazDAO")
    private InterfazDAO interfazDAO;

    

    /**
     * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
     * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
     * la interfaz
     * 
     * @param interfazParams
     *            parametros de la interfaz
     * @return Map con parametros del query
     */
    public Map prepareQueryParams(InterfazParams interfazParams)
            throws InterfazException {
        // Obtenemos los parametros por defecto de la clase padre
        Map queryParams = super.prepareQueryParams(interfazParams);

        // Agregamos los parametros propios de esta interfaz
        InterfazPK pk = new InterfazPK(interfazParams.getInterfaz()
                .getCodigoPais(), interfazParams.getInterfaz()
                .getCodigoSistema(), interfazParams.getInterfaz().getCodigo());
        queryParams.put("fechaUltimoProceso", this.interfazDAO
                .getFechaUltimoProceso(pk));

        if (log.isDebugEnabled()) {
            log.debug(queryParams);
        }

        return queryParams;
    }
    
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazREUEnviarMatrizCampanya(params);
	}
    

//	protected List readData(Map queryParams) throws InterfazException {
//        if (log.isDebugEnabled())
//            log.debug("Entering 'readData' method ");
//        List listMatrizCampanya = null;
//        try {
//
//            listMatrizCampanya = interfazSiCCDAO
//                    .getInterfazREUMatrizCampanya(queryParams);
//
//            String valor = "";
//            int valorint = 0;
//
//            for (int i = 0; i < listMatrizCampanya.size(); i++) {
//                Map row = (Map) listMatrizCampanya.get(i);
//                if (log.isDebugEnabled()) {
//                    log.debug("row es " + row);
//                }
//                String comisionable = MapUtils.getString(row, "comisionable",
//                        "");
//                if (comisionable.trim().equalsIgnoreCase(Constants.NUMERO_CERO)) {
//                    row.put("indicadorComisionable", Constants.NUMERO_CERO);
//                    row
//                            .put("indicadorComisionAdicional",
//                                    Constants.NUMERO_CERO);
//                    /************* codigo 1 *************/
//                } else {
//                    valor/*valorint*/ = interfazSiCCDAO.getDescuentoVarios(
//                            (String) queryParams.get("codigoPeriodo"),
//                            Constants.CODIGO_TIPO_CLIENTE_DEFAULT,
//                            Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT,
//                            (String) queryParams.get("codigoTipoOferta"),
//                            (String) queryParams.get("codigoNegocio"),
//                            (String) queryParams.get("codigoUnidadNegocio"),
//                            (String) queryParams.get("codigoMarcaProducto"),
//                            Constants.NUMERO_CERO) ;
//                    if (valor/*valorint*/ != null) {
//                        valor = interfazSiCCDAO
//                                .getDescuentoEspecifico((new Integer(valor))
//                                        .intValue());
//
//                    } else {
//                    	valor/*valorint*/ = interfazSiCCDAO
//                                .getDescuentoVarios(
//                                        (String) queryParams
//                                                .get("codigoPeriodo"),
//                                        Constants.CODIGO_TIPO_CLIENTE_DEFAULT,
//                                        Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT,
//                                        (String) queryParams
//                                                .get("codigoTipoOferta"),
//                                        (String) queryParams
//                                                .get("codigoNegocio"),
//                                        (String) queryParams
//                                                .get("codigoUnidadNegocio"),
//                                        (String) queryParams
//                                                .get("codigoMarcaProducto"),
//                                        Constants.NUMERO_UNO);
//
//                        if (valor/*valorint*/ != null) {
//                            valor = interfazSiCCDAO
//                                    .getDescuentoEspecifico((new Integer(valor))
//                                            .intValue());
//                        }else{
//                        	valor = null;
//                        }
//                    }
//
//                    if (valor != null) {
//                        if (valor.equals("C")) {
//                            /************* codigo 2 *************/
//                            row.put("indicadorComisionable",
//                                    Constants.NUMERO_UNO);
//                            row.put("indicadorComisionAdicional",
//                                    Constants.NUMERO_UNO);
//                        } else {
//                            /************* codigo 3 *************/
//                            row.put("indicadorComisionable",
//                                    Constants.NUMERO_UNO);
//                            row.put("indicadorComisionAdicional",
//                                    Constants.NUMERO_CERO);
//                        }
//                    } else {
//                        /************* codigo 4 *************/
//                        row.put("indicadorComisionable", Constants.NUMERO_UNO);
//                        row.put("indicadorComisionAdicional",
//                                Constants.NUMERO_CERO);
//                    }
//                }
//                listMatrizCampanya.set(i, row);
//                if (log.isDebugEnabled()) {
//                    log.debug("row sale : " + row);
//                }
//            }
//        } catch (Exception e) {
//            log.error("Error al leer los datos: " + e.getMessage());
//            throw new InterfazException(e.getMessage());
//        }
//        return listMatrizCampanya;
//    }

	
}
