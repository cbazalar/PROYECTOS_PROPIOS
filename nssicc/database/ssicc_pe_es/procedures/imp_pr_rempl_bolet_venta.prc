CREATE OR REPLACE PROCEDURE "IMP_PR_REMPL_BOLET_VENTA" (fechaFacturacion VARCHAR2,
                                                              oidTipoDocumento NUMBER)
AS

-- Variables usadas para el reemplazo
l_CLOB              CLOB;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

-- Cursor principal
CURSOR c_paquete IS
SELECT C.COD_CLIE,
       C.OID_CLIE,
       B.COR_BOLE_VENT,
       TO_NUMBER(TRIM(SUBSTR(B.TEX_BOLE_VENT, INSTR(B.TEX_BOLE_VENT, 'S/.') + 3, 20))) VALOR,
       A.VAL_TOTA_PAGA_LOCA,
       '$$$$$$$$$$$$$' VAL_ACTU,
       LPAD(A.NUM_DOCU_CONT_INTE, 13, ' ') VAL_REEM
FROM FAC_DOCUM_CONTA_CABEC A,
     IMP_TMP_BOLET_VENTA B,
     MAE_CLIEN C,
     MAE_CLIEN_DIREC D
WHERE A.CLDI_OID_CLIE_DIRE = D.OID_CLIE_DIRE
  AND D.CLIE_OID_CLIE = C.OID_CLIE
  AND C.COD_CLIE = B.COD_CLIE
  AND D.IND_DIRE_PPAL = 1
  AND D.IND_ELIM = 0
  AND A.FEC_FACT = fechaFacturacion
  AND A.TIDO_OID_TIPO_DOCU = oidTipoDocumento
  AND TO_NUMBER(TRIM(SUBSTR(B.TEX_BOLE_VENT, INSTR(B.TEX_BOLE_VENT, 'S/.') + 3, 20))) = A.VAL_TOTA_PAGA_LOCA
  AND INSTR(B.TEX_BOLE_VENT, '$$$$$$$$$$$$$') != 0;

r_paquete c_paquete%ROWTYPE;

BEGIN
  -- Iteramos sobre el cursor
/* Iteramos sobre cada una de las lineas del paquete documentario */

OPEN c_paquete;
LOOP
FETCH c_paquete INTO r_paquete;
EXIT WHEN c_paquete%NOTFOUND;
  SELECT TEX_BOLE_VENT INTO l_CLOB
  FROM IMP_TMP_BOLET_VENTA
  WHERE COR_BOLE_VENT = r_paquete.COR_BOLE_VENT FOR UPDATE;
  IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.VAL_ACTU, r_paquete.VAL_REEM, 1);
END LOOP;
CLOSE c_paquete;

END IMP_PR_REMPL_BOLET_VENTA;
/

