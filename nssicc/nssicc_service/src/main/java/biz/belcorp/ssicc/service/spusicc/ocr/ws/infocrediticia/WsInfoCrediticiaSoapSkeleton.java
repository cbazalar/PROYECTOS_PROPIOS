package biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia;

public class WsInfoCrediticiaSoapSkeleton implements biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap, org.apache.axis.wsdl.Skeleton {
    private biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("helloWorld", _params, new javax.xml.namespace.QName("http://tempuri.org/", "HelloWorldResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "HelloWorld"));
        _oper.setSoapAction("http://tempuri.org/HelloWorld");
        _myOperationsList.add(_oper);
        if (_myOperations.get("helloWorld") == null) {
            _myOperations.put("helloWorld", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("helloWorld")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoConsultora"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultar", _params, new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "Consultar"));
        _oper.setSoapAction("http://tempuri.org/Consultar");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultar") == null) {
            _myOperations.put("consultar", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultar")).add(_oper);
    }

    public WsInfoCrediticiaSoapSkeleton() {
        this.impl = new biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoapImpl();
    }

    public WsInfoCrediticiaSoapSkeleton(biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap impl) {
        this.impl = impl;
    }
    public java.lang.String helloWorld() throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.helloWorld();
        return ret;
    }

    public java.lang.String consultar(java.lang.String codigoConsultora, java.lang.String token) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.consultar(codigoConsultora, token);
        return ret;
    }

}
