/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Vendedor;
import com.losalpes.excepciones.CupoInsuficienteException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.sql.DataSource;

/**
 *
 * @author waira2
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersistenciaCMT implements PersistenciaCMTLocal {

    @Resource
    private SessionContext sctx;
    @Resource(mappedName = "jdbc/derbyDatasource")
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void insertRemoteDatabase(Vendedor vendedor) throws Exception {
        try {
            em.persist(vendedor);
        } catch (PersistenceException persistenceException) {
            throw new PersistenceException("El vendedor ya existe. " + persistenceException);
        }
    }

    public void deleteRemoteDatabase(Vendedor vendedor) throws Exception{
        try {
            em.remove(vendedor);
        } catch (PersistenceException persistenceException) {
            throw new PersistenceException("No fue posible eliminar el vendedor. " + persistenceException);
        }
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void comprar(RegistroVenta venta) throws Exception{
        //TODO: implementar logica de inserción 
        
        // Try necesario para evitar problemas de compilación
        try {
            Mueble mueble = venta.getProducto();

            if(mueble.getCantidad()<venta.getCantidad()){
                sctx.setRollbackOnly();
                throw new CupoInsuficienteException("El numero de la orden supera los muebles disponibles.");
            }
        
            em.persist(venta);

            Connection con;
            PreparedStatement ps;
        
            con = dataSource.getConnection("adminLosAlpes","1234");
            
            ps = con.prepareStatement("SELECT * FROM TARJETACREDITOALPES WHERE login = ?");
            ps.setString(1, venta.getComprador().getLogin());
           
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Double cupo = rs.getDouble("cupo");
                if (cupo < venta.getCantidad() * venta.getProducto().getPrecio()) {
                    sctx.setRollbackOnly();
                    throw new CupoInsuficienteException("El cupo de la tarjeta es insuficiente");
                } else {
                    ps = con.prepareStatement("UPDATE  TARJETACREDITOALPES SET cupo=? WHERE login = ?");
                    ps.setDouble(1, cupo - venta.getCantidad() * venta.getProducto().getPrecio());
                    ps.setString(2, venta.getComprador().getLogin());
                    ps.executeUpdate();
                }
            } else {
                sctx.setRollbackOnly();
                throw new CupoInsuficienteException("El usuario no tiene tarjeta de crédito.");
            }       
            
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCMT.class.getName()).log(Level.SEVERE, null, ex);
            sctx.setRollbackOnly();
            throw new CupoInsuficienteException("El usuario no tiene tarjeta de crédito.");
        }
        
    }
}
        
        
        
        