package w.fujiko.dao.repo.departments;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.dao.departments.DepartmentDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class DepartmentDaoRepo 
	extends UniversalCrudRepo<Department ,Integer>
	implements DepartmentDao {

		public DepartmentDaoRepo() {
			super();
			type = Department.class;
		}
}
