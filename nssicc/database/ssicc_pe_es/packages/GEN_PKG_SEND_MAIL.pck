CREATE OR REPLACE PACKAGE GEN_PKG_SEND_MAIL AS

  TYPE attach_info IS RECORD (
        attach_name     VARCHAR2(40),
        data_type       VARCHAR2(4000) DEFAULT 'text/plain',
        attach_content  cLOB DEFAULT NULL
    );

  TYPE array_attachments IS TABLE OF attach_info;
  
  TYPE attach_info_blob IS RECORD (
        attach_name     VARCHAR2(40),
        data_type       VARCHAR2(4000) DEFAULT 'text/plain',
        attach_content  bLOB DEFAULT NULL
    );

  TYPE array_attachments_blob IS TABLE OF attach_info_blob;  

  PROCEDURE SEND_MAIL (
        v_from_name    VARCHAR2,
        v_to_name      VARCHAR2,
        v_subject      VARCHAR2,
        v_message_body CLOB,
        v_cc_name      VARCHAR2 DEFAULT '',
        attachments    array_attachments DEFAULT NULL,
        v_message_type VARCHAR2 DEFAULT 'text/plain'
    );
    
  PROCEDURE send_mail2 ( p_from           IN VARCHAR2,
                         p_to            IN VARCHAR2,
                         p_cc             VARCHAR2 DEFAULT '',                       
                         p_subject        IN VARCHAR2,
                         v_message_body   CLOB DEFAULT NULL,
                         v_message_type   VARCHAR2 DEFAULT 'text/plain',
                         attachments    array_attachments_blob DEFAULT NULL,
                         p_smtp_host   IN VARCHAR2,
                         p_smtp_port   IN NUMBER DEFAULT 25
                        );

END GEN_PKG_SEND_MAIL;
/
CREATE OR REPLACE PACKAGE BODY GEN_PKG_SEND_MAIL AS

