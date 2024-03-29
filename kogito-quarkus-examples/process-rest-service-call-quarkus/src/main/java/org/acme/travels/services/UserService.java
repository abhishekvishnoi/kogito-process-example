/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.travels.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.travels.quarkus.User;
import org.acme.travels.rest.UsersRemoteService;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class UserService {

    @Inject
    @RestClient
    UsersRemoteService usersRemoteService;

    @Fallback(fallbackMethod = "missingUser")
    public User get(String username) {  
        User user = usersRemoteService.get(username);
        System.out.println("found user " + user.getFirstName());
        return user;
    }

    public User missingUser(String username) {
        System.out.println("executing fallback method.!!");
        return null;
    }
}
