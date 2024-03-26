package com.rafaelpavan.services.authorization;

import com.rafaelpavan.exceptions.transaction.UnauthorizedTransactionException;
import com.rafaelpavan.exceptions.transaction.UnavailableServiceException;
import com.rafaelpavan.models.dtos.authorization.Authorization;
import com.rafaelpavan.models.dtos.transaction.TransactionDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final RestClient restClient;

    public AuthorizationService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
                .build();
    }

    public void authorize(TransactionDto transaction) {
        try {
            var response = restClient.get()
                    .retrieve()
                    .toEntity(Authorization.class);

            System.out.println(response + "Response");

            if (response.getStatusCode().isError() || !Objects.requireNonNull(response.getBody()).isAuthorized()) {
                throw new UnauthorizedTransactionException("Unauthorized transaction");
            }
        } catch (HttpServerErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE)
                throw new UnavailableServiceException();
        }
    }
}
