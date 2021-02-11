package com.todo1.hulkstore.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;

public class GenericoDao<T> {

	@Inject
	protected EntityManager em;

	private Class<T> entidadClase;

	/**
	 * Constructor
	 * 
	 * @param entidadClase
	 */
	public GenericoDao(final Class<T> entidadClase) {
		this.entidadClase = entidadClase;
	}

	/**
	 * Generico para crear un nuevo registro
	 * 
	 * @param entidad a crear
	 * @throws NoGuardadoExcepcion 
	 * 
	 */
	public void crear(T entidadClase) throws NoGuardadoExcepcion {
		try {
		em.persist(entidadClase);
		} catch(Exception e) {
			throw new NoGuardadoExcepcion(e);
		}
	}

	/**
	 * Generico para actualizar un registro
	 * 
	 * @param entidad a modificar
	 * @return entidad 
	 */
	public T actualizar(T entidadClase) {
		return em.merge(entidadClase);
	}

	/**
	 * Generico para eliminar un registro
	 * 
	 * @param id identificador de la entidad a eliminar
	 */
	public void eliminar(final Long identificador) {
		T entidadClaseEliminar = obtenerPorId(identificador);
		em.remove(entidadClaseEliminar);
	}

	/**
	 * Generico para eliminar un registro
	 * 
	 * @param id  identificador de la entidad a eliminar
	 */
	public void eliminar(final String identificador) {
		T entidadClaseEliminar = obtenerPorId(identificador);
		em.remove(entidadClaseEliminar);
	}

	/**
	 * Generico para buscar un registro correspondiente a su identificador principal
	 * 
	 * @param identificador identificador de la entidad a buscar
	 * @return entidad buscada o null si no la encuentra
	 */
	public T obtenerPorId(final Long identificador) {
		return em.find(entidadClase, identificador);
	}

	/**
	 * Generico para buscar un registro correspondiente a su identificador principal
	 * 
	 * @param identificador identificador de la entidad a buscar
	 * @return entidad buscada o null si no la encuentra
	 */
	public T obtenerPorId(final String identificador) {
		return em.find(entidadClase, identificador);
	}

	/**
	 * Generico para obtener todos los registros
	 * 
	 * @return lista de registros correspondiente a la entidad
	 */
	public List<T> obtenerTodo() {
		TypedQuery<T> consulta = em.createQuery("Select t from " + entidadClase.getSimpleName() + " t", entidadClase);
		return consulta.getResultList();
	}

	/**
	 * Generico para obtener todos los registros ordenados por el parámetor enviado
	 * @param columnaOrden nombre de la columna
	 * @param tipoOrden ASC ascendente o DESC desecendente
	 * @return
	 */
	public List<T> obtenerTodoOrdenadoPorParametro(String columnaOrden, String tipoOrden) {
		TypedQuery<T> consulta = em.createQuery(
				"Select t from " + entidadClase.getSimpleName() + " t order by t." + columnaOrden + " " + tipoOrden,
				entidadClase);
		return consulta.getResultList();
	}

	public List<T> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(entidadClase);
		Root<T> root = criteria.from(entidadClase);

		criteria.select(root).orderBy(cb.asc(root.get("nombre")));
		return em.createQuery(criteria).getResultList();
	}


	/**
	 * Generico para obtener todos los registros paginados
	 * 
	 * @param numeroDePagina     numero de la pagina
	 * @param elementosPorPagina tamanio o elementos de la pagina
	 * @return lista de registros correspondiente a la tabla de tipo entidiad
	 *         agrupados en paginas
	 */
	public List<T> obtenerPorRangos(int numeroDePagina, int elementosPorPagina) {
		TypedQuery<T> consulta = em.createQuery("Select t from " + entidadClase.getSimpleName() + " t", entidadClase);
		consulta.setFirstResult((numeroDePagina - 1) * elementosPorPagina);
		consulta.setMaxResults(elementosPorPagina);
		return consulta.getResultList();
	}

	/**
	 * Generico que cuenta los registros de una entidad
	 * 
	 * @return numero de registros que tienen la entidad o 0 si no tiene registros
	 */
	@SuppressWarnings("null")
	public int contador() {
		TypedQuery<T> consulta = em.createQuery("Select t from " + entidadClase.getSimpleName() + " t", entidadClase);
		List<T> listaRegistros = consulta.getResultList();
		if (listaRegistros != null || !listaRegistros.isEmpty()) {
			return listaRegistros.size();
		} else {
			return 0;
		}
	}
}
