/**
 * 
 */
package w.fujiko.service.repo.departments;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.departments.DepartmentDao;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.service.departments.DepartmentService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class DepartmentServiceRepo 
		extends UniversalServiceRepo<Department, DepartmentDao, Integer> 
		implements DepartmentService {}
