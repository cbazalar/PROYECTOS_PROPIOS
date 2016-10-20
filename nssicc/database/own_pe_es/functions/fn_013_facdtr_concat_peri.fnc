CREATE OR REPLACE FUNCTION "FN_013_FACDTR_CONCAT_PERI" (

      idperi_ini in number,

      idperi_fin in number



)

return varchar2





is



cursor c_cursor is



      SELECT OID_PERI

      FROM CRA_PERIO PER,

             (

             SELECT P.FEC_INIC, P.PAIS_OID_PAIS, P.MARC_OID_MARC, P.CANA_OID_CANA

             FROM CRA_PERIO P

             WHERE P.OID_PERI = idperi_ini

             ) FI,

             (

             SELECT P.FEC_FINA

             FROM CRA_PERIO P

             WHERE P.OID_PERI = idperi_fin

             ) FF

      WHERE PER.FEC_INIC >= FI.FEC_INIC

              AND PER.FEC_FINA >= FF.FEC_FINA

              AND PER.PAIS_OID_PAIS = FI.PAIS_OID_PAIS

              AND PER.MARC_OID_MARC = FI.MARC_OID_MARC

              AND PER.CANA_OID_CANA = FI.CANA_OID_CANA;





    v_return varchar2(8000);

    ret     varchar2(10);



  begin



    OPEN c_cursor;



      LOOP

            FETCH c_cursor INTO ret;

              exit when c_cursor%notfound;

              v_return := v_return||','||ret;

      end loop;
	  v_return := v_return||',';

      CLOSE c_cursor;

    return v_return;



end;
/

