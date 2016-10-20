package biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia;

public class WsInfoCrediticiaSoapProxy implements biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap {
	  private String _endpoint = null;
	  private biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap wsInfoCrediticiaSoap = null;
	  
	  public WsInfoCrediticiaSoapProxy() {
	    _initWsInfoCrediticiaSoapProxy();
	  }
	  
	  public WsInfoCrediticiaSoapProxy(String endpoint) {
	    _endpoint = endpoint;
	    _initWsInfoCrediticiaSoapProxy();
	  }
	  
	  private void _initWsInfoCrediticiaSoapProxy() {
	    try {
	      wsInfoCrediticiaSoap = (new biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaLocator()).getwsInfoCrediticiaSoap();
	      if (wsInfoCrediticiaSoap != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)wsInfoCrediticiaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)wsInfoCrediticiaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
	  }
	  
	  public String getEndpoint() {
	    return _endpoint;
	  }
	  
	  public void setEndpoint(String endpoint) {
	    _endpoint = endpoint;
	    if (wsInfoCrediticiaSoap != null)
	      ((javax.xml.rpc.Stub)wsInfoCrediticiaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	    
	  }
	  
	  public biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap getWsInfoCrediticiaSoap() {
	    if (wsInfoCrediticiaSoap == null)
	      _initWsInfoCrediticiaSoapProxy();
	    return wsInfoCrediticiaSoap;
	  }
	  
	  public java.lang.String helloWorld() throws java.rmi.RemoteException{
	    if (wsInfoCrediticiaSoap == null)
	      _initWsInfoCrediticiaSoapProxy();
	    return wsInfoCrediticiaSoap.helloWorld();
	  }
	  
	  public java.lang.String consultar(java.lang.String codigoConsultora, java.lang.String token) throws java.rmi.RemoteException{
	    if (wsInfoCrediticiaSoap == null)
	      _initWsInfoCrediticiaSoapProxy();
	    return wsInfoCrediticiaSoap.consultar(codigoConsultora, token);
	  }
	  
	  
	}