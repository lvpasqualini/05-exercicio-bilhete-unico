import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Util {
    private int index = 0;
    private Bilhete[] bilhete = new Bilhete[3];

    public void mainMenu() {
        String menu = "1 - Admin \n2 -  Usuário\n3 - Finalizar";
        int option;
        do {
            option = parseInt(showInputDialog(menu));
            if (option < 1 || option > 3) {
                showMessageDialog(null, "Opção inválida!");
            } else {
                switch (option) {
                    case 1:
                        adminMenu();
                        break;
                    case 2:
                        userMenu();
                        break;
                }
            }

        } while (option != 3);
    }

    private void adminMenu() {
        String menu = "1 - Emitir novo bilhete \n2 - Listar Bilhetes\n3 - Remover Bilhete\n4- Cancelar";
        int option;
        do {
            option = parseInt(showInputDialog(menu));
            if (option < 1 || option > 4) {
                showMessageDialog(null, "Opção inválida!");
            } else {
                switch (option) {
                    case 1:
                        addTicket();
                        break;
                    case 2:
                        listTicket();
                        break;
                    case 3:
                        removeTicket();
                        break;
                }
            }
            option = parseInt(showInputDialog(menu));
        } while (option != 4);

    }

    private void userMenu() {
        String menu = "1 - Consultar saldo\n2 - Carregar\n3 - Passar Catraca\n4- Cancelar";
        int option;
        do {
            option = parseInt(showInputDialog(menu));
            if (option < 1 || option > 4) {
                showMessageDialog(null, "Opção inválida!");
            } else {
                switch (option) {
                    case 1:
                        consultarSaldo();
                        break;
                    case 2:
                        carregarBilhete();
                        break;
                    case 3:
                        passarNaCatraca();
                        break;
                }
            }
            option = parseInt(showInputDialog(menu));
        } while (option != 4);
    }

    private void addTicket() {
        String nome, perfil;
        long cpf;
        if (index < bilhete.length) {
            nome = showInputDialog("Nome do usuário: ");
            cpf = parseLong(showInputDialog("Digite o cpf: "));
            perfil = showInputDialog("Digite o perfil: ");
            bilhete[++index] = new Bilhete(new Usuario(nome, cpf, perfil));
        } else {
            showMessageDialog(null, "Entre em contato com um adiministrador!");
        }
    }

    private void listTicket() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++) {
            aux += "Número do bilhete: " + bilhete[i].numero + "\n";
            aux += "Nome do usuário: " + bilhete[i].usuario.nome + "\n";
            aux += "Perfil (tipo de tarifa): " + bilhete[i].usuario.perfil + "\n";
            aux += "Saldo :" + df.format(bilhete[i].getSaldo());
            aux += "------------------------------------------------------\n";
        }
    }

    private void removeTicket() {

    }

    private int search() {
        long cpf = parseLong(showInputDialog("Informe o CPF para procura: "));
        for (int i = 0; i < index; i++) {
            if (bilhete[i].usuario.cpf == cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf+"Não encontrado");
        return -1;
    }

    private void carregarBilhete() {
        int position = search();
        double valor;
        if(position != -1){
            valor = parseDouble(showInputDialog("Valor da recarga"));
            bilhete[position].carregarBilhete(valor);
        }
    }

    private void consultarSaldo() {
        int position =  search();
        if(position != -1) {
            showMessageDialog(null,"Saldo R$ " +bilhete[position].getSaldo());
        }
    }

    private void passarNaCatraca(){
        int position =  search();
        if(position != -1) {
            bilhete[position].catraca();
        }
    }
}
