/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
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
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenciaBMT implements PersistenciaBMTLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Resource UserTransaction ut;
    
    @Resource(mappedName="jdbc/derbyDatasource") private DataSource dataSource;
    
    @PersistenceContext
    EntityManager em;
    
    
    public void insertRemoteDatabase(Vendedor vendedor) throws Exception{
        try{
            em.persist(vendedor);
        }
        catch(PersistenceException persistenceException){
            throw new PersistenceException("Error al insertar el vendedor "+persistenceException.getMessage());
        }
    }
    
    public void deleteRemoteDatabase(Vendedor vendedor) throws Exception {
        try{
            em.remove(vendedor);
        }
        catch(PersistenceException persistenceException){
            throw new PersistenceException("Error al eliminar el vendedor "+persistenceException.getMessage());
        }
    }
}