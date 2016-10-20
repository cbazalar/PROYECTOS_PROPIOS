package biz.belcorp.ssicc.service.util;

import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;

/**
 * Listas Constantes usadas en la aplicacin.
 * <p>
 * <a href="ConstantsList.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public class ConstantsList {

	/**
	 * Lista de Campañas FDV
	 * @return
	 */
	public static final List getCampanhasListFDV(){
		
		List listCampanha = new ArrayList();
		GenericBean bean = new GenericBean();
		
		String indice = "";
		for (int i = 1; i <= 18; i++) {
			
			if(i < 10){
				indice = "0"+i;
			}else{
				indice = ""+i;
			}
			
			bean = new GenericBean();
			bean.setCodigo(indice);
			bean.setDescripcion("C-"+indice);
			listCampanha.add(bean);
		}
		
		return listCampanha;
	}

	/**
	 * Lista de Procesos de Periodos FDV
	 * @return
	 */
	public static List getProcessPeriodListFDV() {
		
		List listPeriod = new ArrayList();
		GenericBean bean = new GenericBean();
		
		for (int i = 1; i <= 3; i++) {
			
			bean = new GenericBean();
			bean.setCodigo(""+i);
			bean.setDescripcion("P-"+i);
			listPeriod.add(bean);
		}
		
		return listPeriod;
	}

	/**
	 * Lista de Grupo de Poblaciones FDV
	 * @return
	 */
	public static List getGroupPoblationListFDV() {

		List listPeriod = new ArrayList();
		GenericBean bean = new GenericBean();
		
		for (int i = 2; i <= 5; i++) {
			
			bean = new GenericBean();
			bean.setCodigo(""+i);
			bean.setDescripcion(""+i);
			listPeriod.add(bean);
		}
		
		return listPeriod;
	}
}
