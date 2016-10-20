
/**
 * ProspectoBSMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package biz.belcorp.www.soa.business.ffvv.sicc.prospectobs;

        /**
        *  ProspectoBSMessageReceiverInOut message receiver
        */

        public class ProspectoBSMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ProspectoBSSkeleton skel = (ProspectoBSSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("obtenerValidacionCrediticia".equals(methodName)){
                
                biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia msgOutValidacionCrediticia1 = null;
	                        biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia wrappedParam =
                                                             (biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               msgOutValidacionCrediticia1 =
                                                   
                                                   
                                                         skel.obtenerValidacionCrediticia(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), msgOutValidacionCrediticia1, false, new javax.xml.namespace.QName("http://www.belcorp.biz/soa/business/ffvv/sicc/ProspectoBS",
                                                    "obtenerValidacionCrediticia"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (MsgFaultObtenerValidacionCrediticia e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"msgFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
        
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(biz.belcorp.www.ssg.comun.MsgFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(biz.belcorp.www.ssg.comun.MsgFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia wrapobtenerValidacionCrediticia(){
                                biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia wrappedElement = new biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia.class.equals(type)){
                
                           return biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia.class.equals(type)){
                
                           return biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (biz.belcorp.www.ssg.comun.MsgFault.class.equals(type)){
                
                           return biz.belcorp.www.ssg.comun.MsgFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    