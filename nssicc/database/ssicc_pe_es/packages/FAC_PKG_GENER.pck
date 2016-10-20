CREATE OR REPLACE PACKAGE "FAC_PKG_GENER" IS
  /* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;

/**************************************************************************
Descripcion       : Algoritmo de dígito verificador que trabaja con cadenas 
                    de dígitos decimales de cualquier tamaño
Fecha Creacion    : 29/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_VERHOEFF
  (psEntero          VARCHAR2)
RETURN VARCHAR2;
   
/**************************************************************************
Descripcion       : Convierte Decimal a Hexadecimal
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_DEC2HEX
  (pnDecimal          NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Convierte Decimal a Binario
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_DEC2BIN
  (pnInteger          NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Convierte Binario a Decimal
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psEntrada        :     Dato Binario

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_BIN2DEC
  (psEntrada       VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Convierte Decimal a Binario
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_BITXOR
  (pnXBin          VARCHAR2,
   pnYBin          VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Un algoritmo de criptografía simétrica, basado en cifrado 
                    de flujo (stream cipher)
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psMensaje        :  Mensaje a Cifrar
  psClave          :  Clave a usar para cifrar

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_ALLEGED_RC4
  (psMensaje          VARCHAR2,
   psClave            VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : El algoritmo de Base 64, se trata sencillamente de pasar 
                    un número en base 10 a base 64
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  pnNumero        :  Numero de Entrada

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_BASE64
  (pnNumero          NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Se requiere contar con una función que retorne un dato 
                   alfanumérico el cual constituye el código de control, este 
                   código es la representación única de una factura
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  pnMontoFactura  :  Monto Factura
  pnCodigoAutorizacion  :  Codigo de Autorizacion
  pnNumeroFactura  :  Numero de Factura
  pnNumeroNIT     :  Numero NIT
  psFechaProceso  :  Fecha proceso (Formato: dd/MM/yyyy)
  psLlave         :  Llave

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_GENER_CODIG_CTROL
  (pnMontoFactura          NUMBER,
   pnCodigoAutorizacion    NUMBER,
   pnNumeroFactura         NUMBER,
   pnNumeroNIT             NUMBER,
   psFechaProceso          VARCHAR2,
   psLlave                 VARCHAR2)
RETURN VARCHAR2;

end FAC_PKG_GENER;
/
CREATE OR REPLACE PACKAGE BODY "FAC_PKG_GENER" IS

/**************************************************************************
Descripcion       : Algoritmo de dígito verificador que trabaja con cadenas 
                    de dígitos decimales de cualquier tamaño
Fecha Creacion    : 29/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_VERHOEFF
  (psEntero          VARCHAR2)
RETURN VARCHAR2
IS
  type vi IS VARRAY(10) OF INTEGER;
  type vmatriz1 IS VARRAY(10) OF vi;
  type vmatriz2 IS VARRAY(8) OF vi;
  matriz1 vmatriz1;
  matriz2 vmatriz2;
  inv     vi;
  VectorD  vi;
  VectorP  vi;
   
  NumInv  VARCHAR2(100);
  wCheck  NUMBER:=0;
  IndiceN NUMBER;
  IndiceP NUMBER;

BEGIN
   
  matriz1:=vmatriz1(vi(0,1,2,3,4,5,6,7,8,9),
                      vi(1,2,3,4,0,6,7,8,9,5),
                      vi(2,3,4,0,1,7,8,9,5,6),
                      vi(3,4,0,1,2,8,9,5,6,7),
                      vi(4,0,1,2,3,9,5,6,7,8),
                      vi(5,9,8,7,6,0,4,3,2,1),
                      vi(6,5,9,8,7,1,0,4,3,2),
                      vi(7,6,5,9,8,2,1,0,4,3),
                      vi(8,7,6,5,9,3,2,1,0,4),
                      vi(9,8,7,6,5,4,3,2,1,0));
                      
  matriz2:=vmatriz2(vi(0,1,2,3,4,5,6,7,8,9),
                      vi(1,5,7,6,2,8,3,0,9,4),
                      vi(5,8,0,3,7,9,6,1,4,2),
                      vi(8,9,1,6,0,4,3,5,2,7),
                      vi(9,4,5,3,1,2,6,8,7,0),
                      vi(4,2,8,6,5,7,3,9,0,1),
                      vi(2,7,9,3,8,0,6,4,1,5),
                      vi(7,0,4,6,9,1,3,2,5,8));   
                      
  inv := vi(0,4,3,2,1,5,6,7,8,9);                               
   
  numInv := '';
  FOR i IN REVERSE 1..length(psEntero) LOOP
    numInv := numInv || substr(psEntero, i, 1);
  END LOOP;
   
  FOR i IN 0..LENGTH(numInv)-1 LOOP
    VectorD:=matriz1(wCheck+1);
    VectorP:=matriz2(Mod(I+1,8)+1);
    IndiceN:=TO_NUMBER(Substr(NumInv,I+1,1));
    IndiceP:=VectorP(IndiceN+1);
    wCheck:=VectorD(IndiceP+1);   
  END LOOP;
  
  RETURN Inv(wCheck+1);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR FAC_FN_VERHOEFF: ' || ls_sqlerrm);

END FAC_FN_VERHOEFF;

/**************************************************************************
Descripcion       : Convierte Decimal a Hexadecimal
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_DEC2HEX
  (pnDecimal          NUMBER)
RETURN VARCHAR2
IS
  lnDecimal   NUMBER;
  lnResto     NUMBER;
  lsChr       VARCHAR2(10);
  lsHexa      VARCHAR2(100);
BEGIN
  lsHexa := '';
  lnDecimal := pnDecimal;
  
  IF(lnDecimal=0) THEN
    RETURN '00';
  END IF;  
  
  WHILE lnDecimal > 0 LOOP
    lnResto := Mod(lnDecimal, 16);
    lnDecimal := trunc(lnDecimal / 16);
    
    IF(lnResto < 10) THEN
      lsChr := to_Char(lnResto);
    ELSE
      lsChr := chr(lnResto + 55);
    END IF;
    
    lsHexa := lsChr || lsHexa;
  END LOOP; 
  
  RETURN LPAD(lsHexa, 2, '0');
END FAC_FN_DEC2HEX;

/**************************************************************************
Descripcion       : Convierte Decimal a Binario
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_DEC2BIN
  (pnInteger          NUMBER)
RETURN VARCHAR2
IS
  lnInteger   NUMBER;
  lnResto     NUMBER;
  lsChr       VARCHAR2(10);
  lsBinario   VARCHAR2(100);
BEGIN
  lsBinario := '';
  lnInteger := pnInteger;
  
  IF(pnInteger = 0) THEN
    RETURN '00000000';
  END IF;
  
  WHILE lnInteger > 0 LOOP
    lsBinario := TO_CHAR(mod(lnInteger, 2)) || lsBinario;
    lnInteger := TRUNC(lnInteger / 2);
  END LOOP; 
  
  RETURN LPAD(lsBinario, 8, '0');
END FAC_FN_DEC2BIN;

/**************************************************************************
Descripcion       : Convierte Binario a Decimal
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psEntrada        :     Dato Binario

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_BIN2DEC
  (psEntrada       VARCHAR2)
RETURN NUMBER
IS
  lnRetVal      NUMBER;
  lnLoop        NUMBER;

BEGIN

  lnRetVal := 0;
  For lnLoop IN 1..7 LOOP
    lnRetVal := lnRetVal + power(2 * TO_NUMBER(SubStr(psEntrada, lnLoop, 1)),(8 - lnLoop));
  END LOOP;

  RETURN (lnRetVal + TO_NUMBER(SubStr(psEntrada, 8, 1)));

END FAC_FN_BIN2DEC;

/**************************************************************************
Descripcion       : Convierte Decimal a Binario
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_BITXOR
  (pnXBin          VARCHAR2,
   pnYBin          VARCHAR2)
RETURN NUMBER
IS
  lnPosX      NUMBER;
  lnPosY      NUMBER;
  lsBinario   VARCHAR2(100);
BEGIN
  lsBinario := '';
  
  FOR iCount IN 1..8 LOOP 
    lnPosX := Substr(pnXBin, iCount, 1);
    lnPosY := Substr(pnYBin, iCount, 1);
    
    If (lnPosX=lnPosY) Then
      lsBinario := lsBinario || '0';
    Else
      lsBinario := lsBinario  || '1';
    END IF;
    
  END LOOP;

  RETURN FAC_FN_BIN2DEC(lsBinario);
  
END FAC_FN_BITXOR;

/**************************************************************************
Descripcion       : Un algoritmo de criptografía simétrica, basado en cifrado 
                    de flujo (stream cipher)
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  psMensaje        :  Mensaje a Cifrar
  psClave          :  Clave a usar para cifrar

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_ALLEGED_RC4
  (psMensaje          VARCHAR2,
   psClave            VARCHAR2)
RETURN VARCHAR2
IS
  X      Number;
  Y      Number;
  Index1 Number;
  Index2 Number;
  
  lsMensajeCif  VARCHAR2(1000);
  lsCaracter    VARCHAR2(50);
  lnValor1      NUMBER;
  lnValor2      NUMBER;
  lnMen         NUMBER;
  type vs IS VARRAY(256) OF INTEGER;
  State vs := vs();

BEGIN
  
  X:=0; 
  Y:=0;
  Index1:=0;
  Index2:=0;
  lnMen:=0;
  lsMensajeCif:='';

  For i IN 0..255 LOOP
    State.extend;
    State(i+1):=i;
  END LOOP;

  For i IN 0..255 LOOP
    lsCaracter:=ASCII(Substr(psClave,Index1+1,1));
    Index2:=Mod(lsCaracter+State(i+1)+Index2,256);
    --Intercambiar los valores
    lnValor1:=State(i+1);
    lnValor2:=State(Index2+1);
    State(i+1):=lnValor2;
    State(Index2+1):=lnValor1;
    Index1:=Mod((Index1+1),Length(psClave));
  END LOOP;

  For i IN 0..Length(psMensaje)-1 LOOP

    X:= Mod((X+1),256);
    Y:= Mod((State(X+1)+Y),256);
    --Intercambiar los Valores
    lnValor1:=State(X+1);
    lnValor2:=State(Y+1);
    State(X+1):=lnValor2;
    State(Y+1):=lnValor1;

    lsCaracter:=ASCII(Substr(psMensaje,i+1,1));  
    lnMen:=FAC_FN_BITXOR(FAC_FN_DEC2BIN(lsCaracter),
                                       FAC_FN_DEC2BIN(State(Mod(State(X+1)+State(Y+1),256)+1)));
    lsMensajeCif:=lsMensajeCif || '-' || FAC_FN_DEC2HEX(lnMen);
  END LOOP;

  RETURN Substr(lsMensajeCif, 2, Length(lsMensajeCif));

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR FAC_FN_ALLEGED_RC4: ' || ls_sqlerrm);

END FAC_FN_ALLEGED_RC4;

/**************************************************************************
Descripcion       : El algoritmo de Base 64, se trata sencillamente de pasar 
                    un número en base 10 a base 64
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  pnNumero        :  Numero de Entrada

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_BASE64
  (pnNumero          NUMBER)
RETURN VARCHAR2
IS
  lnNumero      NUMBER;
  lnCociente    NUMBER;
  lnResto       NUMBER;
  lsPalabra     VARCHAR2(200);
  lsDiccionario VARCHAR2(100):='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/';
BEGIN
  
  lnNumero:=pnNumero;
  lnCociente:=1;
  lnResto:=0;
  lsPalabra:='';
  
  WHILE (lnCociente>0) LOOP
    lnCociente:= TRUNC(lnNumero/64);
    lnResto:= Mod(lnNumero,64);
    lsPalabra:=Substr(lsDiccionario,lnResto+1,1) || lsPalabra;
    lnNumero:=lnCociente;
  END LOOP;

  RETURN lsPalabra;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR FAC_FN_BASE64: ' || ls_sqlerrm);

END FAC_FN_BASE64;


/**************************************************************************
Descripcion       : Se requiere contar con una función que retorne un dato 
                   alfanumérico el cual constituye el código de control, este 
                   código es la representación única de una factura
Fecha Creacion    : 30/10/2014
Parametros Entrada:
  pnMontoFactura  :  Monto Factura
  pnCodigoAutorizacion  :  Codigo de Autorizacion
  pnNumeroFactura  :  Numero de Factura
  pnNumeroNIT     :  Numero NIT
  psFechaProceso  :  Fecha proceso (Formato: dd/MM/yyyy)
  psLlave         :  Llave

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION FAC_FN_GENER_CODIG_CTROL
  (pnMontoFactura          NUMBER,
   pnCodigoAutorizacion    NUMBER,
   pnNumeroFactura         NUMBER,
   pnNumeroNIT             NUMBER,
   psFechaProceso          VARCHAR2,
   psLlave                 VARCHAR2)
RETURN VARCHAR2
IS
  lnMonto       NUMBER;
  lsMonto       VARCHAR2(50);
  lnAuto        NUMBER;
  lsFactura     VARCHAR2(50);
  lsNIT         VARCHAR2(50);
  lnFecha       NUMBER;

  lnTotalBase1  NUMBER;
  lnTotalBase2  NUMBER;
  lsBase64      VARCHAR2(200);
  
  lsTotalBase1  VARCHAR2(100);
  ls5Digitos    VARCHAR2(5);
  lsCaracter    VARCHAR2(1);
  lnLongitud    NUMBER;
  lsParrafo     VARCHAR2(100);
  lsCadena      VARCHAR2(1000);
  lsCadena2     VARCHAR2(1000);
  
  lnPosicion    NUMBER;
  lnSumatoria1  NUMBER;
  lnSumatoria2  NUMBER;
  lnSumatoria3  NUMBER;
  lnSumatoria4  NUMBER;
  lnSumatoria5  NUMBER;
  lnSumatoria   NUMBER;
  lnResultado   NUMBER;
  lnTotal       NUMBER;
  
BEGIN
  
  lnMonto:=ROUND(pnMontoFactura);
  
  lsFactura:=TO_CHAR(pnNumeroFactura);
  lsFactura:=lsFactura||FAC_FN_VERHOEFF(lsFactura);
  lsFactura:=lsFactura||FAC_FN_VERHOEFF(lsFactura);

  lsNIT:=TO_CHAR(pnNumeroNIT);
  lsNIT:=lsNIT||FAC_FN_VERHOEFF(lsNIT);
  lsNIT:=lsNIT||FAC_FN_VERHOEFF(lsNIT);

  lnFecha:=TO_CHAR(TO_DATE(psFechaProceso, 'dd/MM/yyyy'),'yyyyMMdd');
  lnFecha:=TO_NUMBER(TO_CHAR(lnFecha)||FAC_FN_VERHOEFF(lnFecha));
  lnFecha:=TO_NUMBER(TO_CHAR(lnFecha)||FAC_FN_VERHOEFF(lnFecha));

  lsMonto:=TO_CHAR(lnMonto);
  lsMonto:=lsMonto||FAC_FN_VERHOEFF(lsMonto);
  lsMonto:=lsMonto||FAC_FN_VERHOEFF(lsMonto);
  
  lnTotalBase1:=TO_NUMBER(lsFactura)+TO_NUMBER(lsNIT)+lnFecha+TO_NUMBER(lsMonto);
  lnTotalBase1:=TO_NUMBER(TO_CHAR(lnTotalBase1)||FAC_FN_VERHOEFF(lnTotalBase1));
  lnTotalBase1:=TO_NUMBER(TO_CHAR(lnTotalBase1)||FAC_FN_VERHOEFF(lnTotalBase1));
  lnTotalBase1:=TO_NUMBER(TO_CHAR(lnTotalBase1)||FAC_FN_VERHOEFF(lnTotalBase1));
  lnTotalBase1:=TO_NUMBER(TO_CHAR(lnTotalBase1)||FAC_FN_VERHOEFF(lnTotalBase1));
  lnTotalBase1:=TO_NUMBER(TO_CHAR(lnTotalBase1)||FAC_FN_VERHOEFF(lnTotalBase1));
  
  lsTotalBase1:=TO_CHAR(lnTotalBase1);
  ls5Digitos:=SUBSTR(TO_CHAR(lsTotalBase1),length(lsTotalBase1)-4);
  
  lnPosicion := 1;
  FOR i IN 1..5 LOOP
    lsCaracter:=substr(ls5Digitos,i,1);
    lnLongitud:=TO_NUMBER(lsCaracter)+1;
    
    lsParrafo:=substr(psLlave, lnPosicion, lnLongitud);
    lnPosicion:=lnPosicion+lnLongitud;
    
    IF(i=1) THEN
      lsCadena:=lsCadena||TO_CHAR(pnCodigoAutorizacion)||lsParrafo;
    END IF;
    IF(i=2) THEN
      lsCadena:=lsCadena||lsFactura||lsParrafo;
    END IF;
    IF(i=3) THEN
      lsCadena:=lsCadena||lsNIT||lsParrafo;
    END IF;
    IF(i=4) THEN
      lsCadena:=lsCadena||TO_CHAR(lnFecha)||lsParrafo;
    END IF;
    IF(i=5) THEN
      lsCadena:=lsCadena||lsMonto||lsParrafo;
    END IF;
  END LOOP;
  
  lsCadena2:=FAC_FN_ALLEGED_RC4(lsCadena, psLlave||ls5Digitos);
  lsCadena2:=replace(lsCadena2,'-','');
  
  lnSumatoria1:=0;
  lnSumatoria2:=0;
  lnSumatoria3:=0;
  lnSumatoria4:=0;
  lnSumatoria5:=0;
  lnSumatoria:=0;
  
  FOR i in 1..length(lsCadena2) LOOP
    lnResultado:=mod(i,5);
    
    IF(lnResultado=1) THEN
      lnSumatoria1:=lnSumatoria1+ASCII(substr(lsCadena2,i,1));
    END IF;  
    IF(lnResultado=2) THEN
      lnSumatoria2:=lnSumatoria2+ASCII(substr(lsCadena2,i,1));
    END IF;
    IF(lnResultado=3) THEN
      lnSumatoria3:=lnSumatoria3+ASCII(substr(lsCadena2,i,1));
    END IF;
    IF(lnResultado=4) THEN
      lnSumatoria4:=lnSumatoria4+ASCII(substr(lsCadena2,i,1));
    END IF;
    IF(lnResultado=0) THEN
      lnSumatoria5:=lnSumatoria5+ASCII(substr(lsCadena2,i,1));
    END IF;
  
  END LOOP;
  
  lnSumatoria:=lnSumatoria1+lnSumatoria2+lnSumatoria3+lnSumatoria4+lnSumatoria5;
  
  lnTotal:=TRUNC((lnSumatoria*lnSumatoria1)/(TO_NUMBER(SUBSTR(ls5Digitos,1,1))+1)) +
            TRUNC((lnSumatoria*lnSumatoria2)/(TO_NUMBER(SUBSTR(ls5Digitos,2,1))+1)) +
            TRUNC((lnSumatoria*lnSumatoria3)/(TO_NUMBER(SUBSTR(ls5Digitos,3,1))+1)) +
            TRUNC((lnSumatoria*lnSumatoria4)/(TO_NUMBER(SUBSTR(ls5Digitos,4,1))+1)) +
            TRUNC((lnSumatoria*lnSumatoria5)/(TO_NUMBER(SUBSTR(ls5Digitos,5,1))+1));
  
  lsBase64:=FAC_FN_BASE64(lnTotal);

  RETURN FAC_FN_ALLEGED_RC4(lsBase64, psLlave || ls5Digitos);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR FAC_FN_GENER_CODIG_CTROL: ' || ls_sqlerrm);

END FAC_FN_GENER_CODIG_CTROL;

end FAC_PKG_GENER;
/
