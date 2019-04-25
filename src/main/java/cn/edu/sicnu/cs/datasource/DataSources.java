package cn.edu.sicnu.cs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author kaier
 * @date 2019-04-21 18:14
 */
public class DataSources extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        return DataSourceSwitch.getDataSourceType();
    }

}
