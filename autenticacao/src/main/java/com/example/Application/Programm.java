package com.example.Application;

import java.util.Scanner;

import com.example.Exception.CodigoException;
import com.example.Exception.EmailException;
import com.example.Exception.SenhaException;
import com.example.Service.Autenticacao;

public class Programm {
    public static void main(String[] args) {
        Scanner dados = new Scanner(System.in);
        
        Autenticacao autenticacao = new Autenticacao();

        int opcao;

        do{

            System.out.println("****************************");
            System.out.println("******** MENU DE OPÇÕES *****");
            System.out.println("****************************");
    
            System.out.println("(1) Criar cadastro");
            System.out.println("(2) Fazer login");
            System.out.println("(3) Sair");
            opcao = dados.nextInt();
            dados.nextLine();

            switch (opcao) {
                case 1:
                    try{
                        autenticacao.criarCadastro();
                    }catch(SenhaException e){
                        System.out.println("Erro. " + e.getMessage());
                    }catch(EmailException e){
                        System.out.println("Erro. " + e.getMessage());
                    }                    
                    break;
                case 2:
                    try{
                        autenticacao.fazerLogin();
                    }catch(EmailException e){
                        System.out.println("Erro. " + e.getMessage());
                    }catch(SenhaException e){
                        System.out.println("Erro. " + e.getMessage());
                    }catch(CodigoException e){
                        System.out.println("Erro. " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }


        }while(opcao!= 3);

        dados.close();
    }

}
