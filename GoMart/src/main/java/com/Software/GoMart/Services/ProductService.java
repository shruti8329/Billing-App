//package com.Software.GoMart.Services;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.Software.GoMart.Entites.ProductMaster;
//import com.Software.GoMart.Repositiory.ProductRepositiroy;
//
//@Service
//public class ProductService {
//	
//	@Autowired
//	ProductRepositiroy productRepo;
//	
//	private final Path rootLocation = Paths.get("src/main/resources/static/images");  // give path to image store
//	
//	// To Handel Uploaded Image
//	private String saveImage(MultipartFile image) throws IOException{
//		if(image != null && !image.isEmpty()) {
//			Path product_image = this.rootLocation.resolve(image.getOriginalFilename());
//			Files.copy(image.getInputStream(), product_image);
//			return product_image.toString();
//		}
//		
//		return null;
//	}
//	
//	
//	// Add Product Logic
//	public ProductMaster addProduct(String Productname, int categoryid, double product_price, MultipartFile image) throws IOException{
//		ProductMaster product = new ProductMaster();
//		product.setCreatedon(LocalDate.now());
//		product.setProductname(Productname);
//		product.setCategoryid(categoryid);
//		product.setProduct_price(product_price);
//		 
//		String product_image = saveImage(image);
//		product.setProduct_image(product_image);
//		return productRepo.save(product);
//	}
//	
//	// If Product Already Present Logic
//	public boolean isProductAllreadyExists(String productname) {
//		return productRepo.findByProductname(productname).isPresent();
//	}
//	
//	
//	// Update Product Logic 
//	public ProductMaster updateProduct(int productid, String productname, int categoryid, double product_price, MultipartFile image ) throws IOException {
//		ProductMaster product = productRepo.findById(productid).orElseThrow(() -> new RuntimeException("Product Not Found"));
//		product.setCreatedon(LocalDate.now());
//		product.setProductname(productname);
//		product.setCategoryid(categoryid);
//		product.setProduct_price(product_price);
//		
//		if(image != null && !image.isEmpty()) {
//			
//			String product_image = saveImage(image);
//			product.setProduct_image(product_image);	
//		}
//		
//		return productRepo.save(product);
//	}
//	
//	
//	// Delete Product Logic 
//	public void deleteProduct(int productid) {
//		ProductMaster product = productRepo.findById(productid).orElseThrow(() -> new RuntimeException(" Product Not Found"));
//		if(product.getProduct_image() != null) {
//			
//			Path Product_image = Paths.get(product.getProduct_image());
//			
//			try {
//				Files.deleteIfExists(Product_image);
//			}
//			catch(IOException e) {
//				
//				throw new RuntimeException("Failed to delete image",e);
//			}
//		}
//		
//		productRepo.deleteById(productid);
//	}
//	
//	//Get All Products
//	public List<ProductMaster> getAllProducts(){
//		return productRepo.findAll();
//	}
//	
//	// Get Products List By Products Category Name Logic
//	public List<ProductMaster> getProductByCategoryName(String categoryname){
//		return productRepo.findByCategoryMaster(categoryname);
//	}
//	
//
//}


package com.Software.GoMart.Services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Software.GoMart.Entites.ProductMaster;
import com.Software.GoMart.Repositiory.ProductRepositiroy;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ProductService {
	
	@Autowired
	ProductRepositiroy productRepo;
	
	@Autowired
	private Cloudinary cloudinary;

	// Upload Image to Cloudinary
	private String uploadImage(MultipartFile image) throws IOException {
		if (image != null && !image.isEmpty()) {
			var uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
			return uploadResult.get("url").toString();
		}
		return null;
	}

	// Add Product Logic
	public ProductMaster addProduct(String productname, int categoryid, double product_price, MultipartFile image) throws IOException {
		ProductMaster product = new ProductMaster();
		product.setCreatedon(LocalDate.now());
		product.setProductname(productname);
		product.setCategoryid(categoryid);
		product.setProduct_price(product_price);
		
		String product_image = uploadImage(image);
		product.setProduct_image(product_image);

		return productRepo.save(product);
	}

	// Check if Product Already Exists
	public boolean isProductAllreadyExists(String productname) {
		return productRepo.findByProductname(productname).isPresent();
	}

	// Update Product Logic
	public ProductMaster updateProduct(int productid, String productname, int categoryid, double product_price, MultipartFile image) throws IOException {
		ProductMaster product = productRepo.findById(productid).orElseThrow(() -> new RuntimeException("Product Not Found"));
		product.setCreatedon(LocalDate.now());
		product.setProductname(productname);
		product.setCategoryid(categoryid);
		product.setProduct_price(product_price);

		if (image != null && !image.isEmpty()) {
			String product_image = uploadImage(image);
			product.setProduct_image(product_image);
		}

		return productRepo.save(product);
	}

	// Delete Product Logic
	@SuppressWarnings("unused")
	public void deleteProduct(int productid) {
		ProductMaster product = productRepo.findById(productid).orElseThrow(() -> new RuntimeException(" Product Not Found"));
		productRepo.deleteById(productid);
	}

	// Get All Products
	public List<ProductMaster> getAllProducts() {
		return productRepo.findAll();
	}

	// Get Products List By Category Name
	public List<ProductMaster> getProductByCategoryName(String categoryname) {
		return productRepo.findByCategoryMaster(categoryname);
	}
}


