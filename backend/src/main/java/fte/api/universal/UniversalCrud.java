package fte.api.universal;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import fte.api.Page;
import fte.api.Response;

/**
 * @author Try-Parser
 * @category fte.api
 */
public interface UniversalCrud <T, I extends Serializable> {
	
	/**
	 * @param first
	 * @param max
	 * @return Page
	 * @see fte.api.Page
	 */
	public Page<T> paginate(int first, int max);
	
	/**
	 * @return List
	 */
	public List<T> get();
	
	/**
	 * @param id
	 * @return Optional
	 */
	public Optional<T> get(I id);
	
	/**
	 * @param t
	 * @return Response
	 * @see fte.api.response
	 */
	public List<Response> saveOrUpdate(T t);
	
	/**
	 * 
	 * @param flag
	 * @return Boolean
	 */
	public List<Response> delete(T t);

	/**
	 * 
	 * @param flag
	 * @return Boolean
	 */
	public List<Response> disable(Boolean flag, I id);
}
