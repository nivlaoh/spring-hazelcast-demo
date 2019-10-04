package com.hokaicheng.config;

import com.hazelcast.config.*;
import com.hokaicheng.constant.AppConstant;
import com.hokaicheng.model.ExternalUser;
import com.hokaicheng.serialization.ExternalUserSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {
    @Bean
    public Config hazelcastConfig() {
        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(new ExternalUserSerializer()).setTypeClass(ExternalUser.class);

        Config config = new Config();

        NetworkConfig network = config.getNetworkConfig();
        network.setPortAutoIncrement(true);
        network.setPort(5701).setPortCount(20);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig()
                .addMember("localhost").setEnabled(true);

        config.setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName(AppConstant.EXTERNAL_USER_MAP)
                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setTimeToLiveSeconds(-1))
                .getSerializationConfig()
                .addSerializerConfig(serializerConfig);

        return config;
    }
}
