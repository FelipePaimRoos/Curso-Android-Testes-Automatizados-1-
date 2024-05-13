package leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    @Test
    public void propoe() {


    }

    @Test
    public void getMaiorLance() {
        Leilao teste= new Leilao("");
        Usuario user = new Usuario("teste");
        Lance lanceMenor = new Lance(user, 100);
        Usuario user2 = new Usuario("teste2");
        Lance lanceMaior = new Lance(user2, 200);

        teste.propoe(lanceMaior);
        teste.propoe(lanceMenor);

        assertEquals(teste.getMaiorLance(), lanceMaior.getValor());
    }

    @Test
    public void getDescricao() {
        Leilao teste = new Leilao("descrição");
        assertEquals("descrição", teste.getDescricao());

    }

    @Test
    public void getMenorLance() {
        Leilao teste= new Leilao("");
        Usuario user = new Usuario("teste");
        Lance lanceMenor = new Lance(user, 100);
        Usuario user2 = new Usuario("teste2");
        Lance lanceMaior = new Lance(user2, 200);

        teste.propoe(lanceMaior);
        teste.propoe(lanceMenor);

        assertEquals(teste.getMenorLance(), lanceMenor.getValor());
    }


}