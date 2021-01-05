package com.osj4532.jpastudy.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class JpaConfig (
        @Value("\${spring.datasource.url}") val url: String,
        @Value("\${spring.datasource.driver-class-name}") val driverClassName: String,
        @Value("\${spring.datasource.username}") val userName: String,
        @Value("\${spring.datasource.password}") val password: String,
){
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.osj4532.jpastudy.entity")

        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaPropertyMap(additionalProperties())

        return em
    }

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(driverClassName)
        dataSource.url = url
        dataSource.username = userName
        dataSource.password = password
        return dataSource
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory().`object`

        return transactionManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    private fun additionalProperties(): Map<String, Any> {
        return mapOf(
                "hibernate.dialect" to "org.hibernate.dialect.H2Dialect",
                "hibernate.use_sql_comments" to true,
                "hibernate.format_sql" to true,
                "hibernate.id.new_generator_mappings" to true,
                "hibernate.physical_naming_strategy" to "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy"
        )
    }
}