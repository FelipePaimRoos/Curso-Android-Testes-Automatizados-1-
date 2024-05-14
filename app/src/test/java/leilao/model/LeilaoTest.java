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
    public void getDevolverMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("fran"), 200.0));

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
        CONSOLE.propoe(new Lance(new Usuario("fran"), 200));
        CONSOLE.propoe(new Lance(ALEX, 300));
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

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 500));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400));

        int quantidadeLancesDevolvida = CONSOLE.quantidadeLances();

        assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForDoMesmoUsuarioEmSequencia(){
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(ALEX, 600.0));

        int i = CONSOLE.quantidadeLances();

        assertEquals(1, i);
    }


}