package io.github.ferrazsergio.gestao.multidb.infrastructure.config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "io.github.ferrazsergio.gestao.multidb.historico")
public class MongoConfig {

    @Bean(name = "pedidoHistoricoMongoRepository")
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/multidb"));
    }

}
