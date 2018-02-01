package repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import models.Expenses;

public class ExpensesRepositoryImpl implements ExpensesRepositoryCustom {

	@Autowired
	EntityManager em;

	@Transactional
	public Expenses save(Expenses expenses) {
		// VAT is already calculated in front
		//		Float amount = Float.valueOf(expenses.getAmount());
		//		float vat = (amount * 20) / 100;
		//		expenses.setVat(String.valueOf(vat));
		em.persist(expenses);
		em.flush();
		return expenses;
	}

}