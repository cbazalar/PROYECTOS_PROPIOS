CREATE OR REPLACE FUNCTION "OBTENER_SECUE_OFERT" (oid_cabe IN NUMBER)
RETURN OBJ_PRE_SECUE_OFER_TABLE PIPELINED IS

TYPE asoc IS REF CURSOR;
c2 asoc;
cadena varchar2(4000);
registroA OBJ_PRE_SECUE_OFER := OBJ_PRE_SECUE_OFER(null, null, null, null, null, null, null, null, null, null, null, null, null, null);

cursor c1 is
SELECT   pre_ofert_detal.OID_DETA_OFER,
   pre_catal_orden.num_orde,
   pre_ofert_detal.num_pagi_cata,
   pre_estra.cod_estr,
   pre_ofert.NUM_OFER,
         pre_ofert_detal.val_posi_pagi,
   mae_produ.COD_SAP,
   pre_ofert_detal.IND_PROD_PRIN,
   pre_ofert_detal.GOFE_OID_GRUP_OFER,
   pre_ofert_detal.VAL_CODI_VENT,
   pre_ofert_detal.VAL_TEXT_BREV,
   pre_estra.TIES_OID_TIPO_ESTR,
   pre_ofert.MFCA_OID_CABE,
   pre_ofert.OID_OFER
    FROM pre_ofert,
         pre_ofert_detal,
         mae_produ,
         pre_catal,
         pre_catal_orden,
   PRE_GRUPO_OFERT PGO,
         pre_estra
   WHERE pre_ofert.mfca_oid_cabe = oid_cabe
     AND pre_ofert_detal.ofer_oid_ofer = pre_ofert.oid_ofer
     AND pre_ofert_detal.ocat_oid_catal = pre_catal.oid_cata(+)
     AND pre_ofert.coes_oid_estr = pre_estra.oid_estr
     AND pre_ofert_detal.prod_oid_prod = mae_produ.oid_prod
     AND pre_catal_orden.ocat_oid_cata(+) = pre_catal.oid_cata
  AND pre_ofert_detal.GOFE_OID_GRUP_OFER = PGO.OID_GRUP_OFER(+)
  and (pre_estra.TIES_OID_TIPO_ESTR in (1,3,4,5,6,7) or (pre_estra.TIES_OID_TIPO_ESTR = 2 and pre_ofert_detal.IND_PROD_PRIN = 1) )
ORDER BY PRE_CATAL_ORDEN.NUM_ORDE nulls last,
         NVL (PRE_OFERT_DETAL.NUM_PAGI_CATA,0),
         NVL (PRE_OFERT_DETAL.VAL_POSI_PAGI,0),
   pre_estra.COD_ESTR asc,
   CASE WHEN (pre_estra.TIES_OID_TIPO_ESTR = 3 OR pre_estra.TIES_OID_TIPO_ESTR = 4 OR pre_estra.TIES_OID_TIPO_ESTR = 6 OR pre_estra.TIES_OID_TIPO_ESTR = 7) THEN
      pre_ofert.num_ofer
   end,
         CASE WHEN (pre_estra.TIES_OID_TIPO_ESTR = 2 )THEN
             pre_ofert_detal.IND_PROD_PRIN
      ELSE
         CASE WHEN (pre_estra.TIES_OID_TIPO_ESTR = 3 OR pre_estra.TIES_OID_TIPO_ESTR = 6 OR pre_estra.TIES_OID_TIPO_ESTR = 7) THEN
           PGO.NUM_GRUP*(-1)
       ELSE
               CASE WHEN ( pre_estra.TIES_OID_TIPO_ESTR = 4 )THEN
            to_number(PGO.IND_CNDO)
        END
       END
      END desc,
      CASE WHEN ( pre_estra.TIES_OID_TIPO_ESTR = 4 )THEN
         PGO.NUM_GRUP
     END,
   PRE_OFERT_DETAL.VAL_TEXT_BREV nulls last,
   CASE WHEN (pre_estra.TIES_OID_TIPO_ESTR =1 or pre_estra.TIES_OID_TIPO_ESTR =5 )THEN
      num_ofer
   END,
   CASE WHEN (pre_estra.TIES_OID_TIPO_ESTR =7) then
     cod_sap
      end;

