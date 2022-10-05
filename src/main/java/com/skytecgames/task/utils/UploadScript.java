package com.skytecgames.task.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.Connection;
import java.sql.SQLException;

public class UploadScript {
    public void upload() {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
        rdp.addScript(new ClassPathResource("Script_TEST_BASE.sql"));
//        rdp.addScript(new ClassPathResource(
//                "mysql-scripts/secondScript.sql"));

        try(Connection conn = pool.getConnection()) {
            rdp.populate(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
