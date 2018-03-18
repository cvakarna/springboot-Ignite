package com.cva.demo.config;


import java.util.Arrays;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cva.demo.IgniteSampleApplication;


@Configuration
public class IgniteConfig {

	private static final Logger logger = Logger.getLogger(IgniteSampleApplication.class);
	@Bean
    public IgniteConfiguration igniteConfiguration(@Value("property.key") String serverIp){
		logger.getLogger("org").setLevel(Level.INFO);
       logger.info("Ip:"+serverIp);
       serverIp = "localhost";
		TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
		discoverySpi.setLocalPort(47500);
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		String ip = serverIp + ":47500..47509";
		ipFinder.setAddresses(Arrays.asList(serverIp,ip));
		// Overriding IP finder.
		discoverySpi.setIpFinder(ipFinder);

		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setDiscoverySpi(discoverySpi);
		// Ignite persistence configuration.
		DataStorageConfiguration storageCfg = new DataStorageConfiguration();

		// Enabling the persistence.
		storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(
				true);
        // Applying settings.
		cfg.setDataStorageConfiguration(storageCfg);
		cfg.setPeerClassLoadingEnabled(true);
        return cfg;

		
	}
	
	@Bean
	public Ignite getIgnite(IgniteConfiguration igniteConfig) throws IgniteException
	{     logger.info("server started");
		 //Ignition.setClientMode(true);
		  final Ignite ignite = Ignition.start(igniteConfig);
		  ignite.active(true);
		  logger.info("server started");
		  return ignite;
	}
	
}
