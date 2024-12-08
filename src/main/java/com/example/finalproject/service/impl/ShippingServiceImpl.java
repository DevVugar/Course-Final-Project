package com.example.finalproject.service.impl;

import com.example.finalproject.enums.PaymentStatus;
import com.example.finalproject.enums.ShippingStatus;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.exception.PaymentException;
import com.example.finalproject.exception.ShippingException;
import com.example.finalproject.mapping.ShippingMapping;
import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;
import com.example.finalproject.model.entity.Payment;
import com.example.finalproject.model.entity.Shipping;
import com.example.finalproject.repository.PaymentRepository;
import com.example.finalproject.repository.ShippingRepository;
import com.example.finalproject.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingMapping shippingMapping;
    private final ShippingRepository shippingRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public ShippingResponseDto add(ShippingRequestDto requestDto) {
        log.info("Adding shipping for payment ID: {}", requestDto.getPaymentId());

        Payment payment = paymentRepository.findById(requestDto.getPaymentId()).orElseThrow(() -> new NotFoundException("payment not found"));
        if (payment.getPaymentStatus() != PaymentStatus.completed) {
            log.warn("Payment not completed.");
            throw new PaymentException("Payment not successfully");
        }

        if (payment.getShipping() != null) {
            log.warn("Shipping already exists.");
            throw new ShippingException("This payment is paid");
        }

        Shipping shipping = shippingMapping.toEntity(requestDto);
        shipping.setPayment(payment);
        shipping.setStatus(ShippingStatus.pending);
        shipping.setCreatedAt(LocalDate.now());

        try {
            shippingRepository.save(shipping);


            if (!shippingMethod(shipping)) {
                throw new PaymentException("shipping processing failed");
            }
            shipping.setStatus(ShippingStatus.delivered);
            payment.setShipping(shipping);
            shippingRepository.save(shipping);

        } catch (ShippingException ex) {
            shipping.setStatus(ShippingStatus.failed);
            paymentRepository.save(payment);
            throw new PaymentException("Payment failed: " + ex.getMessage());

        } catch (PaymentException ex) {
            throw new PaymentException("Payment failed: " + ex.getMessage());
        }
        ShippingResponseDto ans = shippingMapping.toResponse(shipping);
        ans.setCustomerName(payment.getUser().getFullName());

        log.info("Shipping process completed.");
        return ans;
    }

    private boolean shippingMethod(Shipping ans) {
        log.info("Processing shipping.");
        return true;
    }

    @Override
    public ShippingResponseDto getById(Long id) {
        log.info("Getting shipping by ID: {}", id);
        return shippingMapping.toResponse(shippingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Shipping not found")));
    }

    @Override
    public ShippingResponseDto update(Long id, String shippingAddress) {
        log.info("Updating shipping ID: {}", id);
        Shipping shipping = shippingRepository.findById(id).orElseThrow(() -> new NotFoundException("Shipping not found"));
        shipping.setShippingAddress(shippingAddress);
        shipping.setUpdatedAt(LocalDate.now());

        Shipping ans = shippingRepository.save(shipping);

        log.info("Shipping updated.");
        return shippingMapping.toResponse(ans);
    }


    @Override
    public List<ShippingResponseDto> getAll() {
        log.info("Getting all shippings.");
        return shippingRepository.findAll().stream().map(shipping ->
                shippingMapping.toResponse(shipping)).collect(Collectors.toList());
    }
}
