package w.fujiko.api.products;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Response;
import w.fujiko.model.masters.products.ProductClassification;
import w.fujiko.service.products.ProductClassificationService;
import w.fujiko.util.common.constants.ProductConstants;



@RestController
@RequestMapping("/api/product/classifications")
public class ProductClassificationApi 
	extends Api<ProductClassificationService, ProductClassification, Integer> {
	
	@GetMapping("/search")
	public @ResponseBody List<ProductClassification> getAllProductClassification(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		List<ProductClassification> entities = 
			service.getProductClassificationsByItemNameOrCode(
					key, Integer.parseInt(index));
		
		return entities;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProductClassification
		(@Valid @RequestBody ProductClassification classification, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<ProductClassification> classificationResult = 
					service.getUndeletedProductClassificationByCode(classification.getCode());
			
			if(classificationResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(ProductConstants.MSG_CONFLICT);
			} else {
				List<Response> response = service.saveOrUpdate(classification);
				return ResponseEntity.ok().body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ProductConstants.MSG_500);
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProductClassification
		(@Valid @RequestBody ProductClassification classification, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Optional<ProductClassification> classificationResult = 
					service.getUndeletedProductClassificationByCode(classification.getCode());
			
			if(classificationResult.isPresent()) {
				String classificationCode = classificationResult.get().getCode();
				if(classificationCode.equals(classification.getCode())) {
					List<Response> response = service.saveOrUpdate(classification);
					return ResponseEntity.ok().body(response);
				} else {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body(ProductConstants.MSG_CONFLICT);
				}
			} else {
				List<Response> response = service.saveOrUpdate(classification);
				return ResponseEntity.ok().body(response);	
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ProductConstants.MSG_500);
		}		
	}
	
	@GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> generatePDF(
			@RequestParam(value = "from", defaultValue = "") String from,
			@RequestParam(value = "to", defaultValue = "") String to) {
				
		try {			
			HttpHeaders headers = getHeaders("list.pdf");
			return ResponseEntity.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(service.exportToPDF(from, to)));
		} catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ProductConstants.MSG_500);
		} 
		
	}
	
	@GetMapping(value = "/export-csv", produces = "text/csv")
	public ResponseEntity<?> generateCSV(
			@RequestParam(value = "from", defaultValue = "") String from,
			@RequestParam(value = "to", defaultValue = "") String to) {
					
		try {
			HttpHeaders headers = getHeaders("list.csv");
			return ResponseEntity.ok()
					.headers(headers)
					.body(service.exportToCSV(from, to));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ProductConstants.MSG_500);
		}
	}
	
	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}
	

}