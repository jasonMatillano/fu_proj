package w.fujiko.dao.repo;
/**
 * @author johnl
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.WarehouseDao;
import w.fujiko.model.masters.Warehouse;

@Repository
@Transactional
public class WarehouseDaoRepo 
	extends UniversalCrudRepo<Warehouse, Integer> 
		implements WarehouseDao{
	
	public WarehouseDaoRepo() {
		super();
		type = Warehouse.class;
	}
	
}
