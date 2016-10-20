package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.BusquedaSTOCodigoVentaMatrizForm;

@ManagedBean
@SessionScoped
public class BusquedaSTOCodigoVentaMatrizAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = -6591481108834941918L;
	
	private String nroCruce;
	private String stoMatrizPrecios=Constants.STO_MATRIZ_PRECIOS;
	private String stoMatrizIncentivos=Constants.STO_MATRIZ_INCENTIVOS;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaSTOCodigoVentaMatrizForm f= new BusquedaSTOCodigoVentaMatrizForm() ;
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		BusquedaSTOCodigoVentaMatrizForm f=(BusquedaSTOCodigoVentaMatrizForm) this.formBusqueda;
		
		String numeroCruceX =this.getNroCruce();
		//--------------------------------------------------
		String numeroCruce = StringUtils.split(numeroCruceX, "|")[0];
		try {
			String indice = StringUtils.split(numeroCruceX, "|")[1];
			f.setIndice(indice);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		//--------------------------------------------------
		
		f.setNumeroCruce(numeroCruce);
		//Valida Codigo Venta
		if(!this.validarCodigoVenta()){			
			throw new Exception("No existe CUV"); 
			
		}
		Map criteria = new HashMap();		
		criteria.put("descripcion",f.getDescripcion());
		criteria.put("codigoVenta",f.getCodigoVenta());
		criteria.put("codigoSAP",f.getCodigoSAP());
		criteria.put("numeroCruce",f.getNumeroCruce());
	
		List lista=new ArrayList();		
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		if(f.getMatriz()==null){
			lista=service.getCodigoVentaMatrizList(criteria);
		}
		else{
			if(f.getMatriz().compareToIgnoreCase("1")==0){
				lista=service.getCodigoVentaMatrizPrecioList(criteria);
			}
			else
				lista=service.getCodigoVentaMatrizIncentivoList(criteria);
		}		
		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		
	}
	
	public void iniciaValores(){
		
		BusquedaSTOCodigoVentaMatrizForm f=(BusquedaSTOCodigoVentaMatrizForm) this.formBusqueda;
		
		String numeroCruceX = this.getNroCruce();
		//--------------------------------------------------
		String numeroCruce = StringUtils.split(numeroCruceX, "|")[0];
		try {
			String indice = StringUtils.split(numeroCruceX, "|")[1];
			f.setIndice(indice);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		//--------------------------------------------------
		
		f.setNumeroCruce(numeroCruce);
		Map criteria = new HashMap();
		criteria.put("numeroCruce",numeroCruce);	

		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		String periodo= service.getPeriodoReferencia(criteria);
		f.setCodigoPeriodo(periodo);
		List lista=service.getCodigoVentaMatrizPrecioList(criteria);		
		
		f.setCodigoSAP("");
		f.setCodigoVenta("");
		f.setDescripcion("");
		f.setMatriz("1");
		
		this.find();
	}
	
	public boolean validarCodigoVenta(){
		BusquedaSTOCodigoVentaMatrizForm f=(BusquedaSTOCodigoVentaMatrizForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(StringUtils.isNotBlank(f.getCodigoVenta())){			
			String valor=ajax.validarCodigosVentaProductoMatriz(f.getDescripcion(),f.getCodigoVenta(),f.getCodigoSAP(),
							f.getMatriz(),f.getNumeroCruce());
			if(!StringUtils.equals(valor, "0"))
				return true;
			else
				return false;			
			
		}else
			return true;		
	}

	public String getNroCruce() {
		return nroCruce;
	}

	public void setNroCruce(String nroCruce) {
		this.nroCruce = nroCruce;
	}

	public String getStoMatrizPrecios() {
		return stoMatrizPrecios;
	}

	public void setStoMatrizPrecios(String stoMatrizPrecios) {
		this.stoMatrizPrecios = stoMatrizPrecios;
	}

	public String getStoMatrizIncentivos() {
		return stoMatrizIncentivos;
	}

	public void setStoMatrizIncentivos(String stoMatrizIncentivos) {
		this.stoMatrizIncentivos = stoMatrizIncentivos;
	}
	
	
	

}
