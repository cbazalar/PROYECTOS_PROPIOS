/**
 * WebServiceEquifaxLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.com.equifax.WebService;

public class WebServiceEquifaxLocator extends org.apache.axis.client.Service implements biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifax {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1631010715869637723L;

	public WebServiceEquifaxLocator() {
    }


    public WebServiceEquifaxLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServiceEquifaxLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS00230301EndpointPort
    private java.lang.String WS00230301EndpointPort_address = "http://190.81.188.82:8080/ws-00230301-ejb/WS00230301";

    public java.lang.String getWS00230301EndpointPortAddress() {
        return WS00230301EndpointPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WS00230301EndpointPortWSDDServiceName = "WS00230301EndpointPort";

    public java.lang.String getWS00230301EndpointPortWSDDServiceName() {
        return WS00230301EndpointPortWSDDServiceName;
    }

    public void setWS00230301EndpointPortWSDDServiceName(java.lang.String name) {
        WS00230301EndpointPortWSDDServiceName = name;
    }

    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint getWS00230301EndpointPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WS00230301EndpointPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWS00230301EndpointPort(endpoint);
    }

    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint getWS00230301EndpointPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301EndpointBindingStub _stub = new biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301EndpointBindingStub(portAddress, this);
            _stub.setPortName(getWS00230301EndpointPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWS00230301EndpointPortEndpointAddress(java.lang.String address) {
        WS00230301EndpointPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint.class.isAssignableFrom(serviceEndpointInterface)) {
            	biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301EndpointBindingStub _stub = new biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301EndpointBindingStub(new java.net.URL(WS00230301EndpointPort_address), this);
                _stub.setPortName(getWS00230301EndpointPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WS00230301EndpointPort".equals(inputPortName)) {
            return getWS00230301EndpointPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://equifax.com/WebService", "WebServiceEquifax");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://equifax.com/WebService", "WS00230301EndpointPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WS00230301EndpointPort".equals(portName)) {
            setWS00230301EndpointPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
