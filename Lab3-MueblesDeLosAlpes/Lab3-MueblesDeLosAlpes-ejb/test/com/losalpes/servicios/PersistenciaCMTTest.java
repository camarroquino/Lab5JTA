/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Usuario;
import com.losalpes.entities.Vendedor;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Camilo Marroquin
 */
public class PersistenciaCMTTest {
    ServicioRegistroMock servicioRegistroMock = lookupServicioRegistroMockBean();
    ServicioCatalogoMock servicioCatalogoMock = lookupServicioCatalogoMockBean();
    
    public PersistenciaCMTTest() {
    }

    /**
     * Test of insertRemoteDatabase method, of class PersistenciaCMT.
     */
    @Test
    public void testInsertRemoteDatabase() throws Exception {
        System.out.println("insertRemoteDatabase");
        Vendedor vendedor = null;
        PersistenciaCMT instance = new PersistenciaCMT();
        instance.insertRemoteDatabase(vendedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRemoteDatabase method, of class PersistenciaCMT.
     */
    @Test
    public void testDeleteRemoteDatabase() throws Exception {
        System.out.println("deleteRemoteDatabase");
        Vendedor vendedor = null;
        PersistenciaCMT instance = new PersistenciaCMT();
        instance.deleteRemoteDatabase(vendedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comprar method, of class PersistenciaCMT.
     */
    @Test
    public void testComprar() throws Exception {
        System.out.println("comprar");
        
        
        Usuario usuario = servicioRegistroMock.obtenerCliente(0);
        Mueble mueble = servicioCatalogoMock.darMueble(0);
        
        RegistroVenta venta = new RegistroVenta(new Date(), mueble, 2,"Bogota", usuario);
            
        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersistenciaCMTLocal instance = (PersistenciaCMTLocal)container.getContext().lookup("java:global/classes/PersistenciaCMT");
        instance.comprar(venta);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private ServicioRegistroMock lookupServicioRegistroMockBean() {
        try {
            Context c = new InitialContext();
            return (ServicioRegistroMock) c.lookup("java:global/Lab3-MueblesDeLosAlpes/Lab3-MueblesDeLosAlpes-ejb/ServicioRegistroMock!com.losalpes.servicios.ServicioRegistroMock");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private ServicioCatalogoMock lookupServicioCatalogoMockBean() {
        try {
            Context c = new InitialContext();
            return (ServicioCatalogoMock) c.lookup("java:global/Lab3-MueblesDeLosAlpes/Lab3-MueblesDeLosAlpes-ejb/ServicioCatalogoMock!com.losalpes.servicios.ServicioRegistroMock");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}