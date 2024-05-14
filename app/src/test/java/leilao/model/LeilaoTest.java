package leilao.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LeilaoTest {

    private Leilao console = new Leilao("Console");

    private Usuario alex = new Usuario("Alex");
    private Usuario fran = new Usuario("Fran");

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao(){
        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance(){
        console.propoe(new Lance(alex, 200));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();

        assertEquals(200, maiorLanceDevolvidoDoConsole, 0.0001);
    }
    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(fran, 200.0));

        double maiorLanceDevolvidoDoComputador = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(fran, 9000.0));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(fran, 200.0));

        double menorLanceDevolvidoDoConsole = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(fran, 100.0));

        double menorLanceDevolvidoDoConsole = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeUmLance(){
        console.propoe(new Lance(alex, 100.0));

        double menorLanceDevolvidoDoConsole = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, 0.0001);
    }

}