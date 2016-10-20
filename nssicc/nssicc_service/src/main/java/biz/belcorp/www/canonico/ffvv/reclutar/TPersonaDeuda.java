
/**
 * TPersonaDeuda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.reclutar;
            

            /**
            *  TPersonaDeuda bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TPersonaDeuda
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tPersonaDeuda
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Reclutar
                Namespace Prefix = ns4
                */
            

                        /**
                        * field for Persona
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.persona.TPersona localPersona ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.persona.TPersona
                           */
                           public  biz.belcorp.www.canonico.ffvv.persona.TPersona getPersona(){
                               return localPersona;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Persona
                               */
                               public void setPersona(biz.belcorp.www.canonico.ffvv.persona.TPersona param){
                            
                                            this.localPersona=param;
                                    

                               }
                            

                        /**
                        * field for Deuda
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[] localDeuda ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDeudaTracker = false ;

                           public boolean isDeudaSpecified(){
                               return localDeudaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[] getDeuda(){
                               return localDeuda;
                           }

                           
                        


                               
                              /**
                               * validate the array for Deuda
                               */
                              protected void validateDeuda(biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Deuda
                              */
                              public void setDeuda(biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[] param){
                              
                                   validateDeuda(param);

                               localDeudaTracker = param != null;
                                      
                                      this.localDeuda=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.reclutar.TDeuda
                             */
                             public void addDeuda(biz.belcorp.www.canonico.ffvv.reclutar.TDeuda param){
                                   if (localDeuda == null){
                                   localDeuda = new biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[]{};
                                   }

                            
                                 //update the setting tracker
                                localDeudaTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localDeuda);
                               list.add(param);
                               this.localDeuda =
                             (biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[list.size()]);

                             }
                             

                        /**
                        * field for Valoracion
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.reclutar.TValoracion localValoracion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.reclutar.tValoracion
                           */
                           public  biz.belcorp.www.canonico.ffvv.reclutar.TValoracion getValoracion(){
                               return localValoracion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Valoracion
                               */
                               public void setValoracion(biz.belcorp.www.canonico.ffvv.reclutar.TValoracion param){
                            
                                            this.localValoracion=param;
                                    

                               }
                            
                       /**
                        * field for MotivoBloqueo
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[] localMotivoBloqueo ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[] getMotivoBloqueo(){
                               return localMotivoBloqueo;
                           }

                           
                        


                               
                              /**
                               * validate the array for MotivoBloqueo
                               */
                              protected void validateMotivoBloqueo(biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param MotivoBloqueo
                              */
                              public void setMotivoBloqueo(biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[] param){
                              
                                   validateMotivoBloqueo(param);

                               
                                      this.localMotivoBloqueo=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo
                             */
                             public void addMotivoBloqueo(biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo param){
                                   if (localMotivoBloqueo == null){
                                   localMotivoBloqueo = new biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localMotivoBloqueo);
                               list.add(param);
                               this.localMotivoBloqueo =
                             (biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[list.size()]);

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Reclutar");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tPersonaDeuda",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tPersonaDeuda",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localPersona==null){
                                                 throw new org.apache.axis2.databinding.ADBException("persona cannot be null!!");
                                            }
                                           localPersona.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","persona"),
                                               xmlWriter);
                                         if (localDeudaTracker){
                                       if (localDeuda!=null){
                                            for (int i = 0;i < localDeuda.length;i++){
                                                if (localDeuda[i] != null){
                                                 localDeuda[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","deuda"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("deuda cannot be null!!");
                                        
                                    }
                                 }
                                         if (localValoracion==null){
                                             throw new org.apache.axis2.databinding.ADBException("valoracion cannot be null!!");
                                        }
                                         localValoracion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","valoracion"),
                                           xmlWriter);
                                         
                                   
                                   if (localMotivoBloqueo!=null){
                                        for (int i = 0;i < localMotivoBloqueo.length;i++){
                                            if (localMotivoBloqueo[i] != null){
                                            	localMotivoBloqueo[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","motivoBloqueo"),
                                                       xmlWriter);
                                            } else {
                                               
                                                       throw new org.apache.axis2.databinding.ADBException("motivoBloqueo cannot be null!!");
                                                
                                            }

                                        }
                                 } else {
                                    
                                           throw new org.apache.axis2.databinding.ADBException("motivoBloqueo cannot be null!!");
                                    
                                }
                             
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Reclutar")){
                return "ns4";
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

                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar",
                                                                      "persona"));
                            
                            
                                    if (localPersona==null){
                                         throw new org.apache.axis2.databinding.ADBException("persona cannot be null!!");
                                    }
                                    elementList.add(localPersona);
                                 if (localDeudaTracker){
                             if (localDeuda!=null) {
                                 for (int i = 0;i < localDeuda.length;i++){

                                    if (localDeuda[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar",
                                                                          "deuda"));
                                         elementList.add(localDeuda[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("deuda cannot be null!!");
                                    
                             }

                        }
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar",
                                                                      "valoracion"));
                                      
									  elementList.add(localValoracion==null?null:
									    	   localValoracion);
			                            
                         if (localMotivoBloqueo!=null) {
                             for (int i = 0;i < localMotivoBloqueo.length;i++){

                                if (localMotivoBloqueo[i] != null){
                                     elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar",
                                                                      "motivoBloqueo"));
                                     elementList.add(localMotivoBloqueo[i]);
                                } else {
                                    
                                           throw new org.apache.axis2.databinding.ADBException("motivoBloqueo cannot be null !!");
                                        
                                }

                             }
                         } else {
                             
                                    throw new org.apache.axis2.databinding.ADBException("motivoBloqueo cannot be null!!");
                                
                         }
                         
                                    

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
        public static TPersonaDeuda parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TPersonaDeuda object =
                new TPersonaDeuda();

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
                    
                            if (!"tPersonaDeuda".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TPersonaDeuda)biz.belcorp.www.soa.business.ffvv.sicc.prospectobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                        java.util.ArrayList list3 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","persona").equals(reader.getName())){
                                
                                                object.setPersona(biz.belcorp.www.canonico.ffvv.persona.TPersona.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","deuda").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list2.add(biz.belcorp.www.canonico.ffvv.reclutar.TDeuda.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone2 = false;
                                                        while(!loopDone2){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone2 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","deuda").equals(reader.getName())){
                                                                    list2.add(biz.belcorp.www.canonico.ffvv.reclutar.TDeuda.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone2 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setDeuda((biz.belcorp.www.canonico.ffvv.reclutar.TDeuda[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.reclutar.TDeuda.class,
                                                                list2));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","valorcion").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setValoracion(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setValoracion(biz.belcorp.www.canonico.ffvv.reclutar.TValoracion.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
	                              }  // End of if for expected property start element
	                                
	                                else{
	                                    // A start element we are not expecting indicates an invalid parameter was passed
	                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
	                                }
                            
                                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","motivoBloqueo").equals(reader.getName())){
                                
                                                                        
                                    // Process the array and step past its final element's end.
                                    list3.add(biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone3 = false;
                                                        while(!loopDone3){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone3 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Reclutar","motivoBloqueo").equals(reader.getName())){
                                                                    list3.add(biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone3 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setMotivoBloqueo((biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo.class,
                                                                list3));
                                                            
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
           
    