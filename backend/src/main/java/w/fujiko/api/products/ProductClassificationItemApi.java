package w.fujiko.api.products;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Response;
import w.fujiko.model.masters.products.ProductClassificationItem;
import w.fujiko.service.products.ProductClassificationItemService;
import w.fujiko.util.common.constants.ProductConstants;

@RestController
@RequestMapping("/api/product")
public class ProductClassificationItemApi 
	extends Api<ProductClassificationItemService, ProductClassificationItem, Integer> {
	
	@GetMapping("/{classificationId}/items")
	public @ResponseBody List<ProductClassificationItem> 
		getProductsByClassificationId(
				@PathVariable(value="classificationId") String productClassId,
				@RequestParam(value = "index", defaultValue = "0") String index) {
		
		Integer id = Integer.parseInt(productClassId);
		Integer indexNo = Integer.parseInt(index);
		List<ProductClassificationItem> entities = service.getProductsByClassificationId(id, indexNo);
		
		return entities;
	}
	
	@GetMapping("/{classificationId}/items/search")
	public @ResponseBody List<ProductClassificationItem> 
		getProductsByClassificationItemNameOrCode(
				@PathVariable(value="classificationId") Integer classificationId,
				@RequestParam(value = "key", defaultValue = "") String key,
				@RequestParam(value = "index", defaultValue = "0") String index) {
		
		List<ProductClassificationItem> entities = 
				service.getProductsByClassificationItemNameOrCode(
						classificationId, key, Integer.parseInt(index));
		
		return entities;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProductClassificationItem
		(@Valid @RequestBody ProductClassificationItem classificationItem, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<ProductClassificationItem> classificationItemResult = 
					service.getUndeletedProductClassificationItemByCode(classificationItem.getCode());
			
			if(classificationItemResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(ProductConstants.MSG_CONFLICT_ITEM);
			} else {
				List<Response> response = service.saveOrUpdate(classificationItem);
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
	public ResponseEntity<?> updateProductClassificationItem
		(@Valid @RequestBody ProductClassificationItem classificationItem, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Optional<ProductClassificationItem> classificationItemResult = 
					service.getUndeletedProductClassificationItemByCode(classificationItem.getCode());
			
			if(classificationItemResult.isPresent()) {
				String classificationItemCode = classificationItemResult.get().getCode();
				if(classificationItemCode.equals(classificationItem.getCode())) {
					List<Response> response = service.saveOrUpdate(classificationItem);
					return ResponseEntity.ok().body(response);
				} else {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body(ProductConstants.MSG_CONFLICT_ITEM);
				}			
			} else {			
				List<Response> response = service.saveOrUpdate(classificationItem);
				return ResponseEntity.ok().body(response);	
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ProductConstants.MSG_500);
		}		
	}

}