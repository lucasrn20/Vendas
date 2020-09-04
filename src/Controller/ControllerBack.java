package Controller;

import domain.Empresa;
import domain.Produto;
import domain.Venda;
import domain.Vendedor;
import domain.Marca;
import domain.Retorno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Conexao;

public class ControllerBack {
    public void view() {
        ArrayList<Empresa> lista1 = new ArrayList<>();
        ArrayList<Vendedor> lista2 = new ArrayList<>();
        ArrayList<Venda> lista3 = new ArrayList<>();
        ArrayList<Produto> lista4 = new ArrayList<>();
        ArrayList<Marca> lista5 = new ArrayList<>();
        Conexao c = new Conexao();
        String sql1 = "SELECT * FROM empresa";
        String sql2 = "SELECT * FROM vendedor";
        String sql3 = "SELECT * FROM venda";
        String sql4 = "SELECT * FROM produto";
        String sql5 = "SELECT * FROM marca";
        try {
            long tempoInicial = System.nanoTime();
            PreparedStatement sentenca = c.con.prepareStatement(sql1);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setIdempresa(rs.getInt("idempresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setDescricao(rs.getString("descricao"));
                lista1.add(empresa);
            }
            sentenca = c.con.prepareStatement(sql2);
            rs = sentenca.executeQuery();
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdvendedor(rs.getInt("idvendedor"));
                vendedor.setNome(rs.getString("nome"));
                vendedor.setCpf(rs.getString("cpf"));
                vendedor.setIdempresa(rs.getInt("idempresa"));
                lista2.add(vendedor);
            }
            sentenca = c.con.prepareStatement(sql3);
            rs = sentenca.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdvenda(rs.getInt("idvenda"));
                venda.setIdvendedor(rs.getInt("idvendedor"));
                venda.setIdproduto(rs.getInt("idproduto"));
                //venda.setData(rs.getDate("data"));
                lista3.add(venda);
            }
            sentenca = c.con.prepareStatement(sql4);
            rs = sentenca.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdproduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setIdmarca(rs.getInt("idmarca"));
                produto.setDescricao(rs.getString("descricao"));
                lista4.add(produto);
            }
            sentenca = c.con.prepareStatement(sql5);
            rs = sentenca.executeQuery();
            while (rs.next()) {Marca marca = new Marca();
                marca.setIdmarca(rs.getInt("idmarca"));
                marca.setNome(rs.getString("nome"));
                marca.setDescricao(rs.getString("descricao"));
                lista5.add(marca);
            }
            //fazer tratamento
            Retorno ret = new Retorno();
            ArrayList<Retorno> retorno = new ArrayList<>();

            for(Empresa emp: lista1){
                for(Vendedor vendedor: lista2){
                    if(emp.getIdempresa() == vendedor.getIdempresa()){
                        for(Venda venda: lista3){
                            if(vendedor.getIdvendedor() == venda.getIdvendedor()){
                                for(Produto prod: lista4){
                                    if(venda.getIdproduto() == prod.getIdproduto()){                                        
                                        for(Marca marca: lista5){                              
                                            if(prod.getIdmarca() == marca.getIdmarca()){
                                                ret.setNomeEmpresa(emp.getNome());
                                                ret.setNomeMarca(marca.getNome());
                                                ret.setCont(1);
                                                retorno.add(ret);
                                                ret = new Retorno();
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            
            ArrayList<Retorno> retornoAux = new ArrayList<>();
            int controle = 0;
            int cont = 0;
            for(Retorno ret1: retorno){
                for(Retorno ret2: retorno){
                    if((ret1.getNomeEmpresa().equals(ret2.getNomeEmpresa())) 
                            && (ret1.getNomeMarca().equals(ret2.getNomeMarca()))){
                        cont++;
                    }
                }
                ret.setNomeEmpresa(ret1.getNomeEmpresa());
                ret.setNomeMarca(ret1.getNomeMarca());
                ret.setCont(cont);

                if(retornoAux.isEmpty()){
                    retornoAux.add(ret);
                }else{
                    for(Retorno retAux: retornoAux){
                        if((retAux.getCont().equals(ret.getCont()))
                                && (retAux.getNomeEmpresa().equals(ret.getNomeEmpresa()))
                                && (retAux.getNomeMarca().equals(ret.getNomeMarca()))){
                                controle = 0;
                        }else{
                            controle = 1;
                        }
                    }
                    if(controle == 1){
                        retornoAux.add(ret);
                        controle = 0;
                    }
                } 
                ret = new Retorno();
                cont = 0; 
            }
            
            for(Retorno retor: retornoAux){
                System.out.println(retor.getCont()+" "+retor.getNomeEmpresa()+" "+retor.getNomeMarca()+"\n");
            }
            System.out.println(retornoAux.size());
            long tempoExec= System.nanoTime() - tempoInicial;
            System.out.println("Tempo de execução: " + tempoExec);
            c.desconectar();
        } catch (SQLException erro) {
            System.out.println("Erro na sentença: " + erro.getMessage());
        }
    }
}
