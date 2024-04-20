package com.creteria.sql.rest.api.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.CriteriaDefinition;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.beans.factory.annotation.Autowired;

import com.creteria.sql.rest.api.entity.Item;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomItemRepositoryImpl implements CustomItemRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Item> getItems() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		cr.select(root);

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;
	}

	@Override
	public Item findItemByPrice(Integer itemPrice) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		Predicate predicate = cb.gt(root.get("itemPrice"), itemPrice);

		cr.where(predicate);

		Query<Item> query = session.createQuery(cr);
		Item result = query.uniqueResult();

		return result;

	}

	@Override
	public List<Item> getItemsBetweenPrice(Integer startPrice, Integer endPrice) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		cr.select(root).where(cb.between(root.get("itemPrice"), startPrice, endPrice));

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;

	}

	@Override
	public List<Item> getItemsInNames(List<String> names) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		cr.select(root).where(root.get("itemName").in(names));

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;
	}

	@Override
	public List<Item> checkPropertyThenfindAllList(String propertyName) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		// cr.select(root).where(cb.isNull(root.get(propertyName)));
		cr.select(root).where(cb.isNotNull(root.get(propertyName)));

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;
	}

	@Override
	public List<Item> findAllListByMultipleExpression() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		Predicate[] predicates = new Predicate[2];
		predicates[0] = cb.isNull(root.get("itemDescription"));
		predicates[1] = cb.like(root.get("itemName"), "chair%");
		cr.select(root).where(predicates);

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;

	}

	@Override
	public List<Item> findAllLogicalOr() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		Predicate greaterThanPrice = cb.gt(root.get("itemPrice"), 1000);
		Predicate chairItems = cb.like(root.get("itemName"), "Chair%");
		cr.select(root).where(cb.or(greaterThanPrice, chairItems));

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;

	}

	@Override
	public List<Item> findAllLogicalAnd() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		Predicate greaterThanPrice = cb.gt(root.get("itemPrice"), 1000);
		Predicate chairItems = cb.like(root.get("itemName"), "Chair%");
		cr.select(root).where(cb.and(greaterThanPrice, chairItems));

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;

	}

	@Override
	public List<Item> findAllBySotring() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);

		cr.orderBy(cb.asc(root.get("itemName")), cb.desc(root.get("itemPrice")));

		Query<Item> query = session.createQuery(cr);
		List<Item> results = query.getResultList();
		return results;

	}

	@Override
	public List<Long> findAllItemCounts() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<Item> root = cr.from(Item.class);
		cr.select(cb.count(root));
		Query<Long> query = session.createQuery(cr);
		List<Long> itemProjected = query.getResultList();
		return itemProjected;
	}

	@Override
	public List<Double> findAllItemAVG() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Double> cr = cb.createQuery(Double.class);
		Root<Item> root = cr.from(Item.class);
		cr.select(cb.avg(root.get("itemPrice")));
		Query<Double> query = session.createQuery(cr);
		List<Double> avgItemPriceList = query.getResultList();
		return avgItemPriceList;
	}

	@Override
	public String updateItem() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaUpdate<Item> criteriaUpdate = cb.createCriteriaUpdate(Item.class);
		Root<Item> root = criteriaUpdate.from(Item.class);
		criteriaUpdate.set("itemPrice", 1000);
		criteriaUpdate.where(cb.equal(root.get("itemId"), 1));

		Transaction transaction = session.beginTransaction();
		session.createMutationQuery(criteriaUpdate).executeUpdate();
		transaction.commit();
		return "----------------Update------------------";
	}

	@Override
	public String deleteItem() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Item> criteriaDelete = cb.createCriteriaDelete(Item.class);
		Root<Item> root = criteriaDelete.from(Item.class);
		criteriaDelete.where(cb.greaterThan(root.get("itemPrice"), 100));

		Transaction transaction = session.beginTransaction();
		session.createMutationQuery(criteriaDelete).executeUpdate();
		transaction.commit();
		return "----------------Delete------------------";
	}

	@Override
	public List<Item> findItems() {
		final Session session = sessionFactory.openSession();
		CriteriaDefinition<Item> query = new CriteriaDefinition<>(sessionFactory, Item.class) {
			private static final long serialVersionUID = 1L;

			{
				@SuppressWarnings("unused")
				JpaRoot<Item> item = from(Item.class);
			}
		};
		List<Item> items = session.createSelectionQuery(query).list();
		return items;
	}

	@Override
	public List<Item> findAllItemsGreaterThanItemPrice() {
		final Session session = sessionFactory.openSession();
		CriteriaDefinition<Item> query = new CriteriaDefinition<>(sessionFactory, Item.class) {
			private static final long serialVersionUID = 1L;

			{
				JpaRoot<Item> item = from(Item.class);
				where(gt(item.get("itemPrice"), 1000));
			}
		};
		List<Item> items = session.createSelectionQuery(query).list();
		return items;
	}

	@Override
	public List<Item> findAllItemsBetweenItemPrice() {
		final Session session = sessionFactory.openSession();
		CriteriaDefinition<Item> query = new CriteriaDefinition<>(sessionFactory, Item.class) {
			private static final long serialVersionUID = 1L;

			{
				JpaRoot<Item> item = from(Item.class);
				where(between(item.get("itemPrice"), 100, 200));
			}
		};
		List<Item> items = session.createSelectionQuery(query).list();
		return items;

	}

}
