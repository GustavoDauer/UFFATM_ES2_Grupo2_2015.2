package test;

import model.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author matheus.froes
 */
public class ClienteTest {
    
    public ClienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInsert() {
        Cliente exemplo = new Cliente();
        exemplo.setId("999");
        exemplo.setNome("Pedro");
        exemplo.setStatus("1");
        exemplo.insertComTodosCampos();
        
        Cliente banco = Cliente.findById("999", 0);
        
        assertEquals(exemplo.getId(), banco.getId());
        assertEquals(exemplo.getNome(), banco.getNome());
        
        banco.delete();
    }
}
