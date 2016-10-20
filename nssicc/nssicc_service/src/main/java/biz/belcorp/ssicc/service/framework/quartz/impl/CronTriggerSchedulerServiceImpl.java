package biz.belcorp.ssicc.service.framework.quartz.impl;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.framework.quartz.CronTriggerSchedulerService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class CronTriggerSchedulerServiceImpl extends CronTriggerBean implements
	CronTriggerSchedulerService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9114870428955021990L;

	public void setCronExpression(String expression) throws ParseException {
	   super.setCronExpression(expression);
	}

}
