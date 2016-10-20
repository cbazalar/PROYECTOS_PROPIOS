
/**
 * TConsultoraDatosDinamicos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.vender;
            

            /**
            *  TConsultoraDatosDinamicos bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TConsultoraDatosDinamicos
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tConsultoraDatosDinamicos
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Vender
                Namespace Prefix = ns5
                */
            

                        /**
                        * field for EstadoConsultora
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TEstadoConsultora localEstadoConsultora ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TEstadoConsultora
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TEstadoConsultora getEstadoConsultora(){
                               return localEstadoConsultora;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EstadoConsultora
                               */
                               public void setEstadoConsultora(biz.belcorp.www.canonico.ffvv.vender.TEstadoConsultora param){
                            
                                            this.localEstadoConsultora=param;
                                    

                               }
                            

                        /**
                        * field for SegmentoConsultora
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TSegmentoConsultora localSegmentoConsultora ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TSegmentoConsultora
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TSegmentoConsultora getSegmentoConsultora(){
                               return localSegmentoConsultora;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SegmentoConsultora
                               */
                               public void setSegmentoConsultora(biz.belcorp.www.canonico.ffvv.vender.TSegmentoConsultora param){
                            
                                            this.localSegmentoConsultora=param;
                                    

                               }
                            

                        /**
                        * field for Saldo
                        */

                        
                                    protected double localSaldo ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getSaldo(){
                               return localSaldo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Saldo
                               */
                               public void setSaldo(double param){
                            
                                            this.localSaldo=param;
                                    

                               }
                            

                        /**
                        * field for PEG
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TSiNo localPEG ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TSiNo
                           */
                           public  biz.belcorp.www.ssg.query.TSiNo getPEG(){
                               return localPEG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PEG
                               */
                               public void setPEG(biz.belcorp.www.ssg.query.TSiNo param){
                            
                                            this.localPEG=param;
                                    

                               }
                            

                        /**
                        * field for CantidadVisitas
                        */

                        
                                    protected java.math.BigInteger localCantidadVisitas ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getCantidadVisitas(){
                               return localCantidadVisitas;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CantidadVisitas
                               */
                               public void setCantidadVisitas(java.math.BigInteger param){
                            
                                            this.localCantidadVisitas=param;
                                    

                               }
                            

                        /**
                        * field for MontoUltimoPedido
                        */

                        
                                    protected double localMontoUltimoPedido ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMontoUltimoPedidoTracker = false ;

                           public boolean isMontoUltimoPedidoSpecified(){
                               return localMontoUltimoPedidoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoUltimoPedido(){
                               return localMontoUltimoPedido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoUltimoPedido
                               */
                               public void setMontoUltimoPedido(double param){
                            localMontoUltimoPedidoTracker = true;
                                   
                                            this.localMontoUltimoPedido=param;
                                    

                               }
                            

                        /**
                        * field for PorcentajeVentaMultimarcaUltimoPedido
                        */

                        
                                    protected double localPorcentajeVentaMultimarcaUltimoPedido ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPorcentajeVentaMultimarcaUltimoPedidoTracker = false ;

                           public boolean isPorcentajeVentaMultimarcaUltimoPedidoSpecified(){
                               return localPorcentajeVentaMultimarcaUltimoPedidoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getPorcentajeVentaMultimarcaUltimoPedido(){
                               return localPorcentajeVentaMultimarcaUltimoPedido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PorcentajeVentaMultimarcaUltimoPedido
                               */
                               public void setPorcentajeVentaMultimarcaUltimoPedido(double param){
                            localPorcentajeVentaMultimarcaUltimoPedidoTracker = true;
                                   
                                            this.localPorcentajeVentaMultimarcaUltimoPedido=param;
                                    

                               }
                            

                        /**
                        * field for EstatusCapacitacion
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TSiNo localEstatusCapacitacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TSiNo
                           */
                           public  biz.belcorp.www.ssg.query.TSiNo getEstatusCapacitacion(){
                               return localEstatusCapacitacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EstatusCapacitacion
                               */
                               public void setEstatusCapacitacion(biz.belcorp.www.ssg.query.TSiNo param){
                            
                                            this.localEstatusCapacitacion=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Vender");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tConsultoraDatosDinamicos",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tConsultoraDatosDinamicos",
                           xmlWriter);
                   }

               
                   }
               
                                    if (localEstadoConsultora==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "estadoConsultora", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localEstadoConsultora.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estadoConsultora"),
                                        xmlWriter);
                                    }
                                
                                    if (localSegmentoConsultora==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "segmentoConsultora", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localSegmentoConsultora.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","segmentoConsultora"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "saldo", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localSaldo)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSaldo));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    if (localPEG==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "PEG", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPEG.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","PEG"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "cantidadVisitas", xmlWriter);
                             

                                          if (localCantidadVisitas==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadVisitas));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localMontoUltimoPedidoTracker){
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoUltimoPedido", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoUltimoPedido)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoUltimoPedido));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPorcentajeVentaMultimarcaUltimoPedidoTracker){
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "porcentajeVentaMultimarcaUltimoPedido", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localPorcentajeVentaMultimarcaUltimoPedido)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPorcentajeVentaMultimarcaUltimoPedido));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    if (localEstatusCapacitacion==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "estatusCapacitacion", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localEstatusCapacitacion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estatusCapacitacion"),
                                        xmlWriter);
                                    }
                                
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Vender")){
                return "ns5";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "estadoConsultora"));
                            
                            
                                    elementList.add(localEstadoConsultora==null?null:
                                    localEstadoConsultora);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "segmentoConsultora"));
                            
                            
                                    elementList.add(localSegmentoConsultora==null?null:
                                    localSegmentoConsultora);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "saldo"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSaldo));
                            
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "PEG"));
                            
                            
                                    elementList.add(localPEG==null?null:
                                    localPEG);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "cantidadVisitas"));
                                 
                                         elementList.add(localCantidadVisitas==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadVisitas));
                                     if (localMontoUltimoPedidoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoUltimoPedido"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoUltimoPedido));
                            } if (localPorcentajeVentaMultimarcaUltimoPedidoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "porcentajeVentaMultimarcaUltimoPedido"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPorcentajeVentaMultimarcaUltimoPedido));
                            }
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "estatusCapacitacion"));
                            
                            
                                    elementList.add(localEstatusCapacitacion==null?null:
                                    localEstatusCapacitacion);
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static TConsultoraDatosDinamicos parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TConsultoraDatosDinamicos object =
                new TConsultoraDatosDinamicos();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"tConsultoraDatosDinamicos".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TConsultoraDatosDinamicos)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estadoConsultora").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setEstadoConsultora(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setEstadoConsultora(biz.belcorp.www.canonico.ffvv.vender.TEstadoConsultora.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","segmentoConsultora").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setSegmentoConsultora(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setSegmentoConsultora(biz.belcorp.www.canonico.ffvv.vender.TSegmentoConsultora.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","saldo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSaldo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setSaldo(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","PEG").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPEG(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPEG(biz.belcorp.www.ssg.query.TSiNo.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","cantidadVisitas").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCantidadVisitas(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoUltimoPedido").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoUltimoPedido(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoUltimoPedido(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setMontoUltimoPedido(java.lang.Double.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","porcentajeVentaMultimarcaUltimoPedido").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPorcentajeVentaMultimarcaUltimoPedido(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setPorcentajeVentaMultimarcaUltimoPedido(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setPorcentajeVentaMultimarcaUltimoPedido(java.lang.Double.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estatusCapacitacion").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setEstatusCapacitacion(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setEstatusCapacitacion(biz.belcorp.www.ssg.query.TSiNo.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                              
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    