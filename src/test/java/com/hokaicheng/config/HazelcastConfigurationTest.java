package com.hokaicheng.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import org.junit.Test;

import static org.junit.Assert.*;

public class HazelcastConfigurationTest {
    private HazelcastConfiguration configuration = new HazelcastConfiguration();

    @Test
    public void hazelcastConfig() {
        Config config = configuration.hazelcastConfig();
        assertNotNull(config);
        assertEquals("hazelcast-instance", config.getInstanceName());
        assertEquals(EvictionPolicy.LRU, config.getMapConfig("externalUsers").getEvictionPolicy());
        assertEquals(-1, config.getMapConfig("externalUsers").getTimeToLiveSeconds());
        assertEquals(200, config.getMapConfig("externalUsers").getMaxSizeConfig().getSize());
        assertEquals(1, config.getSerializationConfig().getSerializerConfigs().size());
    }
}