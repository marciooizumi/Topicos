/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;
import static junit.framework.Assert.assertNull;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Adriano_2
 */
public class UsuarioDAOTest extends TestCase {
    UsuarioDAO dao;
    
    public UsuarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new UsuarioDAO(DAO.createEntityManager());
    }
    
    @After
    public void tearDown() {
        dao.closeManager();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void testCriarNovo() {
        //Salvando um usuario vazio
        assertFalse(dao.criarNovo(new Usuario()));
        
        //Salvando um usuario corretamente
        int rand = new Random().nextInt(100000);
        Usuario user = new Usuario("Usuario " + rand, "u" + rand, "pw" + rand);
        assertTrue(dao.criarNovo(user));
        
        //Tentando salvar o mesmo usuario novamente
        user = new Usuario("Usuario " + rand, "u" + rand, "pw" + rand);
        assertFalse(dao.criarNovo(user));
    }
    
    @Test
    public void testLogin() {
        //Create user
        int rand = new Random().nextInt(100000);
        Usuario user = new Usuario("Usuario " + rand, "u" + rand, "pw" + rand);
        assertTrue(dao.criarNovo(user));
        
        //Test correct login
        Usuario login = dao.login("u" + rand, "pw" + rand);
        assertNotNull(login);
        assertEquals(user.getCodigo(), login.getCodigo());
        
        //Test wrong login
        assertNull(dao.login(user.getUsuario(), "3rewqwerqw"));
        assertNull(dao.login("3rewqwerqw", user.getSenha()));
        assertNull(dao.login("", ""));
        
        //Test disabled user
        
    }
    
    @Test
    public void testAlterar() {       
        //Create user
        int rand = new Random().nextInt(100000);
        Usuario user = new Usuario("Usuario " + rand, "u" + rand, "pw" + rand);
        assertTrue(dao.criarNovo(user));
        assertNotNull(user.getCodigo());
        
        //Change data
        String nome = user.getNome();
        user.setNome(nome + "Update");
        assertTrue(dao.alterar(user));
        
        Usuario login = dao.login("u" + rand, "pw" + rand);
        assertNotNull(login);
        assertEquals(login.getCodigo(), user.getCodigo());
        assertEquals(nome + "Update", login.getNome());
        
        login.setNome(nome);
        assertTrue(dao.alterar(user));
        
        assertFalse(dao.alterar(new Usuario()));
    }
}