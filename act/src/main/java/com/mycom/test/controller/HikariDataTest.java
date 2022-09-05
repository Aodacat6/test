package com.mycom.test.controller;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ：songdalin
 * @date ：2022/8/25 下午 6:31
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Slf4j
@Component
@EnableScheduling
public class HikariDataTest {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;


	private HikariPoolMXBean poolProxy;

	@PostConstruct
	public void test() {
		new Thread(()->{
			HikariDataSource hikaridatasource = new HikariDataSource();
			hikaridatasource.setJdbcUrl(url);
			hikaridatasource.setUsername(username);
			hikaridatasource.setPassword(password);
			hikaridatasource.setDriverClassName("com.mysql.jdbc.Driver");
			hikaridatasource.setRegisterMbeans(true);
			hikaridatasource.setPoolName("HikariConnectionPool");

			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			ObjectName poolName = null;
			try {
				poolName = new ObjectName("com.zaxxer.hikari:type=Pool (" + hikaridatasource.getPoolName() + ")");
			} catch (MalformedObjectNameException e) {
				e.printStackTrace();
			}
			poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);

			Connection conn = null;
			try {
				conn = hikaridatasource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Statement sm = null;
			try {
				sm = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet rs = null;
			for (int i = 0; i < 999999999; i++) {
				try {
					rs = sm.executeQuery("select 1 from my_holiday_record  ");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			hikaridatasource.close();
		}).start();
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	//@Scheduled(fixedDelay = 200)
	public void HikariMonitor() {
		if(poolProxy == null) {
			log.info("==Hikari Monitor==: Hikari not initialized,please wait...");
		}else {
			log.info("==Hikari Monitor==: HikariPoolState = "
					+ "Active=[" + poolProxy.getActiveConnections() + "] "
					+ "Idle=[" + poolProxy.getIdleConnections() + "] "
					+ "Wait=["+poolProxy.getThreadsAwaitingConnection()+"] "
					+ "Total=["+poolProxy.getTotalConnections()+"]");
		}

	}

}
