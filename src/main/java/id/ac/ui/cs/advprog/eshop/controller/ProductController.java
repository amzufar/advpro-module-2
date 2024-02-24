package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    private static final String productString = "product";

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute(productString, product);
        return "createproduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productlist";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute(productString, product);
        return "editproduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@ModelAttribute Product updatedProduct, @PathVariable String productId) {
        updatedProduct.setProductId(productId);
        service.update(updatedProduct);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute(productString, product);
        return "deleteproduct";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProductPost(@PathVariable String productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }
}
