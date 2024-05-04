package com.tejas.googleauth.core.crons;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class logcron {
    private static final Logger log = LoggerFactory.getLogger(logcron.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}    
}
