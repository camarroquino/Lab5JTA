/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.TipoMueble;
import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author G40
 */
public class ServicioCarritoMockTest {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Arreglo de Ventas
     */
    private ArrayList<RegistroVenta> venta;
    
    /**
     * Arreglo Mueble
     */
    private ArrayList<Mueble> muebles;
    
    
    /**
     * Objeto Usuario
     */
    private Usuario usuario;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos
     */
    public ServicioCarritoMockTest()
    {
        
    }

    //-----------------------------------------------------------
    // Métodos de inicialización y terminación
    //-----------------------------------------------------------

    /**
     * Método que se ejecuta antes de comenzar la prueba unitaria
     * Se encarga de inicializar todo lo necesario para la prueba
     */
    @Before
    public void setUp() 
    {
        ServicioCarritoMock servicioCarritoMock = new ServicioCarritoMock();
        usuario=new Usuario("2", "123456789", TipoUsuario.Cliente);

       
        muebles=new ArrayList();
        
        //Agrega los muebles del sistema
        muebles.add(new Mueble(1L, "Silla clásica", "Una confortable silla con estilo del siglo XIX.", TipoMueble.Interior, 45, "", 123));
        muebles.add(new Mueble(2L, "Silla moderna", "Lo último en la moda de interiores. Esta silla le brindará la comodidad e innovación que busca", TipoMueble.Interior, 50, "", 5464));
        muebles.add(new Mueble(3L, "Mesa de jardín", "Una bella mesa para comidas y reuniones al aire libre.", TipoMueble.Exterior, 100, "", 4568));
        servicioCarritoMock.setInventario(muebles);
        

        
       


    }

    /**
     * Método que se ejecuta después de haber ejecutado la prueba
     */
    @After
    public void tearDown()
    {
        
    }

    //-----------------------------------------------------------
    // Métodos de prueba
    //-----------------------------------------------------------

    /**
     * Prueba para agregar un mueble en el sistema
     */
    @Test
    public void testAgregarMueble()
    {
        System.out.println("agregarMueble");
        Mueble mueble = new Mueble(9L,"SillaTest","Una confortable silla.",TipoMueble.Interior,60,"",5342);
        ServicioCatalogoMock instance = new ServicioCatalogoMock();
        int actual=instance.darMuebles().size();
        instance.agregarMueble(mueble);
        int esperado=instance.darMuebles().size();
        assertEquals(esperado,actual+1);
    }

    /**
     * Prueba para obtener todos los muebles del sistema
     */
    @Test
    public void testDarMuebles()
    {
        System.out.println("darMuebles");
        ServicioCatalogoMock instance = new ServicioCatalogoMock();
        List result = instance.darMuebles();
        assertNotNull(result);          
    }

}
