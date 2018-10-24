package w.fujiko.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.Warehouse;
import w.fujiko.service.WarehouseService;

/**
 * 
 * @author johnl
 *
 */
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseApi extends Api<WarehouseService, Warehouse, Integer>{}
