CREATE OR REPLACE PROCEDURE "FAC_PR_ASIGN_NUMER_DOCUM_LEGAL" (p_codigoPais IN VARCHAR2,
                                                              p_codigoTipoDocumento IN VARCHAR2,
                                                              p_serieDocumentoLegal IN VARCHAR2,
                                                              p_numeroEjercicio IN NUMBER,
                                                              p_documentoInternoInicial IN NUMBER,
                                                              p_documentoInternoFinal IN NUMBER,
                                                              p_documentoLegalInicial IN NUMBER,
                                                              p_documentoLegalFinal IN NUMBER) AS

l_oidPais    NUMBER(12);
l_oidCanal    NUMBER(12);
l_count NUMBER;

CURSOR c_documentos IS
SELECT CAB.OID_CABE,
       CAB.NUM_DOCU_CONT_INTE,
       RUV.OID_REGI,
       p_documentoLegalInicial - 1 + rownum
FROM FAC_DOCUM_CONTA_CABEC CAB,
     FAC_REGIS_UNICO_VENTA RUV,
     FAC_TIPO_DOCUM FTD
WHERE CAB.OID_CABE = RUV.DCCA_OID_CABE
AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
AND CAB.VAL_EJER_DOCU_INTE = p_numeroEjercicio
AND CAB.VAL_SERI_DOCU_LEGA = p_serieDocumentoLegal
AND FTD.COD_TIPO_DOCU = p_codigoTipoDocumento
AND CAB.NUM_DOCU_CONT_INTE >= p_documentoInternoInicial
AND CAB.NUM_DOCU_CONT_INTE <= p_documentoInternoFinal
ORDER BY CAB.NUM_DOCU_CONT_INTE, CAB.OID_CABE;

TYPE t_oid_cabe           IS TABLE OF FAC_DOCUM_CONTA_CABEC.OID_CABE%TYPE;
TYPE t_num_docu_cont_inte IS TABLE OF FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE;
TYPE t_oid_regi           IS TABLE OF FAC_REGIS_UNICO_VENTA.OID_REGI%TYPE;
TYPE t_num_docu_lega      IS TABLE OF FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA%TYPE;

v_oid_cabe           t_oid_cabe;
v_num_docu_cont_inte t_num_docu_cont_inte;
v_oid_regi           t_oid_regi;
v_num_docu_lega      t_num_docu_lega;

rows NATURAL        := 1000;
i    BINARY_INTEGER := 0;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

BEGIN

    -- Validamos que los rangos ingresados sean correctos
    SELECT COUNT(*)
    INTO l_count
    FROM FAC_DOCUM_CONTA_CABEC CAB,
         FAC_TIPO_DOCUM FTD
    WHERE CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
    AND CAB.VAL_EJER_DOCU_INTE = p_numeroEjercicio
    AND CAB.VAL_SERI_DOCU_LEGA = p_serieDocumentoLegal
    AND FTD.COD_TIPO_DOCU = p_codigoTipoDocumento
    AND CAB.NUM_DOCU_CONT_INTE >= p_documentoInternoInicial
    AND CAB.NUM_DOCU_CONT_INTE <= p_documentoInternoFinal;

    IF (p_documentoLegalFinal - p_documentoLegalInicial + 1) != l_count THEN
        l_mensajeError := 'RANGO INGRESADO ES INCORRECTO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
    END IF;

    OPEN c_documentos;
    LOOP
        FETCH c_documentos BULK COLLECT INTO
                                v_oid_cabe,
                                v_num_docu_cont_inte,
                                v_oid_regi,
                                v_num_docu_lega LIMIT rows;
        EXIT WHEN v_oid_cabe.count = 0;

        -- Actualizamos la tabla de documentos contables
        FORALL i IN 1..v_oid_cabe.count
        UPDATE FAC_DOCUM_CONTA_CABEC cab
        SET    cab.num_docu_lega = v_num_docu_lega(i)
        WHERE cab.oid_cabe = v_oid_cabe(i);

        -- Actualizamos la tabla de registro unico de ventas
        FORALL i IN 1..v_oid_cabe.count
        UPDATE FAC_REGIS_UNICO_VENTA ruv
        SET    ruv.val_nume_docu_lega = v_num_docu_lega(i)
        WHERE ruv.oid_regi = v_oid_regi(i);

    END LOOP;
    CLOSE c_documentos;

END;
/

