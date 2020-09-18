package Controller;

import domain.Retorno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Conexao;

public class Controller {
    public void view() {
        ArrayList<Retorno> lista = new ArrayList<>();
        Conexao c = new Conexao();
        String sql = "SELECT * FROM vendas.vw_marcaempresa";

        try {
            long tempoInicial = System.nanoTime();
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Retorno retorno = new Retorno();
                retorno.setCont(rs.getInt("count(empresa.idempresa)"));
                retorno.setNomeEmpresa(rs.getString("Nome da Empresa"));
                retorno.setNomeMarca(rs.getString("Nome Marca"));
                lista.add(retorno);
            }
            System.out.println(lista.size());
            long tempoExec= System.nanoTime() - tempoInicial;
            System.out.println("Tempo de execução inner: " + tempoExec);
            c.desconectar();
        } catch (SQLException erro) {
            System.out.println("Erro na sentença: " + erro.getMessage());
        }
    }
}