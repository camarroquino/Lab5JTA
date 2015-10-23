/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
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
}
        
        
        
        