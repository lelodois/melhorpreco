package br.com.lelo.melhorpreco;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();

		emFactory.setDataSource(dataSource);
		emFactory.setPackagesToScan(MelhorPrecoApplication.BASE_PACKAGE);
		emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		additionalProperties.put("hibernate.show_sql", true);
		additionalProperties.put("hibernate.format_sql", true);
		additionalProperties.put("hibernate.hbm2ddl.auto", "create");
		additionalProperties.put("hibernate.enable_lazy_load_no_trans", true);
		additionalProperties.put("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate4.SpringSessionContext");

		emFactory.setJpaProperties(additionalProperties);

		return emFactory;
	}
}
