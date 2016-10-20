package biz.belcorp.ssicc.service.spusicc.com.equifax.WebService;

public class WS00230301EndpointProxy implements biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint {
  private String _endpoint = null;
  private biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint wS00230301Endpoint = null;
  
  public WS00230301EndpointProxy() {
    _initWS00230301EndpointProxy();
  }
  
  public WS00230301EndpointProxy(String endpoint) {
    _endpoint = endpoint;
    _initWS00230301EndpointProxy();
  }
  
  private void _initWS00230301EndpointProxy() {
    try {
      wS00230301Endpoint = (new biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifaxLocator()).getWS00230301EndpointPort();
      if (wS00230301Endpoint != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wS00230301Endpoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wS00230301Endpoint)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wS00230301Endpoint != null)
      ((javax.xml.rpc.Stub)wS00230301Endpoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint getWS00230301Endpoint() {
    if (wS00230301Endpoint == null)
      _initWS00230301EndpointProxy();
    return wS00230301Endpoint;
  }
  
  public java.lang.String consultaServicio16(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio16 estructuraServicio16_1) throws java.rmi.RemoteException{
    if (wS00230301Endpoint == null)
      _initWS00230301EndpointProxy();
    return wS00230301Endpoint.consultaServicio16(estructuraServicio16_1);
  }
  
  public java.lang.String consultaServicio33(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio33 estructuraServicio33_1) throws java.rmi.RemoteException{
    if (wS00230301Endpoint == null)
      _initWS00230301EndpointProxy();
    return wS00230301Endpoint.consultaServicio33(estructuraServicio33_1);
  }
  
  public java.lang.String consultaServicio35(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio35 estructuraServicio35_1) throws java.rmi.RemoteException{
    if (wS00230301Endpoint == null)
      _initWS00230301EndpointProxy();
    return wS00230301Endpoint.consultaServicio35(estructuraServicio35_1);
  }
  
  public java.lang.String consultaServicio40(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio40 estructuraServicio40_1) throws java.rmi.RemoteException{
    if (wS00230301Endpoint == null)
      _initWS00230301EndpointProxy();
    return wS00230301Endpoint.consultaServicio40(estructuraServicio40_1);
  }
  
  public java.lang.String consultaServicio50(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio50 estructuraServicio50_1) throws java.rmi.RemoteException{
    if (wS00230301Endpoint == null)
      _initWS00230301EndpointProxy();
    return wS00230301Endpoint.consultaServicio50(estructuraServicio50_1);
  }
  
  
}