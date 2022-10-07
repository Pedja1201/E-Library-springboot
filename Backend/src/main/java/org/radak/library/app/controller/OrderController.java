package org.radak.library.app.controller;

import org.radak.library.app.aspect.Logged;
import org.radak.library.app.dto.BookDTO;
import org.radak.library.app.dto.CustomerDTO;
import org.radak.library.app.dto.OrderDTO;
import org.radak.library.app.model.Order;
import org.radak.library.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<OrderDTO>> getAll(Pageable pageable) {
        Page<Order> order = orderService.findAll(pageable);
        Page<OrderDTO> orders = order.map(new Function<Order, OrderDTO>() {
            public OrderDTO apply(Order order) {
                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getQuantity(), order.getPayment(),order.getCurrency(),
                        order.getDateOrder(),
                        new CustomerDTO(order.getCustomer().getId(), order.getCustomer().getUsername(),null,
                                order.getCustomer().getFirstName(),order.getCustomer().getLastName(),
                                order.getCustomer().getDateOfBirth(),order.getCustomer().getEmail(),
                                order.getCustomer().getPhoneNumber(),order.getCustomer().getPlace(),order.getCustomer().getAddress()),
                        new BookDTO(order.getBook().getId(), order.getBook().getName(),
                                order.getBook().getAuthor(),order.getBook().getCategory(),order.getBook().getPrice(),
                                order.getBook().getStatus(),null)
                );
                // Conversion logic

                return orderDTO;
            }
        });
        return new ResponseEntity<Page<OrderDTO>>(orders, HttpStatus.OK);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> get(@PathVariable("orderId") Long orderId) {
        Optional<Order> order = orderService.findOne(orderId);
        if (order.isPresent()) {
            OrderDTO orderDTO = new OrderDTO(order.get().getId(),order.get().getQuantity(),
                    order.get().getPayment(),order.get().getCurrency(),order.get().getDateOrder(),
                    new CustomerDTO(order.get().getCustomer().getId(),order.get().getCustomer().getUsername(),null,
                            order.get().getCustomer().getFirstName(), order.get().getCustomer().getLastName(),
                            order.get().getCustomer().getDateOfBirth(),order.get().getCustomer().getEmail(),
                            order.get().getCustomer().getPhoneNumber(),order.get().getCustomer().getPlace(),
                            order.get().getCustomer().getAddress()),
                    new BookDTO(order.get().getBook().getId(), order.get().getBook().getName(),
                            order.get().getBook().getAuthor(),order.get().getBook().getCategory(),
                            order.get().getBook().getPrice(), order.get().getBook().getStatus(),null));
            return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
        }
        return new ResponseEntity<OrderDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> create(@RequestBody Order order) {
        try {
            orderService.save(order);
            CustomerDTO customerDTO =  new CustomerDTO(order.getCustomer().getId(), order.getCustomer().getUsername(),null,
                    order.getCustomer().getFirstName(), order.getCustomer().getLastName(),  order.getCustomer().getDateOfBirth(),
                    order.getCustomer().getEmail(),order.getCustomer().getPhoneNumber(),order.getCustomer().getPlace(),
                    order.getCustomer().getAddress());
            BookDTO bookDTO =  new BookDTO(order.getBook().getId(), order.getBook().getName(),
                    order.getBook().getAuthor(),order.getBook().getCategory(),order.getBook().getPrice(),
                    order.getBook().getStatus(),null);

            OrderDTO orderDTO = new OrderDTO(order.getId(), order.getQuantity(),order.getPayment(),order.getCurrency(),
                    order.getDateOrder(),customerDTO, bookDTO);

            return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<OrderDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.PUT)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> update(@PathVariable("orderId") Long orderId,
                                                @RequestBody Order updatedOrder) {
        Order order = orderService.findOne(orderId).orElse(null);
        if (order != null) {
            updatedOrder.setId(orderId);
            orderService.save(updatedOrder);
            CustomerDTO customerDTO =  new CustomerDTO(updatedOrder.getCustomer().getId(), updatedOrder.getCustomer().getUsername(),null,
                    updatedOrder.getCustomer().getFirstName(), updatedOrder.getCustomer().getLastName(), updatedOrder.getCustomer().getDateOfBirth(),
                    updatedOrder.getCustomer().getEmail(), updatedOrder.getCustomer().getPhoneNumber(), updatedOrder.getCustomer().getPlace(),
                    updatedOrder.getCustomer().getAddress());
            BookDTO bookDTO =  new BookDTO(updatedOrder.getBook().getId(), updatedOrder.getBook().getName(),
                    updatedOrder.getBook().getAuthor(),updatedOrder.getBook().getCategory(),updatedOrder.getBook().getPrice(),
                    updatedOrder.getBook().getStatus(),null);

            OrderDTO orderDTO = new OrderDTO(updatedOrder.getId(), updatedOrder.getQuantity(),updatedOrder.getPayment(),
                    updatedOrder.getCurrency(),updatedOrder.getDateOrder(), customerDTO, bookDTO);

            return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
        }
        return new ResponseEntity<OrderDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.DELETE)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> delete(@PathVariable("orderId") Long orderId) {
        if (orderService.findOne(orderId).isPresent()) {
            orderService.delete(orderId);
            return new ResponseEntity<OrderDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<OrderDTO>(HttpStatus.NOT_FOUND);
    }
}
