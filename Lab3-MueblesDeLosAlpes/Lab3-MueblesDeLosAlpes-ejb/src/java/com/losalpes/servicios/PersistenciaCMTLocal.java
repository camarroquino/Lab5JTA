/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import javax.ejb.Local;

/**
 *
 * @author waira2
 */
@Local
public interface PersistenciaCMTLocal {
    public void comprar(RegistroVenta venta);
}
