package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisätäLiikaa() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonMahtuuVainTilavuus() {
        varasto.lisaaVarastoon(12);

        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(6);

        double maara = varasto.otaVarastosta(8);

        assertEquals(6, maara, vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiMahtuaEnempääKuinTilavuus() {
        varasto.lisaaVarastoon(6);

        varasto.otaVarastosta(8);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisätäNegatiivista() {
        varasto.lisaaVarastoon(-6);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivista() {
        varasto.lisaaVarastoon(2);

        varasto.otaVarastosta(-6);

        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringOikein() {
        varasto.lisaaVarastoon(4);

        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

    @Test
    public void varastonTilavuusEiOleNegatiivinen() {
        Varasto negVarasto = new Varasto(-10);

        assertEquals(0, negVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoonVoiLisätäAlkusaldon() {
        Varasto alkuVarasto = new Varasto(10, 6);

        assertEquals(6, alkuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisätäTilavuuttaEnempää() {
        Varasto alkuVarasto = new Varasto(10, 12);

        assertEquals(10, alkuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuvarastonTilavuusEiVoiOllaNegativiinen() {
        Varasto alkuVarasto = new Varasto(-10, 6);

        assertEquals(0, alkuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkuvarastonSaldoEiVoiOllaNegativiinen() {
        Varasto alkuVarasto = new Varasto(10, -6);

        assertEquals(0, alkuVarasto.getSaldo(), vertailuTarkkuus);
    }

}