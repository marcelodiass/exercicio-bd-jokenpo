package com.example;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.data.VeiculoDao;
import com.example.model.Veiculo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML TextField txtMarca;
    @FXML TextField txtModelo;
    @FXML TextField txtAno;
    @FXML TextField txtValor;
    @FXML TableView<Veiculo> tabela;
    @FXML TableColumn<Veiculo, String> colMarca;
    @FXML TableColumn<Veiculo, String> colModelo;
    @FXML TableColumn<Veiculo, Integer> colAno;
    @FXML TableColumn<Veiculo, BigDecimal> colValor;

    VeiculoDao dao = new VeiculoDao();

    public void cadastrar(){
        var veiculo = new Veiculo(
            txtMarca.getText(), 
            txtModelo.getText(), 
            Integer.valueOf(txtAno.getText()), 
            new BigDecimal(txtValor.getText())
        );

        try{
            dao.inserir(veiculo);
        }catch(SQLException erro){
            mostrarMensagem("Erro", "Erro ao cadastrar. " + erro.getMessage());
        }

        consultar();
    }

    public void consultar(){
        try {
            dao.buscarTodos().forEach(veiculo -> tabela.getItems().add(veiculo));
        } catch (SQLException e) {
            mostrarMensagem("Erro", "Erro ao buscar veículo. " + e.getMessage());
        }
    }

    private void mostrarMensagem(String titulo, String mensagem){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }
   
}
