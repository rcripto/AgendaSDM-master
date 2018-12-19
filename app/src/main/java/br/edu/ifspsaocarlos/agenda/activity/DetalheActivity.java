package br.edu.ifspsaocarlos.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.data.ContatoDAO;

import br.edu.ifspsaocarlos.agenda.R;


public class DetalheActivity extends AppCompatActivity {

    private Contato c;
    private ContatoDAO cDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("contato")) {
            this.c = (Contato) getIntent().getSerializableExtra("contato");
            EditText nameText = (EditText)findViewById(R.id.edtNome);
            nameText.setText(c.getNome());
            EditText foneText = (EditText)findViewById(R.id.edtFone);
            foneText.setText(c.getFone());
            EditText cellPhone = (EditText)findViewById(R.id.edtCelular);
            cellPhone.setText(c.getCellPhone());
            EditText emailText = (EditText)findViewById(R.id.edtEmail);
            emailText.setText(c.getEmail());
            EditText aniversarioText = (EditText)findViewById(R.id.edtAniversario);
            aniversarioText.setText(c.getAniversario());
            int pos =c.getNome().indexOf(" ");
            if (pos==-1)
                pos=c.getNome().length();
            setTitle(c.getNome().substring(0,pos));
        }

        cDAO = new ContatoDAO(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        if (!getIntent().hasExtra("contato")) {
            MenuItem item = menu.findItem(R.id.delContato);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarContato:
                salvar();
                return true;
            case R.id.delContato:
                apagar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void apagar() {
        cDAO.apagaContato(c);

        Intent resultIntent = new Intent();
        setResult(3,resultIntent);
        finish();
    }

    private void salvar() {

        String name = ((EditText) findViewById(R.id.edtNome)).getText().toString();
        String fone = ((EditText) findViewById(R.id.edtFone)).getText().toString();
        String cellPhone = ((EditText) findViewById(R.id.edtCelular)).getText().toString();
        String email = ((EditText) findViewById(R.id.edtEmail)).getText().toString();
        String aniversario = ((EditText) findViewById(R.id.edtAniversario)).getText().toString();

        if (c == null) {
            c = new Contato();
        }

        c.setNome(name);
        c.setFone(fone);
        c.setEmail(email);
        c.setCellPhone(cellPhone);
        c.setAniversario(aniversario);
        c.setFavorito(false);
        cDAO.salvaContato(c);
        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}

