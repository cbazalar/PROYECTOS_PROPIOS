
/**
 * TMsgOutObtenerDetallePedido.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.soa.mensajes.gestionpedidos;
            

            /**
            *  TMsgOutObtenerDetallePedido bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TMsgOutObtenerDetallePedido
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tMsgOutObtenerDetallePedido
                Namespace URI = http://www.belcorp.biz/soa/mensajes/GestionPedidos
                Namespace Prefix = ns6
                */
            

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
						    public static TMsgOutObtenerDetallePedido parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
						        TMsgOutObtenerDetallePedido object =
						            new TMsgOutObtenerDetallePedido();

						        int event;
						        java.lang.String nillableValue = null;
						        java.lang.String prefix ="";
						        java.lang.String namespaceuri ="";
						        try {
						            
						            while (!reader.isStartElement() && !reader.isEndElement()) {
										reader.next();
									}

						            
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
						                
						                        if (!"tMsgOutObtenerDetallePedido".equals(type)){
						                            //find namespace for the prefix
						                            java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
						                            return (TMsgOutObtenerDetallePedido)biz.belcorp.www.soa.business.ffvv.sicc.pedidosbs.types.ExtensionMapper.getTypeObject(
						                                 nsUri,type,reader);
						                          }
						                    

						              }
						            

						            }

						            

						            
						            // Note all attributes that were handled. Used to differ normal attributes
						            // from anyAttributes.
						            java.util.Vector handledAttributes = new java.util.Vector();
						            

						            
						                
						                reader.next();
						            
						                                
						                                while (!reader.isStartElement() && !reader.isEndElement()) {
															reader.next();
														}
						                            
						                                if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","datos").equals(reader.getName())){
						                            
						                                            object.setDatos(biz.belcorp.www.soa.mensajes.gestionpedidos.Datos_type2.Factory.parse(reader));
						                                          
						                                    reader.next();
						                                
						                          }  // End of if for expected property start element
						                            
						                            else{
						                                // A start element we are not expecting indicates an invalid parameter was passed
						                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
						                            }
						                        
						                                
						                                while (!reader.isStartElement() && !reader.isEndElement()) {
															reader.next();
														}
						                            
						                                if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","paginacion").equals(reader.getName())){
						                            
						                                            object.setPaginacion(biz.belcorp.www.ssg.query.TPaginacion.Factory.parse(reader));
						                                          
						                                    reader.next();
						                                
						                          }  // End of if for expected property start element
						                            
						                            else{
						                                // A start element we are not expecting indicates an invalid parameter was passed
						                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
						                            }
						                        
						                                
						                                while (!reader.isStartElement() && !reader.isEndElement()) {
															reader.next();
														}
						                            
						                                if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","ordenamiento").equals(reader.getName())){
						                            
						                                            object.setOrdenamiento(biz.belcorp.www.ssg.query.TOrdenamiento.Factory.parse(reader));
						                                          
						                                    reader.next();
						                                
						                          }  // End of if for expected property start element
						                            
						                            else{
						                                // A start element we are not expecting indicates an invalid parameter was passed
						                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
						                            }
						                        
						                                
						                                while (!reader.isStartElement() && !reader.isEndElement()) {
															reader.next();
														}
						                            
						                                if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","auditoria").equals(reader.getName())){
						                            
						                                            object.setAuditoria(biz.belcorp.www.ssg.comun.TAuditoria.Factory.parse(reader));
						                                          
						                                    reader.next();
						                                
						                          }  // End of if for expected property start element
						                            
						                            else{
						                                // A start element we are not expecting indicates an invalid parameter was passed
						                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
						                            }
						                          
						                        while (!reader.isStartElement() && !reader.isEndElement()) {
													reader.next();
												}
						                        
						                            if (reader.isStartElement()) {
														// A start element we are not expecting indicates a trailing invalid property
														throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
													}
						                        



						        } catch (javax.xml.stream.XMLStreamException e) {
						            throw new java.lang.Exception(e);
						        }

						        return object;
						    }

						    }//end of factory class
                                

                           private static java.lang.String generatePrefix(java.lang.String namespace) {
						    if(namespace.equals("http://www.belcorp.biz/soa/mensajes/GestionPedidos")){
						        return "ns6";
						    }
						    return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
						}

                           
                        
                            /**
							* field for Datos
							*/

							
							            protected biz.belcorp.www.soa.mensajes.gestionpedidos.Datos_type2 localDatos ;
                            

                        /**
                        * field for Paginacion
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TPaginacion localPaginacion ;
                                

                           /**
                        * field for Ordenamiento
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TOrdenamiento localOrdenamiento ;

                           
                        
                            /**
							* field for Auditoria
							*/

							
							            protected biz.belcorp.www.ssg.comun.TAuditoria localAuditoria ;
                            

                        /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.comun.TAuditoria
                           */
                           public  biz.belcorp.www.ssg.comun.TAuditoria getAuditoria(){
                               return localAuditoria;
                           }
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.soa.mensajes.gestionpedidos.Datos_type2
                           */
                           public  biz.belcorp.www.soa.mensajes.gestionpedidos.Datos_type2 getDatos(){
                               return localDatos;
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
                            

                        /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TOrdenamiento
                           */
                           public  biz.belcorp.www.ssg.query.TOrdenamiento getOrdenamiento(){
                               return localOrdenamiento;
                           }
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TPaginacion
                           */
                           public  biz.belcorp.www.ssg.query.TPaginacion getPaginacion(){
                               return localPaginacion;
                           }

                           
                        
                            /**
							* databinding method to get an XML representation of this object
							*
							*/
							public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
							            throws org.apache.axis2.databinding.ADBException{


							
							         java.util.ArrayList elementList = new java.util.ArrayList();
							         java.util.ArrayList attribList = new java.util.ArrayList();

							        
							                    elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
							                                                              "datos"));
							                    
							                    
							                            if (localDatos==null){
							                                 throw new org.apache.axis2.databinding.ADBException("datos cannot be null!!");
							                            }
							                            elementList.add(localDatos);
							                        
							                    elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
							                                                              "paginacion"));
							                    
							                    
							                            if (localPaginacion==null){
							                                 throw new org.apache.axis2.databinding.ADBException("paginacion cannot be null!!");
							                            }
							                            elementList.add(localPaginacion);
							                        
							                    elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
							                                                              "ordenamiento"));
							                    
							                    
							                            if (localOrdenamiento==null){
							                                 throw new org.apache.axis2.databinding.ADBException("ordenamiento cannot be null!!");
							                            }
							                            elementList.add(localOrdenamiento);
							                        
							                    elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
							                                                              "auditoria"));
							                    
							                    
							                            if (localAuditoria==null){
							                                 throw new org.apache.axis2.databinding.ADBException("auditoria cannot be null!!");
							                            }
							                            elementList.add(localAuditoria);
							                        

							        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
							    
							    

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/soa/mensajes/GestionPedidos");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tMsgOutObtenerDetallePedido",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tMsgOutObtenerDetallePedido",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localDatos==null){
                                                 throw new org.apache.axis2.databinding.ADBException("datos cannot be null!!");
                                            }
                                           localDatos.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","datos"),
                                               xmlWriter);
                                        
                                            if (localPaginacion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("paginacion cannot be null!!");
                                            }
                                           localPaginacion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","paginacion"),
                                               xmlWriter);
                                        
                                            if (localOrdenamiento==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ordenamiento cannot be null!!");
                                            }
                                           localOrdenamiento.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","ordenamiento"),
                                               xmlWriter);
                                        
                                            if (localAuditoria==null){
                                                 throw new org.apache.axis2.databinding.ADBException("auditoria cannot be null!!");
                                            }
                                           localAuditoria.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","auditoria"),
                                               xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

        /**
		   * Auto generated setter method
		   * @param param Auditoria
		   */
		   public void setAuditoria(biz.belcorp.www.ssg.comun.TAuditoria param){
		
		                localAuditoria=param;
		        

		   }

        /**
		   * Auto generated setter method
		   * @param param Datos
		   */
		   public void setDatos(biz.belcorp.www.soa.mensajes.gestionpedidos.Datos_type2 param){
		
		                localDatos=param;
		        

		   }
        
        /**
		   * Auto generated setter method
		   * @param param Ordenamiento
		   */
		   public void setOrdenamiento(biz.belcorp.www.ssg.query.TOrdenamiento param){
		
		                localOrdenamiento=param;
		        

		   }

        /**
		   * Auto generated setter method
		   * @param param Paginacion
		   */
		   public void setPaginacion(biz.belcorp.www.ssg.query.TPaginacion param){
		
		                localPaginacion=param;
		        

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
	        
	        //Si hay data 
	        if (localDatos.localPedido != null) {
	          xmlWriter.writeNamespace("ns5", "http://www.belcorp.biz/canonico/ffvv/Vender");
	          xmlWriter.writeNamespace("ns4", "http://www.belcorp.biz/ssg/Query");
              xmlWriter.writeNamespace("ns3", "http://www.belcorp.biz/canonico/ffvv/Persona");
	          xmlWriter.writeNamespace("ns2", "http://www.belcorp.biz/ssg/Comun");
	          xmlWriter.writeNamespace("ns1", "http://www.belcorp.biz/canonico/ffvv/UbicacionGeografica");
	          xmlWriter.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			} else {
	          xmlWriter.writeNamespace("ns4", "http://www.belcorp.biz/ssg/Query");
	          xmlWriter.writeNamespace("ns2", "http://www.belcorp.biz/ssg/Comun");
			}
	        
	        xmlWriter.setPrefix(prefix, namespace);
	    }
	}

        

        }
           
    