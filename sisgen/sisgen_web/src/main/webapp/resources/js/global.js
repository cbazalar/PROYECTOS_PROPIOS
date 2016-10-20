
function completarCaracteres(valor, longitud, caracter){
		var valorAux = valor;
		if (valor.length != 0) {
			var faltante = parseInt(longitud) - valor.length;
			
			if (faltante > 0) {
				var valorAux = new String("");
				for (var i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;   
			}
			
		}
		return valorAux;
}



function aMays(e, elemento) {
	tecla=(document.all) ? e.keyCode : e.which; 
  elemento.value = elemento.value.toUpperCase();  
}



function digitsOnly (keyCode, key) {
	return { cancelKey: !("0123456789".indexOf(key) != -1 || keyCode == 127) };
}

function email (keyCode, key) {
	return { cancelKey: /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/.test(key) };
}

function cancelLetters (keyCode, key) {
	return { cancelKey: /[a-zA-Z ]/.test(key) };
}

function cancelDigits (keyCode, key) {
	return { cancelKey: /[0-9]/.test(key) };
}

function jsMayuscula(dd) {
	dd.value=Trim(dd.value.toUpperCase());
	return dd;
}

function Trim(dato) { 
	var l=dato.length;
	var s="",pi=0,pf=0,sw=0,tt=0;
	if (dato!="")
	{	for(var i=0;i<l;i++)
			if (dato.charAt(i)!=" ")	
			{	pi=i;	
				sw=1;
				break; 
			}
		if (sw==1)
		{	for(var i=l;i>0;i--)
				if (dato.charAt(i-1)!=" ")	
				{	pf=i;
					sw=2;
					break;
				}
			tt = pf-pi;
			s=Mid(dato,pi,tt);
		}
	}
	return s;
}

function Mid(dato,pos,can) {
	var cadenan="";
  	var total=0; 
 	total = ((pos+can)>dato.length) ? dato.length : pos+can ;
	total-=1;
      	for ( var i=pos; i<=total; i++)
      	{ cadenan+=dato.substring(i,i+1);    }
      	return( cadenan );
}  

function jsRellenarCeros(cad, cantidad) {
  if ((cad == null) || (cad.length == 0)) { return cad; }
  n = cantidad - cad.length;
  relleno = '';
  for( i= 0; i < n; i++) {
      relleno = relleno + '0';
  } 
  relleno = relleno + cad;
  return relleno;
}

// Completa una cadena con un caracter hasta obtener el largo requerido. 
// Se debe especificar si se completa en el lado izquierdo o derecho.
// Donde :
// - cadena : Cadena a completar
// - caracter : Caracter con el cual completar
// - largo : Largo requerido
// - ldIzquierda : Indica si se completara en el lado izquierdo o derecho (true: izquierdo, false: derecho)
function jsCompletarCadena(cadena, caracter, largo, ldIzquierda) {
	largoCadena = cadena.length;
	cadenaAuxiliar = "";
	for (i=0; i<largo - largoCadena; i++) { cadenaAuxiliar = cadenaAuxiliar + caracter; }	
	return ldIzquierda ? (cadenaAuxiliar + cadena) : (cadena + cadenaAuxiliar);
}

// Permite solo ingresar numeros
// evento : Evento que se sucede al mantener pulsada una tecla
function jsEsNumero(evento) {
  key = evento.keyCode ? evento.keyCode :
        evento.charCode ? evento.charCode :
		evento.which ? evento.which : void 0;
  return key >= 48 && key <= 57;  
}

// Permite solo ingresar n?meros decimales
// evento : Evento que se sucede al mantener pulsada una tecla
function jsEsNumeroDecimal(evento) {
  key = evento.keyCode ? evento.keyCode :
        evento.charCode ? evento.charCode :
		evento.which ? evento.which : void 0;
  return (key >= 48 && key <= 57) || (key == 46);
}

//Permite solo ingresar n?meros decimales con signo negativo
//evento : Evento que se sucede al mantener pulsada una tecla
function jsEsNumeroDecimalNegativo(evento) {
key = evento.keyCode ? evento.keyCode :
     evento.charCode ? evento.charCode :
		evento.which ? evento.which : void 0;
return (key >= 48 && key <= 57) || (key == 46) || (key == 45);  
}


function esVacio(cad) { //retorna true si la cadena esta vacia o tiene espacios en blanco
  var i;
  var blanco = " \n\t" + String.fromCharCode(13); // blancos
  var es_vacio;
  
    if ((cad == null) || (cad.length == 0)) { return true; }
    for(i = 0, es_vacio = true; (i < cad.length) && es_vacio; i++)
      es_vacio = blanco.indexOf(cad.charAt(i)) != - 1;
    return(es_vacio);
}

function generarPopup() {
  var ventana = window.open("", "ventana", "location=1,status=1,scrollbars=1,width=100,height=100");
  return ventana;
}





/*
the third argument to changeKey should be a function
function exampleKeyChecker (keyCode, key)
which returns an object 
{ cancelKey: boolean, replaceKey: boolean, newKeyCode: number, newKey:string }
Not all properties need to be present, if cancelKey is set to true the
other properties are not needed.
If replaceKey is set to true then at least newKeyCode needs to be set.
*/

// Valida un Tope
// Robinson Vela Bardales. 14-09-2007
function validaTopeNumerico(source,tope) {
	if (source.value=="") return true;
	var montoTemp = parseFloat(source.value);
	var montoTope = parseFloat(tope);
	if (montoTemp > montoTope) {
		return false;
	}
return true;
}

// Formatea un numero a numero Decimales
// Robinson Vela Bardales. 14-09-2007
function formatearNumero(source,decimales) 
 {
 var retorno;
 
 if (source.value=="") return "0.00";
  if (decimales>0) retorno="0.00";
 else retorno="0";
  if ((source.value != '') && (source.value > 0 ))
     {	
	 retorno = MontoNumeroDecimal(source.value,decimales);
	  }
 source.value=retorno;
 }

//Formatea un numero a numero Decimales
//Robinson Vela Bardales. 14-09-2007
function formatearNumeroNegativo(source,decimales) 
{
var retorno;
var valor = source.value;
var count = 0;
var isNegativo =  false;

for (var i = 0; i < valor.length; i++) {
	if((valor.charAt(i)) == '-'){
		count++;
	}
}
if(count == 1 && (valor.charAt(0)=='-') && valor.length>1)
{
		isNegativo = true;
}
if(isNegativo){
	source.value = valor.substring(1); 
}

if (source.value=="") return "0.00";
if (decimales>0) retorno="0.00";
else retorno="0";
if ((source.value != '') && (source.value > 0 ))
  {	
	 retorno = MontoNumeroDecimal(source.value,decimales);
	  }


if(isNegativo){
	source.value='-'+retorno; 
}else{
	source.value=retorno;
	}
}

//Formatea un numero a numero Decimales y valida que el monto no sea mayor al tope
//Robinson Vela Bardales. 14-09-2007
function formatearNumeroWithTope(source,decimales,tope) 
{
var retorno;

if (source.value=="") return "0.00";
if (validaTopeNumerico(source,tope))
	{
	 if (decimales>0) retorno="0.00";
	 else retorno="0";
	 if ((source.value != '') && (source.value > 0 ))
	    {	
		 retorno = MontoNumeroDecimal(source.value,decimales);
		}
	}		  
else
	{
	  retorno="0.00";	
	 }	
source.value=retorno;
}

//Ejcuta el Formato un numero a numero Decimales
//Robinson Vela Bardales. 14-09-2007
function MontoNumeroDecimal(
	montoInput,
	pifMaxDecimalPlaces
)
{
	var montoRetorno;
	var sNumero;
	var iPosicion;
	var sCaracter;
	var sNumeroEntero = '';
	var sNumeroDecimal = '';
	var sNumeroEnteroReverse;
	var sNumeroEnteroReverseNew = '';
	var valNumero = /^\-?[0-9,]{0,}\.?\d{0,}$/;	
	
	var iLongitudDecimal;
	var bConSigno = false;

	sNumero = montoInput;

	if (valNumero.test(sNumero)){
		var montoTemp = parseFloat(sNumero);
		sNumero = montoTemp.toString();
		iPosicion = sNumero.indexOf('.');

		// Separo Decimales y Enteros
		if (iPosicion >= 0){
			sNumeroEntero = sNumero.substring(0,iPosicion);
			sNumeroDecimal = sNumero.substring(iPosicion + 1, sNumero.length);
			iLongitudDecimal = sNumeroDecimal.length;
		}
		else{
			sNumeroEntero = sNumero;
			iLongitudDecimal = 0;
		}

		// Evaluo parte Decimal
		if (pifMaxDecimalPlaces > 0){
			// Si la longitud de decimales es mayor
			if(iLongitudDecimal > pifMaxDecimalPlaces){
				sNumeroDecimal = sNumeroDecimal;

				sNumeroDecimal = sNumeroDecimal.substring(0,pifMaxDecimalPlaces) + '.' + sNumeroDecimal.substring(pifMaxDecimalPlaces, sNumeroDecimal.length);

				sNumeroDecimal = Math.round(sNumeroDecimal);
			}
			// Agrego los Ceros que falten en la parte decimal
			if (iLongitudDecimal < pifMaxDecimalPlaces){
				for (iPosicion=1; iPosicion<=(pifMaxDecimalPlaces - iLongitudDecimal); iPosicion++){
					sNumeroDecimal = sNumeroDecimal + '0';
				}
			}
			sNumeroDecimal = '.' + sNumeroDecimal;
		}
		//Obtengo numero completo
		sNumero = sNumeroEntero + sNumeroDecimal;
		montoRetorno = sNumero;
	}
	return montoRetorno;
}

function soloNumero(e){ 
	
    var tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; // backspace
    if (e.ctrlKey && tecla==86) { return true;} //Ctrl v
    if (e.ctrlKey && tecla==67) { return true;} //Ctrl c
    if (e.ctrlKey && tecla==88) { return true;} //Ctrl x
 
    patron = /[0-9]/; //patron
    
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba de patron
}


function soloLetras(e) { // 1
    var tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; // backspace
    if (tecla==32) return true; // espacio
    if (e.ctrlKey && tecla==86) { return true;} //Ctrl v
    if (e.ctrlKey && tecla==67) { return true;} //Ctrl c
    if (e.ctrlKey && tecla==88) { return true;} //Ctrl x
 
    patron = /[a-zA-ZÑñáéíóúÁÉÍÓÚ]/; //patron
 
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba de patron
}	

function soloLetrasNumeros(e){ 
	
    var tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; // backspace
    if (tecla==32) return true; // espacio
    if (tecla==45) return true; // guion (-) 
    if (e.ctrlKey && tecla==86) { return true;} //Ctrl v
    if (e.ctrlKey && tecla==67) { return true;} //Ctrl c
    if (e.ctrlKey && tecla==88) { return true;} //Ctrl x
 
    patron = /[0-9]|[a-zA-ZÑñáéíóúÁÉÍÓÚ]/; //patron
    
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba de patron
}


function soloLetrasNumerosSEB(e){ 
	
    var tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; // backspace
    if (tecla==32) return false; // espacio
    if (e.ctrlKey && tecla==86) { return true;} //Ctrl v
    if (e.ctrlKey && tecla==67) { return true;} //Ctrl c
    if (e.ctrlKey && tecla==88) { return true;} //Ctrl x
 
    patron = /[0-9]|[a-zA-ZÑñáéíóúÁÉÍÓÚ]/; //patron
    
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba de patron
}

function valFecha(oTxt){
	var bOk = true;
	if (oTxt != ""){
		bOk = bOk && (valAno(oTxt));
		bOk = bOk && (valMes(oTxt));
		bOk = bOk && (valDia(oTxt));
		bOk = bOk && (valSep(oTxt));
	}
	return bOk;
}

function valAno(oTxt){
	var bOk = true;
	var nAno = oTxt.substr(6);
	bOk = bOk && ((nAno.length == 2) || (nAno.length == 4));
	if (bOk){
		for (var i = 0; i < nAno.length; i++){
			bOk = bOk && esDigito(nAno.charAt(i));
		}
	}
	return bOk;
}

function valDia(oTxt){
	var bOk = false;
	var nDia = parseInt(oTxt.substr(0, 2), 10);
	bOk = bOk || ((nDia >= 1) && (nDia <= finMes(oTxt)));
	return bOk;
}

function valMes(oTxt){
	var bOk = false;
	var nMes = parseInt(oTxt.substr(3, 2), 10);
	bOk = bOk || ((nMes >= 1) && (nMes <= 12));
	return bOk;
}

function valSep(oTxt){
	var bOk = false;
	bOk = bOk || ((oTxt.charAt(2) == "-") && (oTxt.charAt(5) == "-"));
	bOk = bOk || ((oTxt.charAt(2) == "/") && (oTxt.charAt(5) == "/"));
	return bOk;
}

function esDigito(sChr){
	var sCod = sChr.charCodeAt(0);
	return ((sCod > 47) && (sCod < 58));
}

function finMes(oTxt){
	var nMes = parseInt(oTxt.substr(3, 2), 10);
	var nRes = 0;
	switch (nMes){
		case 1: nRes = 31; break;
		case 2: nRes = 29; break;
		case 3: nRes = 31; break;
		case 4: nRes = 30; break;
		case 5: nRes = 31; break;
		case 6: nRes = 30; break;
		case 7: nRes = 31; break;
		case 8: nRes = 31; break;
		case 9: nRes = 30; break;
		case 10: nRes = 31; break;
		case 11: nRes = 30; break;
		case 12: nRes = 31; break;
	}
	return nRes;
}

function changeKey (textControl, evt, keyChecker) {
	var keyCode = evt.keyCode ? evt.keyCode :
                  evt.charCode ? evt.charCode :
		          evt.which ? evt.which : void 0;
	//var keyCode = evt.charCode ;
  	var key;
  	if (keyCode) {
    	key = String.fromCharCode(keyCode);
  	}
  	var longitud = textControl.value.length + 1;
  	var maximaLongitud = textControl.maxLength;
  	
  	if (longitud > maximaLongitud) {
  		return false;
  	}
  	
  	
  	var keyCheck = keyChecker(keyCode, key);
  	
  	if (typeof textControl.setSelectionRange != 'undefined') {
  		
		if (keyCheck.cancelKey) {
      		if (evt.preventDefault) {
        		evt.preventDefault();
      		}
      		return false;
    	}
    	else if (keyCheck.replaceKey) {
			// cancel the key event and insert the newKey for the current selection
      		if (evt.preventDefault) {
	  			evt.preventDefault();
      		}
      		var oldSelectionStart = textControl.selectionStart;
      		var oldSelectionEnd = textControl.selectionEnd;
      		var selectedText = textControl.value.substring(oldSelectionStart, oldSelectionEnd);
			var newText = typeof keyCheck.newKey != 'undefined' ? keyCheck.newKey : String.fromCharCode(keyCheck.newKeyCode);
      		textControl.value = textControl.value.substring(0, oldSelectionStart) + 
        						newText + textControl.value.substring(oldSelectionEnd);
      		textControl.setSelectionRange(oldSelectionStart + newText.length, oldSelectionStart + newText.length);
      		return false;
    	}
    	else {
      		return true;
    	} 
  	}
  	else if (keyCheck.cancelKey) {
    	if (evt.preventDefault) {
      		evt.preventDefault();
    	}
    	return false;
  	}
  	else {
    	return true;
  	}
}

function lettersToUpperCase (keyCode, key) {
	var newKey = key.toUpperCase();
  	if (newKey != key) {
  		return { replaceKey: true, newKeyCode: newKey.charCodeAt(), newKey: newKey };
  	}
  	else {
  		return { cancelKey: false };
  	}
}

function activarEnterCriteriosBusqueda(idBotonBusqueda) {
	var id = idBotonBusqueda ;		
    var link = document.getElementById(id);
	$('.cls-inputfield').each(function(indice, elemento) {
        $(elemento).keyup(function(e){
        	var keyCode = e.keyCode ? e.keyCode :
                          e.charCode ? e.charCode :
		                  e.which ? e.which : void 0;
		    if(keyCode == 13) { 
		       if(link != null) {
					link.click();
			   }
		    }
		    
		  });
		  
	 });
	
	$('select').keyup(function(e){
		var keyCode = e.keyCode ? e.keyCode :
            e.charCode ? e.charCode :
            e.which ? e.which : void 0;
	    if(keyCode== 13) { 
	       if(link != null) {
				link.click();
		   }
	    }
	    
	  });
	
}


function activarESCPopup(idPopup) {
	$(idPopup).each(function(indice, elemento) {
		 $(elemento).keyup(function(e){
        	var keyCode = e.which ;
 		    if(keyCode == 27) { 
		       window.close();
		       return;
		    }
		    
		  });
		  
	 });
	
	$('.cls-inputfield').each(function(indice, elemento) {
		$(elemento).keyup(function(e){
        	
        	var keyCode = e.which ;
   	        if(keyCode == 27) { 
		       window.close();
		       return;
		    }
		    
		  });
		  
	 });
	
	
}


function openCentered (url, name, specs, width, height) {
	var w = screen.availWidth;
		var h = screen.availHeight;
	var x = Math.round((w - width) / 2);
		var y = Math.round((h - height) / 2);  

	var newSpecs = 'left=' + x + ',top=' + y + ',width=' + width + ',height=' + height;	

	if(specs != null && specs.trim() != "") {    
    	newSpecs = newSpecs + ',' + specs;
	}    
	window.open(url, name, newSpecs);
}  

function jsValidarMayuscula(evento) {
	key = evento.keyCode ? evento.keyCode :
	        evento.charCode ? evento.charCode :
			evento.which ? evento.which : void 0;
	
    if ((key <= 65 && key >= 90) || (key <= 97 && key >= 127)) {
		return true;
	}
	return false;
}  

function jsEsNumeroyNegativo(evento) {
	  key = evento.keyCode;
	  return (key >= 48 && key <= 57) || (key == 45);   
	}

function formatearNumeroWithTopeyNegativo(source,decimales,tope) 
{
var retorno;

if (source.value=="") return "0.00";
if (validaTopeNumerico(source,tope))
	{
	 if (decimales>0) retorno="0.00";
	 else retorno="0";
	 if ((source.value != ''))
	    {
		var valNumero = /^\-?[0-9,]{0,}\.?\d{0,}$/;		
		if(!valNumero.test(source.value)) {
			if (decimales>0) retorno="0.00";
		 	else retorno="0";
		}	
		else	
		 	retorno = MontoNumeroDecimal(source.value,decimales);
		}
	}		  
else
	{
		if (decimales>0) retorno="0.00";
	 	else retorno="0";
	 }	
source.value=retorno;
}

