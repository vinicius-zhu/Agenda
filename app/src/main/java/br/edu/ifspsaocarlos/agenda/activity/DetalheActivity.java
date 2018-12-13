package br.edu.ifspsaocarlos.agenda.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import br.edu.ifspsaocarlos.agenda.adapter.ContatoAdapter;
import br.edu.ifspsaocarlos.agenda.data.ContatoDAO;
import br.edu.ifspsaocarlos.agenda.model.Contato;
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

        if (getIntent().hasExtra("contato"))
        {
            this.c = (Contato) getIntent().getSerializableExtra("contato");
            EditText nameText = (EditText)findViewById(R.id.editTextNome);
            nameText.setText(c.getNome());
            EditText foneText = (EditText)findViewById(R.id.editTextFone);
            foneText.setText(c.getFone());
            EditText emailText = (EditText)findViewById(R.id.editTextEmail);
            ToggleButton toggleButton = (ToggleButton)findViewById(R.id.toggleButtonFavorito);
            try {
                toggleButton.setChecked(c.getFavorito());
            }
            catch (Exception e){}
            EditText foneText2 = (EditText)findViewById(R.id.editTextFone2);
            foneText2.setText(c.getFone2());
            EditText niver = (EditText)findViewById(R.id.editTextNiver);
            niver.setText(c.getAniversario());

            emailText.setText(c.getEmail());
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
        if (!getIntent().hasExtra("contato"))
        {
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

    private void apagar()
    {
        cDAO.apagaContato(c);

        Intent resultIntent = new Intent();
        setResult(3,resultIntent);
        finish();
    }

    private void salvar()
    {
        String name = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
        String fone = ((EditText) findViewById(R.id.editTextFone)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String fone2 = ((EditText) findViewById(R.id.editTextFone2)).getText().toString();
        String niver = ((EditText) findViewById(R.id.editTextNiver)).getText().toString();
        Boolean favorito;
        try {
            favorito = ((ToggleButton) findViewById(R.id.toggleButtonFavorito)).isChecked();
        } catch (Exception e)
        {
            favorito = false;
        }

        if (c==null)
            c = new Contato();


        c.setNome(name);
        c.setFone(fone);
        c.setEmail(email);
        c.setFone2(fone2);
        c.setAniversario(niver);
        c.setFavorito(favorito);

        cDAO.salvaContato(c);
        //c.setId(10);
        //ContatoAdapter.Adiciona(c);
        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}

