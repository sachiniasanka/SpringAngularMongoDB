package com.javasampleapproach.angular4mongodb.controller;

import java.util.List;

import javax.validation.Valid;

import com.javasampleapproach.angular4mongodb.repo.SearchRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.javasampleapproach.angular4mongodb.model.Customer;
import com.javasampleapproach.angular4mongodb.repo.CustomerMongoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerMongoRepository customerRepository;

    @Autowired
    SearchRepository searchRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        System.out.println("Get all Customers...");

        return customerRepository.findAll();
    }

    @PostMapping("/customers/create")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        System.out.println("Create Customer: " + customer.getName() + "...");
        customer.set_id(ObjectId.get());
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") ObjectId id, @RequestBody Customer customer) {
        System.out.println("Update Customer with ID = " + id + "...");

        Customer customerData = (Customer) customerRepository.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerData.setName(customer.getName());
        customerData.setAge(customer.getAge());
        Customer updatedcustomer = customerRepository.save(customerData);
        return new ResponseEntity<>(updatedcustomer, HttpStatus.OK);
    }

    @GetMapping("/customer/search/{id}")
    public ResponseEntity<?> searchCustomer(@PathVariable("id") ObjectId id){

        Customer customer= (Customer) customerRepository.findById(id);
        if (customer!=null){
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") ObjectId id) {
        System.out.println("Delete Customer with ID = " + id + "...");

        customerRepository.delete(customerRepository.findById(id));

        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/customers/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All Customers...");

        customerRepository.deleteAll();

        return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
    }

//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	public Customer getCustomer(@PathVariable String name) {
//		ArithmeticOperators.Log.info("Getting customer with ID: {}.", name);
//		return customerRepository.findOne(name);
//	}
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ArrayList<Customer> getAllcustomers(){
//		return customerRepository.getAllCustomers();
//	}

//	@GetMapping("/customers/{name}")
//	public ArrayList<Customer> getAllCustomer(@PathVariable("name")String name) {
//		System.out.println("get all customer name"+name);
//		return customerRepository.getAllCustomer(name);
//
//	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public Customer getCustomerById(@PathVariable("id") ObjectId id) {
//		return customerRepository.findBy_id(id);
//	}

//	private ResponseEntity<?> searchByEny(@RequestParam("any")String id){
//		try{
//			return ResponseEntity.ok(customerRepository.findById(id));
//		}catch (Exception e){
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//	}

    //	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Customer customers) {
//		customers.set_id(id);
//		customerRepository.save(customers);
//	}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)

    public List<Customer> getCustomerById(@PathVariable("id") ObjectId id) {

        System.out.println("ggggggg");
        return customerRepository.findById(id);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam String search) {
        model.addAttribute("carList", searchRepository.searchCustomer(search));
        model.addAttribute("search", search);
        return "home";
    }

}
