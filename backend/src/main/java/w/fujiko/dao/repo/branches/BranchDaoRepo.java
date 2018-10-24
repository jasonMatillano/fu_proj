package w.fujiko.dao.repo.branches;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.dao.branches.BranchDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class BranchDaoRepo 
	extends UniversalCrudRepo<Branch ,Integer>
	implements BranchDao {

		public BranchDaoRepo() {
			super();
			type = Branch.class;
		}
}
