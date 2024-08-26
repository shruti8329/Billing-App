package com.Software.GoMart.Repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Software.GoMart.Entites.ReceiptMaster;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptMaster, Integer> {
    // Custom queries if needed
	
}
