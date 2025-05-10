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

    public void showUsuarios(){

        if(usuarios.isEmpty()){
            throw new SenhaException("Nenhum usuário cadastrado.");
        }

        usuarios.entrySet().stream()
            .forEach(System.out::println);
    }

    public void criarCadastro(){
        System.out.println("Informe um email:");
        String email = dados.nextLine();
        existeEmail(email);
        verificaFormatoEmail(email);
        

        System.out.println("Informe uma senha:");
        String senha = dados.nextLine();
        verificaFormatoSenha(senha);

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

        // String codigo = String.valueOf((int)(Math.random() * 900000) + 100000);
        // EmailService.enviarCodigo(senha, codigo);

        // System.out.println("Informe o código recebido pelo email cadastrado.");
        // String codigoInformado = dados.nextLine();

        // if(!codigoInformado.equals(codigo)){
        //     throw new CodigoException("Código inválido");
        // }

        // System.out.println("Acesso autorizado com dois fatores.");
        
    }

    private void existeEmail(String email){

        boolean isExist = usuarios.entrySet().stream()
            .anyMatch(e -> e.getKey().equals(email));

        if(isExist){
            throw new EmailException("Email já cadastrado, efetue o login.");
        }    
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

    private void verificaFormatoEmail(String email){
        if(!email.contains("@gmail.com")){
            throw new EmailException("Email inválido");
        }
    }

    private void verificaFormatoSenha(String senha){
        if(senha.length() < 8){
            throw new SenhaException("A senha tem que ter no mínino 8 caractéres.");
        }

       boolean temNumero = false;
       boolean temMaiuscula = false;
       boolean temMinuscula = false;
       boolean temEspecial = false;

         // Verifica cada caractere da senha
       for(char c: senha.toCharArray()){
        if(Character.isDigit(c)){
            temNumero = true;
        }else if(Character.isUpperCase(c)){
            temMaiuscula = true;
        }else if(Character.isLowerCase(c)){
            temMinuscula = true;
        }else if(!Character.isLetterOrDigit(c)){
            temEspecial = true;
        }
       }

       if(!temNumero){
            throw new SenhaException("A senha precisa conter pelo menos um número");
       }

       if(!temMaiuscula){
            throw new SenhaException("A senha precisa conter pelo menos uma letra maiúscula.");
       }

       if(!temMinuscula){
            throw new SenhaException("A senha precisa conter pelo menos uma letra minúscula.");
       }

       if(!temEspecial){
        throw new SenhaException("A senha precisa conter pelo menos um caracter especial.");
       }

    }

}