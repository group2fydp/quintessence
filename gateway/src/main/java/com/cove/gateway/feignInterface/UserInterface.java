package com.cove.gateway.feignInterface;

import com.cove.gateway.constants.MicroServiceConstants;
import com.cove.gateway.responseDTO.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@FeignClient(name = MicroServiceConstants.USER_SERVICE, configuration = FeignClientConfiguration.class)
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface UserInterface {
    @RequestMapping(MicroServiceConstants.UserMicroServiceConstants.FETCH_USER_BY_USERNAME)
    Optional<UserResponseDTO> fetchUserByUsername(@PathVariable String username, @RequestHeader("X-TenantID") String tenantId);
}
