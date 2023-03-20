/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf3m212.livrariapoo;

import controller.CCliente;
import controller.CEditora;
import controller.CLivro;
import controller.CVendaLivro;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Cliente;
import util.Validadores;

/**
 *
 * @author jbferraz
 */
public class INF3M212LivrariaPOO {

    public static CCliente cadCliente = new CCliente();
    public static CEditora cadEditora = new CEditora();
    public static CLivro cadLivro = new CLivro();
    public static CVendaLivro cadCVendaLivro = new CVendaLivro();
    public static Scanner leia = new Scanner(System.in);

    public static int leiaNumInt() {
        Scanner leiaNum = new Scanner(System.in);
        int num = 99;
        try {
            num = leiaNum.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Tente Novamente!\n" + e.getMessage());
            leiaNum.nextLine();
        }
        return num;
    }

    public static void menuP() {
        System.out.println("..: Livraria :..");
        System.out.println("1 - Ger Clientes");
        System.out.println("2 - Ger Editoras");
        System.out.println("3 - Ger Livros");
        System.out.println("4 - Venda Livro");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void subMenu(int op) {
        String tpCad = null;
        switch (op) {
            case 1:
                tpCad = "Cliente";
                break;
            case 2:
                tpCad = "Editora";
                break;
            case 3:
                tpCad = "Livro";
                break;
        }
        System.out.println("Ger. " + tpCad);
        System.out.println("1 - Cadastrar " + tpCad);
        System.out.println("2 - Editar " + tpCad);
        System.out.println("3 - Listar " + tpCad + "s");
        System.out.println("4 - Deletar " + tpCad);
        System.out.println("0 - Voltar ");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarCliente() {
        int idCliente;
        String nomeCliente;
        String cpf;
        String cnpj = null;
        String endereco;
        String telefone;

        System.out.println("-- Cadastro Cliente --");
        System.out.print("Informe o CPF: ");
        boolean cpfis;
        int opCPF;
        do {
            cpf = leia.nextLine();
            cpfis = Validadores.isCPF(cpf);
            if (!cpfis) {
                System.out.print("CPF Invalido!"
                        + "\nDeseja tentar novamente? 1 - Sim || 2 - Não: ");
                opCPF = leiaNumInt();
                if (opCPF == 1) {
                    System.out.print("Informe o CPF: ");
                } else if (opCPF == 2) {
                    System.out.println("Cadastro encerrado pelo usuario!");
                    break;
                }
            }

        } while (!cpfis);

        if (cadCliente.getClienteCPF(cpf) != null) {
            System.out.println("Cliente já cadastrado!");
        } else {
            System.out.print("Informe o seu nome: ");
            nomeCliente = leia.nextLine();
            System.out.print("Informe o seu telefone: ");
            telefone = leia.nextLine();
            System.out.print("Informe o seu endereço: ");
            endereco = leia.nextLine();
            idCliente = cadCliente.geraID();

            Cliente cli = new Cliente(idCliente, nomeCliente, cpf, cnpj, endereco, telefone);
            cadCliente.addCliente(cli);
            System.out.println("Cliente cadastrado com sucesso!");
        }

    }//fim cadastrarCliente

    public static void deletarCliente() {
        System.out.println("-- Deletar Cliente --");
        System.out.print("Informe o CPF: ");
        String cpf = leia.next();

        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                cadCliente.removeCliente(cli);
                System.out.println("Cliente deletado co sucesso!");
            } else {
                System.out.println("Cliente não consta na base de dados!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }//fim deletarCliente

    public static void listarCliente() {

        for (Cliente cli : cadCliente.getClientes()) {
            System.out.println("----");
            System.out.println("CPF:\t" + cli.getCpf());
            System.out.println("Nome:\t" + cli.getNomeCliente());
            System.out.println("Fone:\t" + cli.getTelefone());
        }

    }//fim listarCliente

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        cadCliente.mockClientes();
        cadEditora.mockEditoras();
        cadLivro.mockLivros();
        cadCVendaLivro.mockVendaLivros();

        int opM;

        do {
            menuP();
            opM = leiaNumInt();
            switch (opM) {
                case 1:
                case 2:
                case 3:
                    int opSM;
                    do {
                        subMenu(opM);
                        opSM = leiaNumInt();
                        switch (opSM) {
                            case 1:
                                System.out.println("--Cadastrar--");
                                cadastrarCliente();
                                break;
                            case 2:
                                System.out.println("--Editar--");
                                break;
                            case 3:
                                System.out.println("--Listar--");
                                listarCliente();
                                break;
                            case 4:
                                System.out.println("--Deletar--");
                                deletarCliente();
                                break;
                            case 0:
                                System.out.println("--Menu Principal--");
                                break;
                            default:
                                System.out.println("--Opção Invalida, Tente Novamente!--");
                                break;

                        }
                    } while (opSM != 0);
                    break;
                case 4:
                    System.out.println("--Venda Livro--");
                    break;
                case 0:
                    System.out.println("--Fim do programa--");
                    break;
                default:
                    System.out.println("--Opção Invalida, Tente Novamente!--");
                    break;
            }
        } while (opM != 0);
    }

}
