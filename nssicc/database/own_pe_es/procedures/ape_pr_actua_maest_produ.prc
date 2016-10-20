CREATE OR REPLACE PROCEDURE APE_PR_ACTUA_MAEST_PRODU (
    pn_oid_pais IN VARCHAR2
)
AS

  CURSOR cur_producto IS
    SELECT mp.oid_prod               oid_produ,
           nvl(mp.val_peso,0)        val_peso,
           nvl(mp.val_dime_alto,0)   val_alto,
           nvl(mp.val_dime_larg,0)   val_largo,
           nvl(mp.val_dime_anch,0)   val_ancho

      FROM mae_produ mp
     WHERE mp.pais_oid_pais = TO_NUMBER(pn_oid_pais)
      AND NOT EXISTS ( SELECT 1 FROM ape_produ ap WHERE ap.prod_oid_prod = mp.oid_prod);

  TYPE productos IS RECORD
  (
      		oid_produ       		mae_produ.oid_prod%TYPE,
      		val_peso      			mae_produ.val_peso%TYPE,
      		val_alto   			    mae_produ.val_dime_alto%TYPE,
      		val_largo   			  mae_produ.val_dime_larg%TYPE,
      		val_ancho           mae_produ.val_dime_anch%TYPE
  );

  TYPE productosTab  IS TABLE OF productos;
  productosRecord productosTab;

  w_filas               NUMBER := 1000;
  vn_oid_tipo_disp      NUMBER;
  vn_val_pack_size      NUMBER;
  vn_val_pick_sped      NUMBER;
  vn_val_hora_inve      NUMBER;
  vn_val_holg_adic_cubi NUMBER;
  vn_val_maxi_chan_prod NUMBER;
  vn_val_scre_quty      NUMBER;
  vn_val_alto_afra      NUMBER;
  vn_val_anch_afra      NUMBER;
  vn_val_larg_afra      NUMBER;
  vn_val_lane_60        NUMBER;
  vn_val_lane_95        NUMBER;
  vn_ind_alto           NUMBER;
  vn_ind_anch           NUMBER;
  vn_ind_larg           NUMBER;
  vn_oid_tipo_anaq      NUMBER;
  vnContadorProd        NUMBER;

