CREATE OR REPLACE TRIGGER "SOCA_BIU_TR" BEFORE
INSERT
OR UPDATE OF "FEC_PROG_FACT" ON "PED_SOLIC_CABEC" FOR EACH ROW
 
when (
new.FEC_PROG_FACT IS NOT NULL
      )
DECLARE
    dia number(2);
    mes number(2);
    ano number(4);
    fecha date;
    fechaStr varchar(8);
BEGIN
  fecha := :new.FEC_PROG_FACT;
  dia := EXTRACT (DAY FROM fecha);
  mes := EXTRACT (MONTH FROM fecha);
  ano := EXTRACT (YEAR FROM fecha);
  fechaStr := ano;
  if (mes < 10) then fechaStr := fechaStr || '0'; end if;
  fechaStr := fechaStr || mes;
  if (dia < 10) then fechaStr := fechaStr || '0'; end if;
  fechaStr := fechaStr || dia;
  :new.FEC_PROG_FACT_COMP := to_number(fechaStr);
END;
/

