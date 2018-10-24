/**
 * 
 */
package fte.api.universal;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.Response;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class UniversalServiceRepo<C, D extends UniversalCrud<C, I>, I extends Serializable> implements UniversalCrud<C, I> {

	public @Autowired D dao;
	
	@Override
	public Page<C> paginate(int first, int max) {
		return dao.paginate(first, max);
	}

	@Override
	public List<C> get() {
		return dao.get();
	}

	@Override
	public Optional<C> get(I id) {
		return dao.get(id);
	}

	@Override
	public List<Response> saveOrUpdate(C t) {
		return dao.saveOrUpdate(t);
	}

	@Override
	public List<Response> delete(C t) {
		return dao.delete(t);
	}

	@Override
	public List<Response> disable(Boolean flag, I id) {
		return dao.disable(flag, id);
	}

}
