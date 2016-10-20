package biz.belcorp.ssicc.service.impl;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.SchedulerDAO;
import biz.belcorp.ssicc.service.SchedulerService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="SchedulerServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("schedulerService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class SchedulerServiceImpl extends BaseService implements SchedulerService {

	/** The pais dao. */
	@Resource(name="paisDAO")
    private PaisDAO paisDAO;

	/** The scheduler dao. */
	@Resource(name="schedulerDAO")
    private SchedulerDAO schedulerDAO;
    
	/** The mail sender. */
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;

	/** The message. */
	private SimpleMailMessage message;

    /** The velocity engine. */
    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    /**
     * Gets the message.
     *
     * @return Returns the message.
     */
    public SimpleMailMessage getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message The message to set.
     */
    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }
}
