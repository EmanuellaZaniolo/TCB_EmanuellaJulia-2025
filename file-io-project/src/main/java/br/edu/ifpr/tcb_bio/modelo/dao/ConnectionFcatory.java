package br.edu.ifpr.tcb_bio.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFcatory {
private static Connection conexao;
private ConnectionFcatory() {

}
public static Connection getConnection(){
    try{
    if(conexao == null) {
        String url = "jdbc:mysql://localhost:3306/tcb_bio";
        String user="aluno";
        String password="aluno";
        conexao = DriverManager.getConnection(url, user, password);
    }
} catch(SQLException e) {
    e.printStackTrace();
}
    return conexao;
}
}
