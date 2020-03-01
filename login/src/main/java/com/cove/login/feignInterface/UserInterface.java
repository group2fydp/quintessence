package com.cove.login.feignInterface;

import com.cove.login.constants.MicroServiceConstants;
import com.cove.login.requestDTO.UserRequestDTO;
import com.cove.login.responseDTO.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = MicroServiceConstants.UserMicroServiceConstants.BASE)
@Service
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface UserInterface {
    @RequestMapping(value = MicroServiceConstants.UserMicroServiceConstants.SEARCH_USER)
    UserResponseDTO searchUser(@RequestBody UserRequestDTO requestDTO);

    @RequestMapping(value = MicroServiceConstants.UserMicroServiceConstants.UPDATE_USER)
    void updateUser(@RequestBody UserResponseDTO responseDTO);
}
