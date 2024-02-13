package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(productId);
        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindById_NotFound() {
        Product product = new Product();
        product.setProductId("1");
        productRepository.create(product);
        Product foundProduct = productRepository.findById("2");
        assertNull(foundProduct);
    }

    @Test
    void testUpdate_ProductNotFound() {
        Product product = new Product();
        product.setProductId("1");
        productRepository.create(product);
        Product updatedProduct = new Product();
        updatedProduct.setProductId("2");
        Product returnedProduct = productRepository.update(updatedProduct);
        assertNull(returnedProduct);
    }

    @Test
    void testUpdateProduct() {
        Product initialProduct = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        initialProduct.setProductId(productId);
        initialProduct.setProductName("Sampo Cap Bambang");
        initialProduct.setProductQuantity(100);

        productRepository.create(initialProduct);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Sampo Cap Bango");
        updatedProduct.setProductQuantity(150);

        productRepository.update(updatedProduct);

        Product foundProduct = productRepository.findById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
        assertEquals(updatedProduct.getProductName(), foundProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product productToDelete = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        productToDelete.setProductId(productId);
        productToDelete.setProductName("Sampo Cap Bambang");
        productToDelete.setProductQuantity(100);

        productRepository.create(productToDelete);

        productRepository.delete(productId);

        Product foundProduct = productRepository.findById(productId);

        assertNull(foundProduct);
    }
}
