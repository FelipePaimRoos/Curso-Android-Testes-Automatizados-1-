package leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");


    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao(){
        String descricaoDevolvida = CONSOLE.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 200));

        double maiorLanceDevolvidoDoConsole = CONSOLE.getMaiorLance();

        assertEquals(200, maiorLanceDevolvidoDoConsole, 0.0001);
    }
    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 200.0));

        double maiorLanceDevolvidoDoComputador = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 9000.0));

        double maiorLanceDevolvidoDoConsole = CONSOLE.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 200.0));

        double menorLanceDevolvidoDoConsole = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 100.0));

        double menorLanceDevolvidoDoConsole = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));

        double menorLanceDevolvidoDoConsole = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void deve_devolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(ALEX, 200));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 300));
        CONSOLE.propoe(new Lance(new Usuario("felipe"), 400));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
    }

}