/*
* Created By    : TI-PE
* Creation Date : 20160210
*/

  PROCEDURE SEND_MAIL(
        v_from_name    VARCHAR2,
        v_to_name      VARCHAR2,
        v_subject      VARCHAR2,
        v_message_body CLOB,
        v_cc_name      VARCHAR2 DEFAULT '',
        attachments    array_attachments DEFAULT NULL,
        v_message_type VARCHAR2 DEFAULT 'text/plain'
    ) AS

    v_smtp_server       VARCHAR2(14)  := '172.16.228.81';
    n_smtp_server_port  NUMBER        := 25;
    conn                utl_smtp.connection;
    v_boundry           VARCHAR2(50)  := '----=*#abc1234321cba#*=';--'SECBOUND';
    n_offset            NUMBER        := 0;
    n_amount            pls_integer    := 32767;
    v_final_to_name     CLOB          := '';
    v_final_cc_name     CLOB          := '';
    v_mail_address      VARCHAR2(100);
  BEGIN


    conn := utl_smtp.open_connection(v_smtp_server,n_smtp_server_port);
    utl_smtp.helo(conn, v_smtp_server);
    utl_smtp.mail(conn, v_from_name);

    -- Add all recipient
    v_final_to_name := v_to_name;
    v_final_to_name := replace(v_final_to_name, ' ');
    v_final_to_name := replace(v_final_to_name, ',', ';');
    LOOP
      n_offset := n_offset + 1;
      v_mail_address := regexp_substr(v_final_to_name, '[^;]+', 1, n_offset);
      EXIT WHEN v_mail_address IS NULL;
      utl_smtp.rcpt(conn, v_mail_address);
    END LOOP;

    -- Add all recipient
    v_final_cc_name := v_cc_name;
    v_final_cc_name := replace(v_final_cc_name, ' ');
    v_final_cc_name := replace(v_final_cc_name, ',', ';');
    n_offset := 0;
    LOOP
      n_offset := n_offset + 1;
      v_mail_address := regexp_substr(v_final_cc_name, '[^;]+', 1, n_offset);
      EXIT WHEN v_mail_address IS NULL;
      utl_smtp.rcpt(conn, v_mail_address);
    END LOOP;

  -- Open data
    utl_smtp.open_data(conn);

  -- Message info
    utl_smtp.write_data(conn, 'To: ' || v_final_to_name || UTL_TCP.crlf);
    utl_smtp.write_DATA(conn, 'Cc: ' || v_final_cc_name || UTL_TCP.crlf);
    utl_smtp.write_data(conn, 'Date: ' || to_char(sysdate, 'Dy, DD Mon YYYY hh24:mi:ss') || UTL_TCP.crlf);
    utl_smtp.write_data(conn, 'From: ' || v_from_name || UTL_TCP.crlf);
    utl_smtp.write_data(conn, 'Subject: ' || v_subject || UTL_TCP.crlf);
    utl_smtp.write_data(conn, 'MIME-Version: 1.0' || UTL_TCP.crlf);
    utl_smtp.write_data(conn, 'Content-Type: multipart/mixed; boundary="' || v_boundry || '"' || UTL_TCP.crlf || UTL_TCP.crlf);

  -- Message body
    utl_smtp.write_data(conn, '--' || v_boundry || UTL_TCP.crlf);
    utl_smtp.write_data(conn, 'Content-Type: ' || v_message_type || UTL_TCP.crlf || UTL_TCP.crlf);
    utl_smtp.write_data(conn, v_message_body || UTL_TCP.crlf);

  -- Attachment Part
    IF attachments IS NOT NULL
    THEN
        FOR i IN attachments.FIRST .. attachments.LAST
        LOOP
        -- Attach info
            utl_smtp.write_data(conn, '--' || v_boundry || UTL_TCP.crlf);
            utl_smtp.write_data(conn, 'Content-Type: ' || attachments(i).data_type
                                || ' name="'|| attachments(i).attach_name || '"' || UTL_TCP.crlf);
            utl_smtp.write_data(conn, 'Content-Disposition: attachment; filename="'
                                || attachments(i).attach_name || '"' || UTL_TCP.crlf || UTL_TCP.crlf);

        -- Attach body
            n_offset := 1;
            WHILE n_offset < dbms_lob.getlength(attachments(i).attach_content)
            LOOP
                utl_smtp.write_data(conn, dbms_lob.substr(attachments(i).attach_content, n_amount, n_offset));
                n_offset := n_offset + n_amount;
            END LOOP;

            utl_smtp.write_data(conn, '' || UTL_TCP.crlf);
        END LOOP;
    END IF;
  -- Last boundry
    utl_smtp.write_data(conn, '--' || v_boundry || '--' || UTL_TCP.crlf);

  -- Close data
    utl_smtp.close_data(conn);
    utl_smtp.quit(conn);

  END SEND_MAIL;
  
PROCEDURE send_mail2 ( p_from           IN VARCHAR2,
                       p_to            IN VARCHAR2,
                       p_cc            VARCHAR2 DEFAULT '',                       
                       p_subject        IN VARCHAR2,
                       v_message_body    CLOB DEFAULT NULL,
                       v_message_type  VARCHAR2 DEFAULT 'text/plain',
                       attachments   array_attachments_blob DEFAULT NULL,
                       p_smtp_host   IN VARCHAR2,
                       p_smtp_port   NUMBER DEFAULT 25
                      )
AS
  l_mail_conn   UTL_SMTP.connection;
  l_boundary    VARCHAR2(50) := '----=*#abc1234321cba#*=';
  l_step        PLS_INTEGER  := 12000; -- make sure you set a multiple of 3 not higher than 24573
  n_offset            NUMBER        := 0;  
  v_final_to_name     CLOB          := '';
  v_final_cc_name     CLOB          := '';
  v_mail_address      VARCHAR2(100);
  
