Encoder = {

	// When encoding do we convert characters into html or numerical entities
	EncodeType : "entity",  // entity OR numerical

	isEmpty : function(val){
		if(val){
			return ((val===null) || val.length==0 || /^\s+$/.test(val));
		}else{
			return true;
		}
	},
	// Convert HTML entities into numerical entities
	HTML2Numerical : function(s){
		var arr1 = new Array('&nbsp;','&iexcl;','&cent;','&pound;','&curren;','&yen;','&brvbar;','&sect;','&uml;','&copy;','&ordf;','&laquo;','&not;','&shy;','&reg;','&macr;','&deg;','&plusmn;','&sup2;','&sup3;','&acute;','&micro;','&para;','&middot;','&cedil;','&sup1;','&ordm;','&raquo;','&frac14;','&frac12;','&frac34;','&iquest;','&agrave;','&aacute;','&acirc;','&atilde;','&Auml;','&aring;','&aelig;','&ccedil;','&egrave;','&eacute;','&ecirc;','&euml;','&igrave;','&iacute;','&icirc;','&iuml;','&eth;','&ntilde;','&ograve;','&oacute;','&ocirc;','&otilde;','&Ouml;','&times;','&oslash;','&ugrave;','&uacute;','&ucirc;','&Uuml;','&yacute;','&thorn;','&szlig;','&agrave;','&aacute;','&acirc;','&atilde;','&auml;','&aring;','&aelig;','&ccedil;','&egrave;','&eacute;','&ecirc;','&euml;','&igrave;','&iacute;','&icirc;','&iuml;','&eth;','&ntilde;','&ograve;','&oacute;','&ocirc;','&otilde;','&ouml;','&divide;','&Oslash;','&ugrave;','&uacute;','&ucirc;','&uuml;','&yacute;','&thorn;','&yuml;','&quot;','&amp;','&lt;','&gt;','&oelig;','&oelig;','&scaron;','&scaron;','&yuml;','&circ;','&tilde;','&ensp;','&emsp;','&thinsp;','&zwnj;','&zwj;','&lrm;','&rlm;','&ndash;','&mdash;','&lsquo;','&rsquo;','&sbquo;','&ldquo;','&rdquo;','&bdquo;','&dagger;','&dagger;','&permil;','&lsaquo;','&rsaquo;','&euro;','&fnof;','&alpha;','&beta;','&gamma;','&delta;','&epsilon;','&zeta;','&eta;','&theta;','&iota;','&kappa;','&lambda;','&mu;','&nu;','&xi;','&omicron;','&pi;','&rho;','&sigma;','&tau;','&upsilon;','&phi;','&chi;','&psi;','&omega;','&alpha;','&beta;','&gamma;','&delta;','&epsilon;','&zeta;','&eta;','&theta;','&iota;','&kappa;','&lambda;','&mu;','&nu;','&xi;','&omicron;','&pi;','&rho;','&sigmaf;','&sigma;','&tau;','&upsilon;','&phi;','&chi;','&psi;','&omega;','&thetasym;','&upsih;','&piv;','&bull;','&hellip;','&prime;','&prime;','&oline;','&frasl;','&weierp;','&image;','&real;','&trade;','&alefsym;','&larr;','&uarr;','&rarr;','&darr;','&harr;','&crarr;','&larr;','&uarr;','&rarr;','&darr;','&harr;','&forall;','&part;','&exist;','&empty;','&nabla;','&isin;','&notin;','&ni;','&prod;','&sum;','&minus;','&lowast;','&radic;','&prop;','&infin;','&ang;','&and;','&or;','&cap;','&cup;','&int;','&there4;','&sim;','&cong;','&asymp;','&ne;','&equiv;','&le;','&ge;','&sub;','&sup;','&nsub;','&sube;','&supe;','&oplus;','&otimes;','&perp;','&sdot;','&lceil;','&rceil;','&lfloor;','&rfloor;','&lang;','&rang;','&loz;','&spades;','&clubs;','&hearts;','&diams;');
		var arr2 = new Array('&#160;','&#161;','&#162;','&#163;','&#164;','&#165;','&#166;','&#167;','&#168;','&#169;','&#170;','&#171;','&#172;','&#173;','&#174;','&#175;','&#176;','&#177;','&#178;','&#179;','&#180;','&#181;','&#182;','&#183;','&#184;','&#185;','&#186;','&#187;','&#188;','&#189;','&#190;','&#191;','&#192;','&#193;','&#194;','&#195;','&#196;','&#197;','&#198;','&#199;','&#200;','&#201;','&#202;','&#203;','&#204;','&#205;','&#206;','&#207;','&#208;','&#209;','&#210;','&#211;','&#212;','&#213;','&#214;','&#215;','&#216;','&#217;','&#218;','&#219;','&#220;','&#221;','&#222;','&#223;','&#224;','&#225;','&#226;','&#227;','&#228;','&#229;','&#230;','&#231;','&#232;','&#233;','&#234;','&#235;','&#236;','&#237;','&#238;','&#239;','&#240;','&#241;','&#242;','&#243;','&#244;','&#245;','&#246;','&#247;','&#248;','&#249;','&#250;','&#251;','&#252;','&#253;','&#254;','&#255;','&#34;','&#38;','&#60;','&#62;','&#338;','&#339;','&#352;','&#353;','&#376;','&#710;','&#732;','&#8194;','&#8195;','&#8201;','&#8204;','&#8205;','&#8206;','&#8207;','&#8211;','&#8212;','&#8216;','&#8217;','&#8218;','&#8220;','&#8221;','&#8222;','&#8224;','&#8225;','&#8240;','&#8249;','&#8250;','&#8364;','&#402;','&#913;','&#914;','&#915;','&#916;','&#917;','&#918;','&#919;','&#920;','&#921;','&#922;','&#923;','&#924;','&#925;','&#926;','&#927;','&#928;','&#929;','&#931;','&#932;','&#933;','&#934;','&#935;','&#936;','&#937;','&#945;','&#946;','&#947;','&#948;','&#949;','&#950;','&#951;','&#952;','&#953;','&#954;','&#955;','&#956;','&#957;','&#958;','&#959;','&#960;','&#961;','&#962;','&#963;','&#964;','&#965;','&#966;','&#967;','&#968;','&#969;','&#977;','&#978;','&#982;','&#8226;','&#8230;','&#8242;','&#8243;','&#8254;','&#8260;','&#8472;','&#8465;','&#8476;','&#8482;','&#8501;','&#8592;','&#8593;','&#8594;','&#8595;','&#8596;','&#8629;','&#8656;','&#8657;','&#8658;','&#8659;','&#8660;','&#8704;','&#8706;','&#8707;','&#8709;','&#8711;','&#8712;','&#8713;','&#8715;','&#8719;','&#8721;','&#8722;','&#8727;','&#8730;','&#8733;','&#8734;','&#8736;','&#8743;','&#8744;','&#8745;','&#8746;','&#8747;','&#8756;','&#8764;','&#8773;','&#8776;','&#8800;','&#8801;','&#8804;','&#8805;','&#8834;','&#8835;','&#8836;','&#8838;','&#8839;','&#8853;','&#8855;','&#8869;','&#8901;','&#8968;','&#8969;','&#8970;','&#8971;','&#9001;','&#9002;','&#9674;','&#9824;','&#9827;','&#9829;','&#9830;');
		return this.swapArrayVals(s,arr1,arr2);
	},	

	// Convert Numerical entities into HTML entities
	NumericalToHTML : function(s){
		var arr1 = new Array('&#160;','&#161;','&#162;','&#163;','&#164;','&#165;','&#166;','&#167;','&#168;','&#169;','&#170;','&#171;','&#172;','&#173;','&#174;','&#175;','&#176;','&#177;','&#178;','&#179;','&#180;','&#181;','&#182;','&#183;','&#184;','&#185;','&#186;','&#187;','&#188;','&#189;','&#190;','&#191;','&#192;','&#193;','&#194;','&#195;','&#196;','&#197;','&#198;','&#199;','&#200;','&#201;','&#202;','&#203;','&#204;','&#205;','&#206;','&#207;','&#208;','&#209;','&#210;','&#211;','&#212;','&#213;','&#214;','&#215;','&#216;','&#217;','&#218;','&#219;','&#220;','&#221;','&#222;','&#223;','&#224;','&#225;','&#226;','&#227;','&#228;','&#229;','&#230;','&#231;','&#232;','&#233;','&#234;','&#235;','&#236;','&#237;','&#238;','&#239;','&#240;','&#241;','&#242;','&#243;','&#244;','&#245;','&#246;','&#247;','&#248;','&#249;','&#250;','&#251;','&#252;','&#253;','&#254;','&#255;','&#34;','&#38;','&#60;','&#62;','&#338;','&#339;','&#352;','&#353;','&#376;','&#710;','&#732;','&#8194;','&#8195;','&#8201;','&#8204;','&#8205;','&#8206;','&#8207;','&#8211;','&#8212;','&#8216;','&#8217;','&#8218;','&#8220;','&#8221;','&#8222;','&#8224;','&#8225;','&#8240;','&#8249;','&#8250;','&#8364;','&#402;','&#913;','&#914;','&#915;','&#916;','&#917;','&#918;','&#919;','&#920;','&#921;','&#922;','&#923;','&#924;','&#925;','&#926;','&#927;','&#928;','&#929;','&#931;','&#932;','&#933;','&#934;','&#935;','&#936;','&#937;','&#945;','&#946;','&#947;','&#948;','&#949;','&#950;','&#951;','&#952;','&#953;','&#954;','&#955;','&#956;','&#957;','&#958;','&#959;','&#960;','&#961;','&#962;','&#963;','&#964;','&#965;','&#966;','&#967;','&#968;','&#969;','&#977;','&#978;','&#982;','&#8226;','&#8230;','&#8242;','&#8243;','&#8254;','&#8260;','&#8472;','&#8465;','&#8476;','&#8482;','&#8501;','&#8592;','&#8593;','&#8594;','&#8595;','&#8596;','&#8629;','&#8656;','&#8657;','&#8658;','&#8659;','&#8660;','&#8704;','&#8706;','&#8707;','&#8709;','&#8711;','&#8712;','&#8713;','&#8715;','&#8719;','&#8721;','&#8722;','&#8727;','&#8730;','&#8733;','&#8734;','&#8736;','&#8743;','&#8744;','&#8745;','&#8746;','&#8747;','&#8756;','&#8764;','&#8773;','&#8776;','&#8800;','&#8801;','&#8804;','&#8805;','&#8834;','&#8835;','&#8836;','&#8838;','&#8839;','&#8853;','&#8855;','&#8869;','&#8901;','&#8968;','&#8969;','&#8970;','&#8971;','&#9001;','&#9002;','&#9674;','&#9824;','&#9827;','&#9829;','&#9830;');
		var arr2 = new Array('&nbsp;','&iexcl;','&cent;','&pound;','&curren;','&yen;','&brvbar;','&sect;','&uml;','&copy;','&ordf;','&laquo;','&not;','&shy;','&reg;','&macr;','&deg;','&plusmn;','&sup2;','&sup3;','&acute;','&micro;','&para;','&middot;','&cedil;','&sup1;','&ordm;','&raquo;','&frac14;','&frac12;','&frac34;','&iquest;','&agrave;','&aacute;','&acirc;','&atilde;','&Auml;','&aring;','&aelig;','&ccedil;','&egrave;','&eacute;','&ecirc;','&euml;','&igrave;','&iacute;','&icirc;','&iuml;','&eth;','&ntilde;','&ograve;','&oacute;','&ocirc;','&otilde;','&Ouml;','&times;','&oslash;','&ugrave;','&uacute;','&ucirc;','&Uuml;','&yacute;','&thorn;','&szlig;','&agrave;','&aacute;','&acirc;','&atilde;','&auml;','&aring;','&aelig;','&ccedil;','&egrave;','&eacute;','&ecirc;','&euml;','&igrave;','&iacute;','&icirc;','&iuml;','&eth;','&ntilde;','&ograve;','&oacute;','&ocirc;','&otilde;','&ouml;','&divide;','&Oslash;','&ugrave;','&uacute;','&ucirc;','&uuml;','&yacute;','&thorn;','&yuml;','&quot;','&amp;','&lt;','&gt;','&oelig;','&oelig;','&scaron;','&scaron;','&yuml;','&circ;','&tilde;','&ensp;','&emsp;','&thinsp;','&zwnj;','&zwj;','&lrm;','&rlm;','&ndash;','&mdash;','&lsquo;','&rsquo;','&sbquo;','&ldquo;','&rdquo;','&bdquo;','&dagger;','&dagger;','&permil;','&lsaquo;','&rsaquo;','&euro;','&fnof;','&alpha;','&beta;','&gamma;','&delta;','&epsilon;','&zeta;','&eta;','&theta;','&iota;','&kappa;','&lambda;','&mu;','&nu;','&xi;','&omicron;','&pi;','&rho;','&sigma;','&tau;','&upsilon;','&phi;','&chi;','&psi;','&omega;','&alpha;','&beta;','&gamma;','&delta;','&epsilon;','&zeta;','&eta;','&theta;','&iota;','&kappa;','&lambda;','&mu;','&nu;','&xi;','&omicron;','&pi;','&rho;','&sigmaf;','&sigma;','&tau;','&upsilon;','&phi;','&chi;','&psi;','&omega;','&thetasym;','&upsih;','&piv;','&bull;','&hellip;','&prime;','&prime;','&oline;','&frasl;','&weierp;','&image;','&real;','&trade;','&alefsym;','&larr;','&uarr;','&rarr;','&darr;','&harr;','&crarr;','&larr;','&uarr;','&rarr;','&darr;','&harr;','&forall;','&part;','&exist;','&empty;','&nabla;','&isin;','&notin;','&ni;','&prod;','&sum;','&minus;','&lowast;','&radic;','&prop;','&infin;','&ang;','&and;','&or;','&cap;','&cup;','&int;','&there4;','&sim;','&cong;','&asymp;','&ne;','&equiv;','&le;','&ge;','&sub;','&sup;','&nsub;','&sube;','&supe;','&oplus;','&otimes;','&perp;','&sdot;','&lceil;','&rceil;','&lfloor;','&rfloor;','&lang;','&rang;','&loz;','&spades;','&clubs;','&hearts;','&diams;');
		return this.swapArrayVals(s,arr1,arr2);
	},


	// Numerically encodes all unicode characters
	numEncode : function(s){
		
		if(this.isEmpty(s)) return "";

		var e = "";
		for (var i = 0; i < s.length; i++)
		{
			var c = s.charAt(i);
			if (c < " " || c > "~")
			{
				c = "&#" + c.charCodeAt() + ";";
			}
			e += c;
		}
		return e;
	},
	
	// HTML Decode numerical and HTML entities back to original values
	htmlDecode : function(s){

		var c,m,d = s;
		
		if(this.isEmpty(d)) return "";

		// convert HTML entites back to numerical entites first
		d = this.HTML2Numerical(d);
		
		// look for numerical entities &#34;
		arr=d.match(/&#[0-9]{1,5};/g);
		
		// if no matches found in string then skip
		if(arr!=null){
			for(var x=0;x<arr.length;x++){
				m = arr[x];
				c = m.substring(2,m.length-1); //get numeric part which is refernce to unicode character
				// if its a valid number we can decode
				if(c >= -32768 && c <= 65535){
					// decode every single match within string
					d = d.replace(m, String.fromCharCode(c));
				}else{
					d = d.replace(m, ""); //invalid so replace with nada
				}
			}			
		}

		return d;
	},		

	// encode an input string into either numerical or HTML entities
	htmlEncode : function(s,dbl){
			
		if(this.isEmpty(s)) return "";

		// do we allow double encoding? E.g will &amp; be turned into &amp;amp;
		dbl = dbl | false; //default to prevent double encoding
		
		// if allowing double encoding we do ampersands first
		if(dbl){
			if(this.EncodeType=="numerical"){
				s = s.replace(/&/g, "&#38;");
			}else{
				s = s.replace(/&/g, "&amp;");
			}
		}

		// convert the xss chars to numerical entities ' " < >
		s = this.XSSEncode(s,false);
		
		if(this.EncodeType=="numerical" || !dbl){
			// Now call function that will convert any HTML entities to numerical codes
			s = this.HTML2Numerical(s);
		}

		// Now encode all chars above 127 e.g unicode
		s = this.numEncode(s);

		// now we know anything that needs to be encoded has been converted to numerical entities we
		// can encode any ampersands & that are not part of encoded entities
		// to handle the fact that I need to do a negative check and handle multiple ampersands &&&
		// I am going to use a placeholder

		// if we don't want double encoded entities we ignore the & in existing entities
		if(!dbl){
			s = s.replace(/&#/g,"##AMPHASH##");
		
			if(this.EncodeType=="numerical"){
				s = s.replace(/&/g, "&#38;");
			}else{
				s = s.replace(/&/g, "&amp;");
			}

			s = s.replace(/##AMPHASH##/g,"&#");
		}
		
		// replace any malformed entities
		s = s.replace(/&#\d*([^\d;]|$)/g, "$1");

		if(!dbl){
			// safety check to correct any double encoded &amp;
			s = this.correctEncoding(s);
		}

		// now do we need to convert our numerical encoded string into entities
		if(this.EncodeType=="entity"){
			s = this.NumericalToHTML(s);
		}

		return s;					
	},

	// Encodes the basic 4 characters used to malform HTML in XSS hacks
	XSSEncode : function(s,en){
		if(!this.isEmpty(s)){
			en = en || true;
			// do we convert to numerical or html entity?
			if(en){
				s = s.replace(/\'/g,"&#39;"); //no HTML equivalent as &apos is not cross browser supported
				s = s.replace(/\"/g,"&quot;");
				s = s.replace(/</g,"&lt;");
				s = s.replace(/>/g,"&gt;");
			}else{
				s = s.replace(/\'/g,"&#39;"); //no HTML equivalent as &apos is not cross browser supported
				s = s.replace(/\"/g,"&#34;");
				s = s.replace(/</g,"&#60;");
				s = s.replace(/>/g,"&#62;");
			}
			return s;
		}else{
			return "";
		}
	},

	// returns true if a string contains html or numerical encoded entities
	hasEncoded : function(s){
		if(/&#[0-9]{1,5};/g.test(s)){
			return true;
		}else if(/&[A-Z]{2,6};/gi.test(s)){
			return true;
		}else{
			return false;
		}
	},

	// will remove any unicode characters
	stripUnicode : function(s){
		return s.replace(/[^\x20-\x7E]/g,"");
		
	},

	// corrects any double encoded &amp; entities e.g &amp;amp;
	correctEncoding : function(s){
		return s.replace(/(&amp;)(amp;)+/,"$1");
	},


	// Function to loop through an array swaping each item with the value from another array e.g swap HTML entities with Numericals
	swapArrayVals : function(s,arr1,arr2){
		if(this.isEmpty(s)) return "";
		var re;
		if(arr1 && arr2){
			//ShowDebug("in swapArrayVals arr1.length = " + arr1.length + " arr2.length = " + arr2.length)
			// array lengths must match
			if(arr1.length == arr2.length){
				for(var x=0,i=arr1.length;x<i;x++){
					re = new RegExp(arr1[x], 'g');
					s = s.replace(re,arr2[x]); //swap arr1 item with matching item from arr2	
				}
			}
		}
		return s;
	},

	inArray : function( item, arr ) {
		for ( var i = 0, x = arr.length; i < x; i++ ){
			if ( arr[i] === item ){
				return i;
			}
		}
		return -1;
	},
		// formato xeros a html
	parseSSiCCHtml : function(s){
	 //alert('hola '+s);
     s=inicializarTxt(s);//eliminando espacios en blanco
     
	 s= iniciarHtml(s);
	 //alert('text '+s);	 
	 s=bloqueVinetas(s);
	 //alert('text2 '+s);	 
	 //font size
	 s=bloqueFontSize(s); 
	 //alert('text3 '+s);	 
	 //bloques fijos
	 s= bloqueFijosHtml(s);	 
	 //alert('text4 '+s);	 
	 s= bloqueNoFijosHtml(s);  	 	 
	 //alert('text5 '+s);	 
	 s= replaceInitHtml(s);
	 //alert('text6 '+s);	 
	 s=replaceImgHtml(s);
	 //alert('S: '+s);	 
	 return s;
	},
		// formato html a xerox
	parseSSiCCTxt : function(s){
	 
	 s=inicializarDiv(s);//eliminando espacios en blanco
	 //alert('1: '+s);
	 
	 s= iniciarTxt(s);
	 //alert('2: '+s);	 	 	 
	 
	 s= replaceInitTxt(s);
	 //alert('3: '+s);
	 
	 s= replaceBloqueTxt(s);
	 //trabajamos imagenes
	 //alert('4: '+s);
	 
	 s=bloqueImagenes(s);	 
	 //alert('a:' + s);
	 
	 //alert('ai '+s);
	 //trabajamos las tablas y bx
	 s=bloqueTablas(s);
	 //alert('b:' + s);
	 
	 // alert('fin tabla '+s);
	 s= replaceFinTxt(s);	
	 //alert('c:' + s);
	 
	 //bloques fijos
	 s= bloqueFijos(s); 
	 //alert('d:' + s);
	 
	 s= finalizarTxt(s);
	 //alert('e:' + s);
	 
	 s=elminarFontDefault(s);
	 //alert('f:' + s);
	 
	 s=eliminarReferencias(s);
	 //alert('g:' + s);
	 
	 s=eliminarBxMultiples(s);
	 //alert('h:' + s);
	 
	 //alert(s);
	 return s;
  }
}

/************************************************************************************
INICO PARSER HTML A XEROX
************************************************************************************/
function bloqueFijos(s){
	//alert('bloq fijo '+s);
	var pos =0;
	conFijos=0;
	conVar =0;

	 s=s.replace(/(<txt><txt\/><\/txt>)/gi,"<txt/>");	 
	 //s=s.replace(/(<txt\/><txt><font color=blue>/i,"<fijo><txt/><txt>");	 
	 //s=s.replace(/(<txt\/><txt><font color=red>/i,"<txt/></fijo><txt>");	 
	 //s=s.replace("<txt><font color=#000ff><\/fh08>&nbsp;<\/txt>)","<txt/><fijo>");	 
	 //s=s.replace(/(<txt\/><txt><font color=blue>/i,"<fijo><txt/><txt>");	 

	 encontrado = true;
	 hayfijo=false;
	 flagCierre = true;
	 while(encontrado){
	 	  off1= s.indexOf('<font color="#0000ff">');
	    off2= s.indexOf('<font color="#ff0000">');
	    //alert('off1 '+off1 + ' off2 '+off2 + ' conFijos '+conFijos + ' conVar '+conVar);
	    if(off1==-1 && off2==-1){
	    	encontrado= false;
	    	if(hayfijo){
	    		  offinicio = s.lastIndexOf("<fijo>");					    		
						offfin = s.lastIndexOf("</fijo>");					    		
						if(offfin!= -1 && offfin < offinicio){
							s+="</fijo><fijo><txt/></fijo>";
						}
						if(offfin!= -1 && offfin > offinicio){
							s+="<fijo><txt/></fijo>";							
						}
	    	}
	    	break;
	    }  
	 	
	 		if( conFijos == 0 && off1!=-1){
	 			 sublinea1 = s.substring(0,off1);	 			 
	 			 //alert(sublinea2)
	 			 //obtener el ultimo <txt> de la sublinea
	 			 offt = sublinea1.lastIndexOf("<txt>"); 			 			 				  
	 			 sublinea2 = s.substring(offt);
				 s=sublinea1.substring(0,offt)+ "<fijo>"+sublinea2;
	 	     s=s.replace('<font color="#0000ff">',"");		 
		
	 			 //alert('s '+s)
	 			 conFijos++;
	 			 conVar=0;
	 			 //alert(s);
	 			 hayfijo=true;
	 			 continue;
	 		}
	 		
	 		if( conFijos > 0 && off1!=-1){	
	 			  //alert(off1 + ' ' +  off2); 			
	 			 if((off1 < off2) || (off2==-1)){
	 			 	
	 			 	 if(off2==-1 && flagCierre){
	 			 	 	 flagCierre=false;
	 			 	 	 conFijos=0;	 			 	 	 	 			 	 	 
	 			 	 }
	 			 	 else{
	 			 	 	 
	 			     s=s.replace('<font color="#0000ff">',"");	 
	 			     conFijos++;
	 			   }
	 			  
	 			}else{	 					 				
	 				
		 				if(conVar == 0){
					 			 
					 			  sublinea1 = s.substring(0,off2);
	 			 				  
	 			 				  //obtener el ultimo <txt> de la sublinea
	 			 				  offt = sublinea1.lastIndexOf("<txt>");	 			 				  	 			 				  
	 			 				  sublinea2 = s.substring(offt);
		 		 					s=sublinea1.substring(0,offt)+ "</fijo>"+sublinea2;
		 				 	 		s=s.replace('<font color="#ff0000">',"");	 		 	 					 				  
		 				 		//conFijos=0;	
		 				}else{
		 					s=s.replace('<font color="#ff0000">',"");	 
		 					pos1= s.indexOf('<font color="#ff0000">');
		 					pos2= s.indexOf('<font color="#0000ff">');
		 					if( pos1!=-1 && pos2 !=-1 ){
		 					  if(pos1 > pos2 ){
		 					    conFijos=0;	
		 					  }
		 					}		 							 					
		 				}
		 				conVar++;
	 			  	 					 				 
	 			}
	 			//alert(s);
	 			hayfijo=true;
	 			continue;
	 		}	
	 		
	 		if(off1 ==-1){	 				 			
	 				if(conVar == 0){
	 				  if(off2!=-1){
	 				  			sublinea1 = s.substring(0,off2);
 									//obtener el ultimo <txt> de la sublinea
	 			 				  offt = sublinea1.lastIndexOf("<txt>");	 			 				  	 			 				  
	 			 				  sublinea2 = s.substring(offt);
		 		 					s=sublinea1.substring(0,offt)+ "</fijo>"+sublinea2;
		 				 	 		s=s.replace('<font color="#ff0000">',"");	 		
		 				 	 		s=s.replace("</fijo></fijo>","</fijo>");	 		
	 				  }
	 				}else{
	 					s=s.replace('<font color="#ff0000">',"");	 
	 				}
	 			conVar++;
	 			conFijos =0;  
	 			hayfijo=true;
	 			continue;
	 		}	 		
	 		
	 		
	 }
	 s=s.replace("<fijo><txt/></fijo><fijo><txt/></fijo>","<fijo><txt/></fijo>");
	 //alert('fin bloque fijo '+s);
	 s=s.replace("<txt/></fijo><fijo><txt/></fijo>","<txt/></fijo>");
	 return s;
}

function iniciarTxt(s){	 
	 var offset = s.indexOf("<div>");
	 //alert('offset '+offset);
	 //primer bloque sin div
	 if(offset == -1 ){
	 	 s="<txt>"+s+"</txt>";
	 }
	 if(offset > 0){
	 	 cad=s.substring(0,offset);
	 	 s="<div>"+cad+"</div>" + s.substring(offset); 
	 	 //s="<div>"+cad+"</div>" + s.substring(offset+5); 
	 	 //s="<div>"+s;	 	  
	 }	 
	 //alert('0 '+s);	 
	 return s;
}

function replaceInitTxt(s){
	 s=s.replace(/(<font size="2" color="#0000ff">)/gi,'<font color="#0000ff"><font size="2">');
	 s=s.replace(/(<font size="3" color="#0000ff">)/gi,'<font color="#0000ff"><font size="3">');
	 s=s.replace(/(<font size="4" color="#0000ff">)/gi,'<font color="#0000ff"><font size="4">');
	 s=s.replace(/(<font size="5" color="#0000ff">)/gi,'<font color="#0000ff"><font size="5">');
	 s=s.replace(/(<font size="6" color="#0000ff">)/gi,'<font color="#0000ff"><font size="6">');
	 s=s.replace(/(<font size="7" color="#0000ff">)/gi,'<font color="#0000ff"><font size="7">');
	 s=s.replace(/(<font size="+0" color="#0000ff">)/gi,"");
	 s=s.replace(/(<font size="2" color="#ff0000">)/gi,'<font color="#ff0000"><font size="2">');
	 s=s.replace(/(<font size="3" color="#ff0000">)/gi,'<font color="#ff0000"><font size="3">');
	 s=s.replace(/(<font size="4" color="#ff0000">)/gi,'<font color="#ff0000"><font size="4">');
	 s=s.replace(/(<font size="5" color="#ff0000">)/gi,'<font color="#ff0000"><font size="5">');
	 s=s.replace(/(<font size="6" color="#ff0000">)/gi,'<font color="#ff0000"><font size="6">');
	 s=s.replace(/(<font size="7" color="#ff0000">)/gi,'<font color="#ff0000"><font size="7">');
	 s=s.replace(/(<font size="+0"  color="#ff0000" size=+0>)/gi,"");
     s=s.replace(/(<font size="2"><\/font>)/gi,"");	 
	 s=s.replace(/(<font size="3"><\/font>)/gi,"");	 
	 s=s.replace(/(<font size="4"><\/font>)/gi,"");	 
	 s=s.replace(/(<font size="5"><\/font>)/gi,"");	 
	 s=s.replace(/(<font size="6"><\/font>)/gi,"");	 
	 s=s.replace(/(<font size="7"><\/font>)/gi,"");
	 s=s.replace(/(<font color="#0000ff"><\/font>)/gi,"");	 	
	 s=s.replace(/(<font color="#ff0000"><\/font>)/gi,"");	 	
	 s=s.replace(/(<font color=#0000ff><\/font>)/gi,"");	 	
	 s=s.replace(/(<font color="#0000ff">)/gi,'<font color="#0000ff">');	 	  
	 s=s.replace(/(<em><\/em>)/gi,"");
	 s=s.replace(/(<em>)/gi,"");
	 s=s.replace(/(<\/em>)/gi,"");
	 s=s.replace(/(<u><\/u>)/gi,"");
	 s=s.replace(/(<u>)/gi,"<u>");
	 s=s.replace(/(<\/u>)/gi,"</u>");
	 s=s.replace(/(<ul>)/gi,"");
	 s=s.replace(/(<\/ul>)/gi,"");
	 s=s.replace(/(<li>)/gi,"<txt><v/>");
	 s=s.replace(/(<\/li>)/gi,"</txt>");
	 s=s.replace(/(<li><\/li>)/gi,"");
	 //s=s.replace(/(<div><\/div>)/gi,"");
	 //s=s.replace(/(<div>&nbsp;<\/div>)/gi,"<txt/>");
	 s=s.replace(/(<strong>)/gi,"<b>");
	 s=s.replace(/(<\/strong>)/gi,"</b>");
	 s=s.replace(/(<strong>)/gi,"<b>");
	 s=s.replace(/(<\/strong>)/gi,"</b>");	
	 s=s.replace(/(<strong><\/strong>)/gi,"");
	 //s=s.replace(/(<\/font><\/div>)/gi,"</div>");		 
	 s=s.replace(/(<\/div><\/font><\/font>)/gi,"</div>");		
	 s=s.replace(/(<\/div><\/font>)/gi,"</div>");	
	 s=s.replace(/(<\/font><\/font>)/gi,"");	
	 s=s.replace(/(<\/font><font color="#ff0000">)/gi,"");	 	
	 //s=s.replace(/(<\/font><\/td>)/gi,"</td>");	  
	 s=s.replace(/(<font size="2">)/gi,"<fhe07/>");	 
	 s=s.replace(/(<font size="3">)/gi,"<fhe08/>");	 
	 s=s.replace(/(<font size="4">)/gi,"<fhe09/>");	 
	 s=s.replace(/(<font size="5">)/gi,"<fhe10/>");	 
	 s=s.replace(/(<font size="6">)/gi,"<fhe11/>");	 
	 s=s.replace(/(<font size="7">)/gi,"<fhe12/>");	 
	 s=s.replace(/(<\/font><\/font>)/gi,"<fhe08/>");	 
	 
	 //alert('3 '+s);	 
	 s=s.replace(/(<\/font>)/gi,"<fhe08/>");	 
	 //s=s.replace(/(<\/font>)/gi,"");	 
	 //alert('4 '+s);
	 
	return s;
}

function replaceFinTxt(s){
	 s=s.replace(/(<div>)/gi,"<txt>");
	 s=s.replace(/(<\/div>)/gi,"</txt>");
	 s=s.replace(/(<br>)/gi,"");
	return s;
}

function replaceBloqueTxt(s){	 
	 var offset = s.lastIndexOf("</div>");
	 var offsetUlt = s.length; 
	 
	 if(offset == -1 )return s;
	 if(offset + 6 < offsetUlt ){
	 	 cadena = s.substring(offset+6);
	 	 offi = s.lastIndexOf("</div>");
	 	 s = s.substring(0,offi)+ cadena + "</div>";	 	 
	 }
	 
	 exista=true;
	 patron='<div><font color="#0000ff">&nbsp;</div>';
	 while(exista){	 	 
     index = s.indexOf(patron);
     if(index == -1) break;	 	
	 	 s=s.replace(patron,"<fijo><txt/></fijo>");
	 }
	 
	 s=s.replace(/(<div><\/div>)/gi,"");
	 s=s.replace(/(<div>&nbsp;<\/div>)/gi,"<txt/>");	 
	 s=s.replace(/(<div><br><\/div>)/gi,"<txt/>");	
	 s=s.replace(/(&nbsp;)/gi," ");
	 //alert('1 '+s);	 
	 return s;
}

function finalizarTxt(s){
	res="";
	for(i=0;i<s.length;i++){
		//alert('cadena '+s.charCodeAt(i) + "  ss "+ s.charAt(i))
		if(s.charCodeAt(i) != 10 && s.charCodeAt(i) != 13){
			res+=""+s.charAt(i); 
		}				  
	}
	res=res.replace(/(<txt><txt>)/gi,"<txt>");
	res=res.replace(/(<\/txt><\/txt>)/gi,"</txt>");
	
	res = res.replace("<txt/><fijo><txt/></fijo>","<fijo><txt/></fijo>");
    res = res.replace(/(&lt;)/gi,"<");
	res = res.replace(/(&gt;)/gi,">");
	res = res.replace(/(<u><\/u>)/gi,"");
	res = res.replace(/(<b><\/b>)/gi,"");	
	res= res.replace(/(<txt><\/txt>)/gi,"");
	res= res.replace(/(<p>&nbsp;<\/p>)/gi,"");
	res= res.replace(/(<p>)/gi,"");
	res= res.replace(/(<\/p>)/gi,"");
	res= res.replace(/(&nbsp;)/gi,"");
	res= res.replace(/(<bx><\/fijo>)/gi,"</fijo><bx>");
	res=res.replace(/(<\/font>)/gi,"<fhe08/>");	 
	res=res.replace(/(<v\/><\/txt><t\/>)/gi,"<v/><t/>");	 
	res=res.replace(/(<v\/><\/txt><tc\/>)/gi,"<v/><tc/>");	 
	res=res.replace(/(<v\/><\/txt><tr\/>)/gi,"<v/><tr/>");	 
	res=res.replace(/(<txt><\/txt>)/gi,"");
	
	if(res.indexOf("<txt/><fijo>") == 0 ){
		res = res.replace(/(<txt\/><fijo>)/i,"<fijo><txt/></fijo><fijo>");
	}
	return res;
}

function inicializarDiv(s){
	res="";
	for(i=0;i<s.length;i++){
		//alert('cadena '+s.charCodeAt(i) + "  ss "+ s.charAt(i))
		if(s.charCodeAt(i) != 10 && s.charCodeAt(i) != 13 ){
			res+=""+s.charAt(i); 
		}				  
	}
	res= res.replace(/(<div style="text-align: center;">)/gi,"<div><tc/>");		 		
	res= res.replace(/(<div style="text-align: left;">)/gi,"<div><t/>");		 		 
	res= res.replace(/(<div style="text-align: right;">)/gi,"<div><tr/>");		
	res=res.replace(/(<p>)/gi,"<txt>");	
	res=res.replace(/(<\/p><\/font>)/gi,"</txt>");
	res=res.replace(/(<\/p><\/font><\/font>)/gi,"</txt>");
	res=res.replace(/(<\/p><\/font><\/font><\/font>)/gi,"</txt>");
	res=res.replace(/(<\/p>)/gi,"</txt>");
	res=res.replace(/(<div><div>)/gi,"<div>");
	res=res.replace(/(<\/div><\/div>)/gi,"</div>");
	res=res.replace(/(<\/txt><\/td>)/gi,"</td>");	
	//alert('xx '+res);
	return res;
}


function formatear(s){	 
	 s=s.replace(/(<\/div><\/font>)/gi,"</div>");
	 s=s.replace(/(<\/div><\/font><\/font>)/gi,"</div>");	
	 var offset = s.lastIndexOf("</div>");
	 var offsetUlt = s.length; 
	 
	 if(offset == -1 ){
	 	  //alert('formatear '+ s)
	 	  if(s.length >0 && s != '' ){
	 	  	cadena = "<div>"+s+"</div>";	 	  	
	 	  	return cadena;
	 	  }	 	  
	 	  return s;
	 }
	 if(offset + 6 < offsetUlt ){
	 	  //alert('formatearxx '+ s)
	 	 cadena = s.substring(offset+6);
	 	 offi = s.lastIndexOf("<div>");
	 	 s = s.substring(0,offi);
	 	 s+="<div>"+cadena+"</div>"
	 }
	 
	 //alert('foemateado '+s);
	 return s;
}

function bloqueImagenes(s){
	var encontrado= true;
	var line1="<txt><img>" ;//"<fijo><txt/></fijo><txt><img>";
	var line2="</img></txt>";//</img</txt><fijo><txt/></fijo>";
	var line3="<img>";
	var line4="</img>";	
	var aux=s;
	var tipo=1;
	//alert(s);
	//alert("ini bloqueImagenes s:" + s);
	while(encontrado){
		offseti= s.indexOf("<img ");
		if(offseti == -1) {
	 	  	encontrado = false;		 	  	
	  	}
		if (encontrado) {
			lineaImg =  s.substring(offseti);
			offsetf = lineaImg.indexOf("</img>");
			if(offsetf == -1){
				offsetf = lineaImg.indexOf('">');
			}
			lineaImg =  lineaImg.substring(0, offsetf);
			//alert('2lineaImg: ' + lineaImg);
			
			offsetsrc = lineaImg.indexOf("src");
			valorImg =  lineaImg.substring(offsetsrc + 5, offsetf );
			
			offsetimg = valorImg.lastIndexOf("/");
			valorImg = valorImg.substring(offsetimg + 1);
						
			lineaImg = line1 + valorImg + line2;
			
			s = s.substring(0, offseti) + lineaImg + s.substring(offseti + offsetf + 2);
			
		}
	}	
	
	
	//alert("fin bloqueImagenes");
	return s;
	
}

function bloqueTablas(s){
	s = s.replace(/(<br>)/gi,"");
	var encontrado= true;
	var line1="";
	var line2="";
	var line3="";
	var line4="";	
	var aux=s;
	var tipo=1;//SIN BX
	while(encontrado){
		//alert('tabla '+s);
	   offset1= s.indexOf("<div><table");
	   //alert(offset1);
		 if(offset1 == -1){		 	
		 	  offset1=  s.indexOf("<table");		 	  
		 	  if(offset1 == -1){
		 	  	encontrado=false;		 	  	
		 	  	return s;
		 	  }
		 	encontrado=false;
		 	return s;
		 }
		 
		 part =  s.substring(offset1);
		 //alert(part);
		 //alert(part.indexOf("</table></div>"));
         offsettf= part.indexOf("</table></div>") + offset1;		
		 
		 //alert('off1 '+ offset1 + 'offsettf ' +offsettf)		 
		 txt = s.substring(offset1,offsettf+14);//
		 //alert('primer bloque '+txt);
		 //
		 offset2 = txt.indexOf("<tbody>");
		 part2= txt.substring(offset2);
		 offset3= part2.indexOf("</tbody>") + offset2;		
		 txt2 =   txt.substring(offset2+7,offset3);//
		 //
		 //alert('segundo bloque '+txt2);
		 
		 txt2 = txt2.replace(/(<tr>)/gi,"<txt>");
		 txt2 = txt2.replace(/(<\/tr>)/gi,"</txt>");
		 txt2 = txt2.replace(/(<td>)/gi,"<t/>");
		 txt2 = txt2.replace(/(<\/td>)/gi,"");
		 txt2 = txt2.replace(/(&lt;)/gi,"<"),
		 txt2 = txt2.replace(/(&gt;)/gi,">");
		 txt2 = txt2.replace(/<\/p>/gi,"");
  	     txt2= txt2.replace(/(<txt><t\/>)/gi,"<txt>");						 
		 //alert('line 1 '+txt2);
		 //se debe recorrer por cada alineacion
		 txt2= txt2.replace(/(<t\/><p style="text-align: center;">)/gi,"<tc/>");		 		
		 txt2= txt2.replace(/(<t\/><p style="text-align: left;">)/gi,"<t/>");		 		 
		 txt2= txt2.replace(/(<t\/><p style="text-align: right;">)/gi,"<tr/>");		 		 
		 txt2= txt2.replace(/(<p style="text-align:right;">)/gi,"");		 		  
     txt2= txt2.replace(/(<p style="text-align: left;">)/gi,"");		 		 
     txt2= txt2.replace(/(<p style="text-align: center;">)/gi,"");		 		 
     txt2= txt2.replace(/(<txt><tc\/>)/gi,"<txt>");
		 txt2= txt2.replace(/(<txt><tr\/>)/gi,"<txt>");		  
		 //alert('line 2 '+txt2);
    
     hayBx=true;
     while(hayBx){ 
     	  //alert(txt2);
		  indbx = txt2.indexOf("<tr style");
		  if(indbx!=-1){		   
		   offbx1  = txt2.indexOf("</txt>");
		   txt2 = txt2.substring(0,indbx) + "<txt><bx>"+ txt2.substring(indbx+38,offbx1)+"</bx></txtbx>"+txt2.substring(offbx1+6);
		   
		 	 //txt2= txt2.replace(/(<tr style="BACKGROUND-COLOR: #cccccc">)/i,"<txt><bx>");
		 	 //txt2= txt2.replace(/(<\/txt>)/i,"</bx></txt>");
		  }else{
		  	hayBx=false;
		  	break;
		  }
		 }
		 txt2= txt2.replace(/(<\/txtbx>)/gi,"</txt>");
		 txt2= txt2.replace(/(<bx><t\/>)/gi,"<bx>");
		 txt2= txt2.replace(/(<bx><tc\/>)/gi,"<bx>");
		 txt2= txt2.replace(/(<bx><tr\/>)/gi,"<bx>");
		 txt2= txt2.replace(/(<txt><bx>)/gi,"<bx><txt>");
		 txt2= txt2.replace(/(<\/bx><\/txt>)/gi,"</txt></bx>");
		 
		// alert('line 4 '+txt2);
		 		 
		 s = s.substring(0,offset1) + txt2 +  s.substring(offsettf+14);
		 
	} 
	return s;
}
/**************************************************************************************
FIN PARSER HTML A XEROX
***************************************************************************************/
/************************************************************************************
INICIO PARSER XEROX A HTML
************************************************************************************/

function inicializarTxt(s){
	res="";

	for(i=0;i<s.length;i++){
		//alert('cadena '+s.charCodeAt(i) + "  ss "+ s.charAt(i))
		if(s.charCodeAt(i) != 10 && s.charCodeAt(i) != 13 ){
			res+=""+s.charAt(i); 
		}				  
	}
	//buscamos si hay fhe para eliminarlo si esta alfina
	index1= res.lastIndexOf("<fhe");
	index2= res.lastIndexOf("</txt>");
	tam = res.length;
	//alert('tam '+tam + ' index1 '+index1)
	if(index1 != -1 && (index1+8 == tam) && index2 < index1){
		res= res.substring(0,index1);
	}
	return res;
}

function iniciarHtml(s){
	 s=s.replace(/(<DATO01>)/gi,"&lt;DATO01&gt;");
	 s=s.replace(/(<DATO02>)/gi,"&lt;DATO02&gt;");
	 s=s.replace(/(<DATO03>)/gi,"&lt;DATO03&gt;");
	 s=s.replace(/(<DATO04>)/gi,"&lt;DATO04&gt;");
	 s=s.replace(/(<DATO05>)/gi,"&lt;DATO05&gt;");
	 s=s.replace(/(<DATO06>)/gi,"&lt;DATO06&gt;");
	 s=s.replace(/(<DATO07>)/gi,"&lt;DATO07&gt;");
	 s=s.replace(/(<DATO08>)/gi,"&lt;DATO08&gt;");
	 s=s.replace(/(<DATO09>)/gi,"&lt;DATO09&gt;");
	 s=s.replace(/(<DATO10>)/gi,"&lt;DATO10&gt;");
	 s=s.replace(/(<DATO11>)/gi,"&lt;DATO11&gt;");
	 s=s.replace(/(<DATO12>)/gi,"&lt;DATO12&gt;");
	 s=s.replace(/(<DATO13>)/gi,"&lt;DATO13&gt;");
	 s=s.replace(/(<DATO14>)/gi,"&lt;DATO14&gt;");
	 s=s.replace(/(<DATO15>)/gi,"&lt;DATO15&gt;");
	 s=s.replace(/(<DATO16>)/gi,"&lt;DATO16&gt;");
	 s=s.replace(/(<DATO17>)/gi,"&lt;DATO17&gt;");
	 s=s.replace(/(<DATO18>)/gi,"&lt;DATO18&gt;");
	 s=s.replace(/(<DATO19>)/gi,"&lt;DATO19&gt;");
	 s=s.replace(/(<DATO20>)/gi,"&lt;DATO20&gt;");
	 s= s.replace(/(<bx><txt\/><txt>)/gi,"<txt><bx>");
	 s= s.replace(/(<bx><txt\/><txt>)/gi,"<txt><bx>");
	 s= s.replace(/(<bx><txt\/>)/gi,"<txt/><bx>");
	 s= s.replace(/(<bx><txt>)/gi,"<txt><bx>");
	 s= s.replace(/(<\/txt><\/bx>)/gi,"</bx></txt>");
	 
	 s= s.replace(/(<fhe07\/><bx>)/gi,"<bx><fhe07/>");
	 s= s.replace(/(<fhe08\/><bx>)/gi,"<bx><fhe08/>");
	 s= s.replace(/(<fhe09\/><bx>)/gi,"<bx><fhe09/>");
	 s= s.replace(/(<fhe10\/><bx>)/gi,"<bx><fhe10/>");
	 s= s.replace(/(<fhe11\/><bx>)/gi,"<bx><fhe11/>");
	 s= s.replace(/(<fhe12\/><bx>)/gi,"<bx><fhe12/>");
	 
	 s= s.replace(/(<fhe07\/><txt>)/gi,"<txt><fhe07/>");
	 s= s.replace(/(<fhe08\/><txt>)/gi,"<txt><fhe08/>");
	 s= s.replace(/(<fhe09\/><txt>)/gi,"<txt><fhe09/>");
	 s= s.replace(/(<fhe10\/><txt>)/gi,"<txt><fhe10/>");
	 s= s.replace(/(<fhe11\/><txt>)/gi,"<txt><fhe11/>");
	 s= s.replace(/(<fhe12\/><txt>)/gi,"<txt><fhe12/>");
	 
	 
	/* s=s.replace(/(<fhe07\/>)/gi,"<font size=2>");	
	 s=s.replace(/(<fhe08\/>)/gi,"<font size=3>");	  
	 s=s.replace(/(<fhe09\/>)/gi,"<font size=4>");	 
	 s=s.replace(/(<fhe10\/>)/gi,"<font size=5>");	 
	 s=s.replace(/(<fhe11\/>)/gi,"<font size=6>");	
	 s=s.replace(/(<fhe12\/>)/gi,"<font size=7>");*/	  
		 
	 exista=true;
	 patron="<fijo><txt/></fijo>"
	 while(exista){	 	 
     index = s.indexOf(patron);
     if(index == -1) break;	 	
	 	 s=s.replace(patron,'<div><font color="#0000ff">&nbsp;</div>');
	 }	 
	 
	 return s;
}


function replaceInitHtml(s){
	 s=s.replace(/(<b>)/gi,"<strong>");
	 s=s.replace(/(<\/b>)/gi,"</strong>");
	 s=s.replace(/(<u>)/gi,"<u>");
	 s=s.replace(/(<\/u>)/gi,"</u>");	 
	 s=s.replace(/(<txt\/>)/gi,"<div>&nbsp;</div>");	 
	 s=s.replace(/(<txt>)/gi,"<div>");	 
	 s=s.replace(/(<\/txt>)/gi,"</div>");	 	
	 s=s.replace(/(<fijo>)/gi,"");	 	 	 	 	 	
	 s=s.replace(/(<\/fijo>)/gi,"");	 	 	 	
	 //alert('1 '+s);	 
	 return s;
}

function bloqueFijosHtml(s){
	
	lineatabla='<div><table border="1"><tbody>';//linea por si hay tabla
	lineaf="</tbody></table></div>";
	//coltdFijo=<>
	//coltdSnFijo=
	var encontrado = true;
	haytabla = false;
	//aux = s.substring(0);
	//aux=s;

	tipoFijo=1;
	offf1=0;
	//solo bloques con la palabra fijo
	while(encontrado){
		//preguntamos si hay fijo
		//alert('encontrado '+encontrado);
		pos1=s.length+100;
		pos2=s.length+100;
		pos3=s.length+100;		
		
		offfijo = s.indexOf("<fijo>");
	
		if(offfijo == -1){
			encontrado =false;
			break;
		}
		//obteneo el cierre de fijo
		offfijof = s.indexOf("</fijo>");
		//preguntamos si hay tabla e inicamos en el primer tag
		offtabla1 = s.indexOf("<t/>");
		offtabla2 = s.indexOf("<tc/>");
		offtabla3 = s.indexOf("<tr/>");
		
	
	  haytabla=false;
		if((offtabla1 != -1) && offfijo < offtabla1){
				pos1 = offtabla1; 
		}
		if((offtabla2 != -1) && offfijo < offtabla2){
				pos2 = offtabla2; 
		}		
		if((offtabla3 != -1)&& offfijo < offtabla3){
				pos3=offtabla3; 
		}				
		encontradotd= true;
		//si el inicio es t/
		lineaTr1="";
		lineaVariable="";
		tabla="";
		//alert('pos1 '+pos1 + 'offfijof '+offfijof);
		if(pos1 < pos2 && pos1 < pos3 &&  pos1< offfijof){
			sublinea= s.substring(0,pos1);
			//obtenemos el ultimo <txt>
			off1 = sublinea.lastIndexOf("<txt>");
			//alert('off1 '+off1);
			lineaFija = s.substring(0,offfijof);			
			fontsize= getLastFontSize(lineaFija);
			if(off1 < offfijof && pos1 > offfijo ){//hay tr fijo					
					lineaTr=s.substring(off1,offfijof);//desde inicio de la tabla hasta qtermina la parte fija
					//alert('tr ' + lineaTr);
				  hayLinea=true;
				  while(hayLinea){
				   index = lineaTr.lastIndexOf("</txt>");	
				   if(index == -1) { hayLinea = false; break;}
				   lineaTr1+=processLine(lineaTr.substring(0,index+6),tipoFijo,fontsize);			
				   lineaTr= lineaTr.substring(index+6);
				  }
			} 
		  //alert('lineaTr1 '+lineaTr1);
			haytabla = true;			
			//se procesa las demas lineas
			//
			if(pos1 > offfijo)
			 lineaTr2=s.substring(offfijof+7);
			else{
				//el ultimo txt
			 off2 = s.indexOf("</txt>");
			 lineaTr2=s.substring(off1,off2+6);	
			}
			
			hayLinea=true;
			tipoVariable=2;
			while(hayLinea){			
					offsetv1=lineaTr2.indexOf("<txt>");
					offsetv2=lineaTr2.indexOf("</txt>");
					if(offsetv1 == -1){
						hayLinea = false; 
						break;
					}
					 //alert('v1 '+lineaTr2.substring(offsetv1,offsetv2+6));
					 if(!existeTabulado(lineaTr2.substring(offsetv1,offsetv2+6))) break;
					 lineaVariable+=processLine(lineaTr2.substring(offsetv1,offsetv2+6),tipoVariable,fontsize);			
					 lineaTr2= lineaTr2.substring(offsetv2+6);
					 //alert(lineaTr2)					  
					 //sigue existiendo tabla??
					 
			}
			//alert('linea1 '+lineatabla);
			 tabla =lineatabla + lineaTr1 + lineaVariable + lineaf;
		}
		//alert('tabla '+tabla);
		//alert('off1 '+off1);
		//alert('encontrado '+encontrado);
			//si el inicio es tc/
		  if(pos2 < pos1 && pos2 < pos3 &&  pos2< offfijof){
			  haytabla = true;
			  sublinea= s.substring(0,pos2);
				//obtenemos el ultimo <txt>
				off1 = sublinea.lastIndexOf("<txt>");
				
				lineaFija = s.substring(0,offfijof);						
			  fontsize= getLastFontSize(lineaFija);				
				if(off1 < offfijof && pos2 > offfijo){//hay tr fijo
					lineaTr=s.substring(off1,offfijof);//desde inicio de la tabla hasta qtermina la parte fija
					//alert('tc ' + lineaTr);
									
				  hayLinea=true;
				  while(hayLinea){
				   index = lineaTr.lastIndexOf("</txt>");	
				   if(index == -1) { hayLinea = false; break;}
				   lineaTr1+=processLine(lineaTr.substring(0,index+6),tipoFijo,fontsize);			
				   lineaTr= lineaTr.substring(index+6);
				  }
				  //alert('lineaTr1 '+lineaTr1);
				} 
				haytabla = true;			
				//se procesa las demas lineas dstas son variables
				//
				if(pos2 > offfijo)
					 lineaTr2=s.substring(offfijof+7);
					else{
						//el ultimo txt
					 off2 = s.indexOf("</txt>");
					 lineaTr2=s.substring(off1,off2+6);	
					}				
				
				
				hayLinea=true;
				tipoVariable=2;
				while(hayLinea){
				
					offsetv1=lineaTr2.indexOf("<txt>");
					offsetv2=lineaTr2.indexOf("</txt>");
					if(offsetv1 == -1){
						hayLinea = false; 
						break;
					}
					 //alert('v1 '+lineaTr2.substring(offsetv1,offsetv2+6));
					 if(!existeTabulado(lineaTr2.substring(offsetv1,offsetv2+6))) break;
					 lineaVariable+=processLine(lineaTr2.substring(offsetv1,offsetv2+6),tipoVariable,fontsize);			
					 lineaTr2= lineaTr2.substring(offsetv2+6);
					 
			  }
				 //alert('lineaVariable '+lineaVariable);
				 
				 tabla =lineatabla + lineaTr1 + lineaVariable + lineaf;				
			}
		  
		  //alert('tabla1 '+tabla);
			//alert('off1 '+off1);
			//si el inicio es tc/
		  if(pos3 < pos1 && pos3 < pos2 &&  pos3< offfijof){
				haytabla = true;		
			  sublinea= s.substring(0,pos3);
				//obtenemos el ultimo <txt>
				off1 = sublinea.lastIndexOf("<txt>");
				
				lineaFija = s.substring(0,offfijof);		
				fontsize= getLastFontSize(lineaFija);
				if(off1 < offfijof && pos3 > offfijo){//hay tr fijo	
						lineaTr=s.substring(off1,offfijof);//desde inicio de la tabla hasta qtermina la parte fija
						//alert('tr2 ' + lineaTr);				
					  
					  hayLinea=true;
					  while(hayLinea){
					   index = lineaTr.lastIndexOf("</txt>");	
					   if(index == -1) { hayLinea = false; break;}
					   lineaTr1+=processLine(lineaTr.substring(0,index+6),tipoFijo,fontsize);			
					   lineaTr= lineaTr.substring(index+6);
					  }
				}	  
			  //alert('lineaTr1 '+lineaTr1);
				haytabla = true;			
				//se procesa las demas lineas
				//
				
				if(pos3 > offfijo)
					 lineaTr2=s.substring(offfijof+7);
					else{
						//el ultimo txt
					 off2 = s.indexOf("</txt>");
					 lineaTr2=s.substring(off1,off2+6);	
					}								
				
				hayLinea=true;
				tipoVariable=2;
				
				while(hayLinea){				
					offsetv1=lineaTr2.indexOf("<txt>");
					offsetv2=lineaTr2.indexOf("</txt>");
					if(offsetv1 == -1){
						hayLinea = false; 
						break;
					}
				 //alert('v1 '+lineaTr2.substring(offsetv1,offsetv2+6));
				 if(!existeTabulado(lineaTr2.substring(offsetv1,offsetv2+6))) break;
				 lineaVariable+=processLine(lineaTr2.substring(offsetv1,offsetv2+6),tipoVariable,fontsize);			
				 lineaTr2= lineaTr2.substring(offsetv2+6);
				 
			  }
				//alert('linea1 '+lineatabla);
				 
				 tabla =lineatabla + lineaTr1 + lineaVariable + lineaf;								
			}
		     //alert('tabla2 '+tabla);
			//alert('off1 '+off1);
				//alert('encorado '+encontrado)
				//alert('lineaTabla '+tabla);
				cadenaFija ="";
				//alert('cadenaFija '+cadenaFija)
				if(haytabla){
					 cadenaFija = s.substring(0,off1);
					 s1= formatoFijo(cadenaFija);
				}else{
					 tabla="";
					 lineaTr2="";
					 cadenaFija  = formatoFijo(s.substring(offfijo,offfijof+7)); 
					 s1 = s.substring(0,offfijo) + cadenaFija + s.substring(offfijof+7);
				}
				lineaTr1="";
				lineaVariable="";				
				s=s1+tabla + lineaTr2;
				//alert('bloqueFijosHtml ' +s);
	}
	return s;
}


function bloqueNoFijosHtml(s){
	
	lineatabla='<div><table border="1"><tbody>';//linea por si hay tabla
	lineaf="</tbody></table></div>";
	//alert('bloqueNoFijosHtml '+s);

	tipoVariable=0;
	inicio=0;
	lineaTr="";
	lineaTr1="";
	lineaNofija="";
	hayTabla=false;
	//alert('inicio 1 '+inicio);
	while(s.length > 0){
			
			offsetv1=s.indexOf("<txt>");
			offsetv2=s.indexOf("</txt>");
			if(offsetv1 == -1){
				//alert('xxxx '+hayTabla);
				if(hayTabla){
					lineaTr1+=lineaf;
					lineaNofija+=lineaTr1;
					//alert('lineaNofija 1'+lineaNofija);
					hayTabla=false;
				}				
				break;
			}
			if(offsetv1!=0){
				lineaNofija+=s.substring(0,offsetv1);
			}
			
			linea =s.substring(offsetv1,offsetv2+6);
			//fontsize= getLastFontSize(s.substring(0,offsetv2+6));
			//preguntamos si existe algun tabulado de tabla
			//alert('inicio2 '+inicio + 'liena '+ linea + ' ? '+ existeTabulado(linea));
			if(existeTabulado(linea)){
				//	alert('inicio3 '+inicio);
				lineaTr=processLine(linea,tipoVariable,-1);
				hayTabla=true;
				//alert('inicio7 '+inicio);
				if(inicio == 0){
					lineaTr1+= lineatabla+lineaTr;
					inicio++;
				}else{
					lineaTr1+=lineaTr;
				}
			}else{ 
				//alert(hayTabla) ;				
				if(hayTabla){
					lineaTr1+=lineaf;
					lineaNofija+=lineaTr1+linea;
					//alert('lineaNofija 1'+lineaNofija);
					hayTabla=false;
					inicio=0;
					lineaTr1="";
				}else{
			    lineaNofija+=linea;
			  }
			}
			s=s.substring(offsetv2+6);
			//alert(s);
	}
	    if(hayTabla){
	    	  //alert(lineaTr1); 
					lineaTr1+=lineaf;
					lineaNofija+=lineaTr1;
					//alert('lineaNofija 2'+lineaNofija);
					hayTabla=false;
					inicio=0;
					lineaTr1="";
	    }				
  lineaNofija+=s;
    //alert('lineaNofija '+lineaNofija);
	return lineaNofija;
}

function existeTabulado(linea){
	
	offtabla1 = linea.indexOf("<t/>");
	offtabla2 = linea.indexOf("<tc/>");
	offtabla3 = linea.indexOf("<tr/>");
	
	if(offtabla1!=-1 || offtabla2!=-1 || offtabla3!=-1 ) return true;
	return false;
}


function formatoFijo(s) {
	 
	 s=s.replace(/(<txt\/>)/gi,"<div>&nbsp;</div>");	 
	 s=s.replace(/(<txt>)/gi,'<div><font color="#0000ff">');	 
	 s=s.replace(/(<\/txt>)/gi,"</font></div>");	 
	 s=s.replace(/(<fijo>)/gi,"");	 
	 s=s.replace(/(<\/fijo>)/gi,"");	 
	 
	return s;
}

function isBx(cadena){
	if(cadena.indexOf("<bx>")!=-1 || cadena.indexOf("</bx>")!=-1 ) return true;
	return false;
}

function processLine(tr,tipoFijo,fontsize){
  lineaTr="<tr>";
  sizeActual ="";
	//alert('fila '+tr);
	//alert(isBx(tr));
  if(isBx(tr)){
		 lineaTr = '<tr style="background-color:#CCCCCC;">';
	}else{
		lineaTr="<tr>";
	} 
	
	var inicio=0;
  ncol=0;
  encontrado=true; 
	//var arr1 = new Array();
	while(encontrado){
			linea1="<td>";
			linea2=linea3=linea4=linea5="";
			//alert('tr '+tr);
			offset1=tr.indexOf("<t");
		  //alert(offset1);
		  if(offset1==0 && inicio ==0) {
		  	 tr= tr.substring(1);
		  	 continue;
		 	}
			if(offset1==-1){
			  encontrado= false;
			  offset1= tr.length;
			  //se procesa la ultima palabra 
			}
			
			ncol++;
			offsetb=tr.indexOf("</b>");
			offsetu=tr.indexOf("</u>");
			offsetfs=tr.indexOf("</font>");

				cadena = tr.substring(0,offset1); 
				//alert('xxxx '+cadena);
				if(inicio==0){
					 inicio++;
				   valorTabulaSgte=tr.substring(offset1,offset1+4);
				  		   
				   	if(tipoFijo==1)
					     linea1+='<font color="#0000ff">';
					  if(tipoFijo==2)
					     linea1+='<font color="#ff0000">';   
					
					
					//if(fontsize == '') size=getFontSize(cadena);
					//else size= fontsize;
					
					size=getFontSize(cadena);
					//if(size==-1) size= fontsize;
					
					if(size!=-1){
					 sizeActual=size;	
					 linea1+="<font size="+sizeActual+">";
					 linea2="</font>"
					}else{
						ind=getFontSize(tr);
						if(ind == -1 && offsetfs !=-1){
					 			linea1+='<font size="'+sizeActual+'">';
					 			linea2="</font>"
					  } 	
					}
					
								
					if(isSubrayado(cadena)){
					 linea1+="<u>";
					 linea3="</u>";
					}else{
					  if(tr.indexOf("<u>") == -1 && offsetu !=-1){
					 			linea1+="<u>";
					 			linea3="</u>";
					  }			  			  			  
					
					}
																
					if(isNegrita(cadena)){
					 linea1+="<strong>";
					 linea4="</strong>" 
					}else{
					  if(tr.indexOf("<b>") == -1 && offsetb !=-1){
					  				 linea1+="<strong>";
					 					 linea4="</strong>" 
					  }			  			  
					}
						
					 cadena = replaceCadena(cadena);		
					 //alert('cade '+cadena);
		       linea2=linea4+linea3+linea2+linea2+"</td>";//termia en doble font
		       lineaTr+=linea1+cadena+linea2;
		       //alert('lineaTrii '+lineaTr);
				}else{
				  inicio++;
		  
				  //alert('valorTabulaSgte '+valorTabulaSgte);
				  if(("<tc/>").indexOf(valorTabulaSgte)!=-1){
				   linea1+='<p style="text-align: center;">'	;	 
				   linea5="</p>"
				  }
				  if(("<tr/>").indexOf(valorTabulaSgte)!=-1){
				   linea1+='<p style="text-align: right;">'	;	 
				   linea5="</p>"
				  }
				  
				  
				  if(encontrado){
				     valorTabulaSgte=tr.substring(offset1,offset1+4);
				   }
				    
				  if(tipoFijo==1)
					     linea1+='<font color="#0000ff">';
				  if(tipoFijo==2)
					      linea1+='<font color="#ff0000">';			     
								
				 size=getFontSize(cadena);										
				 if(size!=-1){
					 sizeActual=size;	
					 linea1+='<font size="'+sizeActual+'">';
					 linea2="</font>"
					}else{
						ind=getFontSize(tr);
						if(ind == -1 && offsetfs !=-1){
					 			linea1+='<font size="'+sizeActual+'">';
					 			linea2="</font>"
					  } 	
					}
					
					
					if(isSubrayado(cadena)){
					 linea1+="<u>";
					 linea3="</u>";
					}else{
					  if(tr.indexOf("<u>") == -1 && offsetu !=-1){
					 			linea1+="<u>";
					 			linea3="</u>";
					  }			  			  			  
					
					}
																
					if(isNegrita(cadena)){
					 linea1+="<strong>";
					 linea4="</strong>" 
					}else{
					  if(tr.indexOf("<b>") == -1 && offsetb !=-1){
					  				 linea1+="<strong>";
					 					 linea4="</strong>" 
					  }			  			  
					}
						
					 cadena = replaceCadena(cadena);		
					 //alert('cadena2 '+cadena)
		       linea2=linea4+linea3+linea2+linea2+linea5+"</td>";//termia en doble font
		       lineaTr+=linea1+cadena+linea2;
				  
				   //alert('lineaTriixxx '+lineaTr);
				
				}
			
			 if(!encontrado) {
			   lineaTr+="</tr>";  
			    break;
			 }  
			 tr = tr.substring(offset1+4);
	 //alert('next '+tr)
	}
	 lineaTr=lineaTr.replace(/(<font color="#0000ff"><font size="2">)/gi,'<font color="#0000ff" size="2">');
	 lineaTr=lineaTr.replace(/(<font color="#0000ff"><font size="3">)/gi,'<font color="#0000ff" size="3">');
	 lineaTr=lineaTr.replace(/(<font color="#0000ff"><font size="4">)/gi,'<font color="#0000ff" size="4">');
	 lineaTr=lineaTr.replace(/(<font color="#0000ff"><font size="5">)/gi,'<font color="#0000ff" size="5">');
	 lineaTr=lineaTr.replace(/(<font color="#0000ff"><font size="6">)/gi,'<font color="#0000ff" size="6">');
	 lineaTr=lineaTr.replace(/(<font color="#0000ff"><font size="7">)/gi,'<font color="#0000ff" size="7">');
	 lineaTr=lineaTr.replace(/(<font color="#ff0000"><font size="2">)/gi,'<font color="#ff0000" size="2">');
	 lineaTr=lineaTr.replace(/(<font color="#ff0000"><font size="3">)/gi,'<font color="#ff0000" size="3">');
	 lineaTr=lineaTr.replace(/(<font color="#ff0000"><font size="4">)/gi,'<font color="#ff0000" size="4">');
	 lineaTr=lineaTr.replace(/(<font color="#ff0000"><font size="5">)/gi,'<font color="#ff0000" size="5">');
	 lineaTr=lineaTr.replace(/(<font color="#ff0000"><font size="6">)/gi,'<font color="#ff0000" size="6">');
	 lineaTr=lineaTr.replace(/(<font color="#ff0000"><font size="7">)/gi,'<font color="#ff0000" size="7">');	 
	 lineaTr=lineaTr.replace(/(<\/font><\/font>)/gi,"</font>");	 	
	//alert('return '+lineaTr)	
	return lineaTr;
}

function isSubrayado(cadena){
 index =  cadena.indexOf("<u>");
 if(index!=-1) return true;
 return false;
}

function isNegrita(cadena){
  index =  cadena.indexOf("<b>");
 if(index!=-1) return true;
 return false;
}

function getFontSize(cadena){
 //<txt><><b>xxx</b>
   //alert('getFontSize '+cadena);
   cadena1 = cadena.substring(0);
	 cadena1= replaceCadena(cadena1);   	      
   
   index1 = cadena.indexOf(cadena1);
   
   for(i=2; i<=7;i++){
      f="<font size="+i+">"; 
      index =cadena.indexOf(f);
      if(index!=-1 && index < index1){
      	return i;
      }
   }
   
  return -1;
}

function replaceCadena(cadena1){
	//&lt;DATO01&gt;
   //cadena1=cadena1.replace(/(<v\/>)/gi,"&lt;UL1&gt&lt;LI1&gt&lt;/LI1&gt&lt;/UL1&gt");		
   //cadena1=cadena1.replace(/(<v\/>)/gi,"<ul><li></li></ul>");		
   indexV = cadena1.indexOf("<v\/>")
   if(indexV != -1) {
   	  cadena1="<ul><li></li></ul>";
   	  return cadena1;
   }	  
   
   cadena1=cadena1.replace(/(<txt>)/gi,"");	
   cadena1=cadena1.replace(/(txt>)/gi,"");	  
   cadena1=cadena1.replace(/(<txt\/>)/gi,"");	
   cadena1=cadena1.replace(/(<\/txt>)/gi,"");	  
   cadena1=cadena1.replace(/(<b>)/gi,"");	 
   cadena1=cadena1.replace(/(<\/b>)/gi,"");	 
   cadena1=cadena1.replace(/(<u>)/gi,"");	    
   cadena1=cadena1.replace(/(<\/u>)/gi,"");
   cadena1=cadena1.replace(/(<fhe07\/>)/gi,"");	    	    
   cadena1=cadena1.replace(/(<fhe08\/>)/gi,"");	    	    
   cadena1=cadena1.replace(/(<fhe09\/>)/gi,"");	    	    
   cadena1=cadena1.replace(/(<fhe10\/>)/gi,"");	    	    
   cadena1=cadena1.replace(/(<fhe11\/>)/gi,"");	    	   
   cadena1=cadena1.replace(/(<fhe12\/>)/gi,"");	
   cadena1=cadena1.replace(/(<font size="2">)/gi,"");	 
	 cadena1=cadena1.replace(/(<font size="3">)/gi,"");	 
	 cadena1=cadena1.replace(/(<font size="4">)/gi,"");	 
	 cadena1=cadena1.replace(/(<font size="5">)/gi,"");	 
	 cadena1=cadena1.replace(/(<font size="6">)/gi,"");	 
	 cadena1=cadena1.replace(/(<font size="7">)/gi,"");   
   cadena1=cadena1.replace(/(<bx>)/gi,"");	 
   cadena1=cadena1.replace(/(<\/font>)/gi,"");	   
   cadena1=cadena1.replace(/(<\/bx>)/gi,"");	      	     
   cadena1=cadena1.replace(/(<)/gi,"");	    	      
   cadena1=cadena1.replace(/(<\/)/gi,"");	    	      
   cadena1=cadena1.replace(/(\/>)/gi,"");	    	         
   cadena1=cadena1.replace(/(>)/gi,"");	    	        
   cadena1=cadena1.replace(/(\/)/gi,"");	    	      
    
 return cadena1;
}
//var objetoTipoR= new Array();
var j=0;
function getLastFontSize(lineaFija){
	 mayor=-1;
	 font=-1;
	 for(i=7; i<=12;i++){
      f="<fhe0"+i+"/>" 
      index =lineaFija.lastIndexOf(f);
      if(index > mayor){
      	  font = i;
      	  mayor= index;
      }
      //objetoTipo[j++]=new Tipo(i,index);		
      //return (i-5);
   }
  if(font!=-1){
  	if(font==8) return -1;
    return font-5;
  }
   
   return -1; 
}


function bloqueFontSize(s){
	
	cadena1 = s.substring(0);
	res="";
	ultimoFont="";
	numeroLinea=0;
	primerFont="";
	//alert('bloqueFontSize '+s)
	while(cadena1.length > 0){		
			offsetv1=cadena1.indexOf("<txt>");
			offsetv2=cadena1.indexOf("</txt>");
			if(offsetv1==-1) {
				break;
			}
			
			
			linea = cadena1.substring(offsetv1,offsetv2+6);
			//alert('1 '+linea)
		/*	if(existeTabulado(linea)){
				 res+=cadena1.substring(0,offsetv1) + linea;
				 cadena1 = cadena1.substring(offsetv2+6);		
				 continue;
			}*/
			//alert('ultm0 '+ ultimoFont);
			primerFont = ultimoFont;	
		
			hayFont=true;
			hayCambio=false;
			
			while(hayFont){
				index = linea.indexOf("<fhe");
				if(index == -1) {
					//off1 = linea.indexOf("</txt>")
					//alert('ultm '+ ultimoFont);
					//alert('primerFont '+primerFont)
					if(primerFont != ''){
						//alert('numeroLinea '+numeroLinea);
						if(numeroLinea > 0 ){	
											 						 
						  off = linea.indexOf("<txt><font size");						
						  ltam="<txt>"+getFont(primerFont,false);
						  //alert(ltam);
						  if(off == -1) linea = linea.replace(/<txt>/gi,ltam);
						  linea = linea.replace(/<\/txt>/gi,"</font></txt>");
						}  
						//alert(linea);
					}
					hayFont =false;
					break; 
				}
				ultimoFont=linea.substring(index,index+8);				
				valor=getFont(linea.substring(index,index+8),hayCambio);			
				//alert('valor '+valor)	
				linea=linea.substring(0,index)+ valor + linea.substring(index+8);
				//alert('liena cambiado '+linea);
				hayCambio=true;							
				numeroLinea++;   
			}//fin del while
			
			res += cadena1.substring(0,offsetv1) + linea;
			//alert('res '+ res);
			cadena1 = cadena1.substring(offsetv2+6);			
			//alert('cadena1 '+cadena1);
	}
	res+=cadena1;
	 res=res.replace(/(<font size="2"><\/font>)/gi,"");	 
	 res=res.replace(/(<font size="3"><\/font>)/gi,"");	 
	 res=res.replace(/(<font size="4"><\/font>)/gi,"");	 
	 res=res.replace(/(<font size="5"><\/font>)/gi,"");	 
	 res=res.replace(/(<font size="6"><\/font>)/gi,"");	 
	 res=res.replace(/(<font size="7"><\/font>)/gi,"");
	//alert('res '+res)	
	return res;
}

function getFont(s,hayCierre){
	//alert(s);
	if(!hayCierre){
	 s=s.replace(/(<fhe07\/>)/gi,'<font size="2">');	
	 s=s.replace(/(<fhe08\/>)/gi,'<font size="3">');	  
	 s=s.replace(/(<fhe09\/>)/gi,'<font size="4">');	 
	 s=s.replace(/(<fhe10\/>)/gi,'<font size="5">');	 
	 s=s.replace(/(<fhe11\/>)/gi,'<font size="6">');	
	 s=s.replace(/(<fhe12\/>)/gi,'<font size="7">');
	}else{
	 s=s.replace(/(<fhe07\/>)/gi,'</font><font size="2">');	
	 s=s.replace(/(<fhe08\/>)/gi,'</font><font size="3">');	  
	 s=s.replace(/(<fhe09\/>)/gi,'</font><font size="4">');	 
	 s=s.replace(/(<fhe10\/>)/gi,'</font><font size="5">');	 
	 s=s.replace(/(<fhe11\/>)/gi,'</font><font size="6">');	
	 s=s.replace(/(<fhe12\/>)/gi,'</font><font size="7">');
	}
	 return s;
}

function Tipo(font,posicion){
	this.font=font;
	this.posicion=posicion;
}

function elminarFontDefault(s){
	s=s.replace(/(<fhe08\/><t\/>)/gi,"<t/>");
	s=s.replace(/(<fhe08\/><tc\/>)/gi,"<tc/>");
	s=s.replace(/(<fhe08\/><tr\/>)/gi,"<tr/>");	
	cadena1 = s.substring(0);
	res="";
	ultimoFont="";
	//alert('elminarFontDefault '+cadena1)
	
	valorAnterior="";
	while(cadena1.length > 0){		
			offsetv1=cadena1.indexOf("<txt>");
			offsetv2=cadena1.indexOf("</txt>");
			if(offsetv1==-1) {
				break;
			}
						
			linea = cadena1.substring(offsetv1,offsetv2+6);
			//alert('ssss '+linea)			
			hayFont=true;
			hayCambio=false;
			while(hayFont){
				index = linea.indexOf("<fhe");
				if(index == -1) {				
					hayFont =false;
					break; 
				}
				ultimoFont=linea.substring(index,index+8);
														
				if(valorAnterior == ultimoFont)
						valor="";			
				else
					  valor = "<tam"+linea.substring(index+4,index+8);			
				//alert('valor '+valor)	
				valorAnterior = ultimoFont;
				linea=linea.substring(0,index)+ valor + linea.substring(index+8);
				//alert('liena cambiado '+linea);
				hayCambio=true;				
			}
			
			res += cadena1.substring(0,offsetv1) + linea;
			//alert('res '+ res);
			cadena1 = cadena1.substring(offsetv2+6);			
			//alert('cadena1 '+cadena1);
	}
	res+=cadena1;
	 res=res.replace(/(<tam07\/>)/gi,"<fhe07/>");	
	 res=res.replace(/(<tam08\/>)/gi,"<fhe08/>");	  
	 res=res.replace(/(<tam09\/>)/gi,"<fhe09/>");	 
	 res=res.replace(/(<tam10\/>)/gi,"<fhe10/>");	 
	 res=res.replace(/(<tam11\/>)/gi,"<fhe11/>");	
	 res=res.replace(/(<tam12\/>)/gi,"<fhe12/>");
	return res;
}

function bloqueVinetas(s){
	//alert(s);
	cadena1 = s.substring(0);
	res="";
	ini=0;
	hubovineta=false;
	while(cadena1.length > 0){		
			offsetv1=cadena1.indexOf("<txt>");
			offsetv2=cadena1.indexOf("</txt>");
			offsetv3=cadena1.indexOf("<v/>");
			
			
			if(offsetv1==-1) {
				break;
			}
			
			linea = cadena1.substring(offsetv1,offsetv2+6);
			
			if(existeTabulado(linea)){
				 res+=cadena1.substring(0,offsetv1) + linea;
				 cadena1 = cadena1.substring(offsetv2+6);		
				 continue;
			}
			//alert('offsetv1 '+offsetv1 + ' offsetv3 '+offsetv3 + ' ini '+ini)
			if(offsetv1+5 == offsetv3 || offsetv1+8 == offsetv3 || offsetv1+11 == offsetv3){
				//hay vineta 
				if(ini==0){
					linea = linea.replace("<txt><v/>","<ul><li>");
					linea = linea.replace("<txt><b><v/>","<ul><li><strong>");
					linea = linea.replace("<txt><u><v/>","<ul><li><u>");
					linea = linea.replace("<txt><b><u><v/>","<ul><li><u><strong>");
					linea = linea.replace("<txt><u><b><v/>","<ul><li><u><strong>");
					linea = linea.replace("</txt>","</li>");
					
				}else{
					linea = linea.replace("<txt><v/>","<ul><li>");
					linea = linea.replace("<txt><b><v/>","<ul><li><strong>");
					linea = linea.replace("<txt><u><v/>","<ul><li><u>");
					linea = linea.replace("<txt><b><u><v/>","<ul><li><u><strong>");
					linea = linea.replace("<txt><u><b><v/>","<ul><li><u><strong>");
					linea = linea.replace("</txt>","</li>");
				}
				hubovineta=true;
				ini++;
			}else{
				ini=0;
				//alert(hubovineta)
				if(hubovineta){
					hubovineta=false;
					index = res.lastIndexOf("</li>");
					res = res.substring(0,index+5)+ "</ul>"+ res.substring(index+5);
				}
			}
						
			
			
			res += cadena1.substring(0,offsetv1) + linea;
			//alert('res '+ res);
			cadena1 = cadena1.substring(offsetv2+6);			
			//alert('cadena1 '+cadena1);
	}
	if(hubovineta){
		hubovineta=false;
		index = res.lastIndexOf("</li>");
		res = res.substring(0,index+5)+ "</ul>"+ res.substring(index+5);
	}
	res+=cadena1;
	//alert(res);
	return res;
	
}

function replaceImgHtml(s){
	//<txt/><txt><img>dupla</img></txt>
	 //ruta="/images/";
	 res="";	
	 //alert('replaceImgHtml');
	 //var ruta = "/nssicc_web/resources/images/";
	 var ruta= path +"/images/";
	 //alert(ruta);
	 cadena = s.substring(0);	 
	 hayimagen= true;
	 valor="";
	 while(hayimagen){
	 	 index1=cadena.indexOf("<img>");
	 	 index2=cadena.indexOf("</img>");
	 	 if(index1==-1) break;
	   imagen	= cadena.substring(index1+5,index2);
	   tline= '<img id="'+imagen+'" src="'+ruta+imagen+'"></img>';  
	   res+=cadena.substring(0,index1)+tline;
	   //alert(res);
	   cadena = cadena.substring(index2+6) ;	 	 
	 }
	 
	 res+=cadena;
	 //alert(res)
	return res;
}

function eliminarReferencias(s){
	 res="";	 		 
	 cadena = s.substring(0);	 
	 hayRef= true;
	 valor="";
	 while(hayRef){
	 	 index1=cadena.indexOf("<a href=");
	 	 index2=cadena.indexOf("</a>");
	 	 
	 	 sublinea= cadena.substring(index1);
	 	 index3 = sublinea.indexOf(">")
	 	 offCierre = index1+index3+1;
	 	 
	 	 
	 	 if(index1==-1) break;
	   valor	= cadena.substring(offCierre,index2);	   
	   res+=cadena.substring(0,index1)+valor;
	   //alert(res);
	   cadena = cadena.substring(index2+4) ;	 	 
	 }
	 
	 res+=cadena;
	// alert('eliminarReferencias'+res)
	 return res;
}

function eliminarBxMultiples(s){
	s=s.replace(/(<\/bx><bx>)/gi,"");
	//cuando se trata de una sola columa no le va a poder dar format de tabla y bx
	s=s.replace(/(<bx>)/gi,"<bx>");
	s=s.replace(/(<\/bx>)/gi,"</bx>");
	s=s.replace(/(<bx><bx>)/gi,"<bx>");
	s=s.replace(/(<\/bx><\/bx>)/gi,"<bx>");
	//s=s.replace(/(<\/img>)/g,"");
	s=s.replace(/(<txt><\/txt>)/gi,"");
	s=s.replace(/(<txt> <\/txt>)/gi,"<txt/>");
	s= s.replace(/(<p style="text-align: center;">)/gi,"<tc/>");		 		
	s= s.replace(/(<p style="text-align: left;">)/gi,"<t/>");		 		 
	s= s.replace(/(<p style="text-align: right;">)/gi,"<tr/>");	
	s = s.replace(/(<bx><fijo>)/gi,"<fijo><bx>");
	s = s.replace(/(<\/fijo><\/bx>)/gi,"</bx></fijo>");
	s=s.replace(/(<font size="">)/gi,"");
	s=s.replace(/(font size="")/gi,"");
	return s;
}