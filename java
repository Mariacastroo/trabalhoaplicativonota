package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextEmail, editTextIdade, editTextDisciplina, editTextNota1, editTextNota2;
    private TextView textViewErro, textViewResumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);
        Button buttonEnviar = findViewById(R.id.buttonEnviar);
        Button buttonResetar = findViewById(R.id.buttonResetar);
        textViewErro = findViewById(R.id.textViewErro);
        textViewResumo = findViewById(R.id.textViewResumo);

        buttonEnviar.setOnClickListener(view -> validarCampos());
        buttonResetar.setOnClickListener(view -> resetarFormulario());
    }

    private void validarCampos() {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String idadeStr = editTextIdade.getText().toString();
        String disciplina = editTextDisciplina.getText().toString();
        String nota1Str = editTextNota1.getText().toString();
        String nota2Str = editTextNota2.getText().toString();

        StringBuilder erros = new StringBuilder();

        if (nome.isEmpty()) erros.append("Nome é obrigatório.\n");
        if (email.isEmpty()) erros.append("Email é obrigatório.\n");
        if (disciplina.isEmpty()) erros.append("Disciplina é obrigatória.\n");
        if (nota1Str.isEmpty()) erros.append("Nota 1º Bimestre é obrigatória.\n");
        if (nota2Str.isEmpty()) erros.append("Nota 2º Bimestre é obrigatória.\n");

        int idade = -1;
        if (idadeStr.isEmpty()) {
            erros.append("Idade é obrigatória.\n");
        } else {
            try {
                idade = Integer.parseInt(idadeStr);
            } catch (NumberFormatException e) {
                erros.append("Idade deve ser um número válido.\n");
            }
        }

        if (erros.length() > 0) {
            textViewErro.setText(erros.toString());
            textViewErro.setVisibility(View.VISIBLE);
            textViewResumo.setVisibility(View.GONE);
        } else {
            textViewErro.setVisibility(View.GONE);
            exibirResumo(nome, email, idade, disciplina, nota1Str, nota2Str);
        }
    }

    private void exibirResumo(String nome, String email, int idade, String disciplina, String nota1Str, String nota2Str) {
        double nota1 = Double.parseDouble(nota1Str);
        double nota2 = Double.parseDouble(nota2Str);
        double media = (nota1 + nota2) / 2;

        String resultado = media >= 6 ? "Aprovado" : "Reprovado";

        String resumo = "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Idade: " + idade + "\n" +
                "Disciplina: " + disciplina + "\n" +
                "Notas 1º e 2º Bimestres: " + nota1 + ", " + nota2 + "\n" +
                "Média: " + media + "\n" +
                "Resultado: " + resultado;

        textViewResumo.setText(resumo);
        textViewResumo.setVisibility(View.VISIBLE);
    }

    private void resetarFormulario() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewErro.setVisibility(View.GONE);
        textViewResumo.setVisibility(View.GONE);
    }
}




