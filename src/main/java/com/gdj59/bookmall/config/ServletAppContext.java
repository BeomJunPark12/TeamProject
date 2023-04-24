package com.gdj59.bookmall.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdj59.bookmall.interceptor.AdminInterceptor;
import com.gdj59.bookmall.interceptor.LoginInterceptor;
import com.gdj59.bookmall.mapper.AdminMapper;
import com.gdj59.bookmall.mapper.BookMapper;
import com.gdj59.bookmall.mapper.OrderMapper;
import com.gdj59.bookmall.mapper.PaymentMapper;
import com.gdj59.bookmall.mapper.userMapper;

// Spring MVC 프로젝트에 관련된 설정을 하는 클래스
@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록
@EnableWebMvc
// 스캔할 패키지를 지정
@ComponentScan("com.gdj59.bookmall.interceptor")
@ComponentScan("com.gdj59.bookmall.controller")
@ComponentScan("com.gdj59.bookmall.dao")
@ComponentScan("com.gdj59.bookmall.service")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {

	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration reg = registry.addInterceptor(loginInterceptor);
		reg.addPathPatterns("/member/*");
		reg.excludePathPatterns(
				"/member/deleteMember.do",
				"/member/join_proc.do",
				"cart/addCart.do",
				"/member/overLap.do",
				"/member/login_proc.do",
				"/member/loginPage.do",
				"/member/join.do");
		
		InterceptorRegistration reg2 = registry.addInterceptor(adminInterceptor);
		reg2.addPathPatterns("/admin/*");
	}

	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		return source;
	}
	
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}

	@Bean
	public MapperFactoryBean<userMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<userMapper> factoryBean = new MapperFactoryBean<userMapper>(userMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	@Bean
	public MapperFactoryBean<PaymentMapper> getPaymentMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<PaymentMapper> factoryBean = new MapperFactoryBean<PaymentMapper>(PaymentMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	@Bean
	public MapperFactoryBean<BookMapper> getBookMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BookMapper> factoryBean = new MapperFactoryBean<BookMapper>(BookMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	@Bean
	public MapperFactoryBean<OrderMapper> getOrderMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<OrderMapper> factoryBean = new MapperFactoryBean<OrderMapper>(OrderMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	@Bean
	public MapperFactoryBean<AdminMapper> getAdminMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<AdminMapper> factoryBean = new MapperFactoryBean<AdminMapper>(AdminMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	

	
	
	
	
	
	
	
	
	


}
