package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired 
    private TodoRepository todoRepository;

    @Autowired 
    private UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override

    public void run(String... args) throws Exception {

        customerRepository.deleteAll();
 
        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();

        String larga1 = "Esta es una cadena muy largaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String larga2 = "Esta es otra cadena muy largaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        todoRepository.deleteAll();
        todoRepository.save(new Todo("tarea 1", 2, new Date(121,3,3), "juan@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 2", 2, new Date(121,3,10), "sebas@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 3", 8, new Date(121,3,30), "juan@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 4", 5, new Date(121,4,3), "juan@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 5", 4, new Date(121,4,30), "juan@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 6", 1, new Date(121,5,30), "santi@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 7", 6, new Date(121,6,3), "juan@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 8", 2, new Date(120,3,3), "laura@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 9", 1, new Date(120,3,30), "laura@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 10", 5, new Date(120,4,3), "andrea@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 11", 4, new Date(120,4,30), "laura@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 12", 1, new Date(120,5,30), "miguel@mail.com", "in progress"));
        todoRepository.save(new Todo(larga2, 8, new Date(120,6,3), "laura@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 14", 2, new Date(119,3,3), "pedro@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 15", 1, new Date(119,3,30), "pedro@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 16", 5, new Date(119,4,3), "pedro@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 17", 4, new Date(119,4,30), "fabian@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 18", 6, new Date(119,5,30), "pedro@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 19", 7, new Date(119,6,3), "pedro@mail.com", "in progress"));
        todoRepository.save(new Todo(larga1, 2, new Date(122,3,3), "cami@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 21", 1, new Date(122,3,30), "cami@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 22", 8, new Date(122,4,3), "cami@mail.com", "ready"));
        todoRepository.save(new Todo("tarea 23", 4, new Date(122,4,30), "cami@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 24", 9, new Date(122,5,30), "santi@mail.com", "in progress"));
        todoRepository.save(new Todo("tarea 25", 2, new Date(122,6,3), "cami@mail.com", "in progress"));

        userRepository.deleteAll();
        userRepository.save(new User("juan", "juan@mail.com"));
        userRepository.save(new User("sebas", "sebas@mail.com"));
        userRepository.save(new User("laura", "laura@mail.com"));
        userRepository.save(new User("pedro", "pedro@mail.com"));
        userRepository.save(new User("cami", "cami@mail.com"));
        userRepository.save(new User("miguel", "miguel@mail.com"));
        userRepository.save(new User("santi", "santi@mail.com"));
        userRepository.save(new User("fabian", "fabian@mail.com"));
        userRepository.save(new User("andrea", "andrea@mail.com"));
        userRepository.save(new User("angelica", "angelica@mail.com"));

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("dueDate").lt(new Date()));
        List<Todo> todoExpired =  mongoOperation.find(query1, Todo.class);

        System.out.println("Todos Expired:");
        System.out.println("-------------------------------");
        todoExpired.forEach(System.out::println);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("priority").gte(5).and("responsible").is("juan@mail.com"));
        List<Todo> todoPriority =  mongoOperation.find(query2, Todo.class);

        System.out.println("Todos with high priority:");
        System.out.println("-------------------------------");
        todoPriority.forEach(System.out::println);

        List<User> usuarios = mongoOperation.findAll(User.class);
        Query query3;
        List<Todo> todosDeUsuario = new ArrayList<Todo> ();
        List<User> usuariosConVariosTodos = new ArrayList<User> ();
        for (int i = 0; i < usuarios.size(); i++ ){
            query3 = new Query();
            query3.addCriteria(Criteria.where("responsible").is(usuarios.get(i).getEmail()));
            todosDeUsuario = mongoOperation.find(query3, Todo.class);
            if (todosDeUsuario.size() > 2){
                usuariosConVariosTodos.add(usuarios.get(i));
            }
        }
        System.out.println("Users with more than 2 todos");
        System.out.println("-------------------------------");
        usuariosConVariosTodos.forEach(System.out::println);
        
        Query query4 = new Query();
        query4.addCriteria(Criteria.where("description").regex(".{30,}"));

        List<Todo> todoDescription =  mongoOperation.find(query4, Todo.class);
        System.out.println("Todos with a long description:");
        System.out.println("-------------------------------");
        todoDescription.forEach(System.out::println);

    }

}