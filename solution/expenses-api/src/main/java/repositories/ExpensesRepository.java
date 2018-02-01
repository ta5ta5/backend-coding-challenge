package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import models.Expenses;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "expenses", path = "expenses")
public interface ExpensesRepository extends JpaRepository<Expenses, Long>, ExpensesRepositoryCustom {

	Expenses save(Expenses expenses);


}
