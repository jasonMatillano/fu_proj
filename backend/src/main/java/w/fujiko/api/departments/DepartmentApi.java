package w.fujiko.api.departments;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.service.departments.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentApi
	extends Api<DepartmentService,Department,Integer> {

}