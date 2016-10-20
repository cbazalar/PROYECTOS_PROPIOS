package biz.belcorp.ssicc.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.form.ProcesoBASLoggerForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;

@ManagedBean
@SessionScoped
public class ProcesoBASLoggerAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9149493927127959119L;
	
	List loggertList;
	
	/**
	 * @return the loggertList
	 */
	public List getLoggertList() {
		return loggertList;
	}

	/**
	 * @param loggertList the loggertList to set
	 */
	public void setLoggertList(List loggertList) {
		this.loggertList = loggertList;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoBASLoggerForm objForm = new ProcesoBASLoggerForm();
		return objForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled())log.debug("Ingresando a executeProcess");
		//super.executeProceso();// (form, params);
		
		return params;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		if (log.isDebugEnabled())log.debug("Ingresando a prepareParamsBeforeExecute");
		params = super.prepareParamsBeforeExecute(params,form);
		
		ProcesoBASLoggerForm f = (ProcesoBASLoggerForm) this.formProceso;
		String[] names = f.getLoggerList();
		
		String level = f.getLevel();
		
		if (log.isWarnEnabled()) log.warn("Modificando el nivel de log a " + level );
		
		if ( (names != null) && (level != null) )
		{
			for (int i=0; i<names.length; i++)
			{
				Logger logger = Logger.getLogger(names[i]);
				Level lev = Level.toLevel(level);
				logger.setLevel(lev);
				
			}		
		}
		return params;

	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled())log.debug("Ingresando a setViewAttributes");
		this.loggertList = new ArrayList();
		Listar();
		
	}
	public void Listar(){
		this.loggertList = new ArrayList();
		ArrayList al = new ArrayList();
		HashMap hm = new HashMap();
		
		this.mostrarBotonBuscar = false;
		
		//GetRootLogger
		Logger rootLogger = LogManager.getRootLogger();
		String rootLoggerName = rootLogger.getName();
		al.add(rootLoggerName);
		hm.put(rootLoggerName, rootLogger);
		
		//All Other Loggers
		Enumeration e = LogManager.getCurrentLoggers();	
		while (e.hasMoreElements())
		{				
			Logger t1Logger = (Logger) e.nextElement();
			String loggerName = t1Logger.getName();
			al.add(loggerName);
			hm.put(loggerName, t1Logger);		
		}
	
		String[] alLoggerStr = ((String[]) al.toArray(new String[0]));
		Arrays.sort(alLoggerStr);
		for (int i=0; i<alLoggerStr.length; i++)
		{
			Logger t2Logger = (Logger) hm.get(alLoggerStr[i]);
			String t2LoggerName = t2Logger.getName();
			String t2LoggerLevel = t2Logger.getEffectiveLevel().toString();
			String thisParent = "";
			if (t2Logger.getParent() != null)
			{
				thisParent = t2Logger.getParent().getName();
			}
			Base base = new Base();
			base.setCodigo(t2LoggerName);
			base.setDescripcion(t2LoggerName+ " [" + t2LoggerLevel + "]" );
			
			this.loggertList.add(base);
			
			
		}

	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#afterExecuteProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void afterExecuteProcessSuccess() {
		
		log.info("Entro a afterExecuteProcessSuccess");
		Listar();
		
	}
    

}
