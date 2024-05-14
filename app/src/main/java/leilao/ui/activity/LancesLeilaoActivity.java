package leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import app.R;
import leilao.model.Leilao;


public class LancesLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lances_leilao);
        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra("leilao")){
            Leilao leilao = (Leilao) dadosRecebidos.getSerializableExtra("leilao");
            TextView descricao = findViewById(R.id.lances_leilao_descricao);
            descricao.setText(leilao.getDescricao());
            TextView maiorLance = findViewById(R.id.lances_leilao_maior_lance);
            maiorLance.setText(String.valueOf(leilao.getMaiorLance()));
            TextView menorLance = findViewById(R.id.lances_leilao_menor_lance);
            menorLance.setText(String.valueOf(leilao.getMenorLance()));
        }
    }
}