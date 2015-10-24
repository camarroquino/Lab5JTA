/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Vendedor;
import com.losalpes.excepciones.CupoInsuficienteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import javax.transaction.UserTransaction;

/**
 *
 * @author G40
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenciaBMT implements PersistenciaBMTLocal {

    @Resource
    private SessionContext sctx;
    @Resource(mappedName = "jdbc/derbyDatasource")
    private javax.sql.DataSource dataSource;

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void insertRemoteDatabase(Vendedor vendedor) throws Exception{
        try{
            em.persist(vendedor);
        }
        catch(PersistenceException persistenceException){
            throw new PersistenceException("Error al insertar el vendedor "+persistenceException.getMessage());
        }
    }
    
    @Override
    public void deleteRemoteDatabase(Vendedor vendedor) throws Exception {
        try{
            em.remove(vendedor);
        }
        catch(PersistenceException persistenceException){
            throw new PersistenceException("Error al eliminar el vendedor "+persistenceException.getMessage());
        }
    }
    
      
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void comprar(RegistroVenta venta) throws Exception{
        //TODO: implementar logica de inserción 
        
        em.persist(venta);
        
        Connection con;
        PreparedStatement ps;
        
        // Try necesario para evitar problemas de compilación
        try {
            con = dataSource.getConnection("adminLosAlpes","12345");
            
            ps = con.prepareStatement("SELECT * FROM TARJETACREDITOALPES WHERE login = ?");
            ps.setString(1, venta.getComprador().getLogin());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Double cupo = rs.getDouble("cupo");
                if (cupo < venta.getCantidad() * venta.getProducto().getPrecio()) {
                    throw new CupoInsuficienteException("El cupo de la tarjeta es insuficiente");
                }
                else {
                    
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}