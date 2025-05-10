package com.example.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {
    
    public static void enviarCodigo(String destino, String codigo) {
        
        // Endereço de e-mail do remetente (o e-mail que vai enviar o código)
        final String remetente = "seu.email@gmail.com";

        // Senha do aplicativo (gerada no Google – não é a senha do e-mail pessoal!)
        final String senha = "sua-senha-de-aplicativo";

        // Cria um objeto de propriedades para configurar a conexão com o servidor SMTP
        Properties props = new Properties();

        // Informa que a autenticação no servidor SMTP será usada
        props.put("mail.smtp.auth", "true");

        // Habilita o protocolo de segurança STARTTLS (criptografia)
        props.put("mail.smtp.starttls.enable", "true");

        // Define o servidor SMTP a ser usado (Gmail, neste caso)
        props.put("mail.smtp.host", "smtp.gmail.com");

        // Define a porta do servidor SMTP (587 é padrão para STARTTLS)
        props.put("mail.smtp.port", "587");

        // Cria uma sessão de e-mail com autenticação
        Session session = Session.getInstance(props,
            new Authenticator() {
                // Fornece o e-mail e a senha para autenticação
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remetente, senha);
                }
            });

        try {
            // Cria a mensagem do tipo MIME (padrão para e-mails)
            Message message = new MimeMessage(session);

            // Define o e-mail do remetente
            message.setFrom(new InternetAddress(remetente));

            // Define o e-mail do destinatário (usuário que vai receber o código)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));

            // Define o assunto do e-mail
            message.setSubject("Seu código de verificação");

            // Define o corpo (conteúdo) do e-mail, contendo o código
            message.setText("Seu código de verificação é: " + codigo);

            // Envia a mensagem para o servidor SMTP
            Transport.send(message);

            System.out.println("Código enviado para o e-mail.");
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}
