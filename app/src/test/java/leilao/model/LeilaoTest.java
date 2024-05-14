package leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
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

        assertEquals(200, maiorLanceDevolvidoDoConsole, DELTA);
    }
    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 200.0));

        double maiorLanceDevolvidoDoComputador = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoDoComputador, DELTA);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 9000.0));

        double maiorLanceDevolvidoDoConsole = CONSOLE.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 200.0));

        double menorLanceDevolvidoDoConsole = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 100.0));

        double menorLanceDevolvidoDoConsole = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void getDevolverMenorLanceQuandoRecebeUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));

        double menorLanceDevolvidoDoConsole = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(ALEX, 200));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 300));
        CONSOLE.propoe(new Lance(new Usuario("felipe"), 400));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_devolverTresMaioresLances_QuandoRecebeUmLances(){

        CONSOLE.propoe(new Lance(ALEX, 200));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_QuandoRecebeDoisLances(){
        CONSOLE.propoe(new Lance(new Usuario("fran"), 300));
        CONSOLE.propoe(new Lance(ALEX, 200));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisLances(){
        CONSOLE.propoe(new Lance(ALEX, 200));
        final Usuario FRAN = new Usuario("Fran");
        CONSOLE.propoe(new Lance(FRAN, 300));
        CONSOLE.propoe(new Lance(new Usuario("felipe"), 400));
        CONSOLE.propoe(new Lance(new Usuario("Vi"), 500));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(500.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

}