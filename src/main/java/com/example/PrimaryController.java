package com.example;

import java.sql.SQLException;
import java.util.Random;

import com.example.data.JogadorDao;
import com.example.model.Jogador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private Button botaoJogar;
    @FXML
    private ToggleGroup escolhaJogador;
    @FXML
    private RadioButton escolhaPapel;
    @FXML
    private RadioButton escolhaPedra;
    @FXML
    private RadioButton escolhaTesoura;
    @FXML
    private TextField nomeJogador;
    @FXML
    private Label restanteRodadas;
    @FXML
    private Label resultado;
    @FXML 
    private Text imagemComputador;
    @FXML 
    private TableView<Jogador> tabela;
    @FXML 
    private TableColumn<String, Jogador> nome;
    @FXML 
    private TableColumn<Integer, Jogador> vitorias;
    @FXML 
    private TableColumn<Integer, Jogador> derrotas;

    JogadorDao dao = new JogadorDao();

    int i;
    int pontosJogador;
    int pontosComputador;
    int numeroJogador;

    
    public void jogar() {

        Random gerador = new Random();
        int escolhaComputador = gerador.nextInt(3);

        RadioButton escolha = (RadioButton) escolhaJogador.getSelectedToggle();
        String valorSelecionado = escolha.getText();

        System.out.println(valorSelecionado);
        
        if (valorSelecionado.equals("Pedra")) {
            numeroJogador = 0;
        } else if (valorSelecionado.equals("Papel")) {
            numeroJogador = 1;
        } else if (valorSelecionado.equals("Tesoura")) {
            numeroJogador = 2;
        }


        if (escolhaComputador == 0) {
            imagemComputador.setText("👊");
        } else if (escolhaComputador == 1) {
            imagemComputador.setText("🖐");
        } else {
            imagemComputador.setText("✌");
        }


        System.out.println(numeroJogador);
        System.out.println(escolhaComputador);


        if (numeroJogador == escolhaComputador) {
            resultado.setText("Empate! Jogue novamente.");
        } else if (numeroJogador == 0 && escolhaComputador == 1) {
            resultado.setText("Derrota! :(");
            pontosComputador++;
            i++;
        } else if (numeroJogador == 0 && escolhaComputador == 2) {
            resultado.setText("Vitória! :)");
            pontosJogador++;
            i++;
        } else if (numeroJogador == 1 && escolhaComputador == 0) {
            resultado.setText("Vitória! :)");
            pontosJogador++;
            i++;
        } else if (numeroJogador == 1 && escolhaComputador == 2) {
            resultado.setText("Derrota! :(");
            pontosComputador++;
            i++;
        } else if (numeroJogador == 2 && escolhaComputador == 1) {
            resultado.setText("Vitória! :)");
            pontosJogador++;
            i++;
        } else if (numeroJogador == 2 && escolhaComputador == 0) {
            resultado.setText("Derrota! :(");
            pontosComputador++;
            i++;
        }
        

        if (i >= 5) {
            var jogador = new Jogador(
                nomeJogador.getText(),
                pontosJogador,
                pontosComputador
            );

            try {
                dao.inserir(jogador);
            } catch (SQLException erro) {
                mostrarMensagem("Erro", "Erro ao inserir. " + erro.getMessage());
            }

            atualizar();

            restanteRodadas.setText("Jogo finalizado!");
            i = 0;
            nomeJogador.clear();
            pontosComputador = 0;
            pontosJogador = 0;
        } else {
            restanteRodadas.setText("Faltam " + (5 - i) + " rodadas");
        }
    }

    public void atualizar(){
        try {
            dao.buscarTodos().forEach(jogador -> tabela.getItems().add(jogador));
        } catch (SQLException e) {
            mostrarMensagem("Erro", "Erro ao buscar jogadores. " + e.getMessage());
        }
    }

    private void mostrarMensagem(String titulo, String mensagem){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.show();
    }


    public void initialize() {
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));
    }
}
