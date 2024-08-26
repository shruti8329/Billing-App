//package com.Software.GoMart.Controllers;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.Software.GoMart.Entites.ProductMaster;
//import com.Software.GoMart.Services.ProductService;
//
//@RestController
//@RequestMapping("/GoMart/product")
//@CrossOrigin(origins = "http://localhost:5173")
//public class ProductController {
//	
//	@Autowired
//	ProductService productService;
//	
//	// Get All Products List
//	@GetMapping
//	public List<ProductMaster> getAllProducts(){
//		return productService.getAllProducts();
//	}
//	
//	
//	// Add Product API
//	@PostMapping("/addProduct")
//	public ResponseEntity<String> addProduct(@RequestParam("productname") String productname, @RequestParam("categoryid") int categoryid,@RequestParam("product_price") double product_price,@RequestParam("image") MultipartFile image){
//		if(productService.isProductAllreadyExists(productname)) {
//			
//			System.err.println("===== Product all ready Exists =====");
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Product Already Exists");
//		}
//		else {
//			try {
//				productService.addProduct(productname, categoryid, product_price, image);
//				System.err.println(" ===== Product Added ====");
//				return ResponseEntity.status(HttpStatus.CREATED).body("Product Added Successfully");
//			}
//			catch( IOException e) {
//				
//				System.out.println("===== Failed to upload image =====");
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//			}
//		}
//	}
//	
//	
//	// Update Product API 
//	@PutMapping("/updateProduct/{productid}")
//	public ResponseEntity<String> updateProduct(@PathVariable int productid, @RequestParam("productname") String productname, @RequestParam("categoryid") int categoryid, @RequestParam("product_price") double product_price, @RequestParam("image") MultipartFile image ){
//		
//		try {
//			
//			productService.updateProduct(productid, productname, categoryid, product_price, image);
//			System.out.println("===== Product Updated Successfully =====");
//			return ResponseEntity.ok("Product Updated Successfully");
//		}
//		catch(IOException e) {
//			
//			System.out.println("===== Failed to upload image =====");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//			
//		}
//	}
//	
//	
//	// Delete Product API
//	@DeleteMapping("/deleteProduct/{productid}")
//	public ResponseEntity<String> deleteProduct(@PathVariable int productid) {
//		productService.deleteProduct(productid);
//		System.out.println("===== Product Deleted Successfully =====");
//		return ResponseEntity.ok("Product Deleted Successfully");
//	}
//	
//	// Product List of Category Name
//	@GetMapping("/{categoryname}")
//	public ResponseEntity<List<ProductMaster>> getProductByCategoryName(@PathVariable String categoryname){
//		List<ProductMaster> products = productService.getProductByCategoryName(categoryname);
//		return ResponseEntity.ok(products);
//	}
//
//}


package com.Software.GoMart.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Software.GoMart.Entites.ProductMaster;
import com.Software.GoMart.Services.ProductService;

@RestController
@RequestMapping("/GoMart/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	
	@Autowired
	ProductService productService;

	// Get All Products List
	@GetMapping
	public List<ProductMaster> getAllProducts() {
		return productService.getAllProducts();
	}

	// Add Product API
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestParam("productname") String productname,
	                                         @RequestParam("categoryid") int categoryid,
	                                         @RequestParam("product_price") double product_price,
	                                         @RequestParam("productimage") MultipartFile productimage) {
		if (productService.isProductAllreadyExists(productname)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Product Already Exists");
		} else {
			try {
				productService.addProduct(productname, categoryid, product_price, productimage);
				return ResponseEntity.status(HttpStatus.CREATED).body("Product Added Successfully");
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
			}
		}
	}

	// Update Product API
	@PutMapping("/updateProduct/{productid}")
	public ResponseEntity<String> updateProduct(@PathVariable int productid,
	                                            @RequestParam("productname") String productname,
	                                            @RequestParam("categoryid") int categoryid,
	                                            @RequestParam("product_price") double product_price,
	                                            @RequestParam("productimage") MultipartFile productimage) {
		try {
			productService.updateProduct(productid, productname, categoryid, product_price, productimage);
			return ResponseEntity.ok("Product Updated Successfully");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
		}
	}

	// Delete Product API
	@DeleteMapping("/deleteProduct/{productid}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productid) {
		productService.deleteProduct(productid);
		return ResponseEntity.ok("Product Deleted Successfully");
	}

	// Product List by Category Name
	@GetMapping("/{categoryname}")
	public ResponseEntity<List<ProductMaster>> getProductByCategoryName(@PathVariable String categoryname) {
		List<ProductMaster> products = productService.getProductByCategoryName(categoryname);
		return ResponseEntity.ok(products);
	}
}


