package w.fujiko.api.branches;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.service.branches.BranchService;

@RestController
@RequestMapping("/api/branch")
public class BranchApi
	extends Api<BranchService,Branch,Integer> {

}