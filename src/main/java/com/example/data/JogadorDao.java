package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Jogador;


public class JogadorDao {

    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private final String USER = "RM98251";
    private final String PASS = "050105";
    
    public void inserir(Jogador jogador) throws SQLException{
        var con = DriverManager.getConnection(URL, USER, PASS);

        var ps = con.prepareStatement("INSERT INTO pontuacao VALUES (?, ?, ?)");
        ps.setString(1, jogador.getNome());
        ps.setInt(2, jogador.getVitorias());
        ps.setInt(3, jogador.getDerrotas());

        ps.executeUpdate();
        con.close();
    }

    public List<Jogador> buscarTodos() throws SQLException{
        var pontuacao = new ArrayList<Jogador>();
        var con = DriverManager.getConnection(URL, USER, PASS);
        var rs = con.createStatement().executeQuery("SELECT * FROM pontuacao");

        while(rs.next()){
            pontuacao.add(new Jogador(
                rs.getString("jogador"), 
                rs.getInt("pontuacaojogador"), 
                rs.getInt("pontuacaocomputador") 
            ));
        }

        con.close();
        return pontuacao;
    }

}
