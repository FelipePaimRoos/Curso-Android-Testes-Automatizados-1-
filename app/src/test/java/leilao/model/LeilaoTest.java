package leilao.model;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import leilao.exception.LanceMenorQueUltimoLanceException;
import leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import leilao.exception.UsuarioJaDeuCincoLancesException;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao(){
        String descricaoDevolvida = CONSOLE.getDescricao();

       // assertEquals("Console", descricaoDevolvida);
        assertThat(descricaoDevolvida, is(equalTo("Console")));
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 200));

        double maiorLanceDevolvidoDoConsole = CONSOLE.getMaiorLance();

       // assertEquals(200, maiorLanceDevolvidoDoConsole, DELTA);
            assertThat(maiorLanceDevolvidoDoConsole, closeTo(200.0, DELTA));
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

        //assertEquals(3, tresMaioresLancesDevolvidos.size());
        //assertThat(tresMaioresLancesDevolvidos, hasSize(equalTo(3)));

        //assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        //assertThat(tresMaioresLancesDevolvidos, hasItem(new Lance(new Usuario("felipe"), 400)));
        //assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        //assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

        assertThat(tresMaioresLancesDevolvidos, both(Matchers.<Lance>hasSize(3)).and(contains(
                new Lance(new Usuario("felipe"), 400),
                new Lance(new Usuario("fran"), 300),
                new Lance(ALEX, 200))));
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
        exception.expect(LanceMenorQueUltimoLanceException.class);
        CONSOLE.propoe(new Lance(ALEX, 500));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400));

    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForDoMesmoUsuarioEmSequencia() {
        exception.expect(LanceSeguidoDoMesmoUsuarioException.class);
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(ALEX, 600.0));

    }
    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances() {
        exception.expect(UsuarioJaDeuCincoLancesException.class);

        final Usuario fran = new Usuario("Fran");
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(fran, 200.0));
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(fran, 400.0));
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(fran, 600.0));
        CONSOLE.propoe(new Lance(ALEX, 700.0));
        CONSOLE.propoe(new Lance(fran, 800.0));
        CONSOLE.propoe(new Lance(ALEX, 900.0));
        CONSOLE.propoe(new Lance(fran, 1000.0));
        CONSOLE.propoe(new Lance(ALEX, 1100.0));

    }


}