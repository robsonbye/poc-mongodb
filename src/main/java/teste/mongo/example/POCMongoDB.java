package teste.mongo.example;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
* The tutorial from http://docs.mongodb.org/ecosystem/tutorial/getting-started-with-java-driver/
*/
public class POCMongoDB {
   // CHECKSTYLE:OFF
   /**
    * Run this main method to see the output of this quick example.
    *
    * @param args takes no args
    * @throws UnknownHostException if it cannot connect to a MongoDB instance at localhost:27017
    */
   public static void main(final String[] args) throws UnknownHostException {
       boolean testKeyId = true;
       int foreachTestCount = 100000;
	   
	   Date dateInitial = new Date();
	   System.out.println("Init: "+dateInitial);
	   // connect to the local database server
       MongoClient mongoClient = new MongoClient();


       // get handle to "mydb"
       DB db = mongoClient.getDB("db");

       // get a collection object to work with
       DBCollection coll = db.getCollection("ListOfValues.TipoProduto");

       // drop all the data in it
       //coll.drop();

       // now, lets add lots of little documents to the collection so we can explore queries and cursors
       for (int i = 1; i <= foreachTestCount; i++) {
    	   coll.save(generateBasicObject(i, testKeyId));
       }
       
       System.out.println("total # of documents after inserting " + coll.getCount());
       
       Date dateInsert = new Date();
       System.out.println("Insert Time: "+(dateInsert.getTime() - dateInitial.getTime()));
       
       
       // now use a query to get 1 document out
       for (int i = 1; i <= foreachTestCount; i++) {
    	   DBCursor cursor = coll.find(searchByKey(i,testKeyId));
       
    	   try {
               while (cursor.hasNext()) {
            	   BasicDBObject pessoa = (BasicDBObject) cursor.next();
            	   BasicDBObject newPessoa = new BasicDBObject().append("$set", new BasicDBObject().append("name", "FooBarTested")); 
            	   coll.update(pessoa, newPessoa);
               }
           } finally {
               cursor.close();
           }
       }
       
       Date dateUpdate = new Date();
       System.out.println("Update Time: "+(dateUpdate.getTime() - dateInsert.getTime()));
       
       DBCursor cursor = coll.find();
       try {
//	       while( cursor.hasNext() ){
//	    	   System.out.println(cursor.next());
//	       }
       } finally {
           cursor.close();
       }
      

       // release resources
       //db.dropDatabase();
       mongoClient.close();
       
       Date dateFinal = new Date();
       System.out.println("SearchTime: "+(dateFinal.getTime() - dateUpdate.getTime()));
       
   }
   
   
   public static BasicDBObject generateBasicObject(int key, boolean testKeyId){
	   BasicDBObject productType = new BasicDBObject();
	   if(testKeyId){
		   productType.append("_id", ""+key);
	   }
	   productType.append("value_id", ""+key);
	   productType.append("name", "FooBar");
	            
	   BasicDBObject end = new BasicDBObject();
	   end.append("rua", "Rua Fulano");
	   end.append("numero",""+key);
	   end.append("bairro", "Centro");
	            
	   productType.append("address", end);
	   
	   return productType;
   }
   
   public static BasicDBObject searchByKey(int key, boolean testKeyId){
	   BasicDBObject query = new BasicDBObject((testKeyId ? "_id" : "value_id"), ""+key);
	   return query;
   }
}
