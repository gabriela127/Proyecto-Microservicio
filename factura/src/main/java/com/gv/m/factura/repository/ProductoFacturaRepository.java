package com.gv.m.factura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gv.m.factura.entity.ProductoFactura;

@Repository
	public interface ProductoFacturaRepository extends JpaRepository<ProductoFactura, Integer> {
	    
	}


