package org.milan.timer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

/**
 * @author Milan Rathod
 */
public class NewsletterTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Email sent at: "
                + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
    }
}
