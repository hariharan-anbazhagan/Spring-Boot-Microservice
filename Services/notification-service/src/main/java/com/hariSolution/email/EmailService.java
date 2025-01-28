package com.hariSolution.email;

import com.hariSolution.Order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hariSolution.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static com.hariSolution.email.EmailTemplates.ORDER_CONFIRMATION; // Assuming you have a different template for order confirmation

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendPaymentSuccessfully(
            String destinationEmail,
            String userName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO);
        messageHelper.setFrom("hariharananbu00@gmail.com");

        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("userName", userName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        String htmlTemplate = templateEngine.process(templateName, context);
        messageHelper.setText(htmlTemplate, true);

        messageHelper.setTo(destinationEmail);

        try {
            mailSender.send(message);
            //log.info("Payment success email sent to: {}", destinationEmail);
        } catch (Exception e) {
            //log.error("Error sending payment success email", e);
            throw e; // Rethrow or handle accordingly
        }
    }

    @Async
    public void sendOrderConfirmation(
            String destinationEmail,
            String userName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO);
        messageHelper.setFrom("hariharananbu00@gmail.com");

        final String templateName = ORDER_CONFIRMATION.getTemplate();  // Use your order confirmation template

        Map<String, Object> variables = new HashMap<>();
        variables.put("userName", userName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("productList", products);  // Correct the variable name here

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        String htmlTemplate = templateEngine.process(templateName, context);
        messageHelper.setText(htmlTemplate, true);

        messageHelper.setTo(destinationEmail);

        try {
            mailSender.send(message);
            //log.info("Order confirmation email sent to: {}", destinationEmail);
        } catch (Exception e) {
           // log.error("Error sending order confirmation email", e);
            throw e; // Rethrow or handle accordingly
        }
    }
}
