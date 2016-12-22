package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Contato;
import util.DataBase;

public class ContatoDAO {

    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public ContatoDAO() {
        db = new DataBase();
    }
    
    public void insert(Contato contato) {
        db.connect();
        sql = "INSERT INTO tb_contatos (con_nome, con_fone, con_email) VALUES (?, ?, ?)";
        try {
            ps = db.connection.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getFone());
            ps.setString(3, contato.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException error) {
            System.out.println("ERRO: "+error);
        } finally {
            db.disconnect();
        }
    }
    
    public void delete(Contato contato) {
        db.connect();
        sql = "DELETE FROM tb_contatos WHERE con_id = ?";
        try {
            ps = db.connection.prepareStatement(sql);
            ps.setInt(1, contato.getId());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException error) {
            System.out.println("ERRO: "+error);
        } finally {
            db.disconnect();
        }
    }
    
    public void update(Contato contato) {
        db.connect();
        sql = "UPDATE tb_contatos SET con_nome = ?, con_fone = ?, con_email = ? WHERE con_id = ?";
        try {
            ps = db.connection.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getFone());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, contato.getId());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException error) {
            System.out.println("ERRO: "+error);
        } finally {
            db.disconnect();
        }
    }
    
    public Contato findById(int id) {
        Contato contato = new Contato();
        db.connect();
        sql = "SELECT * FROM tb_contatos WHERE con_id = ?";
        try {
            ps = db.connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                contato.setId(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setFone(rs.getString(3));
                contato.setEmail(rs.getString(4));
            }
            rs.close();
            ps.close();
            return contato;
        } catch(SQLException error) {
            System.out.println("ERRO: "+error);
        } finally {
            db.disconnect();
        }
        return null;
    }
    
    public List<Contato> findAll() {
        List<Contato> contatos = new ArrayList();
        db.connect();
        sql = "SELECT * FROM tb_contatos";
        try {
            ps = db.connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setFone(rs.getString(3));
                contato.setEmail(rs.getString(4));
                contatos.add(contato);
            }
            rs.close();
            ps.close();
            return contatos;
        } catch(SQLException error) {
            System.out.println("ERRO: "+error);
        } finally {
            db.disconnect();
        }
        return null;
    }
    
}

