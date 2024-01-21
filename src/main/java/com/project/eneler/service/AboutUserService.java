package com.project.eneler.service;

import com.project.eneler.exception.AboutInfoException;
import com.project.eneler.model.dto.request.user.AboutUserRequest;

public interface AboutUserService {

  boolean addInfoAboutUser(AboutUserRequest aboutUserRequest) throws AboutInfoException;

}
