package com.example.payroll_service.notification;

import com.example.payroll_service.model.DTO.PayrollNotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name.json}")
    public void sendEmail(PayrollNotificationDTO notification) {
        logger.info("Notification received, sending email->{}", notification);
        System.out.println("Consumer called");
        // we can implement how we want to send
    }
}
