import java.util.Random;

public class Bilhete {
    static final double tarifaBase = 5.2;
    long numero;
    private double saldo;
    Usuario usuario;
    private double valor;

    public Bilhete(Usuario usuario, long numero, double saldo) {
        Random rd = new Random();
        this.numero = rd.nextInt(1000, 10000);
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public double carregarBilhete(double valor) {
        return saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public String catraca() {
        double debito = tarifaBase / 2;
        if (usuario.perfil.equalsIgnoreCase("Comum")) {
            debito = tarifaBase;
        }

        if (saldo >= debito) {
            saldo -= debito;
            return "Passagem liberada";
        }

        return "Saldo insuficiente";
    }
}
