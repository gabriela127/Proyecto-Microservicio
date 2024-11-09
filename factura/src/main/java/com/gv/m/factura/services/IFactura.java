package com.gv.m.factura.services;

import java.util.List;
import java.util.Optional;

import com.gv.m.factura.dto.FacturaRequest;
import com.gv.m.factura.entity.Factura;

public interface IFactura {

	List<Factura> findAll();
	Optional<Factura> findByNit(String nit);
	Factura save(Factura factura);
	Factura update(Integer id, Factura factura);
	Factura crearFactura(FacturaRequest facturaRequest);
	Integer deleteById(Integer id);
	
		
	}
	
