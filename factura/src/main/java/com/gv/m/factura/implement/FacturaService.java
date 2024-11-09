package com.gv.m.factura.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gv.m.factura.dto.ClienteDto;
import com.gv.m.factura.dto.FacturaRequest;
import com.gv.m.factura.dto.ProductoDto;
import com.gv.m.factura.dto.ProductoRequest;
import com.gv.m.factura.entity.Factura;
import com.gv.m.factura.entity.ProductoFactura;
import com.gv.m.factura.repository.FacturaRepository;
import com.gv.m.factura.repository.ProductoFacturaRepository;
import com.gv.m.factura.services.IFactura;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class FacturaService implements IFactura {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    @Autowired
    private ProductoFacturaRepository productoFacturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CLIENTES_SERVICE_URL = "http://localhost:8081/api/clientes/";
    private final String PRODUCTOS_SERVICE_URL = "http://localhost:8082/api/productos/";
    
    public ClienteDto getCliente(String nit) {
        try {
            return restTemplate.getForObject(CLIENTES_SERVICE_URL + nit, ClienteDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage(), e);
        }
    }

    public ProductoDto getProducto(Integer id) {
        try {
            return restTemplate.getForObject(PRODUCTOS_SERVICE_URL + id, ProductoDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findByNit(String nit) {
        return facturaRepository.findByNit(nit); 
    }

    @Override
    public Factura save(Factura factura) {
        ClienteDto cliente = getCliente(factura.getNit()); 
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con NIT: " + factura.getNit());
        }
        return facturaRepository.save(factura);
    }

    @Override
    public Factura crearFactura(FacturaRequest facturaRequest) {
        
        Factura factura = new Factura();
        factura.setClienteId(facturaRequest.getClienteId());
        factura.setFecha(LocalDate.now());
        factura.setTotal(0.0); 

       
        factura = facturaRepository.save(factura);

       
        for (ProductoRequest producto : facturaRequest.getProductosFactura()) {
            ProductoFactura productoFactura = new ProductoFactura();

            productoFactura.setProductoId(producto.getProductoId());
            productoFactura.setNombreProducto(producto.getNombreProducto()); 
            productoFactura.setPrecio(producto.getPrecio());
            productoFactura.setCantidad(producto.getCantidad());

            productoFacturaRepository.save(productoFactura);
            
            factura.setTotal(factura.getTotal() + (producto.getPrecio() * producto.getCantidad()));
        }

       
        facturaRepository.save(factura);

        return factura; 
 
    }    
    
    @Override
    public Factura update(Integer id, Factura factura) {
        if (facturaRepository.existsById(id)) {
            factura.setId(id);
            return facturaRepository.save(factura);
        }
        throw new RuntimeException("Factura no encontrada");
    }

    @Override
    public Integer deleteById(Integer id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return id;
        }
        throw new RuntimeException("Factura no encontrada");
    }       
}