begin
  for i in c1 loop
    registroA.OID_DETA_OFER := i.OID_DETA_OFER;
    registroA.NUM_ORDE := i.num_orde;
   registroA.NUM_PAGI_CATA := i.NUM_PAGI_CATA;
   registroA.COD_ESTR := i.COD_ESTR;
   registroA.NUM_OFER := i.NUM_OFER;
   registroA.VAL_POSI_PAGI := i.VAL_POSI_PAGI;
   registroA.COD_SAP := i.COD_SAP;
   registroA.IND_PROD_PRIN := i.IND_PROD_PRIN;
   registroA.GOFE_OID_GRUP_OFER := i.GOFE_OID_GRUP_OFER;
   registroA.VAL_CODI_VENT := i.VAL_CODI_VENT;
   registroA.VAL_TEXT_BREV := i.VAL_TEXT_BREV;
   registroA.TIES_OID_TIPO_ESTR := i.TIES_OID_TIPO_ESTR;
   registroA.MFCA_OID_CABE := i.MFCA_OID_CABE;
   registroA.OID_OFER := i.OID_OFER;

    PIPE ROW(registroA);

    if i.TIES_OID_TIPO_ESTR = 2 then
     cadena := 'SELECT pre_ofert_detal.OID_DETA_OFER, pre_catal_orden.num_orde Catalogo_Numero_orden, pre_ofert_detal.num_pagi_cata, pre_estra.cod_estr, pre_ofert.NUM_OFER, ';
    cadena := cadena || ' pre_ofert_detal.val_posi_pagi, mae_produ.COD_SAP, pre_ofert_detal.IND_PROD_PRIN, pre_ofert_detal.GOFE_OID_GRUP_OFER, ';
    cadena := cadena || ' pre_ofert_detal.VAL_CODI_VENT, pre_ofert_detal.VAL_TEXT_BREV, pre_estra.TIES_OID_TIPO_ESTR, pre_ofert.MFCA_OID_CABE, ';
    cadena := cadena || ' pre_ofert.OID_OFER FROM pre_ofert, pre_ofert_detal, mae_produ, pre_catal, pre_catal_orden, PRE_GRUPO_OFERT PGO, pre_estra ';
    cadena := cadena || ' WHERE pre_ofert.mfca_oid_cabe = '||i.MFCA_OID_CABE;
    cadena := cadena || ' AND pre_ofert_detal.ofer_oid_ofer = pre_ofert.oid_ofer AND pre_ofert_detal.ocat_oid_catal = pre_catal.oid_cata(+) AND pre_ofert.coes_oid_estr = pre_estra.oid_estr ';
    cadena := cadena || ' AND pre_ofert_detal.prod_oid_prod = mae_produ.oid_prod AND pre_catal_orden.ocat_oid_cata(+) = pre_catal.oid_cata  AND pre_ofert_detal.GOFE_OID_GRUP_OFER = PGO.OID_GRUP_OFER(+) ';
    cadena := cadena || ' and pre_ofert_detal.IND_PROD_PRIN = 0 and pre_estra.TIES_OID_TIPO_ESTR = 2 ';
    cadena := cadena || ' and pre_ofert.OID_OFER = '|| i.OID_OFER || 'ORDER BY pre_ofert_detal.VAL_TEXT_BREV nulls last ';
    OPEN c2 FOR cadena;
          loop
      Fetch c2 INTO registroA.OID_DETA_OFER, registroA.NUM_ORDE, registroA.NUM_PAGI_CATA, registroA.COD_ESTR, registroA.NUM_OFER, registroA.VAL_POSI_PAGI,
                registroA.COD_SAP, registroA.IND_PROD_PRIN, registroA.GOFE_OID_GRUP_OFER, registroA.VAL_CODI_VENT, registroA.VAL_TEXT_BREV,
          registroA.TIES_OID_TIPO_ESTR, registroA.MFCA_OID_CABE, registroA.OID_OFER;
      EXIT WHEN c2%NOTFOUND;
     PIPE ROW(registroA);
    end loop;
    close c2;
   end if;
  end loop;
--  close c1;
     return;
end;
/

