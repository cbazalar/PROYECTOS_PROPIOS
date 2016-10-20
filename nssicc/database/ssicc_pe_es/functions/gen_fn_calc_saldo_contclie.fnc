CREATE OR REPLACE FUNCTION "GEN_FN_CALC_SALDO_CONTCLIE" (saldo IN NUMBER,
                                                       vchComprobante IN VARCHAR2,
                                                       vchCodigo_per IN VARCHAR2,
                                                       vchPais IN VARCHAR2,
                                                       vnumSecuencia IN NUMBER)
RETURN NUMBER
IS
/************************************************************
Descripcion        : Calcula Saldo de Reporte Control Por Cliente
                     de peridos atras.
Fecha Creacion     : 18/08/2006
Fecha Modificacion :
Parametros         : vchComprobante : Numero Comprobante
             numReg    : Numero Registro
Autor              : David Toledo
Version         : 1.0
*************************************************************/
  v_Num_Result number;
  v_Num_Saldo  number;
  v_Num_Haber number;
  v_Num_Registro number;
begin

v_Num_Saldo:=saldo;

/*Innecesario el calculo del saldo*/

SELECT nro 
INTO   v_Num_Registro 
FROM (
     SELECT rownum as nro,
            PER.SER_COPE ||PER.NUM_COPE AS SER_NUM_COMP_PER,
            PER.SER_DOLE || PER.NUM_DOLE  AS SER_NUM_DOC_PGO,
          PER.COR_PECO AS COR_PECO
       FROM PER_PERCE_CONSO PER,
            MAE_CLIEN CLI,
            SEG_PAIS  PAIS
      WHERE
           PAIS.COD_PAIS   = vchPais         AND
           CLI.PAIS_OID_PAIS  = PAIS.OID_PAIS  AND
           CLI.COD_CLIE        = PER.COD_CLIE   AND
           PER.MON_PERC  > 0 AND
           PER.SER_DOLE || PER.NUM_DOLE = vchComprobante
     )b
       WHERE b.SER_NUM_COMP_PER =vchCodigo_per
    AND   b.COR_PECO = vnumSecuencia       ;

SELECT sum(PER.MON_PAGO) into v_Num_Haber
      FROM PER_PERCE_CONSO PER,
        MAE_CLIEN CLI,
        SEG_PAIS  PAIS
      WHERE
         PAIS.COD_PAIS      = vchPais         AND
         CLI.PAIS_OID_PAIS  = PAIS.OID_PAIS   AND
         CLI.COD_CLIE        = PER.COD_CLIE   AND
         PER.MON_PERC  > 0
       and PER.SER_DOLE || PER.NUM_DOLE  = vchComprobante
       and rownum<=v_Num_Registro;

  v_Num_Result := v_Num_Saldo - v_Num_Haber;

RETURN  v_Num_Result  ;

END GEN_FN_CALC_SALDO_CONTCLIE;
/

