/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adriano_2
 */
public class CategoriaDAOTest {
    CategoriaDAO dao;
    Usuario user;
    
    public CategoriaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new CategoriaDAO(DAO.createEntityManager());
        UsuarioDAO userDao = new UsuarioDAO(dao.getManager());
        
        user = userDao.login("usercateg", "usercateg");
        if (user == null) {
            user = new Usuario("Usercateg", "usercateg", "usercateg");
            assertTrue(userDao.criarNovo(user));
        }
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testCriarNovo() {
        assertFalse(dao.criarNovo(new Categoria()));
        
        int rand = new Random().nextInt(100000);
        
        Categoria categ = new Categoria();
        categ.setNome("error" + rand);
        assertFalse(dao.criarNovo(categ));
        
        categ.setNome("acerto" + rand + "-" + rand);
        categ.setUsuario(user);
        assertNotNull(categ.getUsuario());
        assertTrue(dao.criarNovo(categ));
    }
    
    @Test
    public void testAlterar() {
        int rand = new Random().nextInt(100000);
        
        Categoria categ = new Categoria("categ" + rand, user);
        assertTrue(dao.criarNovo(categ));
        
        categ.setNome("categ" + rand + "a");
        assertTrue(dao.alterar(categ));
        
        categ.setNome("");
        assertFalse(dao.alterar(categ));
    }
    
    @Test
    public void testGetById() {
        int rand = new Random().nextInt(100000);
        
        Categoria categ = new Categoria("categ" + rand, user);
        assertTrue(dao.criarNovo(categ));
        
        int id = categ.getCodigo();
        Categoria c = dao.getById(id);
        assertEquals(categ.getNome(), c.getNome());
                
        c = dao.getById(-1);
        assertNull(c);
    }
    
    @Test
    public void testGetAllByUser() {
        List<Categoria> list1 = dao.getAllByUser(user);
        if (list1.size() > 0) {
            assertNotNull(list1.get(0));
            assertEquals(user, list1.get(0).getUsuario());
        }
    }
    
    @Test
    public void testExcluir() {
         int rand = new Random().nextInt(100000);
        
        Categoria categ = new Categoria("categdelete-" + rand, user);
        assertTrue(dao.criarNovo(categ));
        
        assertTrue(dao.excluir(categ));
        assertFalse(categ.isAtivo());
        categ.setAtivo(true);
        assertTrue(dao.alterar(categ));
        assertTrue(dao.excluirById(categ.getCodigo()));
        
        List<Categoria> list = dao.getAllByUser(user);
        assertFalse(list.contains(categ));
    }
}