package w.fujiko.service;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Warehouse;

/**
 * 
 * @author johnl
 *
 */
@Service
public interface WarehouseService extends UniversalCrud<Warehouse, Integer> {

}
