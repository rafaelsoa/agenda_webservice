
package service;

import dao.ContatoDAO;
import java.util.List;
import javax.jws.WebService;
import model.Contato;

@WebService (name="contatoServices")
public class ContatoFacade {
    
    private ContatoDAO dao;
    
    public ContatoFacade(){
     dao = new ContatoDAO();
     
    }
    
    public List<Contato> listarTodos(){
     return dao.findAll();
    
    }
    
    public Contato buscarPorId (int id){
    return dao.findById(id);
    
    }
    
    public void inserir(String nome, String fone, String email){
    
        dao.insert(new Contato(nome, fone,email));
    
    }
    
    
    
    
}
