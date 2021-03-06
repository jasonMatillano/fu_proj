package w.fujiko.service.products;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.products.ProductClassification;

@Service
public interface ProductClassificationService 
	extends UniversalCrud<ProductClassification, Integer> {

	public List<ProductClassification> 
		getProductClassificationsByItemNameOrCode(String key, Integer index);
	
	public Optional<ProductClassification> 
		getUndeletedProductClassificationByCode(String code);
	
	public ByteArrayInputStream exportToPDF(String from, String to) throws Exception;
	public String exportToCSV(String from, String to) throws Exception;
	
}
