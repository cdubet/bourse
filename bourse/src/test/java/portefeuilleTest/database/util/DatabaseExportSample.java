package portefeuilleTest.database.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;

import net.tuxanna.portefeuille.database.Database;

public class DatabaseExportSample
{

	//code from http://dbunit.sourceforge.net/faq.html#extract
    public static void main(String[] args) throws Exception
    {
        // database connection
    	TempFileDatabaseForTest tempDB=new TempFileDatabaseForTest();
    	
        @SuppressWarnings({ "rawtypes", "unused" })
		Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
        
        Connection jdbcConnection = DriverManager.getConnection(tempDB.getDatabaseName(), Database.getUsername(), Database.getPassword());
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        
        //tell which DB we have
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
        
        // full database export in workspace/protefeuilleTest/full.xml
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
                
        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("test.dtd"));
    }

}
