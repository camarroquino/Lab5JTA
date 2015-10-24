/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Vendedor;
import javax.ejb.Local;

/**
 *
 * @author Camilo Marroquin
 */
@Local
public interface PersistenciaBMTLocal {
    
    public void insertRemoteDatabase(Vendedor vendedor) throws Exception;
    
    public void deleteRemoteDatabase(Vendedor vendedor) throws Exception;
    
    public void comprar(RegistroVenta venta) throws Exception;
}
