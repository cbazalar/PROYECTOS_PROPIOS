package biz.belcorp.ssicc.web.scsicc.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICFaltantesForm.
 * 
 * @autor: Belcorp
 * @version: 1.0 20/05/2014
 */
public class ReporteSICFaltantesForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public ReporteSICFaltantesForm() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		if (StringUtils.isEmpty(this.codigoPeriodo))
			this.codigoPeriodo = periodo;

	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}
