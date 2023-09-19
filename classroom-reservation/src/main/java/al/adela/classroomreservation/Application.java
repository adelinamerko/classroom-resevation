package al.adela.classroomreservation;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:applicationContext.xml" })
public class Application {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.run(Application.class, args);
	}


	@Bean
	ServletRegistrationBean camelServlet() {
		// use a @Bean to register the Camel servlet which we need to do
		// because we want to use the camel-servlet component for the Camel REST service
		ServletRegistrationBean mapping = new ServletRegistrationBean();
		mapping.setName("CamelServlet");
		mapping.setLoadOnStartup(1);
		
		// CamelHttpTransportServlet is the name of the Camel servlet to use
		mapping.setServlet(new CamelHttpTransportServlet());
		mapping.addUrlMappings("/classroom-reservation/*");
		return mapping;
	}

}
