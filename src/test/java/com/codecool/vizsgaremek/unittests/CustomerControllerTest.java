package com.codecool.vizsgaremek.unittests;

import com.codecool.vizsgaremek.controller.CustomerController;
import com.codecool.vizsgaremek.entity.Customer;
import com.codecool.vizsgaremek.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
 class CustomerControllerTest {
   private List<Customer> customers= Arrays.asList(
           new Customer(1L,"John Doe", "1111 Budapest Valami utca 34.", "john.doe@email.hk", "+36151122334",null),
           new Customer(2L,"Jane Doe", "9000 Győr Virág utca 3.", "jane.doe@gmail.kom", "+36111234567",null),
           new Customer(3L,"Joe Doe", "5000 Szolnok Valami utca 12.", "john.doe@email.it", "+36229999999",null),
           new Customer(4L,"Jessica Doe", "2000 Szentendre Otthon utca 4.", "jessica.doe@citromail.hun", "+36228888888",null),
           new Customer(5L,"Tim Doe", "3000 Hatvan Puszta tér 5.", "tim.doe@email.fr", "+36337689130",null)
   );
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;


    @Test
    void testFindAllCustomers(){
        when(customerService.findAll()).thenReturn(customers);
        List<Customer> customersResult=customerController.findAll();
        assertEquals(customers,customersResult);
    }

   @Test
   void findCustomerById_ReturnsCorrectCustomer(){
      when(customerService.findCustomerById(anyLong())).thenReturn(customers.get(2));
      Customer expectedCustomer=customerController.findCustomerById(3).getBody();
      assertEquals(customers.get(2),expectedCustomer);
   }
/*
   @Test
   void findCustomerById_CreateErrorLog_WhenIndexOutOfBounds(CapturedOutput capturedOutput) {
     when(customerService.findCustomerById(100)).thenThrow(CustomerNotFoundException.class);
     customerController.findCustomerById(100);
     assertThat(capturedOutput.getErr().contains("CUSTOMER NOT FOUND"));
   }*/

}