BEGIN

  -- Obteniendo los valores por defecto
  SELECT vda.tidi_oid_tipo_disp,
         vda.val_pack_size,
         vda.val_pick_sped,
         vda.val_hora_inve,
         vda.val_holg_adic_cubi,
         vda.val_maxi_chan_prod,
         vda.val_scre_quty
    INTO vn_oid_tipo_disp,
         vn_val_pack_size,
         vn_val_pick_sped,
         vn_val_hora_inve,
         vn_val_holg_adic_cubi,
         vn_val_maxi_chan_prod,
         vn_val_scre_quty
    FROM ape_valor_defau_afram vda;

  -- obteniendo el tipo de Anaquel por DEfecto y Normal
  SELECT ta.oid_tipo_anaq
    INTO vn_oid_tipo_anaq
    FROM ape_tipo_anaqu ta
   WHERE ta.ind_anaq_defa = 1
     AND ta.ind_tipo_afrm = 0;

  OPEN cur_producto;
    LOOP
      FETCH cur_producto BULK COLLECT INTO productosRecord LIMIT w_filas;

      IF productosRecord.COUNT > 0 THEN
        FOR x IN productosRecord.FIRST .. productosRecord.LAST LOOP
          vn_ind_alto := 0;
          vn_ind_larg := 0;
          vn_ind_anch := 0;

          -- Obteniendo el valor minimo de las dimensiones para asginarlo al valor Alto de Aframe
          IF ( ( productosRecord(x).val_alto <= productosRecord(x).val_largo) AND
               ( productosRecord(x).val_alto <= productosRecord(x).val_ancho) ) THEN
             vn_val_alto_afra := productosRecord(x).val_alto;
             vn_ind_alto := 1;
          ELSE
            IF ( ( productosRecord(x).val_largo <= productosRecord(x).val_alto) AND
                 ( productosRecord(x).val_largo <= productosRecord(x).val_ancho) ) THEN
               vn_val_alto_afra := productosRecord(x).val_largo;
               vn_ind_larg := 1;
            ELSE
               vn_val_alto_afra := productosRecord(x).val_ancho;
               vn_ind_anch := 1;
            END IF;
          END IF;

          -- Obteniendo el mayor valor de las dimensiones para asginarlo al valor Largo de Aframe
          IF ( ( productosRecord(x).val_alto >= productosRecord(x).val_largo) AND
               ( productosRecord(x).val_alto >= productosRecord(x).val_ancho) ) THEN
             vn_val_larg_afra := productosRecord(x).val_alto;
             vn_ind_alto := 1;
          ELSE
            IF ( ( productosRecord(x).val_largo >= productosRecord(x).val_alto) AND
                 ( productosRecord(x).val_largo >= productosRecord(x).val_ancho) ) THEN
               vn_val_larg_afra := productosRecord(x).val_largo;
                vn_ind_larg := 1;
            ELSE
               vn_val_larg_afra := productosRecord(x).val_ancho;
                vn_ind_anch := 1;
            END IF;
          END IF;

          -- Obteniendo el valor Ancho de Aframe
          IF ( vn_ind_alto = 0 ) THEN
            vn_val_anch_afra := productosRecord(x).val_alto;
          ELSE
            IF( vn_ind_larg = 0 ) THEN
              vn_val_anch_afra := productosRecord(x).val_largo;
            ELSE
              vn_val_anch_afra := productosRecord(x).val_ancho;
            END IF;
          END IF;

          -- Calculando  el Lane 60 y 95 en centimetros
          -- Se realiza el Ajuste para el cálculo de Val_Lane_60 y Val_Lane_95 para convertir a Milimetros.
          -- Fecha Modificacion: 16.11.2010, modificado por : Nicolás López
          IF ( vn_val_alto_afra = 0) THEN
            vn_val_lane_60 := 0;
            vn_val_lane_95 := 0;
          ELSE

            vn_val_lane_60 := TRUNC((60 * 2.54)/(vn_val_alto_afra/10));

            IF (vn_val_lane_60 > 999) THEN
               vn_val_lane_60 := 999;
            END IF;

            vn_val_lane_95 := TRUNC((95 * 2.54)/(vn_val_alto_afra/10));

            IF (vn_val_lane_95 > 999) THEN
               vn_val_lane_95 := 999;
            END IF;

          END IF;

          IF (  vn_val_lane_60 > 0 AND vn_val_lane_60 < 1 ) THEN
            vn_val_lane_60 := 1;
          END IF;

          IF (  vn_val_lane_95 > 0 AND vn_val_lane_95 < 1 ) THEN
            vn_val_lane_95 := 1;
          END IF;

          --dbms_output ('Producto: ' || productosRecord(x).oid_produ);


          -- Insertando en la tabla APE_PRODU
          INSERT INTO ape_produ (
              oid_prod,
              prod_oid_prod,
              tidi_oid_tipo_dispe,
              num_afra_pack_size,
              -- PESO
              num_afra_alto,
              num_afra_anch,
              num_afra_larg,
              num_afra_pick_sped,
              -- tich_oid_tipo_chan
              num_lane_capa_60,
              num_lane_capa_95,
              num_hora_inve,
              ticp_oid_tipo_caja_prod,
              num_unid_caja_maes,
              num_holg_adic_cubi,
              num_asig_maxi_chan,
              num_afra_scre_quty )
          VALUES (
              APE_APPR_SEQ.NEXTVAL,
              productosRecord(x).oid_produ,
              vn_oid_tipo_disp,
              vn_val_pack_size,
              -- productosRecord(x).val_peso,
              vn_val_alto_afra,
              vn_val_anch_afra,
              vn_val_larg_afra,
              vn_val_pick_sped,
              -- LANE TYPE
              vn_val_lane_60,
              vn_val_lane_95,
              vn_val_hora_inve,
              NULL,
              0,
              vn_val_holg_adic_cubi,
              vn_val_maxi_chan_prod,
              vn_val_scre_quty );

          -- Se verifica si el producto ya esta registrado en la tabla de Producto Anaquel
          SELECT COUNT(1)
            INTO vnContadorProd
            FROM ape_produ_anaqu apa
           WHERE apa.prod_oid_prod = productosRecord(x).oid_produ
             AND apa.tian_oid_tipo_anaq = vn_oid_tipo_anaq;

          IF (vnContadorProd = 0) THEN
            -- Insertando en la tabla APE_PRODU_ANAQU
            INSERT INTO ape_produ_anaqu(
              oid_prod_anaq,
              prod_oid_prod,
              num_nume_prio,
              tian_oid_tipo_anaq
            )
            VALUES(
              APE_PRAN_SEQ.NEXTVAL,
              productosRecord(x).oid_produ,
              10,
              vn_oid_tipo_anaq
            );
          END IF;

        END LOOP;
      END IF;

      EXIT WHEN (cur_producto%NOTFOUND);
    END LOOP;
  CLOSE cur_producto;

END APE_PR_ACTUA_MAEST_PRODU;
/

