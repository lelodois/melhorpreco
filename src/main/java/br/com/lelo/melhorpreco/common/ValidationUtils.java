package br.com.lelo.melhorpreco.common;

import java.util.List;

public class ValidationUtils {

	private ValidationUtils() {
	}

	public static void notEmpty(List<String> itens, Object... objects) {
		for (Object item : objects) {
			if (item == null || item.toString().trim().isEmpty()) {
				throw new ItemObrigatorioException(itens);
			}
		}
	}
}
