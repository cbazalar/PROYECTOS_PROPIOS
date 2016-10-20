CREATE OR REPLACE PROCEDURE "PRI_PR_INDEN_MONTO_MINIM" IS

/**************************************************************************
Descripcion        : Identifica los pedidos inferiores al monto minimo
Fecha Creacion     : 29/08/2006
Fecha Modificacion : 09/08/2006
Parametros Entrada : 
Autor              : Lennon Shimokawa
Version          : Inicial
***************************************************************************/

/* Declaramos variables */
precioCatalogoTotal  NUMBER(12,2);
oidSolicitudCabecera NUMBER(12);
numeroSolicitud     NUMBER(10);

CURSOR ccSolicitudCabecera IS
SELECT P.OID_SOLI_CABE, P.VAL_NUME_SOLI, P.VAL_PREC_CATA_TOTA_LOCA
  FROM PED_SOLIC_CABEC P
  WHERE
      P.IND_OC=1
  AND  P.IND_TS_NO_CONSO = 1
  AND  P.GRPR_OID_GRUP_PROC = 1
  AND  P.ESSO_OID_ESTA_SOLI = (SELECT OID_ESTA_SOLI FROM PED_ESTAD_SOLIC WHERE COD_ESTA_SOLI = 'BL');

rrSolicitudCabecera ccSolicitudCabecera%ROWTYPE;
  
BEGIN
  OPEN ccSolicitudCabecera;
  LOOP
  FETCH ccSolicitudCabecera INTO rrSolicitudCabecera;
  EXIT WHEN ccSolicitudCabecera%NOTFOUND;
     BEGIN
        DBMS_OUTPUT.PUT_LINE('INICIO');
    END;
     END LOOP;
     CLOSE ccSolicitudCabecera;
END PRI_PR_INDEN_MONTO_MINIM;
/

