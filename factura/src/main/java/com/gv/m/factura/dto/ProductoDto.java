package com.gv.m.factura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductoDto {
	
	@JsonProperty("idProducto")
    private Integer idProducto; 
	
	@JsonProperty("producto")
    private String producto;
	
	@JsonProperty("descripcion")
    private String descripcion;
	
	@JsonProperty("precio")
    private Double precio;
	
	@JsonProperty("stock")
    private Integer stock;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	
}
