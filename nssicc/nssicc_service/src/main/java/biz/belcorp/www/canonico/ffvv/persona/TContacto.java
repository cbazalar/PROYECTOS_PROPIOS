
/**
 * TContacto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.persona;
            

            /**
            *  TContacto bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TContacto
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tContacto
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Persona
                Namespace Prefix = ns3
                */
            

                        /**
                        * field for Telefono
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.persona.TTelefono[] localTelefono ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.persona.TTelefono[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.persona.TTelefono[] getTelefono(){
                               return localTelefono;
                           }

                           
                        


                               
                              /**
                               * validate the array for Telefono
                               */
                              protected void validateTelefono(biz.belcorp.www.canonico.ffvv.persona.TTelefono[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param Telefono
                              */
                              public void setTelefono(biz.belcorp.www.canonico.ffvv.persona.TTelefono[] param){
                              
                                   validateTelefono(param);

                               
                                      this.localTelefono=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.persona.TTelefono
                             */
                             public void addTelefono(biz.belcorp.www.canonico.ffvv.persona.TTelefono param){
                                   if (localTelefono == null){
                                   localTelefono = new biz.belcorp.www.canonico.ffvv.persona.TTelefono[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTelefono);
                               list.add(param);
                               this.localTelefono =
                             (biz.belcorp.www.canonico.ffvv.persona.TTelefono[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.persona.TTelefono[list.size()]);

                             }
                             

                        /**
                        * field for EMail
                        * This was an Array!
                        */

                        
                                    protected java.lang.String[] localEMail ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEMailTracker = false ;

                           public boolean isEMailSpecified(){
                               return localEMailTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String[]
                           */
                           public  java.lang.String[] getEMail(){
                               return localEMail;
                           }

                           
                        


                               
                              /**
                               * validate the array for EMail
                               */
                              protected void validateEMail(java.lang.String[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param EMail
                              */
                              public void setEMail(java.lang.String[] param){
                              
                                   validateEMail(param);

                               localEMailTracker = true;
                                      
                                      this.localEMail=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param java.lang.String
                             */
                             public void addEMail(java.lang.String param){
                                   if (localEMail == null){
                                   localEMail = new java.lang.String[]{};
                                   }

                            
                                 //update the setting tracker
                                localEMailTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localEMail);
                               list.add(param);
                               this.localEMail =
                             (java.lang.String[])list.toArray(
                            new java.lang.String[list.size()]);

                             }
                             

                        /**
                        * field for Direccion
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.persona.TDireccion[] localDireccion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.persona.TDireccion[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.persona.TDireccion[] getDireccion(){
                               return localDireccion;
                           }

                           
                        


                               
                              /**
                               * validate the array for Direccion
                               */
                              protected void validateDireccion(biz.belcorp.www.canonico.ffvv.persona.TDireccion[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param Direccion
                              */
                              public void setDireccion(biz.belcorp.www.canonico.ffvv.persona.TDireccion[] param){
                              
                                   validateDireccion(param);

                               
                                      this.localDireccion=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.persona.TDireccion
                             */
                             public void addDireccion(biz.belcorp.www.canonico.ffvv.persona.TDireccion param){
                                   if (localDireccion == null){
                                   localDireccion = new biz.belcorp.www.canonico.ffvv.persona.TDireccion[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localDireccion);
                               list.add(param);
                               this.localDireccion =
                             (biz.belcorp.www.canonico.ffvv.persona.TDireccion[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.persona.TDireccion[list.size()]);

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Persona");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tContacto",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tContacto",
                           xmlWriter);
                   }

               
                   }
               
                                       if (localTelefono!=null){
                                            for (int i = 0;i < localTelefono.length;i++){
                                                if (localTelefono[i] != null){
                                                 localTelefono[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","telefono"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Persona", "telefono", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Persona", "telefono", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                  if (localEMailTracker){
                             if (localEMail!=null) {
                                   namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                   for (int i = 0;i < localEMail.length;i++){
                                        
                                            if (localEMail[i] != null){
                                        
                                                writeStartElement(null, namespace, "eMail", xmlWriter);

                                            
                                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEMail[i]));
                                                    
                                                xmlWriter.writeEndElement();
                                              
                                                } else {
                                                   
                                                           // write null attribute
                                                            namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                                            writeStartElement(null, namespace, "eMail", xmlWriter);
                                                            writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                            xmlWriter.writeEndElement();
                                                       
                                                }

                                   }
                             } else {
                                 
                                         // write the null attribute
                                        // write null attribute
                                           writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Persona", "eMail", xmlWriter);

                                           // write the nil attribute
                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                           xmlWriter.writeEndElement();
                                    
                             }

                        }
                                       if (localDireccion!=null){
                                            for (int i = 0;i < localDireccion.length;i++){
                                                if (localDireccion[i] != null){
                                                 localDireccion[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","direccion"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("direccion cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("direccion cannot be null!!");
                                        
                                    }
                                 
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Persona")){
                return "ns3";
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

                
                             if (localTelefono!=null) {
                                 for (int i = 0;i < localTelefono.length;i++){

                                    if (localTelefono[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                          "telefono"));
                                         elementList.add(localTelefono[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                          "telefono"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                          "telefono"));
                                        elementList.add(localTelefono);
                                    
                             }

                         if (localEMailTracker){
                            if (localEMail!=null){
                                  for (int i = 0;i < localEMail.length;i++){
                                      
                                         if (localEMail[i] != null){
                                          elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                              "eMail"));
                                          elementList.add(
                                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEMail[i]));
                                          } else {
                                             
                                                    elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                              "eMail"));
                                                    elementList.add(null);
                                                
                                          }
                                      

                                  }
                            } else {
                              
                                    elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                              "eMail"));
                                    elementList.add(null);
                                
                            }

                        }
                             if (localDireccion!=null) {
                                 for (int i = 0;i < localDireccion.length;i++){

                                    if (localDireccion[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                          "direccion"));
                                         elementList.add(localDireccion[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("direccion cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("direccion cannot be null!!");
                                    
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
        public static TContacto parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TContacto object =
                new TContacto();

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
                    
                            if (!"tContacto".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TContacto)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                        java.util.ArrayList list3 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","telefono").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(biz.belcorp.www.canonico.ffvv.persona.TTelefono.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
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
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","telefono").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(biz.belcorp.www.canonico.ffvv.persona.TTelefono.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTelefono((biz.belcorp.www.canonico.ffvv.persona.TTelefono[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.persona.TTelefono.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","eMail").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                              nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                              if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                  list2.add(null);
                                                       
                                                  reader.next();
                                              } else {
                                            list2.add(reader.getElementText());
                                            }
                                            //loop until we find a start element that is not part of this array
                                            boolean loopDone2 = false;
                                            while(!loopDone2){
                                                // Ensure we are at the EndElement
                                                while (!reader.isEndElement()){
                                                    reader.next();
                                                }
                                                // Step out of this element
                                                reader.next();
                                                // Step to next element event.
                                                while (!reader.isStartElement() && !reader.isEndElement())
                                                    reader.next();
                                                if (reader.isEndElement()){
                                                    //two continuous end elements means we are exiting the xml structure
                                                    loopDone2 = true;
                                                } else {
                                                    if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","eMail").equals(reader.getName())){
                                                         
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list2.add(null);
                                                                   
                                                              reader.next();
                                                          } else {
                                                        list2.add(reader.getElementText());
                                                        }
                                                    }else{
                                                        loopDone2 = true;
                                                    }
                                                }
                                            }
                                            // call the converter utility  to convert and set the array
                                            
                                                    object.setEMail((java.lang.String[])
                                                        list2.toArray(new java.lang.String[list2.size()]));
                                                
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","direccion").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list3.add(biz.belcorp.www.canonico.ffvv.persona.TDireccion.Factory.parse(reader));
                                                                
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
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","direccion").equals(reader.getName())){
                                                                    list3.add(biz.belcorp.www.canonico.ffvv.persona.TDireccion.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone3 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setDireccion((biz.belcorp.www.canonico.ffvv.persona.TDireccion[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.persona.TDireccion.class,
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
           
    