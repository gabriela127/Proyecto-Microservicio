package com.gv.m.factura.dto;

import java.util.*;

import lombok.Data;


@Data
public class FacturaRequest {

	private Integer clienteId;
	private String nit;
	private String nombreCliente;
	private String numeroFactura;
    private Double total;
    private String fecha;
	private List<ProductoRequest> productosFactura; 

	
	public FacturaRequest() {
		this.productosFactura = new ArrayList<>(); 
	}

	
	public List<ProductoRequest> getProductosFactura() {
		return productosFactura;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

    public void setProductosFactura(List<ProductoRequest> productosFactura) {
        this.productosFactura = productosFactura;
    }
    
    
}
