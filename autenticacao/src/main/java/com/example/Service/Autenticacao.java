package com.example.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.example.Exception.EmailException;
import com.example.Exception.SenhaException;

public class Autenticacao {
    Scanner dados = new Scanner(System.in);
    
    private Map<String, String> usuarios;

    public Autenticacao(){
        this.usuarios = new HashMap<String, String>();
    }

    public Map<String, String> getUsuarios() {
        return usuarios;
    }

    public void criarCadastro(){
        System.out.println("Informe um email:");
        String email = dados.nextLine();

        System.out.println("Informe uma senha:");
        String senha = dados.nextLine();
        verificaTamanhoSenha(senha);

        usuarios.put(email, senha);
        System.out.println("Email cadastrado com sucesso.");
        
    }

    public void fazerLogin(){
        System.out.println("Informe o email cadastrado:");
        String email = dados.nextLine();
        verificarEmail(email);

        System.out.println("Digite sua senha:");
        String senha = dados.nextLine();
        verificarSenha(senha);

        System.out.println("Login realizado com sucesso.");
    }

    private void verificarSenha(String senha){

        boolean isExist = usuarios.entrySet().stream()
            .anyMatch(s -> s.getValue().equals(senha));

        if(!isExist){
            throw new SenhaException("Senha incorreta");
        }    
    }

    private void verificarEmail(String email){

        boolean isExist = usuarios.entrySet().stream()
            .anyMatch(e -> e.getKey().equals(email));

       if(!isExist){
            throw new EmailException("Email informado não cadastrado.");
       }     
            
    }

    private void verificaTamanhoSenha(String senha){
        if(senha.length() < 8){
            throw new SenhaException("A senha tem que ter no mínino 8 caractéres.");
        }

    }

}