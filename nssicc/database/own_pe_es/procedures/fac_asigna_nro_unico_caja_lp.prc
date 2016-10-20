CREATE OR REPLACE PROCEDURE FAC_ASIGNA_NRO_UNICO_CAJA_LP (
    idspool IN VARCHAR2
)
AS

    -- Cursor para recorrer los documentos impresos a los cuales asignar el numero de secuencia
    CURSOR cur_docs IS
        SELECT doc.oid_docu_impr, doc.val_buff
          FROM gen_docum_impri doc
         WHERE doc.oid_docu_impr IN (SELECT doc.oid_docu_impr
                                       FROM gen_colas cola, gen_docum_impri doc
                                      WHERE cola.gspo_oid_spool = TO_NUMBER(idspool)
                                        AND cola.oid_cola = doc.gcol_oid_cola
                                     MINUS
                                     SELECT SDI.GDIM_OID_DOCU_IMPR
                                       FROM fac_secue_docum_inter sdi
                                      WHERE SDI.GSPO_OID_SPOO = TO_NUMBER(IDSPOOL));

    pos_tag_ptl NUMBER(12);                -- Posicion del tag <ptl> dentro del blob
    num_consolidado VARCHAR2(8);           -- Numero del consolidado (sin digitos de año) tomado del blob
    oid_consolidado NUMBER(12);            -- OID del consolidado
    anio VARCHAR2(2);                      -- Año actual

    pos_ini_cons NUMBER(12);               -- Posicion en el blob donde comienza el numero de consolidado
    pos_ini_caja NUMBER(12);               -- Idem para numero de caja
    pos_fin_caja NUMBER(12);               -- Posicion final del numero de caja
    numero_caja NUMBER(3);                 -- Numero de caja tratada

    secuencia VARCHAR2(12);                -- Secuencia (numero unico de caja) a escribir en el blob
    str_espacio_secuencia VARCHAR2(12);    -- String formado por '$' para buscar en el blob y reemplazar
    pos_ini_secuencia NUMBER(12);          -- Posicion en el blob donde comienza la secuencia

    blob0 BLOB;                            -- Blob auxiliar para guardar y manipular el blob obtenido de BD
    blob1 BLOB;                            -- Blob auxiliar para guardar todo lo anterior al numero de secuencia
    blob2 BLOB;                            -- Idem, para guardar todo lo posterior a dicho numero


BEGIN
    -- Obtengo año actual
    SELECT TO_CHAR(SYSDATE,'YY') INTO anio FROM DUAL;

    FOR i IN cur_docs LOOP
        BEGIN
            pos_tag_ptl := 1;
            blob0 := i.val_buff;
            DBMS_OUTPUT.PUT_LINE('Antes de empezar todo: ' || UTL_RAW.CAST_TO_VARCHAR2(blob0));

            -- Recorro el blob mientras haya listas de picado (tags <ptl>) en el
            LOOP
                pos_tag_ptl := NVL(DBMS_LOB.INSTR(blob0,UTL_RAW.CAST_TO_RAW ('<ptl>'), pos_tag_ptl),0);

                IF POS_TAG_PTL > 0 THEN
                    -- Obtener numero de consolidado
                    pos_ini_cons := DBMS_LOB.INSTR(blob0,UTL_RAW.CAST_TO_RAW ('<nbd>'), pos_tag_ptl) + 5;
                    num_consolidado := UTL_RAW.CAST_TO_VARCHAR2(DBMS_LOB.SUBSTR(blob0, 8, pos_ini_cons));

                    -- Obtener numero de caja
                    pos_ini_caja := DBMS_LOB.INSTR(blob0, UTL_RAW.CAST_TO_RAW ('<caja>'), pos_ini_cons) + 6;
                    pos_fin_caja := DBMS_LOB.INSTR(blob0, UTL_RAW.CAST_TO_RAW ('/'), pos_ini_caja);
                    numero_caja := TO_NUMBER(UTL_RAW.CAST_TO_VARCHAR2(DBMS_LOB.SUBSTR(blob0, pos_fin_caja - pos_ini_caja, pos_ini_caja)));

                    -- Busco la etiqueta a partir del consolidado (lo busco por año || num consolidado) y del numero de caja hallado
                    -- Lo formateo para que quede con tantas cifras como indica long num etiq en la linea de armado
                    SELECT LPAD(TO_CHAR(etiq.num_secu), confi.num_long_etiq, '0') INTO secuencia
                      FROM ped_solic_cabec cons
                         , ape_etiqu etiq
                         , ape_lista_picad_cabec lista
                         , ape_linea_armad linea
                         , app_confi_centr_distr confi
                     WHERE cons.val_nume_soli = TO_NUMBER(anio || num_consolidado)
                       AND cons.oid_soli_cabe = etiq.soca_oid_soli_cabe
                       AND etiq.num_caja = numero_caja
                       AND cons.oid_soli_cabe = lista.soca_oid_soli_cabe
                       AND lista.liar_oid_line_arma = linea.oid_line_arma
                       AND confi.oid_conf_cent_dist = linea.ccdi_oid_conf_cent_dist;

                    -- Adicionalmente, obtengo el string formado por tantos signos $ como la longitud de "secuencia",
                    -- que es el string que voy a buscar en el BLOB para reemplazar por el numero de secuencia
                    str_espacio_secuencia := LPAD('$', LENGTH(secuencia), '$');

                    -- Reemplazar los $$$$$$$$ por el numero de secuencia
                    pos_ini_secuencia := DBMS_LOB.INSTR(blob0, UTL_RAW.CAST_TO_RAW ('<NuniCj>'), pos_ini_cons) + 8;
                    blob1 := DBMS_LOB.SUBSTR(blob0, pos_ini_secuencia - 1 , 1); -- Todo lo anterior al numero de secuencia
                    blob2 := DBMS_LOB.SUBSTR(blob0, DBMS_LOB.GETLENGTH(blob0), pos_ini_secuencia + LENGTH(str_espacio_secuencia)); -- Todo lo posterior
                    DBMS_LOB.APPEND(blob1, UTL_RAW.CAST_TO_RAW(secuencia));
                    DBMS_LOB.APPEND(blob1, blob2);
                    blob0 := blob1;

                    -- Impactar en BD
                    UPDATE gen_docum_impri
                       SET val_buff = blob0
                     WHERE oid_docu_impr = i.oid_docu_impr;

                    -- Mover POS_TAG_PTL a una posicion posterior al ultimo tag leido (para poder seguir avanzando)
                    pos_tag_ptl := pos_tag_ptl + 5;

                ELSE
                    EXIT;
                END IF;
            END LOOP; -- Fin loop mientras el blob tenga listas de picado
        END;
    END LOOP; -- Fin loop cursor documentos imprimibles
END;
/

