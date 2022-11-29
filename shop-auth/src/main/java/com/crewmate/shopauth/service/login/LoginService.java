package com.crewmate.shopauth.service.login;

import com.crewmate.shopapicommon.model.Admin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LoginService {
    Admin getAdminInfo(Admin admin) throws JsonMappingException, JsonProcessingException;
}
