/**
 * 
 */
package w.fujiko.service.repo.branches;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.branches.BranchDao;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.service.branches.BranchService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class BranchServiceRepo 
		extends UniversalServiceRepo<Branch, BranchDao, Integer> 
		implements BranchService {}
