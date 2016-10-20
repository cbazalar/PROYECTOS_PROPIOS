package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLETRecepcionarCursosForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSATRecepcionarImpresionBoletasEntregaForm;

@ManagedBean
@SessionScoped
public class InterfazLETRecepcionarCursosAction extends BaseInterfazAbstractAction{


	private static final long serialVersionUID = -8499061176407397051L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLETRecepcionarCursosForm interfazForm = new InterfazLETRecepcionarCursosForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
		  InterfazLETRecepcionarCursosForm f =(InterfazLETRecepcionarCursosForm)this.formInterfaz;
	        
	        //String codigoProcesoBatch = request.getParameter("codigoProcesoBatch");
			//request.getSession().setAttribute("codigoProcesoBatch", codigoProcesoBatch);
	        
	        
//	        request.getSession().setAttribute(Constants.INTERFAZ_DATA, (Interfaz)request.getAttribute(Constants.INTERFAZ_DATA));
//	        request.getSession().setAttribute("archivoslist", (List)request.getAttribute(Constants.INTERFAZ_FILES));
//	        List archivosList = (List)request.getAttribute(Constants.INTERFAZ_FILES);
//	        LabelArchivos labelArchivos = (LabelArchivos)archivosList.get(0);
//	        String pesoArchivo = labelArchivos.getPesoArchivo();
//	        f.setPesoArchivo(pesoArchivo);
	}

}
