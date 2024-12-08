package com.example.finalproject.config;

import com.example.finalproject.model.entity.Product;
import com.example.finalproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {
    private final ProductRepository productRepository;


    @Scheduled(cron = "0 0 08 * * ?") //seconds minutes hours days month
    public void chronTask() {
        log.info("Daily product check started.");

        List<Product> productList = productRepository.findAll();
        LocalDate twoMonthsFromNow = LocalDate.now().plusMonths(2);


        for (Product product : productList) {
            if (product.getStockQuantity() < 10 && !product.isLowStock()) {
                product.setLowStock(true);
                log.info("Product {} is marked as low stock.", product.getName());
                product.setUpdatedAt(LocalDateTime.now());
                productRepository.save(product);

            } else if (product.getStockQuantity() >= 10 && product.isLowStock()) {
                product.setLowStock(false);
                productRepository.save(product);
            }


            if (product.getExpirationDate().isBefore(twoMonthsFromNow) && !product.isCloseToExpiration()) {
                product.setCloseToExpiration(true);
                log.info("Product {} is marked as close to expiration.", product.getName());
                product.setUpdatedAt(LocalDateTime.now());
                productRepository.save(product);
            } else if (product.getExpirationDate().isAfter(twoMonthsFromNow) && product.isCloseToExpiration()) {
                product.setCloseToExpiration(false);
                productRepository.save(product);
            }




        }

    }
}
