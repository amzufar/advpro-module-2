package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    ProductService productService;

    @Mock
    Model model;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String output = productController.createProductPage(model);
        assertEquals("createProduct", output);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String output = productController.createProductPost(product, model);
        assertEquals("redirect:list", output);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);
        String output = productController.productListPage(model);
        assertEquals("productList", output);
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testEditProductPage() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        when(productService.findById(productId)).thenReturn(product);
        String output = productController.editProductPage(productId, model);
        assertEquals("editProduct", output);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product updatedProduct = new Product();
        String output = productController.editProductPost(updatedProduct, productId);
        assertEquals("redirect:/product/list", output);
    }

    @Test
    void testDeleteProductPage() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        when(productService.findById(productId)).thenReturn(product);
        String output = productController.deleteProductPage(productId, model);
        assertEquals("deleteProduct", output);
    }

    @Test
    void testDeleteProductPost() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String output = productController.deleteProductPost(productId);
        assertEquals("redirect:/product/list", output);
    }
}
