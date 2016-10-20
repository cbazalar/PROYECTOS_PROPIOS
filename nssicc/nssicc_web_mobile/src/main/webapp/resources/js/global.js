function aMays(e, elemento) {
	tecla=(document.all) ? e.keyCode : e.which; 
  elemento.value = elemento.value.toUpperCase();  
}

function aMayusculas(obj,id){
    obj = obj.toUpperCase();
    document.getElementById(id).value = obj;
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

