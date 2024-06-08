package Patricia;

public class Aplicacao {
    public static void main(String[] args) {
        Patricia a = new Patricia();

        a.insere("Galo");
        a.insere("Sola");
        a.insere("Solo");
        a.insere("Sol");
        a.insere("Gel");
        a.insere("Grelha");
        a.insere("Antena");
        a.insere("Lousa");
        a.insere("Cubo");
        a.insere("Aries");
        a.insere("Areia");
        a.insere("Zelda");
        a.insere("Zona");
        a.insere("Norma");
        a.insere("Numero");
        a.insere("Year");
        a.insere("Indonesia");
        a.insere("India");
        a.insere("Lego");
        a.insere("Cebola");
        a.insere("Lenda");
        a.insere("Astronauta");
        a.insere("Logaritimo");
        a.insere("Forca");
        a.insere("Gol");
        a.insere("Belgica");
        a.insere("Urso");
        a.insere("Bola");
        a.insere("Boca");
        a.insere("Marca");
        a.insere("Marco");
        a.insere("Nave");
        a.insere("Espaco");
        a.insere("Calendario");
        a.insere("Mouse");
        a.insere("Estadio");
        a.insere("Regua");

        System.out.println("imprimindo nivel a nivel");
        a.imprimirNivelANivel();
        System.out.println("_______________________________");

        System.out.println("imprimindo todas as palavras");
        a.imprimirTodas();
        System.out.println("_______________________________");

        System.out.println("Buscando palavras");
        System.out.println("palava Sol");
        a.buscar("Sol");
        System.out.println("palava Sola");
        a.buscar("Sola");
        System.out.println("palava Gel");
        a.buscar("Gel");
        System.out.println("palava Galo");
        a.buscar("Galo");
        System.out.println("palava Urso");
        a.buscar("Urso");
        System.out.println("palava Lego");
        a.buscar("Lego");
        System.out.println("palava Indonesia");
        a.buscar("Indonesia");
        System.out.println("palava Marca");
        a.buscar("Marca");
        System.out.println("palava LOGARITIMO");
        a.buscar("LOGARITIMO");
        System.out.println("palava GRELHA");
        a.buscar("GRELHA");
        System.out.println("palava ARIES");
        a.buscar("ARIES");
        System.out.println("palava ZONA");
        a.buscar("ZONA");

        System.out.println("_______________________________");
        System.out.println("PALVRA QUE NÃO EXISTE");
        System.out.println("Analise");
        a.buscar("Analise");
        System.out.println("Barco");
        a.buscar("Barco");
        System.out.println("Onda");
        a.buscar("Onda");
        System.out.println("Casa");
        a.buscar("Casa");
        System.out.println("Medir");
        a.buscar("Medir");
        System.out.println("Saltar");
        a.buscar("Soltar");
        System.out.println("Inglaterra");
        a.buscar("Inglaterra");
        System.out.println("_______________________________");
    }
}
