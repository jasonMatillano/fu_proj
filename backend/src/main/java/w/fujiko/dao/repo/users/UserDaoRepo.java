package w.fujiko.dao.repo.users;

import java.lang.Override;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.dao.users.UserDao;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class UserDaoRepo 
	extends UniversalCrudRepo<User ,Integer>
	implements UserDao {

		public UserDaoRepo() {
			super();
			type = User.class;
		}

		@Override
		public Optional<User> getByName(String email) {
			Session session = getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			criteria.select(root).where(builder.and(builder.equal(root.get(User_.username), email), builder.equal(root.get(User_.isResigned), false)));
			
			try { 
				User user = session.createQuery(criteria).getSingleResult();
				session.close();
				return Optional.of(user);
			} catch (NoResultException nre) {
				session.close();
				return Optional.empty();
			}
		}
		
		@Override
		public Optional<User> getByUserId(Integer id) {
			Session session = getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			criteria.select(root)
					.where(builder
						   .and(builder
								.equal(root.get(User_.id), id)
									  ,builder.equal(root.get(User_.isResigned), false)));

			try { 
				User user = session.createQuery(criteria).getSingleResult();
				session.close();
				return Optional.of(user);
			} catch (NoResultException nre) {
				session.close();
				return Optional.empty();
			}
		}
}
