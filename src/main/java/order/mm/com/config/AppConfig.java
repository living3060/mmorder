package order.mm.com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableScheduling
public class AppConfig {
    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Bean(name = "default_databaseSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /*
    @Bean
    public ServletRegistrationBean druidServlet() {
        log.info("init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", userName);// 用户名
        initParameters.put("loginPassword", password);// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        //initParameters.put("allow", allowIp); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }*/
}
