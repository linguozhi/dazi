package com.dazi.core.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

    public static enum DataSourceEnum {
        MASTER("dataSource"),
        TCOINSLAVE("tcoinSlaveDataSource");

        private String dataSourceKey;

        DataSourceEnum(String dataSourceKey) {
            this.dataSourceKey = dataSourceKey;
        }

        public String getDataSourceKey() {
            return this.dataSourceKey;
        }
    }

    public static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();

    public static void setDataSource(DataSourceEnum dataSource) {
        dataSourceKey.set(dataSource.getDataSourceKey());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String s = dataSourceKey.get();
        dataSourceKey.remove();
        return s;
    }
}