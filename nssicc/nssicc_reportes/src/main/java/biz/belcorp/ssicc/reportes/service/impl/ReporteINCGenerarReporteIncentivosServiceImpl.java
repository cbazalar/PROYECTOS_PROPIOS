package biz.belcorp.ssicc.reportes.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.reportes.web.spusicc.inc.form.ReporteINCEstadoPremioDespachadoForm;


/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteINCGenerarReporteIncentivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteINCGenerarReporteIncentivosServiceImpl extends BaseReporteInterfaceImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863893902708922335L;

	
	
	
	
}
