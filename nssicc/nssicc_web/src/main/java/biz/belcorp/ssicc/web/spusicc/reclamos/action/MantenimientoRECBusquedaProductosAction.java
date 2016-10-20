package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECBusquedaProductosForm;


@ManagedBean  
@SessionScoped
public class MantenimientoRECBusquedaProductosAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = 2467191524444731839L;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECBusquedaProductosForm busquedaForm = new MantenimientoRECBusquedaProductosForm();	
		return busquedaForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {		
		
		MantenimientoRECBusquedaProductosForm f=(MantenimientoRECBusquedaProductosForm)this.formBusqueda;
		MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService) getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
		
		Map criteria = new HashMap();		
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoPeriodo",f.getCodigoPeriodo());
		criteria.put("codigoVenta",f.getCodigoVenta());
		criteria.put("codigoProducto",f.getCodigoProducto());
		criteria.put("descripcionProducto",f.getDescripcionProducto());
		criteria.put("porcentajeDescuento",f.getPorcentajeDescuento());
		criteria.put("codigoCliente",f.getCodigoCliente());	
		
		List listaProductos=service.getProductosByCriteria(criteria);		
		return listaProductos;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECBusquedaProductosForm f=(MantenimientoRECBusquedaProductosForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String codigoPeriodo = sdf.format(new Date(System.currentTimeMillis()));		
		f.setCodigoPeriodo(codigoPeriodo);		
		
	}

}
