package validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import models.Expenses;

@Component("beforeCreateExpensesValidator")
public class ExpensesValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Expenses.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Expenses expenses = (Expenses) obj;
		if (checkInputString(expenses.getReason())) {
			errors.rejectValue("name", "name.empty");
		}

		if (checkInputString(expenses.getVat())) {
			errors.rejectValue("email", "email.empty");
		}
	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}
}
