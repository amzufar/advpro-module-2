package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testServiceCreateProduct() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(createdProduct.getProductId(), product.getProductId());
        assertEquals(createdProduct.getProductName(), product.getProductName());
        assertEquals(createdProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList.iterator());
        List<Product> output = productService.findAll();
        assertEquals(productList, output);
    }

    @Test
    void testFindById() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);
        Product output = productService.findById(productId);
        assertEquals(product, output);
    }

    @Test
    void testFindById_NotFound() {
        String nonExistentId = "1";
        when(productRepository.findById(nonExistentId)).thenReturn(null);
        Product foundProduct = productService.findById(nonExistentId);
        assertNull(foundProduct);
    }

    @Test
    void testUpdate_ProductNotFound() {
        String nonExistentId = "1";
        Product updatedProduct = new Product();
        updatedProduct.setProductId(nonExistentId);
        when(productRepository.findById(nonExistentId)).thenReturn(null);
        productService.update(updatedProduct);
        verify(productRepository, never()).update(any());
    }

    @Test
    void testDelete_ProductNotFound() {
        String nonExistentId = "1";
        when(productRepository.findById(nonExistentId)).thenReturn(null);
        productService.delete(nonExistentId);
        verify(productRepository, never()).delete(any());
    }

    @Test
    public void testServiceUpdateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        productService.update(product);

        verify(productRepository, times(1)).update(product);;
    }

    @Test
    public void testServiceDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        when(productRepository.findById(product.getProductId())).thenReturn(product);
        productService.delete(product.getProductId());
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}
