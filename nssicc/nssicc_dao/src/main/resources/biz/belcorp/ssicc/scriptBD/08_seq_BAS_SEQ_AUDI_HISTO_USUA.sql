-- Create sequence 
drop sequence BAS_SEQ_AUDI_HISTO_USUA;
create sequence BAS_SEQ_AUDI_HISTO_USUA
minvalue 1
maxvalue 99999999
start with 1000
increment by 1
cache 20;