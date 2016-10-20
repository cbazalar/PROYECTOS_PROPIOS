/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;

/**
 * @author USER
 *
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOCuponPagoServiceImpl extends BaseProcesoSTOAbstractService {
}
