package com.Software.GoMart.Services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Software.GoMart.Entites.CategoryMaster;
import com.Software.GoMart.Repositiory.CategoryRepositiroy;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepositiroy categoryRepo;
	
	
	 @Autowired
	    private Cloudinary cloudinary;

	    @SuppressWarnings("rawtypes")
		private String uploadImageToCloudinary(MultipartFile image) throws IOException {
	        if (image != null && !image.isEmpty()) {
	            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
	            return uploadResult.get("url").toString();
	        } else {
	            return null;
	        }
	    }

	    public CategoryMaster addCategory(String categoryname, MultipartFile image) throws IOException {
	        CategoryMaster category = new CategoryMaster();
	        category.setCreatedon(LocalDate.now());
	        category.setCategoryname(categoryname);
	        String category_image_url = uploadImageToCloudinary(image);
	        category.setCategory_image(category_image_url);
	        return categoryRepo.save(category);
	    }

	    public boolean isCategoryAllreadyExists(String categoryname) {
	        return categoryRepo.findByCategoryname(categoryname).isPresent();
	    }

	    public CategoryMaster updateCategory(int categoryid, String categoryname, MultipartFile image) throws IOException {
	        CategoryMaster category = categoryRepo.findById(categoryid).orElseThrow(() -> new RuntimeException("Category Not Found"));
	        category.setCreatedon(LocalDate.now());
	        category.setCategoryname(categoryname);

	        if (image != null && !image.isEmpty()) {
	            String category_image_url = uploadImageToCloudinary(image);
	            category.setCategory_image(category_image_url);
	        }

	        return categoryRepo.save(category);
	    }


	
	
	// Delete Category Logic
	   
	@SuppressWarnings("unused")
	public void deleteCategory(int categoryid) {
		CategoryMaster category = categoryRepo.findById(categoryid).orElseThrow(() -> new RuntimeException("Category Not Found"));
		categoryRepo.deleteById(categoryid);
	}
	
	
	//Find All Categories Logic
	public List<CategoryMaster> getAllCategories(){
		return categoryRepo.findAll();
	}

	public boolean categoryExists(int categoryid) {
		return categoryRepo.findById(categoryid).isPresent();
	}
	
	
	
}
