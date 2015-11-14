package model;

//import control.UsuarioController;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

@ManagedBean
@SessionScoped
public class UsuarioDAO{
    EntityManager em = Factory.getInstance().createEntityManager();
    
    public Usuario insert(String nome, String senha) {
        Usuario user = new Usuario();
        
        if (user == null) {
            return null;
        }
        
        try {
            senha = criptografar(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        user.setNome(nome);
        user.setSenha(senha);
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

     public String criptografar(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String s = hash.toString(16);
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return s;
    }
     
     public List<Usuario> getList() {
        em.getTransaction().begin();
        List<Usuario> lista = em.createQuery("SELECT u from Usuario as u").getResultList();
        em.close();
        return lista;
    }
}
