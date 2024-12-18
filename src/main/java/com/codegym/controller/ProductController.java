package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("list");
		List<Product> products = productService.findAll();
		mv.addObject("products", products);
		return mv;
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("product", new Product());
		return "create";
	}
	@PostMapping("/save")
	public String save(Product product, RedirectAttributes redirectAttributes) {
		do {
			product.setId((int) (Math.random() * 10000));
		} while (productService.findById(product.getId()) != null);
		productService.add(product);
		redirectAttributes.addFlashAttribute("success", "Product successfully added");
		return "redirect:/products";
	}
}
