package w.fujiko.dao.repo.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.systems.ProgramCommandDao;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.Program_;
import w.fujiko.model.masters.systems.ProgramCommand;
import w.fujiko.model.masters.systems.ProgramCommand_;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgram_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.service.systems.ProgramService;

@Repository
@Transactional
public class ProgramCommandDaoRepo 
	extends UniversalCrudRepo<ProgramCommand,Integer>
	implements ProgramCommandDao {
		public ProgramCommandDaoRepo() {
		super();
		type = ProgramCommand.class;
	}

	@Autowired
	ProgramService programService;

	@Override
	public List<Program> getGrantedProgramCommandByUser(Integer userId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProgramCommand> criteria = builder.createQuery(ProgramCommand.class);		
		Root<ProgramCommand> root = criteria.from(type);

		Join<ProgramCommand,Program> programJoined = root.join(ProgramCommand_.program);		
		Join<Program,RoleProgram> roleProgramJoined = programJoined.join(Program_.roles);
		Join<RoleProgram,User> userJoined = roleProgramJoined.join(RoleProgram_.authorized_user);

		criteria
			.select(root)
			.distinct(true)
			.where(builder.equal(userJoined.get(User_.id), userId))			  
			.orderBy(builder.asc(root.get(ProgramCommand_.key_code)));
		try {			

			List<Program> programList=new ArrayList<>();
			List<ProgramCommand> ProgramCommandList = session.createQuery(criteria).getResultList();	
			
			ProgramCommandList.stream().forEach(command->{
				command.setRole_program_commands(command.getRole_program_commands()
											.stream()
											.filter(roleCommand->roleCommand.getRole_program()
															.getAuthorized_user()
															.getId()==userId)
											.collect(Collectors.toSet())
										);										
					
			});

			ProgramCommandList.stream().collect(Collectors.groupingBy(ProgramCommand::getProgram_id,Collectors.toSet()))
				.entrySet().forEach(e->{					
					programService.get(e.getKey()).ifPresent(prog->{
						prog.setCommands(e.getValue()
										  .stream()
										  .sorted((n1,n2)->Integer.valueOf(n1.getKey_code())
										  						 .compareTo(Integer.valueOf(n2.getKey_code())))
																 .collect(Collectors.toList()));									
						programList.add(prog);
					});
			});

			session.close();
			return programList;
		} catch (NoResultException nre) {
			session.close();
			return new ArrayList<>();
		}						
	}

}
