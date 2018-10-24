package w.fujiko.service.repo.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.systems.ProgramCategoryDao;
import w.fujiko.dao.systems.ProgramDao;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.service.systems.ProgramCategoryService;

@Service
@Transactional
public class ProgramCategoryServiceRepo 
	extends UniversalServiceRepo<ProgramCategory,ProgramCategoryDao,String> 
	implements ProgramCategoryService {
	
		@Autowired ProgramDao programDao;		

		@Override
		public List<ProgramCategory> getGrantedProgramInCategoryOfUser(Integer userId) {
			
			List<ProgramCategory> allCategoryEntities = dao.getAllCategorySortedByCategoryId();
			List<Program> programEntities = programDao.getGrantedProgramsOfUser(userId);
					
			if(programEntities != null) {
				
				// Get categoryEntity for each programEntity
				List<ProgramCategory> categoryEntities = programEntities.stream()
						.map(el -> el.getCategory())
						.collect(Collectors.toList());
				
				categoryEntities = removeDuplicate(categoryEntities);
				allCategoryEntities = transform(allCategoryEntities, categoryEntities);
				
				return allCategoryEntities;
			}
			return null;
		}		
		
		private List<ProgramCategory> removeDuplicate(List<ProgramCategory> programEntities) {
			return new ArrayList<>(new HashSet<>(programEntities));
		}
		
		private List<ProgramCategory> transform(List<ProgramCategory> allCategoryEntities, 
				List<ProgramCategory> currentCategoryEntities) {
			for(ProgramCategory programCategory : allCategoryEntities) {
				String programCategoryId = programCategory.getId();
				for(ProgramCategory currentProgramCategory : currentCategoryEntities) {
					String currentCategoryId = currentProgramCategory.getId();
					if(programCategoryId == currentCategoryId) {
						programCategory.setPrograms(currentProgramCategory.getPrograms());
					}
				}
			}
			return allCategoryEntities;
		}
}