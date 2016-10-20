CREATE OR REPLACE FUNCTION "FN_CU_ABONOS_MONE_DAT_32" (
 idzona_par in varchar,
 idf_emi_dde in varchar := NULL,
 idf_emi_hta in varchar := NULL,
 idf_factu_par in varchar := NULL 
)
return number
is
INDICADOR NUMBER;
aux VARCHAR2(200):='';
AUX2 VARCHAR2(32767);
BEGIN
             IF idf_emi_hta IS NOT NULL THEN
                        aux:='AND hmc.FEC_MOVI - hmc.FEC_DOCU <= '|| idf_emi_hta;
             END IF;

AUX2 := 'SELECT SUM(IMP_PEND) ' || 
         'FROM( ' ||
             '(SELECT mcc.IMP_PEND as IMP_PEND ' ||
            'FROM CCC_MOVIM_CUENT_CORRI mcc ' ||  
             'WHERE ' || 
                'EXISTS(SELECT zs.OID_SECC FROM zon_secci zs ' || 
                       'where zs.OID_SECC = mcc.ZSCC_OID_SECC ' || 
                        'AND zs.zzon_oid_zona = :1 ) ' ||  
               'AND mcc.IMP_PAGO <> 0 ' ||
              'AND mcc.FEC_ULTI_MOVI = TO_DATE(:2,' || '''yyyy-MM-dd''' || ')) ' ||   
          'UNION ' ||     
            '(SELECT hmc.IMP_PEN as IMP_PEND ' ||  
               'FROM ' || 
                '(SELECT mcc.OID_MOVI_CC FROM  CCC_MOVIM_CUENT_CORRI mcc ' ||
                 'WHERE EXISTS(SELECT zs.OID_SECC FROM zon_secci zs ' || 
                                      'where zs.OID_SECC = mcc.ZSCC_OID_SECC ' || 
                                        'AND zs.zzon_oid_zona = :3 ) ' || 
                 ') movim, ' ||     
                 'CCC_HISTO_MOVIM_CC hmc ' ||
               'WHERE hmc.MVCC_OID_MOVI_CC = movim.OID_MOVI_CC ' ||  
                 'AND hmc.IMP_PAGO <> 0 ' ||
                 'AND (hmc.FEC_MOVI - hmc.FEC_DOCU > :4 ' || aux || '))) '; 
EXECUTE IMMEDIATE AUX2 INTO INDICADOR
         USING idzona_par,idf_factu_par,idzona_par,idf_emi_dde;
RETURN INDICADOR;
end;
/

