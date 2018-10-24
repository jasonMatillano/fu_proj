package w.fujiko.dao.repo.products;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.products.ProductClassificationDao;
import w.fujiko.model.masters.products.ProductClassification;
import w.fujiko.util.common.constants.ProductConstants;

@Repository
@Transactional
public class ProductClassificationDaoRepo 
	extends UniversalCrudRepo<ProductClassification, Integer>
	implements ProductClassificationDao {
	
	public ProductClassificationDaoRepo() {
		super();
		type = ProductClassification.class;
	}

	@Override
	public List<ProductClassification> getProductClassificationsByItemNameOrCode(String key, Integer index) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductClassification> criteria = builder.createQuery(type);
		Root<ProductClassification> root = criteria.from(type);
		root.fetch(ProductConstants.FIELD_PRODUCT_ITEMS, JoinType.INNER);
		
		criteria.select(root)
			.where(
				builder.or(
					builder.like(
						builder.lower(root.get(ProductConstants.FIELD_CODE)), 
						"%"+key+"%"
					),
					builder.like(
						builder.lower(root.get(ProductConstants.FIELD_NAME)), 
						"%"+key+"%"
					)
				)
			);
		
		List<ProductClassification> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<ProductClassification> getUndeletedProductClassificationByCode(String code) {

		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductClassification> criteria = builder.createQuery(type);
		Root<ProductClassification> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(ProductConstants.FIELD_CODE), code), 
					builder.equal(root.get(ProductConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<ProductClassification> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;


	}

	@Override
	public List<ProductClassification> getProductClassificationsByCode(String from, String to) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductClassification> criteria = builder.createQuery(type);
		criteria.distinct(true);
		Root<ProductClassification> root = criteria.from(type);
		root.fetch(ProductConstants.FIELD_PRODUCT_ITEMS, JoinType.INNER);
		
		criteria.select(root)
			.where(builder.between(builder.lower(root.get(ProductConstants.FIELD_CODE)), from, to));
		
		List<ProductClassification> t = session.createQuery(criteria)
			.getResultList();
		
		session.close();
		return t;
		
	}

}