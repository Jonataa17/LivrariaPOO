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
import model.Editora;
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
                System.out.println("Cliente deletado com sucesso!");
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

    public static void cadastrarEditora() {
        int idEditora;
        String nmEditora;
        String cnpj;
        String endereco;
        String telefone;
        String gerente;

        System.out.println("-- Cadastrar Editora --");
        System.out.print("Informe o CNPJ da Editora: ");
        boolean cnpjis;
        int opCNPJ;
        do {
            cnpj = leia.nextLine();
            cnpjis = Validadores.isCPF(cnpj);
            if (!cnpjis) {
                System.out.print("CNPJ Invalido!"
                        + "\nDeseja tentar novamente? 1 - Sim || 2 - Não: ");
                opCNPJ = leiaNumInt();
                if (opCNPJ == 1) {
                    System.out.print("Informe o CNPJ: ");
                } else if (opCNPJ == 2) {
                    System.out.println("Cadastro encerrado pelo usuario!");
                    break;
                }
            }

        } while (!cnpjis);

        if (cadEditora.getEditoraCNPJ(cnpj) != null) {
            System.out.println("Editora já cadastrada!");
        } else {
            System.out.print("Informe o nome da editora: ");
            nmEditora = leia.nextLine();
            System.out.print("Informe o telefone da editora: ");
            telefone = leia.nextLine();
            System.out.print("Informe o endereço da editora: ");
            endereco = leia.nextLine();
            System.out.print("Informe o nome do gerente: ");
            gerente = leia.nextLine();
            idEditora = cadEditora.geraID();

            Editora edi = new Editora(idEditora, nmEditora, cnpj, endereco, telefone, gerente);
            cadEditora.addEditora(edi);
            System.out.println("Editora cadastrada com sucesso!");
        }
    }//fim do cadastrarEditora

    private static void editarEditora() {
        System.out.println("-- Editar Editora --");
        System.out.print("Informe o CNPJ: ");
        String cnpj = leia.nextLine();
        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = cadEditora.getEditoraCNPJ(cnpj);
            if (edi != null) {
                System.out.println("1 - Nome:\t" + edi.getNmEditora());
                System.out.println("2 - Endereço:\t" + edi.getEndereco());
                System.out.println("3 - Fone:\t" + edi.getTelefone());
                System.out.println("4 - Todas as opções acima");
                System.out.print("Qual das opções deseja alterar? 1 || 2 || 3 || 4\n Digite aqui: ");
                int opEditar = leiaNumInt();
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        edi.setNmEditora(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("Informe o endereço: ");
                        edi.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.println("Informe o fone: ");
                        edi.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.print("Informe o nome: ");
                        edi.setNmEditora(leia.nextLine());
                        System.out.print("Informe o endereço: ");
                        edi.setEndereco(leia.nextLine());
                        System.out.print("Informe o fone: ");
                        edi.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                System.out.println("Editora:\n" + edi.toString());
            } else {
                System.out.println("Editora não cadastrado!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }//fim do editarEditora

    public static void listarEditora() {

        for (Editora edi : cadEditora.getEditoras()) {
            System.out.println("----");
            System.out.println("CNPJ:\t" + edi.getCnpj());
            System.out.println("Nome:\t" + edi.getNmEditora());
            System.out.println("Fone:\t" + edi.getTelefone());
        }

    }//fim listarCliente

    public static void deletarEditora() {
        System.out.println("-- Deletar Editora --");
        System.out.print("Informe o CNPJ: ");
        String cnpj = leia.next();

        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = cadEditora.getEditoraCNPJ(cnpj);
            if (edi != null) {
                cadEditora.removeEditora(edi);
                System.out.println("Editora deletada com sucesso!");
            } else {
                System.out.println("Editora não consta na base de dados!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }//fim deletarEditora

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
                                if (opM == 1) {
                                    cadastrarCliente();
                                } else if (opM == 2) {
                                    cadastrarEditora();
                                } else if (opM == 3) {
                                    //cadastrarLivro();
                                }
                                break;
                            case 2:
                                System.out.println("--Editar--");
                                if (opM == 1) {
                                    editarCliente();
                                } else if (opM == 2) {
                                    editarEditora();
                                } else if (opM == 3) {
                                    //editarLivro();
                                }
                                break;
                            case 3:
                                System.out.println("--Listar--");
                                if (opM == 1) {
                                    listarCliente();
                                } else if (opM == 2) {
                                    listarEditora();
                                } else if (opM == 3) {
                                    //listarLivro();
                                }
                                break;
                            case 4:
                                System.out.println("--Deletar--");
                                if (opM == 1) {
                                    deletarCliente();
                                } else if (opM == 2) {
                                    deletarEditora();
                                } else if (opM == 3) {
                                    //deletarLivro();
                                }
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

    private static void editarCliente() {
        System.out.println("-- Editar Cliente --");
        System.out.print("Informe o CPF: ");
        String cpf = leia.nextLine();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                System.out.println("1 - Nome:\t" + cli.getNomeCliente());
                System.out.println("2 - Endereço:\t" + cli.getEndereco());
                System.out.println("3 - Fone:\t" + cli.getTelefone());
                System.out.println("4 - Todas as opções acima");
                System.out.print("Qual das opções deseja alterar? 1 || 2 || 3 || 4\n Digite aqui: ");
                int opEditar = leiaNumInt();
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.println("Informe o fone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.print("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        System.out.print("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        System.out.print("Informe o fone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                System.out.println("Cliente:\n" + cli.toString());
            } else {
                System.out.println("Cliente não cadastrado!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }

}
