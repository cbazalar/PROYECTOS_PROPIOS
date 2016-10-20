package biz.belcorp.ssicc.web.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoBASLoggerForm extends BaseProcesoForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1699145763388650273L;
	private String[] loggerList;
	private String level;
	/**
	 * @return the loggerList
	 */
	public String[] getLoggerList() {
		return loggerList;
	}
	/**
	 * @param loggerList the loggerList to set
	 */
	public void setLoggerList(String[] loggerList) {
		this.loggerList = loggerList;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
