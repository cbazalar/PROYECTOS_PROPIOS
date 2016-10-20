/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ControlFacturacionDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIRecepcionarFactorConversionServiceImpl"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIRecepcionarFactorConversionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarFactorConversionServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="scdf.controlFacturacionDAO")
    ControlFacturacionDAO controlFacturacionDAO;

   
    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        log.debug("valorFactor: " + row.get("valorFactor"));
        try {
            cargarFactor(row, interfazParams.getUsuario());
        } catch (Exception e) {
            throw new InterfazException(e.getMessage());
        }
    }

    public synchronized void cargarFactor(Map row, Usuario usuario)
            throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarFactor'");
        }
        try {
            ControlFacturacion controlFacturacion = new ControlFacturacion();
            BeanUtils.copyProperties(controlFacturacion, row);
            controlFacturacionDAO.updateControlFacturacion(controlFacturacion,
                    usuario);
            controlFacturacion = null;
        } catch (Exception e) {
            throw e;
        }
    }
}
