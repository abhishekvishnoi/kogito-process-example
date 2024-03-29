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
package org.kie.kogito;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Inject
    MockService mockService;

    public Response reserveStock(String orderId, String failService) {
        LOGGER.info("Reserve Stock for order {}", orderId);
        return mockService.execute(failService, StockService.class, false);
    }

    public Response reserveStock(String orderId, String failService, String throwException) {
        LOGGER.info("Reserve Stock for order {}", orderId);
        return mockService.execute(failService, StockService.class, Boolean.parseBoolean(throwException));
    }

    public Response cancelStock(String id) {
        LOGGER.info("Cancel Stock for  order {}", id);
        return new Response(Response.Type.SUCCESS, id);
    }
}