BEGIN
  
  
  l_mail_conn := UTL_SMTP.open_connection(p_smtp_host, p_smtp_port);
 
  UTL_SMTP.helo(l_mail_conn, p_smtp_host);
  UTL_SMTP.mail(l_mail_conn, p_from);
  
    -- Add all recipient
    v_final_to_name := p_to;
    v_final_to_name := replace(v_final_to_name, ' ');
    v_final_to_name := replace(v_final_to_name, ',', ';');
    LOOP
      n_offset := n_offset + 1;
      v_mail_address := regexp_substr(v_final_to_name, '[^;]+', 1, n_offset);
      EXIT WHEN v_mail_address IS NULL;
      utl_smtp.rcpt(l_mail_conn, v_mail_address);
    END LOOP;
  
      -- Add all recipient
    v_final_cc_name := p_cc;
    v_final_cc_name := replace(v_final_cc_name, ' ');
    v_final_cc_name := replace(v_final_cc_name, ',', ';');
    n_offset := 0;
    LOOP
      n_offset := n_offset + 1;
      v_mail_address := regexp_substr(v_final_cc_name, '[^;]+', 1, n_offset);
      EXIT WHEN v_mail_address IS NULL;
      utl_smtp.rcpt(l_mail_conn, v_mail_address);
    END LOOP;
    
  UTL_SMTP.rcpt(l_mail_conn, p_to);

  UTL_SMTP.open_data(l_mail_conn);

  UTL_SMTP.write_data(l_mail_conn, 'Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS') || UTL_TCP.crlf);
  UTL_SMTP.write_data(l_mail_conn, 'To: ' || v_final_to_name || UTL_TCP.crlf);
  utl_smtp.write_DATA(l_mail_conn, 'Cc: ' || v_final_cc_name || UTL_TCP.crlf);
  UTL_SMTP.write_data(l_mail_conn, 'From: ' || p_from || UTL_TCP.crlf);
  UTL_SMTP.write_data(l_mail_conn, 'Subject: ' || p_subject || UTL_TCP.crlf);
  UTL_SMTP.write_data(l_mail_conn, 'Reply-To: ' || p_from || UTL_TCP.crlf);
  UTL_SMTP.write_data(l_mail_conn, 'MIME-Version: 1.0' || UTL_TCP.crlf);
  UTL_SMTP.write_data(l_mail_conn, 'Content-Type: multipart/mixed; boundary="' || l_boundary || '"' || UTL_TCP.crlf || UTL_TCP.crlf);

-- Message body
    utl_smtp.write_data(l_mail_conn, '--' || l_boundary || UTL_TCP.crlf);
    utl_smtp.write_data(l_mail_conn, 'Content-Type: ' || v_message_type || UTL_TCP.crlf || UTL_TCP.crlf);
    utl_smtp.write_data(l_mail_conn, v_message_body || UTL_TCP.crlf);

--inicio  
    IF attachments IS NOT NULL
    THEN
        FOR i IN attachments.FIRST .. attachments.LAST
        LOOP
        -- Attach info
            utl_smtp.write_data(l_mail_conn, '--' || l_boundary || UTL_TCP.crlf);
            utl_smtp.write_data(l_mail_conn, 'Content-Type: ' || attachments(i).data_type || ' name="'|| attachments(i).attach_name || '"' || UTL_TCP.crlf);
            UTL_SMTP.write_data(l_mail_conn, 'Content-Transfer-Encoding: base64' || UTL_TCP.crlf);
            utl_smtp.write_data(l_mail_conn, 'Content-Disposition: attachment; filename="'|| attachments(i).attach_name || '"' || UTL_TCP.crlf || UTL_TCP.crlf);

        -- Attach body
        FOR j IN 0 .. TRUNC((DBMS_LOB.getlength(attachments(i).attach_content) - 1 )/l_step) LOOP
          UTL_SMTP.write_data(l_mail_conn, UTL_RAW.cast_to_varchar2(UTL_ENCODE.base64_encode(DBMS_LOB.substr(attachments(i).attach_content, l_step, j * l_step + 1))));--ok
        END LOOP;

        UTL_SMTP.write_data(l_mail_conn, UTL_TCP.crlf || UTL_TCP.crlf);--ok
           
        END LOOP;
    END IF;
 ---fin   

-- Last boundry
  UTL_SMTP.write_data(l_mail_conn, '--' || l_boundary || '--' || UTL_TCP.crlf);
  
  UTL_SMTP.close_data(l_mail_conn);

  UTL_SMTP.quit(l_mail_conn);
  
END;
  

END GEN_PKG_SEND_MAIL;
/
